#!/bin/bash

set -xe

cd git-assets/boot-sample
mvn package
cp target/volume-demo-*.jar ../../app-output/vaolume-demo.jar