package psjava.designpattern;

import java.util.ArrayList;
import java.util.List;

interface PaymentStrategy {

    public void pay(int amount);
}

class KakaoStrategy implements PaymentStrategy {

    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;

    public KakaoStrategy(String name, String cardNumber, String cvv, String dateOfExpiry) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using KAKAOCard.");
    }
}

class LUNACardStrategy implements PaymentStrategy {

    private String emailId;
    private String password;

    public LUNACardStrategy(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using LUNACard.");
    }
}

class Item {

    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}

class ShoppingCart {

    List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public int calculateTotal() {
        int sum = 0;
        for (Item item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public void pay(PaymentStrategy paymentMethod) {
        int amount = calculateTotal();
        paymentMethod.pay(amount);
    }
}

public class StrategyEg {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item a = new Item("litsynpA", 100);
        Item b = new Item("litsynpB", 300);

        cart.addItem(a);
        cart.addItem(b);

        // Pay by LUNACard
        cart.pay(new LUNACardStrategy("litsynp@example.com", "testpasswd"));

        // Pay by KakaoCard
        cart.pay(new KakaoStrategy("Litsy", "123456789", "123", "12/01"));
    }
}
