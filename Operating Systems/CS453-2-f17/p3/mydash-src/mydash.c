/*
   This program needs the file error.c and ourhdr.h to compile.
   */
#include    <sys/types.h>
#include    <sys/wait.h>
#include    "mydash.h"
#include	<readline/readline.h>
#include	<readline/history.h>
#include	<stdio.h>
#include	<stdlib.h>
#include	<pwd.h>
#include	<unistd.h>
#include	<dashutils.h>
#include	<List.h>
#include	<setjmp.h>


static int job = 1;
static int bg = 0;
int printDone = 0;
struct list *list; 
struct programObject *object;
char	*line;
pid_t   pid, stoppedPid;
int     status;
int jobNum[100];
char	*prompt = "myDash>";
jmp_buf env;
char* git_version();
int main(int argc, const char *argv[])
{

	if(getenv("DASH_PROMPT") != NULL)
		prompt = getenv("DASH_PROMPT");
	jobNum[0] = -1;

	list = createList(equals, toString, freeObject);
	stoppedPid = sigsetjmp(env, TRUE);

	if(argc == 2){
		if(!strcmp(argv[1], "-v")){
			char* version= git_version();
			printf("Project Version: %s \n",version);
		}
	}
	else{
		while ((line = readline(prompt))) {
			signal(SIGINT, sig_handler);
			signal(SIGTSTP, sig_handler);

			if(!strncmp(line, "fg", 2)){
				kill(stoppedPid, SIGCONT);
				continue;
			}
			if(!strncmp(line, "cd", 2)){
				chdirectory(line);
			}else if(!strncmp(line, "jobs", 4)){
				doneJobs();
				printList(list);
			}else if(!strncmp(line, "exit", 5) ||  line == NULL)
				break;
			else{
				evaluateLine();
			}
			add_history(line);
			bg = 0;
		}
		freeList(list);
		free(line);
		exit(EXIT_SUCCESS);
	}}

void doneJobs(){
	pid_t thisPid;
	while((thisPid = waitpid(-1, &status, WNOHANG)) > 0){
		object = createObject(0,thisPid,"", 5);
		struct node *node = search(list, object);
		object = (struct programObject *)node->obj;
		int thisNum = object->processNum;
		char *thisProcess = object->process;
		printf("[%d] Done %s &\n", thisNum, thisProcess);
		jobNum[thisNum] = -1;
		free(removeNode(list, node));
	}
	findLargestJobNum();

}
void findLargestJobNum(){
	int i;
	int largest = 0;
	for(i = 1; i <= job; i++){
		if(jobNum[i] > largest){
			largest = jobNum[i];
		}
	}

	job = largest + 1;
}
void parentMethod(){
	
	if(bg == 0){	
		object = createObject(job, pid, line, strlen(line));
		free(toString(object));
		printf("[%d] %d %s & \n", job, pid, line);
		struct node *node;
		node = createNode(object);
		if(isEmpty(list)){
			addAtFront(list,node);
		}
		else{
			addAtRear(list,node);
		}
		doneJobs();
		jobNum[job] = job;


	}
	
	doneJobs();
	/* parent */
	signal(SIGINT, sig_handler);
	signal(SIGTSTP, sig_handler);

	if (bg == 1) wait(&status);

	

}
void evaluateLine(){
	if(strncmp(line, "", 4)){
		bg = backgroundCheck(line);
		if ( (pid = fork()) < 0)
			err_sys("fork error");
		else if (pid == 0) {    
			/* child */
		if(bg == 0){
		signal(SIGTSTP, SIG_IGN);
		signal(SIGINT, SIG_IGN);
		}
			startProgram(line);
		}else{
			parentMethod();
		}}
}
void sig_handler(int signo){

	switch(signo){
		case SIGINT:
			printf("Caught Control-c\n");
			fflush(NULL);
			break;
		case SIGTSTP:
			printf("Caught Ctrl-z\n");
			fflush(NULL);
			break;
	}
	siglongjmp(env, pid);
	return;
}
void stop(){

	printf("stopping");

}



