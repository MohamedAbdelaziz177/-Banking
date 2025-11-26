package com.abdelaziz26.bank.loans.mappers;

import com.abdelaziz26.bank.loans.dtos.LoanPaymentRequest;
import com.abdelaziz26.bank.loans.dtos.LoanPaymentResponse;
import com.abdelaziz26.bank.loans.entities.Loan;
import com.abdelaziz26.bank.loans.entities.LoanPayment;

import java.time.LocalDateTime;

public class PaymentMapper {

    public static LoanPaymentResponse toLoanPaymentResponse(LoanPayment payment, Double amount) {
        return LoanPaymentResponse.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .timestamp(payment.getTimestamp())
                .LoanId(payment.getLoan().getLoanId())
                .currentAmount(amount)
                .build();
    }

    public static LoanPayment toEntity(LoanPaymentRequest request, Loan  loan) {
        return LoanPayment.builder()
                .amount(request.getAmount())
                .timestamp(LocalDateTime.now())
                .loan(loan)
                .build();
    }
}
