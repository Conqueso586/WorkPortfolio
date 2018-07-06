#include <stdio.h>
#include <stdlib.h>
#include "List.h"
#include "Node.h"
struct list * createList(int (*equals)(const void *,const void *),
		char * (*toString)(const void *),
		void (*freeObject)(void *))
{
	struct list *list;
	list = (struct list *) malloc(sizeof(struct list));
	list->size = 0;
	list->head = NULL;
	list->tail = NULL;
	list->equals = equals;
	list->toString = toString;
	list->freeObject = freeObject;
	return list;
}

void freeList(struct list *list)
{
	struct node *temp = NULL;
	struct node *currNode = list->head;
	while(currNode != NULL){
		temp = currNode;
		currNode = currNode->next;	
		freeNode(temp, list->freeObject);

	}
	free(list);
}

int getSize(const struct list *list)
{
	return list->size;
}

int isEmpty(const struct list *list)
{
	return list->size == 0;
}

void addAtFront(struct list *list, struct node *node)
{
	if (list == NULL) return;
	if (node == NULL) return;
	list->size++;
	node->next = list->head;
	node->prev = NULL;
	if (list->size == 1) {
		list->head = node;
		list->tail = node;
	} else if (isEmpty(list)){
		list->head = node;
		list->tail = node;

	} else{
		list->head->prev = node;
		list->head = node;
	}
}

void addAtRear(struct list *list, struct node *node)
{
	if(list == NULL) return;
	if(node == NULL) return;

	if(isEmpty(list)){
		list->head = node;
		list->tail = node;		
	}else {	

		list->tail->next = node;
		node->prev = list->tail;
		list->tail = node;
	}
	list->size++;
}

struct node* removeFront(struct list *list)
{
	if(list == NULL) return NULL;

	struct node *node = list->head;

	if(getSize(list) == 1){
		list->head = NULL;	
		list->tail = NULL;
	}
	else if (isEmpty(list)){
		return NULL;
	}
	else{
		list->head = node->next;
		node->next->prev = NULL;
	}
	list->size--;

	node->next = NULL;
	node->prev = NULL;

	return node;

}
struct node* removeRear(struct list *list)
{
	if(list == NULL) return NULL;

	struct node *node = list->tail;

	if(getSize(list) == 1){
		list->head = NULL;
		list->tail = NULL;
	}else if (isEmpty(list)){
		return NULL;
	}else{
		node->prev->next = NULL;
		list->tail = node->prev;
	}

	list->size--;

	node->next = NULL;
	node->prev = NULL;

	return node;

}

struct node* removeNode(struct list *list, struct node *node)
{
	if(!list) return NULL;
	if(!node) return NULL;

	if(list->size == 1){
		list->head = NULL;
		list->tail = NULL;
	}	else{
		if(node == list->head){
			list->head = node->next;
			node->next->prev = NULL;
		}else if(isEmpty(list)){
			return NULL;
		}	
		else if(node == list->tail){
			list->tail = node->prev;
			node->prev->next = NULL;
		}
		else{
			node->next->prev = node->prev;
			node->prev->next = node->next;
		}
	}

	node->next = NULL;
	node->prev = NULL;
	list->size--;

	return node;
}

struct node* search(const struct list *list, const void *obj)
{
	if(!list) return NULL;

	struct node *currNode = list->head;

	while(currNode != NULL){
		if(list->equals(obj ,currNode->obj)){
			return currNode;
		}	
		currNode = currNode->next;
	}		


	return NULL;
}

void reverseList(struct list *list)
{
	if(list == NULL) return;
	struct node *currNode = list->head;
	struct node *prevNode = NULL;
	struct node *nextNode = NULL;
	list->tail = list->head;

	while(currNode != NULL){
		prevNode = currNode->prev;
		nextNode = currNode->next;
		currNode->next = prevNode;
		currNode->prev = nextNode;
		prevNode = currNode;
		currNode = nextNode;


	}

	list->head = prevNode;	


}
void printList(const struct list *list)
{
	if (!list) return; //list was null!!
	char *output;
	struct node *temp = list->head;
	while (temp) {
		output = list->toString(temp->obj);
		printf("%s\n",output);
		free(output);
		temp = temp->next;
	}
}
