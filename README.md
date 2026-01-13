# Projet EjBank

EjBank est une application web simulant un système bancaire. Elle fournit des APIs pour la gestion des comptes, des transactions et des utilisateurs, en suivant une approche structurée basée sur les DAO, DTO et payloads.

## Table des matières
- [Technologies utilisées](#technologies-utilisées)
- [Architecture du projet](#architecture-du-projet)
- [Fonctionnalités](#fonctionnalités)
- [Endpoints disponibles](#endpoints-disponibles)
- [Modèle de base de données](#modèle-de-base-de-données)

---

## Technologies utilisées

- **Java EE** : Pour la logique métier et les couches de services.
- **APIs RESTful** : Pour la communication client-serveur.
- **Docker** : Pour la containerisation et le déploiement.
- **GitLab** : Contrôle de version et CI/CD.
- **IntelliJ IDEA** : Environnement de développement.

---

## Architecture du projet

Le projet suit une architecture en couches :

1. **Couche DAO** :
   - Gère les interactions avec la base de données via des entités JPA.
   - Exemple : `EjbankAccount`, `EjbankTransaction`, `EjbankUser`.

2. **Couche DTO** :
   - Transfère les données entre les services et les APIs.
   - Exemple : `AccountData`, `TransactionPreviewData`.

3. **Couche Payload** :
   - Définit les structures des requêtes et des réponses pour les APIs.
   - Exemple : `TransactionPreviewRequestPayload`, `UserPayload`.

4. **Couche Service** :
   - Contient la logique métier et les validations.
   - Exemple : `AccountService`, `TransactionService`.

5. **Couche API** :
   - Expose des endpoints REST pour les interactions avec les clients.
   - Exemple : `AccountsApi`, `TransactionApi`.

---

## Fonctionnalités

- **Gestion des comptes** :
  - Récupération des comptes d’un utilisateur, des comptes liés, et des détails de compte.
  - APIs associées : `/accounts/{user_id}`, `/account/{account_id}/{user_id}`.

- **Gestion des transactions** :
  - Prévisualisation, application et validation des transactions.
  - APIs associées : `/transaction/preview`, `/transaction/apply`, `/transaction/validation`.

- **Gestion des utilisateurs** :
  - Récupération des informations d’un utilisateur.
  - API associée : `/user/{user_id}`.

- **Suivi du serveur** :
  - Vérification du statut du serveur.
  - API associée : `/server/status`.

---

## Endpoints disponibles

### APIs Comptes
- **GET** `/accounts/{user_id}` : Récupérer tous les comptes d’un utilisateur.
- **GET** `/accounts/attached/{user_id}` : Récupérer les comptes gérés par un conseiller.
- **GET** `/accounts/all/{user_id}` : Récupérer tous les comptes (client et conseiller).

### APIs Détails de compte
- **GET** `/account/{account_id}/{user_id}` : Récupérer les détails d’un compte, y compris le propriétaire, le conseiller, le taux, et le solde.

### APIs Transactions
- **GET** `/transaction/list/{account_id}/{offset}/{user_id}` : Lister les transactions pour un compte.
- **POST** `/transaction/preview` : Prévisualiser une transaction avec impact sur le solde.
- **POST** `/transaction/apply` : Appliquer une transaction entre comptes.
- **POST** `/transaction/validation` : Approuver ou rejeter une transaction.
- **GET** `/transaction/validation/notification/{user_id}` : Compter les validations en attente pour un conseiller.

### APIs Utilisateurs
- **GET** `/user/{user_id}` : Récupérer les informations d’un utilisateur.

### API Serveur
- **GET** `/server/status` : Vérifier le statut du serveur.

---

## Modèle de base de données

Le schéma de la base de données inclut les tables suivantes :

1. **`ejbank_user`** :
   - Entité de base pour les utilisateurs (clients, conseillers).
   - Champs : `id`, `login`, `password`, `email`, `firstname`, `lastname`, `type`.

2. **`ejbank_account`** :
   - Représente les comptes bancaires.
   - Champs : `id`, `customer_id`, `account_type_id`, `balance`.

3. **`ejbank_transaction`** :
   - Représente les transactions entre comptes.
   - Champs : `id`, `account_id_from`, `account_id_to`, `author`, `amount`, `comment`, `applied`, `date`.

4. **`ejbank_account_type`** :
   - Définit les types de comptes (par exemple, épargne, courant).
   - Champs : `id`, `name`, `rate`, `overdraft`.

---
