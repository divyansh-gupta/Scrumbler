package com.example.scrumber;

import java.util.Random;
import java.util.ArrayList;
import java.util.Stack;

/**
 * // ---------------------------------------------------------------------- ---
 * /** Represents a Person that plays a game.
 *
 * @author Divyansh Gupta and Sean Crenshaw and Bhaanu Kaul
 * @version Nov 16, 2014
 */
public class Player {

	private int score;
	private ArrayList<Tile> hand = new ArrayList<Tile>();
	private Random rand = new Random();
	private Stack<Move> moves;
	private Board board;

	// ----------------------------------------------------------
	/**
	 * Constructor for Player Sets the name to the name of the player Creates
	 * the initial hand for them.
	 *
	 * @param newBoard is the Board upon which the Player is going to play.
	 */
	public Player(Board newBoard) {
		moves = new Stack<Move>();
		score = 0;
		this.board = newBoard;
		this.makeHand();
	}

	// ----------------------------------------------------------
	/**
	 * Removes tiles from hand.
	 *
	 * @param index is the index of the tile to remove from the player's hand.
	 */
	public void subTileFromHand(int index) {
		hand.remove(index);
	}

	// ----------------------------------------------------------
	/**
	 * Creates the hands for the player
	 */
	public void makeHand() {
		hand = new ArrayList<Tile>();
		for (int i = 0; i < 5; i++) {
			int newHandTile = 0;
			while (newHandTile == 0) {
				newHandTile = rand.nextInt(17 + 17) - 17;
			}
			NumberTile tile = new NumberTile(newHandTile, new Location(-1, -1));
			hand.add(tile);
		}
	}

	// ----------------------------------------------------------
	/**
	 * Returns the score of the player
	 *
	 * @return the Player's current score.
	 */
	public int getScore() {
		return this.score;
	}

	// ----------------------------------------------------------
	/**
	 * Updates the score of the player.
	 *
	 * @param updateScore is the amount to update the Player score by.
	 */
	public void setScore(int updateScore) {
		this.score = this.score + updateScore;
	}

	// ----------------------------------------------------------
	/**
	 * Get the Hand that the Player is holding.
	 *
	 * @return the Hand that the Player is Holding.
	 */
	public ArrayList<Tile> getHand() {
		return hand;
	}

	// ----------------------------------------------------------
	/**
	 * Add a Move Player has made to his memory.
	 *
	 * @param moveMade is the Move to add to the Player's memory.
	 */
	public void addMoveToMemory(Move moveMade) {
		moves.push(moveMade);
	}

	// ----------------------------------------------------------
	/**
	 * Get the last Move the Player has made. This move is deleted from memory
	 * after it is gotten.
	 *
	 * @return the last Move the Player has made.
	 */
	public Move getLastMove() {
		if (!moves.empty()) {
			return moves.pop();
		}
		return null;
	}

	// ----------------------------------------------------------
	/**
	 * Clear Player's memory of Moves.
	 */
	public void clearMovesMemory() {
		moves.clear();
	}
}