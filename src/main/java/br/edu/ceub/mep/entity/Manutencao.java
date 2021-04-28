package br.edu.ceub.mep.entity;

import br.edu.ceub.mep.javabeans.funcMaisManutencao;
import br.edu.ceub.mep.javabeans.manutencaoPorMes;
import br.edu.ceub.mep.javabeans.manutencaoPorSexo;
import br.edu.ceub.mep.javabeans.relatorioFuncionarioManutencoes;
import br.edu.ceub.mep.javabeans.relatorioManutencoesMensais;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;


@SqlResultSetMappings({
    @SqlResultSetMapping(name = "funcionariosMaisManutencoes", classes = @ConstructorResult(
            targetClass = relatorioFuncionarioManutencoes.class,
            columns = {
                @ColumnResult(name = "NomeFuncionario", type = String.class),
                @ColumnResult(name = "QtManutencoes", type = Integer.class)})),
    
    
      @SqlResultSetMapping(name = "ManutencoesMensais", classes = @ConstructorResult(
            targetClass = relatorioManutencoesMensais.class,
            columns = {
                @ColumnResult(name = "idManutencao", type = Integer.class),
                @ColumnResult(name = "dtManutencao", type = LocalDateTime.class),
                @ColumnResult(name = "descricao", type = String.class),
                @ColumnResult(name = "idChamado", type = Integer.class),
                @ColumnResult(name = "idEquipamento", type = Integer.class),
                @ColumnResult(name = "motivo", type = String.class),
                @ColumnResult(name = "nomeFuncionario", type = String.class)})),
      
      
      @SqlResultSetMapping(name = "funcMaisManutencoes", classes = @ConstructorResult(
            targetClass = funcMaisManutencao.class,
            columns = {
                @ColumnResult(name = "funcionario", type = String.class)})),
      
      @SqlResultSetMapping(name = "manutencaoPorMes", classes = @ConstructorResult(
            targetClass = manutencaoPorMes.class,
            columns = {
                @ColumnResult(name = "mes", type = String.class),
                @ColumnResult(name = "qtManutencao", type = Integer.class)})),
      
      @SqlResultSetMapping(name = "manutencaoPorSexo", classes = @ConstructorResult(
            targetClass = manutencaoPorSexo.class,
            columns = {
                @ColumnResult(name = "sexo", type = String.class),
                @ColumnResult(name = "qtManutencao", type = Integer.class)})),


})
@Entity 
@Table(name = "tb_manutencao")
public class Manutencao implements Serializable{
    
    //CAMPOS DA TABELA
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_manutencao")
    private Integer id;
    
    @Column(name = "descricao", nullable = true, length = 50)
    private String descricao;
    
    @Column(name = "dt_manutencao", nullable = true)
    private LocalDateTime dtManutencao;
    
    @ManyToOne
    @JoinColumn(name = "cd_funcionario")
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "cd_chamado")
    private Chamado chamado;
    
    //CONSTRUTORES
    
    public Manutencao() {
    }

    public Manutencao(String descricao, LocalDateTime dtManutencao, Funcionario funcionario, Chamado chamado) {
        this.descricao = descricao;
        this.dtManutencao = dtManutencao;
        this.funcionario = funcionario;
        this.chamado = chamado;
    }
    
     
    //GETTTERS/SETTTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDtManutencao() {
        return dtManutencao;
    }

    public void setDtManutencao(LocalDateTime dtManutencao) {
        this.dtManutencao = dtManutencao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }
    
    //EQUALS

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
        final Manutencao other = (Manutencao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
