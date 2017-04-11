package bridgegame.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;


public class GraphDemo {
	 private static void createAndShowGui() {
	        JFrame frame = new JFrame("DemoGraph");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        ListenableGraph<String, MyEdge> g = buildGraph();
	        JGraphXAdapter<String, MyEdge> graphAdapter = 
	                new JGraphXAdapter<String, MyEdge>(g);

	        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
	        layout.execute(graphAdapter.getDefaultParent());

	        frame.add(new mxGraphComponent(graphAdapter));

	        frame.pack();
	        frame.setLocationByPlatform(true);
	        frame.setVisible(true);
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

	    public static ListenableGraph<String, MyEdge> buildGraph() {
	        ListenableDirectedWeightedGraph<String, MyEdge> g = 
	            new ListenableDirectedWeightedGraph<String, MyEdge>(MyEdge.class);

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

	        return g;
	    }
}
