Object subclass: Account [
	|number|
	|customer|
	|balance|

	accrue: rate [
	^self subclassResponsibility
]
	balance [
	^balance
]
	deposit: amount [
	balance:=balance + amount.
]

	withdraw: amount [
	balance:= balance - amount.
]

	toString [
	|s|
	s:=s, number,':', customer,':', balance.

	^s
]

]

