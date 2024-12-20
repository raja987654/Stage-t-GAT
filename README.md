Application de Calcul des Assurances Véhicules


Il s'agit d'une application web développée avec Spring Boot et Angular qui fournit un service de calcul des primes d'assurances. Elle permet aux utilisateurs de saisir des informations sur leur véhicule et leur historique de conduite pour obtenir des estimations des primes d'assurance annuelles et mensuelles.


Visualisation des devis précédemment créés via leur identifiant de référence.
Impression d'un devis généré.
Actualisation de la page sans perdre le devis généré.
Accès direct aux devis via une URL.
Gestion des erreurs avec affichage de messages d'erreur appropriés.
Liens pour contacter des spécialistes ou le support pour des demandes générales.
Horodatage indiquant la date et l'heure de la création du devis.
Boutons permettant de vous rediriger vers l'endroit approprié sur chaque page.
Pages d'accueil, de contact et de non trouvé.
Défis
Le principal défi auquel j'ai été confronté était de déterminer la structure de base des entités Driver et Quote. Au départ, j'ai passé beaucoup de temps à expérimenter avec l'idée d'utiliser une relation un-à-plusieurs entre les conducteurs et les devis. Ma principale raison était que cela permettrait d'accéder à tous les devis réalisés par un conducteur. Bien que cela semblait intéressant en tant que fonctionnalité, j'ai finalement décidé de ne pas aller dans cette direction. L'utilisateur pourrait être confus de devoir fournir à la fois son numéro de permis et son historique de conduite. J'ai donc opté pour une relation légèrement indépendante entre les conducteurs et les devis. Si l'utilisateur peut rechercher ses devis par identifiant de référence, cela remplit en grande partie le même objectif. J'ai trouvé que cela offrait le meilleur compromis entre une expérience utilisateur fluide et la protection des informations sensibles.

Un autre défi a été l'organisation des nombreux champs de saisie sur une seule page. Étant habitué à utiliser Angular Material, j'ai décidé de travailler avec Bootstrap pour ce projet. Bien que Bootstrap soit excellent, j'ai constaté qu'il n'était pas idéal pour concevoir de grands formulaires. Ma solution a donc été de mieux utiliser l'espace en regroupant les champs en catégories afin d'organiser plus efficacement la longue liste d'entrées.
#   S t a g e - t - 
 
 #   S t a g e - t - G A T 
 
 
