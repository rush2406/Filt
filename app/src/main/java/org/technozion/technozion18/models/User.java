package org.technozion.technozion18.models;

import org.technozion.technozion18.MyApplication;
import org.technozion.technozion18.common.EntityCallback;
import org.technozion.technozion18.common.OnEntityReceivedListener;

public class User extends BaseModel {
    String username, first_name, last_name, email, password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void createUser(OnEntityReceivedListener<User> listener){
        MyApplication.getInstance().getRepository().signup(this, new EntityCallback<User>(listener));
    }
}
