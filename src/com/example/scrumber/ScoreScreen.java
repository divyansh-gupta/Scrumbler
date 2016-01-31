package com.example.scrumber;

import sofia.app.Screen;
import android.os.Bundle;
import java.util.Iterator;
import java.util.TreeSet;
import android.widget.TextView;

// -------------------------------------------------------------------------
/**
 * Screen that displays the top five high scores that have been achieved when
 * playing the JingleQuiz game. Retrieves and displays scores automatically upon
 * initialization.
 *
 * @author Divyansh Gupta (divyg)
 * @version 2014.12.01
 */
public class ScoreScreen extends Screen {
	// ~ Fields ................................................................
	private TextView display;

	// ~ Methods ...............................................................
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	// ----------------------------------------------------------
	/**
	 * Initialize the screen and display top 5 Scores.
	 */
	public void initialize() {
		//overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		this.displayScores();
	}

	// ----------------------------------------------------------
	/**
	 * Initialize the screen and display top 5 Scores.
	 * @param num this doesn't actually do anything.
	 */
	public void initialize(int num) {
		this.displayScores();
	}

	// ----------------------------------------------------------
	/**
	 * Gets top 5 scores from SharedPreferences of this application and displays
	 * them for user to see.
	 */
	public void displayScores() {
		String toDisplay = "You have not played yet!";
		TreeSet<Integer> scores = new TreeSet<Integer>(
		    ScoreKeeper.keepOnlyTopScores(5, this.getApplicationContext()));
		if (scores.size() > 0) {
			toDisplay = "";
			for (Iterator<Integer> iter = scores.descendingIterator(); iter
			    .hasNext();) {
				Integer score = iter.next();
				toDisplay += score + "\n\n";
			}
		}
		display.setText(toDisplay);
	}

	// ----------------------------------------------------------
	/**
	 * Redirects back to the main menu when backToMenu button is clicked.
	 */
	public void backToMenuClicked() {
		this.presentScreen(HomeScreen.class);
	}

	// ----------------------------------------------------------
	@Override
	public void onBackPressed()
	{
	    super.onBackPressed();
	    finish();
	}
}
