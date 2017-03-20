// ECE 309 Lab 2 by Sam Eddy

import java.io.*;
import java.util.*;
import java.net.*;

public class TherapistClient
{

public static void main(String[] args) throws IOException
	{
    
	// Load I/O Classes
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader     br = new BufferedReader(isr);
	
	FileWriter 		   fw = new FileWriter("TherapyLog.txt", true);
	BufferedWriter 	   bw = new BufferedWriter(fw);
	bw.write(new Date().toString());
	bw.newLine();
	
	// Print header
	System.out.println(" ");
	System.out.println(" ");
	System.out.println("ECE309 Lab 2 by Sam Eddy");
	System.out.println(" ");
	if (args.length != 2) // check if 2 command line parameters are provided
	{
	 System.out.println("Please provide 2 command line parameters:");
	 System.out.println("first, the target address, then the port address.");
	 System.out.println(" ");
	 return; 			
	}
  	
  	String logErrorMessage  = "Unable to connect with server. Session terminated.";
  	
  	String serverAddress = args[0];
  	String serverPort    = args[1];
  	int serverPortNumber = Integer.parseInt(serverPort);

	System.out.println("Press ENTER to begin");
	System.out.println(" ");
	
	String input = br.readLine();		// Wait for ENTER key
	
	System.out.println("------------------------------------------------------------------");
	System.out.println(" ");
	
	// Print instructions and introduce program
	System.out.println("Welcome to the Online Therapy System.");
	System.out.println(" ");
	System.out.println("The Online Therapy System works by you simply typing in a question,");
	System.out.println("followed by the ENTER key, and our advanced Artificial Intelligence");
	System.out.println("machine will respond with the appropriate therapeutic response.");
	System.out.println(" ");
	System.out.println("Please be sure to only enter YES or NO questions, and to stop the");
	System.out.println("therapy session, simply type the word 'END', followed by the ENTER key.");
	System.out.println(" ");
	System.out.println("Let's get started!");
	System.out.println("Press ENTER to continue");
	input = br.readLine();				// Wait for ENTER key
	
	try {
	while(true)								
		{	
		// Begin receiving questions and outputting responses
		System.out.println("Type your question now, followed by the ENTER key.");
		System.out.println(" ");
		input = br.readLine();			// Receive user input
		
		if (input.trim().length() == 0) continue;
		if (input.equalsIgnoreCase("END")) break;
		
		Socket s = new Socket(serverAddress, serverPortNumber); // establish CONNECTION to server
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		dos.writeUTF(input);
		
		DataInputStream dis = new DataInputStream(s.getInputStream());
        String message = dis.readUTF();//wait for send
        System.out.println(message);
        System.out.println(" ");
        
        // Record session to text file
		String answer = message;
		String logLine = "Question was: " + input + " Therapist answer was: " + answer;
		bw.write(logLine);
		bw.newLine();
		}
	}
	
	catch(IOException ioe) {
		System.out.println(" ");
		System.out.println("I'm sorry. We are unable to connect to with server " + serverAddress + " right now.");
		System.out.println("Please try again later.");
		bw.write(logErrorMessage);
		bw.newLine();
	}	
		
	// Output goodbye message and close/save text file	
	System.out.println(" ");
	System.out.println("Thank you, come again!");
	System.out.println(" ");
	
	bw.newLine();
	bw.close();		
	}
}