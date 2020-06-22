/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Vandre
 */
@Entity
@Table(name = "orcamento", schema = "public", uniqueConstraints =
        @UniqueConstraint(columnNames = "codigo"))
public class Orcamento implements java.io.Serializable {

    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;
    
    @Column(name = "data", nullable = false)
    private LocalDate data;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_dentista", nullable = false)
    private Dentista dentista;
    
    @OneToMany(mappedBy = "orcamento", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ServicoOrcamento> listaServico;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_paciente", nullable = false)
    private Paciente paciente;
    
    @Column(name = "porcentagem_desconto", precision = 30, scale = 2)
    private BigDecimal porcentagem_desconto = BigDecimal.ZERO;
    
    @Column(name = "valor_desconto", precision = 30, scale = 2)
    private BigDecimal valor_desconto = BigDecimal.ZERO;
    
    @Column(name = "valor_final", precision = 30, scale = 2)
    private BigDecimal valor_final;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_status", nullable = false)
    private Status status;
    
    @Column(name = "observacao")
    private String observacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_tabela_preco", nullable = false)
    private TabelaPreco tabelaPreco;
    
    public Orcamento() { 
    }

    public int getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public Dentista getDentista() {
        return this.dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }
    
    public List<ServicoOrcamento> getServicoOrcamento() {
        return this.listaServico;
    }
    
    public void setServicoOrcamento(List<ServicoOrcamento> servicos) {
        this.listaServico = servicos;
    }
    
    public void setPaciente(Paciente pac) {
        this.paciente = pac;
    }
    
    public Paciente getPaciente() {
        return this.paciente;
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

    public BigDecimal getValorFinal() {
        return this.valor_final.setScale(2);
    }

    public void setValorFinal(BigDecimal valor) {
        this.valor_final = valor.setScale(2);
    }
    
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(String obs) {
        this.observacao = obs;
    }
    
    public TabelaPreco getTabelaPreco() {
        return this.tabelaPreco;
    }
    
    public void setTabelaPreco(TabelaPreco tabela) {
        this.tabelaPreco = tabela;
    }
    
}