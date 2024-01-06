## Notre Sujet
Nous avons voulu représenter dans une API des éditeurs de jeux vidéos, et les dit jeux vidéos dans une seconde APi.

Un édtieur de jeux est représenté par un "Nom" un "Id" ainsi qu'une lsite de jeux vidéo "LjV" qui ne sera pas reelement 
utilisé au cours de ce projet. Nous avons préféré préviligier les choses qui marches. Il est aussi représenté par une desciption.

Et enfin un jeu-vidéo représenté par son "nom", le nom de l'editeur qui l'a créé, "nomEdi", et son "Id"

## En quoi consite le projet ?
Notre projet, comme il est demandé dans le sujet, possède un back et un consummer qui est pour nous aussi un back. Celui avec un rôle de consumer est le serice de jeu-vidéo
appelé "jeux-service".
Celui-ci a pour but d'interagir avec le premier Back nommé "editeur-service" par l'intermédiare des méthodes GET et POST utilisé lors de la création de l'éditeur ou dans une méthode s^écifique qui récupère tous les éditeurs.


## SWAGGER editeur-service
Il est possible d'accéder à ce swagger en utilisant l'URL suivante :" http://localhost:8080/swagger-ui/index.html#/ "

Dans ce swagger il est possible d'utiliser l'ensemble des méthodes GET, POST, UPDATE et DELETE pour un éditeur.

<b>POST</b> : Permet de créer un éditeur en indiquant son nom et sa description. Il n'est pas possible de créer un autre utilisateur avec le meme nom , dans ce cas-là la requête RENVOIE Error 500.

<b>GET</b> : notre swagger possède plusieurs méthodes GET. La premiére permet de récupérer un éditeur en indiquant son ID. La seconde permet de récupérer l'ensemble des éditeurs créés depuis le départ.
Enfin la troisième permet de retrouver un utilisateur en utilisant son Nom.

<b>PUT</b> : Permet de modifier un éditeur en indiquant son ID. En entrée, c'est le nom et la description qui sont à modifier.

<b>DELETE</b> : Permet de supprimer un éditeur en indiquant l'id de celui-ci.

## SWAGGER jeux-service
Il est possible d'accéder à ce swagger en utilisant l'URL suivante :" http://localhost:8081/swagger-ui/index.html#/ "

On peut considérer ce jeux-service comme étant le CONSUMER de notre premier back. Celui-ci va pouvoir GET tous led éditeurs ainsi que POST au moment de la création de jeux.

<b>POST</b> : Permet de créer un jeu vidéo en indiquant son nom et le nom de son éditeur. REMARQUE: Lorsqu'un jeu est créé , si le nom de l'éditeur n'existe pas parmi l'ensemble des éditeurs,
on va alors faire appel à la méthode POST de l'éditeur avec le POST de jeux.

<b>GET</b> : Le premier GET permet de récupérer un jeu vidéo en indiquant son ID. Le second GET permet de récupérer l'ensemble des éditeurs présents dans la base d'éditeurs.


## Parcours fonctionnel
### Lancement du programme
Une fois l'application récupérée, il suffit de se mettre dans le repertoire démo avec la console puis d'utiliser la commande docker-compose up.

### Parcours éditeur, tester le back
Utiliser l'URL http://localhost:8080/swagger-ui/index.html#/ pour accéder au swagger. 
Ici, commencez par le post et utiliser "Try it out" pour remplir un nouvel éditeur. Ici vous pouvez changer le nom et la description de l'élément que vous créez avant de le créer, son id sera attribuer par le programme.
Si vous essayez de recréer un éditeur avec un nom déjà utiliser, il vous sera retourner un code d'erreur.
Ensuite, nous pouvons vérifier la liste complète des éditeurs avec get/editeurs pour avoir l'ensemble des éditeurs créés.
Avec les deux autres get/editeurs, nous pouvons vérifier qu'un éditeur avec un certain id ou un certain nom existe, cela se vérifie toujours avec "try it out".
Ensuite, nous pouvons modifier l'éditeur créer en utilisant put. Pour cela remplir avec l'id de l'éditeur déjà créé dans la partie prévue puis écrire le nouveau nom et la nouvelle description.
Enfin avec l'id de l'éditeur, nous pouvons le supprimer avec la méthode delete.

### Parcours jeu
Ici, il y a moins de méthode, le but étant d'utiliser la ressource éditeur-service.
Tout d'abord, vous pouvez utiliser la méthode "get/jeux/jeux-editeurs" pour voir les éditeurs déjà créer. 
Ensuite, nous pouvons créer un jeu avec la méthode post qui renverra le jeu créé en réponse si l'éditeur n'existe pas, une erreur sinon. 
Cette erreur n'est pas dû à l'échec de création d'un nouveau jeu mais à l'échec de création d'éditeur car il en existe déjà un avec ce nom.
Ensuite get/jeux permet d'avoir tous les jeux et get/jeux/jeuxid permet de voir un jeu en utilisant son id.
Nous pouvons voir avec cela que bien que la création des jeux puissent renvoyer 500, ces jeux sont bien créés.

### A changer pour utiliser en local
Les indications données précédemment permettent de créer des conteneurs pour les deux services avec un conteneur pour la base de données.
Cependant, il est possible de tester de run les 2 services. Pour cela, il faut tout d'abord lancer une base de donnée MongoDB en docker connecté sur le port 27017. 
Dans le code, allez dans jeux-service/src/main/java/service/JeuService.java, décommentez les lignes 46 et 60 puis mettez en commentaires les lignes 47 et 59.
Ensuite, il suffit de run les services puis de passer aux étapes de parcours expliquer précédemment. 

## Ce qu'il nous manque
Nous n'avons plus retrouver comment utiliser git action et nous avions peur que notre programme ne fonctionn plus si nous le mettions mal en place.
Le reste devriat être effectif bien qu'il manquerait quelques gestions d'erreurs pour être plus clair.
