package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class chamadosPorMes implements Serializable{

    private String mes;
    private Integer qtdChamado;

    public chamadosPorMes() {
    }

    public chamadosPorMes(String mes, Integer qtdChamado) {
        this.mes = mes;
        this.qtdChamado = qtdChamado;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getQtdChamado() {
        return qtdChamado;
    }

    public void setQtdChamado(Integer qtdChamado) {
        this.qtdChamado = qtdChamado;
    }

    @Override
    public String toString() {
        return "chamadosPorMes{" + "mes=" + mes + ", qtdChamado=" + qtdChamado + '}';
    }
    
    
    
}
