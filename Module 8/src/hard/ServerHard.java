package hard;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ServerHard {
    private static final int PORT = 8083;
    private static final Map<String, String> USERS = new HashMap<>();

    static {
        USERS.put("nurik", "1234");
        USERS.put("admin", "admin");
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.printf("Starting server on port %d ...%n", PORT);

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket);

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        out.println("Enter login:");
        String login = in.readLine();
        out.println("Enter password:");
        String password = in.readLine();

        if (!USERS.containsKey(login) || !USERS.get(login).equals(password)) {
            out.println("Invalid login or password. Disconnecting...");
            clientSocket.close();
            serverSocket.close();
            return;
        }

        out.println("Welcome, " + login + "!");

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("[" + login + "]: " + inputLine);

            if ("/time".equalsIgnoreCase(inputLine)) {
                out.println(LocalTime.now().withNano(0));
            } else {
                out.println("Server get: " + inputLine);
            }
        }

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}