package telran.net.tests;

import java.io.Closeable;
import java.io.IOException;

import telran.net.NetworkHandler;

public class NetCalculatorProxy implements Closeable, Calculator {
	
	private NetworkHandler networkHandler;

	public NetCalculatorProxy(NetworkHandler networkHandler) {
		this.networkHandler = networkHandler;
	}
	
	@Override
	public double add(double op1, double op2) {
		return networkHandler.send("add", new double[]{op1, op2});
	}

	@Override
	public double subtract(double op1, double op2) {
		return networkHandler.send("subtract", new double[]{op1, op2});
	}

	@Override
	public double divide(double op1, double op2) {
		return networkHandler.send("divide", new double[]{op1, op2});
	}

	@Override
	public double multiply(double op1, double op2) {
		return networkHandler.send("multiply", new double[]{op1, op2});
	}

	@Override
	public void close() throws IOException {
		networkHandler.close();
	}

}
