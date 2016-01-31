package com.example.scrumber;

/**
 * // -------------------------------------------------------------------------
 * /** Write a one-sentence summary of your class here. Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author Divyansh Gupta (divyg) and Sean Crenshaw.
 * @version Nov 15, 2014
 */
public class OperationTile extends Tile {

	private OperationType oType;

	/**
	 * // ----------------------------------------------------------------------
	 * --- /** Write a one-sentence summary of your class here. Follow it with
	 * additional details about its purpose, what abstraction it represents, and
	 * how to use it.
	 *
	 * @author Divyansh Gupta
	 * @version Nov 15, 2014
	 */
	public enum OperationType {

		/**
		 * An OperationTile that will represent the addition operation.
		 */
		ADDITION,

		/**
		 * An OperationTile that will represent the subtraction operation.
		 */
		SUBTRACTION,

		/**
		 * An OperationTile that will represent the Equals operation.
		 */
		EQUAL;
	}

	// ----------------------------------------------------------------------

	// ----------------------------------------------------------
	/**
	 * Create a new OperationTile object.
	 *
	 * @param type is the OperationType that the OperationTile will represent.
	 * @param tileLocation is the Location of the OperationTile.
	 */
	public OperationTile(OperationType type, Location tileLocation) {
		super(tileLocation);
		oType = type;
	}

	// ----------------------------------------------------------
	/**
	 * Get the OperationType that the OperationTile represents.
	 *
	 * @return the OperationType that the OperationTile represents.
	 */
	public OperationType getOperationType() {
		return oType;
	}

	// ----------------------------------------------------------
	/**
	 * Set the OperationType that the OperationTile represents.
	 *
	 * @param newOType is the new OperationType that the OperationTile will
	 *            represent after this method is invoked.
	 */
	public void setOperationType(OperationType newOType) {
		this.oType = newOType;
	}

	// ----------------------------------------------------------
	/**
	 * Get the value of the OperationTile.
	 *
	 * @return the value of the Tile.
	 */
	public String value() {
		String toReturn = "";
		switch (oType) {
			case ADDITION:
				toReturn = "+";
				break;
			case SUBTRACTION:
				toReturn = "-";
				break;
			case EQUAL:
				toReturn = "=";
				break;
		}
		return toReturn;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @return the String value of the tile
	 */
	protected String getInternalValue() {
		return this.value();
	}
}