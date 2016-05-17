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
public class Usuario {
    private Estudiante estudiante;
    private Empresa empresa;

    public Usuario() {
    }

    public Usuario(Estudiante estudiante, Empresa empresa) {
        this.estudiante = estudiante;
        this.empresa = empresa;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Usuario{" + "estudiante=" + estudiante + ", empresa=" + empresa + '}';
    }
    
    
}
