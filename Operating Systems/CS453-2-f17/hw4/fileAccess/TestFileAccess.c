#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include <time.h>
#include "FileAccess.h"

void *threadMain(void *);
pthread_t *tids;

FileAccessPtr fileAccess;

int numThreads;
int count;

pthread_mutex_t mutex;

void signalHandler(int);

int main(int argc, char **argv)
{
	int i;

	if (argc < 3) {
		fprintf(stderr, "Usage: %s <numThreads> <iterations> <max>\n", argv[0]);
		exit(1);
	}

	numThreads  = atoi(argv[1]);
	count = atoi(argv[2]);
	if (numThreads > 32) {
		fprintf(stderr, "Usage: %s Too many threads specified. Defaulting to 32.\n", argv[0]);
		numThreads = 32;
	}

	fileAccess = fileaccess_init(atoi(argv[3]));
	signal(SIGALRM, signalHandler);
	alarm(30);

	pthread_mutex_init(&mutex, NULL);
	tids = (pthread_t *) malloc(sizeof(pthread_t)*numThreads);
	for (i=0; i<numThreads; i++) {
		pthread_create(&tids[i], NULL, threadMain, (void *) NULL);
	}

	for (i=0; i<numThreads; i++)
		pthread_join(tids[i], NULL);

	pthread_mutex_destroy(&mutex);
	exit(0);
}


static int get_thread_id()
{
	int i;
	pthread_mutex_lock(&mutex);
	for (i=0; i<numThreads; i++)
	{
		if (tids[i] == pthread_self())
		{
			pthread_mutex_unlock(&mutex);
			return i;
		}
	}
	pthread_mutex_unlock(&mutex);
	return -1; /* we have a problem if we reach this statement */
}


void *threadMain(void *arg)
{
	int i;
	int sleepTime;
	time_t current;
	int id = get_thread_id() + 1;
	
	printf("Thread %d starting up \n", id);
	for (i=0; i<count; i++) {
		sleepTime = random() % 4 + 1; // 1 to 4 seconds 
		time(&current);		
		(*fileAccess->startAccess)(fileAccess, id);
		printf("Thread %d start file access for %d seconds \n", id, sleepTime);
		sleep(sleepTime);
		(*fileAccess->endAccess)(fileAccess, id);
		usleep(500);
		
		printf("Thread %d ending file access \n", id);
	}

	pthread_exit(NULL);
}

void signalHandler(int signo)
{
	printf("Exiting early, possible deadlock\n");
	exit(0); //reset alarm
}



