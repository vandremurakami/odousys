/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "pagamento", schema = "public", uniqueConstraints =
        @UniqueConstraint(columnNames = "codigo"))
public class Pagamento implements java.io.Serializable {

    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;
    
    @Column(name = "valor", nullable = false, precision = 30, scale = 2)
    private BigDecimal valor;
    
    @Column(name = "data", nullable = false)
    private LocalDate data;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_paciente", nullable = false)
    private Paciente paciente;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_tipo_pagamento", nullable = false)
    private TipoPagamento tipoPagamento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_status")
    private Status status;

    @Column(name = "observacao")
    private String observacao;
    
    @Column(name = "data_pagamento")
    private LocalDate data_pagamento;
    
    public Pagamento() {   
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
    
    public LocalDate getData() {
        return this.data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente pac) {
        this.paciente = pac;
    }
    
    public TipoPagamento getTipoPagamento() {
        return this.tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipo) {
        this.tipoPagamento = tipo;
    }
    
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public void setObservacao(String o) {
        this.observacao = o;
    }
    
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setDataPagamento(LocalDate d) {
        this.data_pagamento = d;
    }
    
    public LocalDate getDataPagamento() {
        return this.data_pagamento;
    }
    
    public boolean isEmptyDataPagamento() {
        return (this.data_pagamento == null);
    }
    
}
