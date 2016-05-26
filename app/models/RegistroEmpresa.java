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
 * @author EXDER
 */
@Entity
public class RegistroEmpresa extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Constraints.Required
    public String nombre;
     @Constraints.Required
    public String cfi;   
    @Email
    @Constraints.Required
    public String correo;
    @Constraints.Required
    public int telefono;
    @Constraints.Required
    public String contrasenia;

   
//    @Override
//    public String getNombre() {
//        return nombre;
//    }
//
//    @Override
//    public String getCorreo() {
//        return correo;
//    }
//
//    @Override
//    public int getTelefono() {
//        return telefono;
//    }
//
//    @Override
//    public String getContrasenia() {
//        return contrasenia;
//    }
//
//    @Override
//    public String getCFI() {
//        return CFI;
//    }
    
    public static Finder<Long, RegistroEmpresa> find = new Finder<Long, RegistroEmpresa>(RegistroEmpresa.class);
}
