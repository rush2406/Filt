package org.technozion.technozion18.api_services.responses;

import org.technozion.technozion18.models.UserProfile;

public class ProfileResponse {
    UserProfile profile;

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}
