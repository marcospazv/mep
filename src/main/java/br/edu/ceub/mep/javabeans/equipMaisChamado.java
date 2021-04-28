package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class equipMaisChamado implements Serializable{

    private String equipamento;

    public equipMaisChamado() {
    }

    public equipMaisChamado(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public String toString() {
        return "equipMaisChamado{" + "equipamento=" + equipamento + '}';
    }
    
    
}
