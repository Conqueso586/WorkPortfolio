
Doubly-linked Lists {#mainpage}
===================

Project 3

Author: Connor Nagel
Class: CS253 Section 1

Overview
----------------------------------------------------------------------------

This program implements and tests the functionality of a doubly linked-list and its list operations.


Compiling and Using
----------------------------------------------------------------------------
To run the test programs you will need to set the paths to find the library:

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./lib

Then run the test programs as follows:
First, run a simple smoke test:

testsuite/SimpleTestList

Note that if you cd to testsuite and try to run SimpleTestList there, it will
not find the library because of how we set the LD_LIBRARY_PATH variable above. 
If you want to be able to do that, use the following:

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./lib:../lib

Next, run the incomplete unit test. You will need to add to it.

testsuite/UnitTestList

Finally, run a larger test that uses the list.

testsuite/RandomTestList

Each program takes command line arguments and will give a help message if run
without the command line arguments.


Testing
----------------------------------------------------------------------------
I had to write a list of tests that tested all of the possible boundry comditions of the linked list
 functions. I used gdb and valgrind to determine what I hadn't tested yet.


Discussion
----------------------------------------------------------------------------
During this project I became a lot more familiar with gdb and valgrind. There were some bugs that I
didn't predict, so I needed to use gdb to find where the issue was and then examine variables
using print. 

I had a hard time understanding how to properly use git and so for the first checkpoint I 
didn't push my finished files after I commited. At the second checkpoint I couldn't figure out how to fix some of the issues with the grader at the time and after the due date I didn't check piazza
 until saturday so I didn't see the extension. But for this final checkpoint I ironed out all 
the issues I had with git, the grader, and backpack. So now all tests pass with no valgrind issues.

Sources Used
----------------------------------------------------------------------------
Piazza
CS253-resources

