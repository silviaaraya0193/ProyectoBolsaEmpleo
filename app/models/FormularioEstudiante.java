/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import javax.persistence.*;
import models.utils.Anotacion;
import play.data.validation.*;
import play.data.validation.Constraints.*;

/**
 *
 * @author Silvia
 */
@Entity
public class FormularioEstudiante extends Model implements java.io.Serializable{
    
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
     
     //DATOS PERSONALES
     @Anotacion
     @Constraints.Required
     public String fechaNacimiento;
     @Anotacion
    @Constraints.Required
    public String nombre;
     @Anotacion
    @Constraints.Required
    public String primerApellido;
     @Anotacion
    @Constraints.Required
    public String segundoApellido;
     @Anotacion
    @Constraints.Required
    public String cedula;
     @Anotacion
    @Email
    @Constraints.Required
    public String correo;
     @Anotacion
    @Constraints.Required
    public String estadoCivil;
     @Anotacion
    @Constraints.Required
    public String paisNacimiento;
     @Anotacion
    @Constraints.Required
    public String lugarResidencia;
     @Anotacion
    @Constraints.Required
    public String direccion;
    //@Constraints.Required
    public String telefonoCasa;
    @Anotacion
    @Constraints.Required
    public String telefonoMovil;
    //@Constraints.Required
    public String licencia;
    @Anotacion
    @Constraints.Required
    public String perfilPersonal;
    //DATOS LABORALES
    //@Constraints.Required
    @Anotacion(guardar_xml = false)
    public String profesion;
    //@Constraints.Required
    @Anotacion(guardar_xml = false)
    public String perfilProfesional;
   // @Constraints.Required
    @Anotacion(guardar_xml = false)
    public String anosExperiencia;
    //EXPERIENCIA LABORAL
   // @Constraints.Required
    @Anotacion(guardar_xml = false)
    public String empresa,empresa2,empresa3;
   // @Constraints.Required
    @Anotacion(guardar_xml = false)
    public String puesto,puesto2,puesto3;
    @Anotacion(guardar_xml = false)
    public String anosTrabajo, anosTrabajo3, anosTrabajo2;
    //EDUCACION FORMAL
    @Anotacion
    @Constraints.Required
    public String titulo;
    @Anotacion(guardar_xml = false)
    public String titulo2;
    @Anotacion
    @Constraints.Required
    public String institucion;
    @Anotacion(guardar_xml = false)
    public String institucion2;
    
    //IDIOMAS
    //@Constraints.Required
    @Anotacion(guardar_xml = false)
    public String idiomas;
    //EDUCACION NO FORMAL
   // @Constraints.Required
    @Anotacion(guardar_xml = false)
    public String otrosTitulos;
    @Anotacion(guardar_xml = false)
    public String otrosTitulosFormales;
    @Anotacion(guardar_xml = false)
    public String otrosTrabajos;
   // @Constraints.Required
    @Anotacion(guardar_xml = false)
    public String estadoLaboral;
    @Anotacion
    @Constraints.Required
    public String anoIngresoFormal, anoFinalFormal;
    @Anotacion(guardar_xml = false)
    public String anoIngresoFormal2, anoFinalFormal2;
    @Anotacion
    @Constraints.Required
    public String traslado;
    @Anotacion
    @Constraints.Required
    public String genero;
    
    @Anotacion(guardar_xml = false)
    @Constraints.Required
    @OneToOne()
    RegistroUsuario registroUsuario;

    public FormularioEstudiante() {
       
    }

    public RegistroUsuario getRegistroUsuario() {
        return registroUsuario;
    }

    public void setRegistroUsuario(RegistroUsuario registroUsuario) {
        this.registroUsuario = registroUsuario;
    }
    
      public static Finder<Long, FormularioEstudiante> find = new Finder<Long, FormularioEstudiante>(FormularioEstudiante.class);

    public FormularioEstudiante(String fechaNacimiento, String nombre, String primerApellido, String segundoApellido, String cedula, String correo, String estadoCivil, String paisNacimiento, String lugarResidencia, String direccion, String telefonoMovil, String licencia, String perfilPersonal, String titulo, String institucion, String estadoLaboral, String anoIngresoFormal, String anoFinalFormal, String traslado, String genero) {
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.cedula = cedula;
        this.correo = correo;
        this.estadoCivil = estadoCivil;
        this.paisNacimiento = paisNacimiento;
        this.lugarResidencia = lugarResidencia;
        this.direccion = direccion;
        this.telefonoMovil = telefonoMovil;
        this.licencia = licencia;
        this.perfilPersonal = perfilPersonal;
        this.titulo = titulo;
        this.institucion = institucion;
        this.estadoLaboral = estadoLaboral;
        this.anoIngresoFormal = anoIngresoFormal;
        this.anoFinalFormal = anoFinalFormal;
        this.traslado = traslado;
        this.genero = genero;
    }
      
}
