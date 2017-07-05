#!/bin/bash

set -xe
#source ~/.profile
echo $PATH
java -version
mvn -v

cd git-assets/
mvn test
