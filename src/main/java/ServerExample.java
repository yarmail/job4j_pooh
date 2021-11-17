import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * КАРКАС ПРИМЕР СЕРВЕРА
 *
 * Проверим работу сервера через cURL.
 * 1. запустить сервер PoohServer
 * 2. Открыть CMD и перейти :  cd C:\Tools\curl773\bin
 * 3. Проверить работоспособность curl: curl -V
 * 4. Отправить запрос: curl -X POST -d "text=13" http://localhost:9000/queue/weather
 * 5. Ответ должен быть: text=13
 * (все работает)
 *
 */
public class ServerExample {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     InputStream input = socket.getInputStream()) {
                    byte[] buff = new byte[1_000_000];
                    var total = input.read(buff);
                    var text = new String(Arrays.copyOfRange(buff, 0, total), StandardCharsets.UTF_8);
                    System.out.println(text);
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    out.write(text.getBytes());
                }
            }
        }
    }
}