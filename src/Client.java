import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.TreeMap;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8289);
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
