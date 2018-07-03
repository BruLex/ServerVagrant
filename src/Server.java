import java.net.*;
import java.io.*;
public class Server {

    public static void main(String[] ar) throws Throwable   {
        int port = 9999;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept(); //ждем связь с сервером
            new Thread(new SocketProcessing(socket)).run();

        } catch(Exception x) {
            x.printStackTrace();
        }
    }


    private static class SocketProcessing implements Runnable {
        private Socket s;
        private InputStream sin;
        private OutputStream sout;

        private SocketProcessing(Socket s) throws Throwable {
            this.s = s;
            this.sin = s.getInputStream();
            this.sout = s.getOutputStream();
        }

        @Override
        public void run() {
            try {
                System.out.println("start");

                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);

                String line = null;
                while (true) {

                    line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                    System.out.println("send "+line);

                    out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                    out.flush(); // заставляем поток закончить передачу данных.
                    System.out.println("Waiting for the next line...");
                    System.out.println();
                }
            }catch(Exception x) { x.printStackTrace(); }

        }
    }
}