package br.edu.ceub.mep.javabeans;

import java.io.Serializable;


public class funcMaisManutencao implements Serializable{

    private String funcionario;

    public funcMaisManutencao() {
    }

    public funcMaisManutencao(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "funcMaisManutencao{" + "funcionario=" + funcionario + '}';
    }
    
    
    
}
