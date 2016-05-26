/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;
import models.utils.AppException;
import models.utils.Hash;
import play.data.format.Formats;
import play.data.validation.Constraints;
import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

import play.Logger;
/**
 *
 * @author Expression EXDER is undefined on line 12, column 14 in Templates/Classes/Class.java.
 */
@Entity
public class UserEmpresa extends Model{
     @Id
    public Long id;
     
     @Constraints.Required
    @Formats.NonEmpty
    @Column(unique = true)
    public String CFI;
     
    @Constraints.Required
    @Formats.NonEmpty
    public String passwordHash;

    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date creationDate;
    
    // -- querys (long id, user.class)
    public static Model.Finder<Long, UserEmpresa> find = new Model.Finder<Long, UserEmpresa>(Long.class, UserEmpresa.class);
    
     public static UserEmpresa findByUsername(String CFI) {
        return find.where().eq("CFI", CFI).findUnique();
    }
     
     /**
     * Autentica usuarios utilizando el nombre de usuario y la contraseña sin encriptar
     *
     * @param username nombre de usuario
     * @param password contraseña sin encriptar
     * @return un usuario si se autentica correctamente, null en el caso contrario
     * @throws AppException en caso de error
     */
    public static UserEmpresa authenticate(String CFI, String password) throws AppException {
        UserEmpresa userEmpresa = find.where().eq("CFI", CFI).findUnique();
        if (userEmpresa != null) {
            if (Hash.checkPassword(password, userEmpresa.passwordHash)) {
                return userEmpresa;
            }
        }
        return null;
    }
}
