package br.com.letscode.supernova.padroes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.letscode.supernova.modelo.Filme;

public class SumidouroDeRankeamento implements SumidouroDeDados<Filme> {

	private String nomeArquivo;

	public SumidouroDeRankeamento(String nomeArquivo) {
		Objects.requireNonNull(nomeArquivo);
		if (nomeArquivo.isBlank())
			throw new IllegalArgumentException("Nome de arquivo vazio.");
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public void processar(Stream<Filme> fonte) {
		try {
			Files.write(
					Path.of(this.nomeArquivo), fonte.sorted((f1, f2) -> f1.getRank().compareTo(f2.getRank()))
							.map(Filme::toString).collect(Collectors.toList()),
					StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.err.format("Falha na escrita do arquivo de dados \"%s\"", this.nomeArquivo);
		}
	}

}
