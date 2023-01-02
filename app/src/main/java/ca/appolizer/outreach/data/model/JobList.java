package ca.appolizer.outreach.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobList {
    @SerializedName("jobs")
    private List<Job> jobs;
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
