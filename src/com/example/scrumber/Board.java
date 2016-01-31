package com.example.scrumber;

import java.util.Collection;
import android.content.Context;
import java.util.ArrayList;
import sofia.app.ShapeScreen;
import java.util.Observable;

/**
 * This class represents the game board.
 *
 * @author Divyansh Gupta (divyg)
 * @version Nov 15, 2014
 */
public class Board extends Observable {

	private int size;
	private Tile[][] board;
	private int scoreUpdate;
	private ShapeScreen screen;
	private Player player;
	private BoardChecker checker;
	private ScoreKeeper sKeeper;
	private Collection<Location> filledLocations;

	// ----------------------------------------------------------
	/**
	 * Create a new Board object.
	 *
	 * @param size is the size of the width and height of the square Board
	 *            object.
	 */
	public Board(int size) {
		filledLocations = new ArrayList<Location>();
		player = new Player(this);
		this.size = size;
		board = new Tile[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				board[x][y] = new Tile(x, y);
			}
		}
	}

	// ----------------------------------------------------------
	/**
	 * Create a new Board object, with a ShapeScreen context associated with it.
	 *
	 * @param size is the size of the width and height of the square Board
	 *            object.
	 * @param context is the Screen associated with the Board.
	 */
	public Board(int size, Context context) {
		this(size);
		sKeeper = new ScoreKeeper(context.getApplicationContext());
	}

	// ----------------------------------------------------------
	/**
	 * Sets the tile at the given x and y coordinates on the Board object to be
	 * a new Tile.
	 *
	 * @param location is the is Location on the Board at which to place the new
	 *            Tile.
	 * @param newTile is the Tile to replace the current Tile object with.
	 */
	public void setTile(Location location, Tile newTile) {
		if (withinBounds(location)) {
			board[location.x()][location.y()] = newTile;
			this.setChanged();
			this.notifyObservers(location);
		} else {
			throw new IllegalStateException(
			    "Error in setTile() method! Given x and y coordinates"
			        + " are out of bounds!");
		}
	}

	// ----------------------------------------------------------
	/**
	 * Get whether or not given Location is located within the Board object's
	 * boundaries.
	 *
	 * @param location is the Location to check for in the Board.
	 * @return if given Location is within the Board object's boundaries.
	 */
	private boolean withinBounds(Location location) {
		int x = location.x();
		int y = location.y();
		return (x < size && x >= 0 && y < size && y >= 0);
	}

	// ----------------------------------------------------------
	/**
	 * Get the Tile located at the given coordinates on the Board.
	 *
	 * @param location is the Location at which the Tile will be returned.
	 * @return the Tile located at the given coordinates on the Board.
	 */
	public Tile getTile(Location location) {
		if (withinBounds(location)) {
			return board[location.x()][location.y()];
		}
		return null;
	}

	// ----------------------------------------------------------
	/**
	 * Checks to make sure that equations on the Board make sense.
	 *
	 * @return whether or not the current state of the Board is valid or not.
	 */
	public boolean verifyBoard() {
		if (checker == null) {
			checker = new BoardChecker();
		}
		Collection<Location> locsUsed = BoardChecker.verifyBoard(this);
		if (locsUsed != null && locsUsed.size() > 0) {
			filledLocations.addAll(locsUsed);
			sKeeper.setScore(sKeeper.getScore() + this.scoreUpdate);
			this.scoreUpdate = 0;
			return true;
		}
		this.scoreUpdate = 0;
		return false;
	}

	// ----------------------------------------------------------
	/**
	 * @param location
	 * @return the Tiles surrounding the given Location that are Filled
	 *         (non-blank).
	 *
	 */
	public ArrayList<Tile> getSurroundingFilledTiles(Location location) {
		ArrayList<Tile> tiles = getSurroundingTiles(location);
		ArrayList<Tile> toReturn = new ArrayList<Tile>();
		for (Tile tile : tiles) {
			if (tile instanceof OperationTile || tile instanceof NumberTile) {
				toReturn.add(tile);
			}
		}
		return toReturn;
	}

	// ----------------------------------------------------------
	/**
	 *
	 */
	private ArrayList<Tile> getSurroundingTiles(Location location) {
		Location[] coords = { location.west(), location.north(),
		    location.east(), location.south() };
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		for (Location coordinates : coords) {
			Tile potential = this.getTile(coordinates);
			if (potential != null) {
				tiles.add(potential);
			}
		}
		return tiles;
	}

	// ----------------------------------------------------------
	/**
	 * Get the Player that is playing on this Board.
	 *
	 * @return the Player that is playing on this Board.
	 */
	public Player getPlayer() {
		return player;
	}

	// ----------------------------------------------------------
	/**
	 * Get the Screen associated with this Screen.
	 *
	 * @return the Screen associated with this Board.
	 */
	public ShapeScreen getScreen() {
		return screen;
	}

	// ----------------------------------------------------------
	/**
	 * Returns the size of the board. (Height and width are assumed to be
	 * equal).
	 *
	 * @return the size of one dimension of the board.
	 */
	public int size() {
		return size;
	}

	// ----------------------------------------------------------
	/**
	 * @return the score on the Board.
	 *
	 */
	public int getScore() {
		return sKeeper.getScore();
	}

	// ----------------------------------------------------------
	/**
	 * @return the score on the Board.
	 *
	 */
	public ScoreKeeper getScoreKeeper() {
		return sKeeper;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @return all the Locations on the board that have already been filled and
	 *         verified to be parts of valid Equations.
	 */
	public Collection<Location> getFilledLocations() {
		return filledLocations;
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param score
	 */
	public void setScoreUpdate(int score) {
		this.scoreUpdate = score;
	}
}