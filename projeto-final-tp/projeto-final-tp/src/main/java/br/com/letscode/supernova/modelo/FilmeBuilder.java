package br.com.letscode.supernova.modelo;

import java.util.Collections;
import java.util.List;

public class FilmeBuilder {
	
	private Integer rank;
	private String title;
	private String genre;
	private String description;
	private String director;
	private List<String> actors = Collections.emptyList();
	private Integer year;
	private Integer runtime;
	private Float rating;
	private Integer votes;
	private Float revenue;
	private Integer metascore;
	
	private Integer converterOuPadrao(String valor, Integer padrao) {
		try {
			return Integer.valueOf(valor);
		} catch (NumberFormatException ex) {
			return padrao;
		}
	}
	
	private Float converterOuPadrao(String valor, Float padrao) {
		try {
			return Float.valueOf(valor);
		} catch (NumberFormatException ex) {
			return padrao;
		}
	}
	
	public FilmeBuilder setPropriedades(List<String> propriedades, List<String> valores) {
		StringBuilder buffer = new StringBuilder();
		boolean erroEmPropriedade = false;
		for(int i = 0; i < valores.size(); i++) {
			String prop = propriedades.get(i);
			String valor = valores.get(i);
			switch (prop) {
			case "Rank":
				this.rank = converterOuPadrao(valor, 0);
				break;
			case "Title":
				this.title = valor;
				break;
			case "Genre":
				this.genre = valor;
				break;
			case "Description":
				this.description = valor;
				break;
			case "Director":
				this.director = valor;
				break;
			case "Actors":
				this.actors = List.of(valor.split(", "));
				break;
			case "Year":
				this.year = converterOuPadrao(valor, 0);
				break;
			case "Runtime (Minutes)":
				this.runtime = converterOuPadrao(valor, 0);
				break;
				
			case "Rating":
				this.rating = converterOuPadrao(valor, 0.0f);
				break;
			case "Votes":
				this.votes = converterOuPadrao(valor, 0);
				break;
			case "Revenue (Millions)":
				this.revenue = converterOuPadrao(valor, 0.0f);
				break;
			case "Metascore":
				this.metascore = converterOuPadrao(valor, 0);
				break;
			default:
				if (!erroEmPropriedade) {
					buffer.append("Propriedade(s) desconhecida(s): ").append(prop);
					erroEmPropriedade = true;
				} else {
					buffer.append(", ").append(prop);
				}
			}
		}
		if (erroEmPropriedade) {
			buffer.append(".");
			throw new IllegalArgumentException(buffer.toString());
		}
		return this;
	}
	
	public Filme build() {
		return new Filme(this.rank, this.title, this.genre, this.description, this.director, this.actors,
				this.year, this.runtime, this.rating, this.votes, this.revenue, this.metascore);
	}

}
