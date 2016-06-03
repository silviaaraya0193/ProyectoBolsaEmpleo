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
    @Constraints.Required
    public String perfilPersonal;
    //DATOS LABORALES
    //@Constraints.Required
    public String profesion;
    //@Constraints.Required
    public String perfilProfesional;
   // @Constraints.Required
    public String anosExperiencia;
    //EXPERIENCIA LABORAL
   // @Constraints.Required
    public String empresa,empresa2,empresa3;
   // @Constraints.Required
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
    //@Constraints.Required
    public String idiomas;
    //EDUCACION NO FORMAL
   // @Constraints.Required
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

    public RegistroUsuario getRegistroUsuario() {
        return registroUsuario;
    }

    public void setRegistroUsuario(RegistroUsuario registroUsuario) {
        this.registroUsuario = registroUsuario;
    }
    
      public static Finder<Long, FormularioEstudiante> find = new Finder<Long, FormularioEstudiante>(FormularioEstudiante.class);
      
}
