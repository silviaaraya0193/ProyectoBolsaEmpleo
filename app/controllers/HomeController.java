package controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public Result opciones(){
        return ok(opciones.render(" "));
    }
    public Result opciones2(){
        return ok(opciones2.render(" "));
    }
    public Result iniciarSesionEstudiante(){
        return ok(iniciarSesionEstudiante.render(""));
    }
    public Result iniciarSesionEmpresa(){
        return ok(iniciarSesionEmpresa.render(""));
    }
     public Result registroEstudiante(){
        return ok(registroEstudiante.render(""));
    }
      public Result registroEmpresa(){
        return ok(registroEmpresa.render(""));
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
        return ok(formularioEmpresa.render("Recepcion de formulario correcto.", pregForm,
                routes.HomeController.crearFormularioEmpresaPost()));
    }
    public Result crearFormularioEstudianteGet() {
        Form<FormularioEstudiante> pregForm = formFactory.form(FormularioEstudiante.class);
        List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
        
        return ok(formularioEstudiante.render(" ",
                pregForm, anios, routes.HomeController.crearFormularioEstudiantePost()));
    }

    public Result crearFormularioEstudiantePost() {
        List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
        Form<FormularioEstudiante> formEst = formFactory.form(FormularioEstudiante.class).bindFromRequest();
        if (formEst.hasErrors()) {
            //System.out.println("primero: "+pregForm);
            return badRequest(formularioEstudiante.render("Encontramos errores",
                    formEst,anios, routes.HomeController.index()));
        } else {
            Map<String,String> values = formEst.data();
            
            FormularioEstudiante nuevoFormEst = new FormularioEstudiante();
            nuevoFormEst.nombre = values.get("nombre");
            nuevoFormEst.primerApellido = values.get("primerApellido");
            nuevoFormEst.segundoApellido = values.get("segundoApellido");
            nuevoFormEst.fechaNacimiento = values.get("fechaNacimiento");
            nuevoFormEst.cedula = values.get("cedula");
            nuevoFormEst.correo = values.get("correo");
            nuevoFormEst.estadoCivil = values.get("estadoCivil");
            nuevoFormEst.paisNacimiento = values.get("paisNacimiento");
            nuevoFormEst.lugarResidencia = values.get("lugarResidencia");
            nuevoFormEst.direccion = values.get("direccion");
            nuevoFormEst.telefonoCasa = values.get("telefonoCasa");
            nuevoFormEst.telefonoMovil = values.get("telefonoMovil");
            nuevoFormEst.licencia = values.get("licencia");
            nuevoFormEst.profesion = values.get("profesion");
            nuevoFormEst.perfilProfesional = values.get("perfilProfesional");
            nuevoFormEst.anosExperiencia = values.get("anosExperiencia");
            nuevoFormEst.empresa = values.get("empresa");
            nuevoFormEst.puesto = values.get("puesto");
            nuevoFormEst.anosTrabajo = values.get("anosTrabajo");
            nuevoFormEst.titulo = values.get("titulo");
            nuevoFormEst.institucion = values.get("institucion");
            nuevoFormEst.idiomas = values.get("idiomas");
            nuevoFormEst.otrosTitulos = values.get("otrosTitulos");
            nuevoFormEst.estadoLaboral = values.get("estadoLaboral");
            nuevoFormEst.anoIngresoFormal = values.get("anoIngresoFormal");
            nuevoFormEst.anoFinalFormal = values.get("anoFinalFormal");
            nuevoFormEst.traslado = values.get("traslado");
            nuevoFormEst.genero = values.get("genero");
            //System.out.println("informacion: "+preg);
            nuevoFormEst.save();
            formEst = formFactory.form(FormularioEstudiante.class);
        }
        return ok(formularioEstudiante.render("Recepcion de formulario correcto.", formEst,
                anios,
                routes.HomeController.crearFormularioEstudiantePost()));
    }
     public Result perfilEmpresa(){
        return ok(perfilEmpresa.render("Perfil Empresa"));
    }
     public Result perfilEstudiante(){
        return ok(perfilEstudiante.render("Pefil Estudiante"));
    }

}