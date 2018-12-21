CREATE TABLE `upstream` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(20) NOT NULL,
  `jsonStr` varchar(5000) DEFAULT NULL,
  `submitDate` datetime NULL
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;