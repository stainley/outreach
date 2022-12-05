package ca.appolizer.outreach.service;

import ca.appolizer.outreach.model.JobList;
import ca.appolizer.outreach.model.request.AbstractRequest;
import ca.appolizer.outreach.model.request.UserRequest;
import ca.appolizer.outreach.model.response.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST(value = "api/signin")
    Call<UserResponse> login(@Body UserRequest userRequest);

    @POST(value = "api/signup")
    Call<UserResponse> register(@Body AbstractRequest userRequest);

    @GET(value = "api/jobs")
    Call<JobList> fetchAllJobs();
}