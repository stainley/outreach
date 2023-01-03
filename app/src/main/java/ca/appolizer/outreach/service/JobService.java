package ca.appolizer.outreach.service;

import ca.appolizer.outreach.data.dto.JobListDto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JobService {

    @GET(value = "api/jobs")
    Call<JobListDto> fetchAllJobs();

}
