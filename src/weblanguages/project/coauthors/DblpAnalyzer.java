package weblanguages.project.coauthors;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class DblpAnalyzer extends DefaultHandler {
	private SAXParserFactory spf;
	private SAXParser sax;
	private boolean find;
	private String tmpstr=""; 
	private HashSet<String> coauth;
	private HashSet<String> coauth_tmp;
	private String author;
	private boolean auth;
	private int count=0;
	private ArrayList<String> fields;
	
	public DblpAnalyzer(){
		find=false;
		auth=false;
		coauth=new HashSet<String>();
		coauth_tmp=new HashSet<String>();
		spf=SAXParserFactory.newInstance();
		fields=generateFields();
		try {
			sax=spf.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}		
	}
	
	public String[] getCoauthors(String auth){
		author=auth;
		try {
			sax.parse("dblp.xml",this);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(coauth.size());
		String[] res=new String[coauth.size()];
		Iterator<String> i=coauth.iterator();
		int k =0;
		while (i.hasNext()){
			res[k]=i.next();
			k++;
		}
		return res;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if(qName.compareTo("author")==0) auth=true;
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.compareTo("author")==0){
			if (tmpstr.compareToIgnoreCase(author)==0){
				find=true;
			}
			else coauth_tmp.add(tmpstr);
			tmpstr="";
			auth=false;
		}

		else if(fields.contains(qName)){
			if(find==true){
				coauth.addAll(coauth_tmp);
			}
			find=false;
			coauth_tmp.clear();
			tmpstr="";
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (auth) {
			tmpstr=new String(ch,start,length);
		}
	}
	
	
	private ArrayList<String> generateFields(){
		ArrayList<String> res=new ArrayList<String>();
		res.add("article");
		res.add("inproceedings");
		res.add("proceedings");
		res.add("book");
		res.add("incollection");
		res.add("phdthesis");
		res.add("masterthesis");
		res.add("www");
		return res;
	}
	
	
	//**TO BE DELETED**//
	public static void main(String[] args){
		DblpAnalyzer d=new DblpAnalyzer();
		
		String[] ca=d.getCoauthors("Fabio Casati");
//		for(int k=0;k<ca.length;k++){
//			System.out.println(ca[k]);
//		}
	}
}
