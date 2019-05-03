-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `user_role`

CREATE TABLE `authority`
(
    `name` varchar(45) NOT NULL,
    PRIMARY KEY (`name`)
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
    `id`                 int         NOT NULL AUTO_INCREMENT,
    `street`             varchar(45) NOT NULL,
    `city`               varchar(45) NOT NULL,
    `postcode`           varchar(10) NOT NULL,
    `created_by`         varchar(50),
    `created_date`       timestamp,
    `last_modified_by`   varchar(50),
    `last_modified_date` timestamp,
    PRIMARY KEY (`id`)
);

-- ************************************** `product_subcategory`

CREATE TABLE `product_subcategory`
(
    `id`               int         NOT NULL AUTO_INCREMENT,
    `subcategory_name` varchar(45) NOT NULL,
    `category_id`      int         NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fkIdx_95` (`category_id`),
    CONSTRAINT `FK_95` FOREIGN KEY `fkIdx_95` (`category_id`) REFERENCES `product_category` (`id`)
);


-- ************************************** `order_status`

CREATE TABLE `order_status`
(
    `id`                       int         NOT NULL AUTO_INCREMENT,
    `name`                     varchar(45) NOT NULL,
    `order_status_category_id` int         NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fkIdx_116` (`order_status_category_id`),
    CONSTRAINT `FK_116` FOREIGN KEY `fkIdx_116` (`order_status_category_id`) REFERENCES `order_status_category` (`id`)
);


-- ************************************** `product`

CREATE TABLE `product`
(
    `id`                         int          NOT NULL AUTO_INCREMENT,
    `name`                       varchar(100) NOT NULL,
    `description`                text,
    `company`                    varchar(45)  NOT NULL,
    `logical_quantity_in_stock`  int          NOT NULL,
    `physical_quantity_in_stock` int          NOT NULL,
    `unit_price`                 int          NOT NULL,
    `subcategory_id`             int          NOT NULL,
    `image_url`                  varchar(500) NOT NULL,
    `available`                  boolean      NOT NULL,
    `created_by`                 varchar(50),
    `created_date`               timestamp,
    `last_modified_by`           varchar(50),
    `last_modified_date`         timestamp,
    PRIMARY KEY (`id`),
    KEY `fkIdx_98` (`subcategory_id`),
    CONSTRAINT `FK_98` FOREIGN KEY `fkIdx_98` (`subcategory_id`) REFERENCES `product_subcategory` (`id`)
);


-- ************************************** `customer`

CREATE TABLE `customer`
(
    `id`                 int          NOT NULL AUTO_INCREMENT,
    `password_hash`      varchar(200) NOT NULL,
    `email`              varchar(45)  NOT NULL,
    `first_name`         varchar(45)  NOT NULL,
    `last_name`          varchar(45)  NOT NULL,
    `phone_number`       varchar(20),
    `address_id`         int,
    `activated`          boolean      NOT NULL,
    `created_by`         varchar(50),
    `created_date`       timestamp,
    `last_modified_by`   varchar(50),
    `last_modified_date` timestamp,
    PRIMARY KEY (`id`),
    KEY `fkIdx_128` (`address_id`),
    CONSTRAINT `FK_128` FOREIGN KEY `fkIdx_128` (`address_id`) REFERENCES `address` (`id`),
    CONSTRAINT UC_Email UNIQUE (email)
);


-- ************************************** `specification_position`

CREATE TABLE `specification_position`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `name`       varchar(45)  NOT NULL,
    `value`      varchar(300) NOT NULL,
    `product_id` int          NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fkIdx_153` (`product_id`),
    CONSTRAINT `FK_153` FOREIGN KEY `fkIdx_153` (`product_id`) REFERENCES `product` (`id`)
);


-- ************************************** `shopping_cart_position`

CREATE TABLE `shopping_cart_position`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `product_id`  int NOT NULL,
    `customer_id` int NOT NULL,
    `quantity`    int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fkIdx_107` (`product_id`),
    CONSTRAINT `FK_107` FOREIGN KEY `fkIdx_107` (`product_id`) REFERENCES `product` (`id`),
    KEY `fkIdx_157` (`customer_id`),
    CONSTRAINT `FK_157` FOREIGN KEY `fkIdx_157` (`customer_id`) REFERENCES `customer` (`id`)
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
    KEY `fkIdx_113` (`order_status_id`),
    CONSTRAINT `FK_113` FOREIGN KEY `fkIdx_113` (`order_status_id`) REFERENCES `order_status` (`id`),
    KEY `fkIdx_119` (`delivery_type_id`),
    CONSTRAINT `FK_119` FOREIGN KEY `fkIdx_119` (`delivery_type_id`) REFERENCES `delivery_type` (`id`),
    KEY `fkIdx_122` (`payment_method_id`),
    CONSTRAINT `FK_122` FOREIGN KEY `fkIdx_122` (`payment_method_id`) REFERENCES `payment_method` (`id`),
    KEY `fkIdx_125` (`customer_id`),
    CONSTRAINT `FK_125` FOREIGN KEY `fkIdx_125` (`customer_id`) REFERENCES `customer` (`id`),
    KEY `fkIdx_131` (`delivery_address_id`),
    CONSTRAINT `FK_131` FOREIGN KEY `fkIdx_131` (`delivery_address_id`) REFERENCES `address` (`id`)
);


-- ************************************** `order_position`

CREATE TABLE `order_position`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `quantity`   int NOT NULL,
    `unit_price` int NOT NULL,
    `product_id` int NOT NULL,
    `order_id`   int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fkIdx_101` (`product_id`),
    CONSTRAINT `FK_101` FOREIGN KEY `fkIdx_101` (`product_id`) REFERENCES `product` (`id`),
    KEY `fkIdx_104` (`order_id`),
    CONSTRAINT `FK_104` FOREIGN KEY `fkIdx_104` (`order_id`) REFERENCES `order` (`id`)
);

CREATE TABLE `customer_authority`
(
    `customer_id`    int         NOT NULL,
    `authority_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`customer_id`, `authority_name`),
    CONSTRAINT `FK_AUTHORITY_NAME` FOREIGN KEY (`authority_name`) REFERENCES `authority` (`name`),
    CONSTRAINT `FK_CUSTOMER_ID` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

create table `persistent_audit_event`
(
    `event_id`   int         NOT NULL AUTO_INCREMENT,
    `principal`  VARCHAR(50) NOT NULL,
    `event_date` TIMESTAMP,
    `event_type` VARCHAR(255),
    CONSTRAINT `PK_JHI_PERSISTENT_AUDIT_EVENT` PRIMARY KEY (`event_id`)
);
CREATE INDEX `IDX_PERSISTENT_AUDIT_EVENT` ON `persistent_audit_event` (`principal`, `event_date`);

create table `persistent_audit_evt_data`
(
    `event_id` int          NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(150) NOT NULL,
    `value`    VARCHAR(255),
    PRIMARY KEY (`event_id`, `name`),
    CONSTRAINT `FK_EVT_PERS_AUDIT_EVT_DATA` FOREIGN KEY (`event_id`) REFERENCES `persistent_audit_event` (`event_id`)
);
CREATE INDEX `IDX_PERSISTENT_AUDIT_EVT_DATA` ON `persistent_audit_evt_data` (`event_id`);
