INSERT INTO utilisateur (id, civilite, nom, prenom, pseudo, telephone, mail, login, mot_de_passe, est_membre, adresse_id)
VALUES (1,'M','DUPONT','Martin','Gros doigts','0324593874','martin.dupont@gmail.com','dupont','martin',0,1);
INSERT INTO utilisateur (id, civilite, nom, prenom, pseudo, telephone, mail, login, mot_de_passe, est_membre, adresse_id)
VALUES (2,'M','DEPRES','Paul','L''anguille','0654654874','paul.depres@hotmail.com','depres','paul',0,1);
INSERT INTO site (id, nom, type, a_commentaire, photo_url, map_url) VALUES (1,'La falaise', 'TOPO', 0,'','');
-- INSERT INTO topo (site_id, date, promoteur_id) VALUES (1, NOW(),1);
INSERT INTO topo (id, date, promoteur_id) VALUES (1, NOW(),1);
INSERT INTO site (id, nom, type, a_commentaire, photo_url, map_url) VALUES (2,'Le Rock', 'TOPO', 0,'','');
-- INSERT INTO topo (site_id, date, promoteur_id) VALUES (2, NOW(),1);
INSERT INTO topo (id, date, promoteur_id) VALUES (2, NOW(),1);
INSERT INTO site (id, nom, type, a_commentaire, photo_url, map_url) VALUES (3,'La gorge', 'TOPO', 0,'','');
-- INSERT INTO topo (site_id, date, promoteur_id) VALUES (3, NOW(),1);
INSERT INTO topo (id, date, promoteur_id) VALUES (3, NOW(),1);
INSERT INTO site (id, nom, type, a_commentaire, photo_url, map_url) VALUES (4,'Côté est', 'SECTEUR', 0,'','');
--INSERT INTO secteur (site_id, topo_id, equipement) VALUES (4, 1,'100m de corde');
INSERT INTO secteur (id, topo_id, equipement) VALUES (4, 1,'100m de corde');

