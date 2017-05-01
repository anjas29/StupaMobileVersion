package com.exercise.stupa.object.retrofit;

import com.exercise.stupa.object.PresenceCount;
import com.exercise.stupa.object.Profil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anjas on 01/05/17.
 */

public class ProfilResponse {
    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("profil")
    @Expose
    private Profil profil;

    @SerializedName("data")
    @Expose
    private ArrayList<PresenceCount> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public ArrayList<PresenceCount> getData() {
        return data;
    }

    public void setData(ArrayList<PresenceCount> data) {
        this.data = data;
    }
}
