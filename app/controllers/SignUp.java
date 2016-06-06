/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;
import controllers.ApplicationEmpresa;
import models.RegistroEmpresa;  
import models.utils.AppException;
import models.utils.Hash;
import play.Configuration;
import play.Logger;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.mvc.Security;

import static play.data.Form.form;



/**
 * Empresa
 * @authorEXDER 
 */
@Security.Authenticated(Secured.class)
public class SignUp extends Controller{
    
public Result create() {
      //String username = ctx().session().get("username");
      //User user = User.findByUsername(username)
      RegistroEmpresa user = null;
      return ok(create.render(user, form(ApplicationEmpresa.CreateUser.class)));
    }
    /**
     * Se encarga de la renderización del form de creación de usuarios, envía al render la información
     * del usuario logueado y el form necesario para crear los usuarios
     *
     * @return el render de la plantilla de creación de usuarios
     **/
    public Result createOnlyForm() {
      //String username = ctx().session().get("username");
      //User user = User.findByUsername(username);
      RegistroEmpresa user = null;
      return ok(create.render(user, form(ApplicationEmpresa.CreateUser.class)));
      
    }

    /**
     * Se encarga de guardar un nuevo usuario con la información proporcionada en el form correspondiente,
     * antes revisa que no haya errores en la información enviada por el usuario y si todos los datos están bien
     * crea el usuario en la base de datos, sino envía la información con los errores encontrados
     *
     * @return el render de la plantilla de creación correcta de usuario si la información proporcionada es correcta,
               en otro caso retorna badRequest con la información de los errores encontrados en el formulario
     **/
    public Result save() {
        Form<ApplicationEmpresa.CreateUser> registerForm = form(ApplicationEmpresa.CreateUser.class).bindFromRequest();

        if (registerForm.hasErrors()) {
            String cfi = ctx().session().get("cfi");
            return badRequest(create.render(RegistroEmpresa.findByUsername(cfi), registerForm)); // errores en el form
        }

        ApplicationEmpresa.CreateUser register = registerForm.get();
        Result resultError = checkBeforeSave(registerForm, register.cfi); // comprueba si el nombre de usuario ya existe en la bd

        if (resultError != null) {
            return resultError; // en caso de que el nombre de usuario ya exista
        }

        try {
            RegistroEmpresa user = new RegistroEmpresa();
            user.cfi = register.cfi;
            user.passwordHash = Hash.createPassword(register.password);
            //user.creationDate = new Date();
            user.save();
            String cfi = ctx().session().get("cfi");
            return redirect(routes.ApplicationEmpresa.home()); // usuario creado correctamente
        } catch (Exception e) {
            Logger.error("Error salvando usuario", e);
            flash("danger", "Error guardando los datos"); // error al guardar el usuario en la bd
        }
        String cfi = ctx().session().get("cfi");
        return badRequest(create.render(RegistroEmpresa.findByUsername(cfi), registerForm));
    }

    /**
     * Comprueba si el nombre de usuario proporcionado en el form ya existe en
     * la base de datos, si ya existe retorna un error indicandolo, sino envía un null para que se
     * continue correctamente con la creación del usuario en la base de datos
     *
     * @param registerForm form de creación de usuarios para el render
     * @param username nombre del usuario a buscar en la base de datos
     * @return el render de la plantilla de creación de usuarios con el error si
     *         nombre de usuario ya ha sido utilizado, null en caso contrario
     */
    
    
    private Result checkBeforeSave(Form<ApplicationEmpresa.CreateUser> registerForm, String cfi) {
        if (RegistroEmpresa.findByUsername(cfi) != null) {
            flash("danger", "Usuario existente");
            return badRequest(create.render(RegistroEmpresa.findByUsername(cfi), registerForm));
        }
        return null;
    }
}