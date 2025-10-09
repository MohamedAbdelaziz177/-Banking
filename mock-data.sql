-- ===== CUSTOMERS =====
INSERT INTO public.customers (id, name, email, phone)
VALUES
    (1, 'Ahmed Ali', 'ahmed.ali@example.com', '01000112233'),
    (2, 'Mona Hassan', 'mona.hassan@example.com', '01000445566'),
    (3, 'Youssef Ibrahim', 'youssef.ibrahim@example.com', '01000778899'),
    (4, 'Sara Mahmoud', 'sara.mahmoud@example.com', '01000990011'),
    (5, 'Omar Adel', 'omar.adel@example.com', '01000223344');

-- ===== ACCOUNTS =====
INSERT INTO public.accounts (id, account_type, branch_address, customer_id)
VALUES
    (1, 'SAVINGS', 'Nasr City Branch', 1),
    (2, 'CURRENT', 'Heliopolis Branch', 2),
    (3, 'SALARY', 'Maadi Branch', 3),
    (4, 'SAVINGS', 'Zamalek Branch', 4),
    (5, 'CURRENT', 'Dokki Branch', 5);

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
