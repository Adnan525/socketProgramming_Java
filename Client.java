import java.net.*;
import java.util.Random;
import java.util.Scanner;
import java.io.*; 
  
public class Client 
{ 
    // initialize socket
    private Socket socket = null;
    
    public Client(String address, int port) throws IOException 
    { 
        // establish a connection 
        try
        { 
        	String toServer,fromServer; //send and receive
            socket = new Socket(address, port);
            System.out.println("Client has started and connected");
            System.out.println("-------------------");
            Scanner keyboard= new Scanner(System.in);  //type in whatever you wanna say
            toServer="";
            fromServer = "";
            PrintStream send = new PrintStream(socket.getOutputStream());  //send replies to server
            Scanner rcvServer = new Scanner(socket.getInputStream());  //receive replies from server
            
            while(!fromServer.equals("BYE"))  //terminate when receives bye
            {
            	System.out.println("Enter your message:");
            	toServer = keyboard.nextLine();
            	send.println(toServer);
            	fromServer = rcvServer.nextLine();
            	System.out.println("Client received : "+fromServer);
            	
            }
            
            System.out.println("Connection closing...");
            socket.close();
            keyboard.close();
            send.close();
            rcvServer.close();
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        }
    } 
    
    public static void main(String args[]) throws IOException 
    { 
        Client client = new Client("127.0.0.1", 5000); 
    }
} 