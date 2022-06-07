import java.util.ArrayList;
import java.io.Console;
import java.util.Arrays;

public class ShoppingList {

    static ArrayList<String> myShoppingList = new ArrayList<String>();

    public static void listItems() {
        if (myShoppingList.size() == 0) {
            System.out.println("Your cart is empty");
        } else {
            for (int i = 0; i < myShoppingList.size(); i++) {
                String item = myShoppingList.get(i);
                System.out.printf("%d. %s %n", i+1, item);
            }
        }
    }

    public static void addItem(String[] items) {

        for (String item : items) {
            // check item already exists
            if (myShoppingList.contains(item)) {
                System.out.printf("%s already in cart %n", item);
            } else {
                // add item
                myShoppingList.add(item);
                System.out.printf("%s has been added to cart %n", item);
            }
        }
    }

    public static void removeItem(int pos) {
        // check if list is empty
        if (myShoppingList.size() == 0) {
            System.out.println("Cannot remove. Shopping List is already empty.");
        } else {
            // check valid index
            if (pos-1 > myShoppingList.size() || pos-1 < 0) {
                System.out.println("Invalid index, please enter a valid index");
            } else {
                // remove item
                String removedItem = myShoppingList.remove(pos - 1);
                System.out.printf("%s has been removed from cart %n", removedItem);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to your shopping cart");

        Console cons = System.console();
        String input = "_";
        String[] validCommands = { "list", "add", "remove", "exit"}; // define supported operations

        // loop until exit command is given
        while (!input.equals("exit")) {
            input = cons.readLine("> ");
            String[] inputSplit = input.split(",(?=\\p{Alnum})");
            System.out.println(inputSplit[0]);
            // System.out.println(inputSplit[1]);
            String command = inputSplit[0].trim().toLowerCase();
            String[] items = inputSplit[1].split(",");
            // System.out.println(inputSplit);
            // System.out.println(items);
            // String[] items = Arrays.copyOfRange(inputSplit, 1, inputSplit.length);
            
            // validate input
            if (!Arrays.asList(validCommands).contains(command)) {
                System.out.printf("%s is not supported, please enter another command %n", command);
            }
            
            switch (command) {
                case "list":
                    listItems();
                case "add":
                    addItem(items);
                    break;
                case "remove":
                    int posToRemove = Integer.parseInt(items[0]);
                    removeItem(posToRemove);
                    break;
            }
        }
    }
}