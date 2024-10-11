package model;

import java.time.LocalDate;

public class Fatura {

    public enum StatusPagamento {
        PAGO,
        PENDENTE,
        EM_ATRASO
    }

    private int id; 
    private Proprietario proprietario;
    private double valorTotal;
    private StatusPagamento status;
    private LocalDate dataVencimento;

    public Fatura(int id, Proprietario proprietario, double valorTotal, StatusPagamento status, LocalDate dataVencimento) {
        this.id = id; 
        this.proprietario = proprietario;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataVencimento = dataVencimento;
    }

    public int getId() {
        return id;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void gerarFatura() {
        System.out.println("Fatura gerada para o cliente: " + proprietario.getNomeCompleto());
        System.out.println("Valor Total: R$" + valorTotal);
        System.out.println("Status do Pagamento: " + status);
        System.out.println("Data de Vencimento: " + dataVencimento);
    }

    public void gerarRelatorio() {
        System.out.println("Relatório da Fatura:");
        System.out.println("Cliente: " + proprietario.getNomeCompleto());
        System.out.println("Valor Total: R$" + valorTotal);
        System.out.println("Status do Pagamento: " + status);
        System.out.println("Data de Vencimento: " + dataVencimento);
    }

    @Override
    public String toString() {
        return "Faturamento{" +
                "cliente=" + proprietario.getNomeCompleto() +
                ", valorTotal=" + valorTotal +
                ", status=" + status +
                ", dataVencimento=" + dataVencimento +
                '}';
    }
}
