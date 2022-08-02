package com.example.demo.model;

import jakarta.persistence.*;

@Entity
//@DiscriminatorValue("Client")
public class Client extends Company {

    private String mission;
    private String reason;
    private String website;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    public Client(){}

    public Client(String name,String address, String mission, String reason, String website) {
        super(name, address);
        this.mission = mission;
        this.reason = reason;
        this.website = website;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
}
