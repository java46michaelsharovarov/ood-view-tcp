package telran.net.test_example;

import telran.view.InputOutput;
import telran.view.Item;

public class CalculatorMenu {
	
	private static CalculatorService calculator;
	
	public static Item[] getCalculatorItems(CalculatorService calculator) {
		CalculatorMenu.calculator = calculator;
		Item[] res = {
				Item.of("Adding", CalculatorMenu::add),
				Item.of("Subtracting", CalculatorMenu::subtract),
				Item.of("Multiplying", CalculatorMenu::multiply),
				Item.of("Dividing", CalculatorMenu::divide)
			};
		return res;
	}
	
	private static void add(InputOutput io) {
		double numbers[] = enterNumbers(io);
		io.writeLine(calculator.add(numbers[0], numbers[1]));
	}

	private static void subtract(InputOutput io) {
		double numbers[] = enterNumbers(io);		
		io.writeLine(calculator.subtract(numbers[0], numbers[1]));
	}

	private static void divide(InputOutput io) {
		double numbers[] = enterNumbers(io);
		if(numbers[1] == 0) {
			io.writeLine("division by zero");
		} else {
			io.writeLine(calculator.divide(numbers[0], numbers[1]));
		}
	}
	
	private static void multiply(InputOutput io) {
		double numbers[] = enterNumbers(io);
		io.writeLine(calculator.multiply(numbers[0], numbers[1]));
	}
	
	private static double[] enterNumbers(InputOutput io) {
		double res[] = new double[2];
		res[0] = io.readDouble("enter first number", "no number");
		res[1] = io.readDouble("enter second number", "no number");
		return res;
	}
}
