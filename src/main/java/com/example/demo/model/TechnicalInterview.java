package com.example.demo.model;

import com.example.demo.enums.Location;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TechnicalInterview")
public class TechnicalInterview extends Interview {

    private int duration;
    private Location location;
    private String question;

    public TechnicalInterview() {}

    public TechnicalInterview(int duration, Location location, String question) {
        this.duration = duration;
        this.location = location;
        this.question = question;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
