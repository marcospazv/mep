package br.edu.ceub.mep.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "tb_funcionario")
public class Funcionario implements Serializable {

    //CAMPOS DA TABELA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_funcionario")
    private Integer id;

    @Column(name = "cpf", nullable = true, unique = true)
    private String cpf;

    @Column(name = "nome_funcionario", nullable = true, length = 50)
    private String nomeFuncionario;

    @Column(name = "sexo", nullable = true, length = 1)
    private String sexo;
    
    @Column(name = "dt_nascimento", nullable = true)
    private LocalDate dtNascimento;

    @Column(name = "excluido", nullable = true)
    private Boolean excluido;

    @Column(name = "adm", nullable = true)
    private Boolean adm;
    
    @Column(name = "senha", nullable = true, length = 128)
    private String senha;
    
    @Column(name = "salt", nullable = true, length = 32)
    private String salt;

    @OneToMany(mappedBy = "funcionario")
    private List<Equipamento> equipamentos;
    
    @OneToMany(mappedBy = "funcionario")
    private List<Chamado> chamados;

    @OneToMany(mappedBy = "funcionario")
    private List<Manutencao> manutencoes;
   

    //CONSTRUTORES
    public Funcionario() {
    }


    public Funcionario(String cpf, String nomeFuncionario, LocalDate dtNascimento, Boolean excluido, Boolean adm) {
        this.cpf = cpf;
        this.nomeFuncionario = nomeFuncionario;
        this.dtNascimento = dtNascimento;
        this.excluido = excluido;
        this.adm = adm;
    }

    public Funcionario(String cpf, String nomeFuncionario, LocalDate dtNascimento, Boolean excluido, Boolean adm, String senha) {
        this.cpf = cpf;
        this.nomeFuncionario = nomeFuncionario;
        this.dtNascimento = dtNascimento;
        this.excluido = excluido;
        this.adm = adm;
        this.senha = senha;
    }

    public Funcionario(String cpf, String nomeFuncionario, LocalDate dtNascimento, Boolean excluido, Boolean adm, String senha, String salt) {
        this.cpf = cpf;
        this.nomeFuncionario = nomeFuncionario;
        this.dtNascimento = dtNascimento;
        this.excluido = excluido;
        this.adm = adm;
        this.senha = senha;
        this.salt = salt;
    }

    public Funcionario(String cpf, String nomeFuncionario, String sexo, LocalDate dtNascimento, Boolean excluido, Boolean adm, String senha, String salt) {
        this.cpf = cpf;
        this.nomeFuncionario = nomeFuncionario;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
        this.excluido = excluido;
        this.adm = adm;
        this.senha = senha;
        this.salt = salt;
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

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }
    

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
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
        int hash = 7;
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
