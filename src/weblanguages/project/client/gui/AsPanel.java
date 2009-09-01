package weblanguages.project.client.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.*;
import similar.stub.SimilarBean;
import weblanguages.project.client.ConnLocal;

public class AsPanel extends JPanel {
	JTextField a;
	JTextField b;
	JLabel out;
	JButton cmp;
	
	ConnLocal c;
	AsListener asl;
	
	public AsPanel(ConnLocal c){
		this.c = c;
		asl = new AsListener();
		this.setLayout(new BorderLayout());
		this.add(set_top_panel(), BorderLayout.NORTH);
		this.add(set_main_panel(), BorderLayout.CENTER);
	}
	
	private JPanel set_top_panel(){
		JPanel tmp = new JPanel();
		a = new JTextField(8);
		b = new JTextField(8);
		cmp = new JButton("Compare");
		cmp.addMouseListener(asl);
		tmp.add(new JLabel("Author A: "));
		tmp.add(a);
		tmp.add(new JLabel("Author B: "));
		tmp.add(b);
		tmp.add(cmp);
		return tmp;
	}
	
	private JPanel set_main_panel(){
		JPanel tmp = new JPanel();
		out = new JLabel("Waiting for requests");
		tmp.add(out);
		return tmp;
	}
	
	public String get_values(SimilarBean s){
		String res = "<html><p>A ==> B: <b>" + s.getAb_similarity() + "</b></p>";
		res += "<p>B ==> A: <b>" + s.getBa_similarity() + "</b></p>";
		res += "<p>Overall similarity: <b>" + s.getGeneral_similarity() + "</b></p></html>";
		return res;
	}
	
	private class AsListener extends MouseAdapter implements MouseListener{
		public void mouseClicked(MouseEvent e){
			if (e.getSource() == cmp){
				SimilarBean sb;
				try {
					sb = c.as.get_similarity(a.getText(), b.getText());
					out.setText(get_values(sb));
				} catch (RemoteException e1) {
					out.setText("ERR");
				}
				
			}
		}
	}
}
