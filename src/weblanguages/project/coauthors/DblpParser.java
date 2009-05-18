package weblanguages.project.coauthors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DblpParser extends DefaultHandler {
	private String filename;
	
	private ArrayList<String> authors;
	private HashSet<String>[] coauthors;
	private ArrayList<Integer> to_add;
	
	private SAXParserFactory spf;
	private SAXParser sax;
	private HashSet<String> coauth_tmp;
	private boolean auth;
	private ArrayList<String> fields;
	private StringBuffer chars; 
	
	
	public DblpParser(String file){
		auth = false;
		filename = file;
		coauth_tmp = new HashSet<String>();
		chars = new StringBuffer();
		spf = SAXParserFactory.newInstance();
		fields = generateFields();
		try {
			sax = spf.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}		
	}
	
	public HashSet<String>[] start_parsing(ArrayList<String> authors){
		to_add = new ArrayList(authors.size());
		coauthors = new HashSet[authors.size()];
		this.authors = authors;
		for (int i = 0; i < authors.size(); i++){
			coauthors[i] = new HashSet<String>();
		}
		try {
			sax.parse(filename, this);
		} catch (SAXException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		prune_coauthors();
		return coauthors;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if(qName.compareTo("author")==0) auth=true;
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException{
		String tmpstr;
		if(qName.compareTo("author")==0){
			tmpstr=getChars();
			if (authors.contains(tmpstr)){
				int key = authors.indexOf(tmpstr);
				to_add.add(key);
			}
			coauth_tmp.add(tmpstr);
			auth=false;
		}

		else if(fields.contains(qName)){
			if (to_add.size() > 0){
				Iterator<Integer> i = to_add.iterator();
				while (i.hasNext()){
					int k = i.next().intValue();
					coauthors[k].addAll(coauth_tmp);
				}
				to_add.clear();
			}
			coauth_tmp.clear();
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (auth) chars.append(ch,start,length);
	}
	
	private String getChars(){
		String res = chars.toString();
		chars.setLength(0);
		return res;
	}
	
	private void prune_coauthors(){
		for (int i = 0; i < authors.size(); i++){
			coauthors[i].remove(authors.get(i));
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
}
