FROM bitnami/spark

USER root

RUN apt-get update 
RUN apt-get install -y gnupg
RUN apt-get install -y unzip
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list &&\
    echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list &&\
    curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add &&\
    apt-get update &&\
    apt-get install -y sbt

# RUN curl https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-bundle/1.11.704/aws-java-sdk-bundle-1.11.704.jar --output /opt/bitnami/spark/jars/aws-java-sdk-bundle-1.11.704.jar

RUN mkdir /opt/bitnami/dlv1

WORKDIR /opt/bitnami/dlv1

RUN mkdir src/main \
    mkdir -p intake/zip \
    mkdir -p intake/payments \
    mkdir -p intake/originations

EXPOSE 8080

ADD https://github.com/ScudraServicos/data-engineer-code-challenge/raw/main/payments.zip intake/zip/payments.zip
ADD https://github.com/ScudraServicos/data-engineer-code-challenge/raw/main/originations.zip intake/zip/originations.zip

WORKDIR /opt/bitnami/dlv1/intake

RUN unzip zip/payments.zip \
    && unzip zip/originations.zip

WORKDIR /opt/bitnami/dlv1/src/main

COPY ingestor.scala .
COPY DVL1Util.scala .

RUN sbt compile &&\
    sbt package

CMD ["spark-submit", \
    "--name", "dvl1", \
    "--master", "local[*]", \
    "--class", "dvl1.Ingestor" , \
    "/opt/bitnami/dlv1/src/main/target/scala-2.12/main_2.12-0.1.0-SNAPSHOT.jar"]

# USER 1001