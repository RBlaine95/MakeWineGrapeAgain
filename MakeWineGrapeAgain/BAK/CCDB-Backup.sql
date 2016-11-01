DROP TABLE batch IF EXISTS
CREATE TABLE batch (batchid TEXT(15), colour TEXT(15), type TEXT(25), stage TEXT(2), mass FLOAT(15), supplierid TEXT(30), note TEXT(300))
INSERT INTO batch (batchid, colour, type, stage, mass, supplierid, note) VALUES('BL58SHE1', 'Blue', 'sherry', '0', 1000.0, 'a', '')
DROP TABLE subbatch IF EXISTS
CREATE TABLE subbatch (subbatchid TEXT(15), colour TEXT(15), type TEXT(25), stage TEXT(2), mass FLOAT(15), supplierid TEXT(30), note TEXT(300))
DROP TABLE supplier IF EXISTS
CREATE TABLE supplier (sname TEXT(30), tel TEXT(10), email TEXT(50), liason TEXT(30))
INSERT INTO supplier (sname, tel, email, liason) VALUES('a', 'f', 'fc', 'faa')
DROP TABLE blend IF EXISTS
CREATE TABLE blend (bid TEXT(50), winename TEXT(50), colour TEXT(30), volume FLOAT(15), stage TEXT(2), fid1 TEXT(15), pid1 FLOAT(15), fid2 TEXT(15), pid2 FLOAT(15), fid3 TEXT(15), pid3 FLOAT(15), fid4 TEXT(15), pid4 FLOAT(15),fid5 TEXT(15), pid5 FLOAT(15), fid6 TEXT(15), pid6 FLOAT(15), fid7 TEXT(15), pid7 FLOAT(15), fid8 TEXT(15), pid8 FLOAT(15), fid9 TEXT(15), pid9 FLOAT(15), note TEXT(300))
