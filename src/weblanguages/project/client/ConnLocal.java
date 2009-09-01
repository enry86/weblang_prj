package weblanguages.project.client;

import javax.xml.rpc.ServiceException;

import similar.stub.*;
import groupeval.stub.*;
import coauthors.stub.*;

public class ConnLocal {
	public DblpCoauthors ca;
	public GroupEval ge;
	public AuthSimilar as;
	
	public ConnLocal(){
		DblpCoauthorsService ca_s = new DblpCoauthorsServiceLocator();
		GroupEvalService ge_s = new GroupEvalServiceLocator();
		AuthSimilarService as_s = new AuthSimilarServiceLocator();
		
		try {
			ca = ca_s.getDblpCoauthors();
			ge = ge_s.getGroupEval();
			as = as_s.getAuthSimilarity();
		} catch (ServiceException e) {
			ca = null;
			ge = null;
			as = null;
		}
	}
}
