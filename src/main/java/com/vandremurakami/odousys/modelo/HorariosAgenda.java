/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.modelo;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Vandre
 */
@Entity
@Table(name = "horarios_agenda", schema = "public", uniqueConstraints =
        @UniqueConstraint(columnNames = "hora"))
public class HorariosAgenda implements java.io.Serializable {

    @Id
    @Column(name = "hora", unique = true, nullable = false)
    private LocalTime hora;
    
    public HorariosAgenda() {   
    }

    public LocalTime getHora() {
        return this.hora;
    }
    
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
}
