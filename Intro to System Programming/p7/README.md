
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
Periodically the buffer is dumped to a file called ring.log. It is made thread safe by adding a mutex lock on shared data. 

Compiling and Using
---------------------------------------------------------------------
In the main directory run make and then run ./grader <NumberOfThreads> <NumLogs> to use the program

Testing
---------------------------------------------------------------------
I mostly tested the code itself by printing different variables to make sure that they were recieving the right information. Most of my problems came from making sure that I used the right number of pointers and how to write the arguments for the pthread-create function. It took me hours to figure out a problem I was having with the makefile.


threads, time
1, 0m2.004s
2, 0m2.002s
3, 0m2.002s
4, 0m2.003s
32, 0m2.002s
100, 0m2.003s 


Discussion
---------------------------------------------------------------------
s project allowed me to see a way that I could improve code to make it run better. On top of that the mutexes helped me understand how I could make my code more stable. They kept the threads from interfering with eachother.


Sources Used
---------------------------------------------------------------------
CS253-resources
CS253 slides
I primarily used class sources. But I also had to use different forum sites to fully understand how to use threads correctly. 
