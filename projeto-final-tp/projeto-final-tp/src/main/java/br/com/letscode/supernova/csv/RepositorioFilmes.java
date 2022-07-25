package br.com.letscode.supernova.csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.letscode.supernova.modelo.Filme;
import br.com.letscode.supernova.modelo.FilmeBuilder;

public class RepositorioFilmes {
	
	private Set<Filme> filmes;
	
	public RepositorioFilmes(List<Path> arquivos) throws IOException {
		if (arquivos.size() == 0) throw new IllegalArgumentException();
		String linhaCabecalho = Files.readAllLines(arquivos.get(0)).stream().findFirst().orElse("");
		List<String> cabecalho = DivisorCsv.analisarLinha(linhaCabecalho);
		
		this.filmes = arquivos.stream().flatMap(arq -> {
			try {
				return Files.readAllLines(arq).stream();
			} catch (IOException e) {
				return Stream.of();
			}
		}).skip(1)
			.map(DivisorCsv::analisarLinha)
			.map((List<String> lista) -> new FilmeBuilder().setPropriedades(cabecalho, lista).build())
			.collect(Collectors.toSet());
	}
	
	public Stream<Filme> stream() {
		return filmes.stream();
	}

}
