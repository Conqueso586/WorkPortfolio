#build the grader from the file testthreads.c
export LD_LIBRARY_PATH=../lib/

CC=gcc
CFLAGS=-Wall -g -I../include -pthread -D_GNU_SOURCE

grader: test-threads.c
	$(CC) $(CFLAGS) -o $@ $^ -L../lib/ -lring

clean:
	/bin/rm -f *.o grader
