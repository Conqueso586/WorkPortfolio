#!/bin/bash
if [ "$1" = "" ];then
  echo "usage: $0 <output file>"
  echo "   output file - the file to save the grades in"
  exit 0;
fi

dest="$1"
#Generate the students assignment
make

#Make sure the program built
if [ ! -e "homework" ];then
  echo "HW1: FAIL reason: Did not build" >> $dest
  exit 1 # fail the build
fi

#Students program executable name
EXE=homework

#Run the students file with sample input data
./$EXE < data > actual;

#Run the grader with the sample data
./grader < data > expected

diff -q  expected actual

if [ $? -eq 0 ];then
    echo "HW1: PASS" >> $dest
else
    echo "HW1: FAIL" >> $dest
    exit 1
fi

make clean
rm -f expected actual
