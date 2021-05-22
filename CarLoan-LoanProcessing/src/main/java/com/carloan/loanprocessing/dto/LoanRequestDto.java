package com.carloan.loanprocessing.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class LoanRequestDto {

    @NotNull
    private int userId;

    @NotNull
    @Min(value = 10000, message = "Minimum eligible amount is above 10000")
    private Double amount;
}
