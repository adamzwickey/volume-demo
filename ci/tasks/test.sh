#!/bin/bash

set -xe
. ~/.bashrc
whoami
echo $PATH
java -version
mvn -v

cd git-assets/
mvn test
