/*
 * UnitTestList.c
 *
 *      Author: Connor Nagel
 */

#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0


#include "Object.h"
#include <Node.h>
#include <List.h>

/*
 * macro to mimic the functionality of assert() from <assert.h>. The difference is that this version doesn't exit the program entirely.
 * It will just break out of the current function (or test case in this context).
 */
#define myassert(expr) if(!(expr)){ fprintf(stderr, "\t[assertion failed] %s: %s\n", __PRETTY_FUNCTION__, __STRING(expr)); return FALSE; }

struct list *testlist;

int testCount = 0;
int passCount = 0;


//Basic Test Functions

/*
*These functions are for printing test info and results, 
*and creating test nodes for use in the testing.
*
*/
void printTestInfo(char* testName, char *info)
{
	fprintf(stdout, "%s - %s\n", testName, info);
}

void printTestResult(char* testName, int passed)
{
	if(passed) {
		fprintf(stdout, "%s - %s\n\n", "[PASSED]", testName);
	} else {
		fprintf(stdout, "%s - %s\n\n", "[FAILED]", testName);
	}
}

struct node *createTestNode(int jobid)
{
	struct object * job = createObject(jobid, "cmd args");
	struct node *node = createNode(job);
	return node;
}

void beforeTest(char* testName)
{
	printTestInfo(testName, "Running...");
	testlist = createList(equals, toString, freeObject);
	testCount++;
}
void afterTest(char* testName, int result)
{
	printTestResult(testName, result);
	freeList(testlist);
	passCount += result;
}

/*
*The following methods are what each test uses to determine 
*whether each list function is working correctly
*/
int nullNodeTest()
{

	struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(2);
	
	addAtFront(testlist, node);
	addAtRear(testlist, node2);
	
	removeRear(testlist);
	removeNode(testlist, node);

	myassert(testlist->head == NULL);
	myassert(testlist->tail == NULL);
	myassert(testlist->size == 0 );


	
	return TRUE;
}
int getSizeWithNoNode(){

	myassert(getSize(testlist) == 0);
	myassert(testlist->size == 0);

	return TRUE;
}
int getSizeWithOneNode(){
	struct node *node = createTestNode(1);
	addAtFront(testlist, node);
	myassert(getSize(testlist) == 1);
	myassert(testlist->size == 1);

	return TRUE;
}
int isEmptyNoNode(){
	myassert(isEmpty(testlist) == 1);
	myassert(testlist->size == 0);

	return TRUE;
}
int isEmptyOneNode(){
	struct node *node = createTestNode(1);
	addAtFront(testlist, node);
	myassert(isEmpty(testlist) == 0);
	myassert(testlist->size == 1);
	return TRUE;
}

int addAtFrontWithNoNodes()
{
	struct node *node = createTestNode(1);

	addAtFront(testlist, node);

	myassert(testlist->size == 1);
	myassert(testlist->head == node);
	myassert(testlist->tail == node);
	myassert(testlist->head->next == NULL);
	myassert(testlist->head->prev == NULL);	


	return TRUE;
}

int addAtFrontWithOneNode()
{
	struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(2);

	addAtFront(testlist, node);
	addAtFront(testlist, node2);

	myassert(testlist->size == 2);
	myassert(testlist->head == node2);
	myassert(testlist->tail == node);
	myassert(testlist->head->next == node);
	myassert(testlist->head->prev == NULL);
	myassert(testlist->tail->prev == node2);


	return TRUE;
}

int addAtRearWithNoNodes()
{
	struct node *node = createTestNode(1);

	addAtRear(testlist, node);

	myassert(testlist->size == 1);
	myassert(testlist->head == node);
	myassert(testlist->tail == node);


	return TRUE;
}

int addAtRearWithOneNode()
{
	struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(2);

	addAtFront(testlist, node);
	addAtRear(testlist, node2);

	myassert(testlist->size == 2);
	myassert(testlist->head == node);
	myassert(testlist->tail == node2);
	myassert(testlist->head->next == node2);
	myassert(testlist->tail->prev == node);

	return TRUE;
}

int removeFront1Node(){

	struct node *node = createTestNode(1);

	addAtFront(testlist, node);
	struct node *removedNode = removeFront(testlist);

	myassert(testlist->head == NULL);
	myassert(testlist->tail == NULL);
	myassert(testlist->size == 0);

	freeNode(removedNode, testlist->freeObject);
	return TRUE;
}

int removeFront3Nodes(){

	struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(2);
	struct node *node3 = createTestNode(3);

	addAtFront(testlist, node2);
	addAtFront(testlist, node);
	addAtRear(testlist, node3);
	struct node *removedNode = removeFront(testlist);

	myassert(testlist->head == node2);
	myassert(testlist->tail == node3);
	myassert(testlist->size == 2);
	myassert(testlist->head->next == node3);
	myassert(testlist->tail->prev == node2);

	freeNode(removedNode, testlist->freeObject);

	return TRUE;
}

int removeRear1Node(){

	struct node *node = createTestNode(1);

	addAtRear(testlist, node);
	struct node *removedNode = removeRear(testlist);

	myassert(testlist->head == NULL);
	myassert(testlist->tail == NULL);
	myassert(testlist->size == 0);

	freeNode(removedNode, testlist->freeObject);

	return TRUE;
}

