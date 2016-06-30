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
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.data.FormFactory;
import static play.data.Form.form;
import javax.inject.Inject;
import models.utils.EmpresaSession;
import play.api.i18n.Lang;
//import play.api.i18n.Message;

/**
 *
 * @author viccr
 */
public class ControllerEmpresa extends Controller {

    @Inject
    FormFactory formFactory;
    @Inject
    PdfGenerator pdfGenerator;
    Lang underlyingLang;

    public Result document() {

        RegistroEmpresa empresa = new EmpresaSession().getRegistroEmpresa();

        List<FormularioEmpresa> formEmpresa = FormularioEmpresa.find.where().ilike("registroEmpresa", "" + empresa.id).findList();
        redireccionaCambioIdiomaIngles();
        return pdfGenerator.ok(perfilEmpresa.render("", formEmpresa, empresa), "http://localhost:9000").as("application/pdf");//content type
    }

    public Result cambioIdioma() {
        underlyingLang = play.mvc.Http.Context.current().lang();
        if (underlyingLang.code().equals("es")) {
            ctx().setTransientLang("en");
            ctx().changeLang("en");
            RegistroEmpresa empresa = new EmpresaSession().getRegistroEmpresa();
            List<FormularioEmpresa> formEmpresa = FormularioEmpresa.find.where().ilike("registroEmpresa", "" + empresa.id).findList();

            return ok(perfilEmpresa.render("", formEmpresa, empresa));

        }
        ctx().setTransientLang("es");
        ctx().changeLang("es");
        RegistroEmpresa empresa = new EmpresaSession().getRegistroEmpresa();
        List<FormularioEmpresa> formEmpresa = FormularioEmpresa.find.where().ilike("registroEmpresa", "" + empresa.id).findList();
        return ok(perfilEmpresa.render("", formEmpresa, empresa));
    }

    public void redireccionaCambioIdiomaIngles() {
        String aux = "";
        if (underlyingLang.code().equals("es")) {
            aux = "en";
        } else {
            aux = "es";
        }
        ctx().setTransientLang(aux);
    }

    //METODO PARA LISTAR FORMULARIO EMPRESAS
    public Result listarFormularioEmpresa() {
        RegistroEmpresa empresa = new EmpresaSession().getRegistroEmpresa();
        if (empresa != null) {
            List<FormularioEmpresa> formEmpresa = FormularioEmpresa.find.where().ilike("registroEmpresa", "" + empresa.id).findList();
            System.out.println("");
            System.out.println("id: " + empresa.id);
            System.err.println("TAM " + formEmpresa.size());
            return ok(perfilEmpresa.render("", formEmpresa, empresa));
        }
        return redirect(routes.ApplicationEmpresa.home());
    }
//   
    //METODO PARA ELIMINAR FORMULARIO EMPRESAS

    public Result eliminarFormularioEmpresa(Long id) {
        RegistroEmpresa empresa = new EmpresaSession().getRegistroEmpresa();
        if (empresa != null) {
            List<FormularioEmpresa> instancia = FormularioEmpresa.find.where().ilike("registroEmpresa", "" + empresa.id).findList();
            for (FormularioEmpresa femp : instancia) {
                femp.delete();
            }
            return redirect(routes.ControllerEmpresa.listarFormularioEmpresa());
        }
        return redirect(routes.ApplicationEmpresa.home());
    }

    //METODO GET PARA EDITAR EL FORMULARIO DE UNA EMPRESA
    public Result editarPerfilEmpresaGet(Long id) {
        FormularioEmpresa instancia = FormularioEmpresa.find.byId(id);
        Form<FormularioEmpresa> formEmp = formFactory.form(FormularioEmpresa.class).fill(instancia);
        return ok(formularioEmpresa.render("Formulario Empresa/n", formEmp,
                routes.ControllerEmpresa.editarPerfilEmpresaPost(id)));
    }

