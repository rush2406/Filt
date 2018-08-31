package org.technozion.technozion18.models;

public class UserProfile extends BaseModel {
    Integer tzid;
    String profile_picture;
    String gender;
    String phone;
    String other_city_name;
    String other_college_name;
    String state;
    String college_reg_id;
    String is_registered;
    String is_hospitality;
    String is_nitw;
    String is_nitap;
    String referral_code_used;
    Float credits;
    Float money;
    Integer no_of_registered;
    Integer total_no_of_registered;
    User user;
    String city;
    String college;

    public Integer getTzid() {
        return tzid;
    }

    public void setTzid(Integer tzid) {
        this.tzid = tzid;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOther_city_name() {
        return other_city_name;
    }

    public void setOther_city_name(String other_city_name) {
        this.other_city_name = other_city_name;
    }

    public String getOther_college_name() {
        return other_college_name;
    }

    public void setOther_college_name(String other_college_name) {
        this.other_college_name = other_college_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCollege_reg_id() {
        return college_reg_id;
    }

    public void setCollege_reg_id(String college_reg_id) {
        this.college_reg_id = college_reg_id;
    }

    public String getIs_registered() {
        return is_registered;
    }

    public void setIs_registered(String is_registered) {
        this.is_registered = is_registered;
    }

    public String getIs_hospitality() {
        return is_hospitality;
    }

    public void setIs_hospitality(String is_hospitality) {
        this.is_hospitality = is_hospitality;
    }

    public String getIs_nitw() {
        return is_nitw;
    }

    public void setIs_nitw(String is_nitw) {
        this.is_nitw = is_nitw;
    }

    public String getIs_nitap() {
        return is_nitap;
    }

    public void setIs_nitap(String is_nitap) {
        this.is_nitap = is_nitap;
    }

    public String getReferral_code_used() {
        return referral_code_used;
    }

    public void setReferral_code_used(String referral_code_used) {
        this.referral_code_used = referral_code_used;
    }

    public Float getCredits() {
        return credits;
    }

    public void setCredits(Float credits) {
        this.credits = credits;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Integer getNo_of_registered() {
        return no_of_registered;
    }

    public void setNo_of_registered(Integer no_of_registered) {
        this.no_of_registered = no_of_registered;
    }

    public Integer getTotal_no_of_registered() {
        return total_no_of_registered;
    }

    public void setTotal_no_of_registered(Integer total_no_of_registered) {
        this.total_no_of_registered = total_no_of_registered;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
