package telran.net.test_example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import telran.net.client.TcpClient;
import telran.view.ConsoleInputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CalculatorApp {

	public static void main(String[] args) {
		try {
			TcpClient handler = new TcpClient("localhost", 3000);
			NetCalculatorProxy proxy = new NetCalculatorProxy(handler);
			ArrayList<Item> items = new ArrayList<>(
					Arrays.asList(CalculatorMenu.getCalculatorItems(proxy)));
			items.add(Item.of("Exit", e -> {
				try {
					handler.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}, true));
			Menu menu = new Menu("Calculator for double numbers", 30, items);
			menu.perform(new ConsoleInputOutput());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}