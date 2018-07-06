#ifndef __DASHUTILS_H
#define __DASHUTILS_H

typedef struct list *listptr;
#include "Node.h"

struct programObject {

int processNum;
int pid;
char *process;



};

struct programObject *createObject(int processNum, int pid, char *program, int processSize);
void startProgram(char *line);
void    chdirectory(char *line);
int	backgroundCheck(char *line);
int	equals(const void *obj, const void *other);
char	*toString (const void *obj);
void	freeObject(void *obj);


#endif	/* __DASHUTILS_H */
