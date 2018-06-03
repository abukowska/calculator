package com.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.calculator.model.Calculator;


@DisplayName("Calculator operations tests")
public class CalcTests {
	
	private static Calculator calculator;
	
	@BeforeAll
    static void initAll() {
		calculator = new Calculator();
    }

	@AfterEach
    void tearDown() {
		calculator = new Calculator();
    }
	
	@Test
	@DisplayName("Test 001 -- Check whether division by zero will throw an error.")
	public void divisionByZero() {
		String[] invalidMathEquations = new String[] {"12/0", "-9/0.0", "4444.4/0"};
		
		for (String invalidEquation : invalidMathEquations) {
		    assertThrows(ArithmeticException.class, () -> {
		    	calculator.calculate(invalidEquation);
		    });
		}
	}
	
	@Test
	@DisplayName("Test 002 -- Division e.g. 12/12 returns Double 1.0")
	public void divisionSameBySameTest() {
		Double calculatedResult;
		String[] validMathEquations = new String[] {"12/12", "44.8/44.8", "44.8/44.8", "-8/-8"};
		
		for (String validEquation : validMathEquations) {
			calculatedResult = calculator.calculate(validEquation);
			assertEquals((Double)1.0, calculatedResult);
		}
	}
	
	@Test
	@DisplayName("Test 003 -- Division 0/1.9 returns Double 0.0")
	public void divisionZeroByNumberTest() {
		Double calculatedResult;
		String[] validMathEquations = new String[] {"0/1.9", "0/1000000", "0/-787"};
		
		for (String validEquation : validMathEquations) {
			calculatedResult = calculator.calculate(validEquation);
		    assertEquals(Double.valueOf(0), calculatedResult);
		}
	}
	
	@Test
	@DisplayName("Test 004 -- usage of alphanumerical&other invalid signs in equation throw an Exception")
	public void usageOfAlphanumericalSignsInEquation() {
		String[] invalidMathEquations = new String[] {"a+-8.5", "k+77", "dfd*8.5", "f+o.9", "16+j.7", "", " * ", "   ", "12", "111.9"};
		
		for (String invalidEquation : invalidMathEquations) {
		    assertThrows(IllegalArgumentException.class, () -> {
		    	calculator.calculate(invalidEquation);
		    });
		}
	}
	
	@Test
	@DisplayName("Test 005 -- check whether functionality of math operation e.g. 3+-2 == 3 - 2 == 1 works properly")
	public void plusMinusFuntionality() {
		String mathEquation1 = "16+-8.5";
		Double calculatedResult1 = calculator.calculate(mathEquation1);
	    assertEquals((Double)7.5, calculatedResult1);
	    
	    String mathEquation2 = "-16.0+-8.5";
		Double calculatedResult2 = calculator.calculate(mathEquation2);
	    assertEquals(Double.valueOf(-24.5), calculatedResult2);
	    
	    String mathEquation3 = "900+-900";
		Double calculatedResult3 = calculator.calculate(mathEquation3);
	    assertEquals(Double.valueOf(0), calculatedResult3);
	}
	
	@Test
	@DisplayName("Test 006 -- check whether trimming of input works properly")
	public void trimInputFuntionality() {
		String mathEquation1 = "    16+-8.5 ";
		Double calculatedResult1 = calculator.calculate(mathEquation1);
	    assertEquals((Double)7.5, calculatedResult1);
	    
	}

}
