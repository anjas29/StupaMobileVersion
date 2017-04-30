package com.exercise.stupa.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anjas on 30/04/17.
 */

public class Present {

    @SerializedName("present")
    @Expose
    private String present;

    @SerializedName("created_at")
    @Expose
    private String course;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    public Present(String present, String course, String created_at) {
        this.present = present;
        this.course = course;
        this.created_at = created_at;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
