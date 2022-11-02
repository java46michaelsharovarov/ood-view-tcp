package telran.view.tests;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;

class InputOutputTest {
	InputOutput io = new ConsoleInputOutput();
	
	@Test
	@Disabled
	void readObjectTest() {
		Integer[] array = io.readObject("Enter numbers separated by space",
				"no number ", s -> {					
					String strNumbers [] = s.split(" ");
					return Arrays.stream(strNumbers).map(str -> Integer.parseInt(str))
							.toArray(Integer[]::new);					
				}) ;
		io.writeLine(Arrays.stream(array).collect(Collectors.summarizingInt(x -> x)));		
	}
	
	@Test
	void readIntMinMax() {
		Integer res = io.readInt("Enter any number in range [1, 40]", "no number ", 1, 40);
		io.writeLine(res);
	}
	
	@Test
	void readLongTest() {
		Long res = io.readLong("Enter any number", "no number ");
		io.writeLine(res);
	}
	
	@Test
	void readOptionsTest() {
		List<String> listOfOptions = List.of("Option_1", "Option_2", "Option_3", "Option_4");
		String res = io.readOptions("Enter options separated by space, from the options list "  + listOfOptions.toString(), "incorrect input ", listOfOptions);
		io.writeLine(res);
	}
	
	@Test
	void readDateTest() {
		LocalDate res = io.readDate("Enter any date in format YYYY-MM-DD", "incorrect format ");
		io.writeLine(res);
	}
	
	@Test
	void readDateFormatTest() {
		String format = io.readString("Enter the format in which your date will be presented, for example yyyy-MM-dd");
		LocalDate res = io.readDate("Enter any date in format " + format, "incorrect format ", format);
		io.writeLine(res);
	}
	
	@Test
	void readPredicateTest() {
		String zeroTo255 = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";
		String regex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;
		Predicate<String> predicate = s -> s.matches(regex);
		String res = io.readPredicate("Enter IP", "incorrect IP", predicate);
		io.writeLine(res);
	}

}