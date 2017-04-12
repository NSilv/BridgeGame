package bridgegame.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraphSelectionModel;

import bridgegame.graph.Graph;



public class GraphDemo {
	 private static void createAndShowGui() {
	        JFrame frame = new JFrame("DemoGraph");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        SimpleWeightedGraph<String, DefaultWeightedEdge> g = Graph.random();
	        JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter<String, DefaultWeightedEdge>(g){
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

	    public static <T>void log(T x){
	    	System.out.println(x);
	    }
}
