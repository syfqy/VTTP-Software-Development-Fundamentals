package day3.workshop;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartDB {

    private File db;

    public ShoppingCartDB(File db) {
        this.db = db;
    }

    public List<String> readDbFile(File userDbRecord) throws FileNotFoundException {
        Scanner scanner = new Scanner(userDbRecord);
        List<String> records = new LinkedList<>();
        while (scanner.hasNextLine()) {
            records.add(scanner.nextLine());
        }
        scanner.close();
        return records;
    }

    public void writeDbFile(ShoppingCart cart, File userDbRecord) throws IOException {
        List<String> records = cart.getItemsInCart();

        FileWriter writer = new FileWriter(userDbRecord);
        for (String item : records) {
            writer.write(item + "\n");
        }
        writer.close();

    }

}
