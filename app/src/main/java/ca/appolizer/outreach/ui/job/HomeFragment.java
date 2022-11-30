package ca.appolizer.outreach.ui.job;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.appolizer.outreach.databinding.FragmentHomeBinding;
import ca.appolizer.outreach.model.Job;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textHome;
        final RecyclerView jobList = binding.jobList;

        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("Java Developer", "Java Developer with experience on Microservices Spring Boot"));
        jobs.add(new Job("Senior Back-End Developers, Java/Spring Boot", "7 year job"));
        jobs.add(new Job("Senior Java Developer - Payments", "1 year job"));
        jobs.add(new Job("Android Developer", "2 years"));
        jobs.add(new Job("Senior iOS Engineer", "Description Job"));
        final JobAdapter jobAdapter = new JobAdapter(jobs, getContext());
        jobList.setAdapter(jobAdapter);
        jobList.setLayoutManager(new LinearLayoutManager(getContext()));

        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}