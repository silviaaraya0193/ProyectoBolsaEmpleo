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
public class Director {
    public Usuario crearUsuario(AbstractBuilder builder,Estudiante estudiante, Empresa empresa) throws Exception{
        if(builder == null){
            builder = new ConcreteBuilder();
        }
        builder.builEmpresa(empresa);
        builder.builEstudiante(estudiante);
        builder.buildUsuario();
        return builder.getUsuario();
    }
}
