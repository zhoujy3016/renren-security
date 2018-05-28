/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.7.20-log : Database - renren_security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`renren_security` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `renren_security`;

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
('RenrenScheduler','zhoujy-PC1527514036194',1527514328220,15000);

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
('RenrenScheduler','TASK_1','DEFAULT','TASK_1','DEFAULT',NULL,1527516000000,1527514200000,5,'WAITING','CRON',1524745035000,0,NULL,2,'��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.io.renren.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0\nmethodNameq\0~\0	L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0X���0xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0testt\0renrent\0有参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0'),
('RenrenScheduler','TASK_2','DEFAULT','TASK_2','DEFAULT',NULL,1527516000000,1527514200000,5,'WAITING','CRON',1524745035000,0,NULL,2,'��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.io.renren.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0\nmethodNameq\0~\0	L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0X�w�`xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0test2pt\0无参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0');

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
(2,'testTask','test2',NULL,'0 0/30 * * * ?',0,'无参数测试','2016-12-03 14:55:56');

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
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

/*Data for the table `schedule_job_log` */

insert  into `schedule_job_log`(`log_id`,`job_id`,`bean_name`,`method_name`,`params`,`status`,`error`,`times`,`create_time`) values 
(1,1,'testTask','test','renren',0,NULL,1014,'2018-04-26 20:30:00'),
(2,1,'testTask','test','renren',0,NULL,1014,'2018-04-26 21:00:00'),
(3,1,'testTask','test','renren',0,NULL,1050,'2018-05-05 18:00:00'),
(4,1,'testTask','test','renren',0,NULL,1006,'2018-05-05 18:30:00'),
(5,1,'testTask','test','renren',0,NULL,1005,'2018-05-05 19:00:00'),
(6,1,'testTask','test','renren',0,NULL,1018,'2018-05-05 20:00:00'),
(7,1,'testTask','test','renren',0,NULL,1015,'2018-05-06 17:30:00'),
(8,1,'testTask','test','renren',0,NULL,1010,'2018-05-06 18:00:00'),
(9,1,'testTask','test','renren',0,NULL,1006,'2018-05-06 18:30:00'),
(10,1,'testTask','test','renren',0,NULL,1028,'2018-05-06 19:00:00'),
(11,2,'testTask','test2',NULL,0,NULL,1,'2018-05-06 19:14:08'),
(12,2,'testTask','test2',NULL,0,NULL,0,'2018-05-06 19:30:00'),
(13,1,'testTask','test','renren',0,NULL,1012,'2018-05-06 19:30:00'),
(14,2,'testTask','test2',NULL,0,NULL,1,'2018-05-06 20:00:00'),
(15,1,'testTask','test','renren',0,NULL,1046,'2018-05-06 20:00:00'),
(16,2,'testTask','test2',NULL,0,NULL,1,'2018-05-08 10:00:00'),
(17,1,'testTask','test','renren',0,NULL,1017,'2018-05-08 10:00:00'),
(18,2,'testTask','test2',NULL,0,NULL,1,'2018-05-08 10:30:00'),
(19,1,'testTask','test','renren',0,NULL,1004,'2018-05-08 10:30:00'),
(20,2,'testTask','test2',NULL,0,NULL,1,'2018-05-08 11:00:00'),
(21,1,'testTask','test','renren',0,NULL,1053,'2018-05-08 11:00:00'),
(22,2,'testTask','test2',NULL,0,NULL,1,'2018-05-08 12:30:00'),
(23,1,'testTask','test','renren',0,NULL,1017,'2018-05-08 12:30:00'),
(24,2,'testTask','test2',NULL,0,NULL,0,'2018-05-08 13:00:00'),
(25,1,'testTask','test','renren',0,NULL,1018,'2018-05-08 13:00:00'),
(26,2,'testTask','test2',NULL,0,NULL,1,'2018-05-08 14:00:00'),
(27,1,'testTask','test','renren',0,NULL,1097,'2018-05-08 14:00:00'),
(28,2,'testTask','test2',NULL,0,NULL,1,'2018-05-08 14:30:00'),
(29,1,'testTask','test','renren',0,NULL,1038,'2018-05-08 14:30:00'),
(30,2,'testTask','test2',NULL,0,NULL,2,'2018-05-08 15:00:00'),
(31,1,'testTask','test','renren',0,NULL,1012,'2018-05-08 15:00:00'),
(32,2,'testTask','test2',NULL,0,NULL,1,'2018-05-08 16:00:00'),
(33,1,'testTask','test','renren',0,NULL,1055,'2018-05-08 16:00:00'),
(34,2,'testTask','test2',NULL,0,NULL,1,'2018-05-09 09:00:00'),
(35,1,'testTask','test','renren',0,NULL,1018,'2018-05-09 09:00:00'),
(36,2,'testTask','test2',NULL,0,NULL,2,'2018-05-09 09:30:00'),
(37,1,'testTask','test','renren',0,NULL,1021,'2018-05-09 09:30:00'),
(38,2,'testTask','test2',NULL,0,NULL,0,'2018-05-09 11:00:00'),
(39,1,'testTask','test','renren',0,NULL,1008,'2018-05-09 11:00:00'),
(40,2,'testTask','test2',NULL,0,NULL,1,'2018-05-09 11:30:02'),
(41,1,'testTask','test','renren',0,NULL,1217,'2018-05-09 11:30:02'),
(42,2,'testTask','test2',NULL,0,NULL,0,'2018-05-09 12:00:00'),
(43,1,'testTask','test','renren',0,NULL,1064,'2018-05-09 12:00:00'),
(44,2,'testTask','test2',NULL,0,NULL,1,'2018-05-09 12:30:00'),
(45,1,'testTask','test','renren',0,NULL,1032,'2018-05-09 12:30:00'),
(46,2,'testTask','test2',NULL,0,NULL,1,'2018-05-09 13:00:00'),
(47,1,'testTask','test','renren',0,NULL,1003,'2018-05-09 13:00:00'),
(48,2,'testTask','test2',NULL,0,NULL,2,'2018-05-09 13:30:00'),
(49,1,'testTask','test','renren',0,NULL,1015,'2018-05-09 13:30:00'),
(50,2,'testTask','test2',NULL,0,NULL,1,'2018-05-09 14:00:00'),
(51,1,'testTask','test','renren',0,NULL,1028,'2018-05-09 14:00:00'),
(52,2,'testTask','test2',NULL,0,NULL,1,'2018-05-09 14:30:00'),
(53,1,'testTask','test','renren',0,NULL,1005,'2018-05-09 14:30:00'),
(54,2,'testTask','test2',NULL,0,NULL,1,'2018-05-12 09:00:00'),
(55,1,'testTask','test','renren',0,NULL,1017,'2018-05-12 09:00:00'),
(56,2,'testTask','test2',NULL,0,NULL,9,'2018-05-12 09:30:00'),
(57,1,'testTask','test','renren',0,NULL,1037,'2018-05-12 09:30:00'),
(58,2,'testTask','test2',NULL,0,NULL,3,'2018-05-12 10:00:00'),
(59,1,'testTask','test','renren',0,NULL,1048,'2018-05-12 10:00:00'),
(60,2,'testTask','test2',NULL,0,NULL,1,'2018-05-12 15:30:00'),
(61,1,'testTask','test','renren',0,NULL,1026,'2018-05-12 15:30:00'),
(62,2,'testTask','test2',NULL,0,NULL,18,'2018-05-12 16:00:00'),
(63,1,'testTask','test','renren',0,NULL,1037,'2018-05-12 16:00:00'),
(64,2,'testTask','test2',NULL,0,NULL,1,'2018-05-12 20:00:00'),
(65,1,'testTask','test','renren',0,NULL,1022,'2018-05-12 20:00:00'),
(66,2,'testTask','test2',NULL,0,NULL,2,'2018-05-13 13:30:00'),
(67,1,'testTask','test','renren',0,NULL,1029,'2018-05-13 13:30:00'),
(68,2,'testTask','test2',NULL,0,NULL,7,'2018-05-13 15:00:00'),
(69,1,'testTask','test','renren',0,NULL,1039,'2018-05-13 15:00:00'),
(70,2,'testTask','test2',NULL,0,NULL,5,'2018-05-13 15:30:00'),
(71,1,'testTask','test','renren',0,NULL,1030,'2018-05-13 15:30:00'),
(72,2,'testTask','test2',NULL,0,NULL,5,'2018-05-27 14:30:00'),
(73,1,'testTask','test','renren',0,NULL,1078,'2018-05-27 14:30:00'),
(74,2,'testTask','test2',NULL,0,NULL,15,'2018-05-27 15:00:00'),
(75,1,'testTask','test','renren',0,NULL,1050,'2018-05-27 15:00:00'),
(76,2,'testTask','test2',NULL,0,NULL,2,'2018-05-27 15:30:00'),
(77,1,'testTask','test','renren',0,NULL,1012,'2018-05-27 15:30:00'),
(78,2,'testTask','test2',NULL,0,NULL,1,'2018-05-27 16:00:00'),
(79,1,'testTask','test','renren',0,NULL,1020,'2018-05-27 16:00:00'),
(80,2,'testTask','test2',NULL,0,NULL,1,'2018-05-28 21:00:00'),
(81,1,'testTask','test','renren',0,NULL,1021,'2018-05-28 21:00:00'),
(82,2,'testTask','test2',NULL,0,NULL,1,'2018-05-28 21:30:00'),
(83,1,'testTask','test','renren',0,NULL,1043,'2018-05-28 21:30:00');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='部门管理';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`dept_id`,`parent_id`,`name`,`order_num`,`del_flag`) values 
(1,0,'人人开源集团',0,0),
(2,1,'长沙分公司',1,0),
(3,1,'上海分公司',2,0),
(4,3,'技术部',0,0),
(5,3,'销售部',1,0),
(6,4,'软件研发一部',0,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`type`,`code`,`value`,`order_num`,`remark`,`del_flag`) values 
(1,'性别','sex','0','女',0,NULL,0),
(2,'性别','sex','1','男',1,NULL,0),
(3,'性别','sex','2','未知',3,NULL,0),
(4,'民族','mz','1','汉族',1,NULL,0),
(5,'民族','mz','2','满族',2,NULL,0),
(6,'民族','mz','3','朝鲜族',3,NULL,0),
(7,'地区','area','1','沈阳',0,NULL,0),
(8,'地区','area','2','大连',1,NULL,0),
(9,'地区','area','3','丹东',2,NULL,0),
(13,'新闻类型','xwlx','1','科技',1,NULL,0),
(14,'新闻类型','xwlx','2','手机',2,NULL,0),
(15,'新闻类型','xwlx','3','军事',3,NULL,0),
(16,'新闻类型','xwlx','4','头条',4,NULL,0),
(17,'新闻类型','xwlx','5','本地',5,NULL,0),
(18,'警衔','jx','1','三级警督',1,NULL,0),
(19,'警衔','jx','2','三级警监',2,NULL,0),
(20,'警衔','jx','3','一级警司',3,NULL,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='系统日志';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`username`,`operation`,`method`,`params`,`time`,`ip`,`create_date`) values 
(1,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":41,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"人员管理\",\"type\":0,\"orderNum\":0}',19,'0:0:0:0:0:0:0:1','2018-04-26 20:20:39'),
(2,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":42,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"人员管理\",\"url\":\"user\",\"type\":1,\"orderNum\":0}',6,'0:0:0:0:0:0:0:1','2018-04-26 20:25:55'),
(3,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":42,\"parentId\":41,\"parentName\":\"人员管理\",\"name\":\"人员管理\",\"url\":\"user\",\"type\":1,\"orderNum\":0}',9,'0:0:0:0:0:0:0:1','2018-04-26 20:26:08'),
(4,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":41,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"报名管理\",\"type\":0,\"orderNum\":0}',6,'0:0:0:0:0:0:0:1','2018-04-26 20:29:36'),
(5,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":42,\"parentId\":41,\"parentName\":\"报名管理\",\"name\":\"报名人员管理\",\"url\":\"modules/person/person.html\",\"type\":1,\"orderNum\":0}',6,'0:0:0:0:0:0:0:1','2018-04-26 20:30:08'),
(6,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":1,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"系统管理\",\"type\":0,\"icon\":\"fa-user-circle-o\",\"orderNum\":0}',7,'0:0:0:0:0:0:0:1','2018-04-26 20:31:30'),
(7,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":1,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"系统管理\",\"type\":0,\"icon\":\"fa fa-cog\",\"orderNum\":0}',14,'0:0:0:0:0:0:0:1','2018-04-26 20:33:05'),
(8,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":41,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"报名管理\",\"type\":0,\"icon\":\"fa fa-address-card\",\"orderNum\":0}',7,'0:0:0:0:0:0:0:1','2018-04-26 20:33:55'),
(9,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":42,\"parentId\":41,\"parentName\":\"报名管理\",\"name\":\"报名人员管理\",\"url\":\"modules/person/person.html\",\"type\":1,\"icon\":\"fa fa-handshake-o\",\"orderNum\":0}',6,'0:0:0:0:0:0:0:1','2018-04-26 20:35:05'),
(10,'admin','删除菜单','io.renren.modules.sys.controller.SysMenuController.delete()','43',2,'0:0:0:0:0:0:0:1','2018-04-26 20:48:11'),
(11,'admin','删除菜单','io.renren.modules.sys.controller.SysMenuController.delete()','43',2,'0:0:0:0:0:0:0:1','2018-04-26 20:48:33'),
(12,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":43,\"parentId\":41,\"parentName\":\"报名管理\",\"name\":\"人员管理\",\"url\":\"modules/sys/person.html\",\"type\":1,\"icon\":\"fa fa-file-code-o\",\"orderNum\":1}',13,'0:0:0:0:0:0:0:1','2018-04-26 20:49:18'),
(13,'admin','删除菜单','io.renren.modules.sys.controller.SysMenuController.delete()','42',26,'0:0:0:0:0:0:0:1','2018-04-26 20:49:27'),
(14,'admin','恢复定时任务','io.renren.modules.job.controller.ScheduleJobController.resume()','[2]',21,'0:0:0:0:0:0:0:1','2018-05-06 19:13:57'),
(15,'admin','立即执行任务','io.renren.modules.job.controller.ScheduleJobController.run()','[2]',33,'0:0:0:0:0:0:0:1','2018-05-06 19:14:08'),
(16,'admin','保存用户','io.renren.modules.sys.controller.SysUserController.save()','{\"userId\":2,\"username\":\"zhoujy\",\"password\":\"4ed1a4c1b20b8e143f29b4a8896a4246f0e4d9daf8d3e32a04d802de57741ff9\",\"salt\":\"DVNV1SDQEBCFMf4V4pmc\",\"email\":\"zhoujunyi-110@163.com\",\"mobile\":\"18698677940\",\"status\":1,\"roleIdList\":[],\"createTime\":\"May 9, 2018 10:35:47 AM\",\"deptId\":4,\"deptName\":\"技术部\"}',193,'0:0:0:0:0:0:0:1','2018-05-09 10:35:48'),
(17,'admin','保存角色','io.renren.modules.sys.controller.SysRoleController.save()','{\"roleId\":1,\"roleName\":\"开发者\",\"remark\":\"测试\",\"deptId\":4,\"deptName\":\"技术部\",\"menuIdList\":[1,36,37,38,39,40,41,43,44,45,46,47],\"deptIdList\":[4],\"createTime\":\"May 9, 2018 10:36:43 AM\"}',147,'0:0:0:0:0:0:0:1','2018-05-09 10:36:44'),
(18,'admin','修改用户','io.renren.modules.sys.controller.SysUserController.update()','{\"userId\":2,\"username\":\"zhoujy\",\"salt\":\"DVNV1SDQEBCFMf4V4pmc\",\"email\":\"zhoujunyi-110@163.com\",\"mobile\":\"18698677940\",\"status\":1,\"roleIdList\":[1],\"createTime\":\"May 9, 2018 10:35:48 AM\",\"deptId\":4,\"deptName\":\"技术部\"}',48,'0:0:0:0:0:0:0:1','2018-05-09 10:38:02'),
(19,'admin','保存用户','io.renren.modules.sys.controller.SysUserController.save()','{\"userId\":3,\"username\":\"test\",\"password\":\"d867061a2293d7a4cda6f272cbe168954f8de672d857214b4c9694fdf2ed0c2f\",\"salt\":\"d35Qqb0HrSuMTnnzjeK4\",\"email\":\"test@163.com\",\"mobile\":\"15942868366\",\"status\":1,\"roleIdList\":[],\"createTime\":\"May 9, 2018 10:58:36 AM\",\"deptId\":5,\"deptName\":\"销售部\"}',29,'0:0:0:0:0:0:0:1','2018-05-09 10:58:37'),
(20,'admin','保存用户','io.renren.modules.sys.controller.SysUserController.save()','{\"userId\":4,\"username\":\"liss\",\"password\":\"122d07f4bcf274bfb165c19f074ad5185622869de74a9d692e98891ddd8195bd\",\"salt\":\"1tm7pmByTQThag0cQ3om\",\"email\":\"55@163.com\",\"mobile\":\"s\",\"status\":1,\"roleIdList\":[],\"createTime\":\"May 9, 2018 10:59:08 AM\",\"deptId\":3,\"deptName\":\"上海分公司\"}',51,'0:0:0:0:0:0:0:1','2018-05-09 10:59:08'),
(21,'admin','删除用户','io.renren.modules.sys.controller.SysUserController.delete()','[4]',84,'0:0:0:0:0:0:0:1','2018-05-09 11:01:59'),
(22,'admin','修改用户','io.renren.modules.sys.controller.SysUserController.update()','{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createTime\":\"Nov 11, 2016 11:11:11 AM\",\"deptId\":1,\"deptName\":\"人人开源集团\"}',128,'0:0:0:0:0:0:0:1','2018-05-13 15:00:35'),
(23,'admin','修改用户','io.renren.modules.sys.controller.SysUserController.update()','{\"userId\":2,\"username\":\"zhoujy\",\"salt\":\"DVNV1SDQEBCFMf4V4pmc\",\"email\":\"zhoujunyi-110@163.com\",\"mobile\":\"18698677940\",\"status\":1,\"roleIdList\":[1],\"createTime\":\"May 9, 2018 10:35:48 AM\",\"deptId\":4,\"deptName\":\"技术部\"}',19,'0:0:0:0:0:0:0:1','2018-05-13 15:00:43'),
(24,'admin','修改用户','io.renren.modules.sys.controller.SysUserController.update()','{\"userId\":2,\"username\":\"zhoujy\",\"password\":\"c81d449a8f3e4fd635a014ff5b5070b68bd239876ed50010295e256528443d9b\",\"salt\":\"DVNV1SDQEBCFMf4V4pmc\",\"email\":\"zhoujunyi-110@163.com\",\"mobile\":\"18698677940\",\"status\":1,\"roleIdList\":[1],\"createTime\":\"May 9, 2018 10:35:48 AM\",\"deptId\":4,\"deptName\":\"技术部\"}',14,'0:0:0:0:0:0:0:1','2018-05-13 15:02:02'),
(25,'admin','修改用户','io.renren.modules.sys.controller.SysUserController.update()','{\"userId\":2,\"username\":\"zhoujy\",\"salt\":\"DVNV1SDQEBCFMf4V4pmc\",\"email\":\"zhoujunyi-110@163.com\",\"mobile\":\"18698677940\",\"status\":1,\"roleIdList\":[1],\"createTime\":\"May 9, 2018 10:35:48 AM\",\"deptId\":4,\"deptName\":\"技术部\"}',33644,'0:0:0:0:0:0:0:1','2018-05-13 15:15:08'),
(26,'admin','修改用户','io.renren.modules.sys.controller.SysUserController.update()','{\"userId\":2,\"username\":\"zhoujy\",\"salt\":\"DVNV1SDQEBCFMf4V4pmc\",\"email\":\"zhoujunyi-110@163.com\",\"mobile\":\"18698677940\",\"status\":1,\"roleIdList\":[1],\"createTime\":\"May 9, 2018 10:35:48 AM\",\"deptId\":4,\"deptName\":\"技术部\"}',36547,'0:0:0:0:0:0:0:1','2018-05-13 15:16:32'),
(27,'admin','保存用户','io.renren.modules.sys.controller.SysUserController.save()','{\"userId\":5,\"username\":\"root\",\"password\":\"eb518fb922684c1b8b88c0a26dc0cc20af96dd70b817e1cd401f3cbdabb8a1a2\",\"salt\":\"iC9z47JVG6841nhiHbqH\",\"email\":\"zhoujy3016@hotmail.com\",\"mobile\":\"15942869369\",\"status\":1,\"roleIdList\":[],\"createTime\":\"May 13, 2018 3:20:05 PM\",\"deptId\":5,\"deptName\":\"销售部\"}',12,'0:0:0:0:0:0:0:1','2018-05-13 15:20:05'),
(28,'admin','保存角色','io.renren.modules.sys.controller.SysRoleController.save()','{\"roleId\":2,\"roleName\":\"销售部管理员\",\"deptId\":5,\"deptName\":\"销售部\",\"menuIdList\":[1,5,6,7,8,9,10,11,12,13,14,36,37,38,39,40,41,43,44,45,46,47],\"deptIdList\":[5],\"createTime\":\"May 13, 2018 3:20:50 PM\"}',58,'0:0:0:0:0:0:0:1','2018-05-13 15:20:51'),
(29,'admin','修改用户','io.renren.modules.sys.controller.SysUserController.update()','{\"userId\":5,\"username\":\"root\",\"salt\":\"iC9z47JVG6841nhiHbqH\",\"email\":\"zhoujy3016@hotmail.com\",\"mobile\":\"15942869369\",\"status\":1,\"roleIdList\":[2],\"createTime\":\"May 13, 2018 3:20:05 PM\",\"deptId\":5,\"deptName\":\"销售部\"}',4428,'0:0:0:0:0:0:0:1','2018-05-13 15:21:01'),
(30,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":48,\"parentId\":2,\"parentName\":\"管理员管理\",\"name\":\"密码重置\",\"type\":2,\"orderNum\":0}',13,'0:0:0:0:0:0:0:1','2018-05-13 15:24:50'),
(31,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','{\"menuId\":48,\"parentId\":2,\"parentName\":\"管理员管理\",\"name\":\"密码重置\",\"perms\":\"sys:user:psdreset\",\"type\":2,\"orderNum\":0}',12,'0:0:0:0:0:0:0:1','2018-05-13 15:25:51'),
(32,'admin','重置密码','io.renren.modules.sys.controller.SysUserController.passwordReset()','[1,5,2]',2842,'0:0:0:0:0:0:0:1','2018-05-13 15:46:53'),
(33,'admin','重置密码','io.renren.modules.sys.controller.SysUserController.passwordReset()','[1,2,5]',2,'0:0:0:0:0:0:0:1','2018-05-13 16:07:49'),
(34,'admin','重置密码','io.renren.modules.sys.controller.SysUserController.passwordReset()','[2,5]',20,'0:0:0:0:0:0:0:1','2018-05-13 16:07:53'),
(35,'admin','重置密码','io.renren.modules.sys.controller.SysUserController.passwordReset()','[2,5]',13,'0:0:0:0:0:0:0:1','2018-05-13 16:08:14'),
(36,'admin','重置密码','io.renren.modules.sys.controller.SysUserController.passwordReset()','[2,5]',9,'0:0:0:0:0:0:0:1','2018-05-13 16:11:18'),
(37,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','{\"menuId\":49,\"parentId\":41,\"parentName\":\"报名管理\",\"name\":\"信息发布\",\"url\":\"modules/sys/tblinfo.html\",\"type\":1,\"icon\":\"fa fa-file-code-o\",\"orderNum\":0}',21,'0:0:0:0:0:0:0:1','2018-05-27 14:13:41');

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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

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
(41,0,'报名管理',NULL,NULL,0,'fa fa-address-card',0),
(43,41,'人员管理','modules/sys/tblperson.html',NULL,1,'fa fa-file-code-o',1),
(44,43,'查看',NULL,'sys:tblperson:list,sys:tblperson:info',2,NULL,6),
(45,43,'新增',NULL,'sys:tblperson:save',2,NULL,6),
(46,43,'修改',NULL,'sys:tblperson:update',2,NULL,6),
(47,43,'删除',NULL,'sys:tblperson:delete',2,NULL,6),
(48,2,'密码重置',NULL,'sys:user:psdreset',2,NULL,0),
(50,41,'信息管理','modules/sys/tblinfo.html',NULL,1,'fa fa-file-code-o',6),
(51,50,'查看',NULL,'sys:tblinfo:list,sys:tblinfo:info',2,NULL,6),
(52,50,'新增',NULL,'sys:tblinfo:save',2,NULL,6),
(53,50,'修改',NULL,'sys:tblinfo:update',2,NULL,6),
(54,50,'删除',NULL,'sys:tblinfo:delete',2,NULL,6);

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
(1,'开发者','测试',4,'2018-05-09 10:36:43'),
(2,'销售部管理员',NULL,5,'2018-05-13 15:20:51');

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

/*Data for the table `sys_role_dept` */

insert  into `sys_role_dept`(`id`,`role_id`,`dept_id`) values 
(1,1,4),
(2,2,5);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values 
(1,1,1),
(2,1,36),
(3,1,37),
(4,1,38),
(5,1,39),
(6,1,40),
(7,1,41),
(8,1,43),
(9,1,44),
(10,1,45),
(11,1,46),
(12,1,47),
(13,2,1),
(14,2,5),
(15,2,6),
(16,2,7),
(17,2,8),
(18,2,9),
(19,2,10),
(20,2,11),
(21,2,12),
(22,2,13),
(23,2,14),
(24,2,36),
(25,2,37),
(26,2,38),
(27,2,39),
(28,2,40),
(29,2,41),
(30,2,43),
(31,2,44),
(32,2,45),
(33,2,46),
(34,2,47);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`username`,`password`,`salt`,`email`,`mobile`,`status`,`dept_id`,`create_time`) values 
(1,'admin','e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b','YzcmCZNvbXocrsz9dm8e','root@renren.io','13612345678',1,1,'2016-11-11 11:11:11'),
(2,'zhoujy','633beefd43ed0c44d15f672b6d49003b962064e4c3f98c3c6618ba72ff0987e2','zHZUIwB7MDU2dO4PGzEy','zhoujunyi-110@163.com','18698677940',1,4,'2018-05-09 10:35:48'),
(5,'root','99f835258b09d8ea1bde259d7d53e3d08b585e6569105190b68c68132bfa57a9','WkuHaqPLpB8l2iaBhhlF','zhoujy3016@hotmail.com','15942869369',1,5,'2018-05-13 15:20:05');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values 
(5,2,1),
(6,5,2);

/*Table structure for table `tbl_info` */

DROP TABLE IF EXISTS `tbl_info`;

CREATE TABLE `tbl_info` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT,
  `info_title` varchar(50) NOT NULL,
  `info_type` int(10) NOT NULL,
  `info_content` text,
  PRIMARY KEY (`info_id`),
  UNIQUE KEY `info_title` (`info_title`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_info` */

insert  into `tbl_info`(`info_id`,`info_title`,`info_type`,`info_content`) values 
(1,'交友 英雄，在习近平心中重逾千钧',4,'（原标题：英雄，在习近平心中重逾千钧）\n\n新华网记者 王子晖\n\n【学习进行时】&amp;ldquo;天地英雄气，千秋尚凛然。&amp;rdquo;无数英雄先烈是我们民族的脊梁。习近平总书记有着深沉的英雄情怀，他号召全社会都要崇尚英雄，捍卫英雄，学习英雄，关爱英雄。新华社《学习进行时》原创品牌栏目&amp;ldquo;讲习所&amp;rdquo;今天推出文章，为您梳理解读。\n\n一个有希望的民族不能没有英雄，一个有前途的国家不能没有先锋。\n\n在不同时期，不同场合，习近平总书记反复强调，要铭记一切为中华民族和中国人民作出贡献的英雄们，崇尚英雄，捍卫英雄，学习英雄，关爱英雄，目的就是号召全体中华儿女发扬英雄的精神，继承英雄的事业，勠力同心、不懈奋斗！\n\n<strong>&amp;ldquo;要心怀崇敬&amp;rdquo;&amp;mdash;&amp;mdash;英雄之名庄严而神圣</strong>\n\n2014年，我国设立了烈士纪念日、中国人民抗日战争胜利纪念日和南京大屠杀死难者国家公祭日。几年来，习近平亲自出席相关纪念活动，向人民英雄敬献花篮，深切缅怀英烈丰功伟绩。\n\n井冈山革命烈士陵园、红军长征会师纪念碑、华东革命烈士陵园、金寨县革命烈士纪念塔&amp;hellip;&amp;hellip;每到革命老区，习近平一下飞机就前去瞻仰革命遗址，向革命英烈表达无限追思。\n\n习近平说过，&amp;ldquo;不要让英雄既流血又流泪&amp;rdquo;。这几年，建立健全党和国家功勋荣誉表彰制度的意见、英雄烈士保护法相继出台、施行。\n\n英雄，在习近平心中重逾千钧。\n\n正是因为他们的浴血奋斗和英勇牺牲，我们的国家才有了今天的独立自主，我们的民族才有了今天的发展繁荣，我们的人民才有了今天的幸福生活。\n\n&amp;ldquo;诚既勇兮又以武，终刚强兮不可凌。身既死兮神以灵，魂魄毅兮为鬼雄。&amp;rdquo;习近平引用屈原《九歌》中的慷慨激扬诗句，大力赞颂英雄，向他们表达由衷的敬意。\n\n时代变迁、沧海横流，他们的功绩与世长存，他们的名字也不容忘却。要铭记历史，尊崇英雄，习近平殷殷嘱托，语重心长。\n\n他对少年儿童说，要学习英雄人物、先进人物、美好事物，在学习中养成好的思想品德追求。要从自己做起、从身边做起、从小事做起，一点一滴积累，养成好思想、好品德。\n\n他对革命军人说，历史不能忘记，军人的英勇牺牲行为永远值得尊重和纪念。有些人刻意抹黑我们的英雄人物，歪曲我们的光辉历史，要引起我们高度警觉。\n\n他对文艺工作者说，对中华民族的英雄，要心怀崇敬，浓墨重彩记录英雄、塑造英雄，让英雄在文艺作品中得到传扬，绝不做亵渎祖先、亵渎经典、亵渎英雄的事情。\n\n&amp;hellip;&amp;hellip;\n\n在习近平心中，英雄，庄严而神圣。\n\n<strong>&amp;ldquo;精忠报国&amp;rdquo;&amp;mdash;&amp;mdash;将英雄之魂注入血脉</strong>\n\n2015年9月2日，习近平在颁发&amp;ldquo;中国人民抗日战争胜利70周年&amp;rdquo;纪念章仪式上发表了重要讲话，指出在波澜壮阔的中国人民抗日战争中，千千万万的抗战英雄抛头颅、洒热血，为战争胜利作出了重大贡献，为铸就伟大的抗战精神作出了重大贡献。\n\n习近平将伟大的抗战精神归纳为&amp;ldquo;天下兴亡、匹夫有责的爱国情怀&amp;rdquo;&amp;ldquo;视死如归、宁死不屈的民族气节&amp;rdquo;&amp;ldquo;不畏强暴、血战到底的英雄气概&amp;rdquo;，以及&amp;ldquo;百折不挠、坚忍不拔的必胜信念&amp;rdquo;。\n\n概括为4个字，就是&amp;ldquo;精忠报国&amp;rdquo;。\n\n纵观抗日战争14年波澜壮阔的历程，乃至从鸦片战争开始的一部中国近代史，就是海内外中华儿女共同谱写的惊天地、泣鬼神的爱国主义篇章。\n\n从1840年起，无数仁人志士不懈抗争、屡败屡战，唯一的目的就是救国救民。1921年，中国共产党诞生，更是义无反顾地举起了为中国人民谋幸福，中华民族谋复兴的伟大旗帜。\n\n千千万万英雄先烈前赴后继，为的是中华民族独立和解放，为的是中华民族摆脱外来殖民统治和侵略，为的是中华民族掌握自己命运、开创国家发展新路。\n\n&amp;ldquo;精忠报国&amp;rdquo;就是英雄之魂。学习英雄，就必须把&amp;ldquo;精忠报国&amp;rdquo;化为行动。\n\n几十年前，习近平还在五六岁时，母亲给他买了《岳飞传》《岳母刺字》等小人书。习近平回忆说：&amp;ldquo;买回来之后，她就给我讲精忠报国、岳母刺字的故事。我说，把字刺上去，多疼啊！我母亲说，是疼，但心里铭记住了。&amp;lsquo;精忠报国&amp;rsquo;四个字，我从那个时候一直记到现在，它也是我一生追求的目标。&amp;rdquo;\n\n的确，让英雄精神融入血脉，才能不断激发前行力量。\n\n&amp;ldquo;我们要发扬光荣传统、传承红色基因，不忘初心、继续前进，努力在坚持和发展中国特色社会主义伟大进程中创造无愧于时代、无愧于人民、无愧于先辈的业绩。这是我们对老一辈革命家最好的纪念。&amp;rdquo;习近平发出铿锵有力的号召。\n\n只有肩负起历史重任，不断取得中华民族伟大复兴的新成就，才能告慰所有为国家和民族而牺牲的先烈和英灵。\n\n<strong>&amp;ldquo;毋改英雄意气&amp;rdquo;&amp;mdash;&amp;mdash;英雄精神永不过时</strong>\n\n&amp;ldquo;思君夜夜，肝胆长如洗&amp;hellip;&amp;hellip;&amp;rdquo;习近平的一首《念奴娇&amp;middot;追思焦裕禄》，万口传诵。对于&amp;ldquo;县委书记的榜样&amp;rdquo;焦裕禄，数十年来，习近平一直满怀敬意。他说过，&amp;ldquo;我们这一代人，是深受焦裕禄同志的事迹教育成长起来的&amp;rdquo;。\n\n2014年3月，习近平在兰考强调，焦裕禄精神同井冈山精神、延安精神、雷锋精神等革命传统和伟大精神一样，过去是、现在是、将来仍然是我们党的宝贵精神财富，我们要永远向他学习。\n\n&amp;ldquo;暮雪朝霜，毋改英雄意气！&amp;rdquo;这是习近平对千千万万共产党员的期望。\n\n2017年12月，习近平来到第71集团军某旅视察，来到英雄王杰生前所在的连队。\n\n在王杰牺牲时遗留的血衣和钢笔残片前，习近平感慨地说：&amp;ldquo;我小时候就知道王杰的故事，王杰是我心目中的英雄！&amp;rdquo;\n\n习近平对官兵们说，王杰&amp;ldquo;在荣誉上不伸手，在待遇上不伸手，在物质上不伸手&amp;rdquo;，这&amp;ldquo;三不伸手&amp;rdquo;是一面镜子，共产党员都要好好照照这面镜子。\n\n&amp;hellip;&amp;hellip;\n\n在习近平心中，英雄精神永不过时，永远都是我们不断开拓前进的勇气和力量所在。\n\n2016年10月21日，在纪念红军长征胜利80周年大会上，习近平动情地说：&amp;ldquo;在红一方面军二万五千里的征途上，平均每300米就有一名红军牺牲。长征这条红飘带，是无数红军的鲜血染成的。&amp;rdquo;\n\n长征路上，英雄的红军正是靠着铁的精神跨越了&amp;ldquo;雪山&amp;rdquo;&amp;ldquo;草地&amp;rdquo;，征服了&amp;ldquo;娄山关&amp;rdquo;&amp;ldquo;腊子口&amp;rdquo;。\n\n今天，我们这一代人正走在自己的长征路上&amp;mdash;&amp;mdash;去实现中华民族伟大复兴的中国梦，不论我们的事业发展到哪一步，不论我们取得了多大成就，这笔宝贵的精神财富都不能丢掉，英雄精神永远都是照亮我们前路的灯塔。\n'),
(2,'特朗普:仍希望美朝领导人会晤能在6月12日举行',3,'（原标题：特朗普：6月12日举行朝美首脑会晤计划不变）\n\n特朗普26日当晚在美国白宫对记者表示，目前关于美朝领导人会晤的相关准备进行得&amp;ldquo;非常顺利&amp;rdquo;。美方仍希望会晤能于6月12日在新加坡举行，&amp;ldquo;这一点没有改变&amp;rdquo;。 当天早些时候，白宫发布声明说，为美朝领导人会晤做准备的白宫先遣团队将按原计划前往新加坡。\n\n【环球网综合报道】法新社27日报道，美国总统特朗普表示6月12日举行朝美首脑会晤计划不变。\n');

/*Table structure for table `tbl_person` */

DROP TABLE IF EXISTS `tbl_person`;

CREATE TABLE `tbl_person` (
  `person_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `person_name` varchar(20) DEFAULT NULL,
  `nationality` int(11) DEFAULT NULL,
  `area` int(11) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `person_name` (`person_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_person` */

insert  into `tbl_person`(`person_id`,`person_name`,`nationality`,`area`,`remark`,`telephone`) values 
(0000000001,'周骏译',1,3,'预备党员','18698677940'),
(0000000002,'李峰',1,1,'东北凯亚CEO',NULL),
(0000000003,'张兴哲',1,2,'大连党委书记',NULL),
(0000000004,'林斌',1,1,'东北凯亚CEO',NULL),
(0000000005,'包晗',2,1,'东北进口食品总代理',NULL),
(0000000006,'曹亮',1,1,'电网总经理',NULL),
(0000000009,'周仁来',1,1,'东北环保总经理',NULL),
(0000000010,'于占福',1,2,'西岗扛把子',NULL),
(0000000011,'王金宇',1,2,'教育技术中心CTO','18942563337');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
