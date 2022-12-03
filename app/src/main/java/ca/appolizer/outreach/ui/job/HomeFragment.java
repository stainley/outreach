package ca.appolizer.outreach.ui.job;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.databinding.FragmentHomeBinding;
import ca.appolizer.outreach.model.Job;

public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {
    private FragmentHomeBinding binding;

    private SearchView jobSearchView;

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
        adapter = new JobAdapter(this);
        homeViewModel = new ViewModelProvider(this, new JobViewModel(getContext(), token, adapter)).get(HomeViewModel.class);

        homeViewModel.getJobList().observe(getViewLifecycleOwner(), jobListUpdateObserver);
        jobListRecycler.setAdapter(adapter);

        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("HomeFragment:onViewCreated->", "Invoked");


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_job_description, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setQueryHint("Type to filter job");
                searchView.setOnQueryTextListener(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}