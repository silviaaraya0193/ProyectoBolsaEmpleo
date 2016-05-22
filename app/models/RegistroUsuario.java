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
 * @author Exder
 */
@Entity
public class RegistroUsuario implements InterfaceCreacionUsuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
     @Constraints.Required
    String nombre;
    @Constraints.Required
    String correo;
    @Constraints.Required
    String contrasenia;
    @Constraints.Required
    int telefono;

    public RegistroUsuario(String nombre, String correo, String contrasenia, int telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
    }
    public RegistroUsuario() {
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
