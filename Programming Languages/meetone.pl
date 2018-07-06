#!/bin/gprolog --consult-file

:- include('data.pl').


checkBegin(time(Phr, Pmin,Pperiod), time(Thr, Tmin, Tperiod)):- 
	((((Phr<Thr);(Phr=:=Thr, Pmin=<Tmin)), Pperiod==Tperiod)). 
checkEnd(time(Phr, Pmin, Pperiod), time(Thr, Tmin, Tperiod)):- 
	((((Phr>Thr); (Phr=:=Thr, Pmin>=Tmin)), Pperiod==Tperiod)). 
	
compareTime(slot(PersonBegin, PersonEnd), slot(TimeBegin, TimeEnd)):- checkBegin(PersonBegin, TimeBegin), checkEnd(PersonEnd, TimeEnd).

meetone(Person,Time):- free(Person, Slot), compareTime(Slot, Time).


main :- findall(Person,
		meetone(Person,slot(time(8,30,am),time(8,45,am))),
		People),
	write(People), nl, halt.

:- initialization(main).
