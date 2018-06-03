package com.calculator.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
	
	public enum MathOperations {
		ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");
		
		private String sign;
		
		private MathOperations(String sign) {
			this.sign = sign;
		}
	}
	
	public void calculate(String usersEquation) throws ArithmeticException, IllegalArgumentException {
		String pattern = "^((\\-)?(\\d+)(\\.\\d++)?)(\\+|\\-|\\/|\\*)((\\-)?(\\d+)(\\.\\d++)?)$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(usersEquation);

		if (m.find()) {
			MathOperations operSign = getOperationNameBySign(m.group(5));
			
			switch (operSign) {
			case ADD:
				System.out.println(add(Double.valueOf(m.group(1)), Double.valueOf(m.group(6))));
				break;
			case SUBTRACT:
				System.out.println(subtract(Double.valueOf(m.group(1)), Double.valueOf(m.group(6))));
				break;
			case MULTIPLY:
				System.out.println(multiply(Double.valueOf(m.group(1)), Double.valueOf(m.group(6))));
				break;
			case DIVIDE:
				System.out.println(divide(Double.valueOf(m.group(1)), Double.valueOf(m.group(6))));
				break;
			default:
				System.out.println("");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private MathOperations getOperationNameBySign(String sign) {
		for(MathOperations operationName : MathOperations.values()) {
			if(operationName.sign.equals(sign)) {
				return operationName;
			}
		}
		return null;
	}
	
	private Double add(Double a, Double b) {
		return a+b;
	}
	
	private Double subtract(Double a, Double b) {
		return a-b;
	}
	
	private Double multiply(Double a, Double b) {
		return a*b;
	}
	
	private Double divide(Double a, Double b) throws ArithmeticException {
		if (b == 0) {
			throw new ArithmeticException("Can't divide by zero.");
		}
		return a/b;
	}
	
}
