package com.abdelaziz26.bank.loans.mappers;

import com.abdelaziz26.bank.loans.dtos.LoanDto;
import com.abdelaziz26.bank.loans.entities.Loan;

public class LoanMapper {

    public static LoanDto toDto(Loan loans) {

        LoanDto loanDto = new LoanDto();
        loanDto.setLoanNumber(loans.getLoanNumber());
        loanDto.setLoanType(loans.getLoanType());
        loanDto.setMobileNumber(loans.getMobileNumber());
        loanDto.setTotalLoan(loans.getTotalLoan());
        loanDto.setAmountPaid(loans.getAmountPaid());
        loanDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loanDto;
    }

    public static Loan toEntity(LoanDto loanDto) {

        Loan loan = new Loan();
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        return loan;
    }
}
