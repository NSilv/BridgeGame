package bridgegame.MultiServer;

import java.net.*; 
import java.io.*; 

public class MultiServerThread extends Thread { 
	private Socket socket; 
	public MultiServerThread(Socket socket) { 
		this.socket = socket; 
	} 

	public void run() { 

		try { 
			   // Create input and output streams to client
			    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			    // Read modify
			    // TODO here

			    /* Create Message object and retrive information */
			    //inFromClient.readObject();

			    /* Modify the message object */
			    //inMsg.getMessage().toUpperCase());

			    /* Send the modified Message object back */
			    out.writeObject(/*object*/);    

			/*TODO thread creato, invio del grafo e inizio trasmissione tabella posizioni aggiornata 
      				Ricevo spostamenti dal client, una volta ottenuti tutti gli spostamenti 
			 */
			
			out.close(); 
			in.close(); 
			socket.close(); 

		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	} 
}
