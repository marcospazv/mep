package br.edu.ceub.mep.javabeans;

import java.io.Serializable;
import java.time.LocalDateTime;


public class relatorioManutencoesMensais implements Serializable{

    private Integer idManutencao;
    private LocalDateTime dtManutencao;
    private String descricao;
    private Integer idChamado;
    private Integer idEquipamento;
    private String motivo;
    private String nomeFuncionario;

    public relatorioManutencoesMensais() {
    }

    public relatorioManutencoesMensais(Integer idManutencao, LocalDateTime dtManutencao, String descricao, Integer idChamado, Integer idEquipamento, String motivo, String nomeFuncionario) {
        this.idManutencao = idManutencao;
        this.dtManutencao = dtManutencao;
        this.descricao = descricao;
        this.idChamado = idChamado;
        this.idEquipamento = idEquipamento;
        this.motivo = motivo;
        this.nomeFuncionario = nomeFuncionario;
    }
    

    public Integer getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(Integer idManutencao) {
        this.idManutencao = idManutencao;
    }

    public LocalDateTime getDtManutencao() {
        return dtManutencao;
    }

    public void setDtManutencao(LocalDateTime dtManutencao) {
        this.dtManutencao = dtManutencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Integer idChamado) {
        this.idChamado = idChamado;
    }

    public Integer getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(Integer idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    @Override
    public String toString() {
        return "relatorioManutencoesMensais{" + "idManutencao=" + idManutencao + ", dtManutencao=" + dtManutencao + ", descricao=" + descricao + ", idChamado=" + idChamado + ", idEquipamento=" + idEquipamento + ", motivo=" + motivo + ", nomeFuncionario=" + nomeFuncionario + '}';
    }
    
    
}
