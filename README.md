# Ecommerce Sport Shop - Projet Janvier

Application E-Commerce de vente d'articles de sport, développée avec Spring Boot (Java 17) et PostgreSQL.

## Prérequis

*   **Java 17** ou supérieur
*   **Maven**
*   **PostgreSQL** (Service actif sur le port 5432)

## Installation et Configuration

### 1. Base de données PostgreSQL

#### Configuration
L'application est configurée par défaut avec les identifiants suivants (fichier `src/main/resources/application.yml`) :

*   **Base de données** : `janvier_db`
*   **Utilisateur** : `postgres`
*   **Mot de passe** : `Ilies2004_` ⚠️ **Important :** Si votre mot de passe local est différent, modifiez-le dans `src/main/resources/application.yml` avant de lancer l'application.

#### Initialisation
Il faut créer la base de données vide. Les tables seront créées automatiquement.

**Option A (Mac/Linux) :**
```bash
chmod +x init-db.sh
./init-db.sh
```

**Option B (Windows / Manuel) :**
Créez simplement une base de données vide nommée `janvier_db` via pgAdmin ou la commande SQL :
```sql
CREATE DATABASE janvier_db;
```

> **Note :** Le schéma de la base de données (`src/main/resources/schema.sql`) est exécuté automatiquement au démarrage de l'application pour créer les tables et insérer les données de test.

### 2. Lancement de l'application

Ouvrez un terminal à la racine du projet et lancez :

```bash
mvn spring-boot:run
```

Attendez que l'application démarre. Elle sera accessible à l'adresse : [http://localhost:8082/janvier](http://localhost:8082/janvier)

## Utilisation

### Comptes de test
Les utilisateurs suivants sont pré-configurés avec le mot de passe : `password`

*   **Admin** : `admin` (Rôles: ADMIN, USER)
*   **Clients** : `user1`, `user2` (Rôles: USER)

### Pages principales
*   **Accueil** : `/`
*   **Catalogue** : `/produits`
*   **A propos** : `/a-propos`
*   **Connexion** : `/connexion`

## Dépannage
*   **Erreur de connexion JDBC** : Vérifiez que PostgreSQL tourne bien et que le mot de passe dans `application.yml` correspond au vôtre.
*   **Port déjà utilisé** : Si le port 8082 est pris, changez `server.port` dans `application.yml`.
