package telran.view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateOperationsMenu {	
	
	public static Item getDateOperationsMenu() {
		return new Menu("Operations with dates", 7, getItems());
	}
	
	private static Item[] getItems() {
		Item[] res = {
			Item.of("Date after given number days", DateOperationsMenu::getDateAfterGivenNumberDays),
			Item.of("Date before given number days", DateOperationsMenu::getDateBeforeGivenNumberDays),
			Item.of("Number days between two dates", DateOperationsMenu::getNumberDaysBetweenTwoDates),
			Item.of("Age according to the birthdate", DateOperationsMenu::getAgeAccordingToTheBirthdate),
			Item.exit()			
		};
		return res;
	}
	
	static void getDateAfterGivenNumberDays(InputOutput io) {
		int number = io.readInt("enter number days", "no number");
		io.writeLine(LocalDate.now().plusDays(number));
	}
	
	static void getDateBeforeGivenNumberDays(InputOutput io) {
		int number = io.readInt("enter number days", "no number");
		io.writeLine(LocalDate.now().minusDays(number));
	}
	
	static void getNumberDaysBetweenTwoDates(InputOutput io) {
		LocalDate[] dates = enterDates(io);
		io.writeLine(ChronoUnit.DAYS.between(dates[0], dates[1]));
	}
	
	static void getAgeAccordingToTheBirthdate(InputOutput io) {
		LocalDate birthdate = io.readDate("enter birthdate YYYY-MM-DD", "no date");
		io.writeLine(ChronoUnit.YEARS.between(birthdate, LocalDate.now()));
	}
	
	private static LocalDate[] enterDates(InputOutput io) {
		LocalDate res[] = new LocalDate[2];
		res[0] = io.readDate("enter first date YYYY-MM-DD", "no date");
		res[1] = io.readDate("enter second date YYYY-MM-DD", "no date");
		return res;
	}

}
