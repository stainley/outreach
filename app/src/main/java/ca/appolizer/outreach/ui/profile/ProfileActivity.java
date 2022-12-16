package ca.appolizer.outreach.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.appolizer.outreach.MainActivity;
import ca.appolizer.outreach.R;
import ca.appolizer.outreach.databinding.ActivityProfileBinding;
import ca.appolizer.outreach.model.Student;
import ca.appolizer.outreach.model.User;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText emailTxt;
    private EditText phoneTxt;
    private EditText aboutTxt;

    private Spinner availabilitySpinner;
    private long userId;

    private ProfileViewModel profileViewModel;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstNameText = findViewById(R.id.firstNameTxt);
        lastNameText = findViewById(R.id.lastNameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        emailTxt.setEnabled(false);
        phoneTxt = findViewById(R.id.phoneTxt);
        aboutTxt = findViewById(R.id.aboutTxt);
        availabilitySpinner = findViewById(R.id.availabilitySpinner);

        //this.disableTextEdition();
        String[] availability = {"Unavailable", "Available"};

        ArrayAdapter<String> studentAvailability = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, availability);
        availabilitySpinner.setAdapter(studentAvailability);

        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES, MODE_PRIVATE);
        User user = (User) intent.getSerializableExtra("student_profile");
        userId = user != null ? user.getStudent().getId() : sharedPreferences.getLong("user_id", 0);
        profileViewModel = new ViewModelProvider(this, new ProfileViewModelProvider(token, userId)).get(ProfileViewModel.class);

        profileViewModel.getUserProfile().observe(this, userProfileResponse -> {
            if (userProfileResponse != null) {
                Student student = userProfileResponse.getStudent();
                firstNameText.setText(student.getFirstName());
                lastNameText.setText(student.getLastName());
                emailTxt.setText(student.getEmail());
                phoneTxt.setText(student.getContactNumber());
                aboutTxt.setText(student.getAbout());

                availabilitySpinner.setSelection(student.getAvailability());
            }
        });

        List<String> completeSkill = new ArrayList<>();

        profileViewModel.getUserProfile().observe(this, completeSkills -> {
            if (completeSkills != null) {
                String[] mySkills = completeSkills.getCompleteSkills().split(",");
                completeSkill.addAll(Arrays.asList(mySkills));
            }
        });

        System.out.println(completeSkill.size());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES, MODE_PRIVATE);
        userId = sharedPreferences.getLong("user_id", 0);
    }

    private void enableEdition(boolean status) {
        this.firstNameText.setEnabled(status);
        this.lastNameText.setEnabled(status);
        this.phoneTxt.setEnabled(status);
        this.aboutTxt.setEnabled(status);
    }
}
