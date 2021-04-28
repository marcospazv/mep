package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class solicitantesPorLotacao implements Serializable{

    private String lotacao;
    private Integer qtSolicitante;

    public solicitantesPorLotacao() {
    }

    public solicitantesPorLotacao(String lotacao, Integer qtSolicitante) {
        this.lotacao = lotacao;
        this.qtSolicitante = qtSolicitante;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public Integer getQtSolicitante() {
        return qtSolicitante;
    }

    public void setQtSolicitante(Integer qtSolicitante) {
        this.qtSolicitante = qtSolicitante;
    }

    @Override
    public String toString() {
        return "solicitantesPorLotacao{" + "lotacao=" + lotacao + ", qtSolicitante=" + qtSolicitante + '}';
    }
    
    
    
}
