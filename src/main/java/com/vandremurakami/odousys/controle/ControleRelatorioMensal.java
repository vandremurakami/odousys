/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.PagamentoDAO;
import com.vandremurakami.odousys.dao.OrcamentoDAO;
import com.vandremurakami.odousys.dao.StatusDAO;
import com.vandremurakami.odousys.dao.TipoPagamentoDAO;
import java.math.BigDecimal;
import java.util.List;
import com.vandremurakami.odousys.modelo.Orcamento;
import com.vandremurakami.odousys.modelo.Pagamento;

/**
 *
 * @author vandr
 */
public class ControleRelatorioMensal {
    
    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();
    private final OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    
    private List<Pagamento> listaPagamento;
    private List<Orcamento> listaOrcamento;
    
    private int mes;
    private int ano;
    
    public ControleRelatorioMensal() {}
    
    public void setData(int m, int a) {
        this.mes = m;
        this.ano = a;
        listaPagamento = pagamentoDAO.BuscarPagamentos(mes, ano);
        listaOrcamento = orcamentoDAO.BuscaOrcamento(mes, ano);
    }
    
    public int numeroPagamentosCheque() {        
        int numeroPagamentos = 0;
        
        for(int i = 0; i < listaPagamento.size(); i++) { 
            Pagamento p = listaPagamento.get(i);
            
            if ( (p.getTipoPagamento().getCodigo() == TipoPagamentoDAO.TIPO_PAGAMENTO_CHEQUE) && 
                    (p.getStatus().getCodigo() == StatusDAO.STATUS_APROVADO) ) {
                numeroPagamentos ++;
            }

        }
        
        return numeroPagamentos;
    }
    
    public int numeroPagamentosDinheiro() {
        int numeroPagamentos = 0;
        
        for(int i = 0; i < listaPagamento.size(); i++) { 
            Pagamento p = listaPagamento.get(i);
            
            if ( (p.getTipoPagamento().getCodigo() == TipoPagamentoDAO.TIPO_PAGAMENTO_DINHEIRO) && 
                    (p.getStatus().getCodigo() == StatusDAO.STATUS_APROVADO) ) {
                numeroPagamentos ++;
            }

        }
        
        return numeroPagamentos;
    }
    
    public BigDecimal valorPagamentosCheques() {
        BigDecimal valor = BigDecimal.ZERO;
        
        for(int i = 0; i < listaPagamento.size(); i++) { 
            Pagamento p = listaPagamento.get(i);
            
            if ( (p.getTipoPagamento().getCodigo() == TipoPagamentoDAO.TIPO_PAGAMENTO_CHEQUE) && 
                    (p.getStatus().getCodigo() == StatusDAO.STATUS_APROVADO) ) {
                valor = valor.add(p.getValor());
            }

        }
        
        return valor.setScale(2);
    }
    
    public BigDecimal valorPagamentosDinheiro() {
        BigDecimal valor = BigDecimal.ZERO;
        
        for(int i = 0; i < listaPagamento.size(); i++) { 
            Pagamento p = listaPagamento.get(i);
            
            if ( (p.getTipoPagamento().getCodigo() == TipoPagamentoDAO.TIPO_PAGAMENTO_DINHEIRO) && 
                    (p.getStatus().getCodigo() == StatusDAO.STATUS_APROVADO) ) {
                valor = valor.add(p.getValor());
            }

        }
        
        return valor.setScale(2);
    }

    public BigDecimal valorTotal() {
        BigDecimal valor = BigDecimal.ZERO;
        
        for(int i = 0; i < listaPagamento.size(); i++) { 
            Pagamento p = listaPagamento.get(i);
            if ((p.getStatus().getCodigo() == StatusDAO.STATUS_APROVADO) ) {
                valor = valor.add(p.getValor());
            }
        }
        
        return valor.setScale(2);
    }
    
    public int numeroOrcamentos() {
        int numero = 0;
        
        for(int i = 0; i < listaOrcamento.size(); i++) { 
            Orcamento o = listaOrcamento.get(i);
            if ( (o.getStatus().getCodigo() == StatusDAO.STATUS_APROVADO) ) {
                numero++;
            }
        }
        return numero;
    }
    
    public BigDecimal valorOrcamentos() {
        BigDecimal valor = BigDecimal.ZERO;
        
        for(int i = 0; i < listaOrcamento.size(); i++) { 
            Orcamento o = listaOrcamento.get(i);
            if ( (o.getStatus().getCodigo() == StatusDAO.STATUS_APROVADO) ) {
                valor = valor.add(o.getValorFinal());
            }
        }
        
        return valor.setScale(2);        
    }
    
    public BigDecimal Saldo() {
        return valorTotal().subtract(valorOrcamentos());
    }
}
