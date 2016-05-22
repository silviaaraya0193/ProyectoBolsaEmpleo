/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author EXDER
 */
public class CreadorUsuario {
    InterfaceCreacionUsuario usuario;
      public InterfaceCreacionUsuario creadorUsuario(String tipo,String nombre,String correo,String contrasenia,int telefono,int cfi){
          if(tipo.equals("Empresa")){
             usuario = new RegistroEmpresa(nombre, correo, contrasenia, telefono,cfi);
          }
          else if(tipo.equals("Usuario")){
              usuario= new RegistroUsuario(nombre, correo, contrasenia, telefono);
          } 
          return usuario;
          
      }
}
