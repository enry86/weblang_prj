package weblanguages.project.client;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import groupeval.stub.*;

public class Client {
	GroupEval stub;
	GroupEvalService serv;
	
	
	public Client(){
		double res=0;
		String[] strs=new String[2];
		strs[0]="ciao";
		strs[1]="miao";
		serv=new GroupEvalServiceLocator();
		try {
			stub=serv.getGroupEval();
			res = stub.getAuthorsRank(strs);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
	}
	
	public static void main(String[] args){
		Client cli = new Client();
	}
}
