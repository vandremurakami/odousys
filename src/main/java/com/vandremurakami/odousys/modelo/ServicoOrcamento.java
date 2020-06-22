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

/**
 *
 * @author Vandre
 */
@Entity
@Table(name = "servico_orcamento", schema = "public")
public class ServicoOrcamento implements java.io.Serializable {

    @Id
    @Column(name = "codigo", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_orcamento", nullable = false)
    private Orcamento orcamento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_preco", nullable = false)
    private Preco preco;
    
    @Column(name = "observacao")
    private String observacao;
    
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
    
    @Column(name = "porcentagem_desconto", precision = 30, scale = 2)
    private BigDecimal porcentagem_desconto = BigDecimal.ZERO ;
    
    @Column(name = "valor_desconto", precision = 30, scale = 2)
    private BigDecimal valor_desconto = BigDecimal.ZERO;
        
    
    public ServicoOrcamento() {   
    }

    public int getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Orcamento getOrcamento() {
        return this.orcamento;
    }
    
    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
    public Preco getPreco() {
        return this.preco;
    }

    public void setPreco(Preco preco) {
        this.preco = preco;
    }
    
    public String getObservacao() {
        return this.observacao;
    }

    public void setObservacao(String nome) {
        this.observacao = nome;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int q) {
        this.quantidade = q;
    }
    
    public BigDecimal getPorcentagemDesconto() {
        return this.porcentagem_desconto.setScale(2);
    }

    public void setPorcentagemDesconto(BigDecimal porcentagem) {
        this.porcentagem_desconto = porcentagem.setScale(2);
    }
    
    public BigDecimal getValorDesconto() {
        return this.valor_desconto.setScale(2);
    }

    public void setValorDesconto(BigDecimal valor) {
        this.valor_desconto = valor.setScale(2);
    }
    
    public BigDecimal getValorUnitatio() {
        return getPreco().getValor().subtract(getValorDesconto());
    }
    
    public BigDecimal getTotal() {
        return this.preco.getValor().subtract(getValorDesconto()).multiply(BigDecimal.valueOf(getQuantidade()));
    }
    
}