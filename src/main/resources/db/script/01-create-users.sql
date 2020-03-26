CREATE TABLE users (
  user_id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  email_verified BOOLEAN NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (user_id)
);