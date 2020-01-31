-- insertion adresses
INSERT INTO address (street1,zip_code,city)
VALUES ('22, rue de la Paix','75111','Paris'),
        ('1, rue verte','68121','Strasbourg'),
        ('3, chemin de l’Escale','25000','Besançon');

INSERT INTO users (id,  firstname, lastname, phone, email, alias, password, is_member, address_id, photo_url)
VALUES (1,'Admin','ADMIN',null,'admin@escalade.org','ADMIN','$2a$10$IRKMI.Gzq83HyHvat5y0feUuxwYl5Eu22Rmr56q1WyVkiqv2vboSO', TRUE,1, 'ADMIN.png'),
    (2,'Martin','DUPONT','0324593874','martin.dupont@gmail.com','dupont','$2a$10$TpRLNPP8Q1XJqONRvS4QI.ea2f5.jsB/.U4ZnL3iSyFiGZ6pBPHfe', FALSE,2, 'dupont.png'),
    (3,'Paul','TINTIN','0654654874','paul.depres@hotmail.com','tintin','$2a$10$b/DpB2NSMWMokB.ZOP/D4ehRFqXRSx3fKib5BqgWqE8Ox9EaAm6Ty', FALSE,1, 'tintin.png'),
    (4,'Emilio','TAILLACQ',null,'eftaillacq@gmail.com','ET','$2a$10$xcMn7o5CGF9Z9btJK9ACLeG/B.GtJeKDcn/W/aHj64BVVpIJ2QMxe', TRUE,3, 'ET.png');


-- insertion roles
INSERT INTO role(id,name)
VALUES (1,'ROLE_GUEST'),(2,'ROLE_USER'),(3,'ROLE_STAFF'),(4,'ROLE_ADMIN');

INSERT INTO users_roles (user_id,role_id) VALUES (1,3),(1,4),(2,2),(3,2);

INSERT INTO cotation (id,FR,US,GB)
VALUES (1,'3','5.3',''),(2,'3+','5.4',''),(3,'4a','5.5',''),(4,'4b','5.6','4b'),(5,'4c','5.7','4c'),
    (6,'5a','5.8','5a'),(7,'5b','5.9','5a'),(8,'5c','5.10a','5b'),
    (9,'6a','5.10b','5b'),(10,'6a+','5.10c','5c'),(11,'6b','5.10d','5c'),(12,'6b+','5.11a','5c'),(13,'6c','5.11b','5c'),(14,'6c+','5.11c','6a'),
    (15,'7a','5.11d','6a'),(16,'7a+','5.12a','6b'),(17,'7b','5.12b','6b'),(18,'7b+','5.12c','6b'),(19,'7c','5.12d','6c'),(20,'7c+','5.13a','6c'),
    (21,'8a','5.13b','7a'),(22,'8a+','5.13c','7a'),(23,'8b','5.13d','7a'),(24,'8b+','5.14a','7b'),(25,'8c','5.14b','7b'),(26,'8c+','5.14c','7c'),
    (27,'9a','5.14d','7c'),(28,'9a+','5.15a','7c');

-- insertion tags
INSERT INTO tag (id,name)
VALUES (1,'Officiel Les amis de l’escalade'),
        (2,'falaise'),
        (3,'bloc');

INSERT INTO tag_list(topo,tag)
VALUES (1,1),(1,2),(2,1),(3,2);

-- insertion topo
INSERT INTO site (id, name, type, manager_id, photo_url, map_url, nb_comment, latitude, longitude)
VALUES (1,'La falaise', 'TOPO', 2,'TOPO1photo.jpg',null, 3,42.123,122.4564),
    (2,'Le Rock', 'TOPO', 2,'TOPO2photo.jpg','TOPO2map.jpg',2,55.123,12.6454),
    (3,'La gorge', 'TOPO', 2,'TOPO3photo.jpg','TOPO3map.jpg',1,62.123,22.5664),
    (4,'Grand étang','TOPO',2,'TOPO4photo.jpg','TOPO4map.jpg',0,82.123,11.454),
    (5,'Pôle dance', 'SECTOR', 2,'SECTOR5photo.jpg','SECTOR5map.jpg',1,55.123,12.6454),
    (6,'Coude à coude','SECTOR',2,'SECTOR6photo.jpg','SECTOR6map.jpg',2,55.123,12.6454),
    (7,'La terrasse','VOIE',2,'VOIE7photo.jpg','VOIE7map.jpg',0,55.123,12.6454),
    (8,'La grotte','VOIE',2,'VOIE8photo.jpg','VOIE8map.jpg',0,55.123,12.6454),
    (9,'La Roche de Haute pierre','TOPO',3,'TOPO9photo.jpg',null,0,25.123,15.654),
    (10,'La grotte','SECTOR',3,'SECTOR10photo.jpg',null,0,25.123,15.654);



