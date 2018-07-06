#!/bin/gprolog --consult-file

:- include('data.pl').
:- include('uniq.pl').
:- include('meetone.pl').






constrainMeetingTime(Slot, Slot2):- compareTime(Slot, Slot2).

compareSched([], _).
compareSched([H|T], Slot):- free(H, Slot2), isWithinTimeLimit(Slot, Slot2), compareSched(T, Slot).
firstPerson([H|T], Slot):- free(H,Slot), compareSched(T,Slot).

meet(Slot):- people(Peeps), compareSched(Peeps, Slot).

people([ann,bob,carla]).

main :- findall(Slot, meet(Slot), Slots),
        uniq(Slots, Uniq),
        write(Uniq), nl, halt.

:- initialization(main).
