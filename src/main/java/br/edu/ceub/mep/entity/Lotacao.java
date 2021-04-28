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
@Table(name = "tb_lotacao")
public class Lotacao implements Serializable{
    
    //CAMPOS DA TABELA
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_lotacao")
    private Integer id;
    
    @Column(name = "lotacao" , nullable = true, length = 45)
    private String lotacao;
    
    @OneToMany(mappedBy = "lotacao")
    private List<Solicitante> solicitantes;

    //CONSTRUTORES
    
    public Lotacao() {
    }

    public Lotacao(String lotacao) {
        this.lotacao = lotacao;
    }
    
    
    //GETTTERS/SETTTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public List<Solicitante> getSolicitantes() {
        return solicitantes;
    }

    public void setSolicitantes(List<Solicitante> solicitantes) {
        this.solicitantes = solicitantes;
    }
    
    //EQUALS

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Lotacao other = (Lotacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
