
Ring Buffer Library {#mainpage}
===================

YOU MUST COMPLETE THIS README.md FILE BEFORE SUBMITTING!

To run the test programs you will need to set the paths to find the library:

```
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./lib
```

Then run the test programs as follows:

```
./grader
```

Or the run.sh script will set the path for you and execute valgrind:

```
./run.sh
```

To run the autograder before submitting, run:

```
./backpack.sh
```

Overview
---------------------------------------------------------------------

This program uses a ring buffer to hold error logs to be used for debugging.
Periodically the buffer is dumped to a file called ring.log.

Compiling and Using
---------------------------------------------------------------------
In the main directory run make and then run ./run.sh to use the program

Testing
---------------------------------------------------------------------
I added a more simple logs to see if any problems would occur from 
exceeding the buffer size.
I added a null string to see if the program would handle it correctly. This was already handled in the code. 
I also included another sleep call because I wasn't able to get the alarm to go off multiple times before the program finished. I'm not sure if this is correct,
but it's the only way I could get it to work. 


Discussion
---------------------------------------------------------------------

The most difficult part of this program was figuring out where the signal call and the alarm
function needed to be placed. I had trouble getting the buffer to dump multiple times while 
running the program. Hopefully I can clearify this for the next program. 
The suggested test for overflow didn't make much sense to me because the index
is the modulo of buff.curr % MAX-LOG-ENTRY. So the index would never exceed 5.
I would like to know what was expected of this example.


Sources Used
---------------------------------------------------------------------
CS253-resources
CS253 slides
