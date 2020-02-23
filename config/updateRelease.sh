#!/bin/bash

branch=$2
version=$1
git config user.name "Milan Sekulic"
git config user.email "milan.sekulic@htecgroup.com"
git add --all
git commit -m "Bump version to $version"
git push -u origin release/${branch}


