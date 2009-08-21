package weblanguages.project.similar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimilParser extends DefaultHandler {
	
	private String a_auth;
	private String b_auth;
	
	private SAXParserFactory spf;
	private SAXParser sax;
	private StringBuffer chars;
	private String tmp;
	private String res;
	
	private ArrayList<String> fields;
	//private boolean a = false;
	//private boolean b = false;
	private boolean auth = false;
	private boolean field = false;
	
	private HashSet<String> authors;
	
	private int c_a = 0;
	private int c_ab = 0;
	private int c_b = 0;
	
	public SimilParser(String a, String b){
		a_auth = a;
		b_auth = b;
		fields = generateFields();
		authors = new HashSet<String>();
		chars = new StringBuffer();
		spf = SAXParserFactory.newInstance();
		try {
			sax = spf.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	public SimilarBean retrieve_vals(String filename){
		SimilarBean res = new SimilarBean();
		try {
			sax.parse(filename, this);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		res.setAb_similarity(((float) c_ab) / ((float) c_a));
		res.setBa_similarity(((float) c_ab) / ((float) c_b));
		res.setGeneral_similarity(((float) c_ab) / ((float)(c_a + c_b)));
		return res;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if (fields.contains(qName)) field = true;
		if (qName.compareTo("author") == 0) auth = true;
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if (auth && field){
			tmp = getChars();
			authors.add(tmp);
			//if (tmp.compareTo(a_auth) == 0) a = true;
			//else if (tmp.compareTo(b_auth) == 0) b = true;
			auth = false;
		}
		else if (field){
			boolean a = authors.contains(a_auth);
			boolean b = authors.contains(b_auth);
			if (a) c_a++;
			if (b) c_b++;
			if (a && b) c_ab++;
			a = false;
			b = false;
			field = false;
			authors.clear();
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (auth && field) chars.append(ch,start,length);
	}
	
	private String getChars(){
		res = null;
		res = chars.toString();
		chars.setLength(0);
		return res;
	}
	
	private ArrayList<String> generateFields(){
		ArrayList<String> res = new ArrayList<String>();
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
}
