"gst CheckingAccount.st"


Account subclass: CheckingAccount [
	


	CheckingAccount class [
	new: number customer balance[
	^super new
	super number:=number.
	super customer:=customer.
	super balance:=balance.
]]

	accrue: rate [
]
]
