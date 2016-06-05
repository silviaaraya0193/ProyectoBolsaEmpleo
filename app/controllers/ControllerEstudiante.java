/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import models.FormularioEstudiante;
import models.RegistroUsuario;
import models.utils.AppException;
import models.utils.Hash;
import models.utils.UsuarioSession;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.data.validation.Constraints.*;
import play.data.FormFactory;
import static play.data.Form.form;
import javax.inject.Inject;
/**
 *
 * @author viccr
 */
public class ControllerEstudiante extends Controller{
     @Inject 
     FormFactory formFactory;
     
      public Result listarFormularioEstudiante(){
        RegistroUsuario usuario = new UsuarioSession().getRegistroUsuario();
        List<FormularioEstudiante> formEstu = FormularioEstudiante.find.where(
        ).ilike("registroUsuario", ""+usuario.id).findList();
       
        System.err.println("TAM estudiante: "+formEstu.size()+"/n hola");
        return ok(perfilEstudiante.render("", formEstu, usuario));
    }
    public Result eliminarFormularioEstudiante(Long id) {
        RegistroUsuario usuario = new UsuarioSession().getRegistroUsuario();
        List<FormularioEstudiante> instancia = FormularioEstudiante.find.where().ilike("registroUsuario",""+usuario.id).findList();
            for(FormularioEstudiante fest: instancia){
                fest.delete();
            }
        return redirect(routes.ControllerEstudiante.listarFormularioEstudiante());
    } 
    public Result editarPerfilEstudianteGet(Long id){
         List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
         FormularioEstudiante instancia = FormularioEstudiante.find.byId(id);
         Form<FormularioEstudiante> formEst = formFactory.form(FormularioEstudiante.class).fill(instancia);
         return ok(formularioEstudiante.render("Formulario Estudiante", formEst,
                 anios, routes.ControllerEstudiante.editarPerfilPost(id)));
     }
     public Result editarPerfilPost(Long id){
         List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
         FormularioEstudiante instancia = FormularioEstudiante.find.byId(id);
         Form<FormularioEstudiante> formEstu = formFactory.form(FormularioEstudiante.class).fill(instancia).bindFromRequest();
         if(formEstu.hasErrors()){
             return badRequest(formularioEstudiante.render("Encontramos errores en el formulario", formEstu, 
                     anios, routes.ControllerEstudiante.editarPerfilPost(id)));
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
         return redirect(routes.ControllerEstudiante.listarFormularioEstudiante());
     }
      public Result crearFormularioEstudianteGet() {//muestrar la pantalla el post hace la operacio
        FormularioEstudiante formEstudiante = new FormularioEstudiante();
        formEstudiante.setRegistroUsuario(new UsuarioSession().getRegistroUsuario());
        Form<FormularioEstudiante> pregForm = formFactory.form(FormularioEstudiante.class).fill(formEstudiante);
        List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
        
        return ok(formularioEstudiante.render(" ",
                pregForm, anios, routes.ControllerEstudiante.crearFormularioEstudiantePost()));
    }
    public Result crearFormularioEstudiantePost() {
        List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
        Form<FormularioEstudiante> formEst = formFactory.form(FormularioEstudiante.class).bindFromRequest();
        if (formEst.hasErrors()) {
            //System.out.println("primero: "+pregForm);
            return badRequest(formularioEstudiante.render("Encontramos errores",
                    formEst,anios, routes.ControllerEstudiante.crearFormularioEstudiantePost()));
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
            nuevoFormEst.setRegistroUsuario(new UsuarioSession().getRegistroUsuario());
            //System.out.println("informacion: "+preg);
            nuevoFormEst.save();
            formEst = formFactory.form(FormularioEstudiante.class);
        }
        return ok(formularioEstudiante.render("Recepcion de formulario correcto.", formEst,
                anios,
                routes.ControllerEstudiante.crearFormularioEstudiantePost()));
    }
     public Result registroEstudianteGet(){
         Form<RegistroUsuario> formUsuario= formFactory.form(RegistroUsuario.class);
        return ok(registroEstudiante.render("",formUsuario,routes.ControllerEstudiante.registroEstudiantePost())); 
    }
    public Result registroEstudiantePost() throws AppException{
         Form<RegistroUsuario> formUsuario=formFactory.form(RegistroUsuario.class).fill(new RegistroUsuario("ABC123", new Date())).bindFromRequest();
          if(formUsuario.hasErrors()){
              //formRegistro.
              System.out.println("error form registro ");
              return badRequest(registroEstudiante.render("Encontramos errores en form registro",
                    formUsuario, routes.ControllerEstudiante.registroEstudiantePost()));
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
                routes.ControllerEstudiante.registroEstudiantePost()));
            }
          }
          List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
              RegistroUsuario usuario = new RegistroUsuario();
         List<FormularioEstudiante> formEstu = FormularioEstudiante.find.all();
        return ok(perfilEstudiante.render("", formEstu, usuario));
    } 
     public Result perfilEstudiante(){
         RegistroUsuario usuario = new RegistroUsuario();
         List<FormularioEstudiante> formEstu = FormularioEstudiante.find.all();
        return ok(perfilEstudiante.render("", formEstu, usuario));
    }
     public Result opciones(){
        return ok(opciones.render(" "));
    }
}
