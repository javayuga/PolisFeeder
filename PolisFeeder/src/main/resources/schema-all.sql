DROP TABLE domains IF EXISTS;

CREATE TABLE domains  (
    domain VARCHAR(50) NOT NULL PRIMARY KEY,
	document VARCHAR(2000),
	name VARCHAR(2000),
	uf VARCHAR(2000),
	city VARCHAR(2000),
	cep VARCHAR(2000),
	inclusionDate VARCHAR(4000),
	lastUpdate VARCHAR(4000),
	ticketNo VARCHAR(4000)
);
