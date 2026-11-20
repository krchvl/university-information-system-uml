#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"

rm -rf out
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -Dfile.encoding=UTF-8 -cp out university.Main "$@"
