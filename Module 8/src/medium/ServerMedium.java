package medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ServerMedium {
    private static final int TIMEOUT_SECONDS = 30;
    private static final int PORT = 8082;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.printf("Starting server on port %d ...%n", PORT);

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket);

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable disconnectTask = () -> {
            System.out.println("Client is deactivate, delete connection");
            try {
                clientSocket.close();
            } catch (IOException ignored) {}
        };

        ScheduledFuture<?> timeoutFuture = scheduler.schedule(disconnectTask, TIMEOUT_SECONDS, TimeUnit.SECONDS);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            timeoutFuture.cancel(false);
            timeoutFuture = scheduler.schedule(disconnectTask, TIMEOUT_SECONDS, TimeUnit.SECONDS);

            System.out.println("Client: " + inputLine);
            if ("/time".equalsIgnoreCase(inputLine)) {
                out.println(LocalTime.now().withNano(0));
            } else {
                out.println("Server get: " + inputLine);
            }
        }

        scheduler.shutdown();
        clientSocket.close();
        serverSocket.close();
    }
}
