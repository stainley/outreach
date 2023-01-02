package ca.appolizer.outreach.service;

import ca.appolizer.outreach.data.network.requests.StudentSkillsetRequest;
import ca.appolizer.outreach.data.network.responses.SkillsetResponse;
import ca.appolizer.outreach.data.network.responses.StudentSkillsetResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SkillService {
    @GET(value = "api/skillset")
    Call<SkillsetResponse> getAllSkillSet();

    @POST(value = "api/studentSkillset")
    Call<StudentSkillsetResponse> addStudentSkillset(@Body StudentSkillsetRequest request);

    @DELETE(value = "api/studentSkillset/{student_skillset_id}")
    Call<StudentSkillsetResponse> deleteStudentSkillset(@Path(value = "student_skillset_id") long skillsetId);

}
