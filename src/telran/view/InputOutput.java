package telran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	
	String readString(String prompt);
	
	void writeObject(Object obj);
	
	default void close() {}

	default void writeLine(Object obj) {
		String str = obj + "\n";
		writeObject(str);
	}

	default <R> R readObject(String prompt, String errorPrompt, Function<String, R> mapper) {
		R result = null;
		while (true) {
			String str = readString(prompt);
			try {
				result = mapper.apply(str);
				break;
			} catch (Exception e) {
				writeLine(errorPrompt + e.getMessage());
			}
		}
		return result;
	}
	
	default Integer readInt(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Integer::parseInt);
	}
	
	default Integer readInt(String prompt, String errorPrompt, int min, int max) {
		return readObject(prompt, errorPrompt, s -> {
			int num = Integer.parseInt(s);
			if (num < min) {
				throw new RuntimeException("less than " + min);
			}
			if (num > max) {
				throw new RuntimeException("greater than " + max);
			}
			return num;			
		});
	}
	
	default Long readLong(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Long::parseLong);
	}
	
	default String readOptions(String prompt, String errorPrompt, List<String> options) {
		return readObject(prompt, errorPrompt, s -> {
			String strOptions [] = s.split(" ");
			Arrays.stream(strOptions).forEach(o -> {
				if(!options.contains(o)) {
					throw new RuntimeException("no such option");
				}
			});
			return s;
		});
	}
	
	default LocalDate readDate(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, LocalDate::parse);
	}
	
	default LocalDate readDate(String prompt, String errorPrompt, String format) {
		return readObject(prompt, errorPrompt, s -> 
			LocalDate.parse(s, DateTimeFormatter.ofPattern(format)));
	}
	
	default String readPredicate(String prompt, String errorPrompt, Predicate<String> predicate) {
		return readObject(prompt, errorPrompt, s -> {
			if(!predicate.test(s)) {
				throw new RuntimeException(" " + s);
			};
			return s;
		});
	}

}