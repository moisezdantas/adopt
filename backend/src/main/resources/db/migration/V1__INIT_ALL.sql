CREATE TABLE `tb_address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `complement` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `district` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`email`)
);

CREATE TABLE `tb_donation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `card_number` varchar(255) DEFAULT NULL,
  `code_secret` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `due_date` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tb_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tb_person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `type_gender` bigint DEFAULT NULL,
  `type_person` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `status` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
);


CREATE TABLE `tb_donation_order` (
  `donation_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  PRIMARY KEY (`donation_id`,`order_id`),
  FOREIGN KEY (`donation_id`) REFERENCES `tb_donation` (`id`),
  FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`)
);


CREATE TABLE `tb_person_address` (
  `person_id` bigint NOT NULL,
  `address_id` bigint NOT NULL,
  PRIMARY KEY (`person_id`,`address_id`),
  FOREIGN KEY (`person_id`) REFERENCES `tb_person` (`id`),
  FOREIGN KEY (`address_id`) REFERENCES `tb_address` (`id`)
);


CREATE TABLE `tb_person_donation` (
  `person_id` bigint NOT NULL,
  `donation_id` bigint NOT NULL,
  PRIMARY KEY (`person_id`,`donation_id`),
  FOREIGN KEY (`person_id`) REFERENCES `tb_person` (`id`),
  FOREIGN KEY (`donation_id`) REFERENCES `tb_donation` (`id`)
);


CREATE TABLE `tb_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tb_user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
);


CREATE TABLE `tb_animal_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tb_breed` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `life_expectancy` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `animal_type_id` bigint DEFAULT NULL,
  `created_at` datetime NOT NULL,
  FOREIGN KEY (`animal_type_id`) REFERENCES `tb_animal_type` (`id`),
  PRIMARY KEY (`id`)
);

CREATE TABLE `tb_animal` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type_animal` bigint DEFAULT NULL,
  `rga` varchar(255) DEFAULT NULL,
  `birth_date` varchar(255) DEFAULT NULL,
  `deficiency` varchar(255) DEFAULT NULL,
  `is_vaccinated` bigint DEFAULT NULL,
  `type_animal_gender` bigint DEFAULT NULL,
  `is_castrated` bit(1) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `year` bigint DEFAULT NULL,
  `breed_id` bigint DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `animal_type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`animal_type_id`) REFERENCES `tb_animal_type` (`id`),
  FOREIGN KEY (`breed_id`) REFERENCES `tb_breed` (`id`),
  FOREIGN KEY (`address_id`) REFERENCES `tb_address` (`id`)
);
