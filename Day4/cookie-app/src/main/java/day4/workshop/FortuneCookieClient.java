package day4.workshop;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class FortuneCookieClient {

    private static void readResponse(BufferedReader in) {
        try {
            String resp = in.readLine();
            System.out.println("server: " + resp);
        } catch (IOException e) {
            System.out.println("error: no response");
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {

        // set default port
        int defaultPort = 12345;
        
        // connect to server
        Socket socket = new Socket("localhost", defaultPort);
        System.out.printf("Connected to server on port %d\n", defaultPort);

        // init IO streams
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        

        // receive greeting
        readResponse(in);
        readResponse(in);
        
        Console cons = System.console();
        String cmd = "";
        
        // main loop
        while (!cmd.equals("exit")) {
            cmd = cons.readLine("> ");
            pw.println(cmd);
            pw.flush();
    
            readResponse(in);
        }
        readResponse(in);
        socket.close();
        System.out.println("Exited program");
    }
}
