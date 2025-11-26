package com.abdelaziz26.bank.loans.services;

import com.abdelaziz26.bank.loans.dtos.LoanPaymentRequest;
import com.abdelaziz26.bank.loans.dtos.LoanPaymentResponse;
import com.abdelaziz26.bank.loans.entities.Loan;
import com.abdelaziz26.bank.loans.entities.LoanPayment;
import com.abdelaziz26.bank.loans.exceptions.ResourceNotFoundException;
import com.abdelaziz26.bank.loans.mappers.PaymentMapper;
import com.abdelaziz26.bank.loans.repositories.LoansRepository;
import com.abdelaziz26.bank.loans.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final LoansRepository loansRepository;

    public LoanPaymentResponse pay(LoanPaymentRequest request){
        Loan loan = loansRepository.findById(request.getLoanId()).orElseThrow(() ->
                new ResourceNotFoundException("Loan", "Id", request.getLoanId().toString()));

        loan.setAmountPaid(loan.getAmountPaid() + request.getAmount());
        Double restAmount =  loan.getAmountPaid() - request.getAmount();

        LoanPayment payment = PaymentMapper.toEntity(request, loan);
        LoanPaymentResponse response = PaymentMapper.toLoanPaymentResponse(payment, restAmount);

        return response;
    }

    public List<LoanPaymentResponse> findAllByLoanId(Long loanId){

        List<LoanPayment> payments = paymentRepository.findAllByLoan_LoanId(loanId);
        return payments.stream().map(p -> {
            Double rest = p.getLoan().getTotalLoan() - p.getLoan().getAmountPaid();
            return PaymentMapper.toLoanPaymentResponse(p, rest);
        }).toList();
    }

}
