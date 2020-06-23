/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.modelo;

/**
 *
 * @author Vandre
 */
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "agenda", schema = "public", uniqueConstraints =
        @UniqueConstraint(columnNames = {"data", "hora","cod_dentista"}))
public class Agenda implements java.io.Serializable {

    @Id
    @Column(name = "data", nullable = false)
    private LocalDate data;
    
    @Column(name = "hora", nullable = false)
    private LocalTime hora;
    
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_dentista", nullable = false)    
    private Dentista dentista;
    
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_paciente", nullable = false)    
    private Paciente paciente;
    
    @Column(name = "anotacao")
    private String anotacao;

    @Column(name = "feito")
    private boolean check;
    
    public Agenda() {   
    }

    public Agenda(LocalDate data, LocalTime hora, Dentista dentista, String anotacao) {
        this.data = data;
        this.hora = hora;
        this.dentista = dentista;
        this.anotacao = anotacao;
    }

    public LocalDate getData() {
        return this.data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public LocalTime getHora() {
        return this.hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    public Dentista getDentista() {
        return this.dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }
    
    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente pac) {
        this.paciente = pac;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }
    
    public String getAnotacao() {
        return this.anotacao;
    }
    
    public void setCheck(boolean c) {
        this.check = c;
    }
    
    public boolean getCheck() {
        return this.check;
    }
    
}
