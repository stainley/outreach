package ca.appolizer.outreach.ui.profile;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import ca.appolizer.outreach.R;

public class ProfileMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile_main);

        TabLayout tabLayout = findViewById(R.id.tabMenuSkill);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = supportFragmentManager.beginTransaction();

                int position = tabLayout.getSelectedTabPosition();

                switch (position) {

                    case 1:
                        fragment = new SkillSetFragment();
                        break;
                    case 0:
                        fragment = new ProfileFragment();
                        break;
                    default:
                        Log.i("INVALID", "Invalid");
                }
                transaction.replace(R.id.fragment_profile_container, fragment).commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
