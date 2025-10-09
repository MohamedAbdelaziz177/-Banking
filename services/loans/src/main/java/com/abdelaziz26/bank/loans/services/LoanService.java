package com.abdelaziz26.bank.loans.services;

import com.abdelaziz26.bank.loans.common.LoansConstants;
import com.abdelaziz26.bank.loans.dtos.LoanDto;
import com.abdelaziz26.bank.loans.entities.Loan;
import com.abdelaziz26.bank.loans.exceptions.LoanAlreadyExistsException;
import com.abdelaziz26.bank.loans.exceptions.ResourceNotFoundException;
import com.abdelaziz26.bank.loans.mappers.LoanMapper;
import com.abdelaziz26.bank.loans.repositories.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoansRepository loansRepository;

    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoans= loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }


    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }


    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoanMapper.toDto(loan);
    }



    public boolean updateLoan(LoanDto loansDto) {
        Loan loan = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoanMapper.toEntity(loansDto);
        loansRepository.save(loan);
        return  true;
    }


    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loan.getLoanId());
        return true;
    }
}
