package br.com.letscode.supernova.modelo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Filme {

	private final Integer rank;
	private final String title;
	private final String genre;
	private final String description;
	private final String director;
	private final List<String> actors;
	private final Integer year;
	private final Integer runtime;
	private final Float rating;
	private final Integer votes;
	private final Float revenue;
	private final Integer metascore;

	public Filme(Integer rank, String title, String genre, String description, String director, List<String> actors,
			Integer year, Integer runtime, Float rating, Integer votes, Float revenue, Integer metascore) {
		super();
		this.rank = rank;
		this.title = title;
		this.genre = genre;
		this.description = description;
		this.director = director;
		this.actors = actors;
		this.year = year;
		this.runtime = runtime;
		this.rating = rating;
		this.votes = votes;
		this.revenue = revenue;
		this.metascore = metascore;
	}

	public Integer getRank() {
		return rank;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public String getDescription() {
		return description;
	}

	public String getDirector() {
		return director;
	}

	public List<String> getActors() {
		return actors;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public Float getRating() {
		return rating;
	}

	public Integer getVotes() {
		return votes;
	}

	public Float getRevenue() {
		return revenue;
	}

	public Integer getMetascore() {
		return metascore;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.rank, this.director, this.title);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Filme)) {
			return false;
		}
		Filme outro = (Filme) obj;
		return Objects.equals(this.rank, outro.rank) && Objects.equals(this.director, outro.director)
				&& Objects.equals(this.title, outro.title) && Objects.equals(this.year, outro.year);
	}
	@Override
	public String toString() {
		return String.format("%s, dirigido por %s, no ano de %d, tem metascore de %d e é o %d colocado no ranking", this.title, this.director, this.year, this.metascore, this.rank);
	}

}
