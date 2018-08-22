package org.technozion.technozion18.models;

public class ContactMembers extends BaseModel{
    String name, phone_no, facebook_link;

    public String getName() {
        return name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getFacebook_link() {
        return facebook_link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setFacebook_link(String facebook_link) {
        this.facebook_link = facebook_link;
    }
}
