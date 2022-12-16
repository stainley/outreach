package ca.appolizer.outreach.ui.profile.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.model.Skillset;
import ca.appolizer.outreach.model.Student;
import ca.appolizer.outreach.model.response.SkillsetResponse;
import ca.appolizer.outreach.model.response.UserProfileResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<UserProfileResponse> userProfile;
    private MutableLiveData<List<Skillset>> skillMutableLiveData;
    private MutableLiveData<List<Skillset>> skillsetLiveData;

    private final long id;
    private final String token;

    public ProfileViewModel(String token, long id) {
        userProfile = new MutableLiveData<>();
        skillMutableLiveData = new MutableLiveData<>();
        skillsetLiveData = new MutableLiveData<>();

        this.token = token;
        this.id = id;
        getStudentProfile(token, id);
        getAllSkillSet(token);
    }

    public MutableLiveData<UserProfileResponse> getUserProfile() {
        return userProfile;
    }

    public MutableLiveData<List<Skillset>> getSkillMutableLiveData() {
        return skillMutableLiveData;
    }

    public LiveData<List<Skillset>> getSkillsetLiveData() {
        return skillsetLiveData;
    }

    public Student getStudentProfile(String token, long id) {
        final Student[] student = new Student[1];

        Call<UserProfileResponse> studentInfo = ApiClient.getUserServiceWithToken(token).getStudentInfo(id);
        studentInfo.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful()) {
                    Student student1 = response.body().getStudent();
                    student[0] = student1;
                    userProfile.setValue(response.body());
                    System.out.println();

                    if (response.body().getStudent().getSkillsets().size() > 0) {

                        skillsetLiveData.setValue(response.body().getStudent().getSkillsets());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {

            }
        });
        return student[0];
    }

    private void getAllSkillSet(String token) {

        Call<SkillsetResponse> allSkillSet = ApiClient.getUserServiceWithToken(token).getAllSkillSet();
        allSkillSet.enqueue(new Callback<SkillsetResponse>() {
            @Override
            public void onResponse(Call<SkillsetResponse> call, Response<SkillsetResponse> response) {
                if (response.isSuccessful()) {
                    skillMutableLiveData.setValue(response.body().getSkillset());
                }
            }

            @Override
            public void onFailure(Call<SkillsetResponse> call, Throwable t) {

            }
        });
    }

}
