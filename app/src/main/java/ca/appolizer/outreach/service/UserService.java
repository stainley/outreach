package ca.appolizer.outreach.service;

import ca.appolizer.outreach.model.JobList;
import ca.appolizer.outreach.model.request.AbstractUserRequest;
import ca.appolizer.outreach.model.request.StudentSkillsetRequest;
import ca.appolizer.outreach.model.request.StudentUserRequest;
import ca.appolizer.outreach.model.response.SkillsetResponse;
import ca.appolizer.outreach.model.response.StudentSkillsetResponse;
import ca.appolizer.outreach.model.response.UserProfileResponse;
import ca.appolizer.outreach.model.response.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST(value = "api/signin")
    Call<UserResponse> login(@Body StudentUserRequest studentRequest);

    @POST(value = "api/signup")
    Call<UserResponse> register(@Body AbstractUserRequest userRequest);

    @GET(value = "api/jobs")
    Call<JobList> fetchAllJobs();

    /**
     * Get student info
     *
     * @param id
     * @return
     */
    @GET(value = "api/student/{id}")
    Call<UserProfileResponse> getStudentInfo(@Path(value = "id") long id);

    @GET(value = "api/skillset")
    Call<SkillsetResponse> getAllSkillSet();

    @POST(value = "api/studentSkillset")
    Call<StudentSkillsetResponse> addStudentSkillset(@Body StudentSkillsetRequest request);

}