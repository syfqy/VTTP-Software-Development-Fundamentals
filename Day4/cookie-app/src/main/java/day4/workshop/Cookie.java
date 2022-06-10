package day4.workshop;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class Cookie {

    Path cookiePath;

    // constructor
    public Cookie(Path cookiePath) {
        this.cookiePath = cookiePath;
    }

    // methods
    public List<String> getAllCookies() throws IOException {
        // read all lines from from text file
        List<String> lines;
        lines = Files.readAllLines(cookiePath);
        return lines;
    }
    
    public String getRandomCookie() {
        // return random cookie line
        List<String> allCookies;
        try {
            allCookies = this.getAllCookies();
            Random random = new Random();
            return allCookies.get(random.nextInt(allCookies.size()));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error. No cookie for u :(";
        }
    }

}