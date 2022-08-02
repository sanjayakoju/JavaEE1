package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("HiringManagerInterview")
public class HiringManagerInterview extends Interview {

    private int teamSize;
    private LocalDate startDate;

    public HiringManagerInterview() {}

    public HiringManagerInterview(int teamSize, LocalDate startDate) {
        this.teamSize = teamSize;
        this.startDate = startDate;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
