package controllers;

import play.mvc.*;
import views.html.Home.*;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        
        return ok(index.render()); 
                
    }
//    public Result acerca_de(){
//        return ok(acerca_de.render());
//        //redirect("/Home/index");
//    }// 
    public Result iniciarSesionEstudiante(){
        return ok(iniciarSesionEstudiante.render());
    }
    public Result iniciarSesionEmpresa(){
        return ok(iniciarSesionEmpresa.render());
    }
     public Result registroEstudiante(){
        return ok(registroEstudiante.render());
    }
      public Result registroEmpresa(){
        return ok(registroEmpresa.render());
    }
       public Result formularioEmpresa(){
        return ok(formularioEmpresa.render());
    }
}
