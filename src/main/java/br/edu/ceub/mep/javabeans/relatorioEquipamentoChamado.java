package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class relatorioEquipamentoChamado implements Serializable{

    private String nomeEquipamento;
    private Integer qtChamados;

    public relatorioEquipamentoChamado() {
    }

    public relatorioEquipamentoChamado(String nomeEquipamento, Integer qtChamados) {
        this.nomeEquipamento = nomeEquipamento;
        this.qtChamados = qtChamados;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public Integer getQtChamados() {
        return qtChamados;
    }

    public void setQtChamados(Integer qtChamados) {
        this.qtChamados = qtChamados;
    }

    @Override
    public String toString() {
        return "relatorioEquipamentoChamado{" + "nomeEquipamento=" + nomeEquipamento + ", qtChamados=" + qtChamados + '}';
    }
    
    
    
}
