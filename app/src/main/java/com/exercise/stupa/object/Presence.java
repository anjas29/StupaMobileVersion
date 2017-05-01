package com.exercise.stupa.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anjas on 30/04/17.
 */

public class Presence {

    @SerializedName("presence")
    @Expose
    private PresentDetail presence;

    @SerializedName("present")
    @Expose
    private String present;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    public PresentDetail getPresence() {
        return presence;
    }

    public void setPresence(PresentDetail presence) {
        this.presence = presence;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
