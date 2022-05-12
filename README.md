# Data Engineer Code Challenge

This repository was made to a Data Engineer Code Challenge.


## Prerequisites
We'll be using the below tools:

* Docker

## DLV1

The [ `Dockerfile` ](https://github.com/SergioPinheiro/data-engineer-code-challenge/blob/main/Dockerfile) builds a image to resolve the first deliverable (dlv1) of the challenge.
### Running

``` shell
docker build -t dlv1 .
docker run dlv1
```

####  Obs
The [ `docker-compose.yml` ](https://github.com/SergioPinheiro/data-engineer-code-challenge/blob/main/docker-compose.yml) file would be used to build the final deliverable using, spark scala and airflow