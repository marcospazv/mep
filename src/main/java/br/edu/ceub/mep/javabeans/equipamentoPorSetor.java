package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class equipamentoPorSetor implements Serializable {

    private String setor;
    private Integer qtEquipamento;

    public equipamentoPorSetor() {
    }

    public equipamentoPorSetor(String setor, Integer qtEquipamento) {
        this.setor = setor;
        this.qtEquipamento = qtEquipamento;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Integer getQtEquipamento() {
        return qtEquipamento;
    }

    public void setQtEquipamento(Integer qtEquipamento) {
        this.qtEquipamento = qtEquipamento;
    }

    @Override
    public String toString() {
        return "equipamentoPorSetor{" + "setor=" + setor + ", qtEquipamento=" + qtEquipamento + '}';
    }
    
    
    
}
