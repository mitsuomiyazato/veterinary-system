package model;

import java.time.LocalDateTime;
import java.util.List;

public class ReceitaMedica {

    private int id;
    private Paciente paciente;
    private List<String> medicamentos;
    private LocalDateTime dataEmissao;
    private String observacoes;
    private Veterinario veterinario;

    public ReceitaMedica(int id, Paciente paciente, List<String> medicamentos, LocalDateTime dataEmissao, String observacoes, Veterinario veterinario) {
        this.id = id;
        this.paciente = paciente;
        this.medicamentos = medicamentos;
        this.dataEmissao = dataEmissao;
        this.observacoes = observacoes;
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

    public List<String> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<String> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
    public void imprimir() {
    	System.out.println("Gerando Arquivo PDF");
    }
    
    public String getObservacoes() {
    	return observacoes;
    }
     public void setObservacoes(String observacoes) {
    	 this.observacoes = observacoes;
     }

 	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}
	
    @Override
    public String toString() {
        return "ReceitaMedica{" +
                "paciente=" + paciente.getNome() +
                ", medicamentos=" + medicamentos +
                ", dataEmissao=" + dataEmissao +
                ", observacoes=" + observacoes +
                ", veterinario=" + veterinario +
                '}';
    }

}
