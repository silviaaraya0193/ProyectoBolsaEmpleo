/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;


/**
 *
 * @author EXDER 
 */
public class SecuredEstudiante extends Security.Authenticator {
   @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("correo");
    }
    
     @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect(routes.ApplicationEstudiante.homeEst());
    }  
}
