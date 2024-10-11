package model;

public class Paciente {
	public enum EstadoCastracao{
		FERTIL,
		CASTRADO,
		DESCONHECIDO
	}
	
	private int id;
	private String nome;
	private EstadoCastracao estadoCastracao;
	private int idade;
	private String raca;
	private String coloracao;
	private String especie;
	private Proprietario proprietario; 
	
	public Paciente(int id, String nome, String especie, String raca, int idade, String coloracao, EstadoCastracao estadoCastracao, Proprietario proprietario) {
		this.id = id;
		this.nome = nome;
		this.estadoCastracao = estadoCastracao;
		this.idade = idade;
		this.raca = raca;
		this.coloracao = coloracao;
		this.especie = especie;
		this.proprietario = proprietario;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public EstadoCastracao getEstadoCastracao() {
		return estadoCastracao;
	}
	public void setEstadoCastracao(EstadoCastracao estadoCastracao) {
		this.estadoCastracao = estadoCastracao;
	}
	
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	
	public String getColoracao() {
		return coloracao;
	}
	public void setColoracao(String coloracao) {
		this.coloracao = coloracao;
	}
	
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	@Override
	public String toString() {
		return "Paciente{" + "nome=" + nome + ", idade=" + idade + ", estado=" + estadoCastracao + ", raca=" + raca + ", coloracao=" + coloracao + ", especie=" + especie + '}';
	}

}
