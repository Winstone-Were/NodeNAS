import java.util.ArrayList;
import java.util.Random;

public class CoinBag {
    public static void main(String[] args) {
        Bank bank = new Bank(1000);
        Merchant merchant = new Merchant();
        Shopper shopper = new Shopper();

        while (bank.hasMoney()) {
            shopper.drawCoins(bank);
            double price = merchant.randomPrice();
            double change = shopper.buyItem(price);
            if (change >= 0) {
                bank.receiveCoins(shopper.giveChange(change));
            }
        }

        System.out.println("Bank has run out of money. Shopping session is over.");
    }
}

class Bank {
    private double balance;

    public Bank(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean hasMoney() {
        return balance > 0;
    }

    public ArrayList<Coin> provideCoins(int count) {
        ArrayList<Coin> coins = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Coin coin = Coin.values()[random.nextInt(Coin.values().length)];
            coins.add(coin);
        }
        return coins;
    }

    public void deductAmount(double amount) {
        balance -= amount;
    }

    public void receiveCoins(ArrayList<Coin> coins) {
        for (Coin coin : coins) {
            balance += coin.getValue();
        }
    }
}

class Merchant {
    public double randomPrice() {
        Random random = new Random();
        double price = (random.nextInt(81) + 20) / 10.0; // Generates price between $2 and $10 in increments of $0.05
        return Math.round(price * 20) / 20.0; // Round to nearest $0.05
    }
}

class Shopper {
    private ArrayList<Coin> coinBag;

    public Shopper() {
        this.coinBag = new ArrayList<>();
    }

    public void drawCoins(Bank bank) {
        coinBag.addAll(bank.provideCoins(10));
        bank.deductAmount(coinBag.stream().mapToDouble(Coin::getValue).sum());
    }

    public double buyItem(double price) {
        double totalCoins = coinBag.stream().mapToDouble(Coin::getValue).sum();
        if (totalCoins >= price) {
            double change = totalCoins - price;
            coinBag.clear();
            return change;
        }
        return -1; // Insufficient funds
    }

    public ArrayList<Coin> giveChange(double change) {
        ArrayList<Coin> changeCoins = new ArrayList<>();
        while (change > 0) {
            for (Coin coin : Coin.values()) {
                if (coin.getValue() <= change) {
                    changeCoins.add(coin);
                    change -= coin.getValue();
                    break;
                }
            }
        }
        return changeCoins;
    }
}

enum Coin {
    NICKEL(0.05),
    DIME(0.10),
    QUARTER(0.25),
    HALF_DOLLAR(0.50);

    private final double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
