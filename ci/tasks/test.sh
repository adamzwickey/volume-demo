#!/bin/bash

set -xe
#source ~/.profile
whoami
echo $PATH
java -version
mvn -v

cd git-assets/
mvn test
