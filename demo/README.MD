## Notre Sujet
Nous avons voulu représenter dans une API des éditeurs de jeux vidéos , et deux jeux vidéos dans une seconde APi.

Un édtieur de jeux est représenté par un "Nom" un "Id" ainsi qu'une lsite de jeux vidéo "LjV" qui ne sera pas reelement
utilisé au cors de ce projet. Nous avons préféré préviligier les choses qui marches. Il est aussi représenté par une desciption.

Et enfin un jeux-vidéo représenté par son "nom", le nom de l'editeur qui l'a qui l'a créé "nomEdi" et son "Id"

## En quoi consite le projet ?
Notre projet comme il est demandé dans le sujet posséde un back et et un consummer. Cependant notre consumer est un
second back nommé "jeux-service" celui-ci a pour but d'interagir avec le premier Back nommé
"editeur-service" par l'intermédiare de méthode GET et POST.


## SWAGGER editeur-service
Il est possbile d'acceder à ce swagger en utilisant l'URL suivante :" http://localhost:8080/swagger-ui/index.html#/ "

Dans ce swagger il est possible d'uitliser l'ensemble des méthodes GET, POST, UPDATE et DELETE  pour un éditeur.

<b>POST</b> : Permet de creer un éditeur en indiquant son nom et sa description. Il n'est pas possible de creer un autre utilisateur avec le meme nom , dans ce cas là RENVOIE Error 500.

<b>GET</b> : notre swagger posséde plusieurs méthode GET. La premiére pemrte de recupérer un editeur en indiquant son ID.La seconde permet de recuperer l'ensemble des éditeurs crées depuis le départ
. La troisiéme permet de retrouver un utilisateur en utilisant son Nom.

<b>PUT</b> : Permet de modifier un éditeur en indiquant son ID. En entré demandé le nom et la description à modifier.

<b>DELETE</b> : Permet de supprimer un utilsateur en indiquant l'id de celui ci.

## SWAGGER jeux-service
Il est possbile d'acceder à ce swagger en utilisant l'URL suivante :" http://localhost:8081/swagger-ui/index.html#/ "

On peut considérer ce jeux-service coomme étant le CONSUMER de notre premier back. Celui ci va pouvoir GET dans eiteur registre ainsi que POST dans editeur registre.

<b>POST</b> : Permet de créer un jeux vidéo en indiquant sont nom et le nom de son éditeur. REMARQUE: Lorsqu'un jeux est crée , si le nom de l'éditeur n'existe pas par mi l'ensemble des éditeurs ,
on va alors faire appel à la méthode POST de l'editeur avec le POST de jeux.

<b>GET</b> : Le premier GET permet de recupérer un jeux vidéo en indiquant son ID. Le seconde GET permet de recuperer l'ensemble des éditeurs présents dans la base d'éditeurs.


## Parcours fonctionnel