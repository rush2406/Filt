package org.technozion.technozion18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.technozion.technozion18.api_services.responses.ProfileResponse;
import org.technozion.technozion18.common.AuthenticationListener;
import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.User;
import org.technozion.technozion18.models.UserProfile;
import org.technozion.technozion18.presenters.AuthenticationPresenter;
import org.technozion.technozion18.presenters.UserPresenter;

public class SignupActivity extends AppCompatActivity {

    TextView loginButton;
    MaterialEditText emailEditText, passwordEditText, confirmPasswordEditText, lastNameEditText, firstNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.passwordConfirm);
        lastNameEditText = findViewById(R.id.passwordConfirm);
        firstNameEditText = findViewById(R.id.firstName);

        loginButton = findViewById(R.id.logInlink);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });

        findViewById(R.id.buttonSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    protected boolean validate(){
        return true;
    }

    protected void signup(){
        if(!validate())
            return;
        final User user = new User();
        user.setEmail(emailEditText.getText().toString());
        user.setFirst_name(firstNameEditText.getText().toString());
        user.setLast_name(lastNameEditText.getText().toString());
        user.setUsername(user.getEmail());
        user.setPassword(passwordEditText.getText().toString());
        user.createUser(new OnEntityReceivedListener<User>(this) {
            @Override
            public void onReceived(User entity) {
                Toast.makeText(SignupActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();

                //authenticate the user
                new AuthenticationPresenter().authenticate(user, new AuthenticationListener(SignupActivity.this) {
                    @Override
                    public void onAuthenticated(String token) {
                        //save user token
                        MyApplication.getInstance().getPrefManager().saveUserAuthToken(token);

                        //check if profile updated
                        new UserPresenter().getCurrentUserProfile(new OnEntityReceivedListener<ProfileResponse>(SignupActivity.this) {
                            @Override
                            public void onReceived(ProfileResponse entity) {
                                if(entity.getProfile() == null)
                                    startActivity(new Intent(SignupActivity.this, UpdateProfileActivity.class));
                                else
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onFailure() {
                        showMessage("Incorrect username or password!");
                    }
                });
            }
        });
    }
}
