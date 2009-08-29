package weblanguages.project.client.gui;

import it.cp.wl.sartoriWLC.Person;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.rmi.RemoteException;

import javax.swing.*;

import weblanguages.project.client.Connector;

public class CpPanel extends JPanel{
	int num = 7;
	JTextField[] params;
	JButton ins, del, upd, src;
	JLabel status;
	Connector c;
	CpListener cpl;
	JTextArea tb;
	
	public CpPanel(Connector c){
		this.c = c;
		status = new JLabel("Ready");
		cpl = new CpListener(this);
		setLayout(new BorderLayout());
		params = new JTextField[num];
		tb = new JTextArea();
		JScrollPane sp = new JScrollPane(tb);
		add(sp, BorderLayout.CENTER);
		JPanel top = set_top_panel();
		add(top, BorderLayout.NORTH);
		setVisible(true);
	}
	

	private JPanel set_top_panel(){
		JPanel res = new JPanel();
		JPanel but = new JPanel();
		JPanel fie = new JPanel();
		ins = new JButton("Insert");
		del = new JButton("Delete");
		src = new JButton("Search");
		for (int i = 0; i < num; i++){
			params[i] = new JTextField(10);
			fie.add(params[i]);
		}
		but.add(ins);
		but.add(del);
		but.add(src);
		but.add(status);
		ins.addMouseListener(cpl);
		del.addMouseListener(cpl);
		src.addMouseListener(cpl);		
		res.setLayout(new BorderLayout());
		res.add(fie, BorderLayout.NORTH);
		res.add(but, BorderLayout.SOUTH);
		return res;
	}
	
	public Person get_person(){
		Person p = new Person();
		if (params[0].getText().compareTo("") != 0) p.setId_person(Integer.parseInt(params[0].getText()));
		if (params[1].getText().compareTo("") != 0) p.setFirst_name(params[1].getText());
		if (params[2].getText().compareTo("") != 0) p.setLast_name(params[2].getText());
		if (params[3].getText().compareTo("") != 0) p.setCitizenship(params[3].getText());
		if (params[4].getText().compareTo("") != 0) p.setTitle(params[4].getText());
		if (params[5].getText().compareTo("") != 0) p.setEmail(params[5].getText());
		if (params[6].getText().compareTo("") != 0) p.setPerson_uri(params[6].getText());
		return p;
	}
	
	public void show_values(Person[] p){
		String tmp = "";
		for (int i = 0; i < p.length; i++){
			tmp += p[i].getId_person() + ", ";
			tmp += p[i].getFirst_name() + ", ";
			tmp += p[i].getLast_name() + ", ";
			tmp += p[i].getCitizenship() + ", ";
			tmp += p[i].getTitle() + ", ";
			tmp += p[i].getEmail() + ", ";
			tmp += p[i].getPerson_uri() + ", ";
			tmp += "\n";
		}
		tb.setText(tmp);
	}
	
	private class CpListener extends MouseAdapter implements MouseListener {
		CpPanel p;
		public CpListener(CpPanel p){
			this.p = p;
		}
		public void mouseClicked(MouseEvent e){
			if (e.getSource() == p.ins){
				boolean res;
				try {
					status.setText("Loading...");
					res = c.po.createPerson(p.get_person());
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == p.src){
				Person[] res;
				try {
					status.setText("Loading...");
					res = c.po.readPerson(p.get_person());
					status.setText("OK");
					p.show_values(res);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
	}	
}
