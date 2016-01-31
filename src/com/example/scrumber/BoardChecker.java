package com.example.scrumber;

import com.example.scrumber.Location.Direction;
import com.example.scrumber.OperationTile.OperationType;
import java.util.Collection;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Checks a Board for proper Equations. Equations are read from left to right
 * and from top to bottom.
 *
 * @author Divyansh Gupta
 * @version Jan 26, 2015
 */
public class BoardChecker {

	private static ArrayList<Location> usedLocations = new ArrayList<Location>();
	private static Board board;
	private static final Direction[] directionsToCheck = new Direction[] {
	    Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH };

	// ----------------------------------------------------------
	/**
	 * Verifies the board that is passed in, toVerify, for properly formed,
	 * original equations that are mathematically correct.
	 *
	 * @param toVerify is the Board to verify.
	 * @return whether or not the Board is valid.
	 */
	public static Collection<Location> verifyBoard(Board toVerify) {
		board = toVerify;
		ArrayList<Equation> eqnList = getEquations();
		boolean verified = verifyEquations(eqnList) && eqnList.size() > 0;
		if (verified) {
			return usedLocations;
		}
		return null;
	}

	// ----------------------------------------------------------
	/**
	 * Verifies each Equation passed in for mathematical and syntactical
	 * correctness.
	 *
	 * @param eqnList is the List of syntactically correct equations on the
	 *            board.
	 * @return if any of the passed in Equations are invalid, returns false.
	 *         Else, true.
	 */
	private static boolean verifyEquations(ArrayList<Equation> eqnList) {
		int scoreUpdate = 0;
		for (Equation eqn : eqnList) {
			int eqnVal = eqn.verify();
			if (eqnVal == Integer.MAX_VALUE) {
				board.setScoreUpdate(0);
				return false;
			}
			scoreUpdate += Math.abs(eqnVal);
		}
		board.setScoreUpdate(scoreUpdate);
		return true;
	}

	// ----------------------------------------------------------
	/**
	 * Gets all the syntactically correct, original equations on the board.
	 *
	 * @return the list of all syntactically correct, unique equations on the
	 *         board.
	 */
	private static ArrayList<Equation> getEquations() {
		usedLocations = new ArrayList<Location>();
		ArrayList<Location> equalTileLocations = getEqualTileLocations();
		if (equalTileLocations.size() > 0) {
			StringBuilder strEqns = new StringBuilder();
			for (Location eqLoc : equalTileLocations) {
				usedLocations.add(eqLoc);
				for (Direction direction : directionsToCheck) {
					Tile tile = new OperationTile(OperationType.ADDITION,
					    new Location(-1, -1));
					Location tempEqLoc = eqLoc;
					while (validNonBlankTile(tile)) {
						Location shifted = tempEqLoc.directionOf(direction);
						tile = board.getTile(shifted);
						tempEqLoc = shifted;
						if (validNonBlankTile(tile)) {
							usedLocations.add(shifted);
							strEqns.append(tile.getInternalValue());
						} else {
							strEqns.append(" |");
						}
					}
				}
			}
			return parseEquationString(strEqns.toString());
		}
		return new ArrayList<Equation>();
	}

	// ----------------------------------------------------------
	/**
	 * Takes an Equation String (produced by getEquations() method) and parses
	 * it for the 4 separate expressions involved. If the expressions are
	 * original and syntactically correct equations, the list of valid equations
	 * is returned.
	 *
	 * @param strEqns is the Equation string containing the 4 seperate
	 *            expressions, to be parsed.
	 * @return the list of valid expressions.
	 */
	private static ArrayList<Equation> parseEquationString(String strEqns) {
		ArrayList<Equation> eqns = new ArrayList<Equation>();
		String[] strEqnsList = strEqns.toString().split("\\|");

		// East
		String eastTrim = strEqnsList[0].trim();
		String east = internalToExternalExpression(eastTrim);

		// West
		String westTrim = strEqnsList[1].trim();
		String westOrig = Equation.reverseEquation(westTrim);
		String west = internalToExternalExpression(westOrig);
		boolean identicalEqnsEW = eastTrim.equals(westTrim)
		    || eastTrim.equals(westOrig) || west.equals(east);

		// South
		String southTrim = strEqnsList[2].trim();
		String south = internalToExternalExpression(southTrim);

		// North
		String northTrim = strEqnsList[3].trim();
		String northOrig = Equation.reverseEquation(northTrim);
		String north = internalToExternalExpression(northOrig);
		boolean identicalEqnsNS = southTrim.equals(northTrim)
		    || southTrim.equals(northOrig) || north.equals(south);

		String horizEqn = east + "=" + west;
		String vertEqn = south + "=" + north;
		if (!horizEqn.equals("=") && !identicalEqnsEW) {
			eqns.add(new Equation(horizEqn));
		}
		if (!vertEqn.equals("=") && !identicalEqnsNS) {
			eqns.add(new Equation(vertEqn));
		}
		return eqns;
	}

	// ----------------------------------------------------------
	/**
	 * Some tiles toString() methods return an external representation and an
	 * internal representation of the value. This method turns any internal
	 * expression into an external representation.
	 *
	 * @param str is the internal representation of the Tile value.
	 * @return the external representation of the Tile value.
	 */
	private static String internalToExternalExpression(String str) {
		String exps[] = Equation.parseEquation(str);
		StringBuilder builder = new StringBuilder("");
		for (int i = 0; i < exps.length; i++) {
			String exp = exps[i];
			if (exp.length() > 0) {
				if (exp.charAt(0) == '0' && exp.length() > 1) {
					exp = exp.replaceFirst("0", "-");
				}
			}
			builder.append(exp);
		}
		return builder.toString();
	}

	// ----------------------------------------------------------
	/**
	 * Get all the Locations on the board where an OperationTile of
	 * OperationType EQUALS exists.
	 *
	 * @return a list of Locations where an EQUALS tile exists.
	 */
	private static ArrayList<Location> getEqualTileLocations() {
		ArrayList<Location> equalTileLocations = new ArrayList<Location>(
		    board.size() * board.size() / 2);
		for (int x = 0; x < board.size(); x++) {
			for (int y = 0; y < board.size(); y++) {
				Location tileLoc = new Location(x, y);
				Tile tile = board.getTile(tileLoc);
				if (tile instanceof OperationTile) {
					OperationTile oTile = (OperationTile) tile;
					if (oTile.getOperationType() == OperationType.EQUAL
					    && !board.getFilledLocations().contains(tileLoc)) {
						equalTileLocations.add(tileLoc);
					}
				}
			}
		}
		return equalTileLocations;
	}

	// ----------------------------------------------------------
	/**
	 * Checks for valid equation tiles that are not blank. If the passed in tile
	 * is blank, not a valid tile (ex. out of bounds of the board), or an equal
	 * sign, method returns false. Otherwise, true.
	 *
	 * @param toCheck is the tile to check for validity.
	 * @return whether the Tile is valid or not, as per the stated rules of
	 *         validity given above.
	 */
	public static boolean validNonBlankTile(Tile toCheck) {
		if (toCheck != null) {
			if (toCheck instanceof OperationTile) {
				OperationTile oTile = (OperationTile) toCheck;
				return !(oTile.getOperationType() == OperationType.EQUAL);
			}
			return (toCheck instanceof NumberTile);
		}
		return false;
	}
}