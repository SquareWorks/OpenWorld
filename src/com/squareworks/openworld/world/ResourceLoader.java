package com.squareworks.openworld.world;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.squareworks.openworld.client.GraphicsLoader;

public class ResourceLoader {
	private static Map<Resource, Integer> resources = new HashMap<Resource, Integer>();

	public static void loadResources(File resourcesFile, String pack) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(resourcesFile);
		document.getDocumentElement().normalize();
		NodeList nodes = document.getElementsByTagName("resource");
		for(int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element)node;
				Resource resource = new Resource(element.getAttribute("name"));
				NodeList graphics = element.getElementsByTagName("graphic");
				for(int k = 0; k < graphics.getLength(); k++){
					Element graphic = (Element) graphics.item(k);
					String[] graphicReference = graphic.getAttribute("ref").split(":");
					resource.addGraphic(graphic.getAttribute("use"), GraphicsLoader.getResource(graphicReference[0], graphicReference[1]));
				}
				resources.put(resource, 0);
			}
		}
	}
	
	public static Map<Resource, Integer> getBlankResources(){
		return new HashMap<Resource, Integer>(resources);
	}
}
