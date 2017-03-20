// ECE 309 Lab 2 by Sam Eddy

import java.io.*;
import java.util.*;
import java.net.*;

public class TherapistServer
{

public static void main(String[] args) throws IOException
	{
	// Load I/O Classes
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader     br = new BufferedReader(isr);
	
	// Print header
	System.out.println(" ");
	System.out.println(" ");
	System.out.println("ECE309 Lab 2 by Sam Eddy");
	System.out.println("Welcome to TherapistServer.");
	
	if (args.length != 0) // check if any command line parameters are entered
	{
	 System.out.println(" ");
	 System.out.println("No commmand line parameters needed.");
	 System.out.println(" "); 			
	}
	
	System.out.println("Press ENTER to begin");
	System.out.println(" ");
	
	String input = br.readLine();		// Wait for ENTER key
	
	System.out.println("------------------------------------------------------------------");
	System.out.println(" ");
	
	// Fill answers string with therapeutic responses
	String[] answers = {"Duh!", "Yesh.", "Nah", "Prawlly", "Definitely not!", 
	"You must be stupid...", "Never ask a question again!", "HELL YA BOYYY!", "Absofruitly!",
	"Frick nah son", "Shut up bro!", "Why don't you ask yo BUTT!"};
	
	
	ServerSocket      ss = new ServerSocket(2222);	// "listening" for connections to port 2222
	String serverAddress = InetAddress.getLocalHost().getHostAddress();
  	int    serverPort    = ss.getLocalPort();
  	System.out.println("Server up at " + serverAddress + " on port " + serverPort + ".");
  	System.out.println(" ");
	
	while(true)								
		{	
		
		try {
        Socket s = ss.accept();				//wait for next connect
        DataInputStream dis = new DataInputStream(s.getInputStream());
        String message = dis.readUTF();		//wait for send
        
        System.out.println("Received: " + message + " from " + s.getInetAddress().getHostAddress());
        
        int index = (int) (Math.random() * answers.length);
        
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		dos.writeUTF(answers[index]);
		System.out.println(" ");
        
        s.close();							// hang up on this client after one message
        }
        
		catch(IOException ioe)
	    {
		System.out.println(ioe);
	    }

		}			
	}
}