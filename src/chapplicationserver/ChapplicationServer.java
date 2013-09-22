package chapplicationserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author james.wolff
 * @date Sep 20, 2013
 */
public class ChapplicationServer {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> users;
        users = new ArrayList<>();
        PrintWriter out = null;
        Scanner scan=new Scanner(System.in);
        int port=-1;
        try {
            ServerSocket ss=null;
            while(port==-1){
                System.out.print("Input a port between 0-55555: ");
                try{
                    port=scan.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("That is not a number!");
                    System.exit(1);
                }
                if(port>0&&port<55555){
                    break;
                }else{
                    System.out.println("That number is out of range!");
                    port=-1;
                }
            }
            try{
                ss=new ServerSocket(port);
            }catch(IOException e){
                System.err.println("Could not listen on port: 4444.");
                System.exit(1);
            }
            Socket s=null;
            try{
                s=ss.accept();
            }catch(IOException e){
                System.err.println("Accept Failed!");
                System.exit(1);
            }
            out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            String input;
            Protocol p=new Protocol();
            int i;
            while((input=in.readLine())!=null){
                i=p.processInput(input);
                if(i==0||i==1){
                    input=input.substring(3);
                    if(i==0){
                        users.add(input);
                        out.println("-u"+input+"-mWelcome "+input+" to the chat!");
                    }else{
                        users.remove(input);
                        out.println("-u+"+input+"-m"+input+" left.");
                    }
                }else if(i==2){
                    //TODO Add user to mysql database
                }else if(i==3){
                    out.println(input);
                }
            }
            out.close();
            try {
                in.close();
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(ChapplicationServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(ChapplicationServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

}
