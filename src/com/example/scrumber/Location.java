package com.example.scrumber;

// -------------------------------------------------------------------------
/**
 * Represents a Location or cell point in the maze, based on it's x and y
 * coordinates. Indicates positions in the maze.
 *
 * @author Divyansh Gupta (divyg)
 * @version Sep 22, 2014
 */
public class Location {
	private int x;
	private int y;

	// -------------------------------------------------------------------------
	/**
	 * Write a one-sentence summary of your class here. Follow it with
	 * additional details about its purpose, what abstraction it represents, and
	 * how to use it.
	 *
	 * @author Divyansh Gupta
	 * @version Nov 18, 2014
	 */
	public enum Direction {

		/**
		 * Represents the East Direction.
		 */
		EAST,

		/**
		 * Represents the North Direction.
		 */
		NORTH,

		/**
		 * Represents the West Direction.
		 */
		WEST,

		/**
		 * Represents the South Direction.
		 */
		SOUTH;

		// ----------------------------------------------------------
		/**
		 * Get the opposite Direction.
		 *
		 * @return the Opposite Direction of the Direction it is called upon.
		 */
		public Direction opposite() {
			switch (this) {
				case NORTH:
					return Direction.SOUTH;
				case SOUTH:
					return Direction.NORTH;
				case EAST:
					return Direction.WEST;
				case WEST:
					return Direction.EAST;
				default:
					throw new IllegalStateException(
					    "This should never happen: " + this
					        + " has no opposite.");
			}
		}
	}

	// -------------------------------------------------------------------------
	/**
	 * Write a one-sentence summary of your class here. Follow it with
	 * additional details about its purpose, what abstraction it represents, and
	 * how to use it.
	 *
	 * @author Divyansh Gupta
	 * @version Jan 27, 2015
	 */
	public enum Orientation {
		/**
		 * Represents the East and West Direction.
		 */
		HORIZONTAL,

		/**
		 * Represents the South and North Direction.
		 */
		VERITICAL;

		// ----------------------------------------------------------
		/**
		 * Get the opposite Orientation.
		 *
		 * @return the Opposite Direction of the Direction it is called upon.
		 */
		public Orientation opposite() {
			switch (this) {
				case HORIZONTAL:
					return Orientation.VERITICAL;
				case VERITICAL:
					return Orientation.HORIZONTAL;
				default:
					throw new IllegalStateException(
					    "This should never happen: " + this
					        + " has no opposite.");
			}
		}
	}

	// ----------------------------------------------------------

	/**
	 * Create a new Location object.
	 *
	 * @param locationX is the x coordinate of the new Location object.
	 * @param locationY is the y coordinate of the new Location object.
	 */
	public Location(int locationX, int locationY) {
		this.x = locationX;
		this.y = locationY;
	}

	// ----------------------------------------------------------
	/**
	 * Gets a new Location that represents the (x, y) coordinates one cell south
	 * of this Location.
	 *
	 * @return new Location object that represents the coordinate one cell east
	 *         of this Location.
	 */
	public Location east() {
		return new Location(this.x + 1, this.y);
	}

	// ----------------------------------------------------------
	/**
	 * Gets a new Location that represents the (x, y) coordinates one cell north
	 * of this Location.
	 *
	 * @return new Location object that represents the coordinate one north of
	 *         this Location.
	 */
	public Location north() {
		return new Location(this.x, this.y - 1);
	}

	// ----------------------------------------------------------
	/**
	 * Gets a new Location that represents the (x, y) coordinates one cell south
	 * of this Location.
	 *
	 * @return new Location object that represents the coordinate one cell west
	 *         of this Location.
	 */
	public Location south() {
		return new Location(this.x, this.y + 1);
	}

	// ----------------------------------------------------------
	/**
	 * Gets a new Location that represents the (x, y) coordinates one cell west
	 * of this Location.
	 *
	 * @return new Location object that represents the coordinate one cell west
	 *         of this Location.
	 */
	public Location west() {
		return new Location(this.x - 1, this.y);
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param direction is the direction of the Location to find, with respect
	 *            to this Location.
	 * @return the Location that is to the passed in direction of this Location.
	 */
	public Location directionOf(Direction direction) {
		switch (direction) {
			case EAST:
				return this.east();
			case WEST:
				return this.west();
			case SOUTH:
				return this.south();
			case NORTH:
				return this.north();
			default:
				return null;
		}
	}

	// ----------------------------------------------------------
	/**
	 * Gets the x coordinate of the Location object.
	 *
	 * @return the x coordinate of the Location object.
	 */
	public int x() {
		return this.x;
	}

	// ----------------------------------------------------------
	/**
	 * Gets the y coordinate of the Location object.
	 *
	 * @return the y coordinate of the Location object.
	 */
	public int y() {
		return this.y;
	}

	// ----------------------------------------------------------
	/**
	 * Checks "location" Object for equality with this Location object.
	 *
	 * @param location is the object to compare against.
	 * @return true if both objects are Location's with the same x and y value.
	 *         Return false otherwise.
	 */
	public boolean equals(Object location) {
		if (location instanceof Location) {
			Location toCompare = (Location) location;
			return ((toCompare.x == this.x) && (toCompare.y == this.y));
		}
		return false;
	}

	// ----------------------------------------------------------
	/**
	 * Returns the Location object's coordinates in "(x, y)" form.
	 *
	 * @return a human readable representation of the Location object's
	 *         coordinates in "(x, y)" form.
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	// ----------------------------------------------------------
	/**
	 * Gets the distance between two Locations.
	 *
	 * @param position is the Location that the distance will be calculated to.
	 * @return the distance between the this Location and the given position.
	 */
	public double getDistance(Location position) {
		int dx = this.x - position.x();
		int dy = this.y - position.y();
		return Math.sqrt(dx * dx + dy * dy);
	}

	// ----------------------------------------------------------
	/**
	 * Find the direction of a Location object in reference to this one.
	 *
	 * @param location is the Location with which this Location will be
	 *            compared.
	 * @return the Direction the passed in Location is in reference to this one.
	 */
	public Direction getDirection(Location location) {
		Direction direction = null;
		if (this.equals(location.south())) {
			direction = Direction.NORTH;
		} else if (this.equals(location.north())) {
			direction = Direction.SOUTH;
		} else if (this.equals(location.east())) {
			direction = Direction.WEST;
		} else if (this.equals(location.west())) {
			direction = Direction.EAST;
		}
		return direction;
	}
}
