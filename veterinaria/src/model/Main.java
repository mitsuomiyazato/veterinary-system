package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Fatura.StatusPagamento;

public class Main {
	public static void main(String[] args) {
//		// Para Ana Clara Silva
		Proprietario proprietario1 = ProprietarioDAO.getInstance().create("Ana Clara Silva", "12345678901", "21998765432", "Rua das Flores, 101");
		Paciente bolinha = PacienteDAO.getInstance().create("Bolinha", "Cachorro", "Pug", 4, "preto", Paciente.EstadoCastracao.CASTRADO, proprietario1);
		List<String> vacinas1 = Arrays.asList("Raiva", "V8");
		List<String> doencas1 = Arrays.asList("Nenhuma");
		HistoricoDAO.getInstance().create(bolinha, vacinas1, doencas1, "8kg", "Saudável");
//
//		// Para Bruno Souza
		Proprietario proprietario2 = ProprietarioDAO.getInstance().create("Bruno Souza", "98765432100", "21987654321", "Avenida Paulista, 1500");
		Paciente mia = PacienteDAO.getInstance().create("Mia", "Gato", "Siamês", 2, "branco", Paciente.EstadoCastracao.FERTIL, proprietario2);
		List<String> vacinas2 = Arrays.asList("Raiva", "V3");
		List<String> doencas2 = Arrays.asList("Nenhuma");
		HistoricoDAO.getInstance().create(mia, vacinas2, doencas2, "4kg", "Leve sobrepeso");
//
//		// Para Carla Mendes
		Proprietario proprietario3 = ProprietarioDAO.getInstance().create("Carla Mendes", "45678912345", "21955566777", "Rua São João, 223");
		Paciente pipoca = PacienteDAO.getInstance().create("Pipoca", "Cachorro", "Shih-Tzu", 6, "marrom", Paciente.EstadoCastracao.CASTRADO, proprietario3);
		List<String> vacinas3 = Arrays.asList("Raiva", "V10");
		List<String> doencas3 = Arrays.asList("Otite");
		HistoricoDAO.getInstance().create(pipoca, vacinas3, doencas3, "6kg", "Tratamento de otite em andamento");
//
//		// Para Diego Almeida
		Proprietario proprietario4 = ProprietarioDAO.getInstance().create("Diego Almeida", "32165498712", "21944332211", "Avenida Brasil, 3000");
		Paciente thor = PacienteDAO.getInstance().create("Thor", "Cachorro", "Bulldog Francês", 3, "cinza", Paciente.EstadoCastracao.FERTIL, proprietario4);
		List<String> vacinas4 = Arrays.asList("Raiva", "V8");
		List<String> doencas4 = Arrays.asList("Alergia a pulgas");
		HistoricoDAO.getInstance().create(thor, vacinas4, doencas4, "12kg", "Recomendado uso de antipulgas");

//		// Para Eduardo Martins
		Proprietario proprietario5 = ProprietarioDAO.getInstance().create("Eduardo Martins", "65432178909", "21933221144", "Rua das Palmeiras, 45");
		Paciente nina = PacienteDAO.getInstance().create("Nina", "Gato", "Angorá", 5, "branco", Paciente.EstadoCastracao.DESCONHECIDO, proprietario5);
		List<String> vacinas5 = Arrays.asList("Raiva", "V3");
		List<String> doencas5 = Arrays.asList("Cálculo renal");
		HistoricoDAO.getInstance().create(nina, vacinas5, doencas5, "3.5kg", "Dietas especiais para cálculos renais");

//		// Para Fernanda Lima
		Proprietario proprietario6 = ProprietarioDAO.getInstance().create("Fernanda Lima", "74185296312", "21999887766", "Rua da Esperança, 32");
		Paciente lila = PacienteDAO.getInstance().create("Lila", "Cachorro", "Vira-Lata", 9, "bege", Paciente.EstadoCastracao.CASTRADO, proprietario6);
		List<String> vacinas6 = Arrays.asList("Raiva", "V8");
		List<String> doencas6 = Arrays.asList("Nenhuma");
		HistoricoDAO.getInstance().create(lila, vacinas6, doencas6, "20kg", "Saudável");

//		// Para Gustavo Ferreira
		Proprietario proprietario7 = ProprietarioDAO.getInstance().create("Gustavo Ferreira", "96325874100", "21977665544", "Avenida Central, 456");
		Paciente max = PacienteDAO.getInstance().create("Max", "Cachorro", "Golden Retriever", 3, "dourado", Paciente.EstadoCastracao.FERTIL, proprietario7);
		List<String> vacinas7 = Arrays.asList("Raiva", "V8");
		List<String> doencas7 = Arrays.asList("Displasia coxofemoral");
		HistoricoDAO.getInstance().create(max, vacinas7, doencas7, "30kg", "Recomendada fisioterapia para displasia");

//		// Para Helena Costa
		Proprietario proprietario8 = ProprietarioDAO.getInstance().create("Helena Costa", "85296374121", "21966554433", "Rua do Sol, 67");
		Paciente mingau = PacienteDAO.getInstance().create("Mingau", "Gato", "Persa", 5, "branco", Paciente.EstadoCastracao.CASTRADO, proprietario8);
		List<String> vacinas8 = Arrays.asList("Raiva", "V3");
		List<String> doencas8 = Arrays.asList("Nenhuma");
		HistoricoDAO.getInstance().create(mingau, vacinas8, doencas8, "5kg", "Saudável");

//		// Para Igor Santos
		Proprietario proprietario9 = ProprietarioDAO.getInstance().create("Igor Santos", "15975348620", "21955443322", "Rua das Acácias, 89");
		Paciente bento = PacienteDAO.getInstance().create("Bento", "Cachorro", "Poodle", 2, "cinza", Paciente.EstadoCastracao.FERTIL, proprietario9);
		List<String> vacinas9 = Arrays.asList("Raiva", "V10");
		List<String> doencas9 = Arrays.asList("Nenhuma");
		HistoricoDAO.getInstance().create(bento, vacinas9, doencas9, "8kg", "Leve sobrepeso, exercício recomendado");

//		// Para Juliana Rocha
		Proprietario proprietario10 = ProprietarioDAO.getInstance().create("Juliana Rocha", "75395185230", "21944332211", "Rua da Paz, 10");
		Paciente frajola = PacienteDAO.getInstance().create("Frajola", "Gato", "Siamês", 4, "preto", Paciente.EstadoCastracao.DESCONHECIDO, proprietario10);
		List<String> vacinas10 = Arrays.asList("Raiva", "V3");
		List<String> doencas10 = Arrays.asList("Insuficiência renal");
		HistoricoDAO.getInstance().create(frajola, vacinas10, doencas10, "4kg", "Recomendado tratamento contínuo para insuficiência renal");
		
//		// Para José Claudio
//
//		// Para Maria Fernanda
//
//		// Para João Pedro
//
//		// Para Ana Beatriz
		VeterinarioDAO.getInstance().create("Ana Beatriz", "ana.beatriz@yahoo.com", "21965432109");
//
//		// Para Lucas Andrade
		VeterinarioDAO.getInstance().create("Lucas Andrade", "lucas.andrade@outlook.com", "21954321098");
//
//		// Para Paula Mendes
		VeterinarioDAO.getInstance().create("Paula Mendes", "paula.mendes@mail.com", "21943210987");
//
//		// Para Renato Figueiredo
		VeterinarioDAO.getInstance().create("Renato Figueiredo", "renato.figueiredo@vetclinic.com", "21932109876");
//
//		// Para Bianca Moreira
		VeterinarioDAO.getInstance().create("Bianca Moreira", "bianca.moreira@gmail.com", "21921098765");
//
//		// Para Guilherme Souza
		VeterinarioDAO.getInstance().create("Guilherme Souza", "guilherme.souza@clinicvet.com", "21910987654");
//
//		// Para Fabiana Lima
		VeterinarioDAO.getInstance().create("Fabiana Lima", "fabiana.lima@veterinarios.com", "21909876543");
//		
//		// Para Ana Clara Silva e o paciente Bolinha, com o veterinário José Claudio
		Veterinario v1 = VeterinarioDAO.getInstance().create("José Claudio", "ola@hotmail.com", "123123");
		AgendamentoDAO.getInstance().create(bolinha, LocalDateTime.of(2024, 5, 1, 9, 30), "banho", Agendamento.StatusAgendamento.AGENDADO, v1);
//
//		// Para Bruno Souza e o paciente Mia, com o veterinário Maria Fernanda
		Veterinario v2 = VeterinarioDAO.getInstance().create("Maria Fernanda", "maria.fernanda@gmail.com", "21987654321");
		AgendamentoDAO.getInstance().create(mia, LocalDateTime.of(2024, 5, 2, 14, 0), "vacinação", Agendamento.StatusAgendamento.AGENDADO, v2);
//
//		// Para Carla Mendes e o paciente Pipoca, com o veterinário João Pedro
		Veterinario v3 = VeterinarioDAO.getInstance().create("João Pedro", "joao.pedro@hotmail.com", "21976543210");
		AgendamentoDAO.getInstance().create(pipoca, LocalDateTime.of(2024, 5, 3, 10, 45), "consulta", Agendamento.StatusAgendamento.AGENDADO, v3);
//
//		// Para Ana Clara Silva e o paciente Bolinha, com o veterinário José Claudio
		List<String> medicamentos1 = Arrays.asList("Vacina Raiva", "Antipulgas");
		ReceitaMedicaDAO.getInstance().create(bolinha, medicamentos1, LocalDateTime.of(2024, 5, 1, 11, 0), "Deixa quietinha e aplicar antipulgas mensalmente", v1);
//
//		// Para Bruno Souza e o paciente Mia, com o veterinário Maria Fernanda
		List<String> medicamentos2 = Arrays.asList("Vermífugo", "Vacina V3");
		ReceitaMedicaDAO.getInstance().create(mia, medicamentos2, LocalDateTime.of(2024, 5, 2, 15, 30), "Administrar vermífugo e reforço vacinal em 6 meses", v2);
//
//		// Para Carla Mendes e o paciente Pipoca, com o veterinário João Pedro
		List<String> medicamentos3 = Arrays.asList("Anti-inflamatório", "Pomada Otológica");
		ReceitaMedicaDAO.getInstance().create(pipoca, medicamentos3, LocalDateTime.of(2024, 5, 3, 12, 0), "Aplicar a pomada 2x ao dia por 10 dias", v3);
//        
//		// Para Ana Clara Silva (proprietario1), com pagamento PENDENTE
		FaturaDAO.getInstance().create(proprietario1, 500.0, Fatura.StatusPagamento.PENDENTE, LocalDate.of(2024, 9, 23));
//
//		// Para Bruno Souza (proprietario2), com pagamento PAGO
		FaturaDAO.getInstance().create(proprietario2, 300.0, Fatura.StatusPagamento.PAGO, LocalDate.of(2024, 9, 15));
//
//		// Para Carla Mendes (proprietario3), com pagamento EM_ATRASO
		FaturaDAO.getInstance().create(proprietario3, 450.0, Fatura.StatusPagamento.EM_ATRASO, LocalDate.of(2024, 9, 10));
//
//		// Para Diego Almeida (proprietario4), com pagamento PENDENTE
		FaturaDAO.getInstance().create(proprietario4, 700.0, Fatura.StatusPagamento.PENDENTE, LocalDate.of(2024, 9, 28));
//
//		// Para Eduardo Martins (proprietario5), com pagamento PAGO
		FaturaDAO.getInstance().create(proprietario5, 250.0, Fatura.StatusPagamento.PAGO, LocalDate.of(2024, 9, 18));
//
//		// Para Fernanda Lima (proprietario6), com pagamento EM_ATRASO
		FaturaDAO.getInstance().create(proprietario6, 600.0, Fatura.StatusPagamento.EM_ATRASO, LocalDate.of(2024, 9, 12));
//
//		// Para Gustavo Ferreira (proprietario7), com pagamento PENDENTE
		FaturaDAO.getInstance().create(proprietario7, 800.0, Fatura.StatusPagamento.PENDENTE, LocalDate.of(2024, 9, 25));
//
//		// Para Helena Costa (proprietario8), com pagamento PAGO
		FaturaDAO.getInstance().create(proprietario8, 350.0, Fatura.StatusPagamento.PAGO, LocalDate.of(2024, 9, 19));
//
//		// Criando produtos e adicionando ao estoque
//
//		// Produto 1: Coleirinha
		ProdutoDAO.getInstance().create("Coleirinha", "Acessórios", 10.5);
		Produto coleirinha = ProdutoDAO.getInstance().retrieveById(1);
		EstoqueDAO.getInstance().create(coleirinha, 5, 2); // 5 unidades no estoque, mínimo 2 unidades
//
//		// Produto 2: Batata
		ProdutoDAO.getInstance().create("Batata", "Comida", 2.5);
		Produto batata = ProdutoDAO.getInstance().retrieveById(2);
		EstoqueDAO.getInstance().create(batata, 10, 1); // 10 unidades no estoque, mínimo 1 unidade
//
//		// Produto 3: Brinquedo de Cachorro
		ProdutoDAO.getInstance().create("Brinquedo de Cachorro", "Brinquedos", 15.0);
		Produto brinquedoCachorro = ProdutoDAO.getInstance().retrieveById(3);
		EstoqueDAO.getInstance().create(brinquedoCachorro, 20, 3); // 20 unidades no estoque, mínimo 3 unidades
//
//		// Produto 4: Ração Premium
		ProdutoDAO.getInstance().create("Ração Premium", "Comida", 80.0);
		Produto racaoPremium = ProdutoDAO.getInstance().retrieveById(4);
		EstoqueDAO.getInstance().create(racaoPremium, 50, 5); // 50 unidades no estoque, mínimo 5 unidades
//
//		// Produto 5: Shampoo para Pet
		ProdutoDAO.getInstance().create("Shampoo para Pet", "Higiene", 12.0);
		Produto shampooPet = ProdutoDAO.getInstance().retrieveById(5);
		EstoqueDAO.getInstance().create(shampooPet, 30, 4); // 30 unidades no estoque, mínimo 4 unidades
//
//		// Produto 6: Osso de Brinquedo
		ProdutoDAO.getInstance().create("Osso de Brinquedo", "Brinquedos", 5.0);
		Produto ossoBrinquedo = ProdutoDAO.getInstance().retrieveById(6);
		EstoqueDAO.getInstance().create(ossoBrinquedo, 15, 2); // 15 unidades no estoque, mínimo 2 unidades
//
//		// Produto 7: Areia para Gato
		ProdutoDAO.getInstance().create("Areia para Gato", "Higiene", 25.0);
		Produto areiaGato = ProdutoDAO.getInstance().retrieveById(7);
		EstoqueDAO.getInstance().create(areiaGato, 40, 10); // 40 unidades no estoque, mínimo 10 unidades
//
//		// Produto 8: Petisco de Frango
		ProdutoDAO.getInstance().create("Petisco de Frango", "Comida", 7.0);
		Produto petiscoFrango = ProdutoDAO.getInstance().retrieveById(8);
		EstoqueDAO.getInstance().create(petiscoFrango, 25, 5); // 25 unidades no estoque, mínimo 5 unidades
//
//		// Produto 9: Pente para Gato
		ProdutoDAO.getInstance().create("Pente para Gato", "Acessórios", 9.0);
		Produto penteGato = ProdutoDAO.getInstance().retrieveById(9);
		EstoqueDAO.getInstance().create(penteGato, 12, 3); // 12 unidades no estoque, mínimo 3 unidades
//
//		// Produto 10: Coleira Anti-pulgas
		ProdutoDAO.getInstance().create("Coleira Anti-pulgas", "Acessórios", 45.0);
		Produto coleiraAntipulgas = ProdutoDAO.getInstance().retrieveById(10);
		EstoqueDAO.getInstance().create(coleiraAntipulgas, 18, 5); // 18 unidades no estoque, mínimo 5 unidades

	}
}
