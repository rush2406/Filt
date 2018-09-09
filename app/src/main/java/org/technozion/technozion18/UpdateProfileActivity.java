package org.technozion.technozion18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.City;
import org.technozion.technozion18.models.College;
import org.technozion.technozion18.models.UserProfile;
import org.technozion.technozion18.presenters.CityPresenter;
import org.technozion.technozion18.presenters.CollegePresenter;
import org.technozion.technozion18.presenters.UserPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateProfileActivity extends AppCompatActivity {

    MaterialSpinner genderSpinner, citySpinner, collegeSpinner, stateSpinner;
    MaterialEditText mobileEditText;
    CardView buttonSubmit;
    List<City> cities;
    List<College> colleges;
    /** ToDO: Add all states **/
    String[] stateNames = {"Delhi", "Telangana", "Andhra Pradesh", "Uttar Pradesh", "Uttrakhand", "Himachal Pradesh"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        genderSpinner = findViewById(R.id.spinnerGender);
        citySpinner = findViewById(R.id.spinnerCity);
        collegeSpinner = findViewById(R.id.spinnerCollege);
        stateSpinner = findViewById(R.id.spinnerState);

        mobileEditText = findViewById(R.id.phone);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        fillSpinners();


    }

    public void fillSpinners(){
        genderSpinner.setItems("Select Gender", "Male", "Female");
        citySpinner.setItems("Select City");
        collegeSpinner.setItems("Select College");
        stateSpinner.setItems("Select State");

        new CityPresenter().getCities(new OnEntitiesReceivedListener<City>(this) {
            @Override
            public void onReceived(final List<City> cities) {
                UpdateProfileActivity.this.cities = cities;
                final List<String> cityNames = new ArrayList<>();
                for(City city : cities){
                    cityNames.add(city.getName());
                }
                Collections.sort(cityNames);
                cityNames.add(0,"Select City");
                citySpinner.setItems(cityNames);
                citySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        new CollegePresenter().getColleges(cities.get(position - 1).getNo(), new OnEntitiesReceivedListener<College>(UpdateProfileActivity.this) {
                            @Override
                            public void onReceived(List<College> colleges) {
                                UpdateProfileActivity.this.colleges= colleges;
                                ArrayList<String> collegeNames = new ArrayList<>();
                                for(College college : colleges){
                                    collegeNames.add(college.getName());
                                }
                                Collections.sort(collegeNames);
                                collegeNames.add(0,"Select College");
                                collegeSpinner.setItems(collegeNames);
                            }
                        });
                    }
                });
            }
        });

        Arrays.sort(stateNames);
        stateSpinner.setItems(stateNames);
    }

    private void submit(){
        if(!validate())
            return;

        Map<String,String> map = new HashMap<>();
        String cityName = (String) citySpinner.getItems().get(citySpinner.getSelectedIndex());
        String collegeName = (String) collegeSpinner.getItems().get(collegeSpinner.getSelectedIndex());
        for(City city : cities)
            if(city.getName().equals(cityName)) {
                map.put("city", city.getNo() + "");
                break;
            }
        for(College college : colleges)
            if(college.getName().equals(collegeName)) {
                map.put("college", college.getNo() + "");
                break;
            }
        map.put("gender", (String) genderSpinner.getItems().get(genderSpinner.getSelectedIndex()));
        map.put("phone", mobileEditText.getText().toString());

        new UserPresenter().updateUserProfile(map, new OnEntityReceivedListener<UserProfile>(this) {
            @Override
            public void onReceived(UserProfile entity) {
                startActivity(new Intent(UpdateProfileActivity.this, Homepage.class));
            }
        });
    }

    private boolean validate(){
        boolean isValidated = true;
        ArrayList<MaterialSpinner> spinners = new ArrayList<>();
        spinners.add(citySpinner);
        spinners.add(stateSpinner);
        spinners.add(collegeSpinner);
        for(MaterialSpinner spinner : spinners){
            if(spinner.getSelectedIndex() == 0) {
                spinner.setError("This field is requried!");
                isValidated = false;
            }
        }
        if(!mobileEditText.validate()){
            isValidated = false;
        }
        return isValidated;
    }
}
