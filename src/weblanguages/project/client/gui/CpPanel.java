package weblanguages.project.client.gui;

import it.cp.wl.sartoriWLC.Person;
import it.cp.wl.sartoriWLC.PersonLabel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.rmi.RemoteException;
import javax.swing.*;
import javax.swing.border.Border;
import weblanguages.project.client.Connector;

public class CpPanel extends JPanel{
	int num = 7;
	JTextField[] params;
	JTextField[] upd_p;
	JTextField[] l_params;
	JTextField[] l_upd_p;
	JButton ins, del, upd, src;
	JButton bins, bdel, bupd, bsrc;
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
		upd_p = new JTextField[num];
		l_params = new JTextField[2];
		l_upd_p = new JTextField[2];
		tb = new JTextArea();
		JScrollPane sp = new JScrollPane(tb);
		add(sp, BorderLayout.CENTER);
		JPanel top = set_top_panel();
		add(top, BorderLayout.NORTH);
		JPanel bot = set_bot_panel();
		add(bot, BorderLayout.SOUTH);
		setVisible(true);
	}
	

	private JPanel set_top_panel(){
		JPanel res = new JPanel();
		JPanel but = new JPanel();
		JPanel fie = new JPanel();
		JPanel ufie = new JPanel();
		JLabel slab = new JLabel("Select: ");
		JLabel ulab = new JLabel("Update with: ");
		Border bor = BorderFactory.createTitledBorder("Person");
		upd = new JButton("Update");
		ins = new JButton("Insert");
		del = new JButton("Delete");
		src = new JButton("Search");
		fie.add(slab);
		ufie.add(ulab);
		for (int i = 0; i < num; i++){
			params[i] = new JTextField(6);
			upd_p[i] = new JTextField(6);
			ufie.add(upd_p[i]);
			fie.add(params[i]);
		}
		but.add(upd);
		but.add(ins);
		but.add(del);
		but.add(src);
		but.add(status);
		upd.addMouseListener(cpl);
		ins.addMouseListener(cpl);
		del.addMouseListener(cpl);
		src.addMouseListener(cpl);		
		res.setLayout(new BorderLayout());
		res.add(fie, BorderLayout.NORTH);
		res.add(ufie, BorderLayout.CENTER);
		res.add(but, BorderLayout.SOUTH);
		res.setBorder(bor);
		return res;
	}
	
	private JPanel set_bot_panel(){
		JPanel res = new JPanel();
		JPanel but = new JPanel();
		JPanel fie = new JPanel();
		JPanel ufie = new JPanel();
		JLabel slab = new JLabel("Select: ");
		JLabel ulab = new JLabel("Update with: ");
		Border bor = BorderFactory.createTitledBorder("Person Label");
		bupd = new JButton("Update");
		bins = new JButton("Insert");
		bdel = new JButton("Delete");
		bsrc = new JButton("Search");
		fie.add(slab);
		ufie.add(ulab);
		for (int i = 0; i < 2; i++){
			l_params[i] = new JTextField(6);
			l_upd_p[i] = new JTextField(6);
			ufie.add(l_upd_p[i]);
			fie.add(l_params[i]);
		}
		but.add(bupd);
		but.add(bins);
		but.add(bdel);
		but.add(bsrc);
		but.add(status);
		bupd.addMouseListener(cpl);
		bins.addMouseListener(cpl);
		bdel.addMouseListener(cpl);
		bsrc.addMouseListener(cpl);		
		res.setLayout(new BorderLayout());
		res.add(fie, BorderLayout.NORTH);
		res.add(ufie, BorderLayout.CENTER);
		res.add(but, BorderLayout.SOUTH);
		res.setBorder(bor);
		return res;
	}
	
	public Person get_person(JTextField[] params){
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
	
	
	public PersonLabel get_person_l(JTextField[] params){
		PersonLabel p = new PersonLabel();
		if (params[0].getText().compareTo("") != 0) p.setId_user(Integer.parseInt(params[0].getText()));
		if (params[1].getText().compareTo("") != 0) p.setId_label(Integer.parseInt(params[1].getText()));
		return p;
	}
	
	public void show_values(Person[] p){
		String tmp = "";
		for (int i = 0; i < p.length; i++){
			tmp += p[i].getId_person() + ",\t";
			tmp += p[i].getFirst_name() + ",\t";
			tmp += p[i].getLast_name() + ",\t";
			tmp += p[i].getCitizenship() + ",\t";
			tmp += p[i].getTitle() + ",\t";
			tmp += p[i].getEmail() + ",\t";
			tmp += p[i].getPerson_uri() + ",\t";
			tmp += "\n";
		}
		tb.setText(tmp);
	}
	
	public void show_values_l(PersonLabel[] p){
		String tmp = "";
		for (int i = 0; i < p.length; i++){
			tmp += p[i].getId_user() + ",\t";
			tmp += p[i].getId_label() + ",\t";
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
					res = c.po.createPerson(p.get_person(params));
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.del){
				boolean res;
				try {
					status.setText("Loading...");
					res = c.po.deletePerson(p.get_person(params));
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.upd){
				boolean res;
				try {
					status.setText("Loading...");
					res = c.po.updatePerson(p.get_person(p.params), p.get_person(p.upd_p));
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.src){
				Person[] res;
				try {
					status.setText("Loading...");
					res = c.po.readPerson(p.get_person(params));
					status.setText("OK");
					p.show_values(res);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.bins){
				boolean res;
				try {
					status.setText("Loading...");
					res = c.po.createPersonLabel(p.get_person_l(p.l_params));
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.bdel){
				boolean res;
				try {
					status.setText("Loading...");
					res = c.po.deletePersonLabel(p.get_person_l(p.l_params));
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.bupd){
				boolean res;
				try {
					status.setText("Loading...");
					res = c.po.updatePersonLabel(p.get_person_l(p.l_params), p.get_person_l(p.l_upd_p));
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.bsrc){
				PersonLabel[] res;
				try {
					status.setText("Loading...");
					res = c.po.readPersonLabel(p.get_person_l(p.l_params));
					status.setText("OK");
					p.show_values_l(res);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
	}	
}
