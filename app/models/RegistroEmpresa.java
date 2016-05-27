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
import play.data.format.Formats;
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
     @Formats.NonEmpty
    @Column(unique = true)
    public String cfi;   
    @Email
    @Constraints.Required
    public String correo;
    @Constraints.Required
    public int telefono;
    @Constraints.Required
    public String contrasenia;
    // @Constraints.Required
    @Formats.NonEmpty
    public String passwordHash;
@Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date creationDate;

    public RegistroEmpresa(String passwordHash,Date date) {
        this.passwordHash = passwordHash;
        this.creationDate=date;
    }

    public RegistroEmpresa() {
    }
    
     
     
     
    
     
   
    public static Finder<Long, RegistroEmpresa> find = new Finder<Long, RegistroEmpresa>(RegistroEmpresa.class);
    
     public static RegistroEmpresa findByUsername(String cfi) {
         System.out.println("find cfi "+cfi);
        return find.where().eq("cfi", cfi).findUnique();
    }
     
     /**
     * Autentica usuarios utilizando el nombre de usuario y la contraseña sin encriptar
     *
     * @param username nombre de usuario
     * @param password contraseña sin encriptar
     * @return un usuario si se autentica correctamente, null en el caso contrario
     * @throws AppException en caso de error
     */
    public static RegistroEmpresa authenticate(String cfi, String password) throws AppException {
        System.out.println("cfi "+cfi);
        System.out.println("pass"+password);
        RegistroEmpresa userEmpresa = find.where().eq("cfi", cfi).findUnique();
        System.out.println("aqui algo paso "+userEmpresa);
        if (userEmpresa != null) {
            if (Hash.checkPassword(password, userEmpresa.passwordHash)) {
                return userEmpresa;
            }
        }
        return null;
    }
}
