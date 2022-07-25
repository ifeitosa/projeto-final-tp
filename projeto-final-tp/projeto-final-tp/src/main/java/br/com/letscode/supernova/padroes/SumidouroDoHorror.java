package br.com.letscode.supernova.padroes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.letscode.supernova.modelo.Filme;

public class SumidouroDoHorror implements SumidouroDeDados<Filme> {

	private static final int NUM_MELHORES_FILMES_HORROR = 20;

	private String nomeArquivo;

	public SumidouroDoHorror(String nomeArquivo) {
		Objects.requireNonNull(nomeArquivo);
		if (nomeArquivo.isBlank())
			throw new IllegalArgumentException("Nome do arquivo vazio.");
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public void processar(Stream<Filme> fonte) {
		Pattern genero_horror = Pattern.compile(".*horror.*", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
		try {
			Files.write(Path.of(this.nomeArquivo),
					fonte.filter(filme -> genero_horror.matcher(filme.getGenre()).matches())
							.sorted((f1, f2) -> f1.getRank().compareTo(f2.getRank())).map(Filme::toString)
							.limit(NUM_MELHORES_FILMES_HORROR).collect(Collectors.toList()),
					StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.err.format("Falha ao escrever arquivo \"%s\".", this.nomeArquivo);
		}
	}

}
