package com.example.scrumber;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Divyansh Gupta
 * @version Jan 27, 2015
 */

public class EquationTest extends TestCase {

	private static Equation eqn;

	// ----------------------------------------------------------
	/**
	 * Sets up each test method.
	 */
	public void setUp() {
		eqn = new Equation("");
	}

	/**
	 * Test method for {@link com.example.scrumber.Equation#verify()}.
	 */
	public void testVerify() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.example.scrumber.Equation#evaluateExpression(java.lang.String)}
	 * .
	 */
	public void testEvaluateExpression() {
		String expression = "3+7";
		assertEquals(10, eqn.evaluateExpression(expression));

		String expression1 = "13+7";
		assertEquals(20, eqn.evaluateExpression(expression1));

		String expression11 = "-13+7";
		assertEquals(-6, eqn.evaluateExpression(expression11));

		String expression111 = "-13+7-4+4--4";
		assertEquals(-2, eqn.evaluateExpression(expression111));
	}

}
