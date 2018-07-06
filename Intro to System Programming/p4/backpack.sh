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
if [ ! -e "hello" ];then
  echo "P4: FAIL reason: Did not build the right executable" >> $dest
  exit 1 # fail the build
fi

make run
if [ "$?" != 0 ];then
        #We failed
        echo "P4: FAIL make run" >> $dest
        exit 0
fi

make clean
if [ "$?" != 0 ];then
        #We failed
        echo "P4: FAIL make clean" >> $dest
        exit 0
fi

touch myHeader.h
make -q
if [ "$?" == 0 ];then
        #We failed
        echo "P4: FAIL make header file dependency test" >> $dest
        exit 0
fi

grep "\$(CC)" Makefile && grep "\$(CFLAGS)" Makefile
if [ "$?" != 0 ];then
        #We failed
        echo "P4: FAIL Makefile not using macros? " >> $dest
        exit 0
fi

grep "\-MMD" Makefile && grep "\-include" Makefile
if [ "$?" != 0 ];then
        #We failed
        echo "P4: FAIL Makefile not using auto-generated header file dependencies? " >> $dest
        exit 0
fi

make clean
echo "P4: PASS" >> $dest
