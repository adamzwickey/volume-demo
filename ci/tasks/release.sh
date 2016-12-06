#!/bin/bash

set -xe

VERSION=$(<version/number)

cd release/
echo "saving file with version number: $VERSION"
#cp target/*.jar ../volume-demo-releases/volume-demo-$VERSION.jar