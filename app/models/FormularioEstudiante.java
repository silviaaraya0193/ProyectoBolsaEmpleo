/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import javax.persistence.*;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import play.data.validation.*;
import play.data.validation.Constraints.*;

/**
 *
 * @author viccr
 */
@Entity
public class FormularioEstudiante extends Model{
    
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
     //DATOS PERSONALES
     @Constraints.Required
     public String fechaNacimiento;
    @Constraints.Required
    public String nombre;
    @Constraints.Required
    public String primerApellido;
    @Constraints.Required
    public String segundoApellido;
    @Constraints.Required
    public String cedula;
    @Email
    @Constraints.Required
    public String correo;
    @Constraints.Required
    public String estadoCivil;
    @Constraints.Required
    public String paisNacimiento;
    @Constraints.Required
    public String lugarResidencia;
    @Constraints.Required
    public String direccion;
    //@Constraints.Required
    public String telefonoCasa;
    @Constraints.Required
    public String telefonoMovil;
    //@Constraints.Required
    public String licencia;
    //DATOS LABORALES
    //@Constraints.Required
    public String profesion;
    @Constraints.Required
    public String perfilProfesional;
    @Constraints.Required
    public String anosExperiencia;
    //EXPERIENCIA LABORAL
   // @Constraints.Required
    public String empresa;
   // @Constraints.Required
    public String puesto;
    public String anosTrabajo;
    //EDUCACION FORMAL
    @Constraints.Required
    public String titulo;
    @Constraints.Required
    public String institucion;
    //IDIOMAS
    //@Constraints.Required
    public String idiomas;
    //EDUCACION NO FORMAL
    @Constraints.Required
    public String tituloNoFormal;
    @Constraints.Required
    public String institucionNoFormal;
    
    public JComboBox estadoLaboral, anoIngreso, anoFinal;
    
    public JRadioButton traslado;
    
      public static Finder<Long, FormularioEstudiante> find = new Finder<Long, FormularioEstudiante>(FormularioEstudiante.class);
      
}
