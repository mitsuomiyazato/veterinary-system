package model;

public class Proprietario {
	private int id;
	private String cpf;
	private String telefone;
	private String nomeCompleto;
	private String endereco;
	
    public Proprietario(int id, String nomeCompleto, String cpf, String telefone, String endereco) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;  
        this.cpf = cpf;                     
        this.telefone = telefone;          
        this.endereco = endereco;           
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
    public String toString() {
        return "Proprietario{" +
                "nome=" + nomeCompleto +
                ", cpf=" + cpf +
                ", telefone=" + telefone +
                ", endereco=" + endereco +
                '}';
    }
}
