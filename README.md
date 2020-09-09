# image-repository

![avator](https://github.com/YuSun09/image-repository/blob/master/screenshot/index.png)

For the Image Repository, I was mainly focused on Search component, which is implement by elasticsearch(7.9.1).
Besides, the project based on spring boot 2.2.0.RELEASE with java 13. Using the Docker to simply the development and deployment workflows.

## System dependencies
- Docker [how to install docker](https://docs.docker.com/engine/installation/)

## Building
- To build jar file, run
```./build.sh```
- To build docker component, run
```docker-compose build```

## Running
- Run:
```
docker-compose up
```

## source code
![avator](https://github.com/YuSun09/image-repository/blob/master/screenshot/code.png)

- controller (based on spring MVC) 
- repository (CRUD operation of elasticsearch)
- service (core business logic)
- entity (data model)
- vo (view model)