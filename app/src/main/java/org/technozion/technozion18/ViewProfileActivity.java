package org.technozion.technozion18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.technozion.technozion18.api_services.responses.ProfileResponse;
import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.UserProfile;
import org.technozion.technozion18.presenters.UserPresenter;

public class ViewProfileActivity extends AppCompatActivity {

    TextView fullName, phone, city, college;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        fullName = findViewById(R.id.userFirstName);
        phone = findViewById(R.id.phone);
        city = findViewById(R.id.city);
        college = findViewById(R.id.college);

        new UserPresenter().getCurrentUserProfile(new OnEntityReceivedListener<ProfileResponse>(this) {
            @Override
            public void onReceived(ProfileResponse entity) {
                UserProfile profile = entity.getProfile();
                if(profile != null) {
                    fullName.setText(profile.getUser().getFirst_name() + " " + profile.getUser().getLast_name());
                    phone.setText(profile.getPhone());
                    city.setText(profile.getCity());
                    college.setText(profile.getCollege());
                }
            }
        });

        findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getInstance().getPrefManager().logout();
                Intent intent = new Intent(ViewProfileActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
