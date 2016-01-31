package com.example.scrumber;

import sofia.app.Screen;
import android.os.Bundle;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * // -------------------------------------------------------------------------
 *
 * This is the main menu screen for the Scrumbler game.
 *
 * @author Divyansh Gupta
 * @version Nov 16, 2014
 */
public class HomeScreen extends Screen {

	// ~ Fields ................................................................
	private TextView titleTextView;
	private final String TITLE_FONT_PATH = "fonts/chalk_it_up_font.ttf";

	// ~ Methods ...............................................................

	// ----------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//anim anim = new anim();
		//overridePendingTransition(anim.fade_in, anim.fade_out);
	}

	/**
	 * Initialize the screen.
	 */
	public void initialize() {
		//overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	@Override
	public void onPostResume() {
		super.onPostResume();
		addTitleViewFont();
	}

	// ----------------------------------------------------------
	/**
	 * Add custom TitleView font to the HomeScreen.
	 */
	private void addTitleViewFont() {
		titleTextView = (TextView) this.findViewById(R.id.titleTextView);
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
			TITLE_FONT_PATH);
		titleTextView.setTypeface(custom_font);
	}

	// ----------------------------------------------------------
	/**
	 * Initialize the screen.
	 *
	 * @param num some bullshit number.
	 */
	public void initialize(int num) {
		addLogo();
	}

	// ----------------------------------------------------------
	/**
	 * Add Logo to the HomeScreen.
	 */
	private void addLogo() {
//		RectangleShape logoShape = new RectangleShape(0, 0, getWidth(),
//		    getHeight());
//		logoShape.setImage("ic_launcher");
//		shapeView.add(logoShape);
	}

	// ----------------------------------------------------------
	/**
	 * When play is clicked.
	 */
	public void playClicked() {
		this.presentScreen(ScrumberScreen.class, 2);
	}

	// ----------------------------------------------------------
	/**
	 * When scores is clicked.
	 */
	public void scoresClicked() {
		this.presentScreen(ScoreScreen.class, 1);
	}
}
