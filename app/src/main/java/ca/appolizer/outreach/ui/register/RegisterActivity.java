package ca.appolizer.outreach.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.data.model.User;
import ca.appolizer.outreach.data.network.requests.AbstractUserRequest;
import ca.appolizer.outreach.data.network.requests.EmployeeUserRequest;
import ca.appolizer.outreach.data.network.requests.StudentUserRequest;
import ca.appolizer.outreach.repository.RegisterRepositoryImpl;
import ca.appolizer.outreach.util.EmailUtil;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailTxt;
    private EditText passwordTxt;

    private boolean isTypeCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        emailTxt = findViewById(R.id.txtEmailAddress);
        passwordTxt = findViewById(R.id.txtPassword);
        SwitchCompat companySwitch = findViewById(R.id.companySwitch);
        Button createBtn = findViewById(R.id.createAccount);
        createBtn.setOnClickListener(this::register);
        companySwitch.setChecked(false);
        companySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> isTypeCompany = isChecked);
    }

    private void register(View view) {
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Email or Password is empty", Toast.LENGTH_SHORT).show();
        } else {
            if (validateEmail(email)) {

                AbstractUserRequest request;

                if (isTypeCompany) {
                    request = new EmployeeUserRequest();
                    request.setEmail(email);
                    request.setPassword(password);
                    request.setUser_type_id(User.TypeUser.EMPLOYEE.getCode());
                    new RegisterRepositoryImpl().register(request);
                } else {
                    request = new StudentUserRequest();
                    request.setEmail(email);
                    request.setPassword(password);
                    request.setUser_type_id(User.TypeUser.STUDENT.getCode());
                    new RegisterRepositoryImpl().register(request);
                }
            } else {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateEmail(String email) {
        return EmailUtil.isValidEmailAddrRegex("^\\S+@\\S+$", email);
    }


}