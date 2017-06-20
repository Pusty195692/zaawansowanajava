insert into Users (id, first_name, last_name, email, password, phone_number, credentials_non_expired, account_non_expired, account_non_locked, enabled, is_active)
VALUES
(1, 'Admin', 'Admin', 'admin', '$2a$12$jsm4Mbse40VEL/FlMHwk3OyHry0WZ2sZKZkIk92DnZTGoQfzLh1Pq', '+48789987789', true, true, true, true, true),
(2, 'User', 'User', 'user', '$2a$04$YKK57dsnF399WfJhedfKN.xBG08raK6o3XVfyBJ1PxNiyMCSWlgKG', '+48789987789', true, true, true, true, true),
(3, 'test', 'test', 'flightassistant2017@gmail.com', '$2a$04$YKK57dsnF399WfJhedfKN.xBG08raK6o3XVfyBJ1PxNiyMCSWlgKG', '+48789987789', true, true, true, true, true);

insert into ROLES (id, authority) VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER');

INSERT INTO USERS_ROLES (USER_ID, ROLES_ID)
    VALUES (1,1), (1,2), (3,2);

insert into FLIGHTS (id,flight_name) VALUES
(1,'Lot testowy 1'),
(2,'Lot testowy 2');

insert into PASSENGERS (id,first_name,last_name,pesel) values
(1,'Testowy','Pasazer','123456789');

insert into FLIGHTS_PASSENGERS(FLIGHT_ID,PASSENGERS_ID) VALUES
(1,1);

