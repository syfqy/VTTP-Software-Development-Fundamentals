package day3.workshop;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartDB {

    public static List<String> readDbFile(File dbFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(dbFile);
        List<String> records = new LinkedList<>();
        while (scanner.hasNextLine()) {
            records.add(scanner.nextLine());
        }
        scanner.close();
        return records;
    }

    public static void writeDbFile(ShoppingCart cart, File dbFile) {
        List<String> records = cart.getItemsInCart();

        try {
            FileWriter writer = new FileWriter(dbFile);
            for (String item : records) {
                writer.write(item);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot write to db file");
            e.printStackTrace();
        }

    }

    
}
