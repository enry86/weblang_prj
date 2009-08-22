package weblanguages.project.client;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import coauthors.stub.*;
import groupeval.stub.*;
import similar.stub.*;

import it.cg.wl.sartoriWLC.*;
import it.cp.wl.sartoriWLC.*;


public class Client {
	GroupEval stub_e;
	GroupEvalService serv_e;
	
	DblpCoauthors stub_d;
	DblpCoauthorsService serv_d;
	
	PersonOperation stub_cp;
	PersonOperationService serv_cp;
	
	GroupOperation stub_cg;
	GroupOperationService serv_cg;
	
	AuthSimilar stub_sim;
	AuthSimilarService serv_sim;
	
	public Client(){
		//test_eval();
		//test_coau();
		test_cp();
		//test_cg();
		//test_sim();
	}
	
	private void test_eval(){
		EvalBean res;
		String[] input = new String[6];
		input[0] = "Fabio Casati";
		input[1] = "Maurizio Marchese";
		input[2] = "Themis Palpanas";
		input[3] = "Florian Daniel";
		input[4] = "Fausto Giunchiglia";
		input[5] = "Yannis Velegrakis";
		
		serv_e=new GroupEvalServiceLocator();
		try {
			stub_e=serv_e.getGroupEval();
			res = stub_e.getAuthorsRank(input);
			
			
			System.out.println(res.getH_index());
			System.out.println(res.getH_max());
			System.out.println(res.getH_min());
			
			System.out.println(res.getG_index());
			System.out.println(res.getG_max());
			System.out.println(res.getG_min());		
			
			System.out.println(res.getCit_avg());
			System.out.println(res.getCit_max());
			System.out.println(res.getCit_min());
			
			System.out.println(res.getPub_count());
			System.out.println(res.getPub_max());
			System.out.println(res.getPub_min());
			
			System.out.println(res.getNot_found().length);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	private void test_coau(){
		coauthors.stub.Coauthors[] res;
		String[] input = new String[6];
		input[0] = "Fabio Casati";
		input[1] = "Maurizio Marchese";
		input[2] = "Themis Palpanas";
		input[3] = "Florian Daniel";
		input[4] = "Fausto Giunchiglia";
		input[5] = "Yannis Velegrakis";
		
		serv_d=new DblpCoauthorsServiceLocator();
		try {
			stub_d=serv_d.getDblpCoauthors();
			res = stub_d.getDblpCoauthors(input);
			for (int i = 0; i < input.length; i++){
				System.out.println("#######"+input[i]);
				String[] coauth = res[i].getCoauthors();
				for (int k = 0; k < coauth.length; k++){
					System.out.println(coauth[k]);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void test_cp(){
		PersonLabel p = new PersonLabel();
		Person p2 = new Person();
		Person[] r;
		p2.setCitizenship("Nowhere2");
		//p.setFirst_name("Moar2");
		//p.setLast_name("Fud");
		//p.setCitizenship("Kitteh");
		//p.setTitle("kth.");
		//p.setEmail("me@kitteh.lol");
		p.setId_user(2);
		//p.setId_label(12);
		serv_cp = new PersonOperationServiceLocator();
		try {
			stub_cp = serv_cp.getSartoriWLC_CRUDPerson();
		//	System.out.println(stub_cp.readPerson(p2)[0].getCitizenship());
			System.out.println(stub_cp.deletePersonLabel(p));
		/*	for (int i = 0; i < r.length; i++){
				System.out.println(r[i].getId_person());
				System.out.println(r[i].getFirst_name());
				System.out.println(r[i].getLast_name());
				System.out.println(r[i].getCitizenship());
				System.out.println(r[i].getTitle());
				System.out.println(r[i].getEmail());
				System.out.println(r[i].getPerson_uri());
			}*/
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void test_cg(){
		GroupLabel g = new GroupLabel();
		GroupLabel g2 = new GroupLabel();
		GroupLabel[] r;
		g.setId_group(7);
		g2.setId_label(3);
		//g.setId_label(1);
		//g.setGroup_name("Science2");
		//g2.setGroup_uri("http://lolololool.3");
		serv_cg = new GroupOperationServiceLocator();
		try {
			stub_cg = serv_cg.getSartoriWLC_CRUDGroup();
			System.out.println(stub_cg.updateGroupLabel(g,g2));
			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void test_sim(){
		SimilarBean r;
		serv_sim = new AuthSimilarServiceLocator();
		try {
			stub_sim = serv_sim.getAuthSimilarity();
			r = stub_sim.get_similarity("Fabio Casati", "Maurizio Marchese");
			System.out.println(r.getAb_similarity());
			System.out.println(r.getBa_similarity());
			System.out.println(r.getGeneral_similarity());
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
			
		
	}
	
	public static void main(String[] args){
		new Client();
	}
}
