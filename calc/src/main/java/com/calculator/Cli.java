package com.calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.calculator.model.Calculator;

public class Cli {
	
	private BufferedWriter writer;
	private BufferedReader reader;

	public Cli() {
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	private void print(String text) throws IOException {
		writer.write(text);
		writer.flush();
	}

	private void println(String text) throws IOException {
		print(text + "\r\n");
	}

	private String read() throws IOException {
		return reader.readLine().trim();
	}

	private void displayMenu() throws IOException {
		println("\n--- Calculator ---");
		println("Write an equation, e.g. 2+4 OR 11+-1");
		println("//For exit: \"exit\"");
		println(">> ");
	}

	public void start() {
		try {
			Calculator calc = new Calculator();
			do {
				displayMenu();
				String usersEquation = read();
				if (usersEquation.toLowerCase().equals("exit")) {
					break;
				} else {
					try {
						System.out.println(calc.calculate(usersEquation));
						println("");
					} catch (IllegalArgumentException iae) {
						println("Invalid input. Please try again.");
					} catch (ArithmeticException ae) {
						println("Can't divide by zero.");
					}
				}
			} while (true);
			println("Thanks for choosing our calculator! ;).");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
