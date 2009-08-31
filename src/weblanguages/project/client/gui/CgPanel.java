package weblanguages.project.client.gui;

import it.cg.wl.sartoriWLC.Group;
import it.cg.wl.sartoriWLC.GroupLabel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.rmi.RemoteException;
import javax.swing.*;
import javax.swing.border.Border;

import weblanguages.project.client.Connector;

public class CgPanel extends JPanel{
	int num = 3;
	JTextField[] params;
	JTextField[] upd_p;
	JTextField[] l_params;
	JTextField[] l_upd_p;
	JButton ins, del, upd, src;
	JButton bins, bdel, bupd, bsrc;
	JLabel status;
	Connector c;
	CgListener cpl;
	JTextArea tb;
	
	public CgPanel(Connector c){
		this.c = c;
		status = new JLabel("Ready");
		cpl = new CgListener(this);
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
		Border b = BorderFactory.createTitledBorder("Group");
		ins = new JButton("Insert");
		del = new JButton("Delete");
		src = new JButton("Search");
		upd = new JButton("Update");
		fie.add(new JLabel("Select: "));
		ufie.add(new JLabel("Update with: "));
		for (int i = 0; i < num; i++){
			params[i] = new JTextField(10);
			upd_p[i] = new JTextField(10);
			fie.add(params[i]);
			ufie.add(upd_p[i]);
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
		res.setBorder(b);
		return res;
	}
	
	private JPanel set_bot_panel(){
		JPanel res = new JPanel();
		JPanel but = new JPanel();
		JPanel fie = new JPanel();
		JPanel ufie =new JPanel();
		bins = new JButton("Insert");
		bdel = new JButton("Delete");
		bsrc = new JButton("Search");
		bupd = new JButton("Update");
		fie.add(new JLabel("Select: "));
		ufie.add(new JLabel("Update with: "));
		for (int i = 0; i < 2; i++){
			l_params[i] = new JTextField(10);
			l_upd_p[i] = new JTextField(10);
			fie.add(l_params[i]);
			ufie.add(l_upd_p[i]);
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
		Border b = BorderFactory.createTitledBorder("Group Label");
		res.setBorder(b);
		return res;
	}
	
	public Group get_group(JTextField[] params){
		Group g = new Group();
		if (params[0].getText().compareTo("") != 0) g.setId_group(Integer.parseInt(params[0].getText()));
		if (params[1].getText().compareTo("") != 0) g.setGroup_name(params[1].getText());
		if (params[2].getText().compareTo("") != 0) g.setGroup_uri(params[2].getText());
		return g;
	}
	
	public GroupLabel get_group_l(JTextField[] params){
		GroupLabel g = new GroupLabel();
		if (params[0].getText().compareTo("") != 0) g.setId_group(Integer.parseInt(params[0].getText()));
		if (params[1].getText().compareTo("") != 0) g.setId_label(Integer.parseInt(params[1].getText()));
		return g;
	}
	
	public void show_values(Group[] g){
		String tmp = "";
		for (int i = 0; i < g.length; i++){
			tmp += g[i].getId_group() + ",\t";
			tmp += g[i].getGroup_name() + ",\t";
			tmp += g[i].getGroup_uri() + ",\t";
			tmp += "\n";
		}
		tb.setText(tmp);
	}
	
	public void show_values_l(GroupLabel[] g){
		String tmp = "";
		for (int i = 0; i < g.length; i++){
			tmp += g[i].getId_group() + ",\t";
			tmp += g[i].getId_label() + ",\t";
			tmp += "\n";
		}
		tb.setText(tmp);
	}
	
	private class CgListener extends MouseAdapter implements MouseListener {
		CgPanel p;
		public CgListener(CgPanel p){
			this.p = p;
		}
		public void mouseClicked(MouseEvent e){
			if (e.getSource() == p.ins){
				boolean res;
				try {
					status.setText("Loading...");
					res = c.go.createGroup(p.get_group(p.params));
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.src){
				Group[] res;
				try {
					status.setText("Loading...");
					status.updateUI();
					res = c.go.readGroup(p.get_group(p.params));
					status.setText("OK");
					p.show_values(res);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.del){
				boolean res;
				try {
					status.setText("Loading...");
					status.updateUI();
					res = c.go.deleteGroup(p.get_group(p.params));
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
					status.updateUI();
					res = c.go.updateGroup(p.get_group(p.params), p.get_group(p.upd_p));
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.bins){
				boolean res;
				try {
					status.setText("Loading...");
					res = c.go.createGroupLabel(p.get_group_l(p.l_params));
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
					res = c.go.deleteGroupLabel(p.get_group_l(p.l_params));
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
					res = c.go.updateGroupLabel(p.get_group_l(p.l_params), p.get_group_l(p.l_upd_p));
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.getSource() == p.bsrc){
				GroupLabel[] res;
				try {
					status.setText("Loading...");
					res = c.go.readGroupLabel(p.get_group_l(p.l_params));
					status.setText("OK");
					p.show_values_l(res);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
	}	
}
