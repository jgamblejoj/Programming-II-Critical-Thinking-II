import javax.swing.*;
import java.awt.Color;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// *** Packages ***/
		Scanner scr = new Scanner(System.in);
		
		// Account Set-up and error handling -- HAPPENS IN THE SYSTEM CONSOLE
		System.out.println("Enter first name: ");
		String firstName = scr.nextLine();
		System.out.println("Enter last name: ");
		String lastName = scr.nextLine();
		System.out.println("Enter account number: ");
		System.out.println("Must be a 3-digit number");
		int accountID = scr.nextInt();
			// Add error handling loop
		System.out.println("Enter PIN number: ");
		System.out.println("Must be a 4-digit number");
		int pinNumber = scr.nextInt();
			// Add error handling loop
		System.out.println("Enter account starting balance: ");
		double balance = scr.nextDouble();
		
												// End of Account set-up in CONSOLE
		CheckingAccount userAccount = new CheckingAccount(firstName, lastName, accountID, pinNumber, balance);
		
		/*** GUI Components ***/
		JFrame frame = new JFrame("CSU Banking");
		frame.setLayout(null);
		frame.setSize(350, 100);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		JTextArea sysMessage = new JTextArea(450, 200);
		sysMessage.setEditable(false);
		
		// Button Components
		JButton deposit = new JButton("DEPOSIT");
		JButton withdraw = new JButton("WITHDRAW");
		JButton summary = new JButton("SUMMARY");
		JButton quit = new JButton("QUIT");

		/* Program Start */
		JButton menu = new JButton("MAIN MENU");
		menu.setSize(100, 50);
		menu.setVerticalAlignment(JButton.CENTER);
		menu.setVisible(true);
		menu.addActionListener(e -> {
			// Attempts made
			int attempts = 0;
			// Dialog on button press
			while (attempts < 3) {
				String validationCheck = JOptionPane.showInputDialog(sysMessage, "Enter your PIN:");
				if (validationCheck.equals(String.valueOf(pinNumber))) {
					// Successful login
					attempts = 0;
					// Show Main Menu Buttons
					menu.setVisible(false);
					deposit.setVisible(true);
					withdraw.setVisible(true);
					summary.setVisible(true);
					quit.setVisible(true);
					break;
				} else {
					JOptionPane.showMessageDialog(sysMessage, "Please try again...");
					attempts++;
				}
			}
			if (attempts > 3) {
				JOptionPane.showMessageDialog(sysMessage, "Too many attempts. Account Locked.");
				frame.dispose();
				System.exit(0);
				scr.close();
			}
		});
		
		// Hide buttons before validationCheck
		deposit.setVisible(false);
		withdraw.setVisible(false);
		summary.setVisible(false);
		quit.setVisible(false);
		
		// Add panel to frame and components to panel
		frame.getContentPane().add(panel);
		panel.add(menu);
		panel.add(deposit);
		panel.add(withdraw);
		panel.add(summary);
		panel.add(quit);
		
		// Component sizes and colors
		panel.setSize(350, 100);
		Color lightPeach = new Color(232, 223, 195);
		panel.setBackground(lightPeach);
		Color white = new Color(255, 255, 255);
		sysMessage.setBackground(white);
		menu.setSize(100, 50);
		menu.setVerticalAlignment(JButton.CENTER);
		menu.setHorizontalAlignment(JButton.CENTER);
		menu.setBackground(white);
		deposit.setSize(75, 50);
		deposit.setBackground(white);
		withdraw.setSize(75, 50);
		withdraw.setBackground(white);
		summary.setSize(75, 50);
		summary.setBackground(white);
		quit.setSize(75, 50);
		quit.setBackground(white);
		
		// Button Press -- Deposit
		deposit.addActionListener(e -> {
			String depositAmountStr = JOptionPane.showInputDialog(sysMessage, "Enter a deposit amount: ");
			
			if (depositAmountStr != null) {
				try {
					double depositAmount = Double.parseDouble(depositAmountStr);
					userAccount.deposit(depositAmount);
					JOptionPane.showMessageDialog(sysMessage, "Updated balance: " + userAccount.getBalance());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(sysMessage, "Invalid input. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// Button Press -- Withdraw
		withdraw.addActionListener(e -> {
			String withdrawAmountStr = JOptionPane.showInputDialog(sysMessage, "Enter a withdrawal amount: ");
			
			if (withdrawAmountStr != null) {
				try {
					double withdrawAmount = Double.parseDouble(withdrawAmountStr);
					userAccount.processWithdrawal(withdrawAmount);
					JOptionPane.showMessageDialog(sysMessage, "Updated balance: " + userAccount.getBalance());
				} catch (NumberFormatException ex) {
					String accountSummary = userAccount.displayAccount();
					JOptionPane.showMessageDialog(sysMessage, "Invalid input. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// Button Press -- Summary
		summary.addActionListener(e -> {
			JOptionPane.showMessageDialog(sysMessage, userAccount.displayAccount());
		});
		
		// Button Press -- Quit
		quit.addActionListener(e -> {
			// Exit the GUI, end application, and close scanner
			int choice = JOptionPane.showConfirmDialog(sysMessage, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
			
			// Check user's choice
			if (choice == JOptionPane.YES_OPTION) {
				frame.dispose();
				System.exit(0);
				scr.close();
			} 
		});
	}
}