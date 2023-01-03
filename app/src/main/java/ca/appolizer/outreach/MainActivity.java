package ca.appolizer.outreach;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;

import ca.appolizer.outreach.data.dto.StudentDto;
import ca.appolizer.outreach.data.dto.UserDto;

import ca.appolizer.outreach.data.sp.UserSharePreference;
import ca.appolizer.outreach.databinding.ActivityMainBinding;
import ca.appolizer.outreach.ui.job.JobFragment;
import ca.appolizer.outreach.ui.profile.ProfileActivity;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREFERENCES = "profile_info";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView loginEmailView;
    private TextView loginNameView;

    private static String token;

    private UserDto userDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        Intent intent = getIntent();

        token = intent.getStringExtra("token");
        String email = intent.getStringExtra("email");
        String firstName = intent.getStringExtra("first_name");
        String lastName = intent.getStringExtra("last_name");
        String password = intent.getStringExtra("password");
        userDto = (UserDto) intent.getSerializableExtra("userDto");

        /*JobFragment jobFragment = new JobFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", id);
        bundle.putString("token", token);
        bundle.putString("email", email);
        jobFragment.setArguments(bundle);*/

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
        if (email != null) {
            //userDto = getUser(email, firstName, lastName);
            this.saveUserDetailSP(userDto);
        }

        loginNameView.setText(firstName + " " + lastName);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_gallery //, R.id.nav_slideshow
        ).setOpenableLayout(drawer).build();

        //Navigation.findNavController(this, R.id.nav_home).navigate(R.id.nav_home, bundle);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        String nameValue = sp.getString("name", "");
        String emailValue = sp.getString("email", "");

        token = sp.getString("token", "");
        //id = sp.getLong("user_id", 0);

        if (!emailValue.equals("")) {
            loginEmailView.setText(emailValue);
        }
        if (!nameValue.equals("")) {
            loginNameView.setText(nameValue);
        }

    }

    private void saveUserDetailSP(UserDto userDto) {
        UserSharePreference<Object> sharePreference = UserSharePreference.getInstance();
        sharePreference.persistSharePreference(getApplicationContext(), "name", userDto.getStudent().getFirstName() + " " + userDto.getStudent().getLastName());
        sharePreference.persistSharePreference(getApplicationContext(), "email", userDto.getEmail());
        sharePreference.persistSharePreference(getApplicationContext(), "token", token != null ? token : "");
        sharePreference.persistSharePreference(getApplicationContext(), "user_id", userDto.getStudent().getId());

        //SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE).edit();
        //editor.putString("name", userDto.getStudent().getFirstName() + " " + userDto.getStudent().getLastName());
        //editor.putString("email", userDto.getEmail());
        //editor.putString("password", userDto.getPassword());
        //editor.putString("token", token != null ? token : "");
        //editor.putLong("user_id", userDto.getStudent().getId());
        //editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                Intent settingIntent = new Intent(this, ProfileActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.action_section:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    private void logout() {
        this.deleteSharedPreferences(SHARED_PREFERENCES);
    }
}