package ca.appolizer.outreach.ui.job;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.appolizer.outreach.databinding.FragmentHomeBinding;
import ca.appolizer.outreach.model.Job;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    private RecyclerView jobListRecycler;
    private JobAdapter adapter;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        String token = getActivity().getIntent().getStringExtra("token");
        Log.i("HomeFragment:onCreateView->", "Invoked");
        //final TextView textView = binding.textHome;
        jobListRecycler = binding.jobList;
        adapter = new JobAdapter(requireActivity());
        homeViewModel = new ViewModelProvider(this, new JobViewModel(getContext(), token, adapter)).get(HomeViewModel.class);

        homeViewModel.getJobList().observe(getViewLifecycleOwner(), jobListUpdateObserver);
        jobListRecycler.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("HomeFragment:onViewCreated->", "Invoked");
    }

    private Observer<List<Job>> jobListUpdateObserver = new Observer<List<Job>>() {
        @Override
        public void onChanged(List<Job> jobs) {
            if (jobs != null) {
                adapter.jobs = jobs;
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