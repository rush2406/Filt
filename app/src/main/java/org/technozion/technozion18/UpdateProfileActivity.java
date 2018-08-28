package org.technozion.technozion18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.models.City;
import org.technozion.technozion18.models.College;
import org.technozion.technozion18.presenters.CityPresenter;
import org.technozion.technozion18.presenters.CollegePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UpdateProfileActivity extends AppCompatActivity {

    MaterialSpinner genderSpinner, citySpinner, collegeSpinner, stateSpinner;
    MaterialEditText mobileEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        genderSpinner = findViewById(R.id.spinnerGender);
        citySpinner = findViewById(R.id.spinnerCity);
        collegeSpinner = findViewById(R.id.spinnerCollege);
        stateSpinner = findViewById(R.id.spinnerState);

        mobileEditText = findViewById(R.id.phone);

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
                final List<String> cityNames = new ArrayList<>();
                for(City city : cities){
                    cityNames.add(city.getName());
                }
                Collections.sort(cityNames);
                citySpinner.setItems(cityNames);
                citySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        new CollegePresenter().getColleges(cities.get(position - 1).getNo(), new OnEntitiesReceivedListener<College>(UpdateProfileActivity.this) {
                            @Override
                            public void onReceived(List<College> colleges) {
                                ArrayList<String> collegeNames = new ArrayList<>();
                                for(College college : colleges){
                                    collegeNames.add(college.getName());
                                }
                                Collections.sort(collegeNames);
                                collegeSpinner.setItems(collegeNames);
                            }
                        });
                    }
                });
            }
        });

        /** ToDO: Add all states **/
        String[] stateNames = {"Delhi", "Telangana", "Andhra Pradesh", "Uttar Pradesh", "Uttrakhand", "Himachal Pradesh"};
        Arrays.sort(stateNames);
        stateSpinner.setItems(stateNames);
    }
}
