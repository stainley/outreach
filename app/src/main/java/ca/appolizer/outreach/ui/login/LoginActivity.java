package ca.appolizer.outreach.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ca.appolizer.outreach.MainActivity;
import ca.appolizer.outreach.R;
import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.data.model.User;
import ca.appolizer.outreach.data.network.requests.StudentUserRequest;
import ca.appolizer.outreach.data.network.responses.UserResponse;
import ca.appolizer.outreach.ui.register.RegisterActivity;
import ca.appolizer.outreach.ui.custom.CustomProgressDialog;
import ca.appolizer.outreach.ui.job.JobFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailAddressTxt;
    private EditText passwordTxt;

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

        Button createAccountBtn = findViewById(R.id.createAccount);
        createAccountBtn.setOnClickListener(this::invokeCreateAccountView);
    }

    private void invokeCreateAccountView(View view) {
        Intent createAccountIntent = new Intent(this, RegisterActivity.class);

        startActivity(createAccountIntent);
    }

    public StudentUserRequest createRequest() {
        StudentUserRequest studentRequest = new StudentUserRequest();
        studentRequest.setEmail(emailAddressTxt.getText().toString());
        studentRequest.setPassword(passwordTxt.getText().toString());

        return studentRequest;
    }

    public void loginUser(StudentUserRequest studentRequest) {

        final CustomProgressDialog customProgressDialog = new CustomProgressDialog(this);
        customProgressDialog.show(this, "Login", "Login", true, true);

        Call<UserResponse> userResponseCall = ApiClient.getUserService().login(studentRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equals("true")) {
                        Fragment homeFragment = new JobFragment();
                        FragmentManager fm = getSupportFragmentManager();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        User user = response.body().getUser();

                        intent.putExtra("id", user.getId());

                        intent.putExtra("token", response.body().getToken());
                        intent.putExtra("user", user);
                        intent.putExtra("first_name", response.body().getUser().getStudent().getFirstName() != null ? response.body().getUser().getStudent().getFirstName() : "");
                        intent.putExtra("last_name", response.body().getUser().getStudent().getLastName() != null ? response.body().getUser().getStudent().getLastName() : "");

                        intent.putExtra("email", response.body().getUser().getEmail());
                        intent.putExtra("password", studentRequest.getPassword());

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
                Toast.makeText(getApplicationContext(), "UserRequest Fail: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.i("REST_API", t.getMessage());
                Log.i("REST_API", t.getLocalizedMessage());
            }
        });
    }
}