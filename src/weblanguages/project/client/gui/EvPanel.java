package weblanguages.project.client.gui;

import groupeval.stub.EvalBean;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import coauthors.stub.Coauthors;

import weblanguages.project.client.ConnLocal;

public class EvPanel extends JPanel{
	JLabel out;
	JTextArea ata;
	JButton add, del, ok;
	JTextField a;
	
	ArrayList<String> authors;
	EvListener evl;
	ConnLocal l;
	
	public EvPanel(ConnLocal l){
		this.l = l;
		this.setLayout(new BorderLayout());
		authors = new ArrayList<String>();
		evl = new EvListener();
		ata = new JTextArea();
		a = new JTextField(15);
		out = new JLabel();
		out.setText("Ready");
		add = new JButton("Add");
		del = new JButton("Delete");
		ok = new JButton("OK");
		add.addMouseListener(evl);
		del.addMouseListener(evl);
		ok.addMouseListener(evl);
		this.add(set_top_panel(), BorderLayout.NORTH);
		this.add(set_main_panel(), BorderLayout.CENTER);
	}
	
	private JPanel set_top_panel(){
		JPanel tmp = new JPanel();
		tmp.add(new JLabel("Author: "));
		tmp.add(a);
		tmp.add(add);
		tmp.add(del);
		tmp.add(ok);
		return tmp;
	}
	
	private JSplitPane set_main_panel(){
		JSplitPane res = new JSplitPane();
		JScrollPane side = new JScrollPane(ata);
		Border b = BorderFactory.createTitledBorder("Authors");
		Border b2 = BorderFactory.createTitledBorder("Evaluation");
		side.setBorder(b);
		JPanel tmp = new JPanel();
		tmp.add(out);
		tmp.setBorder(b2);
		res.setLeftComponent(tmp);
		res.setRightComponent(side);
		res.setDividerLocation(550);
		return res;
	}
	
	public void draw_output(EvalBean ca){
		out.setText("");
		String txt = "<html>";
		txt += "<p>H index (avg) <b>" + ca.getH_index() + "</b></p>";
		txt += "<p>H index (max) <b>" + ca.getH_max() + "</b></p>";
		txt += "<p>H index (min) <b>" + ca.getH_min() + "</b></p>";
		txt += "<p>G index (avg) <b>" + ca.getG_index() + "</b></p>";
		txt += "<p>G index (max) <b>" + ca.getG_max() + "</b></p>";
		txt += "<p>G index (min) <b>" + ca.getG_min() + "</b></p>";
		txt += "<p>Citation count (avg) <b>" + ca.getCit_avg() + "</b></p>";
		txt += "<p>Citation count (max) <b>" + ca.getCit_max() + "</b></p>";
		txt += "<p>Citation count (min) <b>" + ca.getCit_min() + "</b></p>";
		txt += "<p>Publications (avg) <b>" + ca.getPub_count() + "</b></p>";
		txt += "<p>Publications (max) <b>" + ca.getPub_max() + "</b></p>";
		txt += "<p>Publications (min) <b>" + ca.getPub_min() + "</b></p><br/>";
		txt += "<p>Authors not found:</p>";
		for (int i = 0; i < ca.getNot_found().length; i++){
			txt += "<p>" + ca.getNot_found()[i] + "</p>";
		}
		txt += "</html>";
		out.setText(txt);
	}
	
	private class EvListener extends MouseAdapter implements MouseListener {
		public void mouseClicked(MouseEvent e){
			if (e.getSource() == add){
				String a_name = a.getText();
				if (a_name.compareTo("") != 0){
					authors.add(a_name);
					ata.setText(ata.getText() + a_name + "\n");
				}
			}
			else if (e.getSource() == del){
				authors.clear();
				ata.setText("");
			}
			else if (e.getSource() == ok){
				if (authors.size() != 0){
					String[] arr = new String[authors.size()];
					EvalBean res;
					arr = authors.toArray(arr);
					try {
						res = l.ge.getAuthorsRank(arr);
						draw_output(res);
					} catch (RemoteException e1) {
						out.setText("ERR");
					}
				}
			}
		}
	}
}
