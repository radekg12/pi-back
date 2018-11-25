-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `user_role`

CREATE TABLE `user_role`
(
  `id`        int         NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);


-- ************************************** `shopping_cart`

CREATE TABLE `shopping_cart`
(
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);


-- ************************************** `product_category`

CREATE TABLE `product_category`
(
  `id`            int         NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);


-- ************************************** `payment_method`

CREATE TABLE `payment_method`
(
  `id`    int         NOT NULL AUTO_INCREMENT,
  `name`  varchar(45) NOT NULL,
  `price` int         NOT NULL,
  PRIMARY KEY (`id`)
);


-- ************************************** `order_status_category`

CREATE TABLE `order_status_category`
(
  `id`   int         NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);


-- ************************************** `discount`

CREATE TABLE `discount`
(
  `id`            int          NOT NULL AUTO_INCREMENT,
  `name`          varchar(45)  NOT NULL,
  `description`   varchar(300) NOT NULL,
  `percent_value` int          NOT NULL,
  PRIMARY KEY (`id`)
);


-- ************************************** `delivery_type`

CREATE TABLE `delivery_type`
(
  `id`    int         NOT NULL AUTO_INCREMENT,
  `name`  varchar(45) NOT NULL,
  `price` int         NOT NULL,
  PRIMARY KEY (`id`)
);


-- ************************************** `address`

CREATE TABLE `address`
(
  `id`       int         NOT NULL AUTO_INCREMENT,
  `street`   varchar(45) NOT NULL,
  `city`     varchar(45) NOT NULL,
  `postcode` int         NOT NULL,
  PRIMARY KEY (`id`)
);


-- ************************************** `user_account`

CREATE TABLE `user_account`
(
  `id`           int          NOT NULL AUTO_INCREMENT,
  `username`     varchar(45)  NOT NULL,
  `password`     varchar(255) NOT NULL,
  `user_role_id` int          NOT NULL,
  PRIMARY KEY (`id`),
  KEY            `fkIdx_141` (`user_role_id`),
  CONSTRAINT `FK_141` FOREIGN KEY `fkIdx_141` (`user_role_id`) REFERENCES `user_role` (`id`)
);


-- ************************************** `product_subcategory`

CREATE TABLE `product_subcategory`
(
  `id`               int         NOT NULL AUTO_INCREMENT,
  `subcategory_name` varchar(45) NOT NULL,
  `category_id`      int         NOT NULL,
  PRIMARY KEY (`id`),
  KEY                `fkIdx_95` (`category_id`),
  CONSTRAINT `FK_95` FOREIGN KEY `fkIdx_95` (`category_id`) REFERENCES `product_category` (`id`)
);


-- ************************************** `order_status`

CREATE TABLE `order_status`
(
  `id`                       int         NOT NULL AUTO_INCREMENT,
  `name`                     varchar(45) NOT NULL,
  `order_status_category_id` int         NOT NULL,
  PRIMARY KEY (`id`),
  KEY                        `fkIdx_116` (`order_status_category_id`),
  CONSTRAINT `FK_116` FOREIGN KEY `fkIdx_116` (`order_status_category_id`) REFERENCES `order_status_category` (`id`)
);


-- ************************************** `product`

CREATE TABLE `product`
(
  `id`                int          NOT NULL AUTO_INCREMENT,
  `name`              varchar(45)  NOT NULL,
  `description`       text,
  `company`           varchar(45)  NOT NULL,
  `quantity_in_stock` int          NOT NULL,
  `unity_price`       int          NOT NULL,
  `subcategory_id`    int          NOT NULL,
  `image_url`         varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY                 `fkIdx_98` (`subcategory_id`),
  CONSTRAINT `FK_98` FOREIGN KEY `fkIdx_98` (`subcategory_id`) REFERENCES `product_subcategory` (`id`)
);


-- ************************************** `customer`

