CREATE TABLE `upstream` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `messageId` varchar(255) NOT NULL,
  `topic` varchar(255) DEFAULT NULL,
  `payload` varchar(5000) DEFAULT NULL,
  `submitDate` datetime NULL
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;