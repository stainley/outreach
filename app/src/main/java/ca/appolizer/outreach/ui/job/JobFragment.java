package ca.appolizer.outreach.ui.job;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.data.dto.JobDto;
import ca.appolizer.outreach.data.sp.UserSharePreference;
import ca.appolizer.outreach.databinding.FragmentJobBinding;


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

        //Intent intent = getActivity().getIntent();
        //String token = intent.getStringExtra("token");
        //String email = intent.getStringExtra("email");
        //long student_id = intent.getLongExtra("id", 0);

        Map<String, Object> sharePreference = UserSharePreference.getInstance().getSharePreference(requireContext());
        String token = String.valueOf(sharePreference.get("token"));
        long student_id = sharePreference.get("user_id") != null ? (long) sharePreference.get("user_id") : 0L;

        String email = String.valueOf(sharePreference.get("email"));

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

    private Observer<List<JobDto>> jobListUpdateObserver = new Observer<List<JobDto>>() {
        @Override
        public void onChanged(List<JobDto> jobDtos) {
            if (jobDtos != null) {
                adapter.jobDtos = jobDtos;
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