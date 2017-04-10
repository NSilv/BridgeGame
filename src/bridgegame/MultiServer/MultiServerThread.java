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
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 

			String inputLine, outputLine; 

			/*TODO thread creato, invio del grafo e inizio trasmissione tabella posizioni aggiornata 
      Ricevo spostamenti dal client, una volta ottenuti tutti gli spostamenti 
			 */

			out.println(/*outputLine*/); 

			inputLine = in.readLine();
			out.println(/*outputLine*/); 

			out.close(); 
			in.close(); 
			socket.close(); 

		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	} 
}
