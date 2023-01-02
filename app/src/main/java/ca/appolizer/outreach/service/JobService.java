package ca.appolizer.outreach.service;

import ca.appolizer.outreach.data.model.JobList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JobService {

    @GET(value = "api/jobs")
    Call<JobList> fetchAllJobs();

}
