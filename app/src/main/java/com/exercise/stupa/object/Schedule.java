package com.exercise.stupa.object;

/**
 * Created by anjas on 02/05/17.
 */

public class Schedule {
    private String name;
    private String time;

    public Schedule(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
