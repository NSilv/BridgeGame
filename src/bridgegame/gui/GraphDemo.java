package bridgegame.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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



public class GraphDemo implements ActionListener{
	private static void createAndShowGui(){
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
				startMenu();
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

	public static void startMenu(){
		JFrame frame = new JFrame("Menu Demo");
		frame.setSize(220, 200);
		
		JPanel panel = new JPanel(new FlowLayout());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar jmb = new JMenuBar();

		JMenu jmFile = new JMenu("File");
		JMenuItem jmiOpen = new JMenuItem("Open");
		JMenuItem jmiClose = new JMenuItem("Close");
		JMenuItem jmiSave = new JMenuItem("Save");
		JMenuItem jmiExit = new JMenuItem("Exit");
		jmFile.add(jmiOpen);
		jmFile.add(jmiClose);
		jmFile.add(jmiSave);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		jmb.add(jmFile);

		JMenu jmOptions = new JMenu("Options");
		JMenu a = new JMenu("A");
		JMenuItem b = new JMenuItem("B");
		JMenuItem c = new JMenuItem("C");
		JMenuItem d = new JMenuItem("D");
		a.add(b);
		a.add(c);
		a.add(d);
		jmOptions.add(a);
		JButton startButton = new JButton("Start");

		startButton.addActionListener((ActionEvent event) -> {
			frame.dispose();
			createAndShowGui();
		});
		startButton.setSize(100, 200);
		frame.add(startButton);

		JMenu e = new JMenu("E");
		e.add(new JMenuItem("F"));
		e.add(new JMenuItem("G"));
		jmOptions.add(e);

		jmb.add(jmOptions);

		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiAbout = new JMenuItem("About");
		jmHelp.add(jmiAbout);
		jmb.add(jmHelp);

		ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				String comStr = ae.getActionCommand();
				System.out.println(comStr + " Selected");
			}
		};

		jmiOpen.addActionListener(listener);
		jmiClose.addActionListener(listener);
		jmiSave.addActionListener(listener);
		jmiExit.addActionListener(listener);
		b.addActionListener(listener);
		c.addActionListener(listener);
		d.addActionListener(listener);
		jmiAbout.addActionListener(listener);

		frame.setJMenuBar(jmb);
		frame.setVisible(true);
		
		panel.add(startButton);
		
		frame.setContentPane(panel);
		frame.setJMenuBar(jmb);
		frame.setVisible(true); 
		
	}



	@Override
	public void actionPerformed(ActionEvent ae){
		String comStr = ae.getActionCommand();
		System.out.println(comStr + " Selected");
	}


}
