//Author: Connor Nagel

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define MAX_LENGTH 2048
#define MAX_TOKENS 100

int main(int argc, char **argv) 
{
	FILE *tf;
	char *line = malloc(sizeof(char) * MAX_LENGTH);
	char *word = NULL;
	int multi = 0;
	int count = 0;
	double percentage = 0;


	fprintf(stderr, "%s: running ps augx command.\n", argv[0]);
	
	if(argc != 2){
		fprintf(stderr, "Usage: ps-logger <sleep interval(seconds)>\n");
		exit(0);
	}
	if(atoi(argv[1]) < 1){
		fprintf(stderr, "Time interval too small. Try a value of 1 or greater");
		exit(0);
	}
	
	while (1) {
		system("ps augx > ps.log");
		tf = fopen("ps.log", "r");
			while(fgets(line, MAX_LENGTH, tf) > 0){
				
				word = strtok(line," ");
				for(int i =0; i < 7;i++){
					word = strtok(NULL," ");
				}
				if(strchr(word, 'l')){
					multi++;
				}
				else{
					count++;
				}
				
			}
			sleep(atoi(argv[1]));
			percentage = (double)multi / (double)count * 100;
		fprintf(stderr, "%d/%d Multi to Total Process Ratio [%.2f%]\n", multi, multi + count, percentage);
		count = multi = 0;
	}
			
	exit(0);
}

