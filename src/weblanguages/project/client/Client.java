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
		test_eval();
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
		String[] res;
		String[] strs=new String[2];
		strs[0]="ciao";
		strs[1]="miao";
		serv_d=new DblpCoauthorsServiceLocator();
		try {
			stub_d=serv_d.getDblpCoauthors();
			res = stub_d.getDblpCoauthors(strs);
			for (int k=0;k<res.length;k++){
				System.out.println(res[k]);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		Client cli = new Client();
	}
}
