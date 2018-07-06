#Project 4/TS Monitor Library

* Author: Connor Nagel
* Class: CS453

##Overview

This program is a producer-consumer queue that uses a threadsafe wrapper class to enable the program to use
multiple threads and bound itself.

##Manifest

Item.c/Item.h
pc.c					--Main function
test-pc.sh				--Tests for pc.c
wrapper-library/List.h			--Library used for list implementation
wrapper-library/ThreadsafeBoundedList.c --Wrapper list class
wrapper-library/ThreadsafeBoundedList.h	--Header doc for wrapper

##Building the project

Go to p4 directory
run "make" --Compile the directory files
run "make clean" --remove compiled files made in make


##Features and usage

./pc <poolsize> <ItemsPerproducer> <#producers> <#consumers> <sleepTime>


##Testing

I ran ./pc with different combinations of input to check that the bounds of the list were being kept.

##Discussion

There were two main issues that I had to address when writing the wrapper class. I put the while loop for thread_cond_wait outside of the mutex, which resulted in improper use of condition variables. 
Then I took a long time to figure out that I hadn't allocated the right amount of space for the list. After those issues were fixed the program ran smoothly for tests ran on it.

##Sources Used

Class pdfs
man pages
tutorials point docs 


