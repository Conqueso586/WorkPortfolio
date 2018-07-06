#!/bin/bash
if [ "$1" = "" ];then
  echo "usage: $0 <output file>"
  echo "   output file - the file to save the grades in"
  exit 0;
fi
dest="$1"
#Generate the students assignment
#for this assignment we should have a make file
#which will put the library in a folder called lib
make

#Run the grader. 0 exit status is a pass
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./lib
./grader debug
if [ $? -eq 0 ];then
	echo "Passed the base grader"
else
        echo "P0: FAIL - base grader" >> $dest
	exit 1
fi

ERROR=$({ valgrind --leak-check=full --quiet ./grader; } 2>&1)
if [[ ! -z "$ERROR" ]]
then
    echo "P0: FAIL - valgrind" >> $dest
else
    echo "P0: PASS" >> $dest
fi
