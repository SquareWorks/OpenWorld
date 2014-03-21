package com.squareworks.openworld.research;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResearchTreeLoader {
	private static Map<String,Map<String,ResearchItem>> researchTree = new HashMap<String,Map<String,ResearchItem>>();
	
	public static void loadResearchTree(String pack, File tree) throws ParserConfigurationException, SAXException, IOException{
		researchTree.put(pack, new HashMap<String,ResearchItem>());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(tree);
		document.getDocumentElement().normalize();
		NodeList trees = document.getElementsByTagName("research-tree");
		for(int i = 0; i < trees.getLength(); i++){
			Element e = ((Element)trees.item(i));
			NodeList researchItems = e.getElementsByTagName("research-item");
			for(int k = 0; k < researchItems.getLength(); k++){
				
			}
		}
	}
}
