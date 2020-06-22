/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author vandre
 */
public class ConfigXML {
    
    private static final Document doc = documentFactory();

    
    private static Document documentFactory() {
        Document document = null;
        try {
            File fXmlFile = new File("config/config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(fXmlFile);
            document.getDocumentElement().normalize();
        }
        catch(IOException | ParserConfigurationException | SAXException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo de configurações.");
        }
        
        return document;
    }
    
    public static String getSenha() {
        return doc.getElementsByTagName("senha").item(0).getTextContent().trim();
    }
    
    public static String getCaminhoBackupBD() {
        NodeList nList = doc.getElementsByTagName("caminhoBackupBD");
        return nList.item(0).getTextContent().trim();
    }
    
    
    
}
