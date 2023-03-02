INSERT INTO COFFEE (kor_name, eng_name, price, coffee_code, coffee_status, created_at, last_modified_at)
    VALUES ('카라멜 라떼', 'Caramel Latte', 4500,'CRL', 'COFFEE_FOR_SALE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO COFFEE (kor_name, eng_name, price, coffee_code, coffee_status, created_at, last_modified_at)
    VALUES ('바닐라 라떼', 'Vanilla Latte', 5000, 'VNL', 'COFFEE_FOR_SALE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO MEMBER (email, name, phone, member_status, created_at, last_modified_at)
    VALUES ('hgd1@gmail.com', '홍길동1', '010-1111-1111', 'MEMBER_ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO STAMP (stamp_count, created_at, last_modified_at,  member_id)
    VALUES (0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);
