#!/bin/bash
if [ "$1" = "" ];then
  echo "usage: $0 <output file>"
  echo "   output file - the file to save the grades in"
  exit 0;
fi

dest="$1"
if [ "$2" = "test_mode" ];then
    #Instructor test for solution
    echo -e "\033[33;7mIF YOU SEE THIS CONTACT YOUR INSTRUCTOR\033[0m"
    make grader
    cp grader homework
else
    #Generate the students assignment
    make
fi

#Make sure the program built
if [ ! -e "homework" ];then
  echo "HW2: FAIL reason: Did not build" >> $dest
  exit 1 # fail the build
fi

#Students program executable name
EXE=homework

#Run the students file with sample input data
./$EXE < data.csv 2>student.debug 1> student_out.csv;

#Run the grader with the sample data
./grader < data.csv 2>grader.debug 1>expected.csv

diff -q  expected.csv student_out.csv


if [ $? -eq 0 ];then
    echo "HW2: diff was ok"
else
    echo "HW2: FAIL" >> $dest
    exit 1
fi

valgrind --leak-check=full --quiet --error-exitcode=42 ./homework < data.csv 2> /dev/null 1>/dev/null

if [[ $? -eq 42 ]];then
    echo "HW2: FAIL - valgrind" >> $dest
else
    echo "HW2: PASS" >> $dest
fi


make clean
rm -f expected.csv student_out.csv *.debug
