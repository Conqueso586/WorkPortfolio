

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <pthread.h>
#include <math.h>

#define TRUE 1
#define FALSE 0

// function prototypes
void serial_mergesort(int A[], int p, int r); 
void merge(int A[], int p, int q, int r);
void insertion_sort(int A[], int p, int r);
void multiThreadSort(int A[], int numThreads, int arraySize);
const int INSERTION_SORT_THRESHOLD = 100; //based on trial and error


/*
 * insertion_sort(int A[], int p, int r):
 *
 * description: Sort the section of the array A[p..r].
 */
struct Args{
	void *array;	
	int first;
	int end;
};
static void *passArgs(void * structure){
	struct Args *arguments = (struct Args *) structure;
	serial_mergesort(arguments->array, arguments->first, arguments->end);
	return NULL;
}
void multiThreadSort(int A[], int numThreads, int arraySize){
	pthread_t *tids;
	struct Args *pieceOfArray;
	int thread_count = numThreads;
	int i;
	int size = arraySize;
	struct Args *mergedPiece;
	int mergedSize = (int)ceil(((double)thread_count)/2.0);

	tids = (pthread_t *)calloc((int)thread_count, sizeof(pthread_t));
	pieceOfArray = (struct Args *)calloc((int)thread_count, sizeof(struct Args));
	mergedPiece = (struct Args *)calloc((int)mergedSize, sizeof(struct Args));

	for(i = 0; i < thread_count; i++){
		pieceOfArray[i].array = A;
		pieceOfArray[i].first = (i * floor(size/thread_count)) + 1;
		pieceOfArray[i].end = ((i + 1) * floor(size/thread_count));

	}

	if(size % thread_count)
		pieceOfArray[thread_count - 1].end = size;


	for(i = 0; i < thread_count; i++)
		pthread_create(&tids[i], NULL, passArgs, &pieceOfArray[i]);

	for(i = 0; i < thread_count; i++)
		pthread_join(tids[i], NULL);


	if(thread_count  > 1){

		for(i = 0; i < thread_count/2; i++){
			merge(pieceOfArray[2*i].array, pieceOfArray[2*i].first, pieceOfArray[2*i].end, pieceOfArray[(2*i)+1].end);	     
			mergedPiece[i].array = pieceOfArray[2*i].array;
			mergedPiece[i].first = pieceOfArray[2*i].first;
			mergedPiece[i].end = pieceOfArray[(2*i) + 1].end;


		}
		if(thread_count % 2){
			mergedPiece[mergedSize -1].array = pieceOfArray[thread_count - 1].array;
			mergedPiece[mergedSize -1].first = pieceOfArray[thread_count - 1].first;
			mergedPiece[mergedSize -1].end = pieceOfArray[thread_count - 1].end;
		}
		for(i = 0; i < mergedSize/2; i++){
			merge(mergedPiece[2*i].array, mergedPiece[2*i].first, mergedPiece[2*i].end, mergedPiece[(2*i)+1].end);
			mergedPiece[i].array = mergedPiece[2*i].array;
			mergedPiece[i].first = mergedPiece[2*i].first;
			mergedPiece[i].end = mergedPiece[(2*i) + 1].end;


		}
		if(thread_count > 2 && mergedSize % 2){
			merge(mergedPiece[0].array, mergedPiece[0].first, mergedPiece[0].end, mergedPiece[2].end);
			
		}

		if(thread_count > 6)
			merge(mergedPiece[0].array, mergedPiece[0].first, mergedPiece[0].end, mergedPiece[1].end);

	

	}

}
void insertion_sort(int A[], int p, int r) 
{
	int j;

	for (j=p+1; j<=r; j++) {
		int key = A[j];
		int i = j-1;
		while ((i > p-1) && (A[i] > key)) {	
			A[i+1] = A[i];
			i--;
		}
		A[i+1] = key;
	}
}



/*
 * serial_mergesort(int A[], int p, int r):
 *
 * description: Sort the section of the array A[p..r].
 */
void serial_mergesort(int A[], int p, int r) 
{
	if (r-p+1 <= INSERTION_SORT_THRESHOLD)  {
		insertion_sort(A,p,r);
	} else {
		int q = (p+r)/2;

		serial_mergesort(A, p, q);
		serial_mergesort(A, q+1, r);
		merge(A, p, q, r);
	}
}



/*
 * merge(int A[], int p, int q, int r):
 *
 * description: Merge two sorted sequences A[p..q] and A[q+1..r] 
 *              and place merged output back in array A. Uses extra
 *              space proportional to A[p..r].
 */     
void merge(int A[], int p, int q, int r) 
{
	int *B = (int *) malloc(sizeof(int) * (r-p+1));

	int i = p;
	int j = q+1;
	int k = 0;
	int l;

	// as long as both lists have unexamined elements
	// this loop keeps executing.
	while ((i <= q) && (j <= r)) {
		if (A[i] < A[j]) {
			B[k] = A[i];
			i++;
		} else {
			B[k] = A[j];
			j++;
		}
		k++;
	}

	// now only at most one list has unprocessed elements.

	if (i <= q) { 
		// copy remaining elements from the first list
		for (l=i; l<=q; l++) {
			B[k] = A[l];
			k++;
		}
	} else {
		// copy remaining elements from the second list
		for (l=j; l<=r; l++) {
			B[k] = A[l];
			k++;
		}
	}

	// copy merged output from array B back to array A
	k=0;
	for (l=p; l<=r; l++) {
		A[l] = B[k];
		k++;
	}

	free(B);
}