    //METODO POST PARA EDITAR EL FORMULARIO DE LA EMPRESA
    public Result editarPerfilEmpresaPost(Long id) {
        FormularioEmpresa instancia = FormularioEmpresa.find.byId(id);
        Form<FormularioEmpresa> formEmp = formFactory.form(FormularioEmpresa.class).fill(instancia).bindFromRequest();
        RegistroEmpresa empresa = new EmpresaSession().getRegistroEmpresa();
        if (empresa != null) {
            if (formEmp.hasErrors()) {
                return badRequest(formularioEmpresa.render("Encontramos errores en el formulario de la empresa", formEmp,
                        routes.ControllerEmpresa.editarPerfilEmpresaPost(id)));
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
            return redirect(routes.ControllerEmpresa.listarFormularioEmpresa());
        }
        return redirect(routes.ApplicationEmpresa.home());
    }
    //METODO GET REGISTRO EMPRESA

    public Result registroEmpresaGet() {
        Form<RegistroEmpresa> pregForm = formFactory.form(RegistroEmpresa.class);
        return ok(registroEmpresa.render("", pregForm, routes.ControllerEmpresa.registroEmpresaPost()));

    }
    //METODO POST PARA EL REGISTRO DE EMPRESAS

    public Result registroEmpresaPost() throws AppException {//error por e hashpassword que se crea como variable
        Form<RegistroEmpresa> formRegistro = formFactory.form(RegistroEmpresa.class).fill(new RegistroEmpresa(new Date())).bindFromRequest();
        if (formRegistro.hasErrors()) {
            return badRequest(registroEmpresa.render("Encontramos errores en registro de la empresa",
                    formRegistro, routes.ControllerEmpresa.registroEmpresaPost()));
        } else {
            Map<String, String> values = formRegistro.data();//optiene los datos como un map del registro Empresaa
            RegistroEmpresa nuevaEmpresa = new RegistroEmpresa();
            if (nuevaEmpresa.findByUsername(values.get("cfi")) == null) {
                System.out.println(values);
                nuevaEmpresa.nombre = values.get("nombre");
                nuevaEmpresa.cfi = values.get("cfi");
                nuevaEmpresa.correo = values.get("correo");
                nuevaEmpresa.telefono = Integer.parseInt(values.get("telefono"));
                nuevaEmpresa.contrasenia = Hash.createPassword(values.get("contrasenia"));
                // nuevaEmpresa.passwordHash=Hash.createPassword(values.get("contrasenia"));
                nuevaEmpresa.creationDate = new Date();
                nuevaEmpresa.save();

                formRegistro = formFactory.form(RegistroEmpresa.class);
            } else {
                return ok(registroEmpresa.render("El codigo CFI  ya se encuentra registrado.", formRegistro,
                        routes.ControllerEmpresa.registroEmpresaPost()));
            }
        }
        return ok(opciones2.render(""));//ir a opciones2 para iniciar sesion
    }

    //METODO GET PARA CREAR FORMULARIO DE EMPRESAS
    public Result crearFormularioEmpresaGet() {
        FormularioEmpresa forEmpresa = new FormularioEmpresa();
        forEmpresa.setRegistroEmpresa(new EmpresaSession().getRegistroEmpresa());
        Form<FormularioEmpresa> pregForm = formFactory.form(FormularioEmpresa.class).fill(forEmpresa);

        return ok(formularioEmpresa.render(" ",
                pregForm, routes.ControllerEmpresa.creacionFormularioEmpresaPost()));
    }
    //METODO POST PARA LA CREACION DE FORMULARIO DE EMPRESAS

    public Result creacionFormularioEmpresaPost() {
        Form<FormularioEmpresa> formEmpresa = formFactory.form(FormularioEmpresa.class).bindFromRequest();//captura los datos de la vista
        if (formEmpresa.hasErrors()) {
            return badRequest(formularioEmpresa.render("Encontramos errores en el formulario de la empresa",
                    formEmpresa, routes.ControllerEmpresa.creacionFormularioEmpresaPost()));
        } else {
            Map<String, String> values = formEmpresa.data();//optiene los datos como un map

            FormularioEmpresa nuevoFormEmpresa = new FormularioEmpresa();
            nuevoFormEmpresa.nombre = values.get("nombre");
            nuevoFormEmpresa.direccion = values.get("direccion");
            nuevoFormEmpresa.telefonoContacto = values.get("telefonoContacto");
            nuevoFormEmpresa.correoEmpresa = values.get("correoEmpresa");
            nuevoFormEmpresa.perfilEmpresarial = values.get("perfilEmpresarial");//
            nuevoFormEmpresa.estadoContrataciones = values.get("estadoContrataciones");
            nuevoFormEmpresa.otrasContrataciones = values.get("otrasContrataciones");
            nuevoFormEmpresa.setRegistroEmpresa(new EmpresaSession().getRegistroEmpresa());
            nuevoFormEmpresa.save();
            formEmpresa = formFactory.form(FormularioEmpresa.class);

        }
        //System.out.println("evento perfil");
        RegistroEmpresa empresa = new EmpresaSession().getRegistroEmpresa();
        List<FormularioEmpresa> formEmp = FormularioEmpresa.find.where().ilike("registroEmpresa", "" + empresa.id).findList();

        return ok(perfilEmpresa.render("", formEmp, empresa));
    }

    //METODO QUE CARGA EL PERFIL DE LAS EMPRESAS
    public Result perfilEmpresa() {
        List<FormularioEmpresa> formEmp = FormularioEmpresa.find.all();
        RegistroEmpresa registro = new RegistroEmpresa();
        return ok(perfilEmpresa.render("", formEmp, registro));
    }

    //METODO QUE MUESTRA LAS OPCIONES DE INICIAR SESION, O REGISTRARSE DE LAS EMPRESAS
    public Result opciones2() {
        return ok(opciones2.render(" "));
    }
}
