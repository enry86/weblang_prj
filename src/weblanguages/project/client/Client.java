package weblanguages.project.client;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import Eval.EvalBean;
import coauthors.stub.*;
import groupeval.stub.*;

import sartorienrico.CRUDPerson.stub.*;

public class Client {
	GroupEval stub_e;
	GroupEvalService serv_e;
	
	DblpCoauthors stub_d;
	DblpCoauthorsService serv_d;
	
	PersonOperation stub_cp;
	PersonOperationService serv_cp;
	
	
	public Client(){
		test_eval();
		//test_coau();
		//test_cp();	
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
		Person p = new Person();
		p.setFirst_name("This is a test");
		serv_cp = new PersonOperationServiceLocator();
		try {
			stub_cp = serv_cp.getCRUDPerson();
			System.out.println(stub_cp.deletePerson(p));			
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
