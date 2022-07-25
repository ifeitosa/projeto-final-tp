package br.com.letscode.supernova.transformer;

import java.util.stream.Stream;

public interface Transformador<T> {

	<U> Stream<U> transformar(Stream<T> stream);
}
