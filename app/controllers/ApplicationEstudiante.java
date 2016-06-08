package controllers;
import models.RegistroUsuario;
import models.utils.AppException;
import play.data.Form;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.data.validation.Constraints.*;
import static play.data.Form.form;
import it.innove.PdfGenerator;



/**
 *
 * @author Expression EXDER 
 */
public class ApplicationEstudiante extends Controller {
    @Inject 
     PdfGenerator pdfGenerator;
       public Result GO_HOME = redirect(
            routes.ApplicationEstudiante.homeEst()
    );
       
 
public Result homeEst() {//controlador del home o index
 
        String correo = ctx().session().get("correo");
        if (correo!=null) {
            RegistroUsuario user = RegistroUsuario.findByUsername(correo);//busca el coreo
           // System.out.println("user"+user);
            if (user != null) {
                return redirect(routes.ControllerEstudiante.listarFormularioEstudiante());
                //linea 34 error de anios silvia ya lo arreglo :v
            } else {
                session().clear();
            }
        }
        return ok(iniciarSesionEstudiante.render("Error",form(ApplicationEstudiante.Login.class)));
    }
     
public static class Login {
        @Email
        @Constraints.Required()
        public String correo;
        @Constraints.Required()
        public String contrasenia;
        
         public String validate() {

            RegistroUsuario user = null;
            try {
                user = RegistroUsuario.authenticate(correo, contrasenia);
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
    public String correo;

    @Constraints.Required
    public String contrasenia;

    private boolean isBlank(String input) {
        return input == null || input.isEmpty() || input.trim().isEmpty();
    }
}

     public Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(iniciarSesionEstudiante.render("Error Autentificacion",loginForm));
        } else {
            session("correo", loginForm.get().correo);
            return GO_HOME;
        }
    }
    public Result logout() {
        session().clear();
        flash("success",  "Usted ha cerrado sesion correctamente");
        return GO_HOME;
    }
     
}