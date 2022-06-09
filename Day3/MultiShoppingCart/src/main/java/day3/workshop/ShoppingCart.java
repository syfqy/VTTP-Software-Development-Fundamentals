package day3.workshop;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {

    // global vars
    private List<String> itemsInCart = new LinkedList<String>();

    // constructors
    public ShoppingCart() {

    }

    public ShoppingCart(List<String> ShoppingList) {
        this.itemsInCart = ShoppingList;
    }

    // getters and setters
    public List<String> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(List<String> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    // cart methods
    public void listItems() {
        if (itemsInCart.size() == 0) {
            System.out.println("Your cart is empty");
        } else {
            for (int i = 0; i < itemsInCart.size(); i++) {
                String item = itemsInCart.get(i);
                System.out.printf("%d. %s %n", i + 1, item);
            }
        }
    }

    public void addItem(String[] items) {

        for (String item : items) {
            // check item already exists
            if (itemsInCart.contains(item)) {
                System.out.printf("%s already in cart %n", item);
            } else {
                // add item
                itemsInCart.add(item);
                System.out.printf("%s has been added to cart %n", item);
            }
        }
    }

    public void removeItem(int pos) {
        // check if list is empty
        if (itemsInCart.size() == 0) {
            System.out.println("Cannot remove. Shopping List is already empty.");
            return;
        }

        // check valid index
        if (pos - 1 > itemsInCart.size() || pos - 1 < 0) {
            System.out.println("Invalid index, please enter a valid index");
            return;
        }
        // remove item
        String removedItem = itemsInCart.remove(pos - 1);
        System.out.printf("%s has been removed from cart %n", removedItem);

    }
}