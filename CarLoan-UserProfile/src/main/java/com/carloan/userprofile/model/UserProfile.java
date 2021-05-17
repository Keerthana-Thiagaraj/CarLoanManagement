package com.carloan.userprofile.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column
    private String name;

    @Column
    private String emailId;

    @Column
    private String salary;

    @Column
    private String contact;

    public UserProfile() {
    }

    public UserProfile(String name, String emailId, String salary, String contact) {
        this.name = name;
        this.emailId = emailId;
        this.salary = salary;
        this.contact = contact;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
