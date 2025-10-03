package com.abdelaziz26.bank.loans.exceptions;

public class LoanAlreadyExistsException extends RuntimeException {
    public LoanAlreadyExistsException(String message)
    {
        super(message);
    }
}
