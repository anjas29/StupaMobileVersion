package com.exercise.stupa.object.retrofit;

import com.exercise.stupa.object.Presence;
import com.exercise.stupa.object.Present;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anjas on 01/05/17.
 */

public class PresenceResponse {
    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("data")
    @Expose
    private ArrayList<Presence> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Presence> getData() {
        return data;
    }

    public void setData(ArrayList<Presence> data) {
        this.data = data;
    }
}
