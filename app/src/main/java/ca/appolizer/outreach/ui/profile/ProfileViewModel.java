package ca.appolizer.outreach.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.model.Student;
import ca.appolizer.outreach.model.response.UserProfileResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> firstname;
    private MutableLiveData<UserProfileResponse> userProfile;
    private final long id;
    private final String token;

    public ProfileViewModel(String token, long id) {
        firstname = new MutableLiveData<>();
        userProfile = new MutableLiveData<>();
        this.token = token;
        this.id = id;
        getStudentProfile(token, id);
    }

    public MutableLiveData<UserProfileResponse> getUserProfile() {
        return userProfile;
    }

    public LiveData<String> getFirstName() {
        return firstname;
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
                    firstname.setValue(student1.getFirst_name());
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {

            }
        });


        return student[0];
    }
}
