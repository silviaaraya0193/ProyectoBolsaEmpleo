/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author viccr
 */
public class ConcreteBuilder implements AbstractBuilder{
    public Usuario usuario;

    @Override
    public void builEmpresa(Empresa empresa)throws Exception {
        if(empresa != null){
            usuario.setEmpresa(empresa);
        } else{
            throw new Exception("Error en crear la empresa");
        }
    }

    @Override
    public void builEstudiante(Estudiante estudiante) throws Exception{
        if(estudiante != null){
            usuario.setEstudiante(estudiante);
        } else{
            throw new Exception("Error en crear el estudiante");
        }
    }

    @Override
    public Usuario getUsuario() {
       return usuario;
    }

    @Override
    public void buildUsuario() {
      usuario = new Usuario();
    }

    

   
   
    
}
