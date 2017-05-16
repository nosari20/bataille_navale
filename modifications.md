## Ajouts :

Nous avons simplement ajouté une stratégie de tir pour l'ordinateur : il s'agit d'une stratégie de tir esthétique (pas à prendre au sérieux) qui dessine un coeur sur la mer et tir ensuite au hasard.
La stratégie de tir en croix déjà existante étant assez efficace, il nous aurait été difficile de faire beaucoup mieux, d'où notre choix d'une stratégie purement esthétique. 

Au niveau du code, nous avons juste ajouté la classe correspondante à la stratégie de tir : HeartStrategyComputer.java.
Nous avons ensuite modifié la stratégie de tir de l'ordinateur à la création d'une partie qui était mise en dur dans la méthode createGame de MainGui.java.

## Problèmes/Remarques :

* Le seul problème concernant directement l'ajout demandé est le fait que l'interface graphique ne permet pas de sélectionner une stratégie de tir pour l'ordinateur et c'est pour cette raison que nous avons dû remplacer la stratégie de tir par défaut qui était passé dans la méthode createGame de MainGui.java.
Ainsi, le jeu ne propose pas directement le choix d'une stratégie de tir pour l'ordinateur. Nous aurions pu ajouter nous-même ce choix à l'interface graphique mais la consigne étant de faire le moins de modifications possibles, nous avons choisis de mettre notre stratégie par défaut.

* Mis à part ce léger détail, il nous a été très facile de rajouter une stratégie.
