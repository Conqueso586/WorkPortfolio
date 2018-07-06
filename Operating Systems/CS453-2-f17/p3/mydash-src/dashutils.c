#include <mydash.h>
#include <pwd.h>
#include <unistd.h>
#include <List.h>

struct programObject {
	int processNum;
	int pid;
	char *process;
};

struct programObject *createObject(int processNum, int pid, char *process, int processSize){

	struct programObject *Object = malloc(sizeof(struct programObject));

	Object->processNum = processNum;
	Object->pid = pid;
	Object->process = process;

	return Object;
}

void chdirectory(char *line){
	char bufProg[MAXLINE];
	char *bufArg;
	strcpy(bufProg, line);

	bufProg[strlen(bufProg)] = '\0';
	int i = 0;
	while((char) bufProg[i] != ' ' && (char) bufProg[i] != '\0'){
		i++;
	}
	if(bufProg[i] == '\0'){
		chdir(getpwuid(getuid())->pw_dir);
	}else{
		bufArg = strtok(&bufProg[i+1], " ");
	}
	if(chdir(bufArg))
		err_ret("Invalid arg: %s", bufArg);
}

void startProgram (char *line){
	char bufProg[MAXLINE];
	char *bufArg;
	char *token;
	strcpy(bufProg, line);
	token = strtok(line, " ");

	bufProg[strlen(bufProg)] = '\0';
	int i = 0;
	while((char) bufProg[i] != ' ' && (char) bufProg[i] != '\0'){
		i++;
	}
	if(bufProg[i] == '\0'){
		bufArg = NULL;
	}else{
		bufArg = &bufProg[i+1];
	}

	execlp(token, token, bufArg, (char *) NULL);
	err_ret("couldn't execute: %s", bufProg);
	exit(EXIT_FAILURE);
}

int backgroundCheck(char *line){

	char bufLine[MAXLINE];
	strcpy(bufLine, line);
	int x = strlen(bufLine) - 1;
	while((char) bufLine[x] == ' ' && x < 0){ 
		bufLine[x] = '\0';
		x--;
	}
	if((char)bufLine[x] == '&'){
		bufLine[x] = '\0';
		x--;
		while(bufLine[x] == ' '){
			bufLine[x] = '\0';
			x--;
		}
		strcpy(line, bufLine);	
		return 0;
	}

	return 1;

}

int equals(const void *obj, const void *other){

	struct programObject *object = (struct programObject *)obj;
	struct programObject *otherObject = (struct programObject *)other;
	int o1 =  object->pid;
	int o2 =  otherObject->pid;

	return o1 == o2;

}

char *toString(const void *obj){

	struct programObject *thisObject = (struct programObject*)obj;
	int thisNum = thisObject->processNum;
	int thisPid = thisObject->pid;
	char *thisProcess = (char *)thisObject->process;
	char *thisString = "";

	thisString = (char *)malloc((sizeof(char) * strlen(thisProcess))  + 14);
	sprintf(thisString ,"[%d] %d %s &", thisNum, thisPid, thisProcess);	

	return thisString;
}


void freeObject(void *obj){
	struct programObject *thisObject = (struct programObject*)obj;
	free(thisObject);

}
