#include <string.h>
#include <stdio.h>
#include <time.h>
#include "ring.h"
#include <signal.h>
#include <unistd.h>
#include <pthread.h>
#include <limits.h>

static struct {
	int curr;
	char log[MAX_LOG_ENTRY][MAX_STRING_LENGTH];
	pthread_mutex_t mutex;
} buff;

int j = 0;
void onalarm(int signo);

void dump_buffer();

void init_buffer()
{
	printf("Initialize the ring buffer\n");
	int i;
	for(i = 0; i < MAX_LOG_ENTRY; i++) {
		buff.log[i][0]='\0';
	}
	buff.curr = 0; 
	signal(SIGALRM, onalarm);
	alarm(alarm_interval);
	pthread_mutex_init(&(buff.mutex), NULL);
}

static char *getTimeString() 
{
	time_t myTime;
	myTime = time(NULL); 
	char *timeString = ctime(&myTime);
	timeString[strlen(timeString)-1] = '\0'; 
	return timeString;
}


void log_msg(char *entry)
{
	if (entry == NULL) {
		printf("Skipping null log entry!\n");
		return;
	}

	char *timeString = getTimeString();
	pthread_mutex_lock(&(buff.mutex));
	int idx = buff.curr % MAX_LOG_ENTRY;
	strncpy(buff.log[idx], timeString, MAX_STRING_LENGTH - 1);
	strncat(buff.log[idx], " -- ", 4);
	strncat(buff.log[idx], entry, MAX_STRING_LENGTH - strlen(timeString) - 4);

	buff.log[idx][MAX_STRING_LENGTH - 1]='\0';
	if(buff.curr < INT_MAX) buff.curr++;
	else buff.curr = 0;
	pthread_mutex_unlock(&(buff.mutex));
}

void onalarm(int signo){
	pthread_mutex_lock(&(buff.mutex));
	dump_buffer();
	alarm(alarm_interval);
	pthread_mutex_unlock(&(buff.mutex));
}

/*
 *This function will write all current log entries to a file called log_name 
 *in the main directory.
 */
void dump_buffer()
{
	int i;
	FILE *fs;
	fs = fopen(log_name, "w");
	for(i = 0; i < MAX_LOG_ENTRY; i++) {
		fprintf(fs, "log %d: %s\n", j , buff.log[(buff.curr + i) % MAX_LOG_ENTRY]);
		j++;   
	}
}


