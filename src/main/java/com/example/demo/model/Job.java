package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;


@Entity

@NamedNativeQuery(
        name = "findJobByCompanyAddress",
        query = "SELECT * FROM job j JOIN company c on j.COMPANY_ID = c.id WHERE c.address = ?1",
        resultClass=Job.class
)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double salary;

    @OneToMany(mappedBy = "job", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Skill> skill;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Job(String title, double salary, List<Skill> skill, Company company) {
        this.title = title;
        this.salary = salary;
        this.skill = skill;
        this.company = company;
    }

    public Job(){}

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                ", company ="+company+
                '}';
    }

}
