-- --------------------------------------------------------
-- 호스트:                          C:\Users\user\Downloads\owner_animal.sqlite
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

-- 테이블 owner_animal.animal 구조 내보내기
CREATE TABLE IF NOT EXISTS `animal` (
  `id` integer primary key AUTOINCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `age` integer DEFAULT NULL,
  `owner_id` integer DEFAULT NULL,
  CONSTRAINT `fk_animal_owner` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`)
);

-- 테이블 데이터 owner_animal.animal:-1 rows 내보내기
/*!40000 ALTER TABLE "animal" DISABLE KEYS */;
INSERT INTO "animal" ("id", "name", "age", "owner_id") VALUES
	(1, '슈슈', 2, 1),
	(2, '치치', 2, 1),
	(3, '마당이', 5, 2),
	(4, '얼룩이', 7, 2);
/*!40000 ALTER TABLE "animal" ENABLE KEYS */;

-- 테이블 owner_animal.owner 구조 내보내기
CREATE TABLE IF NOT EXISTS `owner` (
  `id` integer primary key AUTOINCREMENT,
  `name` varchar(50) DEFAULT NULL
);

-- 테이블 데이터 owner_animal.owner:-1 rows 내보내기
/*!40000 ALTER TABLE "owner" DISABLE KEYS */;
INSERT INTO "owner" ("id", "name") VALUES
	(1, '최길동'),
	(2, '홍길동');
/*!40000 ALTER TABLE "owner" ENABLE KEYS */;

-- 테이블 owner_animal.product 구조 내보내기
CREATE TABLE IF NOT EXISTS `product` (
  `id` integer primary key AUTOINCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `price` integer DEFAULT NULL,
  `animal_id` integer DEFAULT NULL,
  CONSTRAINT `fk_product_animal` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id`));

-- 테이블 데이터 owner_animal.product:-1 rows 내보내기
/*!40000 ALTER TABLE "product" DISABLE KEYS */;
INSERT INTO "product" ("id", "name", "price", "animal_id") VALUES
	(1, '목줄', 5000, 1),
	(2, '몸통줄', 5000, 2),
	(3, '중형견 사료', 10000, 1),
	(4, '소형견 사료', 10000, 2),
	(5, '중형묘 사료', 12000, 3),
	(6, '소형묘 사료', 12000, 4);
/*!40000 ALTER TABLE "product" ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
