package Practices.SecondTask;

public class SecondMain {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount(1000);
        System.out.println("Account created. Actual value of money: " + bankAccount.checkMoney());
        bankAccount.putMoney(5000);
        System.out.println("Value after refill: " + bankAccount.checkMoney());
        System.out.println("Value after refill in dollars: " + bankAccount.checkInDollars());
        System.out.println("Value after refill in euros: " + bankAccount.checkInEuros());
        int money = bankAccount.withdrawMoney();
        System.out.println("Value after cash withdrawal: " + bankAccount.checkMoney());
        System.out.println("Cash withdrawal value: " + money);
    }
}
