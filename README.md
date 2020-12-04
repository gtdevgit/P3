![Android](https://img.shields.io/badge/Android-Studio-blue)
# Projet Entrevoisins
## Présentation 
Le projet __Entrevoisins__ est une application Android qui permet de mettre en relation des personnes d'un même quartier afin qu'ils se rendent de petits services.
Cette application appartient à l'entreprise du même nom __Entrevoisins__.

Les sources de l'application sont dans le répertoire __/Android/Entrevoisins__.

Le projet est développé en __java__ avec __Android Studio__.

## Mise en place
- Téléchargez le code du projet, de préférence en utilisant git clone.  
- Télécharger __Android Studio__ : <https://developer.android.com/studio>  
- Dans Android Studio, sélectionnez Fichier | Ouvrez ... et pointez sur le répertoire ./Android/Entrevoisins  

## Compilation et exécution
- Pour compiler le projet : Menu Build->Make project (Ctrl + F9)  
- Pour exécuter l’application : Menu Run->Run… (Alt+Maj+F10) puis sélectionner "app"  
- Pour exécuter les tests unitaires : Menu Run->Run… (Alt+Maj+F10) puis sélectionner "NeighbourServiceTest"  
- Pour exécuter les tests instrumentalisés : Menu Run->Run… (Alt+Maj+F10) puis sélectionner "NeighboursListTest"  

## Les tests unitaires
Des tests unitaires sont disponibles dans le répertoire /src/test/  
Ils utilisent junit4.  
Les méthodes disponibles sont :  
-  getAllNeighbours() : Vérifie que la liste de voisins se charge pleinement.  
- getFavoritesNeighbours() : Vérifie que la liste initiale des favoris est vide.  
- deleteNeighbour() : Test la suppression d’un voisin.  
- createOneNeighbour() : Test le constructeur de la classe Neighbour.  
- addOneNeighbour() : Test l’ajout d’un voisin dans la liste.  
- addOneFavoriteNeighbour() : Test l’ajout d’un nouveau voisin dans les favoris.  
- setNeighbourAsFavorite() : Test la mise en favoris d’un voisin.  
- findNeighbourById() : Test la procédure DummyNeighbourApiService findNeighbourById(long id).  

## Les tests instumentalisés
Des tests instrumentalisés sont disponibles dans le répertoire /src/androidTest/  
Ils utilisent junit et Espresso  
Les méthodes disponibles sont :  
- myNeighboursList_shouldNotBeEmpty() : Des données doivent s’afficher dans la listes des voisins.  
- myFavoriteNeighboursList_shouldBeEmpty() : La liste des favoris doit être vide.  
- myNeighboursList_deleteAction_shouldRemoveItem() : Test du bouton supprimer dans la liste des voisins.  
- myNeighboursList_clickOnItem_shouldOpenDetail() : Test l’ouverture de la fenêtre de détail d’un voisin.  
- myNeighboursList_clickOnFirstItem_shouldShowFirstName() : Test que l’ouverture de la fenêtre de détail d’un voisin affiche le bon voisin.  
- myNeighboursList_CountFavorites() : Test l’ajout d’un voisin en favoris.  
