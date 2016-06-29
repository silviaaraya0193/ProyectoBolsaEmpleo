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
import models.FormularioEmpresa;
import models.RegistroEmpresa;
import models.utils.XML;
import play.api.i18n.Lang;

/**
 *
 * @author Silvia
 */
public class ControllerEstudiante extends Controller {

    @Inject
    FormFactory formFactory;
    @Inject
    PdfGenerator pdfGenerator;
    XML xml = new XML();
     Lang underlyingLang;
     
     
    CreadorArchivo creador = new CreadorArchivo("Estudiante.xml");
    public Result document() {
       RegistroUsuario usuario = new UsuarioSession().getRegistroUsuario();
        List<FormularioEstudiante> formEstu = FormularioEstudiante.find.where(
        ).ilike("registroUsuario", ""+usuario.id).findList();
         redireccionaCambioIdiomaIngles();
        return pdfGenerator.ok(perfilEstudiante.render("",formEstu,usuario), "http://localhost:9000").as("application/pdf");
    }
     public Result cambioIdiomaEst(){
         underlyingLang=play.mvc.Http.Context.current().lang();
           if(underlyingLang.code().equals("es")){
               ctx().setTransientLang("en");
               ctx().changeLang("en");
               RegistroUsuario usuario = new UsuarioSession().getRegistroUsuario();
          List<FormularioEstudiante> formEstu = FormularioEstudiante.find.where().ilike("registroUsuario", ""+usuario.id).findList();
           return ok(perfilEstudiante.render("",formEstu,usuario));
           }
            ctx().setTransientLang("es");
            ctx().changeLang("es");
             RegistroUsuario usuario = new UsuarioSession().getRegistroUsuario();
          List<FormularioEstudiante> formEstu = FormularioEstudiante.find.where().ilike("registroUsuario", ""+usuario.id).findList();
           return ok(perfilEstudiante.render("",formEstu,usuario));
      }
      public void redireccionaCambioIdiomaIngles(){
          String aux="";
          if(underlyingLang.code().equals("es"))
              aux="en";
          else
              aux="es";
             ctx().setTransientLang(aux);
      }
 
    //METODO PARA LISTAR LOS ESTUDIANTES

    public Result listarFormularioEstudiante() {
       RegistroUsuario usuario = new UsuarioSession().getRegistroUsuario();
        if(usuario!=null){
            List<FormularioEstudiante> formEstu = FormularioEstudiante.find.where(
            ).ilike("registroUsuario", ""+usuario.id).findList();

            System.err.println("TAM estudiante: "+formEstu.size()+"/n hola");
            return ok(perfilEstudiante.render("", formEstu, usuario));
        }
         
        return redirect(routes.ApplicationEstudiante.homeEst());

    }
    //METODO PARA ELIMINAR LOS FORMULARIOS DE LOS ESTUDIANTES

    public Result eliminarFormularioEstudiante(Long id) {
         RegistroUsuario usuario = new UsuarioSession().getRegistroUsuario();
        if(usuario!=null){
             List<FormularioEstudiante> instancia = FormularioEstudiante.find.where().ilike("registroUsuario",""+usuario.id).findList();
            for(FormularioEstudiante fest: instancia){
                fest.delete();
            }
                 return redirect(routes.ControllerEstudiante.listarFormularioEstudiante());
        }
          return redirect(routes.ApplicationEstudiante.homeEst());
    } 
    //METODO GET PARA EDITAR EL FORMULARIO DE UN ESTUDIANTE
    //en este metodo usted agrego un if(usuario != null) como el del metodo de arriba de listar, pero 
    //si le dejaba eso me daba error, bueno me redireccionaba a la pagina de home es estudiante
    //o me cargaba el formulario sin los datos del usuario que quiere modificar.
    public Result editarPerfilEstudianteGet(Long id) {
       List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
         FormularioEstudiante instancia = FormularioEstudiante.find.byId(id);
         Form<FormularioEstudiante> formEst = formFactory.form(FormularioEstudiante.class).fill(instancia);
         return ok(formularioEstudiante.render("Formulario Estudiante", formEst,
                 anios, routes.ControllerEstudiante.editarPerfilPost(id)));
    }

