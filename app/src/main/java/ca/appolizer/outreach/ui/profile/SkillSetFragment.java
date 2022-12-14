package ca.appolizer.outreach.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.model.Skill;

public class SkillSetFragment extends Fragment {

    private List<String> items;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skills, container, false);

        MaterialButton addSkillButton = view.findViewById(R.id.addSkillButton);
        AutoCompleteTextView dropdownSkill = view.findViewById(R.id.skillSetSpinner);

        items = Arrays.asList("Angular", "Java", "Python", "C#");
        List<Skill> skills = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.list_item, items);
        dropdownSkill.setAdapter(arrayAdapter);

        RecyclerView recyclerViewSkill = view.findViewById(R.id.skillRecycleView);


        dropdownSkill.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(getContext(), "SELECTED: " + items.get(position), Toast.LENGTH_SHORT).show();
            skills.add(new Skill(items.get(position)));
        });

        addSkillButton.setOnClickListener(v -> {
            arrayAdapter.notifyDataSetChanged();
        });

        skills.addAll(Arrays.asList(new Skill("Angular"), new Skill("Java"), new Skill("React"), new Skill("TypeScript")));
        final SkillAdapter adapter = new SkillAdapter(skills);

        recyclerViewSkill.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerViewSkill.setAdapter(adapter);
        return view;
    }

}
