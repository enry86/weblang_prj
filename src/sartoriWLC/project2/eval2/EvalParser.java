package sartoriWLC.project2.eval2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EvalParser extends DefaultHandler {
	private String base_url = "http://demo.liquidpub.org/reseval/resources/BasicStats/";
	private SAXParserFactory spf;
	private SAXParser sax;
	private StringBuffer chars;
	
	private Hashtable<String,String> tmp_res;
	private String metric;
	private boolean err;
	private boolean first;
	private ArrayList<String> labels;
	
	public EvalParser(){
		labels = new ArrayList<String>(4);
		err = true;
		first = true;
		spf = SAXParserFactory.newInstance();
		chars = new StringBuffer();
		labels.add("h-index");
		labels.add("g-index");
		labels.add("citationsCount");
		labels.add("numberOfPublications");
		try {
			sax = spf.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}		
	}
	
	public Hashtable<String,String> getEvaluation(String auth_url){
		tmp_res = new Hashtable<String,String>();
		first = true;
		err = true;
		try {
			sax.parse(base_url + auth_url, this);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (err) return null;
		return tmp_res;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		chars.setLength(0);
		if (first == true){
			first = false;
			if (qName.compareTo("error")==0) err = true;
			else err = false;
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if (qName.compareTo("name") == 0){
			String val = getChars();
			if (labels.contains(val)) metric = val;
			else metric = null;
		}
		else if (qName.compareTo("value") == 0){
			if (metric != null){
				String tmp = getChars();
				tmp_res.put(metric, tmp);
				metric = null;
			}
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		chars.append(ch,start,length);
	}
	
	private String getChars(){
		String res = chars.toString();
		chars.setLength(0);
		return res;
	}
}
