package br.com.letscode.supernova.padroes;

import java.util.stream.Stream;

public interface SumidouroDeDados<T> {

	void processar(Stream<T> fonte);
}
