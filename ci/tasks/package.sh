#!/bin/bash

set -xe

VERSION=$(<version/number)

cd git-assets/
mvn -DskipTests=true package

echo "saving file with version number: $VERSION"
cp target/*.jar ../volume-demo-releases/volume-demo-$VERSION.jar