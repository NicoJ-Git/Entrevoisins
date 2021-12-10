# OpenClassrooms

Ce dépôt contient une mini-application pour le P3 du parcours **Grande École du Numérique**: ENTREVOISINS

Objectif du dépôt: ajout d'une nouvelle fonctionnalité:

1/ pouvoir afficher les détails concernant les autres utilisateurs.(affiche l’avatar, le nom de l’utilisateur, l'adresse, le facebook, le numéro de téléphone et les commentaires " à propos de moi".
2/ pouvoir ajouter (supprimer) des utilisateurs en favoris avec un bouton dédié à cet usage.
3/ Insérer un bouton retour à la liste à partir de l'affichage de detail.
4/ faire apparaitre la liste des utilisateurs favoris dans un onglet "Favoris".

Contributeur du code: Nicolas Joulié Expleo France / OpenClassRooms

Instructions (comment utiliser ce dépôt):
Technologies: JAVA / butterknife / glide / patterns: intent (intent.putExtra) / Create and return a new instance (bundle.putBoolean)
Liens: https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/GEN+android+P3/De%CC%81veloppez+une+nouvelle+fonctionnalite%CC%81+pour+l%E2%80%99application+Entrevoisins-+Note+de+cadrage+(1).pdf
Commentaires / analyse:
Le binding des nouveaux widget continue à être effectué grace à butterknife (comme à l'initial).
Le parametre clef pour passer les données d'un voisin dans la vue de détail est l'"Id" du voisin.

**************************************************************************************************************************
MAJ: 10-11-: suite à la demande lors de la soutenance, rajout des 3 tests instrumentaux concernant les nouvelles features.
**************************************************************************************************************************
