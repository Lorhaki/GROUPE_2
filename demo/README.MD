## Notre Sujet
Nous avons voulu représenter dans une API des éditeurs de jeux vidéos , et deux jeux vidéos dans une seconde APi.

Un édtieur de jeux est représenté par un "Nom" un "Id" ainsi qu'une lsite de jeux vidéo "LjV" qui ne sera pas reelement 
utilisé au cors de ce projet. Nous avons préféré préviligier les choses qui marches.

Et enfin un jeux-vidéo représenté par son "nom", le nom de l'editeur qui l'a qui l'a créé "nomEdi" et son "Id"

## En quoi consite le projet ? 
Notre projet comme il est demandé dans le sujet posséde un back et et un consummer. Cependant notre consumer est un 
second back nommé "jeux-service" celui-ci a pour but d'interagir avec le premier Back nommé 
"editeur-service" par l'intermédiare de méthode GET et POST.


## SWAGGER editeur-service
Il est possbile d'acceder à ce swagger en utilisant l'URL suivante :" http://localhost:8080/swagger-ui/index.html#/ "

Dans ce swagger il est possible d'uitliser l'ensemble des méthodes GET, POST, UPDATE et DELETE  pour un éditeur.



## SWAGGER jeux-service
Il est possbile d'acceder à ce swagger en utilisant l'URL suivante :" http://localhost:8081/swagger-ui/index.html#/ "