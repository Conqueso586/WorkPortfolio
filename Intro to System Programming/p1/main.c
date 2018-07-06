#include <stdio.h>

#define IN 1 
#define OUT 0

int main()
{
  /*
    Put your c
ode here. You should create as many functions as necessary to create a modular program. We should not 
    see ALL your code in the main function! 
  */

int c, i, charCount, lineCount, wordCount, state;
int digitCount[10];

 state = OUT;
 charCount = lineCount = wordCount = 0;

for(i = 0; i < 10; i++){
	digitCount[i] = 0;
}

while((c = getchar()) != EOF){
	++charCount;

	if(c == '\n'){
		++lineCount;
	}

	if(c >= '0' && c <= '9' ){
		++digitCount[c - '0'];
	}

	if(c == ' ' || c == '\n' || c == '\t'){
	state = OUT;
	}
	else if (state == OUT){
	state = IN;
	wordCount++;

	}


	}
     
  		printf("Number of Chars:%d \nNumber of Lines: %d \nNumber of Words:%d \n",charCount, lineCount, wordCount);

		for(i = 0; i < 10; i++){
        	printf("digit %d: %d \n", i , digitCount[i]);
	}

	return 1;

}
