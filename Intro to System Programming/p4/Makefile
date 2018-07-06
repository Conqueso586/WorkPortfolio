CC=gcc
CFLAGS=-Wall -g -MMD -I .
LFLAGS=
PROGS=main.c f1.c f2.c f3.c

all: $(PROGS) hello

run: all ./hello


hello:  f1.o main.c f2.o f3.o
	$(CC) $(CFLAGS) $^ -o $@ 


main.o: main.c
	$(CC) $(CFLAGS) -c $^

f1.o: f1.c
	$(CC) $(CFLAGS) -c $^

f2.o: f2.c
	$(CC) $(CFLAGS) -c $^

f3.o: f3.c
	$(CC) $(CFLAGS) -c $^




-include *.d


clean:

	rm -f *.o *d *.h.gch hello

