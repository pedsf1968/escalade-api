# escalade
Projet de site d'escalade

[![Build Status](https://travis-ci.com/pedsf1968/escalade.svg?branch=master)](https://travis-ci.com/pedsf1968/escalade-api)
![GitHub](https://img.shields.io/github/license/pedsf1968/escalade-api)

# Objet :
Création d’un site communautaire autour de l’escalade.

#Contexte :
Avec l’objectif de fédérer les licenciés, l’association “Les amis de l’escalade”
souhaite développer sa présence en ligne. À ce titre, plusieurs axes d’amélioration
ont été identifiés dont la création d’un site communautaire.
Ce site aura pour but la mise en relation et le partage d’informations. Il permettra
de donner de la visibilité à l’association afin d’encourager des grimpeurs
indépendants à y adhérer.

#Liste des fonctionnalités :
- F1 : Un utilisateur doit pouvoir consulter les informations des sites
d’escalades (secteurs, voies, longueurs, etc.).
- F2 : Un utilisateur doit pouvoir faire une recherche à l’aide de plusieurs
critères pour trouver un site de grimpe et consulter le résultat de cette
recherche. Les critères peuvent être le lieu, la cotation, le nombre de
secteurs, etc.
- F3 : Un utilisateur doit pouvoir s’inscrire gratuitement sur le site.
- F4 : Un utilisateur connecté doit pouvoir partager des informations sur un
site d’escalade (secteurs, voies, longueurs, etc.).
- F5 : Un utilisateur connecté doit pouvoir laisser des commentaires sur les
pages des sites d’escalade.
- F6 : Un membre de l'association doit pouvoir taguer un site d’escalade
enregistré sur la plateforme comme « Officiel Les amis de l’escalade ».
- F7 : Un membre de l'association doit pouvoir modifier et supprimer un
commentaire.
- F8 : Un utilisateur connecté doit pouvoir enregistrer dans son espace
personnel les topos qu’ils possèdent et préciser si ces derniers sont
disponibles pour être prêtés ou non.
Un topo est défini par un nom, une description, un lieu et une date de
parution.
- F9 : Un utilisateur connecté doit pouvoir consulter la liste des topos
disponibles par d’autres utilisateurs et faire une demande de réservation.
La réservation n’inclut pas les notions de date de début et date de fin.
- F10 : Un utilisateur connecté doit pouvoir accepter une demande de
réservation. Si une réservation est acceptée, automatiquement le topo
devient indisponible. L’utilisateur connecté pourra ensuite de nouveau
changer le statut du topo en « disponible ».
La plateforme se contente de mettre en contact les deux parties pour le
prêt d’un topo (par échange de coordonnées).

#Liste des contraintes fonctionnelles :
- C1 : Le vocabulaire de l’escalade doit être utilisé : site, spot, voie, longueur,
grimpeur, point ou spit, cotation, topo.
- C2 : Le site doit être responsive.
- C3 : Le site doit être sécurisé.

# Définitions
- Un site ou spot, c’est un lieu où il est possible de grimper.
- Secteur : Les sites peuvent être découpés en plusieurs secteurs qui regroupent un ensemble de voies.
- Voie : c’est le chemin à emprunter par le grimpeur pour arriver à destination : le haut de la voie.
si la voie est découpée en plusieurs « parties » à grimper les unes après les autres, ces parties s’appellent des longueurs et on trouve un relai en haut de chaque longueur.
- Relai : Quand le grimpeur arrive en haut de la longueur, c’est à ce relai qu’il se vache, c’est à dire qu’il s’y accroche, à l’aide de sa vache ou longe (corde nouée sur le baudrier du grimpeur et équipée d’un mousqueton à verrouillage).
- Spit : Les points ou spits sont des ancrages fixes que l’on trouve dans les voies dites « équipées » et qui permettent au grimpeur de s’assurer au fur et à mesure de sa progression, à l’aide de dégaines
- Cotation : La cotation d’une longueur ou d’une voie, représente sa difficulté. En France, le système de cotation va, en gros, par ordre croissant de difficulté, de 3 à 9c. Le chiffre est en quelque sorte, l’unité principale et la lettre une sous-unité (de « a » à « c »)
- Topo : C'est est un recueil contenant toutes les informations utiles sur les sites d’escalade d’une région (les secteurs, les voies, leur hauteur, leur cotation, le nombre de points…). Une bible quoi !