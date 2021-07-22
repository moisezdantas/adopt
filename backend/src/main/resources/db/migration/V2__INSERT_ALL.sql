INSERT INTO `tb_user` VALUES
(1,'moises1695@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', now()),
(2,'test@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', now());

INSERT INTO `tb_role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');

INSERT INTO tb_user_role VALUES (1,1),(2,1),(2,2);

INSERT INTO `tb_animal_type` VALUES (1, 'Gato', now());
INSERT INTO `tb_animal_type` VALUES (2, 'Cachorro', now());
INSERT INTO `tb_animal_type` VALUES (3, 'Papagaio', now());

INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('10 e 15 anos','British Shorthair','Entre 15 e 30 centímetros.','Pelos volumosos de tamanho médio.','Reino Unido.',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('15 e 20 anos','Gato Siamês','Entre 15 e 20 centímetros.','pelos curtos','Tailândia e China',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('9 e 15 anos','Maine Coon','Entre 34 e 44 centímetros.','Pelos longos e volumosos','Estados Unidos',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('14 anos','Gato Persa','Entre 20 e 25 centímetros.','Pelos longos e finos, com muito volume','Região da Pérsia',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('9 e 15 anos','Himalaia','Entre 15 e 20 centímetros.','Pelos longos e volumosos','Estados Unidos',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('9 e 13 anos','Burmese','Entre 15 e 30 centímetros.','Pelos médios e pouco volumosos','Tailândia',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('14 anos','Angorá','Entre 15 e 30 centímetros.','Pelos Longo e fino','Turquia',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('14 anos','Sphynx','Entre 15 e 30 centímetros.','Os Sphynx não possuem pelos e são uma raça de gatos notória por ser “careca”.','Canadá',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('12 e 18 anos','Ragdoll','Entre 40 e 50 centímetros.','Pelos longos e volumosos','Estados Unidos',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('15 anos','Sem Raça','Entre 15 e 30 centímetros.','Pelos volumosos de tamanho médio.','Outros ',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('15 anos','Akita','Alto','Leal, amigo e brincalhão','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('12 e 18 anos','Basset hound','Alto','Paciente, teimoso e charmoso','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('12 e 18 anos','Beagle','Médio','Alegre, companheiro e aventureiro','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('15 anos','Bichon frisé','Médio','Brincalhão, curioso e afetivo','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('9 e 13 anos','Boiadeiro australiano','Médio','Alerta, curioso e leal','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('10 e 15 anos','Border collie','Alto','Inteligente, leal e cheio de energia','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('12 e 18 anos','Boston terrier','Médio','Amigável, inteligente e vivaz','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('14 anos','Boxer','Alto','Leal, afetuoso e brincalhão','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at)
VALUES('15 anos','Papaio da serra','32 cm','É considerado como ameaçado de extinção','Outros ',3,now());

INSERT INTO tb_person (cnpj, cpf, mobile_phone, name, rg, type_gender, type_person, user_id, status, created_at)
VALUES('', '1234567890', '', 'Moisez Arles Miranda Dantas', '', 1, 1, 1, 0, '2021-07-03 23:36:21');
INSERT INTO tb_person (cnpj, cpf, mobile_phone, name, rg, type_gender, type_person, user_id, status, created_at)
VALUES('', '1234567890', '', 'Fernando Dantas', '', 1, 1, 2, 0, '2021-07-03 23:36:21');

INSERT INTO tb_address(city, complement, created_at, district, `number`, street, zip_code)
VALUES('São Paulo', null, '2021-07-03 23:36:21', 'Centro', 123, 'Rua Test', '00000-000');
INSERT INTO tb_address(city, complement, created_at, district, `number`, street, zip_code)
VALUES('Rio Janeiro', null, '2021-07-03 23:36:21', 'Lapa', 123, 'Rua Test', '00000-000');
INSERT INTO tb_address(city, complement, created_at, district, `number`, street, zip_code)
VALUES('Bahia', null, '2021-07-03 23:36:21', 'Pituba', 123, 'Rua Test', '00000-000');

INSERT INTO tb_animal (name, rga, birth_date, deficiency, is_vaccinated, type_animal_gender, is_castrated, note, image_url, `year`, breed_id, address_id, adopter_id, created_at)
VALUES('Furao', NULL, '2021-07-03 23:36:21', NULL, NULL, 1, NULL, 'Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis. Viva Forevis aptent taciti sociosqu ad litora torquent. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.', '0c31e787-4562-4a13-af5e-4a52b060277b/cat.jpeg', NULL, 1, 1, NULL, '2021-07-03 23:36:21');
INSERT INTO tb_animal (name, rga, birth_date, deficiency, is_vaccinated, type_animal_gender, is_castrated, note, image_url, `year`, breed_id, address_id, adopter_id, created_at)
VALUES('Flash', NULL, '2021-07-03 23:36:21', NULL, NULL, 1, NULL, 'Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis. Viva Forevis aptent taciti sociosqu ad litora torquent. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.', '3e9dc603-0fa4-4a0f-8fc4-b53429772f8f/cat2.jpeg', NULL, 1, 1, NULL,'2021-07-03 23:36:21');
INSERT INTO tb_animal (name, rga, birth_date, deficiency, is_vaccinated, type_animal_gender, is_castrated, note, image_url, `year`, breed_id, address_id, adopter_id, created_at)
VALUES('Conan', NULL, '2021-07-03 23:36:21', NULL, NULL, 1, NULL, 'Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis. Viva Forevis aptent taciti sociosqu ad litora torquent. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.', 'f54144b2-9837-48d8-b02c-ead844b75ae6/cat3.jpeg', NULL, 1, 1, NULL,'2021-07-03 23:36:21');

INSERT INTO tb_animal (name, rga, birth_date, deficiency, is_vaccinated, type_animal_gender, is_castrated, note, image_url, `year`, breed_id, address_id, adopter_id, created_at)
VALUES('Toy', NULL, '2021-07-03 23:36:21', NULL, NULL, 1, NULL, 'Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis. Viva Forevis aptent taciti sociosqu ad litora torquent. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.', '6cde6ba3-d252-40a1-a002-b069da478746/dog1.png', NULL, 11, 1, NULL,'2021-07-03 23:36:21');
INSERT INTO tb_animal (name, rga, birth_date, deficiency, is_vaccinated, type_animal_gender, is_castrated, note, image_url, `year`, breed_id, address_id, adopter_id, created_at)
VALUES('Costelinha', NULL, '2021-07-03 23:36:21', NULL, NULL, 1, NULL, 'Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis. Viva Forevis aptent taciti sociosqu ad litora torquent. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.', '26e243b8-5b40-45c7-81e2-1af85ce2e0ff/dog2.jpg', NULL, 11, 2,NULL, '2021-07-03 23:36:21');
INSERT INTO tb_animal (name, rga, birth_date, deficiency, is_vaccinated, type_animal_gender, is_castrated, note, image_url, `year`, breed_id, address_id, adopter_id, created_at)
VALUES('Snow', NULL, '2021-07-03 23:36:21', NULL, NULL, 1, NULL, 'Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis. Viva Forevis aptent taciti sociosqu ad litora torquent. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.', 'e75fd373-2691-4f25-a621-beb789290079/dog3.jpg', NULL, 11, 3, NULL,'2021-07-03 23:36:21');

INSERT INTO tb_animal (name, rga, birth_date, deficiency, is_vaccinated, type_animal_gender, is_castrated, note, image_url, `year`, breed_id, address_id, adopter_id, created_at)
VALUES('Arnold', NULL, '2021-07-03 23:36:21', NULL, NULL, 1, NULL, 'Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis. Viva Forevis aptent taciti sociosqu ad litora torquent. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.', 'cc56f3f4-7824-42c6-a680-159dd7fba176/pag.jpg', NULL, 19, 1, NULL,'2021-07-03 23:36:21');
INSERT INTO tb_animal (name, rga, birth_date, deficiency, is_vaccinated, type_animal_gender, is_castrated, note, image_url, `year`, breed_id, address_id, adopter_id, created_at)
VALUES('Benji', NULL, '2021-07-03 23:36:21', NULL, NULL, 1, NULL, 'Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis. Viva Forevis aptent taciti sociosqu ad litora torquent. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.', 'c7950486-3781-4816-9ab5-9b8124b7973a/pag22.jpg', NULL, 19, 2, NULL,'2021-07-03 23:36:21');
INSERT INTO tb_animal (name, rga, birth_date, deficiency, is_vaccinated, type_animal_gender, is_castrated, note, image_url, `year`, breed_id, address_id, adopter_id, created_at)
VALUES('José', NULL, '2021-07-03 23:36:21', NULL, NULL, 1, NULL, 'Mussum Ipsum, cacilds vidis litro abertis. Tá deprimidis, eu conheço uma cachacis que pode alegrar sua vidis. Viva Forevis aptent taciti sociosqu ad litora torquent. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.', '69ceaa86-afe8-4a5e-80c8-b5a9799ce729/pagaio3.jpeg', NULL, 19, 3, NULL, '2021-07-03 23:36:21');
