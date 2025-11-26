package com.abdelaziz26.bank.loans.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class LoanDto {

    @NotBlank(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Loan Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})", message = "LoanNumber must be 12 digits")

    private String loanNumber;

    @NotBlank(message = "LoanType can not be a null or empty")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    private Double totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    private Double amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    private Double outstandingAmount;
}
