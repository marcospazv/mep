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
@Table(name = "tb_setor")
public class Setor implements Serializable{
    
    //CAMPOS DA TABELA
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_setor")
    private Integer id;
    
    @Column(name = "setor", nullable = true, length = 45)
    private String setor;
    
    @OneToMany(mappedBy = "setor")
    private List<Equipamento> equipamentos;

    //CONSTRUTORES
    
    public Setor() {
    }

    public Setor(String setor) {
        this.setor = setor;
    }
    
    
    
    //GETTTERS/SETTTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
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
        final Setor other = (Setor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
