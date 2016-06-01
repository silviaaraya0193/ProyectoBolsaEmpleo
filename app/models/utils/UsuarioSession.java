/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utils;

import controllers.Secured;
import models.RegistroUsuario;
import play.mvc.Security;
import play.mvc.*;
/**
 *
 * @author viccr
 */
//@Security.Authenticated(Secured.class)
public class UsuarioSession extends Controller {
    public RegistroUsuario getRegistroUsuario(){
         String username = ctx().session().get("correo");
         return RegistroUsuario.findByUsername(username);
    }
}
