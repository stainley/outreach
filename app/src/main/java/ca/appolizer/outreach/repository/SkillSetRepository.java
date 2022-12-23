package ca.appolizer.outreach.repository;

import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.data.network.responses.StudentSkillsetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillSetRepository {

    public String deleteStudentSkillset(String token, long skillsetId) {
        StringBuilder message = new StringBuilder();
        Call<StudentSkillsetResponse> studentSkillSet = ApiClient.getUserServiceWithToken(token).deleteStudentSkillset(skillsetId);

        studentSkillSet.enqueue(new Callback<StudentSkillsetResponse>() {
            @Override
            public void onResponse(Call<StudentSkillsetResponse> call, Response<StudentSkillsetResponse> response) {
                if (response.isSuccessful()) {
                    message.append(response.message());
                } else {
                    message.append(response.message());
                }
            }

            @Override
            public void onFailure(Call<StudentSkillsetResponse> call, Throwable t) {

            }
        });
        return message.toString();
    }
}
