package ca.appolizer.outreach.ui.job;

import android.content.Intent;
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

import java.util.List;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.databinding.FragmentJobBinding;
import ca.appolizer.outreach.model.Job;

public class JobFragment extends Fragment implements SearchView.OnQueryTextListener {
    private FragmentJobBinding binding;

    private SearchView jobSearchView;

    private RecyclerView jobListRecycler;
    private JobAdapter adapter;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentJobBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();
        String token = intent.getStringExtra("token");
        String email = intent.getStringExtra("email");
        long student_id = intent.getLongExtra("id", 0);

        jobListRecycler = binding.jobList;
        adapter = new JobAdapter(this, student_id, email);
        homeViewModel = new ViewModelProvider(this, new JobViewModel(getContext(), token, adapter)).get(HomeViewModel.class);

        homeViewModel.getJobList().observe(getViewLifecycleOwner(), jobListUpdateObserver);
        jobListRecycler.setAdapter(adapter);
        setHasOptionsMenu(true);
        return root;
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