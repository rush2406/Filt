package org.technozion.technozion18;

import java.io.Serializable;

/**
 * Created by jay on 10/15/14.
 */
public class SettingsData implements Serializable {
    private String mSize;
    private String mImageColor;
    private String mType;

    public SettingsData() {
        mSize = "any";
        mImageColor = "any";
        mType = "any";
    }

    public boolean hasSize() {
        return !mSize.equals("any");
    }

    public boolean hasColor() {
        return !mImageColor.equals("any");
    }

    public boolean hasType() {
        return !mType.equals("any");
    }


    public String getSize() {
        return mSize;
    }

    public String getImageColor() {
        return mImageColor;
    }

    public String getType() {
        return mType;
    }


    public void changeAttribute(String attribute, String value) {
        switch(attribute) {
            case "size":
                mSize = value;
                break;
            case "color":
                mImageColor = value;
                break;
            case "type":
                mType = value;
                break;
            default:
                break;
        }
    }
    public String getAttribute(String attribute) {
        switch(attribute) {
            case "size":
                return mSize;
            case "color":
                return mImageColor;
            case "type":
                return mType;
            default:
                throw new NullPointerException();
        }
    }
}
