package br.com.letscode.supernova.padroes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.letscode.supernova.modelo.Filme;

public class SumidouroDeRankingPorAno implements SumidouroDeDados<Filme> {
	private static final int NUM_MELHORES_FILMES_ANO = 50;
	
	private int ano;

	public SumidouroDeRankingPorAno(int ano) {
		this.ano = ano;
	}

	@Override
	public void processar(Stream<Filme> fonte) {
		try {
			Files.write(Path.of(String.format("ranking-%d.txt", ano)),
					fonte.filter(filme -> filme.getYear().equals(ano))
							.sorted((f1, f2) -> f1.getRank().compareTo(f2.getRank()))
							.limit(NUM_MELHORES_FILMES_ANO).map(Filme::toString).collect(Collectors.toList()),
					StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.err.format("Falha ao gerar ranking-%d.txt", ano);
		}
	}
}