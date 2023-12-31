import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("\u001B[31mInsufficient funds. Withdrawal failed.\u001B[0m");
            return false;
        }
        balance -= amount;
        return true;
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount account) {
        this.userAccount = account;
    }

    public void displayMenu() {
        System.out.println("\n\033[1m*******************************************\033[0m");
        System.out.println("\033[1m                  ATM Menu\033[0m");
        System.out.println("\033[1m*******************************************\033[0m");
        System.out.println("1. \u001B[34mWithdraw\u001B[0m");
        System.out.println("2. \u001B[32mDeposit\u001B[0m");
        System.out.println("3. \u001B[33mCheck Balance\u001B[0m");
        System.out.println("0. \u001B[31mExit\u001B[0m");
        System.out.println("\033[1m*******************************************\033[0m");
    }

    public void performTransaction(int option, double amount) {
        switch (option) {
            case 1:
                withdraw(amount);
                break;
            case 2:
                deposit(amount);
                break;
            case 3:
                checkBalance();
                break;
            case 0:
                System.out.println("\033[1mThank you for using the ATM. Goodbye!\033[0m");
                System.exit(0);
            default:
                System.out.println("\u001B[31mInvalid option. Please choose a valid option.\u001B[0m");
        }
    }

    private void withdraw(double amount) {
        if (userAccount.withdraw(amount)) {
            System.out.println("\u001B[32mWithdrawal successful.\u001B[0m Current balance: \u001B[33m$"
                    + userAccount.getBalance() + "\u001B[0m");
        }
    }

    private void deposit(double amount) {
        userAccount.deposit(amount);
        System.out.println("\u001B[32mDeposit successful.\u001B[0m Current balance: \u001B[33m$"
                + userAccount.getBalance() + "\u001B[0m");
    }

    private void checkBalance() {
        System.out.println("\u001B[33mCurrent balance: $" + userAccount.getBalance() + "\u001B[0m");
    }
}

public class Interface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial balance: $");
        double initialBalance = scanner.nextDouble();

        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);

        while (true) {
            atm.displayMenu();

            System.out.print("\u001B[36mChoose an option:\u001B[0m ");
            int option = scanner.nextInt();

            if (option != 0) {
                System.out.print("\u001B[36mEnter amount: $\u001B[0m");
                double amount = scanner.nextDouble();
                if (amount < 0) {
                    System.out.println("\u001B[31mInvalid amount. Please enter a positive value.\u001B[0m");
                    continue;
                }
                atm.performTransaction(option, amount);
            } else {
                atm.performTransaction(option, 0); // Exit the ATM
            }
        }
    }
}
