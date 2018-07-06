Project 2

Author:Connor Nagel
Class: CS253 Section 1

Overview

	This program is a state machine that reads in a CSV document line by line and edits the file so that it matches the specified format


Compiling and Using

In the directory containing the source files do the following:

Compile the program: gcc -Wall main.c 
Pass in data to be read: ./a.out < yourCSVfile.csv

main.c will then print the result to console


Testing

My testing process mostly consisted of seeing where each line differed from the expected outcome, and then I focused my attention to whatever state or input
caused the malfunction.

Discussion

It took me a long time to remember how to read the characters from a pointer. I thought that 
fgetc or fgets would be able to take in the pointer value, but I was wrong. After I looked back through the class examples I realized I could just use a for-loop to run
through the line. I also had trouble remembering how the arguments for getline worked, I used the class textbook and the command line manual to figure that out.

Sources used

The man command in command line, and class resources.
