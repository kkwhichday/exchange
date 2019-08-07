DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `id` int(11) NOT NULL auto_increment,
  `openid` varchar(50) NOT NULL,
  `phone` varchar(11) ,
   `nickName` varchar(50) NOT NULL,
    `avatarUrl` varchar(200) NOT NULL,
    `gender` varchar(50) NOT NULL,
    `country` varchar(50) NOT NULL,
    `province` varchar(50) NOT NULL,
    `city` varchar(50) NOT NULL,
    `deleted` int(1),
    `lastLoginTime` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ex_msg`;
CREATE TABLE `ex_msg` (
  `id` int(11) NOT NULL auto_increment,
  `openid` varchar(50) NOT NULL,
  `avatarUrl` varchar(200) NOT NULL,
  `nickName` varchar(50) NOT NULL,
  `msg` varchar(500) NOT NULL,
  `fromCurrency` varchar(50) NOT NULL,
  `toCurrency` varchar(50) NOT NULL,
  `rateRange` decimal(10,2) NOT NULL,
  `deleted` int(1),
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
