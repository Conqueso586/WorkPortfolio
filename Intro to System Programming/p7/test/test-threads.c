#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "../include/ring.h"
#include <limits.h>
#include <pthread.h>
void *threadMain(void *);
int numThreads = 100;
int numLogs;

int main(int argc, char **argv){

	init_buffer();
	pthread_t *threadids;

	if(argc != 3){
		fprintf(stderr, "Usage: thread-ids <numThreads> <numLogs>\n");
		exit(EXIT_FAILURE);
}

	numThreads = atoi(argv[1]);
	numLogs = atoi(argv[2]);
	printf("%d\n",numThreads);

	threadids = (pthread_t*) malloc(sizeof(pthread_t)*numThreads);
	int i = 0;
	for(i = 0; i < numThreads; i++){
		printf("thread %d starting to log %d\n",i,numLogs);
		pthread_create(&threadids[i], NULL, threadMain, (void *) NULL);
}

	for(i = 0; i < numThreads; i++) pthread_join(threadids[i], NULL);

	printf("Sleeping for %d seconds to let signals finish\n" , alarm_interval);
	sleep(alarm_interval + 1);
	free(threadids);
	exit(EXIT_SUCCESS);
}

void *threadMain(void *numThreads){

	int i;
	char *msgInfo;
	
	for(i = 0; i < numLogs; i++){
		asprintf(&msgInfo, "[Thread %lu]test log %d", pthread_self(), i);
		log_msg(msgInfo);
		free(msgInfo);
}
		pthread_exit(NULL);
}
