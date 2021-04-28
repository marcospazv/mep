package br.edu.ceub.mep.javabeans;

import java.io.Serializable;
import java.time.LocalDateTime;


public class relatorioChamadosMensais implements Serializable{

    private Integer idChamado;
    private String motivo;
    private LocalDateTime dtChamado;
    private String status;
    private String solicitante;
    private String funcionario;
    private String equipamento;
    private Integer cdEquipamento;

    public relatorioChamadosMensais() {
    }

    public relatorioChamadosMensais(Integer idChamado, String motivo, LocalDateTime dtChamado, String status, String solicitante, String funcionario, String equipamento, Integer cdEquipamento) {
        this.idChamado = idChamado;
        this.motivo = motivo;
        this.dtChamado = dtChamado;
        this.status = status;
        this.solicitante = solicitante;
        this.funcionario = funcionario;
        this.equipamento = equipamento;
        this.cdEquipamento = cdEquipamento;
    }

    public Integer getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Integer idChamado) {
        this.idChamado = idChamado;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public Integer getCdEquipamento() {
        return cdEquipamento;
    }

    public void setCdEquipamento(Integer cdEquipamento) {
        this.cdEquipamento = cdEquipamento;
    }

    @Override
    public String toString() {
        return "relatorioChamadosMensais{" + "idChamado=" + idChamado + ", motivo=" + motivo + ", dtChamado=" + dtChamado + ", status=" + status + ", solicitante=" + solicitante + ", funcionario=" + funcionario + ", equipamento=" + equipamento + ", cdEquipamento=" + cdEquipamento + '}';
    }
    
    
}
