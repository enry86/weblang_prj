package weblanguages.project.client.gui;

import javax.swing.*;
import javax.swing.event.*;

import weblanguages.project.client.ConnLocal;
import weblanguages.project.client.Connector;
import java.awt.*;
import java.awt.event.*;

public class Gui {
	JFrame frame;
	JTabbedPane cont;
	Connector c;
	ConnLocal l;
	
	public Gui(Connector c, ConnLocal l){
		this.c = c;
		this.l = l;
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("LiquidPub WebServices test client");
		frame.setSize(new Dimension(800,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation((d.width - 800) / 2, (d.height - 600) / 2);
		cont = setup_content();
		frame.setContentPane(cont);
		frame.setVisible(true);
	}
	
	private JTabbedPane setup_content(){
		JTabbedPane main = new JTabbedPane();
		main.setTabPlacement(JTabbedPane.RIGHT);
		main.addTab("Phase 1", setup_phase1());
		main.addTab("Phase 2", setup_phase2());
		return main;
	}
	
	private JTabbedPane setup_phase1(){
		JTabbedPane tmp = new JTabbedPane();
		tmp.addTab("Coauthors", new JPanel());
		tmp.addTab("Group Evaluation", new JPanel());
		tmp.addTab("Author Similarity", new JPanel());
		return tmp;
	}
	
	private JTabbedPane setup_phase2(){
		JTabbedPane tmp = new JTabbedPane();
		tmp.addTab("CRUD Person", new CpPanel(c));
		tmp.addTab("CRUD Group", new CgPanel(c));
		tmp.addTab("Search Label", new SlPanel(c));
		tmp.addTab("Group Metric", new GmPanel(c));
		return tmp;
	}
}
