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
	
	public Double calculate(String usersEquation) throws ArithmeticException, IllegalArgumentException {
		String pattern = "^((\\-)?(\\d+)(\\.\\d++)?)(\\+|\\-|\\/|\\*)((\\-)?(\\d+)(\\.\\d++)?)$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(usersEquation.trim());

		if (m.find()) {
			MathOperations operSign = getOperationNameBySign(m.group(5));
			
			switch (operSign) {
			case ADD:
				return add(Double.valueOf(m.group(1)), Double.valueOf(m.group(6)));
			case SUBTRACT:
				return subtract(Double.valueOf(m.group(1)), Double.valueOf(m.group(6)));
			case MULTIPLY:
				return multiply(Double.valueOf(m.group(1)), Double.valueOf(m.group(6)));
			case DIVIDE:
				return divide(Double.valueOf(m.group(1)), Double.valueOf(m.group(6)));
			}
		} else {
			throw new IllegalArgumentException();
		}
		return null;
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
		if(a==0) {
			return Double.valueOf(0);
		}
		return a/b;
	}
	
}
