package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class relatorioFuncionarioManutencoes implements Serializable{

    private String nomeFuncionario;
    private Integer qtManutencoes;

    public relatorioFuncionarioManutencoes() {
    }

    public relatorioFuncionarioManutencoes(String nomeFuncionario, Integer qtManutencoes) {
        this.nomeFuncionario = nomeFuncionario;
        this.qtManutencoes = qtManutencoes;
    }

    
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Integer getQtManutencoes() {
        return qtManutencoes;
    }

    public void setQtManutencoes(Integer qtManutencoes) {
        this.qtManutencoes = qtManutencoes;
    }

    @Override
    public String toString() {
        return "relatorioFuncionarioManutencoes{" + "nomeFuncionario=" + nomeFuncionario + ", qtManutencoes=" + qtManutencoes + '}';
    }
    
    
}
