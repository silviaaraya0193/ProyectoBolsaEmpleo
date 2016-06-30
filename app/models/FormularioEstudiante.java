/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import javax.persistence.*;
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
    
    @Constraints.Required
    public String perfilPersonal;
    //DATOS LABORALES
    public String profesion;
    public String perfilProfesional;
    public String anosExperiencia;
    //EXPERIENCIA LABORAL
    public String empresa,empresa2,empresa3;
    public String puesto,puesto2,puesto3;
    public String anosTrabajo, anosTrabajo3, anosTrabajo2;
    //EDUCACION FORMAL
    @Constraints.Required
    public String titulo;
    public String titulo2;
    @Constraints.Required
    public String institucion;
    public String institucion2;
    
    //IDIOMAS
    public String idiomas;
    //EDUCACION NO FORMAL
    public String otrosTitulos;
    public String otrosTitulosFormales;
    public String otrosTrabajos;
   // @Constraints.Required
    public String estadoLaboral;
    @Constraints.Required
    public String anoIngresoFormal, anoFinalFormal;
    public String anoIngresoFormal2, anoFinalFormal2;
    @Constraints.Required
    public String traslado;
    @Constraints.Required
    public String genero;
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
