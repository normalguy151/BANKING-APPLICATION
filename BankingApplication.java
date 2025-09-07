import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;
    
    public Account(int accountNumber, String accountHolderName, double initialBalance, 
                   String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
     
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
            System.out.println("New balance: $" + balance);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }
     
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            System.out.println("New balance: $" + balance);
        }
    }
     
    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: $" + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("----------------------");
    }
     
    public void updateContactDetails(String newEmail, String newPhone) {
        this.email = newEmail;
        this.phoneNumber = newPhone;
        System.out.println("Contact details updated successfully!");
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
}
 
class UserInterface {
    private Account[] accounts;
    private int totalAccounts;
    private Scanner scanner;
    private int nextAccountNumber;
     
    public UserInterface() {
        accounts = new Account[50];
        totalAccounts = 0;
        scanner = new Scanner(System.in);
        nextAccountNumber = 1001;
    }
     
    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter initial deposit amount: ");
        double initialAmount = scanner.nextDouble();
        scanner.nextLine();
        
        if (initialAmount < 0) {
            System.out.println("Initial deposit cannot be negative!");
            return;
        }
        
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        Account newAccount = new Account(nextAccountNumber, name, initialAmount, email, phone);
        accounts[totalAccounts] = newAccount;
        totalAccounts++;
        
        System.out.println("Account created successfully with Account Number: " + nextAccountNumber);
        nextAccountNumber++;
    }
    
     
    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        
        Account account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }
    
     
    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        
        Account account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }
    
    
    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        
        Account account = findAccount(accNum);
        if (account != null) {
            account.displayAccountDetails();
        } else {
            System.out.println("Account not found!");
        }
    }
    
    
    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        scanner.nextLine();
        
        Account account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String newPhone = scanner.nextLine();
            
            account.updateContactDetails(newEmail, newPhone);
        } else {
            System.out.println("Account not found!");
        }
    }
    
    
    private Account findAccount(int accountNumber) {
        for (int i = 0; i < totalAccounts; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }
    
    
    public void mainMenu() {
        int choice;
        
        do {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Thank you for using Banking Application!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
    }
}

 
public class BankingApplication {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}