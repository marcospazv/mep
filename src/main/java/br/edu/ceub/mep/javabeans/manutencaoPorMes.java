package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class manutencaoPorMes implements Serializable{

    private String mes;
    private Integer qtManutencao;

    public manutencaoPorMes() {
    }

    public manutencaoPorMes(String mes, Integer qtManutencao) {
        this.mes = mes;
        this.qtManutencao = qtManutencao;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getQtManutencao() {
        return qtManutencao;
    }

    public void setQtManutencao(Integer qtManutencao) {
        this.qtManutencao = qtManutencao;
    }

    @Override
    public String toString() {
        return "manutencaoPorMes{" + "mes=" + mes + ", qtManutencao=" + qtManutencao + '}';
    }
    
    
}
