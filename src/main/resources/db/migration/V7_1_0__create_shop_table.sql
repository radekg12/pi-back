CREATE TABLE `shop`
(
    `id`            int           NOT NULL AUTO_INCREMENT,
    `latitude`      double        NOT NULL,
    `longitude`     double        NOT NULL,
    `description`   varchar(300)  NOT NULL,
    PRIMARY KEY (`id`)
);