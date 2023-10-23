import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Timestamp;


public class client {
    static Socket soc;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String usrIn = new String();
        String nick = "generic"; //aka ivaylo
        soc = null;
        try {
            while(true){
                usrIn = br.readLine();
                if(usrIn.startsWith("/connect ")){
                    String[] ipPort = usrIn.split(" ");
                    String ip = ipPort[1].split(":")[0];
                    int port = Integer.parseInt(ipPort[1].split(":")[1]);
                    soc = new Socket(ip, port);

                    new Thread(() -> {
                        try {
                            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                            String receivedMsg;
                            while ((receivedMsg = in.readLine()) != null) {
                                System.out.println("[" + Timestamp.valueOf(java.time.LocalDateTime.now()) + "] " + receivedMsg); 

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();

                }else if(usrIn.startsWith("/msg ") & soc != null){
                    String[] msg = usrIn.split(" ");
                    String message = msg[1];
                    PrintStream out = new PrintStream(soc.getOutputStream());
                    out.println(nick + ":" +message);

                }else if(usrIn.startsWith("/nick ")){
                    String[] name = usrIn.split(" ");
                    nick = name[1];
                    
                }else if(usrIn.startsWith("/down")){
                    System.exit(0);
                }else{
                    System.out.println("Invalid command");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

