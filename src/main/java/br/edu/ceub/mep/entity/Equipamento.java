package br.edu.ceub.mep.entity;

import br.edu.ceub.mep.javabeans.equipamentoPorSetor;
import br.edu.ceub.mep.javabeans.relatorioSolicitanteChamado;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

 @SqlResultSetMapping(name = "equipamentoPorSetor", classes = @ConstructorResult(
            targetClass = equipamentoPorSetor.class,
            columns = {@ColumnResult(name = "setor", type = String.class)
                      ,@ColumnResult(name = "qtEquipamento", type = Integer.class)}))

@Entity
@Table(name = "tb_equipamento")
public class Equipamento implements Serializable{
    
    //CAMPOS DA TABELA
    
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "cd_equipamento")
    private Integer id;
    
    @Column(name = "nome_equipamento", nullable = true, length = 45)
    private String nomeEquipamento;
    
    @Column(name = "excluido", nullable = true)
    private Boolean excluido;
    
    @ManyToOne
    @JoinColumn(name = "cd_setor")
    private Setor setor;
    
    @OneToMany(mappedBy = "equipamento")
    private List<Chamado> chamados;
    
    @ManyToMany(mappedBy = "equipamentos")
    private List<Solicitante> solicitantes;
    
    @ManyToOne
    @JoinColumn(name = "cd_funcionario")
    private Funcionario funcionario;

    //CONSTRUTORES
    
    public Equipamento() {
    }

    public Equipamento(String nomeEquipamento, Boolean excluido, Setor setor) {
        this.nomeEquipamento = nomeEquipamento;
        this.excluido = excluido;
        this.setor = setor;
    }

    public Equipamento(String nomeEquipamento, Boolean excluido, Setor setor, Funcionario funcionario) {
        this.nomeEquipamento = nomeEquipamento;
        this.excluido = excluido;
        this.setor = setor;
        this.funcionario = funcionario;
    }

    
    //GETTTERS/SETTTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<Solicitante> getSolicitantes() {
        return solicitantes;
    }

    public void setSolicitantes(List<Solicitante> solicitantes) {
        this.solicitantes = solicitantes;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
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
        final Equipamento other = (Equipamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   
    
    
    
}
