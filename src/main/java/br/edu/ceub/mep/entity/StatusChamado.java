package br.edu.ceub.mep.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_status_chamado")
public class StatusChamado implements Serializable{
    
    //CAMPOS DA TABELA
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_status")
    private Integer id;
    
    @Column(name = "status_chamado", nullable = true, length = 45)
    private String statusChamado;
    
    @OneToMany(mappedBy = "statusChamado")
    private List<Chamado> chamados;

    //CONSTRUTORES
    
    public StatusChamado() {
    }
    
    //GETTTERS/SETTTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(String statusChamado) {
        this.statusChamado = statusChamado;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
    
    // EQUALS

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StatusChamado other = (StatusChamado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
