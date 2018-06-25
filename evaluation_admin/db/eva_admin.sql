/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.22-log : Database - eva_admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eva_admin` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `eva_admin`;

/*Table structure for table `bte_evalrefquestion` */

DROP TABLE IF EXISTS `bte_evalrefquestion`;

CREATE TABLE `bte_evalrefquestion` (
  `data_no` int(11) NOT NULL AUTO_INCREMENT,
  `eval_id` int(11) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`data_no`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_evalrefquestion` */

insert  into `bte_evalrefquestion`(`data_no`,`eval_id`,`question_id`) values 
(1,9,1),
(2,9,2),
(3,10,1),
(4,10,2),
(5,11,1),
(6,11,2),
(7,12,1),
(8,12,2),
(9,12,4),
(10,12,5),
(11,12,6),
(12,12,7),
(13,12,8),
(14,12,9),
(15,12,10),
(16,12,11),
(17,12,12),
(18,12,13),
(19,12,14),
(20,12,15),
(21,12,16),
(22,13,12),
(23,13,13),
(24,13,14),
(25,13,15),
(26,13,16),
(27,14,1),
(28,14,2),
(29,14,4),
(30,14,5),
(31,14,6),
(32,14,7),
(33,14,8),
(34,14,9),
(35,14,10),
(36,14,11),
(37,14,12),
(38,14,13),
(39,14,14),
(40,14,15),
(41,14,16);

/*Table structure for table `bte_evaluate` */

DROP TABLE IF EXISTS `bte_evaluate`;

