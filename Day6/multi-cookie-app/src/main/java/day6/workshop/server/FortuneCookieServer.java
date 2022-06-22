package day6.workshop.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

public class FortuneCookieServer {

    private static int defaultPort = 12345;
    private static final String GET = "get-cookie";
    private static final String END = "end";

    public static Socket initSocket(int port) throws IOException {
        System.out.printf("Starting server on localhost: %d\n", port);
        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();
        System.out.println("Client connected");
        return socket;
    }

    public static void sendResponse(PrintWriter out, String msg) {
        out.write(msg+"\n");
        out.flush();
    }

    public static void start(Path cookiePath) {

        Socket socket;
        BufferedReader in;
        PrintWriter out;

        try {
            // init socket
            socket = initSocket(defaultPort);

            // init IO streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            // greet user
            sendResponse(out, "Welcome to the fortune cookie server");
            sendResponse(out, "Enter get-cookie to get a fortune cookie or end to exit the program.");
           
            System.out.println("Awaiting request");
            String cmd = in.readLine();
            System.out.println("Request: " + cmd);

            switch (cmd) {
                case GET:
                    // get-cookie
                    System.out.println("Serving cookie");
                    Cookie cookie = new Cookie(cookiePath);
                    String randCookieText = cookie.getRandomCookie();
                    sendResponse(out, randCookieText);
                case END:
                    sendResponse(out, "Exit program");
                default:
                    sendResponse(out, cmd + " is not supported, please enter another command");
            }
            
            System.out.println("Shutting down server");
            socket.close();

        } catch (IOException e) {
            System.out.println("Cannot start server");
            e.printStackTrace();
        }

    }
}