    //METODO POST PARA EDITAR UN FORMULARIO DE ESTUDIANTE

    public Result editarPerfilPost(Long id) {
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
         instancia.empresa2 = formEst.empresa2;
         instancia.empresa3 = formEst.empresa3;
         instancia.puesto = formEst.puesto;
         instancia.puesto2 = formEst.puesto2;
         instancia.puesto3 = formEst.puesto3;
         instancia.anosTrabajo = formEst.anosTrabajo;
         instancia.anosTrabajo2 = formEst.anosTrabajo2;
         instancia.anosTrabajo3 = formEst.anosTrabajo3;
         instancia.titulo = formEst.titulo;
         instancia.titulo2 = formEst.titulo2;
         instancia.institucion = formEst.institucion;
         instancia.institucion2 = formEst.institucion2;
         instancia.idiomas = formEst.idiomas;
         instancia.otrosTitulos = formEst.otrosTitulos;
         instancia.otrosTrabajos = formEst.otrosTrabajos;
         instancia.otrosTitulosFormales = formEst.otrosTitulosFormales;
         instancia.estadoLaboral = formEst.estadoLaboral;
         instancia.anoIngresoFormal= formEst.anoIngresoFormal;
         instancia.anoIngresoFormal2= formEst.anoIngresoFormal2;
         instancia.anoFinalFormal = formEst.anoFinalFormal;
         instancia.anoFinalFormal2 = formEst.anoFinalFormal2;
         instancia.traslado = formEst.traslado;
         instancia.genero = formEst.genero;
         instancia.save();
         return redirect(routes.ControllerEstudiante.listarFormularioEstudiante());
    }
    //METODO GET PARA CREAR UN FORMULARIO DE ESTUDIANTE

