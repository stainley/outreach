package ca.appolizer.outreach.ui.myjob;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.appolizer.outreach.databinding.FragmentMyJobBinding;
import ca.appolizer.outreach.data.network.responses.JobApplied;

public class MyJobFragment extends Fragment {

    private FragmentMyJobBinding binding;
    private MyJobAdapter adapter;
    private List<JobApplied> jobApplieds = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyJobViewModel myJobViewModel = new ViewModelProvider(this).get(MyJobViewModel.class);

        binding = FragmentMyJobBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        myJobViewModel.setContext(getContext());
        final RecyclerView myJobsRecyclerView = binding.myjobList;
        //MyJobViewModel jobViewModel = new MyJobViewModel(getContext());

        myJobViewModel.getJobsApplied().observe(getViewLifecycleOwner(), jobListUpdateObserver);
        adapter = new MyJobAdapter(jobApplieds, getContext());
        //myJobViewModel.getText().observe(getViewLifecycleOwner(), jobListUpdateObserver);
        myJobsRecyclerView.setAdapter(adapter);
        return root;
    }

    private Observer<List<JobApplied>> jobListUpdateObserver = new Observer<List<JobApplied>>() {
        @Override
        public void onChanged(List<JobApplied> jobs) {
            if (jobs != null) {
                jobApplieds = jobs;
                adapter.addJobsApplied(jobs);
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}