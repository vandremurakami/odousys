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
@Table(name = "dentista", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Dentista implements java.io.Serializable {

    @Id
    @Column(name = "codigo", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;
    
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    
    @Column(name = "cpf")
    private String cpf;
    
    @Column(name = "cro")
    private String cro;
    
    @Column(name = "endereco")
    private String endereco;
    
    @Column(name = "cidade")
    private String cidade;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "cep")
    private String cep;
    
    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "celular")
    private String celular;
    
    @Column(name = "email")
    private String email;
    
    public Dentista() {   
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
    
    public String getCPF() {
        return this.cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
    
    public String getCRO() {
        return this.cro;
    }

    public void setCRO(String cpf) {
        this.cro = cro;
    }
    
    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCEP() {
        return this.cep;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }
    
    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}

