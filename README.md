# bataille_navale


## Build, Run and Test
### Fonctionnalités du jeu

![alt text](https://github.com/nosari20/bataille_navale/blob/master/screenshots/1.PNG?raw=true)

* 2 Modes :  Le mode "normal" qui permet de faire des tirs orbitale c'est à dire qui permet d'effectuer seulement un tir dans le camp adverse 
			   et mode "choix tirreur" qui permet de choisir le bateau tirreur. 

* Il est possible de créer une nouvelle partie à partir du l'interface de lancement de jeu.

* Charger une partie déjà existante, pour l'instant le seul format proposé est le XML. 

* Sauvegarder votre partie pour la reprendre plus tard. 

* Epoque sont disponible dans le jeu, L'époque du XIX siécle ou L'époque ancienne, L'époque du XX siécle ou L'époque moderne et L'époque du XXI siécle ou L'époque future. 

* L'interface de jeu propose de superbe animation hyper dynamique et hyper réalistique afin d'offrir une experience optimale pour nos chanceux utilisateurs (Attention Effet speciaux super realiste essayer de ne pas oubliez que vous êtes dans un jeu) . 

* Positionner votre flotte comme bon vous le semble. 

* Visez bien afin de détruire votre adversaire.

* JEUX PEGI 18 ( attention âme sensible s'abstenir, il y a des explosions )
![alt text](https://github.com/nosari20/bataille_navale/blob/master/screenshots/2.PNG?raw=true)
### Build 
```
$ ant build
```

### Generate Runable JAR
```
$ ant build:jar
```

### Run
```
$ ant run
```

### Run tests
```
$ ant test
```

### Generate HTML tests reports
```
$ ant test:report
````
