package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class manutencaoPorSexo implements Serializable{
    private String sexo;
    private Integer qtManutencao;

    public manutencaoPorSexo() {
    }

    public manutencaoPorSexo(String sexo, Integer qtManutencao) {
        this.sexo = sexo;
        this.qtManutencao = qtManutencao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getQtManutencao() {
        return qtManutencao;
    }

    public void setQtManutencao(Integer qtManutencao) {
        this.qtManutencao = qtManutencao;
    }

    @Override
    public String toString() {
        return "manutencaoPorSexo{" + "sexo=" + sexo + ", qtManutencao=" + qtManutencao + '}';
    }
    
    
    
}
