-- ===== CUSTOMERS =====
INSERT INTO public.customers (id, email, name, phone)
VALUES
    (1, 'john.doe@email.com', 'John Doe', '01060328954'),
    (2, 'jane.smith@email.com', 'Jane Smith', '01060328955'),
    (3, 'robert.johnson@email.com', 'Robert Johnson', '01060328958'),
    (4, 'sarah.wilson@email.com', 'Sarah Wilson', '01060328957'),
    (5, 'mike.brown@email.com', 'Mike Brown', '+1-555-0105'),
    (6, 'emily.davis@email.com', 'Emily Davis', '+1-555-0106'),
    (7, 'david.miller@email.com', 'David Miller', '+1-555-0107'),
    (8, 'lisa.taylor@email.com', 'Lisa Taylor', '+1-555-0108'),
    (9, 'kevin.moore@email.com', 'Kevin Moore', '+1-555-0109'),
    (10, 'amanda.jackson@email.com', 'Amanda Jackson', '+1-555-0110');

-- ===== ACCOUNTS =====

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
INSERT INTO public.loan(
    id, amount_paid, loan_number, loan_type, mobile_number, outstanding_amount, total_loan)
VALUES
    (10, 2500.00, 'LN-1001', 'PERSONAL', '+1-555-0101', 7500.00, 10000.00),
    (11, 18000.00, 'LN-1002', 'MORTGAGE', '+1-555-0102', 182000.00, 200000.00),
    (12, 4500.00, 'LN-1003', 'AUTO', '+1-555-0103', 20500.00, 25000.00),
    (13, 12000.00, 'LN-1004', 'BUSINESS', '+1-555-0104', 38000.00, 50000.00),
    (14, 750.00, 'LN-1005', 'PERSONAL', '+1-555-0105', 4250.00, 5000.00),
    (15, 30000.00, 'LN-1006', 'MORTGAGE', '+1-555-0106', 270000.00, 300000.00),
    (16, 12500.00, 'LN-1007', 'AUTO', '+1-555-0107', 37500.00, 50000.00),
    (17, 8000.00, 'LN-1008', 'EDUCATION', '+1-555-0108', 12000.00, 20000.00),
    (18, 1500.00, 'LN-1009', 'PERSONAL', '+1-555-0109', 3500.00, 5000.00),
    (19, 45000.00, 'LN-1010', 'BUSINESS', '+1-555-0110', 55000.00, 100000.00);

-- ===== CARDS =====
INSERT INTO public.cards(
    id, amount_used, available_amount, card_number, card_type, mobile_number, total_limit)
VALUES
    (20, 500.00, 4500.00, '4532-8192-6745-1001', 'CREDIT', '+1-555-0101', 5000.00),
    (21, 1200.00, 8800.00, '5500-1234-5678-1002', 'CREDIT', '+1-555-0102', 10000.00),
    (22, 300.00, 2700.00, '3714-856723-51003', 'CREDIT', '+1-555-0103', 3000.00),
    (23, 2500.00, 7500.00, '6011-5928-4736-1004', 'DEBIT', '+1-555-0104', 10000.00),
    (24, 800.00, 4200.00, '4024-0071-2938-1005', 'CREDIT', '+1-555-0105', 5000.00),
    (25, 1500.00, 18500.00, '5425-2334-9012-1006', 'CREDIT', '+1-555-0106', 20000.00),
    (26, 2200.00, 7800.00, '4916-3804-5721-1007', 'DEBIT', '+1-555-0107', 10000.00),
    (27, 600.00, 4400.00, '4556-7429-1836-1008', 'CREDIT', '+1-555-0108', 5000.00),
    (28, 950.00, 4050.00, '5178-9463-2057-1009', 'CREDIT', '+1-555-0109', 5000.00),
    (29, 3200.00, 16800.00, '4485-3297-6154-1010', 'CREDIT', '+1-555-0110', 20000.00);