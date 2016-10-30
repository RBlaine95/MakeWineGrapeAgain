DROP TABLE batch IF EXISTS
CREATE TABLE batch (batchid TEXT(15), colour TEXT(15), type TEXT(25), stage TEXT(2), mass FLOAT(15), supplierid TEXT(30))
INSERT INTO batch (batchid, colour, type, stage, mass, supplierid) VALUES('SH82ASD1', 'shetwat', 'asdf', '0', 100.0, 'Herrty')
INSERT INTO batch (batchid, colour, type, stage, mass, supplierid) VALUES('SH87ASD2', 'shetwat', 'asdf', '1', 1000.0, 'casin')
DROP TABLE subbatch IF EXISTS
CREATE TABLE subbatch (subbatchid TEXT(15), colour TEXT(15), type TEXT(25), stage TEXT(2), mass FLOAT(15), supplierid TEXT(30))
INSERT INTO subbatch (subbatchid, colour, type, stage, mass, supplierid) VALUES('SH82ASD1SB1', 'shetwat', 'asdf', '0', 50.0, 'Herrty')
INSERT INTO subbatch (subbatchid, colour, type, stage, mass, supplierid) VALUES('SH82ASD1SB2', 'shetwat', 'asdf', '0', 50.0, 'Herrty')
INSERT INTO subbatch (subbatchid, colour, type, stage, mass, supplierid) VALUES('SH82ASD1SB3', 'shetwat', 'asdf', '0', 85.0, 'Herrty')
INSERT INTO subbatch (subbatchid, colour, type, stage, mass, supplierid) VALUES('SH82ASD1SB4', 'shetwat', 'asdf', '0', 75.0, 'Herrty')
DROP TABLE supplier IF EXISTS
CREATE TABLE supplier (sname TEXT(30), tel TEXT(10), email TEXT(50), liason TEXT(30))
DROP TABLE blend IF EXISTS
CREATE TABLE blend (bid TEXT(50), winename TEXT(50), colour TEXT(30), volume FLOAT(15), stage TEXT(2), fid1 TEXT(15), pid1 FLOAT(15), fid2 TEXT(15), pid2 FLOAT(15), fid3 TEXT(15), pid3 FLOAT(15), fid4 TEXT(15), pid4 FLOAT(15),fid5 TEXT(15), pid5 FLOAT(15), fid6 TEXT(15), pid6 FLOAT(15), fid7 TEXT(15), pid7 FLOAT(15), fid8 TEXT(15), pid8 FLOAT(15), fid9 TEXT(15), pid9 FLOAT(15))
INSERT INTO blend (bid, winename, colour, volume, stage, fid1, pid1, fid2, pid2, fid3, pid3, fid4, pid4, fid5, pid5, fid6, pid6, fid7, pid7, fid8, pid8, fid9, pid9) VALUES('Blend_0', 'Plswekr', 'shetwat', 20.0, '0', 'SH82ASD1SB1', 50.0, 'null', 0.0, 'null', 0.0, 'null', 0.0, 'null', 0.0, 'null', 0.0, 'null', 0.0, 'null', 0.0, 'null', 0.0)
