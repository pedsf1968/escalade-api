-- insertion adresses
INSERT INTO address (street1,zip_code,city)
VALUES ('22, rue de la Paix','75111','Paris'),
        ('1, rue verte','68121','Strasbourg'),
        ('3, chemin de lEscale','25000','Besançon');

INSERT INTO user (id,  firstname, lastname, phone, email, alias, password, is_member, address_id)
VALUES (1,'Admin','ADMIN',null,'admin@escalade.org','ADMIN','$2a$10$IRKMI.Gzq83HyHvat5y0feUuxwYl5Eu22Rmr56q1WyVkiqv2vboSO', 1,1),
    (2,'Martin','DUPONT','0324593874','martin.dupont@gmail.com','dupont','$2a$10$TpRLNPP8Q1XJqONRvS4QI.ea2f5.jsB/.U4ZnL3iSyFiGZ6pBPHfe', 0,2),
    (3,'Paul','TINTIN','0654654874','paul.depres@hotmail.com','tintin','$2a$10$b/DpB2NSMWMokB.ZOP/D4ehRFqXRSx3fKib5BqgWqE8Ox9EaAm6Ty', 0,1),
    (4,'Emilio','TAILLACQ',null,'eftaillacq@gmail.com','ET','$2a$10$xcMn7o5CGF9Z9btJK9ACLeG/B.GtJeKDcn/W/aHj64BVVpIJ2QMxe', 0,3);


-- insertion roles
INSERT INTO role(id,name)
VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
INSERT INTO user_roles VALUES (1,1),(2,2),(3,2);

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
        (2,'falaise');

INSERT INTO tag_list(topo,tag)
VALUES (1,1),(1,2),(2,1),(3,2);

-- insertion topo
INSERT INTO site (id, name, type, photo_url, map_url)
VALUES (1,'La falaise', 'TOPO', 'TOPO1photo.jpg',null),
    (2,'Le Rock', 'TOPO', 'TOPO2photo.jpg',null),
    (3,'La gorge', 'TOPO', 'TOPO3photo.jpg',null),
    (4,'Grand étang','TOPO','TOPO4photo.jpg',null),
    (5,'Pôle dance', 'SECTOR', 'SECTOR5photo.jpg','SECTOR5map.jpg'),
    (6,'Coude à coude','SECTOR','SECTOR6photo.png','SECTOR6map.jpg');



INSERT INTO topo (site_id, region, address_id, date, description, technic, access, manager_id)
VALUES (1, 'Corse',1,'2019-06-11','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.',
'Sed ac orci egestas, imperdiet libero vitae, dignissim nunc. Sed ultrices fermentum nisi, ut dictum justo laoreet et. Etiam cursus sit amet turpis id cursus. Cras at hendrerit risus.',
'Sed porta viverra commodo. Curabitur vehicula sagittis egestas. Nullam et turpis sed mauris molestie rhoncus id et metus. Nulla facilisi.',1);

INSERT INTO topo (site_id, region, address_id, date, description,manager_id)
VALUES (2, 'Corse', 1, '2019-07-11','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.',2),
        (3, 'Alpes', 1, '2019-11-11','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.',3),
        (4,'Alpes', 1, '2019-12-11','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae ante eget leo blandit ullamcorper eu ultricies felis.',2);

INSERT INTO sector (site_id, topo_id, equipment)
VALUES (5, 2,'100m de corde'),(6,2,'Corde de 50m');

INSERT INTO comment (id,site_id,user_id,text)
VALUES (1,1,2,'C''est super on s''éclate'),(2,1,2,'Bien pour les débutants'),(3,1,3,'Belle vue pendant l''ascension');

