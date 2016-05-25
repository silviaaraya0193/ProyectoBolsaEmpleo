/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import javax.persistence.*;
import javax.swing.JCheckBox;
import play.data.validation.*;
import play.data.validation.Constraints.*;

/**
 *
 * @author viccr
 */
@Entity
public class FormularioEmpresa extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Constraints.Required
    public String nombre;
    //@Constraints.Required
    public String codigoCFI;
    @Constraints.Required
    public String direccion;
    @Constraints.Required
    public String telefonoContacto;
    @Email
    @Constraints.Required
    public String correoEmpresa;
    @Constraints.Required
    public String perfilEmpresarial;
    @Constraints.Required
    public String estadoContrataciones;
    public String otrasContrataciones;
    
     public static Finder<Long, FormularioEmpresa> find = new Finder<Long, FormularioEmpresa>(FormularioEmpresa.class);
}
