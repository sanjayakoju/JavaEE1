package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
@DiscriminatorValue("ScreeningInterview")
public class ScreeningInterview extends Interview {

    private String name;
    private String result;

    public ScreeningInterview() {}

    public ScreeningInterview(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
