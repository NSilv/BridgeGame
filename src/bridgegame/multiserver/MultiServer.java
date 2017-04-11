package bridgegame.multiserver;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import graph.Graph;

import java.io.*; 

public class MultiServer { 

	static List<MultiServerThread<String>> players = new ArrayList<>();
	static ConcurrentHashMap<MultiServerThread<String>, String> playerData = new ConcurrentHashMap<>();
	static Graph<String> graph;
	
	public static void main(String[] args) throws IOException { 

		ServerSocket serverSocket = null; 
		int numGiocatori = Integer.parseInt(args[0]);
		players = new ArrayList<>();
		playerData = new ConcurrentHashMap<>();
		
		graph = Graph.random();
		
		try { 
			serverSocket = new ServerSocket(4444); 
		} catch (IOException e) { 
			System.err.println("Could not listen on port: 4444."); 
		}
		
		

		while (players.size() != numGiocatori){				
			players.add(new MultiServerThread<String>(serverSocket.accept(), playerData, graph));
		}
		players.stream().forEach(Thread::start);
		playGame();
		serverSocket.close(); 
	}
	
	static void playGame() {
		
	}
}
