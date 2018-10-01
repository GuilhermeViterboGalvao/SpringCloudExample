FROM openjdk:8u181-jdk

MAINTAINER Guilherme Viterbo Galvão <catanduva.gvg@gmail.com>

# GIT
RUN apt-get install git

# APACHE MAVEN 3.5.4
RUN cd /opt/ && \
    wget http://ftp.unicamp.br/pub/apache/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.tar.gz && \
	tar -xf apache-maven-3.5.4-bin.tar.gz && \
	rm -f apache-maven-3.5.4-bin.tar.gz && \
	ln -s apache-maven-3.5.4 maven
RUN cd /usr/bin && \
	ln -s /opt/maven/bin/* .

WORKDIR /app
VOLUME  /app