CREATE TABLE `bte_evaluate` (
  `data_no` int(11) NOT NULL AUTO_INCREMENT,
  `eval_title` varchar(200) DEFAULT NULL,
  `eval_memo` text,
  `eval_state_id` int(11) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`data_no`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_evaluate` */

insert  into `bte_evaluate`(`data_no`,`eval_title`,`eval_memo`,`eval_state_id`,`create_date`,`create_user_id`) values 
(1,'第一期培训班测评','第一期培训班测评说明',2,NULL,1),
(2,'第二期培训测评','第二期',1,NULL,1),
(3,'第三期培训班测评','第三期说明',0,'2018-06-20 08:48:14',1),
(4,'公安部创建第一期测评','测评1',0,'2018-06-20 08:51:53',2),
(5,'公安部创建第二期测评','第二期',0,'2018-06-20 08:52:12',2),
(6,'人事训练局创建第一期测评','第一期',0,'2018-06-20 08:52:33',3),
(7,'人事训练局创建第二期测评','第二期',0,'2018-06-20 08:52:48',3),
(8,'测试测试一个测评','123121',0,'2018-06-20 14:01:53',1),
(9,'测评9',NULL,0,'2018-06-20 14:12:52',1),
(10,'测评10',NULL,0,'2018-06-20 14:13:04',1),
(11,'测评11',NULL,0,'2018-06-20 14:13:15',1),
(12,'公安部第十四期培训班测评','测评14',1,'2018-06-20 14:17:32',1),
(13,'局级第十五期培训班测评',NULL,1,'2018-06-20 14:18:18',1),
(14,'6月21正式测评','这是一个测试前台好用与否的测评这是一个测试前台好用与否的测评这是一个测试前台好用与否的测评这是一个测试前台好用与否的测评这是一个测试前台好用与否的测评这是一个测试前台好用与否的测评这是一个测试前台好用与否的测评这是一个测试前台好用与否的测评这是一个测试前台好用与否的测评',1,'2018-06-21 14:03:44',1);

/*Table structure for table `bte_lesson` */

DROP TABLE IF EXISTS `bte_lesson`;

CREATE TABLE `bte_lesson` (
  `data_no` int(11) NOT NULL AUTO_INCREMENT,
  `eval_id` int(11) DEFAULT NULL,
  `lesson_title` varchar(200) DEFAULT NULL,
  `lesson_category_id` int(11) DEFAULT NULL COMMENT '1:公共类 2：专业类',
  `lesson_type_id` int(11) DEFAULT NULL,
  `lesson_teacher_name` varchar(40) DEFAULT NULL,
  `lesson_pid` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`data_no`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_lesson` */

insert  into `bte_lesson`(`data_no`,`eval_id`,`lesson_title`,`lesson_category_id`,`lesson_type_id`,`lesson_teacher_name`,`lesson_pid`,`create_date`,`create_user_id`) values 
(7,1,'习近平提出这\"六大原则\"',NULL,1,'周骏译','210603198404263016',NULL,NULL),
(8,1,'习近平心中的长江经济带新路子什么样',NULL,3,'三胖子','210603198804263015',NULL,NULL),
(9,1,'改革开放为什么能成功',NULL,2,'金家藩','210603198404263302',NULL,NULL),
(10,2,'\"贸易恐怖主义\"救不了美国',NULL,1,'膜大','21060319840426312',NULL,NULL),
(11,12,'党的章程',NULL,3,'周骏译','210603198404263016',NULL,NULL),
(12,12,'童心向党',NULL,2,'王力宏','210603198404263045',NULL,NULL),
(13,14,'计算机科学与技术',NULL,1,'周骏译','210603198404263016',NULL,NULL),
(14,14,'线性代数',NULL,2,'战力书','2106032198404263321',NULL,NULL),
(15,14,'青山绿水就是金山银山',NULL,3,'周骏译','210603198404263016',NULL,NULL),
(16,13,'学习十九大，实现中国梦',NULL,3,'周骏译','210603198404263016',NULL,NULL),
(17,13,'汤不热攻略',NULL,2,'张兴哲','xxxxxxx',NULL,NULL),
(18,13,'SpringBoot精髓',NULL,1,'李家志','123456',NULL,NULL),
(19,13,'MySql王者晋级之路',NULL,2,'张冠李戴','12345633',NULL,NULL),
(20,14,'公开课测试1',1,5,'王金宇','123456',NULL,NULL),
(21,14,'我的课程2',1,4,'就额','1231231312',NULL,NULL),
(25,14,'测试课程',2,37,'张中华','1234856',NULL,NULL),
(26,14,'科恒',1,30,'周','123123',NULL,NULL);

/*Table structure for table `bte_lesson_type` */

DROP TABLE IF EXISTS `bte_lesson_type`;

CREATE TABLE `bte_lesson_type` (
  `data_no` tinyint(4) NOT NULL AUTO_INCREMENT,
  `category_id` tinyint(4) DEFAULT NULL COMMENT '1:公共类 2：专业类',
  `type_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`data_no`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_lesson_type` */

insert  into `bte_lesson_type`(`data_no`,`category_id`,`type_name`) values 
(1,1,'政治理论类'),
(2,1,'党风廉政建设类'),
(3,1,'总体形式任务分析类\r\n'),
(4,1,'公安改革强警战略类\r\n'),
(5,1,'公安大数据战略类\r\n'),
(6,1,'法律法规类\r\n'),
(7,1,'舆情引导类'),
(8,1,'保密教育类'),
(9,1,'安全防护类\r\n'),
(10,1,'警务实战训练类（指挥决策）\r\n'),
(11,1,'警务实战训练类（武器警械）'),
(12,1,'警务实战训练类（徒手控制）\r\n'),
(13,1,'警务实战训练类（查缉战术）\r\n'),
(14,1,'警务实战训练类（综合演练）'),
(15,1,'警务实战训练类（现场急救）\r\n'),
(16,1,'警务实战训练类（水上救生）\r\n'),
(17,1,'警务实战训练类（生化防护）\r\n'),
(18,1,'警务实战训练类（安检防爆）'),
(19,1,'警务实战训练类（警务体能）\r\n'),
(20,2,'纪检监察审计类'),
(21,2,'警务督察巡视类\r\n'),
(22,2,'公文写作管理类'),
(23,2,'新闻管理类\r\n'),
(24,2,'档案管理类\r\n'),
(25,2,'信访类\r\n'),
(26,2,'密码机要类\r\n'),
(27,2,'警务指挥类'),
(28,2,'干部人事类\r\n'),
(29,2,'机关党建类\r\n'),
(30,2,'教育训练类\r\n'),
(31,2,'宣传文化类\r\n'),
(32,2,'表彰奖励类\r\n'),
(33,2,'心理健康类\r\n'),
(34,2,'国内安全保卫类\r\n'),
(35,2,'经济侦查类\r\n'),
(36,2,'治安管理类\r\n'),
(37,2,'刑事侦查类\r\n'),
(38,2,'移民管理类\r\n'),
(39,2,'网络安全保卫类'),
(40,2,'技术侦查类\r\n'),
(41,2,'监所管理类\r\n'),
(42,2,'交通管理类\r\n'),
(43,2,'公安法制类\r\n'),
(44,2,'国际合作类\r\n'),
(45,2,'装备财务类\r\n'),
(46,2,'禁毒类\r\n'),
(47,2,'科技信息化类\r\n'),
(48,2,'情报建设类\r\n'),
(49,2,'反恐怖类\r\n'),
(50,2,'警务群团类\r\n');

/*Table structure for table `bte_question` */

DROP TABLE IF EXISTS `bte_question`;

CREATE TABLE `bte_question` (
  `data_no` int(11) NOT NULL AUTO_INCREMENT,
  `question_title` varchar(400) DEFAULT NULL,
  `question_type_id` int(11) DEFAULT NULL,
  `question_state_id` int(11) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `create_user_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`data_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_question` */

insert  into `bte_question`(`data_no`,`question_title`,`question_type_id`,`question_state_id`,`create_date`,`create_user_Id`) values 
(1,'培训时间安排是否科学？',4,1,NULL,NULL),
(2,'培训地点选择是否合理？',4,1,'2018-06-19 20:25:33',1),
(4,'培训课程安排是否全面？',4,1,'2018-06-20 14:06:45',1),
(5,'教学方法是否贴近实战？',4,1,'2018-06-20 14:06:54',1),
(6,'您的收获大吗？',4,1,'2018-06-20 14:07:10',1),
(7,'班主任发挥作用如何？',2,1,'2018-06-20 14:07:18',1),
(8,'其他学管干部发挥作用如何？',2,1,'2018-06-20 14:07:27',1),
(9,'此次培训的管理秩序如何？',2,1,'2018-06-20 14:07:40',1),
(10,'基地交通条件',3,1,'2018-06-20 14:07:50',1),
(11,'基地场馆条件',3,1,'2018-06-20 14:08:31',1),
(12,'装备器材条件',3,1,'2018-06-20 14:08:41',1),
(13,'基地住宿条件',3,1,'2018-06-20 14:08:49',1),
(14,'基地餐饮条件',3,1,'2018-06-20 14:08:59',1),
(15,'基地服务水平',3,1,'2018-06-20 14:10:27',1),
(16,'具体建议',5,1,'2018-06-20 14:10:40',1);

/*Table structure for table `bte_result` */

DROP TABLE IF EXISTS `bte_result`;

CREATE TABLE `bte_result` (
  `data_no` int(11) NOT NULL AUTO_INCREMENT,
  `eval_id` int(11) DEFAULT NULL,
  `question_type_id` int(11) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  `question_score` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `eval_suggest` text,
  PRIMARY KEY (`data_no`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_result` */

insert  into `bte_result`(`data_no`,`eval_id`,`question_type_id`,`question_id`,`question_score`,`create_date`,`eval_suggest`) values 
(45,14,1,13,5,'2018-06-21 19:55:25',NULL),
(46,14,1,14,4,'2018-06-21 19:55:25',NULL),
(47,14,1,15,3,'2018-06-21 19:55:25',NULL),
(48,14,2,9,1,'2018-06-21 19:55:25',NULL),
(49,14,2,7,1,'2018-06-21 19:55:25',NULL),
(50,14,2,8,5,'2018-06-21 19:55:25',NULL),
(51,14,3,11,5,'2018-06-21 19:55:25',NULL),
(52,14,3,14,5,'2018-06-21 19:55:25',NULL),
(53,14,3,12,5,'2018-06-21 19:55:25',NULL),
(54,14,3,10,5,'2018-06-21 19:55:25',NULL),
(55,14,3,15,5,'2018-06-21 19:55:25',NULL),
(56,14,3,13,4,'2018-06-21 19:55:25',NULL),
(57,14,4,1,4,'2018-06-21 19:55:25',NULL),
(58,14,4,5,4,'2018-06-21 19:55:25',NULL),
(59,14,4,2,5,'2018-06-21 19:55:25',NULL),
(60,14,4,6,5,'2018-06-21 19:55:25',NULL),
(61,14,4,4,5,'2018-06-21 19:55:25',NULL),
(62,14,5,16,NULL,'2018-06-21 19:55:25','好建议'),
(63,14,1,13,5,'2018-06-21 20:05:58',NULL),
(64,14,1,14,5,'2018-06-21 20:05:58',NULL),
(65,14,1,15,5,'2018-06-21 20:05:58',NULL),
(66,14,2,9,5,'2018-06-21 20:05:58',NULL),
(67,14,2,7,5,'2018-06-21 20:05:58',NULL),
(68,14,2,8,5,'2018-06-21 20:05:58',NULL),
(69,14,3,14,5,'2018-06-21 20:05:58',NULL),
(70,14,3,12,5,'2018-06-21 20:05:58',NULL),
(71,14,3,10,5,'2018-06-21 20:05:58',NULL),
(72,14,3,15,5,'2018-06-21 20:05:58',NULL),
(73,14,3,13,5,'2018-06-21 20:05:58',NULL),
(74,14,3,11,5,'2018-06-21 20:05:58',NULL),
(75,14,4,6,5,'2018-06-21 20:05:58',NULL),
(76,14,4,1,5,'2018-06-21 20:05:58',NULL),
(77,14,4,2,5,'2018-06-21 20:05:58',NULL),
(78,14,4,5,5,'2018-06-21 20:05:58',NULL),
(79,14,4,4,5,'2018-06-21 20:05:58',NULL),
(80,14,5,16,NULL,'2018-06-21 20:05:58',''),
(81,14,1,13,5,'2018-06-21 20:05:59',NULL),
(82,14,1,14,5,'2018-06-21 20:05:59',NULL),
(83,14,1,15,5,'2018-06-21 20:05:59',NULL),
(84,14,2,9,5,'2018-06-21 20:05:59',NULL),
(85,14,2,7,5,'2018-06-21 20:05:59',NULL),
(86,14,2,8,5,'2018-06-21 20:05:59',NULL),
(87,14,3,14,5,'2018-06-21 20:05:59',NULL),
(88,14,3,12,5,'2018-06-21 20:05:59',NULL),
(89,14,3,10,5,'2018-06-21 20:05:59',NULL),
(90,14,3,15,5,'2018-06-21 20:05:59',NULL),
(91,14,3,13,5,'2018-06-21 20:05:59',NULL),
(92,14,3,11,5,'2018-06-21 20:05:59',NULL),
(93,14,4,6,5,'2018-06-21 20:05:59',NULL),
(94,14,4,1,5,'2018-06-21 20:05:59',NULL),
(95,14,4,2,5,'2018-06-21 20:05:59',NULL),
(96,14,4,5,5,'2018-06-21 20:05:59',NULL),
(97,14,4,4,5,'2018-06-21 20:05:59',NULL),
(98,14,5,16,NULL,'2018-06-21 20:05:59',''),
(99,14,1,13,5,'2018-06-21 20:14:08',NULL),
(100,14,1,14,5,'2018-06-21 20:14:08',NULL),
(101,14,1,15,4,'2018-06-21 20:14:08',NULL),
(102,14,2,8,3,'2018-06-21 20:14:08',NULL),
(103,14,2,7,4,'2018-06-21 20:14:08',NULL),
(104,14,2,9,4,'2018-06-21 20:14:08',NULL),
(105,14,3,14,2,'2018-06-21 20:14:08',NULL),
(106,14,3,13,5,'2018-06-21 20:14:08',NULL),
(107,14,3,11,3,'2018-06-21 20:14:08',NULL),
(108,14,3,15,5,'2018-06-21 20:14:08',NULL),
(109,14,3,12,2,'2018-06-21 20:14:08',NULL),
(110,14,3,10,1,'2018-06-21 20:14:08',NULL),
(111,14,4,6,4,'2018-06-21 20:14:08',NULL),
(112,14,4,1,3,'2018-06-21 20:14:08',NULL),
(113,14,4,2,5,'2018-06-21 20:14:08',NULL),
(114,14,4,5,5,'2018-06-21 20:14:08',NULL),
(115,14,4,4,3,'2018-06-21 20:14:08',NULL),
(116,14,5,16,NULL,'2018-06-21 20:14:08',''),
(117,14,1,13,1,'2018-06-21 20:41:44',NULL),
(118,14,1,14,1,'2018-06-21 20:41:44',NULL),
(119,14,1,15,1,'2018-06-21 20:41:44',NULL),
(120,14,2,9,1,'2018-06-21 20:41:44',NULL),
(121,14,2,7,3,'2018-06-21 20:41:44',NULL),
(122,14,2,8,3,'2018-06-21 20:41:44',NULL),
(123,14,3,13,3,'2018-06-21 20:41:44',NULL),
(124,14,3,11,5,'2018-06-21 20:41:44',NULL),
(125,14,3,14,5,'2018-06-21 20:41:44',NULL),
(126,14,3,12,4,'2018-06-21 20:41:44',NULL),
(127,14,3,10,4,'2018-06-21 20:41:44',NULL),
(128,14,3,15,3,'2018-06-21 20:41:44',NULL),
(129,14,4,6,2,'2018-06-21 20:41:44',NULL),
(130,14,4,1,3,'2018-06-21 20:41:44',NULL),
(131,14,4,2,3,'2018-06-21 20:41:44',NULL),
(132,14,4,5,3,'2018-06-21 20:41:44',NULL),
(133,14,4,4,3,'2018-06-21 20:41:44',NULL),
(134,14,5,16,NULL,'2018-06-21 20:41:44',''),
(135,14,1,13,5,'2018-06-21 20:43:21',NULL),
(136,14,1,14,4,'2018-06-21 20:43:21',NULL),
(137,14,1,15,4,'2018-06-21 20:43:21',NULL),
(138,14,2,9,3,'2018-06-21 20:43:21',NULL),
(139,14,2,7,3,'2018-06-21 20:43:21',NULL),
(140,14,2,8,4,'2018-06-21 20:43:21',NULL),
(141,14,3,13,4,'2018-06-21 20:43:21',NULL),
(142,14,3,11,3,'2018-06-21 20:43:21',NULL),
(143,14,3,14,5,'2018-06-21 20:43:21',NULL),
(144,14,3,12,2,'2018-06-21 20:43:21',NULL),
(145,14,3,10,1,'2018-06-21 20:43:21',NULL),
(146,14,3,15,1,'2018-06-21 20:43:21',NULL),
(147,14,4,6,5,'2018-06-21 20:43:21',NULL),
(148,14,4,1,5,'2018-06-21 20:43:21',NULL),
(149,14,4,2,5,'2018-06-21 20:43:21',NULL),
(150,14,4,5,5,'2018-06-21 20:43:21',NULL),
(151,14,4,4,5,'2018-06-21 20:43:21',NULL),
(152,14,5,16,NULL,'2018-06-21 20:43:21',''),
(153,14,1,13,1,'2018-06-22 10:52:04',NULL),
(154,14,1,14,1,'2018-06-22 10:52:04',NULL),
(155,14,1,15,3,'2018-06-22 10:52:04',NULL),
(156,14,2,9,1,'2018-06-22 10:52:04',NULL),
(157,14,2,7,2,'2018-06-22 10:52:04',NULL),
(158,14,2,8,4,'2018-06-22 10:52:04',NULL),
(159,14,3,13,4,'2018-06-22 10:52:04',NULL),
(160,14,3,15,2,'2018-06-22 10:52:04',NULL),
(161,14,3,11,3,'2018-06-22 10:52:04',NULL),
(162,14,3,10,5,'2018-06-22 10:52:04',NULL),
(163,14,3,12,3,'2018-06-22 10:52:04',NULL),
(164,14,3,14,5,'2018-06-22 10:52:04',NULL),
(165,14,4,6,3,'2018-06-22 10:52:04',NULL),
(166,14,4,2,2,'2018-06-22 10:52:04',NULL),
(167,14,4,4,2,'2018-06-22 10:52:04',NULL),
(168,14,4,5,2,'2018-06-22 10:52:04',NULL),
(169,14,4,1,2,'2018-06-22 10:52:04',NULL),
(170,14,5,16,NULL,'2018-06-22 10:52:04',''),
(171,14,1,13,5,'2018-06-22 15:09:02',NULL),
(172,14,1,14,5,'2018-06-22 15:09:02',NULL),
(173,14,1,15,5,'2018-06-22 15:09:02',NULL),
(174,14,2,9,5,'2018-06-22 15:09:02',NULL),
(175,14,2,7,5,'2018-06-22 15:09:02',NULL),
(176,14,2,8,5,'2018-06-22 15:09:02',NULL),
(177,14,3,11,5,'2018-06-22 15:09:02',NULL),
(178,14,3,12,5,'2018-06-22 15:09:02',NULL),
(179,14,3,10,5,'2018-06-22 15:09:02',NULL),
(180,14,3,14,5,'2018-06-22 15:09:02',NULL),
(181,14,3,13,5,'2018-06-22 15:09:02',NULL),
(182,14,3,15,5,'2018-06-22 15:09:02',NULL),
(183,14,4,4,5,'2018-06-22 15:09:02',NULL),
(184,14,4,5,5,'2018-06-22 15:09:02',NULL),
(185,14,4,1,5,'2018-06-22 15:09:02',NULL),
(186,14,4,6,5,'2018-06-22 15:09:02',NULL),
(187,14,4,2,5,'2018-06-22 15:09:02',NULL),
(188,14,5,16,NULL,'2018-06-22 15:09:02','巴巴爸爸巴巴爸爸啊'),
(189,14,1,13,1,'2018-06-22 15:09:35',NULL),
(190,14,1,14,1,'2018-06-22 15:09:35',NULL),
(191,14,1,15,1,'2018-06-22 15:09:35',NULL),
(192,14,2,7,1,'2018-06-22 15:09:35',NULL),
(193,14,2,8,1,'2018-06-22 15:09:35',NULL),
(194,14,2,9,1,'2018-06-22 15:09:35',NULL),
(195,14,3,15,1,'2018-06-22 15:09:35',NULL),
(196,14,3,12,1,'2018-06-22 15:09:35',NULL),
(197,14,3,10,1,'2018-06-22 15:09:35',NULL),
(198,14,3,13,1,'2018-06-22 15:09:35',NULL),
(199,14,3,14,1,'2018-06-22 15:09:35',NULL),
(200,14,3,11,1,'2018-06-22 15:09:35',NULL),
(201,14,4,2,1,'2018-06-22 15:09:35',NULL),
(202,14,4,4,1,'2018-06-22 15:09:35',NULL),
(203,14,4,5,1,'2018-06-22 15:09:35',NULL),
(204,14,4,1,1,'2018-06-22 15:09:35',NULL),
(205,14,4,6,1,'2018-06-22 15:09:35',NULL),
(206,14,5,16,NULL,'2018-06-22 15:09:35',NULL),
(207,13,1,16,5,'2018-06-22 15:36:47',NULL),
(208,13,1,17,5,'2018-06-22 15:36:47',NULL),
(209,13,1,18,5,'2018-06-22 15:36:47',NULL),
(210,13,1,19,5,'2018-06-22 15:36:47',NULL),
(211,13,3,13,1,'2018-06-22 15:36:47',NULL),
(212,13,3,14,1,'2018-06-22 15:36:47',NULL),
(213,13,3,12,2,'2018-06-22 15:36:47',NULL),
(214,13,3,15,2,'2018-06-22 15:36:47',NULL),
(215,13,5,16,NULL,'2018-06-22 15:36:47','好，我很喜欢这次培训'),
(216,13,1,16,5,'2018-06-22 22:08:19',NULL),
(217,13,1,17,5,'2018-06-22 22:08:19',NULL),
(218,13,1,18,5,'2018-06-22 22:08:19',NULL),
(219,13,1,19,5,'2018-06-22 22:08:19',NULL),
(220,13,3,15,5,'2018-06-22 22:08:19',NULL),
(221,13,3,14,5,'2018-06-22 22:08:19',NULL),
(222,13,3,12,5,'2018-06-22 22:08:19',NULL),
(223,13,3,13,5,'2018-06-22 22:08:19',NULL),
(224,13,5,16,NULL,'2018-06-22 22:08:19',NULL);

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_cron_triggers` */

insert  into `qrtz_cron_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`CRON_EXPRESSION`,`TIME_ZONE_ID`) values 
('RenrenScheduler','TASK_1','DEFAULT','0 0/30 * * * ?','Asia/Shanghai'),
('RenrenScheduler','TASK_2','DEFAULT','0 0/30 * * * ?','Asia/Shanghai');

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_job_details` */

insert  into `qrtz_job_details`(`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`JOB_CLASS_NAME`,`IS_DURABLE`,`IS_NONCONCURRENT`,`IS_UPDATE_DATA`,`REQUESTS_RECOVERY`,`JOB_DATA`) values 
('RenrenScheduler','TASK_1','DEFAULT',NULL,'io.renren.modules.job.utils.ScheduleJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.io.renren.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0\nmethodNameq\0~\0	L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0X���0xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0testt\0renrent\0有参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0'),
('RenrenScheduler','TASK_2','DEFAULT',NULL,'io.renren.modules.job.utils.ScheduleJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.io.renren.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0\nmethodNameq\0~\0	L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0X�w�`xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0test2pt\0无参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0x\0');

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values 
('RenrenScheduler','STATE_ACCESS'),
('RenrenScheduler','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values 
('RenrenScheduler','WIN-19ISNCJ37UB1529637549537',1529914382503,15000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_triggers` */

insert  into `qrtz_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`NEXT_FIRE_TIME`,`PREV_FIRE_TIME`,`PRIORITY`,`TRIGGER_STATE`,`TRIGGER_TYPE`,`START_TIME`,`END_TIME`,`CALENDAR_NAME`,`MISFIRE_INSTR`,`JOB_DATA`) values 
('RenrenScheduler','TASK_1','DEFAULT','TASK_1','DEFAULT',NULL,1529915400000,1529913600000,5,'WAITING','CRON',1529375408000,0,NULL,2,'��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.io.renren.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0\nmethodNameq\0~\0	L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0X���0xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0testt\0renrent\0有参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0'),
('RenrenScheduler','TASK_2','DEFAULT','TASK_2','DEFAULT',NULL,1529377200000,-1,5,'PAUSED','CRON',1529375408000,0,NULL,2,'��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.io.renren.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0\nmethodNameq\0~\0	L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0X�w�`xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0test2pt\0无参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0x\0');

/*Table structure for table `schedule_job` */

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

/*Data for the table `schedule_job` */

insert  into `schedule_job`(`job_id`,`bean_name`,`method_name`,`params`,`cron_expression`,`status`,`remark`,`create_time`) values 
(1,'testTask','test','renren','0 0/30 * * * ?',0,'有参数测试','2016-12-01 23:16:46'),
(2,'testTask','test2',NULL,'0 0/30 * * * ?',1,'无参数测试','2016-12-03 14:55:56');

/*Table structure for table `schedule_job_log` */

DROP TABLE IF EXISTS `schedule_job_log`;

CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

/*Data for the table `schedule_job_log` */

insert  into `schedule_job_log`(`log_id`,`job_id`,`bean_name`,`method_name`,`params`,`status`,`error`,`times`,`create_time`) values 
(1,1,'testTask','test','renren',0,NULL,1015,'2018-06-19 11:30:00'),
(2,1,'testTask','test','renren',0,NULL,1010,'2018-06-19 12:00:00'),
(3,1,'testTask','test','renren',0,NULL,1009,'2018-06-19 12:30:00'),
(4,1,'testTask','test','renren',0,NULL,1011,'2018-06-19 13:00:00'),
(5,1,'testTask','test','renren',0,NULL,1007,'2018-06-19 13:30:00'),
(6,1,'testTask','test','renren',0,NULL,1019,'2018-06-19 14:00:00'),
(7,1,'testTask','test','renren',0,NULL,1012,'2018-06-19 14:30:00'),
(8,1,'testTask','test','renren',0,NULL,1020,'2018-06-19 15:00:00'),
(9,1,'testTask','test','renren',0,NULL,1029,'2018-06-19 15:30:00'),
(10,1,'testTask','test','renren',0,NULL,1017,'2018-06-19 16:00:00'),
(11,1,'testTask','test','renren',0,NULL,1019,'2018-06-19 20:30:00'),
(12,1,'testTask','test','renren',0,NULL,1036,'2018-06-19 21:00:00'),
(13,1,'testTask','test','renren',0,NULL,1051,'2018-06-19 21:30:00'),
(14,1,'testTask','test','renren',0,NULL,1041,'2018-06-20 09:00:00'),
(15,1,'testTask','test','renren',0,NULL,1030,'2018-06-20 10:00:00'),
(16,1,'testTask','test','renren',0,NULL,1027,'2018-06-20 11:00:00'),
(17,1,'testTask','test','renren',0,NULL,1022,'2018-06-20 11:30:00'),
(18,1,'testTask','test','renren',0,NULL,1011,'2018-06-20 12:00:00'),
(19,1,'testTask','test','renren',0,NULL,1012,'2018-06-20 12:30:00'),
(20,1,'testTask','test','renren',0,NULL,1027,'2018-06-20 13:00:00'),
(21,1,'testTask','test','renren',0,NULL,1028,'2018-06-20 13:30:00'),
(22,1,'testTask','test','renren',0,NULL,1033,'2018-06-20 14:30:00'),
(23,1,'testTask','test','renren',0,NULL,1034,'2018-06-20 15:00:00'),
(24,1,'testTask','test','renren',0,NULL,1051,'2018-06-21 08:30:00'),
(25,1,'testTask','test','renren',0,NULL,1023,'2018-06-21 09:00:00'),
(26,1,'testTask','test','renren',0,NULL,1029,'2018-06-21 11:00:00'),
(27,1,'testTask','test','renren',0,NULL,1046,'2018-06-21 11:30:00'),
(28,1,'testTask','test','renren',0,NULL,1013,'2018-06-21 12:00:00'),
(29,1,'testTask','test','renren',0,NULL,1019,'2018-06-21 14:30:00'),
(30,1,'testTask','test','renren',0,NULL,1009,'2018-06-21 15:00:00'),
(31,1,'testTask','test','renren',0,NULL,1062,'2018-06-21 15:30:00'),
(32,1,'testTask','test','renren',0,NULL,1008,'2018-06-21 16:00:00'),
(33,1,'testTask','test','renren',0,NULL,1052,'2018-06-21 19:00:00'),
(34,1,'testTask','test','renren',0,NULL,1049,'2018-06-21 19:30:00'),
(35,1,'testTask','test','renren',0,NULL,1007,'2018-06-21 20:00:00'),
(36,1,'testTask','test','renren',0,NULL,1008,'2018-06-21 20:30:00'),
(37,1,'testTask','test','renren',0,NULL,1020,'2018-06-22 09:00:00'),
(38,1,'testTask','test','renren',0,NULL,1011,'2018-06-22 09:30:00'),
(39,1,'testTask','test','renren',0,NULL,1021,'2018-06-22 10:00:00'),
(40,1,'testTask','test','renren',0,NULL,1017,'2018-06-22 10:30:00'),
(41,1,'testTask','test','renren',0,NULL,1064,'2018-06-22 11:00:00'),
(42,1,'testTask','test','renren',0,NULL,1023,'2018-06-22 11:30:00'),
(43,1,'testTask','test','renren',0,NULL,1019,'2018-06-22 12:00:00'),
(44,1,'testTask','test','renren',0,NULL,1016,'2018-06-22 12:30:01'),
(45,1,'testTask','test','renren',0,NULL,1023,'2018-06-22 13:00:00'),
(46,1,'testTask','test','renren',0,NULL,1017,'2018-06-22 13:30:00'),
(47,1,'testTask','test','renren',0,NULL,1089,'2018-06-22 14:00:00'),
(48,1,'testTask','test','renren',0,NULL,1011,'2018-06-22 14:30:00'),
(49,1,'testTask','test','renren',0,NULL,1144,'2018-06-22 15:00:00'),
(50,1,'testTask','test','renren',0,NULL,1026,'2018-06-22 15:30:00'),
(51,1,'testTask','test','renren',0,NULL,1061,'2018-06-22 16:00:00'),
(52,1,'testTask','test','renren',0,NULL,1031,'2018-06-22 16:30:00'),
(53,1,'testTask','test','renren',0,NULL,1032,'2018-06-22 17:00:00'),
(54,1,'testTask','test','renren',0,NULL,1025,'2018-06-22 17:30:00'),
(55,1,'testTask','test','renren',0,NULL,1049,'2018-06-22 18:00:00'),
(56,1,'testTask','test','renren',0,NULL,1028,'2018-06-22 18:30:00'),
(57,1,'testTask','test','renren',0,NULL,1034,'2018-06-22 19:00:00'),
(58,1,'testTask','test','renren',0,NULL,1025,'2018-06-22 19:30:00'),
(59,1,'testTask','test','renren',0,NULL,1047,'2018-06-22 20:00:00'),
(60,1,'testTask','test','renren',0,NULL,1021,'2018-06-22 20:30:00'),
(61,1,'testTask','test','renren',0,NULL,1035,'2018-06-22 21:00:00'),
(62,1,'testTask','test','renren',0,NULL,1053,'2018-06-22 21:30:00'),
(63,1,'testTask','test','renren',0,NULL,1052,'2018-06-22 22:00:00'),
(64,1,'testTask','test','renren',0,NULL,1021,'2018-06-22 22:30:00'),
(65,1,'testTask','test','renren',0,NULL,1019,'2018-06-22 23:00:00'),
(66,1,'testTask','test','renren',0,NULL,1014,'2018-06-22 23:30:00'),
(67,1,'testTask','test','renren',0,NULL,1059,'2018-06-23 00:00:00'),
(68,1,'testTask','test','renren',0,NULL,1013,'2018-06-23 00:30:00'),
(69,1,'testTask','test','renren',0,NULL,1016,'2018-06-23 01:00:00'),
(70,1,'testTask','test','renren',0,NULL,1013,'2018-06-23 01:30:00'),
(71,1,'testTask','test','renren',0,NULL,1022,'2018-06-23 02:00:00'),
(72,1,'testTask','test','renren',0,NULL,1021,'2018-06-23 02:30:00'),
(73,1,'testTask','test','renren',0,NULL,1022,'2018-06-23 03:00:00'),
(74,1,'testTask','test','renren',0,NULL,1030,'2018-06-23 03:30:00'),
(75,1,'testTask','test','renren',0,NULL,1016,'2018-06-23 04:00:00'),
(76,1,'testTask','test','renren',0,NULL,1020,'2018-06-23 04:30:00'),
(77,1,'testTask','test','renren',0,NULL,1019,'2018-06-23 05:00:00'),
(78,1,'testTask','test','renren',0,NULL,1019,'2018-06-23 05:30:00'),
(79,1,'testTask','test','renren',0,NULL,1032,'2018-06-23 06:00:00'),
(80,1,'testTask','test','renren',0,NULL,1017,'2018-06-23 06:30:00'),
(81,1,'testTask','test','renren',0,NULL,1020,'2018-06-23 07:00:00'),
(82,1,'testTask','test','renren',0,NULL,1012,'2018-06-23 07:30:00'),
(83,1,'testTask','test','renren',0,NULL,1015,'2018-06-23 08:00:00'),
(84,1,'testTask','test','renren',0,NULL,1011,'2018-06-23 08:30:00'),
(85,1,'testTask','test','renren',0,NULL,1017,'2018-06-23 09:00:00'),
(86,1,'testTask','test','renren',0,NULL,1027,'2018-06-23 09:30:00'),
(87,1,'testTask','test','renren',0,NULL,1022,'2018-06-23 10:00:00'),
(88,1,'testTask','test','renren',0,NULL,1031,'2018-06-23 10:30:00'),
(89,1,'testTask','test','renren',0,NULL,1013,'2018-06-23 11:00:00'),
(90,1,'testTask','test','renren',0,NULL,1011,'2018-06-23 11:30:00'),
(91,1,'testTask','test','renren',0,NULL,1015,'2018-06-23 12:00:00'),
(92,1,'testTask','test','renren',0,NULL,1013,'2018-06-23 12:30:00'),
(93,1,'testTask','test','renren',0,NULL,1012,'2018-06-23 13:00:00'),
(94,1,'testTask','test','renren',0,NULL,1014,'2018-06-23 13:30:00'),
(95,1,'testTask','test','renren',0,NULL,1047,'2018-06-23 14:00:00'),
(96,1,'testTask','test','renren',0,NULL,1010,'2018-06-23 14:30:00'),
(97,1,'testTask','test','renren',0,NULL,1010,'2018-06-23 15:00:00'),
(98,1,'testTask','test','renren',0,NULL,1011,'2018-06-23 15:30:00'),
(99,1,'testTask','test','renren',0,NULL,1011,'2018-06-23 16:00:00'),
(100,1,'testTask','test','renren',0,NULL,1011,'2018-06-23 16:30:00'),
(101,1,'testTask','test','renren',0,NULL,1015,'2018-06-23 17:00:00'),
(102,1,'testTask','test','renren',0,NULL,1012,'2018-06-23 17:30:00'),
(103,1,'testTask','test','renren',0,NULL,1017,'2018-06-23 18:00:00'),
(104,1,'testTask','test','renren',0,NULL,1011,'2018-06-23 18:30:00'),
(105,1,'testTask','test','renren',0,NULL,1014,'2018-06-23 19:00:00'),
(106,1,'testTask','test','renren',0,NULL,1019,'2018-06-23 19:30:00'),
(107,1,'testTask','test','renren',0,NULL,1016,'2018-06-23 20:00:00'),
(108,1,'testTask','test','renren',0,NULL,1027,'2018-06-23 20:30:00'),
(109,1,'testTask','test','renren',0,NULL,1012,'2018-06-23 21:00:00'),
(110,1,'testTask','test','renren',0,NULL,1011,'2018-06-23 21:30:00'),
(111,1,'testTask','test','renren',0,NULL,1015,'2018-06-23 22:00:00'),
(112,1,'testTask','test','renren',0,NULL,1015,'2018-06-23 22:30:00'),
(113,1,'testTask','test','renren',0,NULL,1025,'2018-06-23 23:00:00'),
(114,1,'testTask','test','renren',0,NULL,1012,'2018-06-23 23:30:00'),
(115,1,'testTask','test','renren',0,NULL,1017,'2018-06-24 00:00:00'),
(116,1,'testTask','test','renren',0,NULL,1017,'2018-06-24 00:30:00'),
(117,1,'testTask','test','renren',0,NULL,1016,'2018-06-24 01:00:00'),
(118,1,'testTask','test','renren',0,NULL,1011,'2018-06-24 01:30:00'),
(119,1,'testTask','test','renren',0,NULL,1017,'2018-06-24 02:00:00'),
(120,1,'testTask','test','renren',0,NULL,1011,'2018-06-24 02:30:00'),
(121,1,'testTask','test','renren',0,NULL,1014,'2018-06-24 03:00:00'),
(122,1,'testTask','test','renren',0,NULL,1017,'2018-06-24 03:30:00'),
(123,1,'testTask','test','renren',0,NULL,1014,'2018-06-24 04:00:00'),
(124,1,'testTask','test','renren',0,NULL,1013,'2018-06-24 04:30:00'),
(125,1,'testTask','test','renren',0,NULL,1017,'2018-06-24 05:00:00'),
(126,1,'testTask','test','renren',0,NULL,1019,'2018-06-24 05:30:00'),
(127,1,'testTask','test','renren',0,NULL,1010,'2018-06-24 06:00:00'),
(128,1,'testTask','test','renren',0,NULL,1031,'2018-06-24 06:30:00'),
(129,1,'testTask','test','renren',0,NULL,1015,'2018-06-24 07:00:00'),
(130,1,'testTask','test','renren',0,NULL,1009,'2018-06-24 07:30:00'),
(131,1,'testTask','test','renren',0,NULL,1027,'2018-06-24 08:00:00'),
(132,1,'testTask','test','renren',0,NULL,1009,'2018-06-24 08:30:00'),
(133,1,'testTask','test','renren',0,NULL,1012,'2018-06-24 09:00:00'),
(134,1,'testTask','test','renren',0,NULL,1011,'2018-06-24 09:30:00'),
(135,1,'testTask','test','renren',0,NULL,1013,'2018-06-24 10:00:00'),
(136,1,'testTask','test','renren',0,NULL,1022,'2018-06-24 10:30:00'),
(137,1,'testTask','test','renren',0,NULL,1015,'2018-06-24 11:00:00'),
(138,1,'testTask','test','renren',0,NULL,1015,'2018-06-24 11:30:00'),
(139,1,'testTask','test','renren',0,NULL,1015,'2018-06-24 12:00:00'),
(140,1,'testTask','test','renren',0,NULL,1011,'2018-06-24 12:30:00'),
(141,1,'testTask','test','renren',0,NULL,1014,'2018-06-24 13:00:00'),
(142,1,'testTask','test','renren',0,NULL,1011,'2018-06-24 13:30:00'),
(143,1,'testTask','test','renren',0,NULL,1011,'2018-06-24 14:00:00'),
(144,1,'testTask','test','renren',0,NULL,1018,'2018-06-24 14:30:00'),
(145,1,'testTask','test','renren',0,NULL,1010,'2018-06-24 15:00:00'),
(146,1,'testTask','test','renren',0,NULL,1012,'2018-06-24 15:30:00'),
(147,1,'testTask','test','renren',0,NULL,1019,'2018-06-24 16:00:00'),
(148,1,'testTask','test','renren',0,NULL,1012,'2018-06-24 16:30:00'),
(149,1,'testTask','test','renren',0,NULL,1013,'2018-06-24 17:00:00'),
(150,1,'testTask','test','renren',0,NULL,1013,'2018-06-24 17:30:00'),
(151,1,'testTask','test','renren',0,NULL,1011,'2018-06-24 18:00:00'),
(152,1,'testTask','test','renren',0,NULL,1011,'2018-06-24 18:30:00'),
(153,1,'testTask','test','renren',0,NULL,1080,'2018-06-24 19:00:00'),
(154,1,'testTask','test','renren',0,NULL,1025,'2018-06-24 19:30:00'),
(155,1,'testTask','test','renren',0,NULL,1015,'2018-06-24 20:00:00'),
(156,1,'testTask','test','renren',0,NULL,1016,'2018-06-24 20:30:00'),
(157,1,'testTask','test','renren',0,NULL,1011,'2018-06-24 21:00:00'),
(158,1,'testTask','test','renren',0,NULL,1006,'2018-06-24 21:30:00'),
(159,1,'testTask','test','renren',0,NULL,1013,'2018-06-24 22:00:00'),
(160,1,'testTask','test','renren',0,NULL,1014,'2018-06-24 22:30:00'),
(161,1,'testTask','test','renren',0,NULL,1009,'2018-06-24 23:00:00'),
(162,1,'testTask','test','renren',0,NULL,1009,'2018-06-24 23:30:00'),
(163,1,'testTask','test','renren',0,NULL,1020,'2018-06-25 00:00:00'),
(164,1,'testTask','test','renren',0,NULL,1015,'2018-06-25 00:30:00'),
(165,1,'testTask','test','renren',0,NULL,1010,'2018-06-25 01:00:00'),
(166,1,'testTask','test','renren',0,NULL,1020,'2018-06-25 01:30:00'),
(167,1,'testTask','test','renren',0,NULL,1017,'2018-06-25 02:00:00'),
(168,1,'testTask','test','renren',0,NULL,1013,'2018-06-25 02:30:00'),
(169,1,'testTask','test','renren',0,NULL,1008,'2018-06-25 03:00:00'),
(170,1,'testTask','test','renren',0,NULL,1012,'2018-06-25 03:30:00'),
(171,1,'testTask','test','renren',0,NULL,1013,'2018-06-25 04:00:00'),
(172,1,'testTask','test','renren',0,NULL,1014,'2018-06-25 04:30:00'),
(173,1,'testTask','test','renren',0,NULL,1025,'2018-06-25 05:00:00'),
(174,1,'testTask','test','renren',0,NULL,1010,'2018-06-25 05:30:00'),
(175,1,'testTask','test','renren',0,NULL,1011,'2018-06-25 06:00:00'),
(176,1,'testTask','test','renren',0,NULL,1014,'2018-06-25 06:30:00'),
(177,1,'testTask','test','renren',0,NULL,1013,'2018-06-25 07:00:00'),
(178,1,'testTask','test','renren',0,NULL,1012,'2018-06-25 07:30:00'),
(179,1,'testTask','test','renren',0,NULL,1010,'2018-06-25 08:00:00'),
(180,1,'testTask','test','renren',0,NULL,1010,'2018-06-25 08:30:00'),
(181,1,'testTask','test','renren',0,NULL,1065,'2018-06-25 09:00:00'),
(182,1,'testTask','test','renren',0,NULL,1009,'2018-06-25 09:00:00'),
(183,1,'testTask','test','renren',0,NULL,1388,'2018-06-25 09:30:00'),
(184,1,'testTask','test','renren',0,NULL,1014,'2018-06-25 09:30:00'),
(185,1,'testTask','test','renren',0,NULL,1007,'2018-06-25 10:00:00'),
(186,1,'testTask','test','renren',0,NULL,1089,'2018-06-25 10:00:00'),
(187,1,'testTask','test','renren',0,NULL,1012,'2018-06-25 10:30:00'),
(188,1,'testTask','test','renren',0,NULL,1009,'2018-06-25 10:30:00'),
(189,1,'testTask','test','renren',0,NULL,1013,'2018-06-25 11:00:00'),
(190,1,'testTask','test','renren',0,NULL,1016,'2018-06-25 11:00:00'),
(191,1,'testTask','test','renren',0,NULL,1008,'2018-06-25 11:30:00'),
(192,1,'testTask','test','renren',0,NULL,1040,'2018-06-25 11:30:00'),
(193,1,'testTask','test','renren',0,NULL,1009,'2018-06-25 12:00:00'),
(194,1,'testTask','test','renren',0,NULL,1013,'2018-06-25 12:00:00'),
(195,1,'testTask','test','renren',0,NULL,1017,'2018-06-25 12:30:00'),
(196,1,'testTask','test','renren',0,NULL,1007,'2018-06-25 12:30:00'),
(197,1,'testTask','test','renren',0,NULL,1013,'2018-06-25 13:00:00'),
(198,1,'testTask','test','renren',0,NULL,1013,'2018-06-25 13:00:00'),
(199,1,'testTask','test','renren',0,NULL,1025,'2018-06-25 13:30:00'),
(200,1,'testTask','test','renren',0,NULL,1039,'2018-06-25 14:00:00'),
(201,1,'testTask','test','renren',0,NULL,1040,'2018-06-25 14:30:00'),
(202,1,'testTask','test','renren',0,NULL,1031,'2018-06-25 14:30:00'),
(203,1,'testTask','test','renren',0,NULL,1237,'2018-06-25 15:00:02'),
(204,1,'testTask','test','renren',0,NULL,1037,'2018-06-25 15:00:00'),
(205,1,'testTask','test','renren',0,NULL,1087,'2018-06-25 15:30:00'),
(206,1,'testTask','test','renren',0,NULL,1007,'2018-06-25 15:30:00'),
(207,1,'testTask','test','renren',0,NULL,1009,'2018-06-25 16:00:00'),
(208,1,'testTask','test','renren',0,NULL,1021,'2018-06-25 16:00:00');

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`id`,`param_key`,`param_value`,`status`,`remark`) values 
(1,'CLOUD_STORAGE_CONFIG_KEY','{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',0,'云存储配置信息');

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='部门管理';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`dept_id`,`parent_id`,`name`,`order_num`,`del_flag`) values 
(1,0,'公安部',0,0),
(2,1,'人事训练局',1,0),
(3,1,'宣传局',2,0),
(4,3,'技术部',0,-1),
(5,3,'销售部',1,-1);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `type` varchar(100) NOT NULL COMMENT '字典类型',
  `code` varchar(100) NOT NULL COMMENT '字典码',
  `value` varchar(1000) NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`type`,`code`,`value`,`order_num`,`remark`,`del_flag`) values 
