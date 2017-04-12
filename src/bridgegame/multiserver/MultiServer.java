package bridgegame.multiserver;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import bridgegame.graph.Graph;

import java.io.*; 

public class MultiServer { 

	static List<MultiServerThread> players = new ArrayList<>();
	static ConcurrentHashMap<String, String> playerData = new ConcurrentHashMap<>();
	static SimpleWeightedGraph<String,DefaultEdge> graph;
	
	public static void main(String[] args) throws IOException { 

		ServerSocket serverSocket = null; 
		int numGiocatori = Integer.parseInt(args[0]);
		players = new ArrayList<>();
		playerData = new ConcurrentHashMap<>();
		
		//graph = Graph.random();
		
		try { 
			serverSocket = new ServerSocket(4444); 
		} catch (IOException e) { 
			System.err.println("Could not listen on port: 4444."); 
		}
		
		

		while (players.size() < numGiocatori){			
			players.add(new MultiServerThread(serverSocket.accept(), playerData, graph));
		}
		players.stream().forEach(Thread::start);
		playGame();
		serverSocket.close(); 
	}
	
	static void playGame() {
		boolean won = false;
		while(!won){
			//fare cose
		}
	}
}
