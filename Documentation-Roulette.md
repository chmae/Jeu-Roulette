# Jeu de la Roulette - Documentation

## Indication
Lorsque l'utilisateur se connecte ou s'inscrit ne prêtait pas attention à la console
de l'IDE, ces erreurs là sont juste des "conseils" pour améliorer hibernate plus précisément dans la base de données, mais n'empêche absolument pas la connexion à la
Base de données. 

## Introduction

Bienvenue dans le jeu de roulette ! Cette application vous offre une expérience de jeu immersive avec diverses fonctionnalités. Voici un aperçu des principales fonctionnalités du jeu.

## Vue d'accueil

Au lancement du jeu, vous êtes accueilli par la Vue d'Accueil. Les fonctionnalités disponibles comprennent :

- **Bouton Jouer :** Lance le jeu de roulette.
- **Bouton Quitter :** Permet de quitter l'application.
- **Inscription et Connexion :** Possibilité de s'inscrire ou de se connecter (si déjà inscrit).
- **Logo -18 ans :** Affiche un logo pour signaler que le jeu est réservé aux personnes de 18 ans et plus.
- **Bouton Info :** Accède aux règles du jeu avec un lien PDF.
- **Mode de jeu :** Choix entre le mode solo ou multijoueur.

## Interface de Jeu

Lorsque le jeu démarre, une interface de jeu complète s'affiche :

- **Bouton Quitter :** Permet de revenir à la Vue d'Accueil uniquement si l'utilisateur n'est pas connecté.
- **Bouton Paramètres :** Change la langue (anglais/français), permet de modifier le prénom et le solde (uniquement si l'utilisateur est connecté), et offre la possibilité de se déconnecter (uniquement si l'utilisateur est connecté).
- **Bouton Musique :** Il se situe dans le Bouton Paramètre, il permet de diminuer le son, de couper le son et d'ajuster le son à votre guise.
- **Statistiques :** Affiche le nombre de fois que chaque couleur (noir, rouge, vert) est tombée, ainsi que les trois derniers numéros avec leur pourcentage se situe sur la Vue de Droite.
- **Nombres Tombés :** Affiche les cinq derniers numéros tombés qui se situe sur la Vue de Gauche.
- **Roulette et Plateau :** La roulette tourne et le plateau propose des mises avec des effets de survol.
- **Joueur Courant :** Affiche le solde actuel, la mise en cours, et la mise totale du joueur.
- **Jetons et Validation :** Permet au joueur de placer ses jetons sur le plateau et de valider sa mise avec le bouton de validation.
- **Popup Résultat :** Affiche le résultat du tour (gagné ou perdu) après validation.

## Inscription et Connexion

Les joueurs peuvent s'inscrire en fournissant leurs informations et leur solde. Une fois inscrits, une popup de connexion s'affiche, leur permettant de se connecter et d'accéder aux fonctionnalités complètes du jeu.

### Modification des Informations

Les joueurs connectés peuvent modifier leur prénom et leur solde directement dans l'interface (grâce au bouton Paramètre). Ces modifications sont enregistrées instantanément dans la base de données, assurant une mise à jour immédiate.
- **Déconnexion :** Permet au joueur connecté de se déconnecter, fermant ainsi la fenêtre principale.

## Base de Données

Les données des joueurs, y compris le prénom, le solde, et les résultats des jeux, sont stockées dans une base de données. Les mises à jour sont automatiquement enregistrées. Les informations sont sécurisés et les mots de passes sont encryptés avec un hachage SHA-256.

Profitez de l'excitation du jeu de roulette ! 🎰✨
