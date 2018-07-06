#!/bin/bash

# dest="results.txt"

#Clean up everything
rm *.class > /dev/null 2>&1

echo "-------------------"
echo "Testing Song.java"
echo "-------------------"

#Generate the students assignment
javac SongTest.java > /dev/null 2>&1

#Make sure the program built
if [ ! $? == 0 ];then
  # echo "FAIL reason: Song did not compile" >> $dest
  echo "FAIL reason: Song did not compile"
  exit 1 # fail the build
fi

# Run the test
java SongTest

#Make sure the program exited with success
if [ ! $? == 0 ];then
  # echo "FAIL reason: Song tests failed" >> $dest
  echo "FAIL reason: Song tests failed"
  exit 1 # fail the build
fi

echo "----------------------"
echo "Testing PlayList.java"
echo "----------------------"

#Generate the students assignment
javac PlayListTest.java > /dev/null 2>&1

#Make sure the program built
if [ ! $? == 0 ];then
  # echo "FAIL reason: PlayList did not compile" >> $dest
  echo "FAIL reason: PlayList did not compile"
  exit 1 # fail the build
fi

# Run the test
java PlayListTest

#Make sure the program exited with success
if [ ! $? == 0 ];then
  # echo "FAIL reason: PlayList tests failed" >> $dest
  echo "FAIL reason: PlayList tests failed"
  exit 1 # fail the build
fi

echo "-------------------"
echo "Testing Jukebox.java (output below)"
echo "-------------------"

#Generate the students assignment
javac Jukebox.java > /dev/null 2>&1

#Make sure the program built
if [ ! $? == 0 ];then
  # echo "FAIL reason: Jukebox did not compile" >> $dest
  echo "FAIL reason: Jukebox did not compile"
  exit 1 # fail the build
fi

# Run the test
java Jukebox <<END
f
a
An awesome song
Coolio Jo
5
sounds/westernBeat.wav
d
2
p
l
q
END
echo "--------------------"
echo "End of output."
echo
#Make sure the program exited with success
if [ ! $? == 0 ];then
  # echo "FAIL reason: Jukebox test failed" >> $dest
  echo "FAIL reason: Jukebox test failed"
  exit 1 # fail the build
else
  echo "PASS: Jukebox test passed."
fi
echo
#Generate Javadocs
javadoc -author -d doc -quiet Song.java PlayList.java
if [ ! $? == 0 ];then
  # echo "FAIL reason: javadoc generation failed" >> $dest
  echo "FAIL reason: javadoc generation failed"
  exit 1 # fail the build
fi
echo
echo "Generated javadocs. Run the following command to view your documentation!"
echo "google-chrome doc/index.html"
echo
echo "Make sure that each method has the correct documentation or you will lose points"
echo "When you are done, remove the entire doc directory using \"rm -rf doc\""
echo
#Make sure README exists
if [ ! -f "README" ];then
  # echo "FAIL reason: No README file (or it may be named something different)" >> $dest
  echo "FAIL reason: No README file (or it may be named something different)"
  exit 1 # fail the build
fi
echo "README found. Make sure it follows the correct format."
echo
echo "PASS: Looks good overall! Keep in mind that this doesn't test EVERYTHING."
echo "      You should still make sure your indentation is correct and that you are submitting"
echo "      the correct files, you are using good coding practices, etc."

rm *.class
