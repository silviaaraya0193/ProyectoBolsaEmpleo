/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.util.List;
import models.FormularioEmpresa;
import models.RegistroEmpresa;
import models.utils.AppException;
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
     List<FormularioEmpresa> instancia = FormularioEmpresa.find.all();
        String cfi = ctx().session().get("cfi");
        System.out.println("cfi home "+ cfi);
        if (cfi!=null) {
            RegistroEmpresa user = RegistroEmpresa.findByUsername(cfi);//busca el cfi
            System.out.println("paso el cfi registro empresa");
           // System.out.println("user"+user);
            if (user != null) {
                return  redirect(routes.ControllerEmpresa.listarFormularioEmpresa());
            } else {
                session().clear();
            }
        }
        return ok(iniciarSesionEmpresa.render("Error",form(ApplicationEmpresa.Login.class)));
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
                return Messages.get("user es null");
            }
            return null;
        }

    }
  
  public static class CreateUser {

    @Constraints.Required
    public String cfi;

    @Constraints.Required
    public String contraseniaEmpresa;

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
            System.out.println("SESSION:   "+loginForm.get().cfi);
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