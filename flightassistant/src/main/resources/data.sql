insert into Users (id, first_name, last_name, email, password, phone_number, credentials_non_expired, account_non_expired, account_non_locked, enabled)
VALUES
(1, 'Admin', 'Admin', 'admin', '$2a$12$jsm4Mbse40VEL/FlMHwk3OyHry0WZ2sZKZkIk92DnZTGoQfzLh1Pq', '+48789987789', true, true, true, true),
(2, 'User', 'User', 'user', '$2a$04$YKK57dsnF399WfJhedfKN.xBG08raK6o3XVfyBJ1PxNiyMCSWlgKG', '+48789987789', true, true, true, true),
(3, 'test', 'test', 'flightassistant2017@gmail.com', '$2a$04$YKK57dsnF399WfJhedfKN.xBG08raK6o3XVfyBJ1PxNiyMCSWlgKG', '+48789987789', true, true, true, true);

insert into ROLES (id, authority) VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER');

INSERT INTO USERS_ROLES (USER_ID, ROLES_ID)
    VALUES (1,1), (1,2), (2,2);

INSERT INTO FLIGHTS (id, gate, flight_direction, expected_time_boarding, get_expected_time_gate_closing,
                      schedule_date, airline_code, estimated_landing_time) VALUES
(1, 'gate 5', 'Bieszczady', 'some time boarding', 'some expected time of closing gate',
TO_DATE('07/06/2017', 'mm/dd/yyyy'), 5, TO_DATE('07/06/2017', 'mm/dd/yyyy')),
(2, 'gate 3', 'Malediwy', 'some another time boarding', 'some another expected time of closing gate',
TO_DATE('07/06/2017', 'mm/dd/yyyy'), 4, TO_DATE('07/06/2017', 'mm/dd/yyyy')),
--(1, 'some boarding time', 'time of closing gate', TO_DATE('06/06/2017', 'mm/dd/yyyy'),'Bieszczady', 2, 'gate 5', 'expectedTimeGateOpen',
--'some main flight', TO_DATE('07/06/2017', 'mm/dd/yyyy'), TO_DATE('07/06/2017', 'mm/dd/yyyy'),
 --'all locations', TO_DATE('06/06/2017', 'mm/dd/yyyy')),
--(2, 'second boarding time', 'time of closing gate', 'Malediwy', 'gate 4');


