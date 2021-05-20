package com.carloan.userprofile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserProfileDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull
    private Double salary;

    @Email
    private String email;

    private Long contact;

    public UserProfileDTO(@NotBlank(message = "Name is required") String name, @NotNull Double salary, @Email String email, Long contact) {
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.contact = contact;
    }
}