INSERT INTO topo (site_id, region, address_id, date, description, technic, access,status,status_auto,nb_lane,cotation_min,cotation_max)
VALUES (1, 'Corse',1,'2019-06-11','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.',
'Sed ac orci egestas, imperdiet libero vitae, dignissim nunc. Sed ultrices fermentum nisi, ut dictum justo laoreet et. Etiam cursus sit amet turpis id cursus. Cras at hendrerit risus.',
'Sed porta viverra commodo. Curabitur vehicula sagittis egestas. Nullam et turpis sed mauris molestie rhoncus id et metus. Nulla facilisi.','AVAILABLE', true,0,3,10),
        (2, 'Corse', 1, '2019-07-11','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.',
'Sed ac orci egestas, imperdiet libero vitae, dignissim nunc. Sed ultrices fermentum nisi, ut dictum justo laoreet et. Etiam cursus sit amet turpis id cursus. Cras at hendrerit risus.','AVAILABLE',true,2,3,10),
        (3, 'Alpes', 1, '2019-11-11','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.',
'Sed ac orci egestas, imperdiet libero vitae, dignissim nunc. Sed ultrices fermentum nisi, ut dictum justo laoreet et. Etiam cursus sit amet turpis id cursus. Cras at hendrerit risus.','UNVAILABLE',false,0,5,15),
        (4,'Alpes', 1, '2019-12-11','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.',
'Sed ac orci egestas, imperdiet libero vitae, dignissim nunc. Sed ultrices fermentum nisi, ut dictum justo laoreet et. Etiam cursus sit amet turpis id cursus. Cras at hendrerit risus.','AVAILABLE',false,0,3,11),
        (9,'Franche-Comté', 3, '2020-01-01','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.',
'Sed ac orci egestas, imperdiet libero vitae, dignissim nunc. Sed ultrices fermentum nisi, ut dictum justo laoreet et. Etiam cursus sit amet turpis id cursus. Cras at hendrerit risus.','AVAILABLE',true,0,3,11);

INSERT INTO sector (site_id, topo_id, equipment)
VALUES (5, 2,'100m de corde'),
        (6,2,'Corde de 50m'),
        (10,9,'Corde de 50m');

INSERT INTO voie (site_id, parent_id, cotation_id, heigth, is_equipped)
VALUES (7,2,1,452,true),(8,6,15,122,false);

INSERT INTO longueur (id,voie_id,name,cotation_id,heigth)
VALUES (1,7,'La corde',5,122),
        (2,7,'La ficelle',7,22),
        (3,7,'La cassoulet',9,82),
        (4,8,'La coucourde',5,75);

INSERT INTO spit(topo_id,number,voie_id,longueur_id,cotation_id,comment,is_relay)
VALUES (2,1,7,1,3,'', false),(2,2,7,1,7,'',false),(2,3,7,1,8,'passez à droite',true),(2,4,7,1,10,'',false),(2,5,7,1,3,'',false),
    (2,6,7,1,3,'', false),(2,7,7,1,7,'roche fragile',false),(2,8,7,1,8,'',true),(2,9,7,1,10,'',false),(2,10,7,1,3,'',false),
    (2,11,7,2,3,'',false),(2,12,7,2,7,'',false),(2,13,7,2,8,'',true),(2,14,7,2,10,'',false),(2,15,7,2,3,'',false),
    (2,16,7,3,3,'',false),(2,17,7,3,7,'',false),(2,18,7,3,8,'',true),(2,19,7,3,10,'',false),(2,20,7,3,3,'',false),
    (2,21,8,4,3,'',false),(2,22,8,4,7,'',false),(2,23,8,4,8,'beau paysage devant',true),(2,24,8,4,10,'',false),(2,25,8,4,3,'',false),
    (2,26,8,4,3,'',false),(2,27,8,4,7,'passez à gauche',false),(2,28,8,4,8,'',true),(2,29,8,4,10,'',false),(2,30,8,4,3,'',false);

INSERT INTO comment (id,site_id,user_id,text)
VALUES (1,1,2,'C''est super on s''éclate'),(2,1,2,'Bien pour les débutants'),(3,1,3,'Belle vue pendant l''ascension'),
       (4,2,2,'C''est super on s''éclate'),(5,2,2,'Bien pour les débutants'),(6,3,3,'Belle vue pendant l''ascension'),
       (7,5,2,'C''est super on s''éclate'),(8,6,2,'Bien pour les débutants'),(9,6,3,'Belle vue pendant l''ascension'),
       (10,7,2,'C''est super on s''éclate'),(11,8,2,'Bien pour les débutants'),(12,8,3,'Belle vue pendant l''ascension');


ALTER SEQUENCE address_id_seq RESTART WITH 4;
ALTER SEQUENCE comment_id_seq RESTART WITH 13;
ALTER SEQUENCE cotation_id_seq RESTART WITH 29;
ALTER SEQUENCE longueur_id_seq RESTART WITH 5;
ALTER SEQUENCE role_id_seq RESTART WITH 5;
ALTER SEQUENCE site_id_seq RESTART WITH 11;
ALTER SEQUENCE tag_id_seq RESTART WITH 4;
ALTER SEQUENCE users_id_seq RESTART WITH 5;
