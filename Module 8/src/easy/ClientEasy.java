package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEasy {
    private static final String HOST = "localhost";
    private static final int PORT = 8081;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        System.out.println("Connected to server");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            String response;
            try {
                while ((response = in.readLine()) != null) {
                    System.out.println("Server: " + response);
                }
            } catch (IOException e) {
                System.out.println("Server sleep error");
            }
        }).start();

        while (true) {
            String message = scanner.nextLine();
            out.println(message);
        }
    }
}
