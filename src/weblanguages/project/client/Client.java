package weblanguages.project.client;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import coauthors.stub.*;
import groupeval.stub.*;

public class Client {
	GroupEval stub_e;
	GroupEvalService serv_e;
	
	DblpCoauthors stub_d;
	DblpCoauthorsService serv_d;
	
	
	public Client(){
		//test_eval();
		test_coau();
	}
	
	private void test_eval(){
		double res=0;
		String[] strs=new String[2];
		strs[0]="ciao";
		strs[1]="miao";
		serv_e=new GroupEvalServiceLocator();
		try {
			stub_e=serv_e.getGroupEval();
			res = stub_e.getAuthorsRank(strs);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println(res);
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
	
	
	public static void main(String[] args){
		new Client();
	}
}
