package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class solicMaisChamao implements Serializable{

    private String solicitante;

    public solicMaisChamao() {
    }

    public solicMaisChamao(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    @Override
    public String toString() {
        return "solicMaisChamao{" + "solicitante=" + solicitante + '}';
    }
    
    
}