int removeRear3Nodes(){

	struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(2);
	struct node *node3 = createTestNode(3);

	addAtFront(testlist, node2);
	addAtFront(testlist, node);
	addAtRear(testlist, node3);
	struct node *removedNode = removeRear(testlist);

	myassert(testlist->head == node);
	myassert(testlist->tail == node2);
	myassert(testlist->size == 2);
	myassert(testlist->head->next == node2);
	myassert(testlist->tail->prev == node);

	freeNode(removedNode, testlist->freeObject);

	return TRUE;
}

int removeNode1Node(){

	struct node *node = createTestNode(1);

	addAtFront(testlist, node);

	struct node *removedNode = removeNode(testlist, node);

	myassert(testlist->head == NULL);
	myassert(testlist->tail == NULL);
	myassert(testlist->size == 0);

	freeNode(removedNode, testlist->freeObject);

	return TRUE;
}

int removeNode3Nodes(){

	struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(2);
	struct node *node3 = createTestNode(3);

	addAtFront(testlist, node2);
	addAtFront(testlist, node);
	addAtRear(testlist, node3);
	struct node *removedNode = removeNode(testlist, node2);

	myassert(testlist->head == node);
	myassert(testlist->tail == node3);
	myassert(testlist->size == 2);
	myassert(testlist->head->next == node3);
	myassert(testlist->tail->prev == node);

	freeNode(removedNode, testlist->freeObject);

	return TRUE;
}

int reverseList3Nodes(){
	struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(2);
	struct node *node3 = createTestNode(3);

	addAtFront(testlist,node);
	addAtRear(testlist,node2);
	addAtRear(testlist,node3);
	reverseList(testlist);

	myassert(testlist->head == node3);
	myassert(testlist->tail == node);
	myassert(testlist->head->next == node2);
	myassert(testlist->tail->prev == node2);
	myassert(testlist->head->next->prev == node3);
	myassert(testlist->tail->prev->next == node);

	return TRUE;
}

int reverseList2Nodes(){
	struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(2);

	addAtFront(testlist, node);
	addAtRear(testlist, node2);
	reverseList(testlist);

	myassert(testlist->head == node2);
	myassert(testlist->tail == node);
	myassert(testlist->head->next == node);
	myassert(testlist->tail->prev == node2);

	return TRUE;
}

int searchList(){

	struct node *node = createTestNode(1);
	struct node *node2 = createTestNode(2);
	struct node *node3 = createTestNode(3);

	addAtFront(testlist,node);
	addAtFront(testlist,node2);
	addAtRear(testlist,node3);

	struct object *testObject = createObject(3, "cmd args");

	struct node *foundNode = search(testlist, testObject);
	myassert(foundNode == node3);	

	return TRUE;

}



/*
* runUnitTests calls on each test method to test the linked-list
*/
void runUnitTests()
{
	int result;
	char *testName;

	testName = "nullNodeTest";
	beforeTest(testName);
	result = nullNodeTest();
	afterTest(testName, result);

	testName = "getSizeWithNoNode";
	beforeTest(testName);
	result = getSizeWithNoNode();
	afterTest(testName, result);

	testName = "getSizeWithOneNode";
	beforeTest(testName);
	result = getSizeWithOneNode();
	afterTest(testName, result);

	testName = "isEmptyNoNode";
	beforeTest(testName);
	result = isEmptyNoNode();
	afterTest(testName, result);

	testName = "addAtFrontWithNoNodes";
	beforeTest(testName);
	result = addAtFrontWithNoNodes();
	afterTest(testName, result);

	testName = "addAtFrontWithOneNode";
	beforeTest(testName);
	result = addAtFrontWithOneNode();
	afterTest(testName, result);

	testName = "addAtRearWithNoNodes";
	beforeTest(testName);
	result = addAtRearWithNoNodes();
	afterTest(testName, result);

	testName = "addAtRearWithOneNode";
	beforeTest(testName);
	result = addAtRearWithOneNode();
	afterTest(testName, result);

	testName = "removeFront1Node";
	beforeTest(testName);
	result = removeFront1Node();
	afterTest(testName, result);

	testName = "removeFront3Nodes";
	beforeTest(testName);
	result = removeFront3Nodes();
	afterTest(testName, result);

	testName = "removeRear1Node";
	beforeTest(testName);
	result = removeRear1Node();
	afterTest(testName, result);

	testName = "removeRear3Nodes";
	beforeTest(testName);
	result = removeRear3Nodes();
	afterTest(testName, result);

	testName = "removeNode1Node";
	beforeTest(testName);
	result = removeNode1Node();
	afterTest(testName, result);

	testName = "removeNode3Nodes";
	beforeTest(testName);
	result = removeNode3Nodes();
	afterTest(testName, result);

	testName = "reverseList3Nodes";
	beforeTest(testName);
	result = reverseList3Nodes();
	afterTest(testName, result);

	testName = "reverseList2Nodes";
	beforeTest(testName);
	result = reverseList2Nodes();
	afterTest(testName, result);

	testName = "searchList";
	beforeTest(testName);
	result = searchList();
	afterTest(testName, result);


	//TODO: Add in your tests here

	fprintf(stdout, "Test Cases: %d\n",  testCount);
	fprintf(stdout, "Passed: %d\n", passCount);
	fprintf(stdout, "Failed: %d\n", testCount - passCount);
}

int main(int argc, char *argv[])
{
	runUnitTests();
	exit(0);
}
