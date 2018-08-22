package org.technozion.technozion18.models;

public class Venue extends BaseModel {
    String name;
    Float lat, longt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLongt() {
        return longt;
    }

    public void setLongt(Float longt) {
        this.longt = longt;
    }
}
