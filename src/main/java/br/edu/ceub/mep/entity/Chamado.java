package br.edu.ceub.mep.entity;

import br.edu.ceub.mep.javabeans.chamadosPorMes;
import br.edu.ceub.mep.javabeans.chamadosPorSexo;
import br.edu.ceub.mep.javabeans.equipMaisChamado;
import br.edu.ceub.mep.javabeans.relatorioChamadosMensais;
import br.edu.ceub.mep.javabeans.relatorioEquipamentoChamado;
import br.edu.ceub.mep.javabeans.relatorioSolicitanteChamado;
import br.edu.ceub.mep.javabeans.solicMaisChamao;
import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;


@SqlResultSetMappings({


    @SqlResultSetMapping(name = "solicitanteMaisChamados", classes = @ConstructorResult(
            targetClass = relatorioSolicitanteChamado.class,
            columns = {@ColumnResult(name = "NomeSolicitante", type = String.class)
                      ,@ColumnResult(name = "QtChamados", type = Integer.class)})),


        
        @SqlResultSetMapping(name = "equipamentosMaisChamados", classes = @ConstructorResult(
                targetClass = relatorioEquipamentoChamado.class,
                columns = {@ColumnResult(name = "NomeEquipamento", type = String.class)
                          ,@ColumnResult(name = "QtChamados", type = Integer.class)})),
        

         
        @SqlResultSetMapping(name = "chamadosMensais", classes = @ConstructorResult(
                targetClass = relatorioChamadosMensais.class,
                columns = {@ColumnResult(name = "idChamado", type = Integer.class)
                          ,@ColumnResult(name = "motivo", type = String.class)
                          ,@ColumnResult(name = "dtChamado", type = LocalDateTime.class)
                          ,@ColumnResult(name = "status", type = String.class)
                          ,@ColumnResult(name = "solicitante", type = String.class)
                          ,@ColumnResult(name = "funcionario", type = String.class)
                          ,@ColumnResult(name = "equipamento", type = String.class)
                          ,@ColumnResult(name = "cdEquipamento", type = Integer.class)})),
        
      
         @SqlResultSetMapping(name = "equipMaisChamado", classes = @ConstructorResult(
                targetClass = equipMaisChamado.class,
                columns = {@ColumnResult(name = "equipamento", type = String.class)})),
        
         @SqlResultSetMapping(name = "solicMaisChamado", classes = @ConstructorResult(
                targetClass = solicMaisChamao.class,
                columns = {@ColumnResult(name = "solicitante", type = String.class)})),
        

          @SqlResultSetMapping(name = "chamadosPorMes", classes = @ConstructorResult(
                targetClass = chamadosPorMes.class,
                columns = {@ColumnResult(name = "mes", type = String.class)
                          ,@ColumnResult(name = "qtdChamado", type = Integer.class)})),
         
          
              @SqlResultSetMapping(name = "chamadosPorSexo", classes = @ConstructorResult(
                targetClass = chamadosPorSexo.class,
                columns = {@ColumnResult(name = "sexo", type = String.class)
                          ,@ColumnResult(name = "qtChamado", type = Integer.class)})),
         
})
  

@Entity
@Table(name = "tb_chamado")
public class Chamado implements Serializable {

    //CAMPOS DA TABELA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_chamado")
    private Integer id;

    @Column(name = "motivo", nullable = true, length = 45)
    private String motivo;

    @Column(name = "dt_chamado", nullable = true)
    private LocalDateTime dtChamado;

    @ManyToOne
    @JoinColumn(name = "cd_equipamento")
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "cd_status")
    private StatusChamado statusChamado;

    @OneToMany(mappedBy = "chamado")
    private List<Manutencao> manutencoes;

    @ManyToOne
    @JoinColumn(name = "cd_solicitante")
    private Solicitante solicitante;

    @ManyToOne
    @JoinColumn(name = "cd_funcionario")
    private Funcionario funcionario;

    //CONSTRUTORES
    public Chamado() {
    }

    public Chamado(String motivo, LocalDateTime dtChamado, Equipamento equipamento, StatusChamado statusChamado) {
        this.motivo = motivo;
        this.dtChamado = dtChamado;
        this.equipamento = equipamento;
        this.statusChamado = statusChamado;
    }

    public Chamado(String motivo, LocalDateTime dtChamado, Equipamento equipamento, StatusChamado statusChamado, Solicitante solicitante) {
        this.motivo = motivo;
        this.dtChamado = dtChamado;
        this.equipamento = equipamento;
        this.statusChamado = statusChamado;
        this.solicitante = solicitante;
    }

    //GETTTERS/SETTTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getDtChamado() {
        return dtChamado;
    }

    public void setDtChamado(LocalDateTime dtChamado) {
        this.dtChamado = dtChamado;
    }

    public StatusChamado getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(StatusChamado statusChamado) {
        this.statusChamado = statusChamado;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
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
        final Chamado other = (Chamado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
