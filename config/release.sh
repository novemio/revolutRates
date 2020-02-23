#!/bin/bash

version=$1
branch=$2
git config user.name "Milan Sekulic"
git config user.email "milan.sekulic@htecgroup.com"
git checkout -b release/${branch} HEAD
git checkout release/${branch}
git add --all
git commit -m "Bump version to $version"
git push -u origin release/${branch}


