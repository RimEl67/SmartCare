

package com.app.myapplication.models;

import java.io.Serializable;

public class Medication implements Serializable {
    private int id;
    private String name;
    private String dosage;
    private String form;  // Changed from type to form
    private String frequency;
    private String time;
    private String[] timesOfDay;  // Changed from schedule to timesOfDay
    private boolean active;

    public Medication(int id, String name, String dosage, String form, String frequency, String time, String[] timesOfDay, boolean active) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.form = form;
        this.frequency = frequency;
        this.time = time;
        this.timesOfDay = timesOfDay;
        this.active = active;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] getTimesOfDay() {
        return timesOfDay;
    }

    public void setTimesOfDay(String[] timesOfDay) {
        this.timesOfDay = timesOfDay;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}













