/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.modelo;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Vandre
 */
@Entity
@Table(name = "preco", schema = "public", uniqueConstraints =
        @UniqueConstraint(columnNames = "codigo"))
public class Preco implements java.io.Serializable {
    
    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;
    
    @Column(name = "valor", nullable = false, precision = 30, scale = 2)
    BigDecimal valor;
    
    @Column(name = "nome", nullable = false)
    String nome;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_tabela_preco", nullable = false)
    private TabelaPreco tabela_preco;

    
    public Preco() {   
    }

    public int getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }   

    public BigDecimal getValor() {
        return this.valor.setScale(2);
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor.setScale(2);
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String n) {
        this.nome = n;
    }
    
    public TabelaPreco getTabelaPreco() {
        return this.tabela_preco;
    }
    
    public void setTabelaPreco(TabelaPreco tabela) {
        this.tabela_preco = tabela;
    }
        
    
}
