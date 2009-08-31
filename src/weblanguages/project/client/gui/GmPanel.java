package weblanguages.project.client.gui;

import it.eval.wl.sartoriWLC.EvalBean;
import it.eval.wl.sartoriWLC.GroupEval;
import it.eval.wl.sartoriWLC.GroupEvalService;
import it.eval.wl.sartoriWLC.GroupEvalServiceLocator;
import it.gm.wl.sartoriWLC.GMetric;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import weblanguages.project.client.Connector;

public class GmPanel extends JPanel{
	Connector c;
	int num_metr = 12;
	
	JComboBox metric;
	JLabel out;
	JTextField group;
	JButton ok;
	
	GmListener gml;
	
	public GmPanel(Connector c){
		this.c = c;
		gml = new GmListener(this);
		out = new JLabel("Watining for request");
		metric = new JComboBox(get_met_array());
		group = new JTextField(5);
		ok = new JButton("OK");
		ok.addMouseListener(gml);
		JPanel top = new JPanel();
		JPanel main = new JPanel();
		top.add(new JLabel("Compute metric no. "));
		top.add(metric);
		top.add(new JLabel(" for group no "));
		top.add(group);
		top.add(ok);
		main.add(out);
		this.setLayout(new BorderLayout());
		this.add(top, BorderLayout.NORTH);
		this.add(main, BorderLayout.CENTER);	
		
	}
	
	public String get_value(GMetric m){
		String res = "<html><p>Metric name: " + m.getMetric_name();
		res += " (" + m.getMetric_type() + ")</p>";
		res += "<h3>Metric value: " + m.getMetric_value() + "</h3>";
		res += "Result url: " + m.getGroup_metric_uri();
		res += "</html>";
		return res;
	}
	
	private Integer[] get_met_array(){
		Integer[] res = new Integer[num_metr];
		for (int i = 0; i < num_metr; i++){
			res[i] = new Integer(i + 1);
		}
		return res;
	}
	
	private class GmListener extends MouseAdapter implements MouseListener {
		private GmPanel p;
		
		public GmListener(GmPanel p){
			this.p = p;
		}
		
		public void mouseClicked(MouseEvent e){
			GMetric res;
			if (e.getSource() == p.ok){
				try {
					int g = Integer.parseInt(p.group.getText());
					int m = ((Integer)p.metric.getSelectedItem()).intValue();
					res = c.gm.getGroupMetric(g, m);
					p.out.setText(p.get_value(res));
					//GroupEvalService serv = new GroupEvalServiceLocator();
					//GroupEval st = serv.getSartoriWLC_GroupEval();
					//EvalBean eb = st.getAuthorsRank(new String[] {"Fabio Casati"});
					//System.out.println(eb.getCit_avg());
				} catch (Exception e1) {
					e1.printStackTrace();
					p.out.setText("ERR");
				}
			}
		}
	}
}
