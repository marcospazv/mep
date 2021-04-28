package br.edu.ceub.mep.entity;

import br.edu.ceub.mep.javabeans.equipamentoPorSetor;
import br.edu.ceub.mep.javabeans.solicitantesPorLotacao;
import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@SqlResultSetMapping(name = "solicitantePorLotacao", classes = @ConstructorResult(
            targetClass = solicitantesPorLotacao.class,
            columns = {@ColumnResult(name = "lotacao", type = String.class)
                      ,@ColumnResult(name = "qtSolicitante", type = Integer.class)}))

@Entity
@Table(name = "tb_solicitante")
public class Solicitante implements Serializable{
    
    //CAMPOS DA TABELA
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_solicitante")
    private Integer id;
    
    @Column(name = "cpf", nullable = true, unique = true)
    private String cpf;
    
    @Column(name = "nome_solicitante", nullable = true, length = 50)
    private String nomeSolicitante;
    
    @Column(name = "sexo", nullable = true, length = 1)
    private String sexo;
    
    @Column(name = "dt_nascimento", nullable = true)
    private LocalDate dtNascimento;
    
    @Column(name = "excluido", nullable = true)
    private Boolean excluido;
    
    @Column(name = "senha", nullable = true, length = 128)
    private String senha;
    
    @Column(name = "salt", nullable = true, length = 32)
    private String salt;
    
    @ManyToOne
    @JoinColumn(name = "cd_lotacao")
    private Lotacao lotacao;
    
    @OneToMany(mappedBy = "solicitante")
    private List<Chamado> chamados;
    
  

    @ManyToMany
    @JoinTable(name = "rl_solicitante_equipamento", joinColumns = @JoinColumn(name = "cd_solicitante"), 
                                                    inverseJoinColumns = @JoinColumn(name = "cd_equipamento"))
    private List<Equipamento> equipamentos;
    
    //CONSTRUTORES
    
    public Solicitante() {
    }

    public Solicitante(String cpf, String nomeSolicitante, LocalDate dtNascimento, Boolean excluido, Lotacao lotacao) {
        this.cpf = cpf;
        this.nomeSolicitante = nomeSolicitante;
        this.dtNascimento = dtNascimento;
        this.excluido = excluido;
        this.lotacao = lotacao;
    }

    public Solicitante(String cpf, String nomeSolicitante, LocalDate dtNascimento, Boolean excluido, String senha, Lotacao lotacao) {
        this.cpf = cpf;
        this.nomeSolicitante = nomeSolicitante;
        this.dtNascimento = dtNascimento;
        this.excluido = excluido;
        this.senha = senha;
        this.lotacao = lotacao;
    }

    public Solicitante(String cpf, String nomeSolicitante, LocalDate dtNascimento, Boolean excluido, String senha, String salt, Lotacao lotacao) {
        this.cpf = cpf;
        this.nomeSolicitante = nomeSolicitante;
        this.dtNascimento = dtNascimento;
        this.excluido = excluido;
        this.senha = senha;
        this.salt = salt;
        this.lotacao = lotacao;
    }

    public Solicitante(String cpf, String nomeSolicitante, String sexo, LocalDate dtNascimento, Boolean excluido, String senha, String salt, Lotacao lotacao) {
        this.cpf = cpf;
        this.nomeSolicitante = nomeSolicitante;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
        this.excluido = excluido;
        this.senha = senha;
        this.salt = salt;
        this.lotacao = lotacao;
    }

    
    //GETTTERS/SETTTERS

    public Integer getId() {    
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Lotacao getLotacao() {
        return lotacao;
    }

    public void setLotacao(Lotacao lotacao) {
        this.lotacao = lotacao;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    
    
    
    //EQUALS

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.cpf);
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
        final Solicitante other = (Solicitante) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
    
}
