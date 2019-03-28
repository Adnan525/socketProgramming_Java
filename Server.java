// A Java program for a Server 
import java.net.*;
import java.util.Scanner;
import java.io.*; 
  
public class Server 
{ 
    //initialize socket, server and receiver
    private Socket          socket   = null; 
    private ServerSocket    server   = null;
    Scanner rcvClient = null;
  
    
    String gday="G'DAY";
    String bye="BYE";
    
    
    public Server(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
        	String temp, send;
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            server = new ServerSocket(port);
            socket = server.accept();
            System.out.println("Connected");
            System.out.println("--------------------------------");
            rcvClient = new Scanner(socket.getInputStream()); //get reply from client
            PrintStream sendClient = new PrintStream(socket.getOutputStream());  // send replies to client
            
            while(true)
            {
            	temp = rcvClient.nextLine();
            	System.out.println("Server received : "+temp);
            	if(temp.equals("Helo"))
            		sendClient.println("G'DAY");
            	else if (temp.equals("Bye"))
            	{
            		sendClient.println("BYE");
            		break;
            	}
            	else
            		sendClient.println("Only Helo and Bye accepted");
            }
            
            System.out.println("Server Closing...");
            server.close();
            socket.close();
            sendClient.close();
            rcvClient.close();
            
            
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Server server = new Server(5000); 
    } 
} 