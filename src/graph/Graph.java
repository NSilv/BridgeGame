/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import static java.util.Arrays.stream;
import java.util.function.Consumer;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import utils.*;

/**
 *
 * @author s01412
 * @param <A>
 */
public class Graph<T> {
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
    }
}