     public Result crearFormularioEstudianteGet() {//muestrar la pantalla el post hace la operacio
        FormularioEstudiante formEstudiante = new FormularioEstudiante();
        formEstudiante.setRegistroUsuario(new UsuarioSession().getRegistroUsuario());
        Form<FormularioEstudiante> pregForm = formFactory.form(FormularioEstudiante.class).fill(formEstudiante);
        List anios = new ArrayList();
        for (int x = 1990; x<2017; x++){anios.add(x);}
        
        return ok(formularioEstudiante.render(" ",
                pregForm, anios, routes.ControllerEstudiante.crearFormularioEstudiantePost()));
    }
      //METODO POST PARA CREAR UN FORMULARIO DE ESTUDIANTE
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
//       return  redirect(routes.ApplicationEstudiante.homeEst());
        RegistroUsuario usuario = new UsuarioSession().getRegistroUsuario();
        List<FormularioEstudiante> formEstu = FormularioEstudiante.find.where(
        ).ilike("registroUsuario", ""+usuario.id).findList();
        return ok(perfilEstudiante.render("",formEstu,usuario ));
    }
    //METODO GET PARA REGISTAR ESTUDIANTES
     public Result registroEstudianteGet(){
         Form<RegistroUsuario> formUsuario= formFactory.form(RegistroUsuario.class);
        return ok(registroEstudiante.render("",formUsuario,routes.ControllerEstudiante.registroEstudiantePost())); 
    }
     //METODO POST PARA REGISTRAR ESTUDIANTES
    public Result registroEstudiantePost() throws AppException{
         Form<RegistroUsuario> formUsuario=formFactory.form(RegistroUsuario.class).fill(new RegistroUsuario( new Date())).bindFromRequest();
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
               nuevoUsuario.contrasenia= Hash.createPassword(values.get("contrasenia"));
                //nuevoUsuario.passwordHash=Hash.createPassword(values.get("contrasenia"));
                nuevoUsuario.creationDate=new Date();
                nuevoUsuario.save();
                formUsuario=formFactory.form(RegistroUsuario.class);
            }
            else{
                 return ok(registroEstudiante.render("\n Correo ya existe.", formUsuario,
                routes.ControllerEstudiante.registroEstudiantePost()));
            }
          }
        return ok(opciones.render(""));
    } 
    //METODO PARA CARGAR EL PERFIL DE ESTUDIANTES
     public Result perfilEstudiante(){
         RegistroUsuario usuario = new RegistroUsuario();
         List<FormularioEstudiante> formEstu = FormularioEstudiante.find.all();

        return ok(perfilEstudiante.render("", formEstu, usuario));
    }

   //METODO QUE CARGA LAS OPCIONES DE INICIAR SESION Y REGISTRARSE PARA LOS ESTUDIANTES
     public Result opciones(){
        return ok(opciones.render(" "));
    }

    public String[] devolverDatos() {
        RegistroUsuario usuario = new UsuarioSession().getRegistroUsuario();
        List<FormularioEstudiante> formEstu = FormularioEstudiante.find.where().ilike("registroUsuario", "" + usuario.id).findList();
        String[] datos;
        datos = new String[41];
        for (int i = 0; i < formEstu.size(); i++) {
            datos[0] = formEstu.get(i).cedula;
            datos[1] = formEstu.get(i).nombre;
            datos[2] = formEstu.get(i).direccion;
            datos[3] = formEstu.get(i).fechaNacimiento;
            datos[4] = formEstu.get(i).primerApellido;
            datos[5] = formEstu.get(i).segundoApellido;
            datos[6] = formEstu.get(i).correo;
            datos[7] = formEstu.get(i).estadoCivil;
            datos[8] = formEstu.get(i).paisNacimiento;
            datos[9] = formEstu.get(i).lugarResidencia;
            //datos[10] = formEstu.get(i).id;
            datos[10] = formEstu.get(i).telefonoCasa;
            datos[11] = formEstu.get(i).telefonoMovil;
            datos[12] = formEstu.get(i).licencia;
            datos[13] = formEstu.get(i).perfilPersonal;
            datos[14] = formEstu.get(i).profesion;
            datos[15] = formEstu.get(i).perfilProfesional;
            datos[16] = formEstu.get(i).anosExperiencia;
            datos[17] = formEstu.get(i).empresa;
            datos[18] = formEstu.get(i).empresa2;
            datos[19] = formEstu.get(i).empresa3;
            datos[20] = formEstu.get(i).puesto;
            datos[21] = formEstu.get(i).puesto2;
            datos[22] = formEstu.get(i).puesto3;
            datos[23] = formEstu.get(i).anosTrabajo;
            datos[24] = formEstu.get(i).anosTrabajo2;
            datos[25] = formEstu.get(i).anosTrabajo3;
            datos[26] = formEstu.get(i).titulo;
            datos[27] = formEstu.get(i).titulo2;
            datos[28] = formEstu.get(i).institucion;
            datos[29] = formEstu.get(i).institucion2;
            datos[30] = formEstu.get(i).idiomas;
            datos[31] = formEstu.get(i).otrosTitulos;
            datos[32] = formEstu.get(i).otrosTitulosFormales;
            datos[33] = formEstu.get(i).otrosTrabajos;
            datos[34] = formEstu.get(i).estadoLaboral;
            datos[35] = formEstu.get(i).anoIngresoFormal;
            datos[36] = formEstu.get(i).anoIngresoFormal2;
            datos[37] = formEstu.get(i).anoFinalFormal;
            datos[38] = formEstu.get(i).anoFinalFormal2;
            datos[39] = formEstu.get(i).traslado;
            datos[40] = formEstu.get(i).genero;
            
        }
        return datos;
    }
}