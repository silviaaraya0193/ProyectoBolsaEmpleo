/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.util.Date;
import java.util.List;
import java.util.Map;
import models.FormularioEmpresa;
import models.RegistroEmpresa;
import models.utils.AppException;
import models.utils.Hash;
import play.Logger;
import play.data.Form;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.data.FormFactory;
import static play.data.Form.form;
import javax.inject.Inject;
import models.FormularioEstudiante;
import models.utils.EmpresaSession;



/**
 *
 * @author Expression EXDER 
 */
public class ApplicationEmpresa extends Controller {
    @Inject
    FormFactory formFactory;
    
    //METODO PARA LISTAR FORMULARIO EMPRESAS
    public Result listarFormularioEmpresa(){
        RegistroEmpresa empresa = new EmpresaSession().getRegistroEmpresa();
       // List<FormularioEmpresa> formulario = FormularioEmpresa.find.all();
        List<FormularioEmpresa> formEmpresa = FormularioEmpresa.find.where().ilike("registroEmpresa", ""+empresa.id).findList();
        System.out.println("");
        System.out.println("id: "+empresa.id);
        System.err.println("TAM "+formEmpresa.size());
        return ok(perfilEmpresa.render("", formEmpresa, empresa));
    }
    //METODO PARA ELIMINAR FORMULARIO EMPRESAS
    public Result eliminarFormularioEmpresa(Long id) {
        RegistroEmpresa empresa = new EmpresaSession().getRegistroEmpresa();
        List<FormularioEmpresa> instancia = FormularioEmpresa.find.where().ilike("registroEmpresa",""+empresa.id).findList();
            for(FormularioEmpresa femp: instancia){
                femp.delete();
            }
        return redirect(routes.ApplicationEmpresa.listarFormularioEmpresa());
    } 
    //METODO GET PARA EDITAR EL FORMULARIO DE UNA EMPRESA
    public Result editarPerfilEmpresaGet(Long id){
         FormularioEmpresa instancia = FormularioEmpresa.find.byId(id);
         Form<FormularioEmpresa> formEmp = formFactory.form(FormularioEmpresa.class).fill(instancia);
         return ok(formularioEmpresa.render("Formulario Empresa", formEmp,
                  routes.ApplicationEmpresa.editarPerfilEmpresaPost(id)));
     }
    //METODO POST PARA EDITAR EL FORMULARIO DE UN ESTUDIANTE
     public Result editarPerfilEmpresaPost(Long id){
         FormularioEmpresa instancia = FormularioEmpresa.find.byId(id);
         Form<FormularioEmpresa> formEmp = formFactory.form(FormularioEmpresa.class).fill(instancia).bindFromRequest();
         if(formEmp.hasErrors()){
             return badRequest(formularioEmpresa.render("Encontramos errores en el formulario", formEmp, 
                    routes.ApplicationEmpresa.editarPerfilEmpresaPost(id)));
         }
         FormularioEmpresa formEmpresa = formEmp.get();
         instancia.nombre = formEmpresa.nombre;
         instancia.correoEmpresa = formEmpresa.correoEmpresa;
         instancia.direccion = formEmpresa.direccion;
         instancia.estadoContrataciones = formEmpresa.estadoContrataciones;
         instancia.otrasContrataciones = formEmpresa.otrasContrataciones;
         instancia.perfilEmpresarial = formEmpresa.perfilEmpresarial;
         instancia.telefonoContacto = formEmpresa.telefonoContacto;
         
         instancia.save();
         return redirect(routes.ApplicationEmpresa.listarFormularioEmpresa());
     }
     
