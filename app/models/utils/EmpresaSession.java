/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utils;

import models.RegistroEmpresa;
import play.mvc.*;
/**
 *
 * @author viccr
 */
//@Security.Authenticated(Secured.class)
public class EmpresaSession extends Controller {
    public RegistroEmpresa getRegistroEmpresa(){
         String username = ctx().session().get("cfi");
         return RegistroEmpresa.findByUsername(username);
    }
    
}
