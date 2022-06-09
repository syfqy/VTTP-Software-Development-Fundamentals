package day3.workshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.io.Console;

public class App {

    // class vars
    private static String[] validOps = { "login", "save", "users", "list", "add", "remove", "exit" }; // define
                                                                                                      // supported
                                                                                                      // operations
    private static String dbPath = "cartdb"; // default db dir

    // utility methods

    private static boolean isValidCmd(String cmd, String[] validCmds) {
        return Arrays.asList(validCmds).contains(cmd);
    }

    private static String[] splitInput(String input) {
        // read and process user input
        String processedInput = input.replace(",", "").toLowerCase();
        String[] inputSplit = processedInput.split(" ");
        return inputSplit;
    }

    // app methods
    public static ShoppingCart loginUser(String username, File dbDir) {

        // build user db filepath
        String userDbPath = dbDir + "/" + username + ".db";
        File userDbFile = new File(userDbPath);
        // read file if exist
        if (!userDbFile.exists()) {
            try {
                userDbFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Cannot create db file");
                e.printStackTrace();
            }
        }

        List<String> records;
        try {
            records = ShoppingCartDB.readDbFile(userDbFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find db file");
            e.printStackTrace();
            records = new LinkedList<>();
        }
        ShoppingCart userCart = new ShoppingCart(records);
        return userCart;

    }

    public static void saveCart(ShoppingCart cart, File dbDir, String username) {
        // build user db filepath
        String userDbPath = dbDir + "/" + username + ".db";
        File userDbFile = new File(userDbPath);
        ShoppingCartDB.writeDbFile(cart, userDbFile);
        System.out.println("Cart has been saved");
    }

    public static void listUsers(File dbDir) {
        // list all users under db
        File[] files = dbDir.listFiles();

        for (int i = 0; i < files.length; i++) {
            // remove file ext
            String user = files[i].getName().replaceFirst("[.][^.]+$", "");
            System.out.printf("%d. %s\n", i+1, user);
        }
    }

    public static void main(String[] args) {

        // get db dir if given as arg
        if (args.length > 0) {
            dbPath = args[0];
            System.out.println(dbPath);
        }

        // create dir if does not exit
        File dbDir = new File(dbPath);
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }

        // greet user
        System.out.println("Welcome to your shopping cart");
        System.out.println("List, add, remove, login, save, users or exit program");

        // init vars
        Console cons = System.console();
        String input = "_";
        ShoppingCart userCart = new ShoppingCart();
        String username = "";
        boolean userLoggedIn = false;

        // loop until exit command is given
        while (!input.equals("exit")) {

            // read and process user input
            input = cons.readLine(">  ");
            String[] processedInput = splitInput(input);
            String command = processedInput[0].trim();
            // boolean isArgsProvided = processedInput.length > 1;

            // validate input
            if (!isValidCmd(command, validOps)) {
                System.out.printf("%s is not supported, please enter another command %n", command);
            }

            // perform operation
            switch (command) {
                case "login":
                    username = processedInput[1];
                    userCart = loginUser(username, dbDir);
                    userLoggedIn = true;
                    userCart.listItems();
                    break;
                case "save":
                    if (userLoggedIn) {
                        // build path
                        saveCart(userCart, dbDir, username);
                    } else {
                        System.out.println("Please login first to save cart");
                    }
                    break;
                case "users":
                    listUsers(dbDir);
                    break;
                case "list":
                    userCart.listItems();
                    break;
                case "add":
                    String[] items = Arrays.copyOfRange(processedInput, 1, processedInput.length);
                    userCart.addItem(items);
                    break;
                case "remove":
                    int posToRemove = Integer.parseInt(processedInput[1]);
                    userCart.removeItem(posToRemove);
                    break;
            }

        }
    }
}
