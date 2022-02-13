# BankApplication
Bank application

Backend:
-Register
-Login
-Transfer money
-Deposit money
-Withdraw money

DB (USER):
	ID: long
	//nric need to check if it is valid
	//check online where to get the singapore NRIC
	NRIC: string
	NAME: string
	ADDRESS: string
	DEPOSIT AMOUNT: BIGINTEGER
	PHONE NUMBER: int
	
Transfer money:
//No need check payer, because it is "logged in" as the payer
-Check valid payee PHONE NUMBER
-Cannot less than current deposit amount
-Must be valid number
