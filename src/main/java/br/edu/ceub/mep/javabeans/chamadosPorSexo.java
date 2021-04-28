package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class chamadosPorSexo implements Serializable{

    private String sexo;
    private Integer qtChamado;

    public chamadosPorSexo() {
    }

    public chamadosPorSexo(String sexo, Integer qtChamado) {
        this.sexo = sexo;
        this.qtChamado = qtChamado;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getQtChamado() {
        return qtChamado;
    }

    public void setQtChamado(Integer qtChamado) {
        this.qtChamado = qtChamado;
    }

    @Override
    public String toString() {
        return "chamadosPorSexo{" + "sexo=" + sexo + ", qtChamado=" + qtChamado + '}';
    }
    
    
    
}
