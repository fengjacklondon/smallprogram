CREATE TABLE `assets` (
  `id` int(11) NOT NULL auto_increment,
  `image_name` varchar(200) NOT NULL,
  `image_url` varchar(200) NOT NULL,
  `image_desc` varchar(200) NOT NULL,
 `image_type` varchar(200) NOT NULL,
  `content` mediumtext NOT NULL,

  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;