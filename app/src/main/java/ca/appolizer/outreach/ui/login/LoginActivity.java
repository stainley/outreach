package ca.appolizer.outreach.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ca.appolizer.outreach.MainActivity;
import ca.appolizer.outreach.R;
import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.model.UserRequest;
import ca.appolizer.outreach.model.UserResponse;
import ca.appolizer.outreach.ui.custom.CustomProgressDialog;
import ca.appolizer.outreach.ui.job.HomeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailAddressTxt;
    private EditText passwordTxt;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAddressTxt = findViewById(R.id.txtEmailAddress);
        passwordTxt = findViewById(R.id.txtPassword);
        Button completeRegister = findViewById(R.id.btnCompleteRegister);
        completeRegister.setOnClickListener(view -> {
            loginUser(createRequest());
        });
    }

    public UserRequest createRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(emailAddressTxt.getText().toString());
        userRequest.setPassword(passwordTxt.getText().toString());

        return userRequest;
    }

    public void loginUser(UserRequest userRequest) {

        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this);
        customProgressDialog.show(this, "Login", "Login", true, true);


        Call<UserResponse> userResponseCall = ApiClient.getUserService().login(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equals("true")) {
                        Fragment homeFragment = new HomeFragment();
                        FragmentManager fm = getSupportFragmentManager();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("token", response.body().getToken());
                        intent.putExtra("id", response.body().getUser().getId());
                        intent.putExtra("email", response.body().getUser().getEmail());
                        intent.putExtra("password", userRequest.getPassword());
                        // Bundle bundle = new Bundle();
                        //bundle.putString("token", response.body().getToken());
                        customProgressDialog.dismiss();
                        startActivity(intent);

                    } else {
                        customProgressDialog.dismiss();
                        customProgressDialog.cancel();
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(getApplicationContext(), jsonObject.get("message").toString(), Toast.LENGTH_LONG).show();
                        customProgressDialog.cancel();
                        customProgressDialog.dismiss();
                    } catch (JSONException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                customProgressDialog.dismiss();
                customProgressDialog.cancel();
                Toast.makeText(getApplicationContext(), "Request Fail: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.i("REST_API", t.getMessage());
                Log.i("REST_API", t.getLocalizedMessage());
            }
        });
    }


}