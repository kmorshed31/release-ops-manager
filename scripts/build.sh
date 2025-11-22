#!/bin/bash
set -e
cd "$(dirname "$0")/.."/backend || exit 1
mvn clean package -DskipTests
