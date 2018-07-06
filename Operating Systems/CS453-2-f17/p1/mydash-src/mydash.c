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


static int job = 1;
static int bg = 0;

int main(int argc, const char *argv[])
{
	char	*line;
	pid_t   pid;
	int     status;
	char	*prompt = getenv("DASH_PROMPT");
	struct list *list = createList(equals, toString, freeObject);
	struct programObject *object;

	if(argc == 2){
		if(!strcmp(argv[1], "-v")){
			printf("Project Version: %d \n",git_version());
		}}else{

			while ((line = readline(prompt))) {

				if(!strncmp(line, "cd", 2)){
					chdirectory(line);
				}else if(!strncmp(line, "jobs", 4)){
					printList(list);

				}
				else{

					if(!strncmp(line, "exit", 5) ||  line == NULL)
						break;
					if(strncmp(line, "", 4)){
						bg = backgroundCheck(line);
						if ( (pid = fork()) < 0)
							err_sys("fork error");

						else if (pid == 0) {        /* child */

							startProgram(line);
							if(bg == 0){
								pid = waitpid(pid,&status,WNOHANG);
								object = createObject(0,pid,"", 5);
								struct node *node = search(list, object);
								object = node->obj;
								object->done = 1;
								free(object);

							}}else{
								if(bg == 0){	
									char *token = strtok(line, " ");	
									object = createObject(job, pid, token, strlen(token));
									free(toString(object));

									struct node *node;
									node = createNode(object);
									if(isEmpty(list)){
										addAtFront(list,node);
									}
									else{
										addAtRear(list,node);
									}
									job++;
									printList(list);
								}
								/* parent */
								if (bg == 1 && (pid = waitpid(pid, &status, 0)) < 0)
									err_sys("waitpid error");



								if (bg == 0 && (pid = waitpid(pid, &status, WNOHANG)) < 0)
									err_sys("waitpid error");

								
							}}}
				add_history(line);

				bg = 0;
			}
			freeList(list);
			free(line);
			exit(EXIT_SUCCESS);
		}}


