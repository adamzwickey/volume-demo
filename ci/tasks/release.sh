#!/bin/bash

set -xe

VERSION=$(cat version/number)

cd pre-release/
echo "saving file with version number: $VERSION"
cp $CF_APP-*.jar ../$CF_APP-releases/$CF_APP-$VERSION.jar
