#!/bin/bash
if [ "$1" = "" ];then
  echo "usage: $0 <output file>"
  echo "   output file - the file to save the grades in"
  exit 0;
fi
dest="$1"
make clean
/bin/rm -fr output #remove any stale results files
/bin/rm -f wf #just in case something was hard coded :) 

#Make sure they named script correctly
if [ ! -e "tester.sh" ];then
  echo "P5: FAIL reason: no tester.sh script was found" >> $dest
  exit 1 # fail the build
fi

#run the students script
/bin/bash ./tester.sh

#Make sure the program built
if [ ! -e "wf" ];then
  echo "P5: FAIL reason: Did not build wf" >> $dest
  exit 1 # fail the build
fi

#Make sure they created the output folder named correctly
if [ ! -e "output" ];then
  echo "P5: FAIL reason: Did not create output folder" >> $dest
  exit 1 # fail the build
fi

#check that the results files were all created as specified
for dataFile in input/*txt
do
	name=$(basename $dataFile)
	if [ ! -e output/$name-std-list-results ]; then
  		echo "P5: FAIL reason: Did not find results file: " output/$name-std-results >> $dest
  		exit 1 # fail the build
	fi
	if [ ! -e output/$name-self-organized-list-results ]; then
  		echo "P5: FAIL reason: Did not find results file: " output/$name-self-organized-list-results >> $dest
  		exit 1 # fail the build
	fi
done

#check that the files were processed in reverse sorted size
ls -Sr input > l1
cd output
for f in $(ls -tr *std*)
do 
	basename $f -std-list-results
done > l2 
mv l2 ..
cd ..
diff l1 l2
if [ ! "$?" = "0" ]; then
  	echo "P5: FAIL reason: files were not processed sorted by size"
  	exit 1 # fail the build
fi
/bin/rm -f l1 l2

make clean
echo
echo "P5: PASS" >> $dest
echo
