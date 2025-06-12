package medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ClientMedium {
    private static final String HOST = "localhost";
    private static final int PORT = 8082;
    private static final int TIMEOUT_SECONDS = 30;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        System.out.println("Connected to server");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable disconnectTask = () -> {
            System.out.println("No activity. The client ends the connection.");
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing the socket " + e.getMessage());
            }
            System.exit(0);
        };

        final ScheduledFuture<?>[] timeoutFuture = {scheduler.schedule(disconnectTask, TIMEOUT_SECONDS, TimeUnit.SECONDS)};

        new Thread(() -> {
            try {
                String serverResponse;
                while ((serverResponse = in.readLine()) != null) {
                    System.out.println("Server: " + serverResponse);
                    timeoutFuture[0].cancel(false);
                    timeoutFuture[0] = scheduler.schedule(disconnectTask, TIMEOUT_SECONDS, TimeUnit.SECONDS);
                }
            } catch (IOException e) {
                System.out.println("Connection is closed by the server");
                System.exit(0);
            }
        }).start();

        while (true) {
            String message = scanner.nextLine();
            out.println(message);
            timeoutFuture[0].cancel(false);
            timeoutFuture[0] = scheduler.schedule(disconnectTask, TIMEOUT_SECONDS, TimeUnit.SECONDS);
        }
    }
}