CREATE TABLE `customer`
(
  `id`               int         NOT NULL,
  `company_name`     varchar(45) NOT NULL,
  `first_name`       varchar(45) NOT NULL,
  `last_name`        varchar(45) NOT NULL,
  `phone_number`     int,
  `email`            varchar(45) NOT NULL,
  `address_id`       int         NOT NULL,
  `shopping_cart_id` int         NOT NULL,
  `user_account_id`  int         NOT NULL,
  PRIMARY KEY (`id`),
  KEY                `fkIdx_128` (`address_id`),
  CONSTRAINT `FK_128` FOREIGN KEY `fkIdx_128` (`address_id`) REFERENCES `address` (`id`),
  KEY                `fkIdx_134` (`shopping_cart_id`),
  CONSTRAINT `FK_134` FOREIGN KEY `fkIdx_134` (`shopping_cart_id`) REFERENCES `shopping_cart` (`id`),
  KEY                `fkIdx_144` (`user_account_id`),
  CONSTRAINT `FK_144` FOREIGN KEY `fkIdx_144` (`user_account_id`) REFERENCES `user_account` (`id`)
);


-- ************************************** `specification_position`

CREATE TABLE `specification_position`
(
  `id`         int          NOT NULL AUTO_INCREMENT,
  `name`       varchar(45)  NOT NULL,
  `value`      varchar(300) NOT NULL,
  `product_id` int          NOT NULL,
  PRIMARY KEY (`id`),
  KEY          `fkIdx_153` (`product_id`),
  CONSTRAINT `FK_153` FOREIGN KEY `fkIdx_153` (`product_id`) REFERENCES `product` (`id`)
);


-- ************************************** `shopping_cart_position`

CREATE TABLE `shopping_cart_position`
(
  `id`               int NOT NULL AUTO_INCREMENT,
  `quantity`         int NOT NULL,
  `product_id`       int NOT NULL,
  `shopping_cart_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY                `fkIdx_107` (`product_id`),
  CONSTRAINT `FK_107` FOREIGN KEY `fkIdx_107` (`product_id`) REFERENCES `product` (`id`),
  KEY                `fkIdx_110` (`shopping_cart_id`),
  CONSTRAINT `FK_110` FOREIGN KEY `fkIdx_110` (`shopping_cart_id`) REFERENCES `shopping_cart` (`id`)
);


-- ************************************** `order`

CREATE TABLE `order`
(
  `id`                  int      NOT NULL AUTO_INCREMENT,
  `date_of_order`       datetime NOT NULL,
  `date_of_delivery`    datetime,
  `total_amount`        int      NOT NULL,
  `order_status_id`     int      NOT NULL,
  `delivery_type_id`    int      NOT NULL,
  `payment_method_id`   int      NOT NULL,
  `customer_id`         int      NOT NULL,
  `delivery_address_id` int      NOT NULL,
  PRIMARY KEY (`id`),
  KEY                   `fkIdx_113` (`order_status_id`),
  CONSTRAINT `FK_113` FOREIGN KEY `fkIdx_113` (`order_status_id`) REFERENCES `order_status` (`id`),
  KEY                   `fkIdx_119` (`delivery_type_id`),
  CONSTRAINT `FK_119` FOREIGN KEY `fkIdx_119` (`delivery_type_id`) REFERENCES `delivery_type` (`id`),
  KEY                   `fkIdx_122` (`payment_method_id`),
  CONSTRAINT `FK_122` FOREIGN KEY `fkIdx_122` (`payment_method_id`) REFERENCES `payment_method` (`id`),
  KEY                   `fkIdx_125` (`customer_id`),
  CONSTRAINT `FK_125` FOREIGN KEY `fkIdx_125` (`customer_id`) REFERENCES `customer` (`id`),
  KEY                   `fkIdx_131` (`delivery_address_id`),
  CONSTRAINT `FK_131` FOREIGN KEY `fkIdx_131` (`delivery_address_id`) REFERENCES `address` (`id`)
);


-- ************************************** `order_position`

CREATE TABLE `order_position`
(
  `id`          int NOT NULL AUTO_INCREMENT,
  `quantity`    int NOT NULL,
  `unity_price` int NOT NULL,
  `product_id`  int NOT NULL,
  `order_id`    int NOT NULL,
  PRIMARY KEY (`id`),
  KEY           `fkIdx_101` (`product_id`),
  CONSTRAINT `FK_101` FOREIGN KEY `fkIdx_101` (`product_id`) REFERENCES `product` (`id`),
  KEY           `fkIdx_104` (`order_id`),
  CONSTRAINT `FK_104` FOREIGN KEY `fkIdx_104` (`order_id`) REFERENCES `order` (`id`)
);





