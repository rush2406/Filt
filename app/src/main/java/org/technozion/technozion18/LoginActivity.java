package org.technozion.technozion18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.technozion.technozion18.api_services.responses.ProfileResponse;
import org.technozion.technozion18.common.AuthenticationListener;
import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.User;
import org.technozion.technozion18.presenters.AuthenticationPresenter;
import org.technozion.technozion18.presenters.UserPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends BaseActivity {

    RelativeLayout loginForm;
    CircleImageView logoImageView;
    CardView signUpButton,loginButton;
    MaterialEditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        logoImageView = findViewById(R.id.logo);
        loginForm = findViewById(R.id.loginForm);
        signUpButton = findViewById(R.id.buttonSignup);
        loginButton = findViewById(R.id.buttonLogIn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //authenticate the user
                User user = new User();
                user.setUsername(email.getText().toString());
                user.setPassword(password.getText().toString());
                new AuthenticationPresenter().authenticate(user, new AuthenticationListener(LoginActivity.this) {

                    @Override
                    public void onAuthenticated(String token) {
                        //save user token
                        MyApplication.getInstance().getPrefManager().saveUserAuthToken(token);

                        //check if profile updated
                        new UserPresenter().getCurrentUserProfile(new OnEntityReceivedListener<ProfileResponse>(LoginActivity.this) {
                            @Override
                            public void onReceived(ProfileResponse entity) {
                                if (entity.getProfile() == null)
                                    startActivity(new Intent(LoginActivity.this, UpdateProfileActivity.class));
                                else
                                    startActivity(new Intent(LoginActivity.this, Homepage.class));
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

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });

        loginForm.setVisibility(View.GONE);
        animateLogo();
    }

    private void animateLogo(){
        Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate);
        animTranslate.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation arg0) {
            }

            public void onAnimationRepeat(Animation arg0) {
            }

            public void onAnimationEnd(Animation arg0) {
                LoginActivity.this.loginForm.setVisibility(View.VISIBLE);
                Animation animFade = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.fade);
                LoginActivity.this.loginForm.startAnimation(animFade);
            }
        });
        this.logoImageView.startAnimation(animTranslate);
    }
}
