package model;

import java.util.List;

public class Historico {

    private int id;
    private Paciente paciente;
    private List<String> vacinas;
    private List<String> doencas;
    private String peso;
    private String observacoes;

    public Historico(int id, Paciente paciente, List<String> vacinas, List<String> doencas, String peso, String observacoes) {
        this.id = id;
        this.paciente = paciente;
        this.vacinas = vacinas;
        this.doencas = doencas;
        this.peso = peso;
        this.observacoes = observacoes;
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

    public List<String> getVacinas() {
        return vacinas;
    }

    public void setVacinas(List<String> vacinas) {
        this.vacinas = vacinas;
    }

    public List<String> getDoencas() {
        return doencas;
    }

    public void setDoencas(List<String> doencas) {
        this.doencas = doencas;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void registrar() {
        System.out.println("Histórico registrado com sucesso.");
    }

    public void visualizar() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Historico{" +
                "paciente=" + paciente.toString() +
                ", vacinas=" + vacinas +
                ", doencas=" + doencas +
                ", peso='" + peso + '\'' +
                ", observacoes=" + observacoes +
                '}';
    }
}
