

Account subclass: SavingsAccount [
	|interest|

	SavingsAccount class: number customer balance [
	super number = number.
	super customer = customer.
	super balance = balance.
]
	accrue:rate [
	super balance = balance + balance * rate.
	interest = balance + balance * rate.
]

]
