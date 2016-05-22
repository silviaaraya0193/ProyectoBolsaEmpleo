package controllers;


import javax.inject.Inject;
import models.FormularioEmpresa;
import models.FormularioEstudiante;
import play.data.Form;
import play.mvc.*;
import play.data.FormFactory;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    @Inject
    FormFactory formFactory;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        
        return ok(index.render("")); 
                
    } 
    public Result iniciarSesionEstudiante(){
        return ok(iniciarSesionEstudiante.render("\n Iniciar Seccion Estudiante "));
    }
    public Result iniciarSesionEmpresa(){
        return ok(iniciarSesionEmpresa.render("\n Inicar Seccion Empresa "));
    }
     public Result registroEstudiante(){
        return ok(registroEstudiante.render("Registro estudiante "));
    }
      public Result registroEmpresa(){
        return ok(registroEmpresa.render("Registro empresa "));
    }
    public Result crearFormularioEmpresaGet() {
        Form<FormularioEmpresa> pregForm = formFactory.form(FormularioEmpresa.class);
        return ok(formularioEmpresa.render(" ",
                pregForm, routes.HomeController.crearFormularioEmpresaPost()));
    }


    public Result crearFormularioEmpresaPost() {
        Form<FormularioEmpresa> pregForm = formFactory.form(FormularioEmpresa.class).bindFromRequest();
        if (pregForm.hasErrors()) {
            return badRequest(formularioEmpresa.render("Encontramos errores",
                    pregForm, routes.HomeController.index()));
        } else {
            FormularioEmpresa preg = pregForm.get();
            preg.save();
            pregForm = formFactory.form(FormularioEmpresa.class);
        }
        return ok(formularioEmpresa.render("Recepción de formulario correcto.", pregForm,
                routes.HomeController.crearFormularioEmpresaPost()));
    }

    public Result crearFormularioEstudianteGet() {
        Form<FormularioEstudiante> pregForm = formFactory.form(FormularioEstudiante.class);
        return ok(formularioEstudiante.render(" ",
                pregForm, routes.HomeController.crearFormularioEstudiantePost()));
    }

    public Result crearFormularioEstudiantePost() {
        Form<FormularioEstudiante> pregForm = formFactory.form(FormularioEstudiante.class).bindFromRequest();
        if (pregForm.hasErrors()) {
            return badRequest(formularioEstudiante.render("Encontramos errores",
                    pregForm, routes.HomeController.index()));
        } else {
            FormularioEstudiante preg = pregForm.get();
            preg.save();
            pregForm = formFactory.form(FormularioEstudiante.class);
        }
        return ok(formularioEstudiante.render("Recepcion de formulario correcto.", pregForm,
                routes.HomeController.crearFormularioEstudiantePost()));
    }
}