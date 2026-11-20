#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"

JAR=".tools/plantuml.jar"
if [ ! -f "$JAR" ]; then
  mkdir -p .tools
  curl -sL -o "$JAR" https://github.com/plantuml/plantuml/releases/latest/download/plantuml.jar
fi

java -Dfile.encoding=UTF-8 -jar "$JAR" -charset UTF-8 -tpng -o ../docs/diagrams model/*.puml
