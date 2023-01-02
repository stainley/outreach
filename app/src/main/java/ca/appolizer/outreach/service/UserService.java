package ca.appolizer.outreach.service;

import ca.appolizer.outreach.data.network.requests.AbstractUserRequest;
import ca.appolizer.outreach.data.network.requests.StudentUserRequest;
import ca.appolizer.outreach.data.network.responses.UserProfileResponse;
import ca.appolizer.outreach.data.network.responses.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService extends JobService, SkillService {
    @POST(value = "api/signin")
    Call<UserResponse> login(@Body StudentUserRequest studentRequest);

    @POST(value = "api/signup")
    Call<UserResponse> register(@Body AbstractUserRequest userRequest);

    /**
     * Get student info
     *
     * @param id
     * @return
     */
    @GET(value = "api/student/{id}")
    Call<UserProfileResponse> getStudentInfo(@Path(value = "id") long id);

}