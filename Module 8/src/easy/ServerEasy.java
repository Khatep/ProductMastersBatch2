package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

public class ServerEasy {
    private static final int PORT = 8081;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.printf("Starting server on port %d ...%n", PORT);

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket);

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Client: " + inputLine);

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
