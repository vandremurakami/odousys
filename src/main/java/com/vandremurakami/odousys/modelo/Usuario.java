/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.modelo;

import java.io.Serializable;
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
 * @author vandre
 */

@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Usuario implements Serializable {

    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long codigo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_dentista")
    private Dentista dentista = null;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;
    
    public long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(long id) {
        this.codigo = id;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dent) {
        this.dentista = dent;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
