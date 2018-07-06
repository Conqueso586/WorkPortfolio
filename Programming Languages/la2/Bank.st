"gst sum.st"

Object subclass: Bank [

		 |accounts| accounts:=Set new.
		 <import: 'Customer.st'>
		 Bank class[

		 new[
			^super new.
		 ]
		 ]
		 add: account [
		 accounts add:account
		 ]

		 accrue: rate [
		 |r|
		 accounts do: [ :account |
		 r:= account , '\n'.
		 ].
		 ^r
		 ]

		 ]

		 |bank| bank:=Bank new.
		 |customer| customer:=Customer new.
		 customer named:'Ann'.
		 bank add: CheckingAccount new:'01001' customer '100.00'.
		 bank accrue: '0.02'.
		 bank printNl.
