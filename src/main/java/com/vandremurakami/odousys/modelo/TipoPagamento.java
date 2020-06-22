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
@Table(name = "tipo_pagamento", schema = "public", uniqueConstraints =
        @UniqueConstraint(columnNames = "codigo"))
public class TipoPagamento implements java.io.Serializable {

    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    private int codigo;
    
    @Column(name = "nome", nullable = false)
    private String nome;   
    
    public TipoPagamento() {   
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
