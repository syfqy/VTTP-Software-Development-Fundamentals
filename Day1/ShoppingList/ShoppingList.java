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

        // greet user
        System.out.println("Welcome to your shopping cart");
        System.out.println("List, add, remove or exit program");

        // init vars
        Console cons = System.console();
        String input = "_";
        String[] validCommands = { "list", "add", "remove", "exit"}; // define supported operations

        // loop until exit command is given
        while (!input.equals("exit")) {

            // read and process user input
            input = cons.readLine("> ");
            String processedInput = input.replace(",", "").toLowerCase();
            String[] inputSplit = processedInput.split(" ");
            String command = inputSplit[0].trim();
            
            // validate input
            if (!Arrays.asList(validCommands).contains(command)) {
                System.out.printf("%s is not supported, please enter another command %n", command);
            }
            
            // perform operation
            switch (command) {
                case "list":
                    listItems();
                    break;
                case "add":
                    String[] items = Arrays.copyOfRange(inputSplit, 1, inputSplit.length); 
                    addItem(items);
                    break;
                case "remove":
                    int posToRemove = Integer.parseInt(inputSplit[1]);
                    removeItem(posToRemove);
                    break;
            }
            
        }

        // exit program
        System.out.print("Bye!");
    }
}