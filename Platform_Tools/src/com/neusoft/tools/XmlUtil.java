package com.neusoft.tools;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



public class XmlUtil {

	private static Logger log = Logger.getLogger(XmlUtil.class);

	
	public static Document getDocument(String filePath) {
		File file = new File(filePath);
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(file);
			return document;
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	
	public static String formate(String xml) {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			StringWriter writer = new StringWriter();
			XMLWriter output = new XMLWriter(writer, format);
			output.write(document);
			output.close();
			return writer.getBuffer().toString();
		} catch (Exception ex) {
			return xml;
		}

	}
	
	public static void main(String [] arges){
		String path = "e:/function.xml";
		Document doc = getDocument(path);
		for(Iterator<Element> it=doc.getRootElement().elementIterator();it.hasNext();){
			Element el=it.next();
			
			System.out.println(el.attributeValue("sequence"));
			for(Iterator<Element> it2=el.elementIterator();it2.hasNext();){
				Element el2=it2.next();
				//System.out.println(el2.attributeValue("code"));
				System.out.println(el2.attributeValue("sequence"));
			}
		}
	}
}
