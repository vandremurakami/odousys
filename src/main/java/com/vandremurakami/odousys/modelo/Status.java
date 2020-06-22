/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.modelo;

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
@Table(name = "status", schema = "public", uniqueConstraints =
        @UniqueConstraint(columnNames = "codigo"))
public class Status implements java.io.Serializable {

    @Id
    @Column(name = "codigo", nullable = false)
    private int codigo;
    
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;   
    
    public Status() {   
    }
    
    public Status(int codigo, String nome) {   
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
