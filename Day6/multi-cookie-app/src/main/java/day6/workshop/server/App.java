package day6.workshop.server;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {

        // set default path if none provided
        Path cookiePath = Paths.get("/Users/User/Documents/Projects/VTTP Software Development Fundamentals/Day4/cookie-app/src/main/java/day4/workshop/cookie_file.txt");
        if (args.length > 0) 
            cookiePath = Paths.get(args[0]);        

        // start server
        FortuneCookieServer.start(cookiePath);

    }
}
