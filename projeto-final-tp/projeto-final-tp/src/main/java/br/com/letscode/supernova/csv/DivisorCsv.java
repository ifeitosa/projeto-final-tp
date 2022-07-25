package br.com.letscode.supernova.csv;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DivisorCsv {
	
	private static final String REGEX = "(?:,|\\n|^)(\"(?:(?:\"\")*[^\"]*)*\"|[^\",\\n]*|(?:\\n|$))";
	private static final Pattern PADRAO = Pattern.compile(REGEX);
	
	public static List<String> analisarLinha(String linha) {
		return PADRAO.matcher(linha).results().map(rs -> rs.group().replace(",", "")).collect(Collectors.toList());
	}

}
