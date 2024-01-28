import java.util.Scanner;

public class CheckingAccount extends BankAccount{
	/* Scanner Object */
	Scanner scr = new Scanner(System.in);
	
	/* Fields */
	double interestRate;
	int overdraftFees;
	
	/* Methods */
	
	// Parameterized Constructor
	public CheckingAccount (String firstName, String lastName, int accountID, int pinNumber, double balance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountID = accountID;
		this.pinNumber = pinNumber;
		this.balance = balance;
		
	}
	
	// Process Withdrawal
	
	public double processWithdrawal(double withdrawAmount) {
		if (getBalance() < withdrawAmount) {
			System.out.println("A $30 overdraft fee has been assessed");
			double overdraftFees = 30;
			withdrawAmount += overdraftFees;
			
			setBalance(getBalance() - withdrawAmount);
			System.out.println("Updated balance: " + getBalance());
		} else {
			setBalance(getBalance() - withdrawAmount);
			System.out.println("Updated balance: " + getBalance());
		}
		return getBalance();
	}
	
	// Display Account
	public String displayAccount() {
		accountSummary();
		interestRate = 0.01;
		String displayInfo = 	"First Name: " + getFirstName() + "\n" +
								"Last Name: " + getLastName() + "\n" +
								"Account ID: " + getAccountID() + "\n" +
								"Balance: " + getBalance() + "\n" +
								"Interest rate: " + interestRate + "\n";
		return displayInfo;
	}
	
}