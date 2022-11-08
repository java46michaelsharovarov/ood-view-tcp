package telran.net.tests;

import java.io.Serializable;

import telran.net.ApplProtocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class CalculatorProtocol implements ApplProtocol {
	
	Calculator calculator;
	
	public CalculatorProtocol(Calculator calculator) {
		this.calculator = calculator;
	}
	
	@Override
	public Response getResponse(Request request) {
		Response response;
		try {
			switch(request.requestType) {
			case "add": response = add(getArguments(request.requestData)); break;
			case "subtract": response = subtract(getArguments(request.requestData)); break;
			case "divide": response = divide(getArguments(request.requestData)); break;
			case "multiply": response = multiply(getArguments(request.requestData)); break;
			default: response = new Response(ResponseCode.WRONG_REQUEST_TYPE, request.requestType);
			}
		} catch (Exception e) {
			response = new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
		}
		return response;
	}
	
	Response add(double [] operands) {
		double res = calculator.add(operands[0], operands[1]);
		return new Response(ResponseCode.OK, res);
	}
	
	Response subtract(double [] operands) {
		double res = calculator.subtract(operands[0], operands[1]);
		return new Response(ResponseCode.OK, res);
	}
	
	Response divide(double [] operands) {
		double res = calculator.divide(operands[0], operands[1]);
		return new Response(ResponseCode.OK, res);
	}
	
	Response multiply(double [] operands) {
		double res = calculator.multiply(operands[0], operands[1]);
		return new Response(ResponseCode.OK, res);
	}
	
	private double[] getArguments(Serializable requestData) throws Exception {
		try {
			double[] res = (double[]) requestData;
			if (res.length != 2) {
				throw new Exception("no two operands");
			}
			return res;
		} catch (ClassCastException e) {
			throw new Exception("no array of doubles");
		}		
	}

}