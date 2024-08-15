create database Human

CREATE TABLE HumanType (
typeid INT PRIMARY KEY,
typename NVARCHAR(50)
)

CREATE TABLE Human (
humanid INT PRIMARY KEY,
humanname NVARCHAR(50),
humandob DATE,
humangender BIT,
typeid INT,
FOREIGN KEY (typeid) REFERENCES HumanType(typeid)
)

INSERT INTO HumanType (typeid, typename) VALUES
(1, 'student'),
(2, 'teacher'),
(3, 'worker')

INSERT INTO Human (humanid, humanname, humandob, humangender, typeid) VALUES
(1, 'Steve', '2020-09-09', 1, 1),
(2, 'Elon', '2019-09-09', 0, 2),
(3, 'Musk', '2020-09-09', 1, 3),
(4, 'Melon', '2019-08-09', 1, 3),
(5, 'Kais', '2020-07-08', 0, 3)

INSERT INTO Human (humanid, humanname, humandob, humangender, typeid) VALUES
(6, 'Ttttt', '2020-09-09', 1, 1)

SELECT h.humanid, h.humanname, h.humandob, h.humangender, ht.typeid, ht.typename FROM Human h INNER JOIN HumanType ht ON h.typeid = ht.typeid