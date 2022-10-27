-- MySQL dump 10.13  Distrib 8.0.30, for macos12.6 (arm64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `author` varchar(255) NOT NULL,
  `publisher` varchar(255) NOT NULL,
  `datePublished` date DEFAULT NULL,
  `price` double NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `ratingValue` int DEFAULT '0',
  `ratingCount` int DEFAULT '0',
  `categoryId` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('b1','Idea Man','\"The entire conversation took five minutes. When it was over, Bill and I looked at each other. It was one thing to talk about writing a language for a microprocessor and another to get the job done . . . If we\'d been older or known better, Bill and I might have been put off by the task in front of us. But we were young and green enough to believe that we just might pull it off.\" <br/><br/> Paul Allen, best known as the cofounder of Microsoft, has left his mark on numerous fields, from aviation and science to rock \'n\' roll, professional sports, and philanthropy. His passions and curiosity have transformed the way we live.  In 2007 and again in 2008, <i>Time</i> named him one of the hundred most influential people in the world. <br/><br/> It all started on a snowy day in December 1974, when he was twenty-one years old. After buying the new issue of <i>Popular Electronics</i> in Harvard Square, Allen ran to show it to his best friend from Seattle, Bill Gates, then a Harvard undergrad. The magazine\'s cover story featured the Altair 8800, the first true personal computer; Allen knew that he and Gates had the skills to code a programming language for it. When Gates agreed to collaborate on BASIC for the Altair, one of the most influential partnerships of the digital era was up and running. <br/><br/> While much has been written about Microsoft\'s early years, Allen has never before told the story from his point of view.  Nor has he previously talked about the details of his complex relationship with Gates or his behind-closed- doors perspective on how a struggling startup became the most powerful technology company in the world. <i>Idea Man</i> is the candid and long-awaited memoir of an intensely private person, a tale of triumphant highs and terrifying lows. <br/><br/> After becoming seriously ill with Hodgkin\'s lymphoma in 1982, Allen began scaling back his involvement with Microsoft. He recovered and started using his fortune-and his ideas-for a life of adventure and discovery, from the first privately funded spacecraft (SpaceShipOne) to a landmark breakthrough in neuroscience (the Allen Brain Atlas). His eclectic ventures all start with the same simple question: What <i>should</i> exist?  As Allen has written: <br/><br/> <i>To me, that\'s the most exciting question imaginable. . . . From technology to science to music to art, I\'m inspired by those who\'ve blurred the boundaries, who\'ve looked at the possibilities, and said, \"What if...?\"  In my own work, I\'ve tried to anticipate what\'s coming over the horizon, to hasten its arrival, and to apply it to people\'s lives in a meaningful way. . . . The varied possibilities of the universe have dazzled me since I was a child, and they continue to drive my work, my investments, and my philanthropy. </i><br/><br/> <i>Idea Man</i> is an astonishing true story of ideas made real.','Paul Allen','Portfolio Hardcover','2011-04-19',16.48,'book_images/51-yqFdVngL._SS500_.jpg',10,3,'c1'),('b2','The Velocity Manifesto','In this hands-on guide to helping leaders effectively steer their organizations through wrenching infrastructure and social changes, author Scott Klososky details the actions that leaders must take to keep their digital plumbing - the all-important technological infrastructure of their organizations - up-to-date. <p class=\"more_details\"> Readers will learn that the survival of their organization depends on continuously adapting and integrating appropriate new technologies, including everything from robust IT systems to social technologies. Looking into the future, the author presents readers with a planning strategy that will enable them to stay ahead of burgeoning technological trends - especially those that will significantly impact their organizations. </p><p class=\"more_details\"> Klososky deftly combines his discussion of technology with an in-depth, actionable program for workforce integration. By providing essential educational tools, he presents a process for building technology bridges across generations to maximize performance, loyalty, and satisfaction. This call-to-action will energize readers who are frustrated by cloistered IT departments, unproductive lines of communication between Baby Boomers and younger staff members, and an overall lack of technological sophistication. </p><p class=\"more_details\"> By following this book readers will be able to revitalize any business culture threatened by technology. </p><p class=\"more_details\"> With experience at every level of the technology business, from assembling computers as a teenager to delivering TED talks in Mumbai, Scott Klososky is a recognized expert in technological innovation.','Scott Klososky','Greenleaf Book Group Press','2011-02-01',16.47,'book_images/Qffs+v35leryeeQWb1CWFycCUqS1VZ935HdTFtWzSPjJqmHsGB4ObUSVpECGqw7aoEoWnFJFRYM=.jpg',8,3,'c1'),('b3','Born To Run','Full of incredible characters, amazing athletic achievements, cutting-edge science, and, most of all, pure inspiration, <i>Born to Run</i> is an epic adventure that began with one simple question: <i>Why does my foot hurt?</i> In search of an answer, Christopher McDougall sets off to find a tribe of the world�s greatest distance runners and learn their secrets, and in the process shows us that everything we thought we knew about running is wrong.<br/>    <p class=\"more_details\">Isolated by the most savage terrain in North America, the reclusive Tarahumara Indians of Mexico�s deadly Copper Canyons are custodians of a lost art. For centuries they have practiced techniques that allow them to run hundreds of miles without rest and chase down anything from a deer to an Olympic marathoner while enjoying every mile of it. Their superhuman talent is matched by uncanny health and serenity, leaving the Tarahumara immune to the diseases and strife that plague modern existence. With the help of Caballo Blanco, a mysterious loner who lives among the tribe, the author was able not only to uncover the secrets of the Tarahumara but also to find his own inner ultra-athlete, as he trained for the challenge of a lifetime: a fifty-mile race through the heart of Tarahumara country pitting the tribe against an odd band of Americans, including a star ultramarathoner, a beautiful young surfer, and a barefoot wonder.</p>    <p class=\"more_details\">With a sharp wit and wild exuberance, McDougall takes us from the high-tech science labs at Harvard to the sun-baked valleys and freezing peaks across North America, where ever-growing numbers of ultrarunners are pushing their bodies to the limit, and, finally, to the climactic race in the Copper Canyons. <i>Born to Run</i> is that rare book that will not only engage your mind but inspire your body when you realize that the secret to happiness is right at your feet, and that you, indeed all of us, were born to run.</p>','Christopher McDougall','Vintage','2011-03-29',8.96,'book_images/8=.jpg',13,3,'c3'),('b4','Bottom Of The 33rd','<i>No description available</i>','Dan Barry','Harper','2011-04-12',17.09,'book_images/51hG4TRgq7L._SS500_.jpg',9,3,'c3'),('b5','Skipped Parts','<i>No description available</i>','Tim Sandlin','Sourcebooks Landmark','2010-09-01',10.19,'book_images/F0Vv+gqh0u5OC3hL5Ke1B1PI8eaRUTqRfzm218=.jpg',11,3,'c2'),('b6','Life','<i>No description available</i>','Keith Richards','Back Bay Books','2011-05-03',11.31,'book_images/Qffs+v35leqI5aJNtPQvAme0VMsRG+xfAqH8s+3bVf5LnAYG3k7QKp5d6BCDpudYM1YaKEDjB8A=.jpg',11,3,'c2');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sort_order` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('c1','Computers & Internet',1),('c2','Entertainment',3),('c3','Sports',2);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` varchar(255) NOT NULL,
  `rating` int DEFAULT '0',
  `content` varchar(10000) DEFAULT NULL,
  `userId` varchar(255) NOT NULL,
  `bookId` varchar(255) NOT NULL,
  `date` date DEFAULT (curdate()),
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `bookId` (`bookId`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES ('a0ddbf74-4d76-11ed-8652-759bfb819d78',1,'aa','95f12afc-4a2c-11ed-bfad-ff4515a3486c','b3',NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_line` (
  `id` varchar(255) NOT NULL,
  `orderId` varchar(255) NOT NULL,
  `bookId` varchar(255) NOT NULL,
  `unitPrice` double NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `orderId` (`orderId`),
  KEY `bookId` (`bookId`),
  CONSTRAINT `order_line_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_line_ibfk_2` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL,
  `order_date` date DEFAULT (curdate()),
  `amount` double NOT NULL,
  `userId` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `date_created` date DEFAULT (curdate()),
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('95f12afc-4a2c-11ed-bfad-ff4515a3486c','v0d0110','v0d0110','v0d0110','9090909090','valisha','2022-10-11',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-16 23:17:54
