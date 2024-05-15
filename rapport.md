# Rendu de mi-parcours

[Repo Github](https://github.com/YacineTrousselle/Archi-distribue)

![Archi mi-parcours](./archi-mi-parcours.png)

- Application cliente en Android Java
- Le client Android envoie des messages qui seront ecrits dans la bonne queue par l'API.
- Une queue par service, les services consument les messages lorsqu'ils sont disponibles.
- Le client peut communiquer directement avec le serveur Ice (pas sur pour le moment)

Si c'est trop complique a implementer alors je ferais des services Web avec une API pour l'ASR et une autre pour le NLP.

# Rendu final

Finalement j'ai fait des services Web. Pourquoi ? JMS avec Android c'est chiant, je vais pas brancher l'application sur une queue de message.  
Du coup j'ai une api pour l'ASR et une api pour le NLP.  
L'appli Android peut communiquer avec le serveur Ice et effectuer les fonctionnalites de base tel que rechercher une musique, la jouer ou uploader une musique.  

## Schema de l'application



# Gestion optimisee des serveurs dans le cadre d'une architecture distribuee

> L'architecture distribuée ou l'informatique distribuée désigne un système d'information ou un réseau pour lequel l'ensemble des ressources disponibles ne se trouvent pas au même endroit ou sur la même machine. Ce concept, dont une version peut être une combinaison de transmissions du type client-serveur, s'oppose à celui d'informatique centralisée. 
\- Wikipedia, Architecture distribuée  

La gestion des serveurs est essentielle pour garantir que l'application reste active et puisse repondre aux demandes des clients.  
Dans le cadre d'une architecture distribuee, l'application est divisee en plusieurs composants qui peuvent fonctionner sur des machines et des environnements differentes, ces services ont ensuite besoin de pouvoir communiquer entre eux.  
