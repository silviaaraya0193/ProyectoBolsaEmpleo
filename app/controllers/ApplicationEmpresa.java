/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import models.RegistroEmpresa;
import models.utils.AppException;
import play.Logger;
import play.data.Form;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import static play.data.Form.form;



/**
 *
 * @author Expression EXDER 
 */
public class ApplicationEmpresa extends Controller {
    
 public Result GO_HOME = redirect(
            routes.ApplicationEmpresa.home()
    );

 public Result home() {//controlador del home o index
        String cfi = ctx().session().get("cfi");
        if (cfi!=null) {
            RegistroEmpresa user = RegistroEmpresa.findByUsername(cfi);//busca el cfi
           // System.out.println("user"+user);
            if (user != null) {
                return  ok(perfilEmpresa.render("Hola empresa",user));//redirect("/");
            } else {
                session().clear();
            }
        }
        return ok(iniciarSesionEmpresa.render("Error",form(Login.class)));
    }
    
    public static class Login {

        @Constraints.Required()
        public String cfi;
        @Constraints.Required()
        public String password;
        
         public String validate() {

            RegistroEmpresa user = null;
            try {
                user = RegistroEmpresa.authenticate(cfi, password);
            } catch (AppException e) {
                return Messages.get("error.technical");
            }
            if (user == null) {
                return Messages.get("Verifique la contrasenia o el CFI");
            }
            return null;
        }

    }
  
  public static class CreateUser {

    @Constraints.Required
    public String cfi;

    @Constraints.Required
    public String password;

    /**
     * Comprueba que los datos ingresados en un input dado no estén en blanco
     *
     * @param input el string con el contenido del input que se desea verificar
     * @return un booleano indicando si el input de entrada está vacío o no
     **/
    private boolean isBlank(String input) {
        return input == null || input.isEmpty() || input.trim().isEmpty();
    }
}
  
  public Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(iniciarSesionEmpresa.render("Error Autentificacion",loginForm));
        } else {
            session("cfi", loginForm.get().cfi);
            return GO_HOME;
        }
    }
  public Result logout() {
        session().clear();
        flash("success",  "Usted ha cerrado sesión correctamente");
        return GO_HOME;
    }
        
  }