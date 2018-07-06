#!/bin/sh
# ==================================================================
# Test program for CS121 P4 - ProcessText
# - Checks if required files exist and compile correctly.
# - Tests the ProcessText program on the given test files.
# ==================================================================

echo
echo "Running this shell script should compile and run your program.
The output should match the sample output in the assignment."

echo

if (test -f TextStatistics.java)
then
    javac TextStatistics.java
else
    echo "Missing file: TextStatistics.java"
    echo "(Make sure your file name matches exactly)"
fi

if (test -f ProcessText.java)
    then
    javac ProcessText.java
else
    echo "Missing file: ProcessText.java"
    echo "(Make sure your file name matches exactly)"
fi

if (test -f ProcessText.class)
    then
    echo
    echo Testing ProcessText...
    echo
    java ProcessText testfile.txt not-a-file.txt etext/Gettysburg-Address.txt
    echo
    echo "The file 'testresults' has the statistics for the files"
    echo "testfile.txt, not-a-file.txt, and Gettysburg-Address.txt"
    echo "(not-a-file.txt is a file that doesn't exist. Make sure"
    echo "your program handles it correctly."
else
    echo
    echo "ProcessText class not found - your program didn't compile."
fi
echo

if ! test -e README
    then
    echo Did you forget that you need a file called README
fi

