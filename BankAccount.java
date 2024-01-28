import java.util.Scanner;

public class BankAccount {
	// Scanner
	Scanner scr = new Scanner(System.in)
			;
	/* Attributes */
	public String firstName;
	public String lastName;
	public int accountID;
	public int pinNumber;
	public double balance;
	
	/* Getters */
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getAccountID() {
		return accountID;
	}
	protected double getBalance() {
		return balance;
	}
	protected int getPinNumber() {
		return pinNumber;
	}
	
	/* Setters */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/* Methods */
	
	// Default Constructor
	public BankAccount() {
		firstName = "John";
		lastName = "Doe";
		accountID = 123;
		pinNumber = 1234;
		balance = 0.00;
	}
	// Parameterized Constructor
	public BankAccount(String firstName, String lastName, int accountID, int pinNumber, double balance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountID = accountID;
		this.pinNumber = pinNumber;
		this.balance = balance;
	}
	// Deposit
	public void deposit(double depositAmount) {
		setBalance(depositAmount + getBalance());
		System.out.println("Updated balance: " + balance);
	}
	// Withdrawal
	public void withdraw() {
		System.out.println("Enter withdrawal amount: ");
		double userInput = scr.nextDouble();
		if (userInput != 0 && userInput < this.balance) {
			double withdraw = this.balance - userInput;
			System.out.println("Updated balance: " + withdraw);
		} else {
			System.out.println("Insufficient funds");
		}
	}
	// Print account attributes
	public String accountSummary() {
		String accountInfo = 	"First Name: " + getFirstName() + "\n" +
								"Last Name: " + getLastName() + "\n" +
								"Account ID: " + getAccountID() + "\n" +
								"Balance: " + getBalance();
		
		return accountInfo;
	}
	
}