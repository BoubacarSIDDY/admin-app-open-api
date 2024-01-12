# Documentation
Ce projet a pour but de mettre en place : 
> Un projet **Spring Boot** pour le backend

> Un projet **Angular** pour le frontend 

> ET un projet Maven **OPEN API** qui sera utilisé pour la documentation de nos EndPoints et plus loin la génération de nos **dtos** sur le backend et de nos **models** pour le frontend

### 1. Projet Spring Boot
Notre projet spring boot est composé de ces packages:
* **entities** : pour les entités rélationnelles
* **exceptions** : pour la gestion des exceptions
* **mapping** : pour mapper les dto et les entités
* **repositories** : pour la persistence des données
* **services** : pour gérer le métier
* **web** : pour les Rest controllers
  
***NB*** : on n'a pas de package DTO car c'est le projet **OPEN API** qui s'en chargera

### 2. Projet OPEN API
Pour notre projet Open API on créé un projet maven: dans notre cas c'est le projet **admin-open-api**
Dans ce projet on va créér tous nos **DTO** avec des fichiers **.yml** qui seront ensuite utilisés dans notre application **Spring Boot**
Ci-dessous un exemple de notre produit.yml
