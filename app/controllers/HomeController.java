package controllers;


import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import models.CreadorUsuario;
import play.data.Form;
import play.mvc.*;
import models.FormularioEmpresa;
import models.FormularioEstudiante;
import models.InterfaceCreacionUsuario;
import models.RegistroEmpresa;
import play.data.FormFactory;
import play.data.validation.Constraints;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    @Inject FormFactory formFactory;
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

        return ok(iniciarSesionEstudiante.render("\n Iniciar Seccion Estudiante "));
        
    }
    public Result iniciarSesionEmpresa(){
        return ok(iniciarSesionEmpresa.render("\n Inicar Seccion Empresa "));
        
    }
     public Result registroEstudiante(){
        return ok(registroEstudiante.render(" "));
      
    }
      public Result registroEmpresaGet(){
          Form<RegistroEmpresa> pregForm = formFactory.form(RegistroEmpresa.class);
        return ok(registroEmpresa.render(" Registro Empresa",pregForm,routes.HomeController.registroEmpresaPost()));
        
    }
      public Result registroEmpresaPost(){
          Form<RegistroEmpresa> formRegistro=formFactory.form(RegistroEmpresa.class).bindFromRequest();
          if(formRegistro.hasErrors()){
              System.out.println("error");
              return badRequest(registroEmpresa.render("Encontramos errores",
                    formRegistro, routes.HomeController.registroEmpresaPost()));
          }
          else{
            Map<String ,String> values=formRegistro.data();//optiene los datos como un map del registro Empresaa
            RegistroEmpresa nuevaEmpresa= new RegistroEmpresa();
              System.out.println(values);
            nuevaEmpresa.nombre=values.get("nombre");
            nuevaEmpresa.cfi=values.get("cfi");
            nuevaEmpresa.correo= values.get("correo");
            nuevaEmpresa.telefono= Integer.parseInt(values.get("telefono"));
            nuevaEmpresa.contrasenia= values.get("contrasenia");
            
            nuevaEmpresa.save();
            
            
            formRegistro=formFactory.form(RegistroEmpresa.class);
          }
          return ok(registroEmpresa.render("Recepción de registro correcto.", formRegistro,
                routes.HomeController.registroEmpresaPost()));
      }
     
      
      
    public Result crearFormularioEmpresaGet() {
        Form<FormularioEmpresa> pregForm = formFactory.form(FormularioEmpresa.class);
        return ok(formularioEmpresa.render(" ",
                pregForm, routes.HomeController.crearFormularioEmpresaPost()));
    }
    public Result crearFormularioEmpresaPost() {//creacion del formulario sin errores
        //verificacion y recepciones de requierds and data view//
        //datas- que son los datos de la vista, values obtenidos por la key q se obtiene de el preform que fuarda la vista
        Form<FormularioEmpresa> formEmpresa = formFactory.form(FormularioEmpresa.class).bindFromRequest();//captura los datos de la vista
        if (formEmpresa.hasErrors()) {
            
            return badRequest(formularioEmpresa.render("Encontramos errores",
                    formEmpresa, routes.HomeController.crearFormularioEmpresaPost()));
        } else {
            Map<String ,String> values=formEmpresa.data();//optiene los datos como un map
            System.out.println(values);
            FormularioEmpresa nuevoFormEmpresa= new FormularioEmpresa();            
            nuevoFormEmpresa.nombre=values.get("nombre");
            nuevoFormEmpresa.direccion=values.get("direccion");
            nuevoFormEmpresa.telefonoContacto=values.get("telefonoContacto");
            nuevoFormEmpresa.correoEmpresa=values.get("correoEmpresa");
            nuevoFormEmpresa.perfilEmpresarial=values.get("perfilEmpresarial");//
            nuevoFormEmpresa.estadoContrataciones=Integer.parseInt(values.get("estadoContrataciones"));
            nuevoFormEmpresa.otrasContrataciones=values.get("otrasContrataciones");
            nuevoFormEmpresa.save();
            
            formEmpresa = formFactory.form(FormularioEmpresa.class);
        }
        return ok(formularioEmpresa.render("Recepción de formulario correcto.", formEmpresa,
                routes.HomeController.crearFormularioEmpresaPost()));
    }
    
    public Result crearFormularioEstudianteGet() {//muestrar la pantalla el post hace la operacio
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
            //crear el estudiante formulario
            preg.save();
            pregForm = formFactory.form(FormularioEstudiante.class);
        }
        return ok(formularioEstudiante.render("Recepción de formulario correcto.", pregForm,
                routes.HomeController.crearFormularioEstudiantePost()));
    }
     public Result perfilEmpresa(){
        return ok(perfilEmpresa.render("Perfil Empresa"));
    }
     public Result perfilEstudiante(){
        return ok(perfilEstudiante.render("Pefil Estudiante"));
    }
}