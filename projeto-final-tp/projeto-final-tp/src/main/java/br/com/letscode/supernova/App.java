package br.com.letscode.supernova;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import br.com.letscode.supernova.csv.RepositorioFilmes;
import br.com.letscode.supernova.padroes.SumidouroDeRankingPorAno;
import br.com.letscode.supernova.padroes.SumidouroDoHorror;

public class App {
	private static final List<Path> filmes = List.of("movies1.csv", "movies2.csv", "movies3.csv").stream()
			.map(str -> Path.of(str)).collect(Collectors.toList());

	public static void main(String[] args) throws IOException {

		LocalDateTime inicio = LocalDateTime.now();
		long inicioMs = System.currentTimeMillis();
		ExecutorService executor = Executors.newCachedThreadPool();
		final RepositorioFilmes repositorio = new RepositorioFilmes(filmes);

		repositorio.stream().map(filme -> filme.getYear()).distinct()
			.forEach(ano -> executor.submit(new RankeadorPorAno(ano, repositorio)));
		
		Runnable horror = () -> {
			new SumidouroDoHorror("horror-melhores.txt").processar(repositorio.stream());
		};
		executor.submit(horror);
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("Threads interrompidos.");
		}

		long duracao = System.currentTimeMillis() - inicioMs;
		LocalDateTime termino = LocalDateTime.now();
		System.out.println("Hora de início: " + inicio);
		System.out.println("Hora de término: " + termino);
		System.out.println("Tempo de execução: " + duracao + " ms.");

		long tempo = 0;
		List<String> temporizacaoDeExecucao = List.of(String.format("Início de processamento: %s", inicio),
				String.format("Final de processamento: %s", LocalDateTime.now()),
				String.format("Tempo em milissegundos: %d ms", tempo = System.currentTimeMillis() - inicioMs),
				String.format("Tempo de execução em segundos: %.2f s", (double) tempo / 1000));
		Files.write(Path.of("tempo-de-execucao.txt"), temporizacaoDeExecucao, StandardOpenOption.TRUNCATE_EXISTING,
				StandardOpenOption.CREATE);
	}
}

class RankeadorPorAno implements Runnable {

	private int ano;
	private RepositorioFilmes repositorio;

	public RankeadorPorAno(int ano, RepositorioFilmes repositorio) {
		this.ano = ano;
		this.repositorio = repositorio;
	}

	@Override
	public void run() {
		new SumidouroDeRankingPorAno(this.ano).processar(repositorio.stream());
	}

}
