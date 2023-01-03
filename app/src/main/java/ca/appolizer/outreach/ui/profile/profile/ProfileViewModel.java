package ca.appolizer.outreach.ui.profile.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.data.dto.SkillsetDto;
import ca.appolizer.outreach.data.dto.StudentDto;
import ca.appolizer.outreach.data.network.requests.StudentSkillsetRequest;
import ca.appolizer.outreach.data.network.responses.SkillsetResponse;
import ca.appolizer.outreach.data.network.responses.StudentSkillsetResponse;
import ca.appolizer.outreach.data.network.responses.UserProfileResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = ProfileViewModel.class.getName();
    private MutableLiveData<UserProfileResponse> userProfile;
    private MutableLiveData<List<SkillsetDto>> skillMutableLiveData;
    private MutableLiveData<List<SkillsetDto>> skillsetLiveData;

    private MutableLiveData<String> messageLive;

    public ProfileViewModel(String token, long id) {
        messageLive = new MutableLiveData<>();
        userProfile = new MutableLiveData<>();
        skillMutableLiveData = new MutableLiveData<>();
        skillsetLiveData = new MutableLiveData<>();

        getStudentProfile(token, id);
        getAllSkillSet(token);
    }

    public MutableLiveData<UserProfileResponse> getUserProfile() {
        return userProfile;
    }

    public MutableLiveData<List<SkillsetDto>> getSkillMutableLiveData() {
        return skillMutableLiveData;
    }

    public LiveData<List<SkillsetDto>> getSkillsetLiveData() {
        return skillsetLiveData;
    }

    public MutableLiveData<String> getMessageLive() {
        return messageLive;
    }

    public StudentDto getStudentProfile(String token, long id) {
        final StudentDto[] studentDto = new StudentDto[1];

        Call<UserProfileResponse> studentInfo = ApiClient.getUserServiceWithToken(token).getStudentInfo(id);
        studentInfo.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if (response.isSuccessful()) {
                    StudentDto studentDto1 = response.body().getStudent();
                    studentDto[0] = studentDto1;
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
        return studentDto[0];
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

    public void addStudentSkillset(String token, StudentSkillsetRequest request) {

        Call<StudentSkillsetResponse> studentSkillset = ApiClient.getUserServiceWithToken(token).addStudentSkillset(request);
        studentSkillset.enqueue(new Callback<StudentSkillsetResponse>() {
            @Override
            public void onResponse(Call<StudentSkillsetResponse> call, Response<StudentSkillsetResponse> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, response.message());
                    messageLive.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<StudentSkillsetResponse> call, Throwable t) {

            }
        });
    }

}
