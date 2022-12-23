package ca.appolizer.outreach.ui.myjob;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ca.appolizer.outreach.data.network.responses.JobApplied;
import ca.appolizer.outreach.data.database.JobDbHelper;

public class MyJobViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<JobApplied>> jobsApplied;

    private Context context;

    public MyJobViewModel() {
        mText = new MutableLiveData<>();
        jobsApplied = new MutableLiveData<>();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<JobApplied>> getJobsApplied() {
        jobsApplied.setValue(getAllMyJobsApplied());
        return jobsApplied;
    }

    private List<JobApplied> getAllMyJobsApplied() {
        JobDbHelper db = new JobDbHelper(context);
        return db.getJobs();
    }
}