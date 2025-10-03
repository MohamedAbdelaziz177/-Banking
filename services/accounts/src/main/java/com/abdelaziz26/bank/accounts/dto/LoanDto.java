package com.abdelaziz26.bank.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    private int outstandingAmount;
}
