package model;

import java.time.LocalDateTime;

public class Agendamento {

    public enum StatusAgendamento {
        AGENDADO,
        CONCLUIDO,
        CANCELADO
    }

    private int id;
    private Paciente paciente;
    private LocalDateTime dataHora;
    private String servico;
    private StatusAgendamento status;
    private Veterinario veterinario;

    public Agendamento(int id, Paciente paciente, LocalDateTime dataHora, String servico, StatusAgendamento status, Veterinario veterinario) {
        this.id = id;
        this.paciente = paciente;
        this.dataHora = dataHora;
        this.servico = servico;
        this.status = status;
        this.veterinario = veterinario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }
    
	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}
}
