package com.carloan.userprofile.dto;

import com.carloan.userprofile.validation.EmailContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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

    @EmailContent
    private String email;

    private Long contact;
}
