#Project Number/Title {#mainpage}

* Author: Connor Nagel	
* Class: CS453/552 [Operating Systems] Section 1

##Overview

This program mimics the functionality of a bash shell. It contains multiple built-in
commands and keeps track of jobs executed by the shell.

##Manifest

dashutils.c -- The main functions that are used by mydash
dashutils.h -- header for dashutils
error.c     -- file containing error information
List.h	    -- the header for the linked list library
Makefile    -- makefile used to generate the executable
mydash.c    -- the main file for mydash, this handles read in of input
mydash.h    -- header for mydash.c
Node.h	    -- Node header used for the list

##Building the project

cd into main p1 directory
make 		 -- creates executable file
./mydash	 -- run the generated executable
mydash> 	 -- prompt for input



##Features and usage

Usage
mydash> <command> <args...> -- input a command along with a list arguments

Features

./mydash -v 		--Displays version and quits
mydash> cd <path>	--Changes directory

mydash> sleep 10 & 	--Creates and keeps track of background jobs if the input ends with a &

mydash> jobs		--lists jobs created in order


##Testing

I started out with a basic toString that I could use to print out object contents to 
verify that they were correct. I also used gdb to examine closly the changes made at each step. 
Valgrind was a great resource for finding mistakes in heap usage.

###Valgrind

I used valgrind to fix a problem I was having with the printList function. I found that I was not
mallocing some strings in my objects so that my program was improperly reading their values. It assisted me
in findings the places where free() was required.

###Known Bugs

Job list does not keep track of when the job finishes and the job numbers do not account for finished jobs.

##Discussion

I had a pretty easy time with all of the checkpoint requirements. Once I started working with the list 
I had way more trouble progressing. I had a problem figuring out how to create the objects that are
passed to the list functions. Casting them so that they were usable was difficult. I had difficulty 
finding a problem were the contents of an object would be corrupted. I found that it was due to not mallocing 
the returned char* in tostring. Once I fixed that problem I realized that I couldn't wrap my head around 
how to use waitpid to keep track of finished jobs. This kept me from completing every function of the project.

##Sources used

man pages

tutorialsPoint documentation

man7.org
