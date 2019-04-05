#!/bin/bash
BASE=$(pwd)
DIRS=$BASE/*/

# Check if javac and java is installed
if type -p javac; then
    echo "found javac executable in PATH"
else
	echo "no javac executable found"
	exit 1
fi

if type -p java; then
    echo "found java executable in PATH"
else
	echo "no java executable found"
	exit 1
fi

# Check if JDK has version >= 9
version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F'.' '{ print $1 }')
echo version "$version"
if [[ $version -ge 9 ]]; then
    echo "JDK version is more than 9"
else         
    echo "JDK version is less than 9"
    exit 1
fi

# Process 
for dir in $DIRS
do
  echo "Processing $dir..."
  cd $DIRS
  mkdir -p out
  javac src/* -d out
  for (( i = 0; i < 1000; i++ )); do
  	java -ea -Dfile.encoding=UTF-8 -classpath out Main
  done
  cd -
done