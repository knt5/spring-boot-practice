create table users (
	username varchar(100) primary key not null,
	encoded_password varchar(255)
) default charset=utf8;

/* password = demo */
insert into users (username, encoded_password) VALUES ('doraemon', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9');
insert into users (username, encoded_password) VALUES ('nobita', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9');
insert into users (username, encoded_password) VALUES ('shizuka', 'ce5f8d0c5790bf82e9b253d362feb51ba02853301ae24149b260bd30acb00f1b2a0d8b18bbff97a9');

ALTER TABLE customers ADD username VARCHAR(100) NOT NULL DEFAULT 'doraemon';
ALTER TABLE customers ADD CONSTRAINT FK_CUSTOMERS_USERNAME FOREIGN KEY (username) REFERENCES users;
