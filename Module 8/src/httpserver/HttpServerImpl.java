package httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServerImpl {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", (HttpServerImpl::getExchange));
            server.setExecutor(null);
            server.start();
            System.out.println("Server started");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getExchange(HttpExchange exchange) throws IOException {
        Path path = Path.of("Module 8/resources/index.html");
        if (Files.exists(path)) {
            String htmlContent = Files.readString(path);
            exchange.getResponseHeaders().add("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, htmlContent.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(htmlContent.getBytes());
            }
        } else {
            String notFound = "<h1>404 - File Not Found</h1>";
            exchange.sendResponseHeaders(404, notFound.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(notFound.getBytes());
            }
        }
    }
}
