package ca.appolizer.outreach.ui.profile.profile;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import ca.appolizer.outreach.MainActivity;
import ca.appolizer.outreach.R;
import ca.appolizer.outreach.data.dto.StudentDto;

public class ProfileFragment extends Fragment {

    private TextInputEditText firstNameTxt, lastNameTxt, emailTxt, phoneTxt, aboutTxt;
    private AppCompatSpinner availabilitySpinner;
    private ProfileViewModel profileViewModel;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firstNameTxt = view.findViewById(R.id.firstNameTxt);
        lastNameTxt = view.findViewById(R.id.lastNameTxt);
        emailTxt = view.findViewById(R.id.emailTxt);
        phoneTxt = view.findViewById(R.id.phoneTxt);
        aboutTxt = view.findViewById(R.id.aboutTxt);
        availabilitySpinner = view.findViewById(R.id.availabilitySpinner);

        emailTxt.setEnabled(false);
        activateEdition();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = requireActivity().getSharedPreferences(MainActivity.SHARED_PREFERENCES, MODE_PRIVATE);
        long userId = sharedPreferences.getLong("user_id", 0);
        String token = sharedPreferences.getString("token", "");

        profileViewModel = new ViewModelProvider(this, new ProfileViewModelProvider(token, userId)).get(ProfileViewModel.class);
        ArrayAdapter<String> studentAvailability = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.availability));
        profileViewModel.getUserProfile().observe(requireActivity(), userProfileResponse -> {
            if (userProfileResponse != null) {
                StudentDto studentDto = userProfileResponse.getStudent();
                firstNameTxt.setText(studentDto.getFirstName());
                lastNameTxt.setText(studentDto.getLastName());
                emailTxt.setText(studentDto.getEmail());
                phoneTxt.setText(studentDto.getContactNumber());
                aboutTxt.setText(studentDto.getAbout());

                availabilitySpinner.setSelection(studentDto.getAvailability());
            }
        });

        availabilitySpinner.setAdapter(studentAvailability);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuEdit:
                activateEdition();
                break;
            case R.id.menuSave:
                Toast.makeText(getContext(), "Saving info", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void activateEdition() {
        firstNameTxt.setEnabled(!firstNameTxt.isEnabled());
        lastNameTxt.setEnabled(!lastNameTxt.isEnabled());
        phoneTxt.setEnabled(!phoneTxt.isEnabled());
        aboutTxt.setEnabled(!aboutTxt.isEnabled());
        availabilitySpinner.setEnabled(!availabilitySpinner.isEnabled());
    }

    interface SkillSetHandlerCallback {
        String onDeleteSkillset();
    }
}
