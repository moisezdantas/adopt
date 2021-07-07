INSERT INTO `tb_user` VALUES (1,'test@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', now()),(2,'test2@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', now());

INSERT INTO `tb_role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');

INSERT INTO tb_user_role VALUES (1,1),(2,1),(2,2);

INSERT INTO `tb_animal_type` VALUES (1, 'CAT', now());
INSERT INTO `tb_animal_type` VALUES (2, 'DOG', now());
INSERT INTO `tb_animal_type` VALUES (3, 'PARROT', now());

INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('10 e 15 anos','British Shorthair','Entre 15 e 30 centímetros.','Pelos volumosos de tamanho médio.','Reino Unido.',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('15 e 20 anos','Gato Siamês;','Entre 15 e 20 centímetros.','pelos curtos','Tailândia e China',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('9 e 15 anos','Maine Coon;','Entre 34 e 44 centímetros.','Pelos longos e volumosos','Estados Unidos',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('14 anos','Gato Persa;','Entre 20 e 25 centímetros.','Pelos longos e finos, com muito volume','Região da Pérsia',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('9 e 15 anos','Himalaia;','Entre 15 e 20 centímetros.','Pelos longos e volumosos','Estados Unidos',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('9 e 13 anos','Burmese;','Entre 15 e 30 centímetros.','Pelos médios e pouco volumosos','Tailândia',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('14 anos','Angorá;','Entre 15 e 30 centímetros.','Pelos Longo e fino','Turquia',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('14 anos','Sphynx;','Entre 15 e 30 centímetros.','Os Sphynx não possuem pelos e são uma raça de gatos notória por ser “careca”.','Canadá',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('12 e 18 anos','Ragdoll;','Entre 40 e 50 centímetros.','Pelos longos e volumosos','Estados Unidos',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('15 anos','Sem Raça','Entre 15 e 30 centímetros.','Pelos volumosos de tamanho médio.','Outros ',1,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('15 anos','Akita','Alto','Leal, amigo e brincalhão','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('12 e 18 anos','Basset hound','Alto','Paciente, teimoso e charmoso','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('12 e 18 anos','Beagle','Médio','Alegre, companheiro e aventureiro','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('15 anos','Bichon frisé','Médio','Brincalhão, curioso e afetivo','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('9 e 13 anos','Boiadeiro australiano','Médio','Alerta, curioso e leal','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('10 e 15 anos','Border collie','Alto','Inteligente, leal e cheio de energia','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('12 e 18 anos','Boston terrier','Médio','Amigável, inteligente e vivaz','Outros ',2,now());
INSERT INTO `tb_breed` (life_expectancy, name, height, description, origin, animal_type_id, created_at) VALUES('14 anos','Boxer','Alto','Leal, afetuoso e brincalhão','Outros ',2,now());

