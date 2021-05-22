package com.carloan.loanprocessing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    private int userId;

    private String name;

    private String email;

    private Double salary;

    private Long contact;

    public UserProfile(String name, String email, Double salary, Long contact) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.contact = contact;
    }

}
