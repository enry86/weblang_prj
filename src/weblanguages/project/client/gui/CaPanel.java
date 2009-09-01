package weblanguages.project.client.gui;

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

public class CaPanel extends JPanel{
	JTextArea out;
	JTextArea ata;
	JButton add, del, ok;
	JTextField a;
	
	ArrayList<String> authors;
	CaListener cal;
	ConnLocal l;
	
	public CaPanel(ConnLocal l){
		this.l = l;
		this.setLayout(new BorderLayout());
		authors = new ArrayList<String>();
		cal = new CaListener();
		ata = new JTextArea();
		a = new JTextField(15);
		out = new JTextArea();
		add = new JButton("Add");
		del = new JButton("Delete");
		ok = new JButton("OK");
		add.addMouseListener(cal);
		del.addMouseListener(cal);
		ok.addMouseListener(cal);
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
		Border b2 = BorderFactory.createTitledBorder("Coauthors");
		side.setBorder(b);
		JScrollPane tmp = new JScrollPane(out);
		tmp.setBorder(b2);
		res.setLeftComponent(tmp);
		res.setRightComponent(side);
		res.setDividerLocation(550);
		return res;
	}
	
	public void draw_output(Coauthors[] ca){
		out.setText("");
		String txt = "";
 		for (int i = 0; i < ca.length; i++){
			txt += ca[i].getAuthor() + ":\n";
			String[] co = ca[i].getCoauthors();
			for (int k = 0; k < co.length; k++){
				txt += "\t" + co[k] + "\n";
			}
			txt += "\n";
		}
		out.setText(txt);
	}
	
	private class CaListener extends MouseAdapter implements MouseListener {
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
					Coauthors[] res;
					arr = authors.toArray(arr);
					try {
						res = l.ca.getDblpCoauthors(arr);
						draw_output(res);
					} catch (RemoteException e1) {
						out.setText("ERR");
					}
				}
			}
		}
	}
}
