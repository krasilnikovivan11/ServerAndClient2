import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8289);
            System.out.println("Started");
            Socket socket = serverSocket.accept();
            System.out.println("Connected");
           final Scanner in = new Scanner(socket.getInputStream());
           final Scanner sin = new Scanner(System.in);
           final PrintWriter out = new PrintWriter(socket.getOutputStream());
           new Thread(()->{
                System.out.println("inputthread");
                while (true){
                    if(in.hasNext())
                    System.out.println(in.nextLine());
                }
            }).start();
           new Thread(()->{
                System.out.println("outputthread");
                while(true){
                    if(sin.hasNext()) {
                        String msg = sin.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
