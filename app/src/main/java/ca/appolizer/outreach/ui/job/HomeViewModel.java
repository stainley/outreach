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
import ca.appolizer.outreach.data.dto.JobDto;
import ca.appolizer.outreach.data.dto.JobListDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<JobDto>> mJobList;
    private List<JobDto> jobDtoList;

    private JobAdapter jobAdapter;

    private final Context context;
    private final String token;

    public HomeViewModel(Context context, String token, JobAdapter jobAdapter) {
        mJobList = new MutableLiveData<>();
        jobDtoList = new ArrayList<>();
        this.context = context;
        this.token = token;
        this.jobAdapter = jobAdapter;
        init();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<JobDto>> getJobList() {
        return mJobList;
    }

    private void init() {
        fetchAllJobs();
        mJobList.setValue(jobDtoList);
    }

    private List<JobDto> fetchAllJobs() {
        Call<JobListDto> fetchAllJobs = ApiClient.getUserServiceWithToken(token).fetchAllJobs();
        fetchAllJobs.enqueue(new Callback<JobListDto>() {
            @Override
            public void onResponse(Call<JobListDto> call, Response<JobListDto> response) {
                if (response.isSuccessful()) {
                    List<JobDto> jb = response.body().getJobs();
                    jobDtoList.addAll(jb);
                    Log.i("SERVICIO ", "" + jobDtoList.size());
                    jobAdapter.add(jb);
                }
            }

            @Override
            public void onFailure(Call<JobListDto> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return jobDtoList;
    }
}