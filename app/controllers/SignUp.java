/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;
//import controllers.ApplicationUser;
import models.UserEmpresa;  
//import models.utils.AppException;
//import models.utils.Hash;
import play.Configuration;
import play.Logger;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
//import views.html.userhome;

import java.net.URL;
import java.util.UUID;
import java.util.Date;
import models.RegistroEmpresa;
import play.mvc.Security;

import static play.data.Form.form;



/**
 *
 * @authorEXDER 
 */
@Security.Authenticated(Secured.class)
public class SignUp {
    
//public Result create() {
//      //String username = ctx().session().get("username");
//      //User user = User.findByUsername(username)
//      UserEmpresa user = null;
//      return ok(registroEmpresa.render(user, form(ApplicationUser.CreateUser.class)));
//    }
    /**
     * Se encarga de la renderización del form de creación de usuarios, envía al render la información
     * del usuario logueado y el form necesario para crear los usuarios
     *
     * @return el render de la plantilla de creación de usuarios
     **/
//    public Result createOnlyForm() {
//      //String username = ctx().session().get("username");
//      //User user = User.findByUsername(username);
//      UserEmpresa user = null;
//      return ok(registroEmpresa.render(user, form(ApplicationUser.CreateUser.class)));
//      
//    }

    /**
     * Se encarga de guardar un nuevo usuario con la información proporcionada en el form correspondiente,
     * antes revisa que no haya errores en la información enviada por el usuario y si todos los datos están bien
     * crea el usuario en la base de datos, sino envía la información con los errores encontrados
     *
     * @return el render de la plantilla de creación correcta de usuario si la información proporcionada es correcta,
               en otro caso retorna badRequest con la información de los errores encontrados en el formulario
     **/
//    public Result save() {
//        Form<ApplicationUser.CreateUser> registerForm = form(ApplicationUser.CreateUser.class).bindFromRequest();
//
//        if (registerForm.hasErrors()) {
//            String CFI = ctx().session().get("CFI");
//            return badRequest(index.render(UserEmpresa.findByUsername(CFI), registerForm)); // errores en el form
//        }
//
//        ApplicationUser.CreateUser register = registerForm.get();
//        Result resultError = checkBeforeSave(registerForm, register.CFI); // comprueba si el nombre de usuario ya existe en la bd
//
//        if (resultError != null) {
//            return resultError; // en caso de que el nombre de usuario ya exista
//        }
//
//        try {
//            UserEmpresa user = new UserEmpresa();
//            user.CFI = register.CFI;
//            //user.passwordHash = Hash.createPassword(register.password);
//            user.creationDate = new Date();
//            user.save();
//            String CFI = ctx().session().get("CFI");
//            return redirect(routes.Application.home()); // usuario creado correctamente
//        } catch (Exception e) {
//            Logger.error("Error salvando usuario", e);
//            flash("danger", "Error guardando los datos"); // error al guardar el usuario en la bd
//        }
//        String CFI = ctx().session().get("CFI");
//        return badRequest(index.render(UserEmpresa.findByUsername(CFI), registerForm));
//    }
//
//    /**
//     * Comprueba si el nombre de usuario proporcionado en el form ya existe en
//     * la base de datos, si ya existe retorna un error indicandolo, sino envía un null para que se
//     * continue correctamente con la creación del usuario en la base de datos
//     *
//     * @param registerForm form de creación de usuarios para el render
//     * @param username nombre del usuario a buscar en la base de datos
//     * @return el render de la plantilla de creación de usuarios con el error si
//     *         nombre de usuario ya ha sido utilizado, null en caso contrario
//     */
//    
//    
//    private Result checkBeforeSave(Form<ApplicationUser.CreateUser> registerForm, String CFI) {
//        if (UserEmpresa.findByUsername(CFI) != null) {
//            flash("danger", "Usuario existente");
//            return badRequest(index.render(UserEmpresa.findByUsername(CFI), registerForm));
//        }
//        return null;
//    }
}