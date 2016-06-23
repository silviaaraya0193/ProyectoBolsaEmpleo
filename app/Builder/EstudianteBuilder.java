/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Builder;

import models.FormularioEstudiante;
import org.w3c.dom.Element;

/**
 *
 * @author viccr
 */
public class EstudianteBuilder implements InterfazBuilder{

    @Override
    public FormularioEstudiante construyeEstudiante(Object obj) {
        Element eElement = (Element) obj;
        FormularioEstudiante estudiante = new FormularioEstudiante(
                eElement.getElementsByTagName("fechaNacimiento").item(0).getTextContent(),
                eElement.getElementsByTagName("nombre").item(0).getTextContent(),
                eElement.getElementsByTagName("primerApellido").item(0).getTextContent(),
                eElement.getElementsByTagName("segundoApellido").item(0).getTextContent(),
                eElement.getElementsByTagName("cedula").item(0).getTextContent(),
                eElement.getElementsByTagName("correo").item(0).getTextContent(),
                eElement.getElementsByTagName("estadoCivil").item(0).getTextContent(),
                eElement.getElementsByTagName("paisNacimiento").item(0).getTextContent(),
                eElement.getElementsByTagName("lugarResidencia").item(0).getTextContent(),
                eElement.getElementsByTagName("direccion").item(0).getTextContent(),
                eElement.getElementsByTagName("telefonoMovil").item(0).getTextContent(),
                eElement.getElementsByTagName("licencia").item(0).getTextContent(),
                eElement.getElementsByTagName("perfilPersonal").item(0).getTextContent(),
                eElement.getElementsByTagName("titulo").item(0).getTextContent(),
                eElement.getElementsByTagName("institucion").item(0).getTextContent(),
                eElement.getElementsByTagName("estadoLaboral").item(0).getTextContent(),
                eElement.getElementsByTagName("anoIngresoFormal").item(0).getTextContent(),
                eElement.getElementsByTagName("anoFinalFormal").item(0).getTextContent(),
                eElement.getElementsByTagName("traslado").item(0).getTextContent(),
                eElement.getElementsByTagName("genero").item(0).getTextContent());
                
        return estudiante;
    }
    
}
