# Rabbit Worker Example

This was built as a proof of concept with the requirements:
* Java + Spring
* Listen for Jobs posted to a RabbitMQ Queue
* Take and works 1 Job at a time
* Build to a Docker Container
* Can scale to multiple workers running in parallel
* Should NOT prefetch Jobs but only take a new Job once the current one is complete
* REST API for checking status / metrics

## Prerequisites

* Docker-CE
* Java JDK

## Building

```bash
./mvn package
```

This produces a docker image called rabbit-worker:latest and adds it to the local Docker image list.

## Running

### Docker-compose

If you have docker-compose installed:

```bash
docker-compose -f src/main/docker/app.yml up
```

This will start RabbitMQ and 3 rabbit-workers with nice, readable names.

### Docker Swarm

If you have docker-compose installed:

```bash
docker swarm init
docker stack deploy -c src/main/docker/app-swarm.yml myApp
```

This will start RabbitMQ and 3 rabbit-workers in swarm mode.  Scaling is easier in swarm mode but container names are random.

### Single instance with Maven

First start RabbitMQ then run an instance of the application either in an IDE or with Maven as shown below:

```bash
docker run -d -p 5672:5672 -p 15672:15672 rabbitmq:management
./mvn spring-boot:run
```

## Testing

With the stack up and running, use curl to post Jobs or run the curl-test script:

```bash
./curl-test.sh
```

You can watch the queue fill up and get worked down from the RabbitMQ Management UI at http://localhost:15672.  The default username/password is guest/guest.