package Practices.SecondTask;

import lombok.Getter;
import lombok.Setter;

public class BankAccount {

    public BankAccount(int money) {
        this.money = money;
    }

    private int money;

    public int checkMoney() { return money; }

    public void putMoney(int quantity) { money += quantity; }

    public int withdrawMoney() {
        int withdraw = money;
        money = 0;
        return withdraw;
    }

    public double checkInDollars() {
        return money / ExchangeRates.getDollarRate();
    }

    public double checkInEuros() {
        return money / ExchangeRates.getEuroRate();
    }
}
