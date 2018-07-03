import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] ar) {
        int serverPort = 9999;
        String address = "127.0.0.1";

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;

            while (true) {
                line = keyboard.readLine();

                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.

                line = in.readUTF(); // ждем пока сервер отошлет строку текста.

                System.out.println("output" + line);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}