     //METODO GET REGISTRO EMPRESA
     public Result registroEmpresaGet(){
          Form<RegistroEmpresa> pregForm = formFactory.form(RegistroEmpresa.class);
        return ok(registroEmpresa.render(" Registro Empresa",pregForm,routes.ApplicationEmpresa.registroEmpresaPost()));
        
    }
       public Result registroEmpresaPost() throws AppException{//error por e hashpassword que se crea como variable
          Form<RegistroEmpresa> formRegistro=formFactory.form(RegistroEmpresa.class).fill(new RegistroEmpresa("ABC123", new Date())).bindFromRequest();
          if(formRegistro.hasErrors()){
              //formRegistro.
              //System.out.println("error form registro ");
              return badRequest(registroEmpresa.render("Encontramos errores en form registro",
                    formRegistro, routes.ApplicationEmpresa.registroEmpresaPost()));
          }
          else{
            Map<String ,String> values=formRegistro.data();//optiene los datos como un map del registro Empresaa
            RegistroEmpresa nuevaEmpresa= new RegistroEmpresa();
             if(nuevaEmpresa.findByUsername(values.get("cfi"))==null){
            System.out.println(values);
            nuevaEmpresa.nombre=values.get("nombre");
            nuevaEmpresa.cfi=values.get("cfi");
            nuevaEmpresa.correo= values.get("correo");
            nuevaEmpresa.telefono= Integer.parseInt(values.get("telefono"));
            nuevaEmpresa.contrasenia= values.get("contrasenia");
            nuevaEmpresa.passwordHash=Hash.createPassword(nuevaEmpresa.contrasenia);
            nuevaEmpresa.creationDate=new Date();
            nuevaEmpresa.save();
            
            formRegistro=formFactory.form(RegistroEmpresa.class);
            }
            else{
                  return ok(registroEmpresa.render("\n El codigo CFI  ya se encuentra registrado.", formRegistro,
                routes.ApplicationEmpresa.registroEmpresaPost()));
            }
      }
          Form<FormularioEmpresa> pregForm = formFactory.form(FormularioEmpresa.class);
          RegistroEmpresa empresa = new RegistroEmpresa();
          List<FormularioEmpresa> formEmp = FormularioEmpresa.find.all();
          return ok(perfilEmpresa.render("",formEmp,empresa));//ir a opciones2 para iniciar sesion
      }
    
     public Result crearFormularioEmpresaGet() {
        Form<FormularioEmpresa> pregForm = formFactory.form(FormularioEmpresa.class);
        return ok(formularioEmpresa.render(" ",
                pregForm, routes.ApplicationEmpresa.crearFormularioEmpresaPost()));
    }
    public Result crearFormularioEmpresaPost() {//creacion del formulario sin errores
        //verificacion y recepciones de requierds and data view//
        //datas- que son los datos de la vista, values obtenidos por la key q se obtiene de el preform que fuarda la vista
        // List<FormularioEmpresa> instancia = FormularioEmpresa.find.all();
        Form<FormularioEmpresa> formEmpresa = formFactory.form(FormularioEmpresa.class).bindFromRequest();//captura los datos de la vista
        if (formEmpresa.hasErrors()) {
            
            return badRequest(formularioEmpresa.render("Encontramos errores",
                    formEmpresa, routes.ApplicationEmpresa.crearFormularioEmpresaPost()));
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
         String cfi = ctx().session().get("cfi");
         RegistroEmpresa user = RegistroEmpresa.findByUsername(cfi);//busca el cfi
        return ok(formularioEmpresa.render("Recepcion de formulario correcto.", formEmpresa,
                routes.ApplicationEmpresa.perfilEmpresa()));
    }
    public Result perfilEmpresa(){
         List<FormularioEmpresa> formEmp = FormularioEmpresa.find.all();
         RegistroEmpresa registro = new RegistroEmpresa();
        return ok(perfilEmpresa.render("", formEmp,registro));
    }
    
 public Result GO_HOME = redirect(
            routes.ApplicationEmpresa.home()
    );

 public Result home() {//controlador del home o index
     List<FormularioEmpresa> instancia = FormularioEmpresa.find.all();
        String cfi = ctx().session().get("cfi");
        if (cfi!=null) {
            RegistroEmpresa user = RegistroEmpresa.findByUsername(cfi);//busca el cfi
           // System.out.println("user"+user);
            if (user != null) {
                return  redirect(routes.ApplicationEmpresa.listarFormularioEmpresa());
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
                return Messages.get("user es null");
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