#!/bin/bash

set -xe

cd git-assets/
mvn package
cp target/volume-demo-*.jar ../app-output/volume-demo.jar