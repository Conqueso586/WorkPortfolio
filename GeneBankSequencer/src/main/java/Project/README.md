****************
* GeneBankSequencer
* Date 8/20/2018
* Author: Connor Nagel 
**************** 

OVERVIEW:

*The cache is currently not used in the BTree, I still need to add that functionality. The cache itself works.*

This program was built to create a BTree data structure which contains Helpers.DNA sequences from a file. A second program was built to search 
for searching the BTree.

PROGRAM FILES:

BTree Files:
BTree.java
KeyMaker.java
TreeObject.java
Helpers.Cache.java

Main Classes:
GeneBankCreateBTree.java
GeneBankSearch.java

Folders for testing:
gbk - Formatted Gene bank files used to generate the Btree
queries - Query files used to test the GeneBankSearch program

COMPILING AND RUNNING:

From the source directory containing all program files, compile the
driver class (and all dependencies) with the command:
javac GeneBankCreateBTree.java || GeneBankSearch.java
 
Run GeneBankCreateBtree first. This will generate a file in the source directory that will contain the BTree data.
java GeneBankCreateBTree <0/1(no/with Helpers.Cache)> <degree> <gbk file> <sequence length> [<cache size>] [<debug level>]

example: java GeneBankCreateBTree 0 4 gbk/test1.gbk 4

Run GeneBankSearch with following command. Query files are located in queries.
java GeneBankSearch <0/1(no/with Helpers.Cache)> <btree file> <query file> [<cache size>][<debug level>]

example: java GeneBankSearch 0 test1.gbk.btree.data.4.4 queries/query4
 
Results will be printed to the console.

PROGRAM DESIGN AND IMPORTANT CONCEPTS:

This program reads gene data bank files and breaks them into sequences that can then be analyzed to find the frequency of certain sequences. It uses a BTree to store and order the sequences in the tree's nodes. Each sequence is stored as a long value.
 