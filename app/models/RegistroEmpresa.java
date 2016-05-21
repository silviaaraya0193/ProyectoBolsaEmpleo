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
 * @author Expression EXDER is undefined on line 12, column 14 in Templates/Classes/Class.java.
 */
public class RegistroEmpresa implements InterfaceCreacionUsuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Constraints.Required
    String nombre,correo,contrasenia;
    @Constraints.Required
    int telefono,cfi;

    public RegistroEmpresa(String nombre, String correo, String contrasenia, int telefono,int CFI) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.cfi=CFI;
    }

    public RegistroEmpresa() {
    }

    public void setCfi(int cfi) {
        this.cfi = cfi;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getCorreo() {
        return correo;
    }

    @Override
    public int getTelefono() {
        return telefono;
    }

    @Override
    public String getContrasenia() {
        return contrasenia;
    }

    @Override
    public int getCFI() {
        return cfi;
    }

}
