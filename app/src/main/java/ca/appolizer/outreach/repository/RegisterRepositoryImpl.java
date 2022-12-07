package ca.appolizer.outreach.repository;

import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.model.request.AbstractUserRequest;
import ca.appolizer.outreach.model.response.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepositoryImpl implements RegisterRepository<AbstractUserRequest> {

    @Override
    public UserResponse register(AbstractUserRequest request) {
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
}
