****************
* Project 4
* Class CS 321
* Date 12/11/16 
* Your name Timothy Adams, shraddha timilsina, Connor Nagel 
**************** 

OVERVIEW:

This program was built to store a DNA sequences from a file storing them in a BTree. A second program was built to search 
for searching the BTree.

INCLUDED FILES:

BTree.java
Cache.java
DoubleLinkedList.java
DoubleLinkedListADT.java
DoubleNode.java
ElementNotFoundException.java
EmptyCollectionException.java
GeneBankCreateBTree.java
GeneBankSearch.java
IndexedListADT.java
KeyMaker.java
ListADT.java
TreeObject.java
UnorderedListADT.java

COMPILING AND RUNNING:

 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ javac GeneBankCreateBTree.java || GeneBankSearch.java
 
 java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequence length>
[<cache size>] [<debug level>]

java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<cache size>]
[<debug level>]
 
 File or Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

We stated this project by writing the BTree class. We wrote the insert methods first after writing our internal BTreeNode class.
We then wrote Our search method and writing to the file came next. We used RandomAccessFile to write and read from the file. This part
of the project took the longest to figure out. We then wrote our driver classes and the required files to run those.  
 
TESTING:

We tested along the way with small programs that tested the functionality of each method as we went. Our project never 
was 100% complete so we couldn't test our program fully. 

DISCUSSION:

For most of early development we spent our time figuring out how we were to write the BTree program. The first time we attempted to write 
it we found that near completion, it became apparent that we didn't understand how we were to store the data. Initially we
thought that the Btree data and it's nodes were directly stored into a file. Once we realized this we started fresh and
payed more attention to how we should write Disk-Read and Disk-Write. A few days prior to the due date we were confident in
the Btree was correct. Our next big challenge was dealing with the GeneBankCreateBTree. We were able to use it to test BTree
with small sequences, but we found that once we started testing with larger sequences we had to re-do the way that we converted
the DNA sequences into binary. This took a very long time and required large amounts of help from TA's and friends. Halfway through the week
after the due date we wrote out the conversion code. The bug that has stopped us in our tracks is an ElementNotFoundException that
occurs when we read in the DNA file. This was not a problem until recently, but it has completely kept us from debugging the 
rest of the program. Which is frustrating because we were so close to completing it.
 

