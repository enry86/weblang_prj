package weblanguages.project.client.gui;

import it.cg.wl.sartoriWLC.Group;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.rmi.RemoteException;
import javax.swing.*;
import weblanguages.project.client.Connector;

public class CgPanel extends JPanel{
	int num = 3;
	JTextField[] params;
	JButton ins, del, upd, src;
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
	
	public Group get_group(){
		Group g = new Group();
		if (params[0].getText().compareTo("") != 0) g.setId_group(Integer.parseInt(params[0].getText()));
		if (params[1].getText().compareTo("") != 0) g.setGroup_name(params[1].getText());
		if (params[2].getText().compareTo("") != 0) g.setGroup_uri(params[2].getText());
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
					res = c.go.createGroup(p.get_group());
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
					res = c.go.readGroup(p.get_group());
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
					res = c.go.deleteGroup(p.get_group());
					if (res) status.setText("OK");
					else status.setText("ERR");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
	}	
}
