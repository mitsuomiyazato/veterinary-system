package model;

public class Veterinario {

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private boolean is_active;

    public Veterinario(int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.setIs_active(true);
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
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean Is_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
    @Override
    public String toString() {
        return "Veterinario{" +
                "nome=" + nome +
                ", email=" + email +
                ", telefone=" + telefone +
                '}';
    }
}
