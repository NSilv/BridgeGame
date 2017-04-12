/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridgegame.graph;

import static java.util.Arrays.stream;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import bridgegame.gui.GraphDemo.MyEdge;
import bridgegame.utils.Tuple3;

/**
 *
 * @author s01412
 * @param <A>
 */
public class Graph<T> {
	/*
    SimpleWeightedGraph<T,DefaultEdge> graph;
    
    public Graph(){
        graph = new SimpleWeightedGraph<>(DefaultEdge.class);
    }
    
    public Graph(T... args){
        this();
        stream(args).forEach(graph::addVertex);
    }
    
    
    public void addEdge(Tuple3<T,T,DefaultEdge> vertex){
        graph.addEdge(vertex.first, vertex.second, vertex.third);
    }
    
    public void addEdges(Tuple3<T,T,DefaultEdge>... edges){
        stream(edges).forEach(this::addEdge);
    }*/
    
    public static  SimpleWeightedGraph<String, DefaultWeightedEdge> random(){
        SimpleWeightedGraph<String, DefaultWeightedEdge> g = 
	            new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        int nVertex = 8;
        String[][] AdMatrix = new String[nVertex][nVertex];
        
        for(int i = 0; i < nVertex; i++){
        	String name = String.valueOf((int)(Math.random()*9)) + String.valueOf((int)(Math.random()*9));
        	AdMatrix[0][i] = AdMatrix[i][0] = name;
        	g.addVertex(AdMatrix[0][i]);
        }
        
        int rnd;
        for(int i = 1; i < nVertex; i++){
        	for(int j = 1; j < nVertex; j++){
        		if(i != j){
        			rnd = (int) (Math.random()*2+1) -1;
        			AdMatrix[i][j] = String.valueOf(rnd);
        			System.out.print(rnd);
        		} 
        	}
        }
        
        DefaultWeightedEdge e;
        for(int i = 1; i < nVertex; i++){
        	for(int j = 1; j < nVertex; j++){
        		System.out.println(AdMatrix[i][0]+" - "+AdMatrix[0][j]);
        		if(i != j && AdMatrix[i][j].compareTo("0") == 1){
        			e = g.addEdge(AdMatrix[i][0].toString(),AdMatrix[0][j].toString());
        			System.out.println(e);
        			if(e != null){
        				rnd = (int)(Math.random()*15+1);
        				g.setEdgeWeight(e, rnd);
        			}
        		}
        	}
        }
        return g;
    }
}
