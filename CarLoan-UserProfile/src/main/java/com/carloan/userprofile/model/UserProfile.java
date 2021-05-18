package com.carloan.userprofile.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Double salary;

    @Column
    private Long contact;

    public UserProfile(String name, String email, Double salary, Long contact) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.contact = contact;
    }
}
