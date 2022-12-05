package ca.appolizer.outreach.register.repository;

import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.model.response.UserResponse;
import ca.appolizer.outreach.model.request.AbstractRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepositoryImpl implements RegisterRepository<AbstractRequest> {

    @Override
    public UserResponse registerStudent(AbstractRequest request) {
        final UserResponse[] userResponse = new UserResponse[1];

        Call<UserResponse> register = ApiClient.getUserService().register(request);
        register.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    userResponse[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
        return userResponse[0];
    }

    @Override
    public UserResponse registerCompany(AbstractRequest request) {
        return null;
    }
}
