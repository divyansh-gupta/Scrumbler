package com.example.scrumber;

import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.Expression;

// -------------------------------------------------------------------------
/**
 * Represents a mathematical equation. Also contains static methods to evaluate
 * expressions.
 *
 * @author Divyansh Gupta
 * @version Jan 27, 2015
 */

public class Equation {

	private final String strEquation;

	// ----------------------------------------------------------
	/**
	 * Create a new Equation object.
	 *
	 * @param strEquation
	 */
	public Equation(String strEquation) {
		this.strEquation = strEquation;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @return value of side of the equation if valid, otherwise, Integer.MAX_VALUE.
	 */
	public int verify() {
		String[] expressions = strEquation.split("=");
		if (expressions.length == 2) {
			int left = evaluateExpression(expressions[0]);
			int right = evaluateExpression(expressions[1]);
			if (left == right && left != Integer.MAX_VALUE) return left;
		}
		return Integer.MAX_VALUE;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param expression
	 *
	 * @return the value of the expression.
	 */
	public static int evaluateExpression(String expression) {
		if (expression.length() < 1)
			return Integer.MAX_VALUE;
		Expression eBuilder = new ExpressionBuilder(expression).build();
		return (int) eBuilder.evaluate();
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param num
	 * @return
	 */
	public static boolean isNumeric(String num) {
		try {
			double d = Double.parseDouble(num);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param num
	 * @return
	 */
	public static boolean isNumeric(char num) {
		return isNumeric(String.valueOf(num));
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param equation
	 * @return
	 */
	public static String[] parseEquation(String equation) {
		String[] result = equation.split("(?<=[-+*/])|(?=[-+*/])");
		return result;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param equation
	 * @return
	 */
	public static String reverseEquation(String equation) {
		String[] parsed = Equation.parseEquation(equation);
		StringBuilder reversedEqn = new StringBuilder();
		for (int i = parsed.length - 1; i >= 0; i--) {
			reversedEqn.append(parsed[i]);
		}
		return reversedEqn.toString();
	}
}