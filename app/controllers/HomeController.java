package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import play.data.Form;
import play.mvc.*;
import models.FormularioEmpresa;
import models.FormularioEstudiante;
import models.utils.Hash;
import models.RegistroEmpresa;
import models.RegistroUsuario;
import models.utils.AppException;
import play.data.FormFactory;
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
   
     public Result registroEstudianteGet(){
         Form<RegistroUsuario> formUsuario= formFactory.form(RegistroUsuario.class);
        return ok(registroEstudiante.render(" Registro Estudiante",formUsuario,routes.HomeController.registroEstudiantePost())); 
    }
    public Result registroEstudiantePost() throws AppException{
         Form<RegistroUsuario> formUsuario=formFactory.form(RegistroUsuario.class).fill(new RegistroUsuario("ABC123", new Date())).bindFromRequest();
          if(formUsuario.hasErrors()){
              //formRegistro.
              System.out.println("error form registro ");
              return badRequest(registroEstudiante.render("Encontramos errores en form registro",
                    formUsuario, routes.HomeController.registroEstudiantePost()));
          }
          else{
            Map<String ,String> values=formUsuario.data();//optiene los datos como un map del registro Empresaa
            RegistroUsuario nuevoUsuario= new RegistroUsuario();
            if(nuevoUsuario.findByUsername(values.get("correo"))==null){
            System.out.println(values);
            nuevoUsuario.nombre=values.get("nombre");
            nuevoUsuario.correo= values.get("correo");
            nuevoUsuario.telefono= Integer.parseInt(values.get("telefono"));
            nuevoUsuario.contrasenia= values.get("contrasenia");
            nuevoUsuario.passwordHash=Hash.createPassword(nuevoUsuario.contrasenia);
            nuevoUsuario.creationDate=new Date();
            nuevoUsuario.save();
            
            formUsuario=formFactory.form(RegistroUsuario.class);
            }
            else{
                 return ok(registroEstudiante.render("\n Correo ya existe.", formUsuario,
                routes.HomeController.registroEstudiantePost()));
            }
          }
          return ok(registroEstudiante.render("\nRecepción de registro correcto.", formUsuario,
                routes.HomeController.registroEstudiantePost()));
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
           if(nuevaEmpresa.findByUsername(values.get("cfi"))==null){
            System.out.println(values);

            nuevaEmpresa.nombre=values.get("nombre");
            nuevaEmpresa.cfi=values.get("cfi");
            nuevaEmpresa.correo= values.get("correo");
            nuevaEmpresa.telefono= Integer.parseInt(values.get("telefono"));
            nuevaEmpresa.contrasenia= values.get("contrasenia");
            
            nuevaEmpresa.save();
            
            
            formRegistro=formFactory.form(RegistroEmpresa.class);
            }
            else{
                  return ok(registroEmpresa.render("\n El codigo CFI  yas se encuentra registrado.", formRegistro,
                routes.HomeController.registroEmpresaPost()));
            }
          }
          return ok(registroEmpresa.render("\nRecepción de registro correcto.", formRegistro,
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
            nuevoFormEmpresa.estadoContrataciones=values.get("estadoContrataciones");
            nuevoFormEmpresa.otrasContrataciones=values.get("otrasContrataciones");
            nuevoFormEmpresa.save();
            
            formEmpresa = formFactory.form(FormularioEmpresa.class);
        }
        return ok(formularioEmpresa.render("Recepcion de formulario correcto.", formEmpresa,

                routes.HomeController.crearFormularioEmpresaPost()));
    }
    
    public Result crearFormularioEstudianteGet() {//muestrar la pantalla el post hace la operacio
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
         RegistroEmpresa registro = new RegistroEmpresa();
        return ok(perfilEmpresa.render("Perfil Empresa", registro));
    }
     public Result perfilEstudiante(){
         RegistroUsuario usuario = new RegistroUsuario();
         List<FormularioEstudiante> formEstu = FormularioEstudiante.find.all();
        return ok(perfilEstudiante.render("", formEstu, usuario));
    }
     public Result editarPerfilEstudianteGet(Long id){
         List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
         FormularioEstudiante instancia = FormularioEstudiante.find.byId(id);
         Form<FormularioEstudiante> formEst = formFactory.form(FormularioEstudiante.class).fill(instancia);
         return ok(formularioEstudiante.render("Formulario Estudiante", formEst,
                 anios, routes.HomeController.editarPerfilPost(id)));
     }
     public Result editarPerfilPost(Long id){
         List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
         FormularioEstudiante instancia = FormularioEstudiante.find.byId(id);
         Form<FormularioEstudiante> formEstu = formFactory.form(FormularioEstudiante.class).fill(instancia).bindFromRequest();
         if(formEstu.hasErrors()){
             return badRequest(formularioEstudiante.render("Encontramos errores en el formulario", formEstu, 
                     anios, routes.HomeController.editarPerfilPost(id)));
         }
         FormularioEstudiante formEst = formEstu.get();
         instancia.nombre = formEst.nombre;
         instancia.primerApellido = formEst.primerApellido;
         instancia.segundoApellido = formEst.segundoApellido;
         instancia.fechaNacimiento = formEst.fechaNacimiento;
         instancia.cedula = formEst.cedula;
         instancia.correo = formEst.correo;
         instancia.estadoCivil = formEst.estadoCivil;
         instancia.paisNacimiento = formEst.paisNacimiento;
         instancia.lugarResidencia = formEst.lugarResidencia;
         instancia.direccion = formEst.direccion;
         instancia.telefonoCasa = formEst.telefonoCasa;
         instancia.telefonoMovil = formEst.telefonoMovil;
         instancia.licencia = formEst.licencia;
         instancia.perfilPersonal = formEst.perfilPersonal;
         instancia.profesion = formEst.profesion;
         instancia.perfilProfesional = formEst.perfilProfesional;
         instancia.anosExperiencia = formEst.anosExperiencia;
         instancia.empresa = formEst.empresa;
         instancia.puesto = formEst.puesto;
         instancia.anosTrabajo = formEst.anosTrabajo;
         instancia.titulo = formEst.titulo;
         instancia.institucion = formEst.institucion;
         instancia.idiomas = formEst.idiomas;
         instancia.otrosTitulos = formEst.otrosTitulos;
         instancia.estadoLaboral = formEst.estadoLaboral;
         instancia.anoIngresoFormal= formEst.anoIngresoFormal;
         instancia.anoFinalFormal = formEst.anoFinalFormal;
         instancia.traslado = formEst.traslado;
         instancia.genero = formEst.genero;
         instancia.save();
         return redirect(routes.HomeController.listarFormularioEstudiante());
     }
     
     public Result listarFormularioEstudiante(){
         RegistroUsuario usuario = new RegistroUsuario();
         List<FormularioEstudiante> formEstu = FormularioEstudiante.find.all();
         System.out.println("impresion lista formulario est: "+formEstu);
         return ok(perfilEstudiante.render("Formulario Estudiantes", formEstu, usuario));
     }

}
