#!/bin/bash

set -xe
source ~/.profile
java -version
mvn -v

cd git-assets/
mvn test
