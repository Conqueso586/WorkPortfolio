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
./grader
if [ $? -eq 0 ];then
	echo "Passed the base grader"
else
        echo "P3: FAIL -base grader" >> $dest
	exit 1
fi

log="valgrind.log"
valgrind --leak-check=full --quiet --log-file=$log ./grader 2> /dev/null
if [[ -s "$log" ]]
then
    echo "P3: FAIL - valgrind" >> $dest
else
    echo "P3: PASS" >> $dest
	rm -f $log
fi
