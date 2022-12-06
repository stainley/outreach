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
import ca.appolizer.outreach.model.TypeUser;
import ca.appolizer.outreach.model.request.AbstractUserRequest;
import ca.appolizer.outreach.model.request.EmployeeUserRequest;
import ca.appolizer.outreach.model.request.StudentUserRequest;
import ca.appolizer.outreach.repository.RegisterRepositoryImpl;
import ca.appolizer.outreach.util.EmailUtil;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailTxt;
    private EditText passwordTxt;
    private Button createBtn;

    private SwitchCompat companySwitch;
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
        companySwitch = findViewById(R.id.companySwitch);
        createBtn = findViewById(R.id.createAccount);
        createBtn.setOnClickListener(this::register);
        companySwitch.setChecked(false);
        companySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                isTypeCompany = true;
            } else {
                isTypeCompany = false;
            }
        });
    }

    private void register(View view) {
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Email or Password is empty", Toast.LENGTH_SHORT).show();
        } else if (!email.equals("") && !password.equals("")) {
            if (validateEmail(email)) {

                AbstractUserRequest request;

                if (isTypeCompany) {
                    request = new EmployeeUserRequest();
                    request.setEmail(email);
                    request.setPassword(password);
                    new RegisterRepositoryImpl().registerCompany(request);
                } else {
                    request = new StudentUserRequest();
                    request.setEmail(email);
                    request.setPassword(password);
                    ((StudentUserRequest) request).setUser_type_id(TypeUser.STUDENT.getCode());
                    new RegisterRepositoryImpl().registerStudent(request);
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