# TP 25 – Microservices, Docker Compose et Consul

Les services Spring Boot Client, Voiture et Gateway reprennent le code de [Lab23C2](https://github.com/medhamza47/Lab23C2) avec les noms Consul, les ports et les bases MySQL demandés au TP 25.

## Construire et démarrer

Docker et l’extension Docker Compose sont nécessaires. Maven et Java 17 sont fournis par les images de construction. Chaque Dockerfile compile le projet dans une image Maven, puis copie le JAR depuis ce premier stage dans une image Java d’exécution.

```bash
docker --version
docker compose version
docker build -t client-service:1.0 ./clientService
docker compose up -d --build
docker compose ps
```

## Vérifier les services

| Composant | Accès local | Résultat attendu |
| --- | --- | --- |
| Consul | http://localhost:8500/ | `gateway`, `service-client` et `service-voiture` dans **Services**, à l’état `passing` |
| phpMyAdmin | http://localhost:8081/ | `Micro_ClientDB` et `Micro_VoitureDB` |
| Gateway | http://localhost:8888/actuator/health | État `UP` |
| Client | http://localhost:8088/api/client | Liste des clients |
| Voiture | http://localhost:8089/api/car | Liste des voitures, vide au premier démarrage |
| MySQL | `127.0.0.1:3306` | Les deux schémas dans le même serveur |

Se connecter à phpMyAdmin avec `root` et le mot de passe de démonstration `root`. Pour la première initialisation du volume, le mot de passe est configurable via `MYSQL_ROOT_PASSWORD` dans l’environnement ou dans un fichier `.env` local. Les ports sont publiés sur l’interface de boucle locale.

`mysql` et `consul` sont résolus par le DNS du réseau Compose. Dans un conteneur, `localhost` désigne ce conteneur. Depuis la machine hôte, `localhost` permet d’atteindre les ports publiés. Le Gateway découvre ses routes via Consul et Voiture retrouve Client avec le nom `service-client`.

```bash
curl -fsS http://localhost:8888/service-client/api/client
curl -fsS http://localhost:8888/service-voiture/api/car
```

## Mini-défis et dépannage

```bash
docker compose restart client-service
docker compose logs --tail=50 client-service consul
docker compose logs -f gateway-service
```

Après le redémarrage de Client, observer sa réinscription et son retour à l’état `passing` dans Consul. Dans les logs du Gateway, repérer le démarrage de Spring Boot et l’inscription auprès de Consul. Si un port est déjà occupé, libérer le port ou modifier le port hôte dans `docker-compose.yml`. Si Maven ne trouve pas le POM, vérifier que le contexte de construction est le dossier du microservice.

```bash
docker compose down
```

MySQL conserve les données dans le volume `mysql-data`.
