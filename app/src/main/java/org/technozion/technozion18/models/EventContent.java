package org.technozion.technozion18.models;

public class EventContent extends BaseModel{
    String title;
    String content;
    String last_update_by;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLast_update_by() {
        return last_update_by;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLast_update_by(String last_update_by) {
        this.last_update_by = last_update_by;
    }
}
