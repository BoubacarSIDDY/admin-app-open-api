# Documentation
Ce projet a pour but de mettre en place :
> Un projet Maven **OPEN API** qui sera utilisé pour la documentation de nos EndPoints et plus loin la génération de nos **dtos** sur le backend et de nos **models** sur le frontend

> Un projet **Spring Boot** pour le backend

> Un projet **Angular** pour le frontend

### 1. Projet OPEN API : admin-open-api
Pour notre projet Open API `admin-open-api` on créé un projet maven: dans notre cas c'est le projet **admin-open-api**
dans ce projet on va créér tous nos **DTO** avec des fichiers **.yml** qui seront ensuite utilisés dans notre application **Spring Boot** et **Angular**.
Structure de notre projet : 
comme vous le remarqué dans ce projet on n'a pas : `src/main/java/com/groupeisi` qui indique le package de base avec le triplet maven: groupID et ArtifactId, on l'a tout simplement supprimé
ensuite nous avons créé un dossier **resources/openapi/** et à l'intérieur nous avons la structure suivante:
* **interfaces** <br>
  Dans ce dossier nous avons le fichier `global-api.yml` qui represente la porte d'entrée principale pour accéder à l'ensemble des endpoints de notre API.
    ```apache
    openapi: "3.0.0"
    
    info:
      title: API de gestion des utilisateurs, roles et produits # ça sera utiliser pour les endPoint @RequestMapping("${openapi.aPIDeTest.base-path:/service/api}")
      version: "1.0"
      description: Documentation du microservices de la gestion des utilisateurs,roles et produits
    
    servers:
      - url: http://localhost:8080/backend-admin/api
    
    tags:
      - name: AppRoles
        description: AppRoles specific data.
      - name: AppUser
        description: App User specific data.
      - name: Produit
        description: Produit specific data.
    paths:
    
      ##############
      ## API AppRoles ##
      ##############
    
      /roles:
        $ref: '../components/api/roles.yml#/paths/~1roles' # ~1 => signifie slash(/)
      /roles/{id}:
        $ref: '../components/api/roles.yml#/paths/~1roles-reference'
    #
    #  ##############
    #  ## API AppUser ##
    #  ##############
    #
      /users:
        $ref: '../components/api/users.yml#/paths/~1users' # ~1 => signifie slash(/)
      /users/{id}:
        $ref: '../components/api/users.yml#/paths/~1users-reference'
    
      ##############
      ## API Produit ##
      ##############
    
      /produits:
        $ref: '../components/api/produits.yml#/paths/~1produits' # ~1 => signifie slash(/)
      /produits/{id}:
        $ref: '../components/api/produits.yml#/paths/~1produits-reference'
    ```
* **components**:
1. **model** <br>
      Dans ce dossier comme son nom l'indique nous avons nos objets **DTO** <br>
      Aperçu de l'objet ProduitDTO.yml
     ```apache
     type: object    # cet objet sera générée comme classe côté spring boot ProduitDTO et comme model .ts côté Angular
     properties:
       id:               # attribut : id
         type: integer   # integer
         description: Fonctionnel id of the classe
         format: int32   # int32 => int, int64 => Long
       name:             # attribut : name
         type: string    # type : String
         description: the product's name
       qtyStock:         # attribut : qtyStock
         type: number    # type : number (float)
         description: the stock quantity
       user:
         description: The user who added this product
         $ref: '../users/UserDTO.yml'
     ```
2. **api** : <br>
   Dans ce dossier nous avons les différents endpoints pour chaque composant ***produit***,***role***,***user*** avec les différents VERBS : **GET**,**POST**, **PUT** ...<br>
   Aperçu du fichier produits.yml par exemple
    ```apache
    openapi: "3.0.0"
    info:
      title: API Produit 
      version: "1.0"
      description: Produit API # une briève description de l'endpoint
    
    servers:
      - url: http://localhost:8080/backend-admin/api # on met l'url de notre backend
    
    tags:
      - name: Produit
        description: Produit specific data.
    
    paths:
      /produits:    # premier endPoint : /produits
        get:        # type de réquête get
          tags:
            - Produit
          summary: Get All Products    # un resumé de ce que fait cet endPoint une fois appelée
          operationId: getAllProducts  # **operationId** spécifie le nom de la méthode qui sera générée : getAllProducts
          parameters:                  # les paramètres à fournir en appelant cet endPoint
            - name: currentPage        # currentPage qui servira pour la pagination :
              in: query                  # ça sera passer directement au niveau de l'url => /produits?currentPage=1
              required: true             # c'est un paramètre qui est obligatoire
              schema:
                type: integer            # de type integer
                format: int32            # int
            - name: sizePage           # sizePage : paramètre
              in: query                  # doit être passé sur l'url => /produits?currentPage=1&sizePage=10
              required: true             # c'est un paramètre qui est obligatoire
              schema:
                type: integer            # de type integer
                format: int32            # int
                format: int32
          responses:                    # format de réponses qui seront renvoyées
            '200':                  
              description: SUCCESS
              content:
                'application/json':
                  schema:
                    $ref: '../model/produits/ProduitsResultListDTO.yml' # comme on doit renvoyer une liste de produits on le mappe avec ProduitsResultListDTO.yml
            '404':
              description: NOT FOUND
            '400':
              description: BAD REQUEST
        post:       # type de réquête post
          tags:
            - Produit
          summary: Create a new product
          operationId: createProduit    # methode qui sera générée : createProduit
          requestBody:                  # les paramètres de la requêtes sont obligatoires
            required: true
            content:
              'application/json':
                schema:
                  $ref: '../model/produits/ProduitDTO.yml'  # le type doit être de ProduitDTO
          responses:
            '201':
              description: CREATED
              content:
                'application/json':
                  schema:
                    $ref: '../model/produits/ProduitDTO.yml'
            '400':
              description: BAD REQUEST
    
      /produits-reference:    # endPoint /produits/{id}
        get:        # type de requête get
          tags:
            - Produit
          summary: Retrieve a product
          operationId: getProduit       # méthode getProduit
          parameters:                   # parametres
            - name: id                      # {id} 
              in: path                      # sur le path => /produits/1
              required: true                # obligatoire
              schema:
                type: integer               # de type integer
                format: int32               # int
          responses:
            '200':
              description: SUCCESS
              content:
                'application/json':
                  schema:
                    $ref: '../model/produits/ProduitDTO.yml'  # renvoi un produit ProduitDTO
            '404':
              description: NOT FOUND
            '400':
              description: BAD REQUEST
        put:        # type de requête put 
          tags:
            - Produit
          summary: Update a product
          operationId: updateProduit    # méthode : updateProduit
          parameters:                   # parametres
            - name: id                      # {id}
              in: path                      # à transmettre sur le lien : /produits/5
              required: true                # obligatoire
              schema:
                type: integer               # integer
                format: int32               # int
          requestBody:
            content:
              application/json:
                schema:
                  $ref: '../model/produits/ProduitDTO.yml'
          responses:
            '200':
              description: SUCCESS
              content:
                application/json:
                  schema:
                    $ref: '../model/produits/ProduitDTO.yml'
            '404':
              description: NOT FOUND
            '400':
              description: BAD REQUEST
    
      /produitsByUser:          # récupération des produits par utilisateur
        get:
          tags:
            - Produit
          summary: Get All Products by user
          operationId: getProductsByUser
          parameters:
            - name: currentPage
              in: query
              required: true
              schema:
                type: integer
                format: int32
            - name: sizePage
              in: query
              required: true
              schema:
                type: integer
                format: int32
          responses:
            '200':
              description: SUCCESS
              content:
                'application/json':
                  schema:
                    $ref: '../model/produits/ProduitResultListByUserDTO.yml'
            '404':
              description: NOT FOUND
            '400':
              description: BAD REQUEST
    ```

### 2. Projet Spring Boot : backend-admin
Notre projet spring boot `backend-admin` est structuré comme suit:
* **entities** : pour nos entités rélationnelles : `Produit`,`Role`,`User`
* **exceptions** : pour la gestion des exceptions
* **mapping** : pour mapper les dto et les entités vice versa
* **repositories** : pour la persistence des données
* **services** : pour la logique métier
* **web** : pour nos controllers

***NB*** : comme vous le remarquez on n'a pas de package DTO car c'est le projet **OPEN API** qui s'en chargera de cette tâche pour générer les **DTO**<br>
Ci-dessous l'inclusion du projet `admin-open-api` comme dependence dans le fichier `pom.xml` de notre projet back
``` apacheconf  
######## OpenApi - Génération des API et des models #####
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
    <version>${openapi-generator.version}</version>
    <dependencies>
        <dependency>                                    # la dependence admin-open-api
            <groupId>com.groupeisi</groupId>            # le groupId du projet 
            <artifactId>admin-open-api</artifactId>     # le nom du projet
            <classifier>api</classifier>
            <version>${openapi.version}</version>       # la version
        </dependency>
    </dependencies>
    # Génération des models (true) et des api (true)
    <executions>
        <execution>
            <id>generate-api</id>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <generatorName>spring</generatorName> # generator spring
                <inputSpec>${openapi.dir}/interfaces/global-api.yml</inputSpec>
                <generateModels>true</generateModels>        # Génération des models
                <generateApis>true</generateApis>            # Génération des apis
                <generateSupportingFiles>true</generateSupportingFiles>
                <output>target/generated-sources</output>
                <configOptions>
                    <sourceFolder>src/gen/main/java</sourceFolder>
                    <java8>true</java8>
                    <modelPackage>com.groupeisi.generated.model</modelPackage>   # le dossier où sera généré les models
                    <apiPackage>com.groupeisi.generated.api</apiPackage>         # le dossier où ser a généré les api
                    <delegatePattern>true</delegatePattern>
                    <unhandledException>true</unhandledException>
                    <useTags>true</useTags>
                </configOptions>
                <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>
            </configuration>
        </execution>
    </executions>
</plugin>
```
### 3. Projet Angular : admin-fronted
