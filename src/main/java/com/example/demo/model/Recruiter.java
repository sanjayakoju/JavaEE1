package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
//@DiscriminatorValue("Recruiter")
public class Recruiter extends Company {

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.PERSIST)
    private List<Client> clients;

    public Recruiter() {
    }

    public Recruiter(String name, String address, List<Client> clients) {
        super(name,address);
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

}
