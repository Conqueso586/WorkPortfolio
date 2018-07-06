# Project 3: Regex

* Author: Connor Nagel
* Class: CS361 Section 1
* Semester: Spring 2017

## Overview

This program creates an NFA using recursive decent parsing and a given regular expression. 
Then it creates the equivalent DFA and tests a list of input strings.

## Compiling and Using

Navigate to the main folder: P3/
To Compile: javac -cp ".:./CS361FA.jar" re/REDriver.java
To Run: java -cp ".:./CS361.jar" re.REDriver <inputFilePath> 
Input example: ./tests/p3tc3.txt

## Discussion

I thought this project was the most interesting project we've done this semester. 
Translating the recursive decent logic into something that the NFA could use
came pretty easy for the most part, there were a few times where I got stuck while 
implementing some of the methods.

## Testing

I would go through each method and check relevent variables to see if they were correctly 
creating and connecting states for the NFA. Once I fixed all issues in the methods, 
I ran all the given tests and even created a few of my own to see if it could 
handle more complex regular expressions, and they all passed for me.
