package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Fatura.StatusPagamento;

public class Main {
	public static void main(String[] args) {
		
		
		//ProprietarioDAO.getInstance().create("Mitsuo Miyazato", "43614218899", "972476503", "Rua Alexandre Martins");
		//ProprietarioDAO.getInstance().create("Gabriel Emerenciano", "12389123", "219301232", "Rua Gabriel Emerenciano");
		Proprietario p1 = ProprietarioDAO.getInstance().retrieveById(1);
		//Proprietario p2 = ProprietarioDAO.getInstance().retrieveById(2);
		//ProprietarioDAO.getInstance().delete(p1);
		//p1.setNomeCompleto("Mitsuo Luan de Andrade Miyazato");
		//ProprietarioDAO.getInstance().update(p1);
		System.out.println(ProprietarioDAO.getInstance().retrieveById(1));
		
		//PacienteDAO.getInstance().create("Hachi", "Gato", "Vira-Lata", 3, "branco", Paciente.EstadoCastracao.CASTRADO, p1);
		//PacienteDAO.getInstance().create("Lila", "Cachorro", "Vira-Lata", 9, "bege", Paciente.EstadoCastracao.CASTRADO, p1);
		
		//PacienteDAO.getInstance().create("Theo", "Gato", "Vira-Lata", 4, "marrom", Paciente.EstadoCastracao.DESCONHECIDO, p2);
		//Paciente b1 = PacienteDAO.getInstance().retrieveById(1);
		//System.out.println(PacienteDAO.getInstance().retrieveByIdProprietario(1));
		
		List<String> vacinas = new ArrayList<>();
		vacinas.add("Benzetacil");
		vacinas.add("Gripe");
		vacinas.add("Fivi e Felvi");
		
		List<String> doencas = new ArrayList<>();
		doencas.add("dodoi");
		
//		List<String> obs = new ArrayList<>();
//		obs.add("MUITO FOFO");
		
		//HistoricoDAO.getInstance().create(b1, vacinas, doencas, "4kg", "MUITO FOFO");
		//System.out.println(HistoricoDAO.getInstance().retrieveByIdPaciente(1));
		
		//VeterinarioDAO.getInstance().create("José Claudio", "ola@hotmail.com", "123123");
		//Veterinario v1 = VeterinarioDAO.getInstance().retrieveById(1);
		//System.out.println(VeterinarioDAO.getInstance().retrieveById(1));
		
		//AgendamentoDAO.getInstance().create(b1, LocalDateTime.of(2024, 5, 1, 1, 1), "banho", Agendamento.StatusAgendamento.AGENDADO, v1);
		//Agendamento a1 = AgendamentoDAO.getInstance().retrieveById(1);
		//System.out.println(AgendamentoDAO.getInstance().retrieveByIdPaciente(1));
		
		//ReceitaMedicaDAO.getInstance().create(b1, vacinas, LocalDateTime.of(2024, 5, 1, 1, 1), "Deixa quietinha", v1);
		//System.out.println(ReceitaMedicaDAO.getInstance().retrieveById(1));
        
		//FaturaDAO.getInstance().create(p1, 500.0, Fatura.StatusPagamento.PENDENTE, LocalDate.of(2024, 9, 23));
		//Fatura f1 = FaturaDAO.getInstance().retrieveById(1);
		//f1.setStatus(StatusPagamento.PAGO);
		//FaturaDAO.getInstance().update(f1);
		//System.out.println(FaturaDAO.getInstance().retrieveById(1));
		
		//ProdutoDAO.getInstance().create("Coleirinha", "Lojinha", 10.5);
		//Produto coleirinha = ProdutoDAO.getInstance().retrieveById(1);
		//ProdutoDAO.getInstance().create("Batata", "Comida", 2.5);
		//Produto batata = ProdutoDAO.getInstance().retrieveById(2);
		System.out.println(ProdutoDAO.getInstance().retrieveAll());
		
		//EstoqueDAO.getInstance().create(coleirinha, 5, 2);
		//EstoqueDAO.getInstance().create(batata, 10, 1);
		//Estoque batataEstoque = EstoqueDAO.getInstance().retrieveById(2);
		//batataEstoque.setQuantidade(0);
		//EstoqueDAO.getInstance().update(batataEstoque);
		System.out.println(EstoqueDAO.getInstance().retrieveAll());
		
		FaturaDAO.getInstance().create(p1, 100.0, Fatura.StatusPagamento.PAGO, LocalDate.of(2024, 10, 8));
	}
}
