/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;
import com.avaje.ebean.Model;
import java.util.Date;
import javax.persistence.*;
import models.utils.AppException;
import models.utils.Hash;
import play.data.validation.*;
import play.data.validation.Constraints.*;
import play.data.format.Formats;
/**
 *
 * @author Exder
 */
@Entity
public class RegistroUsuario extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
     @Constraints.Required
    public String nombre;
     @Email
    @Constraints.Required
    public String correo;
    @Constraints.Required
    public String contrasenia;
    @Constraints.Required
    public int telefono;
    @Formats.NonEmpty
    public String passwordHash;
    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date creationDate;

    public RegistroUsuario(String passwordHash, Date creationDate) {
        this.passwordHash = passwordHash;
        this.creationDate = creationDate;
    }
    public RegistroUsuario() {
        
    }
     public static Finder<Long, RegistroUsuario> find = new Finder<Long, RegistroUsuario>(RegistroUsuario.class);
    
     public static RegistroUsuario findByUsername(String correo) {
         System.out.println("find correo "+correo);
        return find.where().eq("correo", correo).findUnique();
    }
     
     /**
     * Autentica usuarios utilizando el nombre de usuario y la contraseña sin encriptar
     *
     * @param username nombre de usuario
     * @param password contraseña sin encriptar
     * @return un usuario si se autentica correctamente, null en el caso contrario
     * @throws AppException en caso de error
     */
    public static RegistroUsuario authenticate(String correo, String password) throws AppException {
        System.out.println("Correo "+correo);
        System.out.println("pass usuario"+password);
        RegistroUsuario userEstudiante = find.where().eq("correo", correo).findUnique();
        System.out.println("aqui algo paso "+userEstudiante);
        if (userEstudiante != null) {
            if (Hash.checkPassword(password, userEstudiante.passwordHash)) {
                return userEstudiante;
            }
        }
        return null;
    }
}
