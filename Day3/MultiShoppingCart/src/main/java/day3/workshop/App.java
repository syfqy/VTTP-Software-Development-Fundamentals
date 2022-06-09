package day3.workshop;

import java.io.File;

public class App {

    private static String defaultDbDirName = "cartDb";

    public static void main(String[] args) {
        // print out first argument db name use to create the directory
        if (args.length > 0)
            if (args[0] != null) {
                System.out.println(args[0]);
                App.defaultDbDirName = args[0];
            }

        System.out.println(defaultDbDirName);
        File defaultDbDir = new File(defaultDbDirName);

        // start session
        Session session = new Session(defaultDbDir);
        session.start();

    }
}