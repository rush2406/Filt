package org.technozion.technozion18.models;

import java.util.List;

public class Event extends BaseModel {
    Venue venue;
    List<ContactMembers> contact_members;
    List<EventContent> contents;
    String event_type, name, alias, banner_image, department, category1, category2, logo;
    Integer maxTeamSize, minTeamSize, rounds;
    Float cost;
    Boolean is_banner, is_logo, is_content, is_published, is_homepage, unpaid_event;
    String day;
    String event_time;
    Float nitw_discount_price;
    Integer priority;
    String prize_money;
    String workshop_percent;

    public Venue getVenue() {
        return venue;
    }

    public List<ContactMembers> getContact_members() {
        return contact_members;
    }

    public List<EventContent> getContents() {
        return contents;
    }

    public String getEvent_type() {
        return event_type;
    }

    public String getName() {
        if(name == null)
            return "";
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String getAlias() {
        return alias;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public String getDepartment() {
        return department;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getLogo() {
        return logo;
    }

    public Integer getMaxTeamSize() {
        return maxTeamSize;
    }

    public Integer getMinTeamSize() {
        return minTeamSize;
    }

    public Integer getRounds() {
        return rounds;
    }

    public Float getCost() {
        return cost;
    }

    public Boolean getIs_banner() {
        return is_banner;
    }

    public Boolean getIs_logo() {
        return is_logo;
    }

    public Boolean getIs_content() {
        return is_content;
    }

    public Boolean getIs_published() {
        return is_published;
    }

    public Boolean getIs_homepage() {
        return is_homepage;
    }

    public Boolean getUnpaid_event() {
        return unpaid_event;
    }

    public String getDay() {
        return day;
    }

    public String getEvent_time() {
        return event_time;
    }

    public Float getNitw_discount_price() {
        return nitw_discount_price;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getPrize_money() {
        return prize_money;
    }

    public String getWorkshop_percent() {
        return workshop_percent;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setContact_members(List<ContactMembers> contact_members) {
        this.contact_members = contact_members;
    }

    public void setContents(List<EventContent> contents) {
        this.contents = contents;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setMaxTeamSize(Integer maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
    }

    public void setMinTeamSize(Integer minTeamSize) {
        this.minTeamSize = minTeamSize;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public void setIs_banner(Boolean is_banner) {
        this.is_banner = is_banner;
    }

    public void setIs_logo(Boolean is_logo) {
        this.is_logo = is_logo;
    }

    public void setIs_content(Boolean is_content) {
        this.is_content = is_content;
    }

    public void setIs_published(Boolean is_published) {
        this.is_published = is_published;
    }

    public void setIs_homepage(Boolean is_homepage) {
        this.is_homepage = is_homepage;
    }

    public void setUnpaid_event(Boolean unpaid_event) {
        this.unpaid_event = unpaid_event;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public void setNitw_discount_price(Float nitw_discount_price) {
        this.nitw_discount_price = nitw_discount_price;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setPrize_money(String prize_money) {
        this.prize_money = prize_money;
    }

    public void setWorkshop_percent(String workshop_percent) {
        this.workshop_percent = workshop_percent;
    }
}
