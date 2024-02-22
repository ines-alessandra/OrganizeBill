package br.edu.ufape.organizeBill.dataSeed;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ufape.organizeBill.model.Categoria;
import br.edu.ufape.organizeBill.model.Despesas;
import br.edu.ufape.organizeBill.model.ObjetivoFinanceiro;
import br.edu.ufape.organizeBill.model.Receita;
import br.edu.ufape.organizeBill.model.Usuario;
import br.edu.ufape.organizeBill.repository.CategoriaRepository;
import br.edu.ufape.organizeBill.repository.DespesasRepository;
import br.edu.ufape.organizeBill.repository.ObjetivoFinanceiroRepository;
import br.edu.ufape.organizeBill.repository.ReceitaRepository;
import br.edu.ufape.organizeBill.repository.UsuarioRepository;

@Component	
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	UsuarioRepository userRepository;
	@Autowired
	ReceitaRepository receitaRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	DespesasRepository despesasRepository;
	@Autowired
	ObjetivoFinanceiroRepository objetivoRepository;

	@Override
	public void run(String... args) throws Exception {
		loadUsuarioData();
		loadReceitaData();
		loadCategoriaData();
		loadDespesasData();
		loadObjetivoData();
	}
	
	private void loadUsuarioData() {
		if (userRepository.count() == 0) {
			Usuario user1 = new Usuario("11571988733",  LocalDate.of(2023,2,5) , "banco", "Sofia", "sofia@gmail.com");
			Usuario user2 = new Usuario("88577688499",  LocalDate.of(2022,6,8) , "banco", "Artur", "artur@gmail.com");
			userRepository.save(user1);
			userRepository.save(user2);
		}
		System.out.println(userRepository.count());
	}
	
	private void loadReceitaData() {
		if (receitaRepository.count() == 0) {
			Usuario user1 = userRepository.findByCpf("11571988733");
			Usuario user2 = userRepository.findByCpf("88577688499");
			
			Receita receita1 = new Receita(	0,"salario", 2000.0, LocalDate.of(2024,2,15), user1, true);
			Receita receita2 = new Receita(	0,"renda extra", 350.0, LocalDate.of(2024,2,5), user1, false);
			Receita receita5 = new Receita(	0,"renda extra", 350.0, LocalDate.of(2024,1,5), user1, false);
			Receita receita6 = new Receita(	0,"renda extra", 350.0, LocalDate.of(2023,12,5), user1, false);
			receitaRepository.save(receita1);
			receitaRepository.save(receita2);
			receitaRepository.save(receita5);
			receitaRepository.save(receita6);
			
			Receita receita3 = new Receita(	0,"salario", 4000.0, LocalDate.of(2024,6,8), user2, true);
			Receita receita4 = new Receita(	0,"renda extra", 500.0, LocalDate.of(2024,6,8), user2, false);
			receitaRepository.save(receita3);
			receitaRepository.save(receita4);
		
		}
		System.out.println(receitaRepository.count());
	}
	
	private void loadCategoriaData() {
		if (categoriaRepository.count() == 0) {
			Usuario user1 = userRepository.findByCpf("11571988733");
			Usuario user2 = userRepository.findByCpf("88577688499");
			
			Categoria categoria1 = new Categoria(0,"contas da casa","gastos fixos", 430.0, user1);
			Categoria categoria2 = new Categoria(0,"alimentação","feiras, lanches, etc", 300.0, user1);
			Categoria categoria3 = new Categoria(0,"bem-estar","academia, psicologa, remedios, etc", 700.0, user1);
			Categoria categoria4 = new Categoria(0,"compras extras","roupas, moveis, etc", 100.0, user1);
			categoriaRepository.save(categoria1);
			categoriaRepository.save(categoria2);
			categoriaRepository.save(categoria3);
			categoriaRepository.save(categoria4);
			
			Categoria categoria5 = new Categoria(0,"contas da casa","gastos fixos", 1000.0, user2);
			Categoria categoria6 = new Categoria(0,"alimentação","feiras, lanches, etc", 500.0, user2);
			Categoria categoria7 = new Categoria(0,"bem-estar","academia, psicologa, remedios, etc", 1000.0, user2);
			Categoria categoria8 = new Categoria(0,"compras extras","roupas, moveis, etc", 400.0, user2);
			categoriaRepository.save(categoria5);
			categoriaRepository.save(categoria6);
			categoriaRepository.save(categoria7);
			categoriaRepository.save(categoria8);
		
		}
		System.out.println(categoriaRepository.count());
	}
	
	private void loadDespesasData() {
		if (despesasRepository.count() == 0) {
			Usuario user1 = userRepository.findByCpf("11571988733");
			List<Categoria> categorias1 = categoriaRepository.findByUsuarioCpf("11571988733");
			
			Despesas despesas1 = new Despesas(0, "aluguel", 340.0, LocalDate.of(2024,1,5), categorias1.get(0), user1, true);
			Despesas despesas2 = new Despesas(0, "luz", 30.0, LocalDate.of(2024,2,5), categorias1.get(0), user1, true);
			Despesas despesas3 = new Despesas(0, "agua", 25.0, LocalDate.of(2024,1,5), categorias1.get(0), user1, true);
			Despesas despesas4 = new Despesas(0, "academia", 109.0, LocalDate.of(2023,11,15), categorias1.get(2), user1, true);
			Despesas despesas5 = new Despesas(0, "psicologa", 400.0, LocalDate.of(2024,2,18), categorias1.get(2), user1, true);
			Despesas despesas6 = new Despesas(0, "tenis", 300.0, LocalDate.of(2024,1,9), categorias1.get(3), user1, false);
			Despesas despesas7 = new Despesas(0, "feira", 340.0, LocalDate.of(2023,12,6), categorias1.get(1), user1, true);
			Despesas despesas8 = new Despesas(0, "lanche", 50.0, LocalDate.of(2024,1,5), categorias1.get(1), user1, false);
			Despesas despesas9 = new Despesas(0, "internet", 25.0, LocalDate.of(2024,2,5), categorias1.get(0), user1, true);
			despesasRepository.save(despesas1);
			despesasRepository.save(despesas2);
			despesasRepository.save(despesas3);
			despesasRepository.save(despesas4);
			despesasRepository.save(despesas5);
			despesasRepository.save(despesas6);
			despesasRepository.save(despesas7);
			despesasRepository.save(despesas8);
			despesasRepository.save(despesas9);
			
			
			Usuario user2 = userRepository.findByCpf("88577688499");
			List<Categoria> categorias2 = categoriaRepository.findByUsuarioCpf("88577688499");
			
			Despesas despesas10 = new Despesas(0, "aluguel", 900.0, LocalDate.of(2024,1,8), categorias2.get(0), user2, true);
			Despesas despesas11 = new Despesas(0, "luz", 100.0, LocalDate.of(2024,1,8), categorias2.get(0), user2, true);
			Despesas despesas12 = new Despesas(0, "agua", 80.0, LocalDate.of(2024,1,8), categorias2.get(0), user2, true);
			Despesas despesas13 = new Despesas(0, "academia", 200.0, LocalDate.of(2024,1,15), categorias2.get(2), user2, true);
			Despesas despesas14 = new Despesas(0, "psicologa", 500.0, LocalDate.of(2024,1,18), categorias2.get(2), user2, true);
			Despesas despesas15 = new Despesas(0, "tenis", 500.0, LocalDate.of(2024,1,24), categorias2.get(3), user2, false);
			Despesas despesas16 = new Despesas(0, "feira", 1000.0, LocalDate.of(2024,1,8), categorias2.get(1), user2, true);
			Despesas despesas17 = new Despesas(0, "lanche", 200.0, LocalDate.of(2024,1,26), categorias2.get(1), user2, false);
			Despesas despesas18 = new Despesas(0, "internet", 100.0, LocalDate.of(2024,1,8), categorias2.get(0), user2, true);
			despesasRepository.save(despesas10);
			despesasRepository.save(despesas11);
			despesasRepository.save(despesas12);
			despesasRepository.save(despesas13);
			despesasRepository.save(despesas14);
			despesasRepository.save(despesas15);
			despesasRepository.save(despesas16);
			despesasRepository.save(despesas17);
			despesasRepository.save(despesas18);

		
		}
		System.out.println(despesasRepository.count());
	}
	
	private void loadObjetivoData() {
		if (objetivoRepository.count() == 0) {
			Usuario user1 = userRepository.findByCpf("11571988733");
			
			ObjetivoFinanceiro objetivo1 = new ObjetivoFinanceiro(0, "Show da Taylor", 
																  LocalDate.of(2024,1,5), 200.0,
																  LocalDate.of(2024,10,26), 3000.0, 2800.0,
																  "ingresso, passagem, hotel para o show, etc", null, user1);
			Categoria categoria1 = new Categoria(0,"Objetivo: "+objetivo1.getNome(),objetivo1.getDescricao(),objetivo1.getValorTransitorio()
					, objetivo1.getUsuario());
			categoriaRepository.save(categoria1);
			objetivo1.setCategoria(categoria1);
			objetivoRepository.save(objetivo1);
			Despesas despesas1 = new Despesas(0, "valor transferido para objetivo", 400.0, LocalDate.of(2024,3,8), categoria1, user1, false);
			Despesas despesas2 = new Despesas(0, "valor transferido para objetivo", 400.0, LocalDate.of(2024,4,8), categoria1, user1, false);
			Despesas despesas3 = new Despesas(0, "valor transferido para objetivo", 2000.0, LocalDate.of(2024,10,8), categoria1, user1, false);
			despesasRepository.save(despesas1);
			despesasRepository.save(despesas2);
			despesasRepository.save(despesas3);

			ObjetivoFinanceiro objetivo2 = new ObjetivoFinanceiro(0, "Viagem Porto de Galinhas",
					LocalDate.of(2023,5,5), 200.0,
					LocalDate.of(2023,10,26), 5000.0, 5000.0,
					"praia, hospedagem", null, user1);
			Categoria categoria2 = new Categoria(0,"Objetivo: "+objetivo2.getNome(),objetivo2.getDescricao(),objetivo2.getValorTransitorio()
					, objetivo2.getUsuario());
			categoriaRepository.save(categoria2);
			objetivo2.setCategoria(categoria2);
			objetivoRepository.save(objetivo2);
			Despesas despesas4 = new Despesas(0, "valor transferido para objetivo", 500.0, LocalDate.of(2024,3,8), categoria2, user1, false);
			Despesas despesas5 = new Despesas(0, "valor transferido para objetivo", 500.0, LocalDate.of(2024,4,8), categoria2, user1, false);
			Despesas despesas6 = new Despesas(0, "valor transferido para objetivo", 2000.0, LocalDate.of(2024,10,8), categoria2, user1, false);
			despesasRepository.save(despesas4);
			despesasRepository.save(despesas5);
			despesasRepository.save(despesas6);
		
		}
		System.out.println(objetivoRepository.count());
	}
}
