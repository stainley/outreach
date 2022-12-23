package ca.appolizer.outreach.ui.job;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.data.model.Job;
import ca.appolizer.outreach.data.model.JobList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Job>> mJobList;
    private List<Job> jobList;

    private JobAdapter jobAdapter;

    private final Context context;
    private final String token;

    public HomeViewModel(Context context, String token, JobAdapter jobAdapter) {
        mJobList = new MutableLiveData<>();
        jobList = new ArrayList<>();
        this.context = context;
        this.token = token;
        this.jobAdapter = jobAdapter;
        init();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<Job>> getJobList() {
        return mJobList;
    }

    private void init() {
        fetchAllJobs();
        mJobList.setValue(jobList);
    }

    private List<Job> fetchAllJobs() {
        Call<JobList> fetchAllJobs = ApiClient.getUserServiceWithToken(token).fetchAllJobs();
        fetchAllJobs.enqueue(new Callback<JobList>() {
            @Override
            public void onResponse(Call<JobList> call, Response<JobList> response) {
                if (response.isSuccessful()) {
                    List<Job> jb = response.body().getJobs();
                    jobList.addAll(jb);
                    Log.i("SERVICIO ", "" + jobList.size());
                    jobAdapter.add(jb);
                }
            }

            @Override
            public void onFailure(Call<JobList> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return jobList;
    }
}