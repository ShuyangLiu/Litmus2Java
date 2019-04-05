#!/bin/bash
BASE=$(pwd)
DIRS=$BASE/*/

for dir in $DIRS
do
  echo "Processing $dir..."
  cd $dir
  mkdir -p out
  /usr/lib/jvm/java-10-oracle/bin/javac src/* -d out
  for (( i = 0; i < 100; i++ )); do
  	/usr/lib/jvm/java-10-oracle/bin/java -ea -Dfile.encoding=UTF-8 -classpath out Main
  done
  cd -
done