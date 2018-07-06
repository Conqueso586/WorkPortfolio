/* 
 *
 *@Connor Nagel
 *
 *This program takes in a CSV file and then reads through each line to edit
 *the file to meet the specified format.
 *
 *
 * */


#include <stdio.h>
#include <string.h>

#define START 0
#define END 1
#define NOT_QUOTED 2
#define QUOTED 3
#define CHECK_DOUBLE_QUOTED 4
#define DOUBLE_QUOTED 5
#define END_QUOTED 6

int main(int argc, char *argv[])
{

	char c;
	char *lineptr;	
	size_t len = 0;
	int state = START;
	int i = 0;
	ssize_t read;
	
	//This while loop gets each line from the CSV file

	while((read = getline(&lineptr, &len, stdin)) != -1){
		state = START;

	//The for loop loops through each character of the line and then using the
	//state machine to edit the line and fit the format.
		for(i = 0; i < strlen(lineptr); i++){

			c = lineptr[i];

			if(state == END){
				state = START;
			}

			if(state == START){
				if(c == '"'){
					putchar(c);
					state = QUOTED;
				}
				else if(c == ','){
					printf("XXXXXX,");
					state = END;
				}
				else if(c == '\n'){
					printf("XXXXXX");
					putchar(c);
				}
				else{
					putchar(c);
					state = NOT_QUOTED;
				}

			}
			else if(state == NOT_QUOTED){
				if(c == ','){
					putchar(c);
					state = END;
				}
				else{
					putchar(c);
					state = NOT_QUOTED;
				}
			}
			else if(state == QUOTED){
				if(c == '"'){
					putchar(c);
					state = CHECK_DOUBLE_QUOTED;

				}else{
					putchar(c);
					state = QUOTED;
				}
			}
			else if(state == CHECK_DOUBLE_QUOTED){
				if(c == '"'){
					putchar(c);
					state = DOUBLE_QUOTED;
				}else if(c == ','){
					putchar(c);
					state = START;
				}
			}
			else if(state == DOUBLE_QUOTED){
				if(c =='"'){
					putchar(c);
					state = END_QUOTED;
				}
				else{
					putchar(c);
				}

			}
			else if(state == END_QUOTED){

				if(c == '"'){
					putchar(c);
					state = QUOTED;
				}
			}


		}
	}
	return 0;
}		


