package com.abdelaziz26.bank.accounts.mapper;

import com.abdelaziz26.bank.accounts.dto.CustomerDetailsDto;
import com.abdelaziz26.bank.accounts.dto.CustomerDto;
import com.abdelaziz26.bank.accounts.entities.Account;
import com.abdelaziz26.bank.accounts.entities.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerDto customerDto)
    {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }

    public static CustomerDto toDto(Customer customer)
    {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        return customerDto;
    }

    public static CustomerDetailsDto toCustomerDetailsDto(Customer customer) {

        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();

        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getPhone());

        return customerDetailsDto;
    }

}
