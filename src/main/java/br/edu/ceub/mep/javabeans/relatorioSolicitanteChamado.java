package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class relatorioSolicitanteChamado implements  Serializable{

    private String nomeSolicitante;
    private Integer qtChamados;

    public relatorioSolicitanteChamado() {
    }

    public relatorioSolicitanteChamado(String nomeSolicitante, Integer qtChamados) {
        this.nomeSolicitante = nomeSolicitante;
        this.qtChamados = qtChamados;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public Integer getQtChamados() {
        return qtChamados;
    }

    public void setQtChamados(Integer qtChamados) {
        this.qtChamados = qtChamados;
    }

    @Override
    public String toString() {
        return "relatorioSolicitanteChamado{" + "nomeSolicitante=" + nomeSolicitante + ", qtChamados=" + qtChamados + '}';
    }
    
    
    
}
