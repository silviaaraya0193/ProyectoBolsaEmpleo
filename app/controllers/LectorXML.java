/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author viccr
 */
public class LectorXML {
    Document doc = null;
    private void Obtengo_el_documento(String inputString) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
        // Creo un  DocumentBuilder
        DocumentBuilderFactory factory
                = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(inputString);
        ByteArrayInputStream input = new ByteArrayInputStream(
                xmlStringBuilder.toString().getBytes("UTF-8"));
        
        doc = builder.parse(input);
        
    }
    
    private NodeList busco_en_documento(String expresion) throws XPathExpressionException{
        XPath xPath =  XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(
                expresion).evaluate(
            doc, XPathConstants.NODESET);
        return nodeList;
    }
    
    
    private void _buscar(String expresion, ArrayList<String> campo) throws XPathExpressionException{
         NodeList nodeList = this.busco_en_documento(expresion);
         for (int i = 0; i < nodeList.getLength(); i++) {
             Node nNode = nodeList.item(i);
             System.out.println("\nElemento :" + nNode.getNodeName());
             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               for (int x=0; x<campo.size(); x++){
                   
                   System.out.println(
                           
                           campo.get(x)+" = "+
                    eElement.getElementsByTagName(campo.get(x)).item(0).getTextContent()+
                    eElement.getAttribute("class")
                            );
               }
               
             }
         }
    }
    
    
    public void buscar(String expresion, ArrayList<String> campo){
        try {
            this._buscar(expresion, campo);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(LectorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setText(String text){
        try {
            this.Obtengo_el_documento(text);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LectorXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(LectorXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LectorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
