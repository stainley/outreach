package ca.appolizer.outreach;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import ca.appolizer.outreach.databinding.ActivityMainBinding;
import ca.appolizer.outreach.model.User;
import ca.appolizer.outreach.ui.job.JobFragment;

public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREFERENCES = "profile_info";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private TextView loginEmailView;
    private TextView loginNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        String token = intent.getStringExtra("token");
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");

        JobFragment jobFragment = new JobFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", id);
        bundle.putString("token", token);
        jobFragment.setArguments(bundle);

        if (savedInstanceState != null) {
            savedInstanceState.putString("email", email);
            loginEmailView.setText(savedInstanceState.getString("email"));
        }

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        View headerView = navigationView.getHeaderView(0);
        loginNameView = headerView.findViewById(R.id.loginNameTxt);
        loginEmailView = headerView.findViewById(R.id.loginEmailTxt);
        User user = new User();
        if (email != null) {
            user.setEmail(email);
            this.saveUserDetailSP(user);
        }

        loginNameView.setText(name);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow).setOpenableLayout(drawer).build();

        //Navigation.findNavController(this, R.id.nav_home).navigate(R.id.nav_home, bundle);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        String value = sp.getString("email", "");
        if (!value.equals("")) {
            loginEmailView.setText(value);
            Log.i("MainActivity - Inside: onResume: ", value);
        }
        Log.i("MainActivity onResume: ", value);
        // TODO: from SP get the username and password and invoke the service again to
    }

    private void saveUserDetailSP(User user) {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE).edit();
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void logout() {
        this.deleteSharedPreferences(SHARED_PREFERENCES);
    }
}