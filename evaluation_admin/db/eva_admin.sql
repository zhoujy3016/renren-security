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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_evalrefquestion` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_evaluate` */

/*Table structure for table `bte_lesson` */

DROP TABLE IF EXISTS `bte_lesson`;

CREATE TABLE `bte_lesson` (
  `data_no` int(11) NOT NULL AUTO_INCREMENT,
  `eval_id` int(11) DEFAULT NULL,
  `lesson_title` varchar(200) DEFAULT NULL,
  `lesson_type_id` int(11) DEFAULT NULL,
  `lesson_teacher_name` varchar(40) DEFAULT NULL,
  `lesson_pid` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`data_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_lesson` */

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_question` */

insert  into `bte_question`(`data_no`,`question_title`,`question_type_id`,`question_state_id`,`create_date`,`create_user_Id`) values 
(1,'培训时间安排是否科学？',1,0,NULL,NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `bte_result` */

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
('RenrenScheduler','zhoujy-PC1529395428337',1529395584894,15000);

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
('RenrenScheduler','TASK_1','DEFAULT','TASK_1','DEFAULT',NULL,1529397000000,-1,5,'WAITING','CRON',1529375408000,0,NULL,2,'��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.io.renren.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0\nmethodNameq\0~\0	L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0X���0xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0testt\0renrent\0有参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0'),
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

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
(10,1,'testTask','test','renren',0,NULL,1017,'2018-06-19 16:00:00');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`type`,`code`,`value`,`order_num`,`remark`,`del_flag`) values 
(1,'性别','sex','0','女',0,NULL,-1),
(2,'性别','sex','1','男',1,NULL,-1),
(3,'性别','sex','2','未知',3,NULL,-1),
(4,'试题类型','stlx','1','总体评价',1,NULL,0),
(5,'试题类型','stlx','2','管理质量',2,NULL,0),
(6,'试题类型','stlx','3','服务质量',3,NULL,0),
(7,'试题类型','stlx','5','其他建议',4,NULL,0),
(8,'课程类型','kclx','1','公共课',1,NULL,0),
(9,'课程类型','kclx','2','专业课',2,NULL,0),
(10,'课程类型','kclx','3','十九大',3,NULL,0),
(11,'启用状态','qyzt','0','关闭',0,NULL,0),
(12,'启用状态','qyzt','1','启用',1,NULL,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='系统日志';

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
(20,'admin','重置密码','io.renren.modules.sys.controller.SysUserController.passwordReset()','[1]',2,'0:0:0:0:0:0:0:1','2018-06-19 13:55:56');

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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

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
(43,41,'试题管理','modules/sys/btequestion.html',NULL,1,'fa fa-file-code-o',6),
(44,43,'查看',NULL,'sys:btequestion:list,sys:btequestion:info',2,NULL,6),
(45,43,'新增',NULL,'sys:btequestion:save',2,NULL,6),
(46,43,'修改',NULL,'sys:btequestion:update',2,NULL,6),
(47,43,'删除',NULL,'sys:btequestion:delete',2,NULL,6);

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

/*Data for the table `sys_role_dept` */

insert  into `sys_role_dept`(`id`,`role_id`,`dept_id`) values 
(18,2,2),
(22,1,1),
(23,1,2),
(24,1,3);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values 
(137,2,41),
(166,1,1),
(167,1,2),
(168,1,15),
(169,1,16),
(170,1,17),
(171,1,18),
(172,1,3),
(173,1,19),
(174,1,20),
(175,1,21),
(176,1,22),
(177,1,4),
(178,1,23),
(179,1,24),
(180,1,25),
(181,1,26),
(182,1,31),
(183,1,32),
(184,1,33),
(185,1,34),
(186,1,35),
(187,1,36),
(188,1,37),
(189,1,38),
(190,1,39),
(191,1,40),
(192,1,41);

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
(1,'admin','e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b','YzcmCZNvbXocrsz9dm8e','root@163.com','13612345678',1,1,'2016-11-11 11:11:11'),
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
