#!/bin/bash
set -e
cd "$(dirname "$0")/.."/backend || exit 1
mvn -q package -DskipTests
cd ..
docker build -t releaseops:latest -f backend/Dockerfile .
