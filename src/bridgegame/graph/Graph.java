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
        
        int notTheActualNofVertex = 2;
        int nVertex = 1+notTheActualNofVertex;
        
        String[][] AdMatrix = new String[nVertex][nVertex];
        Boolean[][] bul = new Boolean[nVertex][nVertex];
        
        AdMatrix[0][0] = "0";
        for(int i = 1; i < nVertex; i++){
        	String name = String.valueOf((int)(Math.random()*9)) + String.valueOf((int)(Math.random()*9));
        	AdMatrix[0][i] = AdMatrix[i][0] = name;
        	g.addVertex(AdMatrix[0][i]);
        }
        
        for(int i = 1; i < nVertex; i++){
        	for(int j = 1; j < nVertex; j++){
        		if(i != j){
        			AdMatrix[i][j] = String.valueOf((int) (Math.random()*2+1) -1);
        		}else AdMatrix[i][i] = "0";
        	}
        }
        
        for(int i = 0; i < nVertex; i++){
        	for(int j = 0; j < nVertex; j++){
        		System.out.print(AdMatrix[i][j]+" ");
        	}
        	System.out.println();
        }
        
        DefaultWeightedEdge e;        
        for(int i = 1; i < nVertex; i++){
        	for(int j = 1; j < nVertex; j++){
        		if(AdMatrix[i][j].compareTo("1") == 0 && AdMatrix[i][0]!=AdMatrix[0][j]){
        			bul[i][j] = true;
        			e = g.addEdge(AdMatrix[i][0],AdMatrix[0][j]);
    				if(e != null) g.setEdgeWeight(e, (double)(Math.random()*15+1));
        		}        		
        	}
        }
        
        for(int i = 0; i < nVertex; i++){
        	for(int j = 0; j < nVertex; j++){
        		System.out.print(bul[i][j]+" ");
        	}
        	System.out.println();
        }
        return g;
    }
}
