/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Builder;

import models.FormularioEstudiante;

/**
 *
 * @author viccr
 */
public interface InterfazBuilder {
     public abstract FormularioEstudiante construyeEstudiante(Object obj);
}
