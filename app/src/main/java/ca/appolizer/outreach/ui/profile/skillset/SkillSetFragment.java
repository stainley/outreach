package ca.appolizer.outreach.ui.profile.skillset;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.data.dto.SkillsetDto;
import ca.appolizer.outreach.data.network.requests.StudentSkillsetRequest;
import ca.appolizer.outreach.ui.profile.profile.ProfileViewModel;
import ca.appolizer.outreach.ui.profile.profile.ProfileViewModelProvider;

public class SkillSetFragment extends Fragment {
    List<SkillsetDto> skills = new ArrayList<>();
    private RecyclerView recyclerViewSkill;
    private SkillAdapter adapter;
    private ProfileViewModel skillSetViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skills, container, false);
        recyclerViewSkill = view.findViewById(R.id.skillRecycleView);
        EditText skillYearTxt = view.findViewById(R.id.skillYearTxt);
        MaterialButton addSkillButton = view.findViewById(R.id.addSkillButton);
        AutoCompleteTextView dropdownSkill = view.findViewById(R.id.skillSetSpinner);

        skillSetViewModel = new ViewModelProvider(this, new ProfileViewModelProvider(getToken(), getUserId())).get(ProfileViewModel.class);

        skillSetViewModel.getSkillMutableLiveData().observe(requireActivity(), skillet -> skills.addAll(skillet));

        ArrayAdapter<SkillsetDto> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.list_item, skills);
        dropdownSkill.setAdapter(arrayAdapter);

        final SkillsetDto[] skillsetDto = new SkillsetDto[1];

        dropdownSkill.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(getContext(), "SELECTED: " + skills.get(position), Toast.LENGTH_SHORT).show();
            skillsetDto[0] = skills.get(position);
        });

        addSkillButton.setOnClickListener(viewButton -> {
            String year = skillYearTxt.getText().toString();

            if (year.equals("") || dropdownSkill.getText().toString().equals("")) {
                Toast.makeText(requireActivity(), "Could not be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            StudentSkillsetRequest request = new StudentSkillsetRequest((int) getUserId(), skillsetDto[0].getId(), Integer.parseInt(year));
            skillSetViewModel.addStudentSkillset(getToken(), request);

            arrayAdapter.notifyDataSetChanged();
        });

        recyclerViewSkill.setLayoutManager(new GridLayoutManager(getContext(), 1));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        skillSetViewModel.getSkillsetLiveData().observe(requireActivity(), skillset -> {
            adapter = new SkillAdapter(skillset);
            recyclerViewSkill.setAdapter(adapter);
            adapter.notifyItemRangeChanged(0, skillset.size());
            //adapter.notifyDataSetChanged();
        });
    }

    private String getToken() {
        SharedPreferences sp = requireActivity().getSharedPreferences("profile_info", Context.MODE_PRIVATE);
        return sp.getString("token", "");
    }

    private long getUserId() {
        SharedPreferences sp = requireActivity().getSharedPreferences("profile_info", Context.MODE_PRIVATE);
        return sp.getLong("user_id", 0);
    }

}