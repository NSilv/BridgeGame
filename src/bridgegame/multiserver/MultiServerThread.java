package bridgegame.multiserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import bridgegame.graph.Graph; 

public class MultiServerThread extends Thread { 
	private Socket socket; 
	ConcurrentHashMap<String, String> playerData;
	private SimpleWeightedGraph<String,DefaultEdge> map;
	
	public MultiServerThread(Socket socket, ConcurrentHashMap<String,String> playerData, SimpleWeightedGraph<String,DefaultEdge> map) { 
		this.socket = socket; 
		this.playerData = playerData;
		this.map = map;
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
			    out.writeObject(new Object());    

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
