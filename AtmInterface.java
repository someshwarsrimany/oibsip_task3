
import java.util.Scanner;

class BankAccount {
	
	String name;
	String userName;
	String password;
	String accountNo;
	float balance = 500000;
	int transactions = 0;
	String transactionHistory = "";
	

	
	public void register() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Your Name: ");
		this.name = sc.nextLine();
		System.out.print("Enter Your Username: ");
		this.userName = sc.nextLine();
		System.out.print("Enter Your Password: ");
		this.password = sc.nextLine();
		System.out.print("Enter Your Account Number: ");
		this.accountNo = sc.nextLine();
		System.out.println("\nRegistration completed.Welcome to our family,You can know login to your account");
	}
	
	public boolean login() {
		boolean isLogin = false;
		Scanner sc = new Scanner(System.in);
		while ( !isLogin ) {
			System.out.print("\nEnter Your Username:  ");
			String Username = sc.nextLine();
			if ( Username.equals(userName) ) {
				while ( !isLogin ) {
					System.out.print("Enter Your Password: ");
					String Password = sc.nextLine();
					if ( Password.equals(password) ) {
						System.out.print("Login successful!!");
						isLogin = true;
					}
					else {
						System.out.println("\nIncorrect Password");
					}
				}
			}
			else {
				System.out.println("\n Invalid Username");
			}
		}
		return isLogin;
	}
	
	public void withdraw() {
		
		System.out.print("\nEnter Amount to be Withdrawn: Rs. ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		try {
			
			if ( balance >= amount ) {
				transactions++;
				balance -= amount;
				System.out.println("\nWithdrawn Successfully");
				String str = "Rs. "+amount + " Withdrawed\n";
				transactionHistory = transactionHistory.concat(str);
				
			}
			else {
				System.out.println("\nInsufficient Balance");
			}
			
		}
		catch ( Exception e) {
		}
	}
	
	public void deposit() {
		
		System.out.print("\nEnter The Amount to Deposit: ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		
		try {
			if ( amount <= 40000f ) {
				transactions++;
				balance += amount;
				System.out.println("\nSuccessfully Deposited.");
				String str ="Rs. "+ amount + " deposited\n";
				transactionHistory = transactionHistory.concat(str);
			}
			else {
				System.out.println("\nCurrently limit is Rs.40000.00");
			}
			
		}
		catch ( Exception e) {
		}
	}
	
	public void transfer() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Receipent's Name: ");
		String receipent = sc.nextLine();
		System.out.print("\nEnter Amount to Transfer: ");
		float amount = sc.nextFloat();
		
		try {
			if ( balance >= amount ) {
				if ( amount <= 10000 ) {
					transactions++;
					balance -= amount;
					System.out.println("\nSuccessfully Transfered to " + receipent);
					String str = "Rs. "+amount + " transfered to " + receipent + "\n";
					transactionHistory = transactionHistory.concat(str);
				}
				else {
					System.out.println("\n Currently limit is Rs.10000.00");
				}
			}
			else {
				System.out.println("\nInsufficient Balance");
			}
		}
		catch ( Exception e) {
		}
	}
	
	public void checkBalance() {
		System.out.println(" Rs" + balance );
	}
	
	public void transHistory() {
		if ( transactions == 0 ) {
			System.out.println("\nEmpty");
		}
		else {
			System.out.println("\n" + transactionHistory);
		}
	}
}

public class AtmInterface {
	public static int ValidInput(int limit) {
		int input = 0;
		boolean flag = false;
		
		while ( !flag ) {
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				flag = true;
				
				if ( flag && input > limit || input < 1 ) {
					System.out.print("Choose the number between 1 to " + limit+": ");
					flag = false;
				}
			}
			catch ( Exception e ) {
				System.out.print("Enter Only Integer Value: ");
				flag = false;
			}
		};
		return input;
	}
	
	
	public static void main(String[] args) {
		Outer:
			do {
				System.out.println("\n*********WELCOME TO BANK OF SOMESHWAR*********\n");
				System.out.println("1.Register \n2.Exit");
				System.out.print("Enter Your Choice:  ");
				int choice = ValidInput(2);
				
				if ( choice == 1 ) {
					BankAccount b = new BankAccount();
					b.register();
					while(true) {
						System.out.println("\n1.Login \n2.Exit");
						System.out.print("Enter Your Choice : ");
						int ch = ValidInput(2);
						if ( ch == 1 ) {
							if (b.login()) {
								System.out.println("\nWelcome Mr."+b.name);
								System.out.println("\n\n**********SOMESHWAR'S BANK IS HAPPY TO HAVE YOU HERE AGAIN  **********\n");
								boolean isFinished = false;
								while (!isFinished) {
									System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
									System.out.print("\nEnter Your Choice: ");
									int c = ValidInput(6);
									switch(c) {
										case 1:
										b.withdraw();
										break;
										case 2:
										b.deposit();
										break;
										case 3:
										b.transfer();
										break;
										case 4:
										b.checkBalance();
										break;
										case 5:
										b.transHistory();
										break;
										case 6:
										isFinished = true;
										break;
									}
								}
							}
						}
						else {
							continue Outer;
						}
					}
				}
				else {
					System.exit(0);
				}
				
				
				
			}while(true);
	}
}
