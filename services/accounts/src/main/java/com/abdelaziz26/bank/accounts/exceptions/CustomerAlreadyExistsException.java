package com.abdelaziz26.bank.accounts.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {super(message);}
}
