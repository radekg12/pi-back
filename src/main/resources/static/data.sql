CREATE TABLE IF NOT EXISTS user(
  id int(11) NOT NULL auto_increment,
  email VARCHAR(255),
  firstName  VARCHAR(255),
  lastName VARCHAR(255),
  PRIMARY KEY (id)
);