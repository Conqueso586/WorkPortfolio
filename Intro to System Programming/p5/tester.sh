#!/bin/bash

export LD_LIBRARY_PATH=./lib
files=`ls -Sr input/*`

		make
		echo
		
		echo -e "Creating output folder\n"     
		if test -d "output"; 
		then
			rm -r output
			mkdir output
		else
			mkdir output
		fi

		
		echo -e "Running wf, results in output/ folder\n"
		for pathName in $files
		do

			baseName=`basename $pathName`

			echo "Processing file:" $pathName

			echo -n '  std-list:'
			command time -f "\t\t%Us" ./wf --std-list $pathName | sort > output/$baseName-std-list-results

		        echo -n '  self-organized-list:' 
		        command time -f "\t%Us" ./wf --self-organized-list $pathName | sort > output/$baseName-self-organized-list-results

		        diff output/$baseName-std-list-results output/$baseName-self-organized-list-results

			if [ $? == 0 ] 
			then

				echo -e "  OK: std-list and self-organized-list results match!\n"

			fi
		done

		echo "All done!"
