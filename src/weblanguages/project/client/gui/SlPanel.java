package weblanguages.project.client.gui;

import it.sl.wl.sartoriWLC.Item;
import it.sl.wl.sartoriWLC.Label;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import weblanguages.project.client.Connector;

public class SlPanel extends JPanel {
	Connector c;
	int num = 3;
	SlListener sll;
	ArrayList<Label> labels;
	
	JTextField[] params;
	JButton add, del, src_all, src_any;
	JTextArea out;
	JTextArea lab;
	JLabel status;
	
	public SlPanel(Connector c){
		this.c = c;
		sll = new SlListener(this);
		params = new JTextField[num];
		out = new JTextArea();
		lab = new JTextArea();
		labels = new ArrayList<Label>();
		JPanel top = set_top_panel();
		Border b = BorderFactory.createTitledBorder("Labels");
		JScrollPane side_r = new JScrollPane(lab);
		JScrollPane side_l = new JScrollPane(out);
		JSplitPane main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, side_l, side_r);
		main.setDividerLocation(600);
		side_r.setBorder(b);
		setLayout(new BorderLayout());
		add(top, BorderLayout.NORTH);
		add(main, BorderLayout.CENTER);
		setVisible(true);
	}
	
	private JPanel set_top_panel(){
		JPanel res = new JPanel();
		JPanel fie = new JPanel();
		JPanel but = new JPanel();
		fie.add(new JLabel("Select: "));
		for (int i = 0; i < num; i++){
			params[i] = new JTextField(9);
			fie.add(params[i]);
		}
		
		add = new JButton("Add");
		del = new JButton("Delete");
		src_all = new JButton("Search All");
		src_any = new JButton("Search Any");
		add.addMouseListener(sll);
		del.addMouseListener(sll);
		src_any.addMouseListener(sll);
		src_all.addMouseListener(sll);
		but.add(add);
		but.add(del);
		but.add(src_all);
		but.add(src_any);
		status = new JLabel("Ready");
		but.add(status);
		res.setLayout(new BorderLayout());
		res.add(fie, BorderLayout.NORTH);
		res.add(but, BorderLayout.SOUTH);
		return res;
	}
	
	public Label get_label(JTextField[] p){
		Label res = new Label();
		if (p[0].getText().compareTo("") != 0) res.setId_label(Integer.parseInt(p[0].getText()));
		if (p[1].getText().compareTo("") != 0) res.setLabel_name(p[1].getText());
		if (p[2].getText().compareTo("") != 0) res.setLabel_value(p[2].getText());
		return res;
	}
	
	public String get_values(Label l){
		String tmp = "";
		tmp += l.getId_label() + ", ";
		tmp += l.getLabel_name() + ", ";
		tmp += l.getLabel_value() + ",\n";
		return tmp;
	}
	
	public String get_values(Item i){
		String tmp = "";
		tmp += i.getId_item() + ", ";
		tmp += i.getItem_type() + ", ";
		tmp += i.getItem_name() + ", ";
		tmp += i.getItem_uri() + ",\n";
		return tmp;
	}
	
	
	private class SlListener extends MouseAdapter implements MouseListener {
		SlPanel p;
		
		public SlListener(SlPanel p){
			this.p = p;
		}
		
		public void mouseClicked(MouseEvent e){
			if (e.getSource() == p.add){
				Label l = p.get_label(p.params);
				labels.add(l);
				p.lab.setText(p.lab.getText() + p.get_values(l)); 
			}
			else if (e.getSource() == p.del){
				labels.clear();
				p.lab.setText(""); 
			}
			else if (e.getSource() == p.src_all){
				Label l = get_label(p.params);
				Item[] res = new Item[0];
				if (labels.size() == 0){
					try {
						res = c.sl.searchLabel(l);
					} catch (RemoteException e1) {
						p.status.setText("ERR");
						p.out.setText("");
					}
				}
				else {
					try {
						Label[] ls = labels.toArray(new Label[labels.size()]); 
						res = c.sl.searchAllLabel(ls);
					} catch (RemoteException e1) {
						p.status.setText("ERR");
						p.out.setText("");
					}
				}
				String tmp = "";
				for (int i = 0; i < res.length; i++){
					tmp += p.get_values(res[i]);
				}
				p.out.setText(tmp);
			}
			else if (e.getSource() == p.src_any){
				Label l = get_label(p.params);
				Item[] res = new Item[0];
				if (labels.size() == 0){
					try {
						res = c.sl.searchLabel(l);
					} catch (RemoteException e1) {
						p.status.setText("ERR");
						p.out.setText("");
					}
				}
				else {
					try {
						Label[] ls = labels.toArray(new Label[labels.size()]); 
						res = c.sl.searchAnyLabel(ls);
					} catch (RemoteException e1) {
						p.status.setText("ERR");
						p.out.setText("");
					}
				}
				String tmp = "";
				for (int i = 0; i < res.length; i++){
					tmp += p.get_values(res[i]);
				}
				p.out.setText(tmp);
			}
		}
	}
	
}
