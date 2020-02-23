#!/bin/bash

version=$1
git config user.name "Milan Sekulic"
git config user.email "milan.sekulic@htecgroup.com"
./gradlew assembleDevRelease assembleStageRelease
git add --all
git commit -m "Bump version to $version, snapshot release"
git push -u origin HEAD