(1,'性别','sex','0','女',0,NULL,-1),
(2,'性别','sex','1','男',1,NULL,-1),
(3,'性别','sex','2','未知',3,NULL,-1),
(4,'试题类型','stlx','4','总体评价',4,NULL,0),
(5,'试题类型','stlx','2','管理质量',2,NULL,0),
(6,'试题类型','stlx','3','服务质量',3,NULL,0),
(7,'试题类型','stlx','5','其他建议',4,NULL,0),
(8,'课程分类','kcfl','1','公共课',1,NULL,0),
(9,'课程分类','kcfl','2','专业课',2,NULL,0),
(11,'启用状态','qyzt','0','关闭',0,NULL,0),
(12,'启用状态','qyzt','1','启用',1,NULL,0),
(13,'测评状态','cpzt','0','未开始',0,NULL,0),
(14,'测评状态','cpzt','1','进行中',1,NULL,0),
(15,'测评状态','cpzt','2','已结束',2,NULL,0);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='系统日志';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`username`,`operation`,`method`,`params`,`time`,`ip`,`create_date`) values 
(1,'admin','保存角色','io.renren.modules.sys.controller.SysRoleController.save()','{\"roleId\":1,\"roleName\":\"部级管理员\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,31,32,33,34,35,36,37,38,39,40],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',309,'0:0:0:0:0:0:0:1','2018-06-19 10:47:09'),
(2,'admin','保存角色','io.renren.modules.sys.controller.SysRoleController.save()','{\"roleId\":2,\"roleName\":\"人事训练局管理员\",\"deptId\":2,\"deptName\":\"人事训练局\",\"menuIdList\":[1,36,37,38,39,40],\"deptIdList\":[2],\"createTime\":\"Jun 19, 2018 10:47:58 AM\"}',83,'0:0:0:0:0:0:0:1','2018-06-19 10:47:58'),
(3,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,31,32,33,34,35,36,37,38,39,40],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',45,'0:0:0:0:0:0:0:1','2018-06-19 10:48:55'),
(4,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":2,\"roleName\":\"人事训练局\",\"deptId\":2,\"deptName\":\"人事训练局\",\"menuIdList\":[1,36,37,38,39,40],\"deptIdList\":[2],\"createTime\":\"Jun 19, 2018 10:47:58 AM\"}',34,'0:0:0:0:0:0:0:1','2018-06-19 10:49:13'),
(5,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":2,\"roleName\":\"局级\",\"deptId\":2,\"deptName\":\"人事训练局\",\"menuIdList\":[1,36,37,38,39,40],\"deptIdList\":[2],\"createTime\":\"Jun 19, 2018 10:47:58 AM\"}',26,'0:0:0:0:0:0:0:1','2018-06-19 10:49:29'),
(6,'admin','保存用户','io.renren.modules.sys.controller.SysUserController.save()','{\"userId\":2,\"username\":\"gab\",\"password\":\"34f347ccb8f7fa74809273faffb249e123e630831f4171966a27255f9661d8ff\",\"salt\":\"jirNx0zYOOxyF0vC2cHA\",\"email\":\"gab@163.com\",\"status\":1,\"roleIdList\":[1],\"createTime\":\"Jun 19, 2018 10:50:05 AM\",\"deptId\":1,\"deptName\":\"公安部\"}',122,'0:0:0:0:0:0:0:1','2018-06-19 10:50:05'),
(7,'admin','修改用户','io.renren.modules.sys.controller.SysUserController.update()','{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@163.com\",\"mobile\":\"\",\"status\":1,\"roleIdList\":[],\"createTime\":\"Nov 11, 2016 11:11:11 AM\",\"deptId\":1,\"deptName\":\"公安部\"}',22,'0:0:0:0:0:0:0:1','2018-06-19 10:50:29'),
(8,'admin','保存用户','io.renren.modules.sys.controller.SysUserController.save()','{\"userId\":3,\"username\":\"xlj\",\"password\":\"aa791c9196d1b15e546b45647780a251a1d649865b9378351798d6323d38d73d\",\"salt\":\"ThKDkUYfS61nNn9r3d3V\",\"email\":\"xlj@163.com\",\"status\":1,\"roleIdList\":[2],\"createTime\":\"Jun 19, 2018 10:51:18 AM\",\"deptId\":2,\"deptName\":\"人事训练局\"}',28,'0:0:0:0:0:0:0:1','2018-06-19 10:51:18'),
(9,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":2,\"roleName\":\"局级\",\"deptId\":2,\"deptName\":\"人事训练局\",\"menuIdList\":[1,2,15,16,17,18,36,37,38,39,40],\"deptIdList\":[2],\"createTime\":\"Jun 19, 2018 10:47:58 AM\"}',44,'0:0:0:0:0:0:0:1','2018-06-19 10:53:20'),
(10,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,31,32,33,34,35,36,37,38,39,40],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',150,'127.0.0.1','2018-06-19 11:29:28'),
(11,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":41,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"测评管理\",\"type\":0,\"icon\":\"fa fa-industry\",\"orderNum\":0}',22,'0:0:0:0:0:0:0:1','2018-06-19 13:28:38'),
(12,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,31,32,33,34,35,36,37,38,39,40,41],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',54,'0:0:0:0:0:0:0:1','2018-06-19 13:32:01'),
(13,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":2,\"roleName\":\"局级\",\"deptId\":2,\"deptName\":\"人事训练局\",\"menuIdList\":[1,2,15,16,17,18,36,37,38,39,40,41],\"deptIdList\":[2],\"createTime\":\"Jun 19, 2018 10:47:58 AM\"}',42,'0:0:0:0:0:0:0:1','2018-06-19 13:32:21'),
(14,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":2,\"roleName\":\"局级\",\"deptId\":2,\"deptName\":\"人事训练局\",\"menuIdList\":[41],\"deptIdList\":[2],\"createTime\":\"Jun 19, 2018 10:47:58 AM\"}',31,'0:0:0:0:0:0:0:1','2018-06-19 13:32:44'),
(15,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":42,\"parentId\":2,\"parentName\":\"管理员管理\",\"name\":\"密码重置\",\"perms\":\"sys:user:psdreset\",\"type\":2,\"orderNum\":0}',22,'0:0:0:0:0:0:0:1','2018-06-19 13:35:18'),
(16,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,42,3,19,20,21,22,4,23,24,25,26,31,32,33,34,35,36,37,38,39,40,41],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',54,'0:0:0:0:0:0:0:1','2018-06-19 13:36:27'),
(17,'admin','重置密码','io.renren.modules.sys.controller.SysUserController.passwordReset()','[2]',30,'0:0:0:0:0:0:0:1','2018-06-19 13:38:25'),
(18,'gab','修改密码','io.renren.modules.sys.controller.SysUserController.password()','\"000000\"',16,'0:0:0:0:0:0:0:1','2018-06-19 13:38:40'),
(19,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,31,32,33,34,35,36,37,38,39,40,41],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',71,'0:0:0:0:0:0:0:1','2018-06-19 13:39:38'),
(20,'admin','重置密码','io.renren.modules.sys.controller.SysUserController.passwordReset()','[1]',2,'0:0:0:0:0:0:0:1','2018-06-19 13:55:56'),
(21,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":48,\"parentId\":43,\"parentName\":\"试题管理\",\"name\":\"变更状态\",\"perms\":\"sys:btequestion:state\",\"type\":2,\"orderNum\":0}',23,'0:0:0:0:0:0:0:1','2018-06-19 20:26:27'),
(22,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,31,32,33,34,35,36,37,38,39,40,41,43,44,45,46,47,48],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',50,'0:0:0:0:0:0:0:1','2018-06-19 20:28:58'),
(23,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":49,\"parentId\":41,\"parentName\":\"测评管理\",\"name\":\"测评信息管理\",\"url\":\"modules/sys/bteevaluate.html\",\"type\":1,\"icon\":\"fa fa-cubes\",\"orderNum\":0}',22,'0:0:0:0:0:0:0:1','2018-06-19 21:02:38'),
(24,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":43,\"parentId\":41,\"parentName\":\"测评管理\",\"name\":\"试题管理\",\"url\":\"modules/sys/btequestion.html\",\"type\":1,\"icon\":\"fa fa-file-code-o\",\"orderNum\":1}',13,'0:0:0:0:0:0:0:1','2018-06-19 21:04:31'),
(25,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":50,\"parentId\":43,\"parentName\":\"试题管理\",\"name\":\"查看\",\"perms\":\"sys:bteevaluate:list,sys:bteevaluate:info\",\"type\":2,\"orderNum\":0}',11,'0:0:0:0:0:0:0:1','2018-06-19 21:06:30'),
(26,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":51,\"parentId\":49,\"parentName\":\"测评信息管理\",\"name\":\"新增\",\"perms\":\"sys:bteevaluate:save\",\"type\":2,\"orderNum\":0}',10,'0:0:0:0:0:0:0:1','2018-06-19 21:06:54'),
(27,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":50,\"parentId\":49,\"parentName\":\"测评信息管理\",\"name\":\"查看\",\"perms\":\"sys:bteevaluate:list,sys:bteevaluate:info\",\"type\":2,\"orderNum\":0}',10,'0:0:0:0:0:0:0:1','2018-06-19 21:07:07'),
(28,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":52,\"parentId\":49,\"parentName\":\"测评信息管理\",\"name\":\"修改\",\"perms\":\"sys:bteevaluate:update\",\"type\":2,\"orderNum\":0}',9,'0:0:0:0:0:0:0:1','2018-06-19 21:07:25'),
(29,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":53,\"parentId\":49,\"parentName\":\"测评信息管理\",\"name\":\"删除\",\"perms\":\"sys:bteevaluate:delete\",\"type\":2,\"orderNum\":0}',10,'0:0:0:0:0:0:0:1','2018-06-19 21:07:44'),
(30,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":2,\"roleName\":\"局级\",\"deptId\":2,\"deptName\":\"人事训练局\",\"menuIdList\":[41,43,44,45,46,47,48,49,50,51,52,53],\"deptIdList\":[2],\"createTime\":\"Jun 19, 2018 10:47:58 AM\"}',103,'0:0:0:0:0:0:0:1','2018-06-20 08:50:18'),
(31,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,31,32,33,34,35,36,37,38,39,40,41,43,44,45,46,47,48,49,50,51,52,53],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',78,'0:0:0:0:0:0:0:1','2018-06-20 08:50:30'),
(32,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,31,32,33,34,35,36,37,38,39,40,41,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',78,'0:0:0:0:0:0:0:1','2018-06-20 10:03:11'),
(33,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":2,\"roleName\":\"局级\",\"deptId\":2,\"deptName\":\"人事训练局\",\"menuIdList\":[41,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58],\"deptIdList\":[2],\"createTime\":\"Jun 19, 2018 10:47:58 AM\"}',92,'0:0:0:0:0:0:0:1','2018-06-20 13:37:34'),
(34,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"menuIdList\":[1,2,15,16,17,18,42,3,19,20,21,22,4,23,24,25,26,31,32,33,34,35,36,37,38,39,40,41,43,44,45,46,47,48,49,50,51,52,53],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',258,'58.154.130.77','2018-06-22 09:52:42'),
(35,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":2,\"roleName\":\"局级\",\"deptId\":2,\"menuIdList\":[41,43,44,45,46,47,48,49,50,51,52,53],\"deptIdList\":[2],\"createTime\":\"Jun 19, 2018 10:47:58 AM\"}',71,'58.154.130.77','2018-06-22 09:52:56'),
(36,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":64,\"parentId\":49,\"parentName\":\"测评信息管理\",\"name\":\"未开始\",\"perms\":\"sys:bteevaluate:nostart\",\"type\":2,\"orderNum\":0}',26,'58.154.130.77','2018-06-22 09:56:18'),
(37,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":65,\"parentId\":49,\"parentName\":\"测评信息管理\",\"name\":\"进行中\",\"perms\":\"sys:bteevaluate:ongoing\",\"type\":2,\"orderNum\":0}',23,'58.154.130.77','2018-06-22 09:57:09'),
(38,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":66,\"parentId\":49,\"parentName\":\"测评信息管理\",\"name\":\"结束\",\"perms\":\"sys:bteevaluate:over\",\"type\":2,\"orderNum\":0}',21,'58.154.130.77','2018-06-22 09:57:49'),
(39,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":65,\"parentId\":49,\"parentName\":\"测评信息管理\",\"name\":\"开始\",\"perms\":\"sys:bteevaluate:ongoing\",\"type\":2,\"orderNum\":0}',21,'58.154.130.77','2018-06-22 09:58:10'),
(40,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":65,\"parentId\":49,\"parentName\":\"测评信息管理\",\"name\":\"开始\",\"perms\":\"sys:bteevaluate:start\",\"type\":2,\"orderNum\":0}',27,'58.154.130.77','2018-06-22 09:58:23'),
(41,'admin','修改角色','io.renren.modules.sys.controller.SysRoleController.update()','{\"roleId\":1,\"roleName\":\"部级\",\"deptId\":1,\"deptName\":\"公安部\",\"menuIdList\":[1,2,15,16,17,18,42,3,19,20,21,22,4,23,24,25,26,31,32,33,34,35,36,37,38,39,40,41,43,44,45,46,47,48,49,50,51,52,53,64,65,66],\"deptIdList\":[1,2,3],\"createTime\":\"Jun 19, 2018 10:47:08 AM\"}',145,'58.154.130.77','2018-06-22 10:32:08');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`) values 
(1,0,'系统管理',NULL,NULL,0,'fa fa-cog',0),
(2,1,'管理员管理','modules/sys/user.html',NULL,1,'fa fa-user',1),
(3,1,'角色管理','modules/sys/role.html',NULL,1,'fa fa-user-secret',2),
(4,1,'菜单管理','modules/sys/menu.html',NULL,1,'fa fa-th-list',3),
(5,1,'SQL监控','druid/sql.html',NULL,1,'fa fa-bug',4),
(6,1,'定时任务','modules/job/schedule.html',NULL,1,'fa fa-tasks',5),
(7,6,'查看',NULL,'sys:schedule:list,sys:schedule:info',2,NULL,0),
(8,6,'新增',NULL,'sys:schedule:save',2,NULL,0),
(9,6,'修改',NULL,'sys:schedule:update',2,NULL,0),
(10,6,'删除',NULL,'sys:schedule:delete',2,NULL,0),
(11,6,'暂停',NULL,'sys:schedule:pause',2,NULL,0),
(12,6,'恢复',NULL,'sys:schedule:resume',2,NULL,0),
(13,6,'立即执行',NULL,'sys:schedule:run',2,NULL,0),
(14,6,'日志列表',NULL,'sys:schedule:log',2,NULL,0),
(15,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),
(16,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),
(17,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),
(18,2,'删除',NULL,'sys:user:delete',2,NULL,0),
(19,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),
(20,3,'新增',NULL,'sys:role:save,sys:menu:perms',2,NULL,0),
(21,3,'修改',NULL,'sys:role:update,sys:menu:perms',2,NULL,0),
(22,3,'删除',NULL,'sys:role:delete',2,NULL,0),
(23,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),
(24,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),
(25,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),
(26,4,'删除',NULL,'sys:menu:delete',2,NULL,0),
(27,1,'参数管理','modules/sys/config.html','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'fa fa-sun-o',6),
(29,1,'系统日志','modules/sys/log.html','sys:log:list',1,'fa fa-file-text-o',7),
(30,1,'文件上传','modules/oss/oss.html','sys:oss:all',1,'fa fa-file-image-o',6),
(31,1,'部门管理','modules/sys/dept.html',NULL,1,'fa fa-file-code-o',1),
(32,31,'查看',NULL,'sys:dept:list,sys:dept:info',2,NULL,0),
(33,31,'新增',NULL,'sys:dept:save,sys:dept:select',2,NULL,0),
(34,31,'修改',NULL,'sys:dept:update,sys:dept:select',2,NULL,0),
(35,31,'删除',NULL,'sys:dept:delete',2,NULL,0),
(36,1,'字典管理','modules/sys/dict.html',NULL,1,'fa fa-bookmark-o',6),
(37,36,'查看',NULL,'sys:dict:list,sys:dict:info',2,NULL,6),
(38,36,'新增',NULL,'sys:dict:save',2,NULL,6),
(39,36,'修改',NULL,'sys:dict:update',2,NULL,6),
(40,36,'删除',NULL,'sys:dict:delete',2,NULL,6),
(41,0,'测评管理','',NULL,0,'fa fa-industry',0),
(42,2,'密码重置',NULL,'sys:user:psdreset',2,NULL,0),
(43,41,'试题管理','modules/sys/btequestion.html',NULL,1,'fa fa-file-code-o',1),
(44,43,'查看',NULL,'sys:btequestion:list,sys:btequestion:info',2,NULL,6),
(45,43,'新增',NULL,'sys:btequestion:save',2,NULL,6),
(46,43,'修改',NULL,'sys:btequestion:update',2,NULL,6),
(47,43,'删除',NULL,'sys:btequestion:delete',2,NULL,6),
(48,43,'变更状态',NULL,'sys:btequestion:state',2,NULL,0),
(49,41,'测评信息管理','modules/sys/bteevaluate.html',NULL,1,'fa fa-cubes',0),
(50,49,'查看',NULL,'sys:bteevaluate:list,sys:bteevaluate:info',2,NULL,0),
(51,49,'新增',NULL,'sys:bteevaluate:save',2,NULL,0),
(52,49,'修改',NULL,'sys:bteevaluate:update',2,NULL,0),
(53,49,'删除',NULL,'sys:bteevaluate:delete',2,NULL,0),
(64,49,'未开始',NULL,'sys:bteevaluate:nostart',2,NULL,0),
(65,49,'开始',NULL,'sys:bteevaluate:start',2,NULL,0),
(66,49,'结束',NULL,'sys:bteevaluate:over',2,NULL,0);

/*Table structure for table `sys_oss` */

DROP TABLE IF EXISTS `sys_oss`;

CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

/*Data for the table `sys_oss` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`remark`,`dept_id`,`create_time`) values 
(1,'部级',NULL,1,'2018-06-19 10:47:08'),
(2,'局级',NULL,2,'2018-06-19 10:47:58');

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

/*Data for the table `sys_role_dept` */

insert  into `sys_role_dept`(`id`,`role_id`,`dept_id`) values 
(39,2,2),
(40,1,1),
(41,1,2),
(42,1,3);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=429 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values 
(375,2,41),
(376,2,43),
(377,2,44),
(378,2,45),
(379,2,46),
(380,2,47),
(381,2,48),
(382,2,49),
(383,2,50),
(384,2,51),
(385,2,52),
(386,2,53),
(387,1,1),
(388,1,2),
(389,1,15),
(390,1,16),
(391,1,17),
(392,1,18),
(393,1,42),
(394,1,3),
(395,1,19),
(396,1,20),
(397,1,21),
(398,1,22),
(399,1,4),
(400,1,23),
(401,1,24),
(402,1,25),
(403,1,26),
(404,1,31),
(405,1,32),
(406,1,33),
(407,1,34),
(408,1,35),
(409,1,36),
(410,1,37),
(411,1,38),
(412,1,39),
(413,1,40),
(414,1,41),
(415,1,43),
(416,1,44),
(417,1,45),
(418,1,46),
(419,1,47),
(420,1,48),
(421,1,49),
(422,1,50),
(423,1,51),
(424,1,52),
(425,1,53),
(426,1,64),
(427,1,65),
(428,1,66);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`username`,`password`,`salt`,`email`,`mobile`,`status`,`dept_id`,`create_time`) values 
(1,'admin','e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b','YzcmCZNvbXocrsz9dm8e','root@163.com','',1,1,'2016-11-11 11:11:11'),
(2,'gab','d5704b6794fa5037fa16c2049525f70ab2453aa542b61ff848fca40b83ee7afc','gw9CdAr6WtAYw9RZe1lw','gab@163.com',NULL,1,1,'2018-06-19 10:50:05'),
(3,'xlj','aa791c9196d1b15e546b45647780a251a1d649865b9378351798d6323d38d73d','ThKDkUYfS61nNn9r3d3V','xlj@163.com',NULL,1,2,'2018-06-19 10:51:18');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values 
(1,2,1),
(2,3,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
