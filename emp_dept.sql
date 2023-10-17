-- --------------------------------------------------------
-- 호스트:                          C:\Users\user\springboot\basic\emp_dept.sqlite
-- 서버 버전:                        3.39.4
-- 서버 OS:                        
-- HeidiSQL 버전:                  12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 emp_dept.dept 구조 내보내기
CREATE TABLE IF NOT EXISTS "dept" (
	"deptno"	INTEGER,
	"dname"	TEXT,
	"loc"	TEXT,
	PRIMARY KEY("deptno")
);

-- 테이블 데이터 emp_dept.dept:4 rows 내보내기
/*!40000 ALTER TABLE "dept" DISABLE KEYS */;
INSERT INTO "dept" ("deptno", "dname", "loc") VALUES
	(10, 'ACCOUNTING', 'NEW YORK'),
	(20, 'RESEARCH', 'DALLAS'),
	(30, 'SALES', 'CHICAGO'),
	(40, 'OPERATIONS', 'BOSTON');
/*!40000 ALTER TABLE "dept" ENABLE KEYS */;

-- 테이블 emp_dept.emp 구조 내보내기
CREATE TABLE IF NOT EXISTS "emp" (
	"empno"	INTEGER,
	"ename"	TEXT,
	"job"	TEXT,
	"mgr"	INTEGER,
	"hiredate"	TEXT,
	"sal"	INTEGER,
	"comm"	INTEGER,
	"deptno"	INTEGER,
	PRIMARY KEY("empno"),
	CONSTRAINT "FK_EMP_DEPT" FOREIGN KEY("deptno") REFERENCES "dept"("deptno") ON DELETE CASCADE
);

-- 테이블 데이터 emp_dept.emp:14 rows 내보내기
/*!40000 ALTER TABLE "emp" DISABLE KEYS */;
INSERT INTO "emp" ("empno", "ename", "job", "mgr", "hiredate", "sal", "comm", "deptno") VALUES
	(7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800, NULL, 20),
	(7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600, 300, 30),
	(7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250, 500, 30),
	(7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975, NULL, 20),
	(7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250, 1400, 30),
	(7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850, NULL, 30),
	(7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450, NULL, 10),
	(7788, 'SCOTT', 'ANALYST', 7566, '1982-12-09', 3000, NULL, 20),
	(7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000, NULL, 10),
	(7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500, 0, 30),
	(7876, 'ADAMS', 'CLERK', 7788, '1983-01-12', 1100, NULL, 20),
	(7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950, NULL, 30),
	(7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000, NULL, 20),
	(7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300, NULL, 10);
/*!40000 ALTER TABLE "emp" ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
