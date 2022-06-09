package day3.workshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.io.Console;

public class Session {

    public static final String LIST = "list";
    public static final String ADD = "add";
    public static final String REMOVE = "remove";
    public static final String USERS = "users";
    public static final String SAVE = "save";
    public static final String EXIT = "exit";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";

    private File dbDir;
    private ShoppingCartDB cartDb;

    public Session(File dbDir) {
        this.dbDir = dbDir;
    }

    // utility methods

    private String[] splitInput(String input) {
        // read and process user input
        String processedInput = input.replace(",", "").toLowerCase();
        String[] inputSplit = processedInput.split(" ");
        return inputSplit;
    }

    private static File buildUserDbPath(String username, File dbDir) {
        // build user db filepath
        String userDbPath = dbDir + "/" + username + ".db";
        File userDb = new File(userDbPath);
        return userDb;
    }

    // session methods
    public ShoppingCart loginUser(String username, File dbDir) {

        // get user db filepath
        File userDbFile = buildUserDbPath(username, dbDir);

        // create file if does not exit
        if (!userDbFile.exists()) {
            try {
                userDbFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Cannot create db file");
                e.printStackTrace();
            }
        }

        List<String> records;

        // read file
        try {
            records = this.cartDb.readDbFile(userDbFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find db file");
            e.printStackTrace();
            records = new LinkedList<>();
        }
        ShoppingCart userCart = new ShoppingCart(records);
        System.out.printf("%s successfully logged in.", username);

        return userCart;

    }

    public void saveCart(ShoppingCart cart, String username) {
        // build user db filepath
        File userDbFile = buildUserDbPath(username, this.dbDir);
        try {
            this.cartDb.writeDbFile(cart, userDbFile);
        } catch (IOException e) {
            System.out.println("Cannot write to file");
            e.printStackTrace();
        }
        System.out.println("Cart has been saved");
    }

    public void listUsers() {
        // list all files under db
        File[] userFiles = this.dbDir.listFiles();

        for (int i = 0; i < userFiles.length; i++) {
            // remove file exts to get username only
            String user = userFiles[i].getName().replaceFirst("[.][^.]+$", "");
            System.out.printf("%d. %s\n", i + 1, user);
        }
    }

    public void start() {
        
        // create DB dir if does not exist
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }

        // init DB
        this.cartDb = new ShoppingCartDB(this.dbDir);

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

            // perform operation
            switch (command) {
                case LOGIN:
                    username = processedInput[1];
                    if (userLoggedIn) {
                        System.out.printf("%s already logged in. Logout first if changing to a different user",
                                username);
                        break;
                    }
                    userCart = loginUser(username, dbDir);
                    userLoggedIn = true;
                    break;
                case LOGOUT:
                    if (!userLoggedIn) {
                        System.out.println("No user logged in yet.");
                        break;
                    }
                    userLoggedIn = false;
                    System.out.printf("%s has been logged out", username);
                    break;
                case SAVE:
                    if (userLoggedIn) {
                        // build path
                        saveCart(userCart, username);
                    } else {
                        System.out.println("Please login first to save cart");
                    }
                    break;
                case USERS:
                    listUsers();
                    break;
                case LIST:
                    userCart.listItems();
                    break;
                case ADD:
                    String[] items = Arrays.copyOfRange(processedInput, 1, processedInput.length);
                    userCart.addItem(items);
                    break;
                case REMOVE:
                    int posToRemove = Integer.parseInt(processedInput[1]);
                    userCart.removeItem(posToRemove);
                    break;
                case EXIT:
                    System.out.println("Program exited");
                    break;
                default:
                    // validate input
                    System.out.printf("%s is not supported, please enter another command %n", command);
            }
        }
    
    }
}
