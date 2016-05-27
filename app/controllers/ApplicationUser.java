/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import models.RegistroEmpresa;
import models.UserEmpresa;
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
public class ApplicationUser extends Controller {
 public Result GO_HOME = redirect(
            routes.HomeController.registroEmpresaGet()
    );

 public Result home() {//controlador del home o index
        String CFI = ctx().session().get("cfi");
        if (CFI!=null) {
            UserEmpresa user = UserEmpresa.findByUsername(CFI);
            if (user != null) {
                return  ok(userhome.render(user));//redirect("/");
            } else {
                session().clear();
            }
        }
        return ok(home.render(form(Login.class)));
    }
    
    public static class Login {

        @Constraints.Required()
        public String cfi;
        @Constraints.Required()
        public String password;
        
         public String validate() {

            UserEmpresa user = null;
            try {
                user = UserEmpresa.authenticate(cfi, password);
            } catch (AppException e) {
                return Messages.get("error.technical");
            }
            if (user == null) {
                return Messages.get("invalid.CFI.or.password");
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
            return badRequest(home.render(loginForm));
        } else {
            //String variable=loginForm.get().CFI.toString();
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