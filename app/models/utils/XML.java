/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author viccr
 * **/
 
public class XML {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    DOMImplementation implementation;
    Document document;
    ArrayList titulos;
    ArrayList valores;
    Element raiz, principal;
    String arregloInformacion[];
    Source source;
    Result result;
    Result console;
    Transformer transformer;
    String nombreArchivo;
    
    public XML(){
        nombreArchivo = "Estudiante";
        if(cargarXML()){
            System.out.println("Este archivo ya existe y se puede utilizar");
        } else{
            try {
                crearXML();
            } catch (TransformerException ex) {
                System.out.println("error del try, catch del crearxml");
            }
            System.out.println("No existe el archivo, pero ya fue creado");
        }
        arregloInformacion = new String[41];
        titulos = new ArrayList();
        valores = new ArrayList();
    }
    
    public void crearXML() throws TransformerConfigurationException, TransformerException{
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "xml", null);
            document.setXmlVersion("1.0");
            source = new DOMSource(document);
            console = new StreamResult(System.out);
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            transformer.transform(source, console);
        } catch (ParserConfigurationException ex) {
            System.out.println("Error en crear el xml");
        }
        
    }
    public boolean cargarXML(){
        
            boolean cargo = false;
            try {
            File fXmlFile = new File(nombreArchivo + ".xml");
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.parse(fXmlFile);
            document.getDocumentElement().normalize();
            cargo = true;
            
            NodeList nList = document.getElementsByTagName("FormularioEstudiante");
            Node nNode = nList.item(0);
            raiz = (Element) nNode;
        } catch (Exception ex) {
                System.out.println(ex.toString());
        }
            return cargo;
    }
    public void guardarEnXML(String arregloInformacion[])
    {
        try {

            raiz = document.createElement("Estudiante");
            principal = document.createElement("Estudiante");
            document.getDocumentElement().appendChild(raiz);

            Element valor1 = document.createElement("cedula");
            Text text = document.createTextNode(arregloInformacion[0]);
            Element valor2 = document.createElement("nombre");
            Text text2 = document.createTextNode(arregloInformacion[1]);
            Element valor3 = document.createElement("direccion");
            Text text3 = document.createTextNode(arregloInformacion[2]);
            Element valor4 = document.createElement("fechaNacimiento");
            Text text4 = document.createTextNode(arregloInformacion[3]);
            Element valor5 = document.createElement("primerApellido");
            Text text5 = document.createTextNode(arregloInformacion[4]);
            Element valor6 = document.createElement("segundoApellido");
            Text text6 = document.createTextNode(arregloInformacion[5]);
            Element valor7 = document.createElement("correo");
            Text text7 = document.createTextNode(arregloInformacion[6]);
            Element valor8 = document.createElement("estadoCivil");
            Text text8 = document.createTextNode(arregloInformacion[7]);
            Element valor9 = document.createElement("paisNacimiento");
            Text text9 = document.createTextNode(arregloInformacion[8]);
            Element valor10 = document.createElement("lugarResidencia");
            Text text10 = document.createTextNode(arregloInformacion[9]);
//            Element valor11 = document.createElement("id");
//            Text text11 = document.createTextNode(arregloInformacion[10]);
            Element valor12 = document.createElement("telefonoCasa");
            Text text12 = document.createTextNode(arregloInformacion[10]);
            Element valor13 = document.createElement("telefonoMovil");
            Text text13 = document.createTextNode(arregloInformacion[11]);
            Element valor14 = document.createElement("licencia");
            Text text14 = document.createTextNode(arregloInformacion[12]);
            Element valor15 = document.createElement("perfilPersonal");
            Text text15 = document.createTextNode(arregloInformacion[13]);
            Element valor16 = document.createElement("profesion");
            Text text16 = document.createTextNode(arregloInformacion[14]);
            Element valor17 = document.createElement("perfilProfesional");
            Text text17 = document.createTextNode(arregloInformacion[15]);
            Element valor18 = document.createElement("anosExperiencia");
            Text text18 = document.createTextNode(arregloInformacion[16]);
            Element valor19 = document.createElement("empresa");
            Text text19 = document.createTextNode(arregloInformacion[17]);
            Element valor20 = document.createElement("empresa2");
            Text text20 = document.createTextNode(arregloInformacion[18]);
            Element valor21 = document.createElement("empresa3");
            Text text21 = document.createTextNode(arregloInformacion[19]);
             Element valor22 = document.createElement("puesto");
            Text text22 = document.createTextNode(arregloInformacion[20]);
             Element valor23 = document.createElement("puesto2");
            Text text23 = document.createTextNode(arregloInformacion[21]);
             Element valor24 = document.createElement("puesto3");
            Text text24 = document.createTextNode(arregloInformacion[22]);
//             Element valor25 = document.createElement("anosTrabajo");
//            Text text25 = document.createTextNode(arregloInformacion[24]);
             Element valor26 = document.createElement("anosTrabajo");
            Text text26 = document.createTextNode(arregloInformacion[23]);
             Element valor27 = document.createElement("anosTrabajo2");
            Text text27 = document.createTextNode(arregloInformacion[24]);
             Element valor28 = document.createElement("anosTrabajo3");
            Text text28 = document.createTextNode(arregloInformacion[25]);
             Element valor29 = document.createElement("titulo");
            Text text29 = document.createTextNode(arregloInformacion[26]);
             Element valor30 = document.createElement("titulo2");
            Text text30 = document.createTextNode(arregloInformacion[27]);
             Element valor31 = document.createElement("institucion");
            Text text31 = document.createTextNode(arregloInformacion[28]);
             Element valor32 = document.createElement("institucion2");
            Text text32 = document.createTextNode(arregloInformacion[29]);
             Element valor33 = document.createElement("idiomas");
            Text text33 = document.createTextNode(arregloInformacion[30]);
             Element valor34 = document.createElement("otrosTitulos");
            Text text34 = document.createTextNode(arregloInformacion[31]);
             Element valor35 = document.createElement("otrosTitulosFormales");
            Text text35 = document.createTextNode(arregloInformacion[32]);
             Element valor36 = document.createElement("otrosTrabajos");
            Text text36 = document.createTextNode(arregloInformacion[33]);
             Element valor37 = document.createElement("estadoLaboral");
            Text text37 = document.createTextNode(arregloInformacion[34]);
             Element valor38 = document.createElement("anoIngresoFormal");
            Text text38 = document.createTextNode(arregloInformacion[35]);
            Element valor39 = document.createElement("anoIngresoFormal2");
            Text text39 = document.createTextNode(arregloInformacion[36]);
            Element valor40 = document.createElement("anoFinalFormal");
            Text text40 = document.createTextNode(arregloInformacion[37]);
            Element valor41 = document.createElement("anoFinalFormal2");
            Text text41 = document.createTextNode(arregloInformacion[38]);
            Element valor42 = document.createElement("traslado");
            Text text42 = document.createTextNode(arregloInformacion[39]);
            Element valor43 = document.createElement("genero");
            Text text43 = document.createTextNode(arregloInformacion[40]);
//            Element valor44 = document.createElement("registroUsuario");
//            Text text44 = document.createTextNode(arregloInformacion[43]);
            
            raiz.appendChild(valor1);
            valor1.appendChild(text);
            raiz.appendChild(valor2);
            valor2.appendChild(text2);
            raiz.appendChild(valor3);
            valor3.appendChild(text3);
            raiz.appendChild(valor4);
            valor4.appendChild(text4);
            raiz.appendChild(valor5);
            valor5.appendChild(text5);
            raiz.appendChild(valor6);
            valor6.appendChild(text6);
            raiz.appendChild(valor7);
            valor7.appendChild(text7);
            raiz.appendChild(valor8);
            valor8.appendChild(text8);
            raiz.appendChild(valor9);
            valor9.appendChild(text9);
            raiz.appendChild(valor10);
            valor10.appendChild(text10);
//            raiz.appendChild(valor11);
//            valor11.appendChild(text11);
            raiz.appendChild(valor12);
            valor12.appendChild(text12);
            raiz.appendChild(valor13);
            valor13.appendChild(text13);
            raiz.appendChild(valor14);
            valor14.appendChild(text14);
            raiz.appendChild(valor15);
            valor15.appendChild(text15);
            raiz.appendChild(valor16);
            valor16.appendChild(text16);
            raiz.appendChild(valor17);
            valor17.appendChild(text17);
            raiz.appendChild(valor18);
            valor18.appendChild(text18);
            raiz.appendChild(valor19);
            valor19.appendChild(text19);
            raiz.appendChild(valor20);
            valor20.appendChild(text20);
            raiz.appendChild(valor21);
            valor21.appendChild(text21);
            raiz.appendChild(valor22);
            valor22.appendChild(text22);
            raiz.appendChild(valor23);
            valor23.appendChild(text23);
            raiz.appendChild(valor24);
            valor24.appendChild(text24);
//            raiz.appendChild(valor25);
//            valor25.appendChild(text25);
            raiz.appendChild(valor26);
            valor26.appendChild(text26);
            raiz.appendChild(valor27);
            valor27.appendChild(text27);
            raiz.appendChild(valor28);
            valor28.appendChild(text28);
            raiz.appendChild(valor29);
            valor29.appendChild(text29);
            raiz.appendChild(valor30);
            valor30.appendChild(text30);
            raiz.appendChild(valor31);
            valor31.appendChild(text31);
            raiz.appendChild(valor32);
            valor32.appendChild(text32);
            raiz.appendChild(valor33);
            valor33.appendChild(text33);
            raiz.appendChild(valor34);
            valor34.appendChild(text34);
            raiz.appendChild(valor35);
            valor35.appendChild(text35);
            raiz.appendChild(valor36);
            valor36.appendChild(text36);
            raiz.appendChild(valor37);
            valor37.appendChild(text37);
            raiz.appendChild(valor38);
            valor38.appendChild(text38);
            raiz.appendChild(valor39);
            valor39.appendChild(text39);
            raiz.appendChild(valor40);
            valor40.appendChild(text40);
            raiz.appendChild(valor41);
            valor41.appendChild(text41);
            raiz.appendChild(valor42);
            valor42.appendChild(text42);
            raiz.appendChild(valor43);
            valor43.appendChild(text43);
            
            result = new StreamResult(new java.io.File(nombreArchivo + ".xml"));
            console = new StreamResult(System.out);
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            transformer.transform(source, console);

        } catch (Exception e) {
            //System.err.println("Error al guardar: " + e);
        }
    }
    public void crearArchivo(String nombreArchivo) 
    {
        try{
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, nombreArchivo, null);
            document.setXmlVersion("1.0");
            raiz = document.getDocumentElement();
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File(nombreArchivo+".xml")); 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.toString());
        
        } catch (TransformerException ex) {
            System.out.println(ex.toString());
        }   
    }
}