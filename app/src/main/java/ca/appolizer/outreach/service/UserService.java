package ca.appolizer.outreach.service;

import ca.appolizer.outreach.model.JobList;
import ca.appolizer.outreach.model.UserRequest;
import ca.appolizer.outreach.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST(value = "api/signin")
    Call<UserResponse> login(@Body UserRequest userRequest);

    @GET(value = "api/jobs")
    Call<JobList> fetchAllJobs();
}