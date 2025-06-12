package hard;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHard {
    private static final String HOST = "localhost";
    private static final int PORT = 8083;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        System.out.println("Connected to server");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        System.out.println(in.readLine()); // Enter login:
        String login = scanner.nextLine();
        out.println(login);

        System.out.println(in.readLine()); // Enter password:
        String password = scanner.nextLine();
        out.println(password);

        String welcomeMessage = in.readLine();
        System.out.println("Server: " + welcomeMessage);

        if (welcomeMessage.startsWith("Invalid")) {
            socket.close();
            return;
        }

        // Асинхронный приём
        new Thread(() -> {
            try {
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println("Server: " + response);
                }
            } catch (IOException e) {
                System.out.println("Server closed connection");
            }
        }).start();

        // Отправка сообщений
        while (true) {
            String message = scanner.nextLine();
            out.println(message);
        }
    }
}