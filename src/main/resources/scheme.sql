INSERT INTO T_ROLE VALUES(1, 1, '2020-08-24 10:00:00', 1, 'admin', 1,  '2020-08-24 10:00:00');
INSERT INTO T_ROLE VALUES(2, 1, '2020-08-24 10:00:00', 1, 'tea', 1,  '2020-08-24 10:00:00');
INSERT INTO T_ROLE VALUES(3, 1, '2020-08-24 10:00:00', 1, 'stu', 1,  '2020-08-24 10:00:00');

insert into t_user
(id, age, create_by, create_time, gender, is_del, nickname, no, password, phone, role_id, update_by, update_time, username)
values
(1, 1, 1, '2020-08-24 10:00:00', 1, 1, 'Admin', 1, 'e3afed0047b08059d0fada10f400c1e5', '18202559941','1', '1', '2020-08-24 10:00:00', 'Admin')