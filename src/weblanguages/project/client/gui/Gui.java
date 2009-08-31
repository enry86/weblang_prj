package weblanguages.project.client.gui;

import javax.swing.*;
import javax.swing.event.*;

import weblanguages.project.client.Connector;
import java.awt.*;
import java.awt.event.*;

public class Gui {
	JFrame frame;
	JPanel cont;
	Connector c;
	
	public Gui(Connector c){
		this.c = c;
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
	
	private JPanel setup_content(){
		JPanel tmp = new JPanel();
		tmp.setLayout(new BorderLayout());
		JTabbedPane tp = new JTabbedPane();
		tp.addTab("CRUD Person", new CpPanel(c));
		tp.addTab("CRUD Group", new CgPanel(c));
		tp.addTab("Search Label", new SlPanel(c));
		tp.addTab("Group Metric", new GmPanel(c));
		tmp.add(tp);
		return tmp;
	}
}
