# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.19)
# Database: shop
# Generation Time: 2018-07-12 01:30:36 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table admin
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` int(10) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) NOT NULL COMMENT '管理员名称',
  `admin_password` varchar(255) NOT NULL COMMENT '管理员密码',
  `admin_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '正常',
  `create_time` int(10) NOT NULL,
  `update_time` int(10) DEFAULT NULL,
  `admin_role_id` int(10) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;

INSERT INTO `admin` (`admin_id`, `admin_name`, `admin_password`, `admin_status`, `create_time`, `update_time`, `admin_role_id`)
VALUES
	(6,'tuzi','e10adc3949ba59abbe56e057f20f883e',1,1531107023,1531107034,-1),
	(8,'money1','e10adc3949ba59abbe56e057f20f883e',1,1531325800,1531325814,11),
	(9,'dianzhang1','e10adc3949ba59abbe56e057f20f883e',1,1531325969,1531325969,12);

/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table admin_function
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin_function`;

CREATE TABLE `admin_function` (
  `admin_function_id` int(10) NOT NULL AUTO_INCREMENT,
  `admin_function_url` varchar(255) DEFAULT NULL COMMENT '功能url',
  `admin_function_name` varchar(255) NOT NULL COMMENT '功能名称',
  `admin_function_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '管理员功能状态1正常0禁用',
  `create_time` int(10) NOT NULL,
  `update_time` int(10) NOT NULL,
  `pid` int(10) NOT NULL DEFAULT '0' COMMENT '无限极分类0为顶级菜单',
  PRIMARY KEY (`admin_function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `admin_function` WRITE;
/*!40000 ALTER TABLE `admin_function` DISABLE KEYS */;

INSERT INTO `admin_function` (`admin_function_id`, `admin_function_url`, `admin_function_name`, `admin_function_status`, `create_time`, `update_time`, `pid`)
VALUES
	(1,NULL,'用户管理',1,800,1,0),
	(2,'user_list','用户列表',1,1,1,1),
	(3,NULL,'商品管理',1,1,1,0),
	(4,'admin_goods_category_list','商品类别',1,1,1,3),
	(5,'admin_goods_list','商品列表',1,1,1,3),
	(6,NULL,'订单管理',1,1,1,0),
	(7,'admin_orders_list','订单列表',1,1,1,6),
	(8,NULL,'邮件管理',1,1,1,0),
	(9,'admin_set_email','邮件设置',1,1,1,8),
	(10,'admin_email_template_list','邮件模板',1,1,1,8),
	(11,'admin_email_list','已发邮件',1,1,1,8),
	(12,NULL,'统计管理',1,1,1,0),
	(13,'admin_order_count','订单统计',1,1,1,12),
	(14,NULL,'权限管理',1,1,1,0),
	(15,'admin_function_list','功能列表',1,1,1,14),
	(16,'admin_rote_list','角色列表',1,1,1,14),
	(17,'admin_admin_list','管理员列表',1,1,1,14);

/*!40000 ALTER TABLE `admin_function` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table admin_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin_role`;

CREATE TABLE `admin_role` (
  `admin_role_id` int(10) NOT NULL AUTO_INCREMENT,
  `admin_role_name` varchar(255) NOT NULL COMMENT '管理员角色名称',
  `admin_role_jurisdiction` varchar(255) NOT NULL COMMENT '管理员角色权限列表例如：1，2，3，4',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  `update_time` int(10) NOT NULL COMMENT '更新时间',
  `admin_role_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '管理员角色状态',
  PRIMARY KEY (`admin_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `admin_role` WRITE;
/*!40000 ALTER TABLE `admin_role` DISABLE KEYS */;

INSERT INTO `admin_role` (`admin_role_id`, `admin_role_name`, `admin_role_jurisdiction`, `create_time`, `update_time`, `admin_role_status`)
VALUES
	(11,'财务','7,13',1531325783,1531325783,1),
	(12,'店长','2,4,5,7,11,13',1531325955,1531325955,1);

/*!40000 ALTER TABLE `admin_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table email_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `email_info`;

CREATE TABLE `email_info` (
  `email_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `from_email` varchar(50) NOT NULL COMMENT '发送邮箱',
  `from_email_user_name` varchar(50) NOT NULL COMMENT '发送邮箱的用户名',
  `from_email_password` varchar(100) NOT NULL COMMENT '发送邮箱的密码',
  `host` varchar(50) NOT NULL COMMENT '发送邮件的服务器',
  `protocol` varchar(50) NOT NULL COMMENT '协议',
  `timeout` int(10) NOT NULL DEFAULT '1000' COMMENT '超时时间',
  `auth` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否认证,1认证，0，不认证',
  `update_time` int(10) NOT NULL COMMENT '更新时间',
  `port` int(10) NOT NULL COMMENT '端口号',
  PRIMARY KEY (`email_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `email_info` WRITE;
/*!40000 ALTER TABLE `email_info` DISABLE KEYS */;

INSERT INTO `email_info` (`email_info_id`, `from_email`, `from_email_user_name`, `from_email_password`, `host`, `protocol`, `timeout`, `auth`, `update_time`, `port`)
VALUES
	(1,'2788828128@qq.com','2788828128','teqawhszflszdded','smtp.qq.com','smtp',5000,1,1528714003,465);

/*!40000 ALTER TABLE `email_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table email_template
# ------------------------------------------------------------

DROP TABLE IF EXISTS `email_template`;

CREATE TABLE `email_template` (
  `email_template_id` int(10) NOT NULL AUTO_INCREMENT,
  `email_template_title` varchar(255) NOT NULL COMMENT '邮件标题',
  `email_template_content` varchar(500) NOT NULL COMMENT '邮件内容',
  `email_template_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1注册，2登录，3下单，4退款',
  `email_template_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '模板状态1正常0关闭',
  `create_time` int(10) NOT NULL,
  `update_time` int(10) DEFAULT NULL,
  `email_template_pic` varchar(255) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`email_template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `email_template` WRITE;
/*!40000 ALTER TABLE `email_template` DISABLE KEYS */;

INSERT INTO `email_template` (`email_template_id`, `email_template_title`, `email_template_content`, `email_template_type`, `email_template_status`, `create_time`, `update_time`, `email_template_pic`)
VALUES
	(3,'恭喜您注册成功!','点击跳转到登录页面(http://localhost:8888/jsp-shop/route?get_type=user_login)',1,1,1531325172,1531325172,NULL),
	(4,'欢迎您来到召唤师峡谷！','敌军还有30秒到达战场。',2,1,1531325347,1531325347,NULL),
	(5,'您有一笔订单支出！','支付成功。详情请点击（http://www.baidu.com）',3,1,1531325490,1531325490,NULL);

/*!40000 ALTER TABLE `email_template` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table emails
# ------------------------------------------------------------

DROP TABLE IF EXISTS `emails`;

CREATE TABLE `emails` (
  `email_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '邮件id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `email_title` varchar(255) NOT NULL COMMENT '邮件标题',
  `email_content` varchar(255) NOT NULL COMMENT '邮件内容',
  `create_time` int(10) NOT NULL COMMENT '邮件发送时间',
  `update_time` int(10) NOT NULL COMMENT '邮件更新时间',
  `email_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '邮件发送状态1发送成功，0正在发送中，2发送失败',
  `email_type` tinyint(4) NOT NULL COMMENT '邮件类型',
  `user_email` varchar(50) NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `emails` WRITE;
/*!40000 ALTER TABLE `emails` DISABLE KEYS */;

INSERT INTO `emails` (`email_id`, `user_id`, `email_title`, `email_content`, `create_time`, `update_time`, `email_status`, `email_type`, `user_email`)
VALUES
	(5,8,'恭喜您注册成功!','点击跳转到登录页面(http://localhost:8888/jsp-shop/route?get_type=user_login)',1531325277,1531325277,1,1,'891392850@qq.com'),
	(6,7,'欢迎您来到召唤师峡谷！','敌军还有30秒到达战场。',1531325372,1531325372,1,2,'891392850@qq.com'),
	(10,7,'欢迎您来到召唤师峡谷！','敌军还有30秒到达战场。',1531325902,1531325902,1,2,'891392850@qq.com'),
	(11,7,'您有一笔订单支出！','支付成功。详情请点击（http://www.baidu.com）',1531325909,1531325909,1,3,'891392850@qq.com'),
	(12,7,'欢迎您来到召唤师峡谷！','敌军还有30秒到达战场。',1531330077,1531330077,1,2,'891392850@qq.com'),
	(13,7,'欢迎您来到召唤师峡谷！','敌军还有30秒到达战场。',1531354669,1531354669,1,2,'891392850@qq.com'),
	(14,7,'您有一笔订单支出！','支付成功。详情请点击（http://www.baidu.com）',1531354677,1531354677,1,3,'891392850@qq.com');

/*!40000 ALTER TABLE `emails` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table goods
# ------------------------------------------------------------

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `goods_pic` varchar(255) NOT NULL COMMENT '商品图片地址',
  `goods_bid` int(10) NOT NULL DEFAULT '0' COMMENT '商品进货价',
  `goods_describe` varchar(255) NOT NULL COMMENT '商品描述',
  `goods_unit` int(10) NOT NULL DEFAULT '0' COMMENT '商品单位id比如个，件',
  `goods_num` int(10) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `goods_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态1.正常0已下架',
  `goods_category_id` int(10) NOT NULL DEFAULT '0' COMMENT '商品分类id',
  `goods_price` int(10) NOT NULL DEFAULT '0' COMMENT '商品出售价格',
  `create_time` int(10) NOT NULL,
  `update_time` int(10) NOT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;

INSERT INTO `goods` (`goods_id`, `goods_name`, `goods_pic`, `goods_bid`, `goods_describe`, `goods_unit`, `goods_num`, `goods_status`, `goods_category_id`, `goods_price`, `create_time`, `update_time`)
VALUES
	(21,'服装1','http://localhost:8080/jsp-shop/static/img/goods/1531323865.png',10000,'服装，指的是衣服鞋包玩具饰品等的总称，多指衣服。服装在人类社会发展的早期就已出现，古代人把身边能找到的各种材料做成粗陋的“衣服”用以护身。人类最初的衣服是用兽皮制成的，包裹身体的最早“织物”用麻类纤维和草制等成。',0,199,1,6,20000,1531323891,1531323891),
	(22,'服装2','http://localhost:8080/jsp-shop/static/img/goods/1531326075.png',10000,'服装，指的是衣服鞋包玩具饰品等的总称，多指衣服。服装在人类社会发展的早期就已出现，古代人把身边能找到的各种材料做成粗陋的“衣服”用以护身。人类最初的衣服是用兽皮制成的，包裹身体的最早“织物”用麻类纤维和草制等成。',0,300,1,6,20000,1531326099,1531326099),
	(23,'服装3','http://localhost:8080/jsp-shop/static/img/goods/1531326133.png',10000,'服装，指的是衣服鞋包玩具饰品等的总称，多指衣服。服装在人类社会发展的早期就已出现，古代人把身边能找到的各种材料做成粗陋的“衣服”用以护身。人类最初的衣服是用兽皮制成的，包裹身体的最早“织物”用麻类纤维和草制等成。',0,300,1,6,20000,1531326163,1531326163),
	(24,'服装4','http://localhost:8080/jsp-shop/static/img/goods/1531326204.png',10000,'服装，指的是衣服鞋包玩具饰品等的总称，多指衣服。服装在人类社会发展的早期就已出现，古代人把身边能找到的各种材料做成粗陋的“衣服”用以护身。人类最初的衣服是用兽皮制成的，包裹身体的最早“织物”用麻类纤维和草制等成。',0,200,1,6,10000,1531326208,1531326208),
	(25,'服装5','http://localhost:8080/jsp-shop/static/img/goods/1531326235.png',10000,'服装，指的是衣服鞋包玩具饰品等的总称，多指衣服。服装在人类社会发展的早期就已出现，古代人把身边能找到的各种材料做成粗陋的“衣服”用以护身。人类最初的衣服是用兽皮制成的，包裹身体的最早“织物”用麻类纤维和草制等成。',0,200,1,6,200100,1531326238,1531326238),
	(26,'服装7','http://localhost:8080/jsp-shop/static/img/goods/1531326262.png',10000,'服装，指的是衣服鞋包玩具饰品等的总称，多指衣服。服装在人类社会发展的早期就已出现，古代人把身边能找到的各种材料做成粗陋的“衣服”用以护身。人类最初的衣服是用兽皮制成的，包裹身体的最早“织物”用麻类纤维和草制等成。',0,199,1,6,20000,1531326265,1531326265),
	(27,'服装8','http://localhost:8080/jsp-shop/static/img/goods/1531326296.png',10000,'服装，指的是衣服鞋包玩具饰品等的总称，多指衣服。服装在人类社会发展的早期就已出现，古代人把身边能找到的各种材料做成粗陋的“衣服”用以护身。人类最初的衣服是用兽皮制成的，包裹身体的最早“织物”用麻类纤维和草制等成。',0,200,1,6,100000,1531326300,1531326300),
	(28,'服装9','http://localhost:8080/jsp-shop/static/img/goods/1531326320.png',10000,'服装，指的是衣服鞋包玩具饰品等的总称，多指衣服。服装在人类社会发展的早期就已出现，古代人把身边能找到的各种材料做成粗陋的“衣服”用以护身。人类最初的衣服是用兽皮制成的，包裹身体的最早“织物”用麻类纤维和草制等成。',0,1000,1,6,100000,1531326324,1531326324),
	(29,'服装10','http://localhost:8080/jsp-shop/static/img/goods/1531326340.png',1000,'服装，指的是衣服鞋包玩具饰品等的总称，多指衣服。服装在人类社会发展的早期就已出现，古代人把身边能找到的各种材料做成粗陋的“衣服”用以护身。人类最初的衣服是用兽皮制成的，包裹身体的最早“织物”用麻类纤维和草制等成。',0,100,1,6,10000,1531326343,1531326343),
	(30,'家居1','http://localhost:8080/jsp-shop/static/img/goods/1531326457.png',1000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,1000,1,7,10000,1531326569,1531326569),
	(31,'家居2','http://localhost:8080/jsp-shop/static/img/goods/1531326606.png',1000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,1000,1,7,10000,1531326612,1531326612),
	(32,'家居3','http://localhost:8080/jsp-shop/static/img/goods/1531326641.png',10000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,100,1,7,100000,1531326648,1531326648),
	(33,'家居4','http://localhost:8080/jsp-shop/static/img/goods/1531326670.png',10000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,100,1,7,100100,1531326673,1531326673),
	(34,'家居5','http://localhost:8080/jsp-shop/static/img/goods/1531326692.png',1000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,100,1,7,10000,1531326695,1531326695),
	(35,'家居6','http://localhost:8080/jsp-shop/static/img/goods/1531326712.png',1000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,100,1,7,1000,1531326715,1531326715),
	(36,'家居7','http://localhost:8080/jsp-shop/static/img/goods/1531326728.png',1000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,100,1,7,1000,1531326733,1531326733),
	(37,'家居8','http://localhost:8080/jsp-shop/static/img/goods/1531326778.png',10000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,100,1,7,100000,1531326782,1531326782),
	(38,'家居9','http://localhost:8080/jsp-shop/static/img/goods/1531326818.png',10000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,100,1,7,210000,1531326821,1531326821),
	(39,'家居10','http://localhost:8080/jsp-shop/static/img/goods/1531326848.png',1000,'家居（拼音jiā jū）指的是家庭装修、家具配置、电器摆放等一系列和居室有关的甚至包括地理位置（家居风水）都属于家居范畴。丽芙家居,支付满49元,12件福利赠品随便选,随时免费领取,立即点击查看!丽芙家居 努力确保每一件产品0瑕疵',0,100,1,7,2000,1531326851,1531326851),
	(40,'美妆1','http://localhost:8080/jsp-shop/static/img/goods/1531327011.png',10000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,100200,1531327081,1531327081),
	(41,'美妆2','http://localhost:8080/jsp-shop/static/img/goods/1531327102.png',20000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,300000,1531327111,1531327111),
	(42,'美妆3','http://localhost:8080/jsp-shop/static/img/goods/1531327128.png',10000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,20000,1531327135,1531327135),
	(43,'美妆4','http://localhost:8080/jsp-shop/static/img/goods/1531327151.png',10000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,10000,1531327155,1531327155),
	(44,'美妆5','http://localhost:8080/jsp-shop/static/img/goods/1531327174.png',10000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,20000,1531327178,1531327178),
	(45,'美妆6','http://localhost:8080/jsp-shop/static/img/goods/1531327200.png',10000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,100100,1531327203,1531327203),
	(46,'美妆7','http://localhost:8080/jsp-shop/static/img/goods/1531327222.png',10000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,100000,1531327227,1531327227),
	(47,'美妆8','http://localhost:8080/jsp-shop/static/img/goods/1531327248.png',10000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,20000,1531327251,1531327251),
	(48,'美妆9','http://localhost:8080/jsp-shop/static/img/goods/1531327270.png',10000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,10300,1531327274,1531327274),
	(49,'美妆10','http://localhost:8080/jsp-shop/static/img/goods/1531327294.png',10000,'同时提供原创的美容视点及专题内容，包含致力于纯净交流的“百合花园”、品牌新品体验的“免费试用”、限时团购的“粒粒团”和品牌折扣的“享美会”等多个核心频道。并先后通过网上交易保障中心和支付宝消费保障计划双重认证，凭借对产品的理解和优质的服务，美合美妆实现了较短时间内的快速发展，跃居山东省内同行业排名第二',0,100,1,8,20000,1531327297,1531327297),
	(50,'手表1','http://localhost:8080/jsp-shop/static/img/goods/1531327381.png',10000,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,100,1,9,20000,1531327432,1531327432),
	(51,'手表2','http://localhost:8080/jsp-shop/static/img/goods/1531327455.png',10000,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,100,1,9,100000,1531327463,1531327463),
	(52,'手表3','http://localhost:8080/jsp-shop/static/img/goods/1531327481.png',10000,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,200,1,9,100000,1531327490,1531327490),
	(53,'手表4','http://localhost:8080/jsp-shop/static/img/goods/1531327508.png',10000,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,100,1,9,20000,1531327513,1531327513),
	(54,'手表5','http://localhost:8080/jsp-shop/static/img/goods/1531327532.png',100,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,3,1,9,200,1531327537,1531327537),
	(55,'手表6','http://localhost:8080/jsp-shop/static/img/goods/1531327556.png',1100,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,13,1,9,1200,1531327563,1531327563),
	(56,'手表7','http://localhost:8080/jsp-shop/static/img/goods/1531327583.png',10000,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,200,1,9,101000,1531327588,1531327588),
	(57,'手表8','http://localhost:8080/jsp-shop/static/img/goods/1531327616.png',1200,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,14,1,9,1300,1531327621,1531327621),
	(58,'手表9','http://localhost:8080/jsp-shop/static/img/goods/1531327648.png',1300,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,12,1,9,1500,1531327655,1531327655),
	(59,'手表10','http://localhost:8080/jsp-shop/static/img/goods/1531327681.png',10000,'手表生产厂家_多种投资店型_标准化运营_无条件退换货_强势品牌宣传和完善的供货渠道,手表生产厂家 低门槛加盟开店,从选址到经营,总部全程扶持,1-2人开店,让您省心省力创业!',0,100,1,9,10200,1531327687,1531327687),
	(60,'数码1','http://localhost:8080/jsp-shop/static/img/goods/1531328284.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328329,1531328329),
	(61,'数码2','http://localhost:8080/jsp-shop/static/img/goods/1531328352.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328358,1531328358),
	(62,'数码3','http://localhost:8080/jsp-shop/static/img/goods/1531328372.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328380,1531328380),
	(63,'数码4','http://localhost:8080/jsp-shop/static/img/goods/1531328394.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328400,1531328400),
	(64,'数码5','http://localhost:8080/jsp-shop/static/img/goods/1531328422.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328427,1531328427),
	(65,'数码6','http://localhost:8080/jsp-shop/static/img/goods/1531328455.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328460,1531328460),
	(66,'数码7','http://localhost:8080/jsp-shop/static/img/goods/1531328476.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328482,1531328482),
	(67,'数码8','http://localhost:8080/jsp-shop/static/img/goods/1531328500.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328505,1531328505),
	(68,'数码9','http://localhost:8080/jsp-shop/static/img/goods/1531328522.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328529,1531328529),
	(69,'数码10','http://localhost:8080/jsp-shop/static/img/goods/1531328559.png',1100,'数码（digital）系统，又称为数字系统，是使用离散（即不连续的）的0或1来进行信息的输入，处理，传输、存贮等处理的系统。相对的非数码（模拟信号）系统使用连续的数值代表信息。',0,33,1,10,2200,1531328565,1531328565),
	(70,'玩具1','http://localhost:8080/jsp-shop/static/img/goods/1531328631.png',1100,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,33,1,11,2200,1531328658,1531328658),
	(71,'玩具2','http://localhost:8080/jsp-shop/static/img/goods/1531328672.png',1100,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,33,1,11,2200,1531328677,1531328677),
	(72,'玩具3','http://localhost:8080/jsp-shop/static/img/goods/1531328696.png',112200,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,333,1,11,222200,1531328698,1531328698),
	(73,'玩具4','http://localhost:8080/jsp-shop/static/img/goods/1531328717.png',2200,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,44,1,11,3300,1531328719,1531328719),
	(74,'玩具5','http://localhost:8080/jsp-shop/static/img/goods/1531328739.png',3300,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,55,1,11,4400,1531328749,1531328749),
	(75,'玩具6','http://localhost:8080/jsp-shop/static/img/goods/1531328766.png',3300,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,55,1,11,4400,1531328769,1531328769),
	(76,'玩具7','http://localhost:8080/jsp-shop/static/img/goods/1531328791.png',2200,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,44,1,11,3300,1531328794,1531328794),
	(77,'玩具8','http://localhost:8080/jsp-shop/static/img/goods/1531328810.png',3300,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,55,1,11,4400,1531328813,1531328813),
	(78,'玩具9','http://localhost:8080/jsp-shop/static/img/goods/1531328825.png',3300,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,55,1,11,4400,1531328827,1531328827),
	(79,'玩具','http://localhost:8080/jsp-shop/static/img/goods/1531328840.png',4400,'玩具，泛指可用来玩的物品，玩玩具在人类社会中常常被作为一种寓教于乐的方式。玩具也可以是自然物体，即是沙、石、泥、树枝等等的非人工东西，对玩具应作广义理解，它不是只限于街上卖的供人玩的东西，凡是可以玩的、看的、听的和触摸的东西，都可以叫玩具。玩具适合儿童，更适合青年和中老年人。',0,33,1,11,5500,1531328843,1531328843),
	(80,'箱包1','http://localhost:8080/jsp-shop/static/img/goods/1531328929.png',1100,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,33,1,12,2200,1531328964,1531328964),
	(81,'箱包2','http://localhost:8080/jsp-shop/static/img/goods/1531328976.png',3300,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,55,1,12,4400,1531328981,1531328981),
	(82,'箱包3','http://localhost:8080/jsp-shop/static/img/goods/1531328996.png',2200,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,44,1,12,3300,1531329002,1531329002),
	(83,'箱包4','http://localhost:8080/jsp-shop/static/img/goods/1531329023.png',3300,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,55,1,12,4400,1531329027,1531329027),
	(84,'箱包5','http://localhost:8080/jsp-shop/static/img/goods/1531329099.png',2200,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,44,1,12,3300,1531329104,1531329104),
	(85,'箱包6','http://localhost:8080/jsp-shop/static/img/goods/1531329124.png',33400,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,55,1,12,44400,1531329127,1531329127),
	(86,'箱包7','http://localhost:8080/jsp-shop/static/img/goods/1531329144.png',1100,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,33,1,12,2200,1531329148,1531329148),
	(87,'箱包8','http://localhost:8080/jsp-shop/static/img/goods/1531329162.png',11100,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,33,1,12,22200,1531329166,1531329166),
	(88,'箱包9','http://localhost:8080/jsp-shop/static/img/goods/1531329183.png',9900,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,100,1,12,99900,1531329187,1531329187),
	(89,'箱包10','http://localhost:8080/jsp-shop/static/img/goods/1531329201.png',3300,'箱包（xiāng bāo）是对袋子的统称，是用来装东西的各种包包的统称，包括一般的购物袋、手提包、手拿包、钱包、背包、单肩包、挎包、腰包和多种拉杆箱等。',0,55,1,12,4400,1531329205,1531329205),
	(90,'眼镜1','http://localhost:8080/jsp-shop/static/img/goods/1531329263.png',9900,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,99,1,13,99900,1531329305,1531329305),
	(91,'眼镜2','http://localhost:8080/jsp-shop/static/img/goods/1531329321.png',2200,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,44,1,13,3300,1531329324,1531329324),
	(92,'眼镜3','http://localhost:8080/jsp-shop/static/img/goods/1531329338.png',3300,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,55,1,13,4400,1531329340,1531329340),
	(93,'眼镜4','http://localhost:8080/jsp-shop/static/img/goods/1531329352.png',9900,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,99,1,13,99900,1531329355,1531329355),
	(94,'眼镜5','http://localhost:8080/jsp-shop/static/img/goods/1531329385.png',10000,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,22,1,13,20000,1531329391,1531329391),
	(95,'眼镜6','http://localhost:8080/jsp-shop/static/img/goods/1531329403.png',9900,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,99,1,13,99900,1531329406,1531329406),
	(96,'眼镜7','http://localhost:8080/jsp-shop/static/img/goods/1531329425.png',9900,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,99,1,13,99900,1531329428,1531329428),
	(97,'眼镜8','http://localhost:8080/jsp-shop/static/img/goods/1531329450.png',9900,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,99,1,13,99900,1531329453,1531329453),
	(98,'眼镜9','http://localhost:8080/jsp-shop/static/img/goods/1531329470.png',9900,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,99,1,13,99900,1531329472,1531329472),
	(99,'眼镜10','http://localhost:8888/jsp-shop/static/img/goods/1531329486.png',9900,'眼镜由镜片、镜架组成。分近视眼镜、远视眼镜、老花眼镜及散光眼镜四种。亦有特制眼镜供观看3D立体影像或虚拟真实影像。眼镜的其他种类包括护目镜，太阳镜，游泳镜等，为眼睛提供各种保护。现代的眼镜，通常在镜片中间设有鼻托，及在左右两臂搁在耳朵上的位置设有软垫。爱美或不习惯佩戴眼镜的人，可以选择以隐形眼镜矫正视力。虽然近年隐形眼镜及激光矫视手术越来越普及，但眼镜仍然是最普遍的矫正视力工具。',0,99,1,13,99900,1531329492,1531329492);

/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table goods_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `goods_category`;

CREATE TABLE `goods_category` (
  `goods_category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
  `goods_category_name` varchar(255) NOT NULL COMMENT '商品分类名称',
  `goods_category_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品类别的状态0禁用1正常',
  `create_time` int(10) NOT NULL COMMENT '商品类别创建时间',
  `update_time` int(10) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`goods_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `goods_category` WRITE;
/*!40000 ALTER TABLE `goods_category` DISABLE KEYS */;

INSERT INTO `goods_category` (`goods_category_id`, `goods_category_name`, `goods_category_status`, `create_time`, `update_time`)
VALUES
	(6,'服装',1,1531323781,1531323781),
	(7,'家居',1,1531326409,1531326409),
	(8,'美妆',1,1531326931,1531326931),
	(9,'手表',1,1531327339,1531327339),
	(10,' 数码',1,1531328203,1531328203),
	(11,'玩具',1,1531328600,1531328600),
	(12,'箱包',1,1531328898,1531328898),
	(13,'眼镜',1,1531329231,1531329231);

/*!40000 ALTER TABLE `goods_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table goods_orders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `goods_orders`;

CREATE TABLE `goods_orders` (
  `goods_order_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品订单表',
  `goods_id` int(10) NOT NULL COMMENT '商品id',
  `order_id` int(10) NOT NULL DEFAULT '0' COMMENT '订单id',
  `goods_num` int(10) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `goods_price` int(10) NOT NULL DEFAULT '0' COMMENT '商品售货单价',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` int(10) NOT NULL,
  `update_time` int(10) NOT NULL,
  `goods_order_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '加入购物车0，1已购买',
  PRIMARY KEY (`goods_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `goods_orders` WRITE;
/*!40000 ALTER TABLE `goods_orders` DISABLE KEYS */;

INSERT INTO `goods_orders` (`goods_order_id`, `goods_id`, `order_id`, `goods_num`, `goods_price`, `user_id`, `create_time`, `update_time`, `goods_order_status`)
VALUES
	(10,21,16,1,20000,6,123,222,1),
	(11,21,0,7,20000,6,1531324863,1531324863,0),
	(12,21,17,1,20000,7,1531325511,1531325511,1),
	(14,21,0,1,20000,7,1531325909,1531325909,1),
	(15,21,21,1,20000,7,1531330094,1531330094,1),
	(16,26,0,1,20000,7,1531354677,1531354677,1),
	(17,26,21,1,20000,7,1531354714,1531354714,1);

/*!40000 ALTER TABLE `goods_orders` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table goods_unit
# ------------------------------------------------------------

DROP TABLE IF EXISTS `goods_unit`;

CREATE TABLE `goods_unit` (
  `goods_unit_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品单位id',
  `goods_unit_name` varchar(255) NOT NULL COMMENT '商品单位名称',
  `create_time` int(10) NOT NULL,
  `update_time` int(11) NOT NULL COMMENT '更新时间',
  `goods_unit_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0禁用1正常',
  PRIMARY KEY (`goods_unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table orders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_total` int(10) NOT NULL DEFAULT '0' COMMENT '订单总额',
  `order_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态0 正在支付中，1支付成功，2支付失败，3已经退款',
  `create_time` int(10) NOT NULL COMMENT '10',
  `update_time` int(10) NOT NULL COMMENT '更新时间',
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `user_address_name` varchar(255) NOT NULL DEFAULT '0' COMMENT '用户地址',
  `out_trade_no` varchar(55) NOT NULL,
  `complete_time` int(10) NOT NULL COMMENT '订单完成时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;

INSERT INTO `orders` (`order_id`, `order_total`, `order_status`, `create_time`, `update_time`, `user_id`, `user_address_name`, `out_trade_no`, `complete_time`)
VALUES
	(16,200,1,1531324639,1531324639,6,'222','1000000563481747',1531324639),
	(17,200,1,1531325538,1531325538,7,'河北师范大学','1000000061010110',1531325538),
	(19,200,1,1531325909,1531325909,7,'河北师范大学','40001',1531325909),
	(20,200,1,1531354677,1531354677,7,'河北师范大学','40001',1531354677),
	(21,400,1,1531354762,1531354762,7,'河北科技大学','1000001490889584',1531354762);

/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_address`;

CREATE TABLE `user_address` (
  `user_address_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户地址',
  `user_address_name` varchar(255) NOT NULL COMMENT '地址',
  `create_time` int(10) NOT NULL,
  `update_time` int(10) NOT NULL,
  `user_address_status` tinyint(4) NOT NULL COMMENT '1.正在使用的地址0备用',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`user_address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;

INSERT INTO `user_address` (`user_address_id`, `user_address_name`, `create_time`, `update_time`, `user_address_status`, `user_id`)
VALUES
	(16,'1234324',1531324622,1531324622,1,6),
	(17,'222',1531324627,1531324627,0,6),
	(18,'河北师范大学',1531325530,1531325530,0,7),
	(19,'河北科技大学',1531354748,1531354748,1,7);

/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `user_password` varchar(255) NOT NULL COMMENT '用户密码，md5加密',
  `user_email` varchar(50) NOT NULL COMMENT '用户邮箱',
  `user_phone` varchar(20) NOT NULL COMMENT '用户手机号',
  `create_time` int(10) NOT NULL COMMENT '注册时间',
  `update_time` int(10) NOT NULL COMMENT '更新时间',
  `user_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户状态1正常，0禁用',
  `address_id` int(10) DEFAULT NULL COMMENT '地址id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`user_id`, `user_name`, `user_password`, `user_email`, `user_phone`, `create_time`, `update_time`, `user_status`, `address_id`)
VALUES
	(6,'user1','202cb962ac59075b964b07152d234b70','tuzisir@163.com','15703277652',123,222,1,NULL),
	(7,'user2','202cb962ac59075b964b07152d234b70','891392850@qq.com','15703277652',123,222,1,NULL),
	(8,'user3','202cb962ac59075b964b07152d234b70','891392850@qq.com','15703277652',123,222,1,NULL);

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
