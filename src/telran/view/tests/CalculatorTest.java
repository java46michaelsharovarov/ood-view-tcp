package telran.view.tests;

import telran.view.ConsoleInputOutput;
import telran.view.Item;

import static telran.view.DateOperationsMenu.*;
import telran.view.Menu;
import static telran.view.NumbersOperationsMenu.*;

public class CalculatorTest {

	public static void main(String[] args) {
		Menu menu = new Menu("Main menu", 3, 
				getNumbersOperationsMenu(),
				getDateOperationsMenu(),
				Item.exit());
		menu.perform(new ConsoleInputOutput());
	}

}