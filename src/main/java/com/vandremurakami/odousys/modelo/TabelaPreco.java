/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Vandre
 */
@Entity
@Table(name = "tabela_preco", schema = "public", uniqueConstraints =
        @UniqueConstraint(columnNames = "codigo"))
public class TabelaPreco implements java.io.Serializable {

    @Id
    @Column(name = "codigo", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;
    
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    
    @Column(name = "ativo", nullable = false)
    private boolean ativo = false;
    
    public TabelaPreco() {   
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
    
    public boolean getAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(boolean a) {
        this.ativo = a;
    }

}
