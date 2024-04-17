# Rendu de mi-parcours

[Repo Github](https://github.com/YacineTrousselle/Archi-distribue)

![Archi mi-parcours](./archi-mi-parcours.png)

- Application cliente en Android Java
- Le client Android envoie des messages qui seront ecrits dans la bonne queue par l'API.
- Une queue par service, les services consument les messages lorsqu'ils sont disponibles.
- Le client peut communiquer directement avec le serveur Ice (pas sur pour le moment)

Si c'est trop complique a implementer alors je ferais des services Web avec une API pour l'ASR et une autre pour le NLP.
