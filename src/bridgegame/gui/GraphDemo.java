package bridgegame.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraphSelectionModel;

import bridgegame.gui.GraphDemo_2.MyEdge;


public class GraphDemo {
	 private static void createAndShowGui() {
	        JFrame frame = new JFrame("DemoGraph");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        SimpleWeightedGraph<String, MyEdge> g = buildGraph();
	        JGraphXAdapter<String, MyEdge> graphAdapter = new JGraphXAdapter<String, MyEdge>(g){
	        	  public boolean isCellMovable(Object cell)
	        	  {
	        	    return !getModel().isEdge(cell);
	        	  }
	        };

	        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
	        layout.execute(graphAdapter.getDefaultParent());
	        final mxGraphComponent comp = new mxGraphComponent(graphAdapter);
	        frame.add(comp);
	        frame.pack();
	        frame.setLocationByPlatform(true);
	        frame.setVisible(true);
	        graphAdapter.setCellsEditable(false);
	        
	        Object[] cell = graphAdapter.getSelectionCells();
	        
	        graphAdapter.getSelectionModel().addListener(mxEvent.CHANGE, (sender, evt) -> {
	        	log(evt.toString());
	        	log(((mxGraphSelectionModel)sender).getCells()[0]);
	        	for(Object cell1 : ((mxGraphSelectionModel)sender).getCells()){
	        		log("cell: " + graphAdapter.getLabel(cell1));
	        	}
	        });
	        
	        
	        
	 }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGui();
	            }
	        });
	    }

	    public static class MyEdge extends DefaultWeightedEdge {
	        @Override
	        public String toString() {
	            return String.valueOf(getWeight());
	        }
	    }

	    public static SimpleWeightedGraph<String, MyEdge> buildGraph() {
	        SimpleWeightedGraph<String, MyEdge> g = 
	            new SimpleWeightedGraph<String, MyEdge>(MyEdge.class);

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
	        
	        MyEdge e;
	        for(int i = 1; i < nVertex; i++){
	        	for(int j = 1; j < nVertex; j++){
	        		System.out.println(AdMatrix[i][0]+" - "+AdMatrix[0][j]);
	        		if(i != j && AdMatrix[i][j].compareTo("0") == 1){
	        			e = g.addEdge(AdMatrix[i][0].toString(),AdMatrix[0][j].toString());
	        			
	        			rnd = (int)(Math.random()*15+1);
	        			g.setEdgeWeight(e, rnd);
	        		}
	        	}
	        }
	        /*
	        String x1 = "x1";
	        String x2 = "x2";
	        String x3 = "x3";

	        g.addVertex(x1);
	        g.addVertex(x2);
	        g.addVertex(x3);

	        MyEdge e = g.addEdge(x1, x2);
	        g.setEdgeWeight(e, 1);
	        e = g.addEdge(x2, x3);
	        g.setEdgeWeight(e, 2);

	        e = g.addEdge(x3, x1);
	        g.setEdgeWeight(e, 3);
*/
	        return g;
	    }
	    public static <T>void log(T x){
	    	System.out.println(x);
	    }
}
