#!/bin/bash

set -xe

cd git-assets/
mvn -DskipTests=true package
cp target/*.jar ../volume-demo-releases/