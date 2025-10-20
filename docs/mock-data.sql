-- ===== CUSTOMERS =====
INSERT INTO public.customers (id, email, name, phone)
VALUES
    (1, 'john.doe@email.com', 'John Doe', '+1-555-0101'),
    (2, 'jane.smith@email.com', 'Jane Smith', '+1-555-0102'),
    (3, 'robert.johnson@email.com', 'Robert Johnson', '+1-555-0103'),
    (4, 'sarah.wilson@email.com', 'Sarah Wilson', '+1-555-0104'),
    (5, 'mike.brown@email.com', 'Mike Brown', '+1-555-0105'),
    (6, 'emily.davis@email.com', 'Emily Davis', '+1-555-0106'),
    (7, 'david.miller@email.com', 'David Miller', '+1-555-0107'),
    (8, 'lisa.taylor@email.com', 'Lisa Taylor', '+1-555-0108'),
    (9, 'kevin.moore@email.com', 'Kevin Moore', '+1-555-0109'),
    (10, 'amanda.jackson@email.com', 'Amanda Jackson', '+1-555-0110');

-- ===== ACCOUNTS =====
INSERT INTO public.accounts (id, account_type, branch_address, customer_id)
VALUES
INSERT INTO public.accounts (customer_id, id, account_type, branch_address, balance)
VALUES
    (1, 1001, 'SAVINGS', '123 Main Street, New York, NY 10001', 2500.00),
    (2, 1002, 'CHECKING', '123 Main Street, New York, NY 10001', 1500.50),
    (3, 1003, 'SAVINGS', '456 Oak Avenue, Los Angeles, CA 90210', 18000.75),
    (4, 1004, 'CHECKING', '789 Pine Road, Chicago, IL 60601', 3200.25),
    (5, 1005, 'SAVINGS', '321 Elm Street, Houston, TX 77002', 12500.00),
    (6, 1006, 'CHECKING', '654 Maple Drive, Phoenix, AZ 85001', 875.80),
    (7, 1007, 'SAVINGS', '654 Maple Drive, Phoenix, AZ 85001', 15000.00),
    (8, 1008, 'CHECKING', '987 Cedar Lane, Philadelphia, PA 19102', 4200.45),
    (9, 1009, 'SAVINGS', '147 Walnut Street, San Antonio, TX 78201', 9600.30),
    (10, 1010, 'CHECKING', '258 Birch Avenue, San Diego, CA 92101', 2100.15);

-- ===== LOANS =====
INSERT INTO public.loans (id, mobile_number, loan_number, loan_type, total_loan, amount_paid, outstanding_amount)
VALUES
    (1, '01000112233', 'LN10001', 'HOME', 500000, 150000, 350000),
    (2, '01000445566', 'LN10002', 'CAR', 200000, 80000, 120000),
    (3, '01000778899', 'LN10003', 'PERSONAL', 100000, 40000, 60000),
    (4, '01000990011', 'LN10004', 'EDUCATION', 80000, 20000, 60000),
    (5, '01000223344', 'LN10005', 'BUSINESS', 300000, 120000, 180000);

-- ===== CARDS =====
INSERT INTO public.cards (id, amount_used, available_amount, card_number, card_type, mobile_number, total_limit)
VALUES
    (1, 2000, 8000, '4111111111111111', 'CREDIT', '01000112233', 10000),
    (2, 500, 9500, '4222222222222222', 'DEBIT', '01000445566', 10000),
    (3, 3000, 7000, '4333333333333333', 'CREDIT', '01000778899', 10000),
    (4, 1000, 9000, '4444444444444444', 'DEBIT', '01000990011', 10000),
    (5, 2500, 7500, '4555555555555555', 'CREDIT', '01000223344', 10000);
