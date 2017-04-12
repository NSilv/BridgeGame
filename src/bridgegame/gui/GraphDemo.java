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
/*
 * Il main è in fondo
 */
	
//CREATE AND SHOW GUY
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



	public static class MyEdge extends DefaultWeightedEdge {
		@Override
		public String toString() {
			return String.valueOf(getWeight());
		}
	}

	public static <T>void log(T x){
		System.out.println(x);
	}    

//MENU
	public static void startMenu(){
	//Creazione frame
		
		//Creazione jframe
		JFrame frame = new JFrame("Menu Demo");
		frame.setSize(440, 400);
		
		//Creazione jPanel gridLayout
		JPanel panel = new JPanel(new GridLayout(4,1));
		
		//Setto chiusura frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	//Creo il listener
		ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				String comStr = ae.getActionCommand();
				System.out.println(comStr + " Selected");
			}
		};
		
		
	//Creazione menù
		
		//Setto il menù
		JMenuBar jmb = new JMenuBar();
		
		//Setto file
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
		jmb.add(jmFile);		//aggiungo file al menuBar

		//Setto options
		JMenu jmOptions = new JMenu("Options");
		
		JMenu a = new JMenu("A");
		JMenuItem b = new JMenuItem("B");
		JMenuItem c = new JMenuItem("C");
		JMenuItem d = new JMenuItem("D");
		a.add(b);
		a.add(c);
		a.add(d);
		jmOptions.add(a);	//Aggiungo a alle opzioni	

		JMenu e = new JMenu("E");
		e.add(new JMenuItem("F"));
		e.add(new JMenuItem("G"));
		jmOptions.add(e);	//Aggiungo e alle opzioni	

		jmb.add(jmOptions);		//aggiungo options al menuBar

		//Setto help
		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiAbout = new JMenuItem("About");
		jmHelp.add(jmiAbout);
		jmb.add(jmHelp);		//aggiungo help al menuBar

		//Setto il listener nei vari campi
		jmiOpen.addActionListener(listener);
		jmiClose.addActionListener(listener);
		jmiSave.addActionListener(listener);
		jmiExit.addActionListener(listener);
		b.addActionListener(listener);
		c.addActionListener(listener);
		d.addActionListener(listener);
		jmiAbout.addActionListener(listener);
	
		
	//Creazione componenti
		
		//bottone
		JButton startButton = new JButton("Start");
		startButton.addActionListener((ActionEvent event) -> {
			frame.dispose();
			createAndShowGui();
		});
		startButton.setSize(100, 200);
		frame.add(startButton);
		
		//label inserisci nickname
		JLabel lInsNN = new JLabel("Inserisci nickname");
		
		//textbox inserisci nickname
		JTextField tbInsNN = new JTextField();
		
		//label inserisci ip
		JLabel lInsIP = new JLabel("Inserisci nickname");
				
		//textbox inserisci ip
		JTextField tbInsIP = new JTextField();
		
		//label inserisci porta
		JLabel lInsPort = new JLabel("Inserisci nickname");
						
		//textbox inserisci porta
		JTextField tbInsPort = new JTextField();
		
		
	//Aggiunta componenti al panel
		panel.add(lInsNN);
		panel.add(tbInsNN);
		panel.add(lInsIP);
		panel.add(tbInsIP);
		panel.add(lInsPort);
		panel.add(tbInsPort);
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
	
	
	
//M A I N
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				startMenu();
			}
		});
	}


}
