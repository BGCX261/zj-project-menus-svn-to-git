insert into Customer (username, password, name) values ('gavin', 'foobar', 'Gavin King')
insert into Customer (username, password, name) values ('demo', 'demo', 'Demo User')

INSERT INTO `seller` (`id`, `address`, `createDate`, `deliveryKey`, `description`, `displayName`, `logoUrl`, `modifyDate`, `name`, `pwd`, `status`, `tel`) VALUES (998, '长沙市岳麓区谷苑路255号', '2013-03-26', '高新区管委会，麓谷公园，软件大楼，像素汇，加州阳光，信息港，中联重科南门，企业广场，创业大厦，麓谷实验小学', '主营炒菜、盖浇饭、各式汤点小菜，可外卖', '潇湘馆', NULL, NULL, 'test', '12345', 1, '18070087522');
INSERT INTO `seller` (`id`, `address`, `createDate`, `deliveryKey`, `description`, `displayName`, `logoUrl`, `modifyDate`, `name`, `pwd`, `status`, `tel`) VALUES (999, '麓枫路黄荆小区', '2013-03-27', '罗马广场，中房联邦，柏家塘，黄荆小区，天顶乡，涉外经济管理学院，梅溪湖', '汤粉、面、凉菜', '常德原味粉馆', '', NULL, 'test', '12345', 1, '18200000000');

INSERT INTO `menu` (`id`, `description`, `imgUrl`, `insertDate`, `modifyDate`, `name`, `price`, `status`, `seller_id`) VALUES (10000, '营养香菇炖鸡饭，采用上乘鸡胸肉外加蒜苗、香菜炒制而成，口感细腻', NULL, '2013-03-26', NULL, '香菇炖鸡饭', 13.00, 1, 998);
INSERT INTO `menu` (`id`, `description`, `imgUrl`, `insertDate`, `modifyDate`, `name`, `price`, `status`, `seller_id`) VALUES (10001, '新鲜杏鲍菇、肉丝、胡萝卜等混炒而成，菌', NULL, '2013-03-25', NULL, '杏鲍菇炒肉', 12.00, 1, 999);

--INSERT INTO `booking` (`id`, `beds`, `checkinDate`, `checkoutDate`, `creditCard`, `creditCardExpiryMonth`, `creditCardExpiryYear`, `creditCardName`, `smoking`, `menu_id`, `user_username`) VALUES (1, 1, '2013-03-31', '2013-04-01', '0123456789123456', 1, 2005, 'uuu', 0, 0, 'demo');


