package com.example.scrumber;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Vibrator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.widget.TextView;
import android.media.MediaPlayer;
import java.io.IOException;
import com.example.scrumber.OperationTile.OperationType;
import sofia.graphics.ShapeView;
import java.util.Observable;
import java.util.Observer;
import sofia.app.ShapeScreen;

/**
 * // -------------------------------------------------------------------------
 * /** Write a one-sentence summary of your class here. Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author Divyansh Gupta
 * @version Nov 15, 2014
 */
public class ScrumberScreen extends ShapeScreen implements Observer, Pausable {

	private static final String BACKGROUND_MP3 = "background";
	private static final String BLANK_TILE_IMAGE = "blank_tile.png";

	private TileView[][] boardGrid;
	private Board board;
	private final int DEFAULT_SIZE = 9;
	private ToggleButtonGroup tButtons;
	private Player player;
	private TextView currScore;
	private Vibrator vibrator;
	private CountDownTimerView timer;
	private MediaPlayer musicPlayer;
	private boolean paused;
	private boolean isFinished;

	// ----------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// overridePendingTransition(anim.fade_in, anim.fade_out);
	}

	// ----------------------------------------------------------
	/**
	 * Sets up the Screen View to have a basic maze of DEFAULT_SIZE.
	 *
	 * @param num
	 */
	public void initialize(int num) {
		// overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		boardGrid = new TileView[DEFAULT_SIZE][DEFAULT_SIZE];
		this.createBoardGrid();
		board = new Board(DEFAULT_SIZE, this);
		vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
		board.addObserver(this);
		player = board.getPlayer();
		initializeButtons();
		this.updateHandButtons();
		timer = (CountDownTimerView) this.findViewById(R.id.timerView);
		timer.setScreen(this);
		timer.setFormat("m:ss");
		timer.start();
		this.musicPlayerSetup();
		this.paused = false;
		this.isFinished = false;
	}

	// ----------------------------------------------------------
	/**
	 * Setup the music player for usage.
	 */
	private void musicPlayerSetup() {
		musicPlayer = new MediaPlayer();
		musicPlayer.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				if (!mp.isPlaying()) {
					mp.start();
				}
			}
		});
		musicPlayer.setLooping(true);
		this.playSoundResource(BACKGROUND_MP3);
	}

	// ----------------------------------------------------------
	private void updateHandButtons() {
		player.makeHand();
		for (int i = 0; i < 5; i++) {
			HandButton toChange = (HandButton) tButtons.getButtonList().get(i);
			NumberTile tile = (NumberTile) board.getPlayer().getHand().get(i);
			int num = tile.getTileNum();
			toChange.setTile(tile);
			toChange.setAllText(Integer.toString(num));
		}
	}

	// ----------------------------------------------------------
	/**
	 * Creates and sets up the visual representation of the HandButtons the
	 * player uses to select Tiles.
	 */
	private void initializeButtons() {
		tButtons = new ToggleButtonGroup();
		HandButton one = (HandButton) this.findViewById(R.id.hand1);
		HandButton two = (HandButton) this.findViewById(R.id.hand2);
		HandButton three = (HandButton) this.findViewById(R.id.hand3);
		HandButton four = (HandButton) this.findViewById(R.id.hand4);
		HandButton five = (HandButton) this.findViewById(R.id.hand5);
		HandButton plus = (HandButton) this.findViewById(R.id.plus);
		HandButton minus = (HandButton) this.findViewById(R.id.minus);
		HandButton equal = (HandButton) this.findViewById(R.id.equal);
		tButtons.add(one, two, three, four, five, plus, minus, equal);
		tButtons.setHapticFeedback(true);
		OperationTile plusTile = new OperationTile(OperationType.ADDITION,
		    new Location(-1, -1));
		plus.setTile(plusTile);
		plus.setAllText("+");
		OperationTile minusTile = new OperationTile(OperationType.SUBTRACTION,
		    new Location(-1, -1));
		minus.setTile(minusTile);
		minus.setAllText("-");
		OperationTile equalTile = new OperationTile(OperationType.EQUAL,
		    new Location(-1, -1));
		equal.setTile(equalTile);
		equal.setAllText("=");
	}

	// ----------------------------------------------------------
	/**
	 * Create the visual aspect of the BoardGrid for the user to interact with.
	 */
	private void createBoardGrid() {
		ImageShapeView shapeView = (ImageShapeView) findViewById(R.id.shapeView);
		float mazePixelSize = Math.min(shapeView.getHeight(),
		    shapeView.getWidth());
		float cellSize = mazePixelSize / DEFAULT_SIZE;
		//Bitmap blankTileImage = BitmapFactory.decodeResource(getResources(), R.drawable.blank_tile);
		for (int x = 0; x < DEFAULT_SIZE; x++) {
			float left = cellSize * x;
			float right = cellSize * (x + 1);
			for (int y = 0; y < DEFAULT_SIZE; y++) {
				float top = cellSize * y;
				float bottom = cellSize * (y + 1);
				//TileView tile = new TileView(left, top, right, bottom,
				//    new Location(x, y));
				//tile.setImage(new TextImage(blankTileImage, ""));
				//this.add(tile);
				//boardGrid[x][y] = tile;
			}
		}
	}

	// ----------------------------------------------------------
	/**
	 * If board is modified, ScrumberScreen will automatically update itself to
	 * represent it better, using this method.
	 */
	@Override
	public void update(Observable oBoard, Object location) {
		Location loc = (Location) location;
		TileView tile = boardGrid[loc.x()][loc.y()];
		tile.changeTileText(board.getTile(loc), this);
	}

	// ----------------------------------------------------------
	/**
	 * Processes a tap, click touch on the Screen.
	 *
	 * @param x is the x coordinate of the touch on the screen.
	 * @param y is the y coordinate of the touch on the screen.
	 */
	public void onTouchDown(float x, float y) {
		processTouch(x, y);
	}

	// ----------------------------------------------------------
	/**
	 * Process a touch and see if there is any intent by the user behind the
	 * Touch.
	 */
	private void processTouch(float x, float y) {
		TileView tile = getShapes().locatedAt(x, y).withClass(TileView.class)
		    .front();
		if (tile != null && tButtons.getCheckedButton() != null) {
			this.handleTileTouch(tile);
		}
	}

	// ----------------------------------------------------------
	/**
	 * Handle the actions of the Screen if a Tile on the Board of the Screen is
	 * actually touched and an action is intended.
	 */
	private void handleTileTouch(TileView tile) {
		Location tileLoc = tile.getTileLocation();
		if (!board.getFilledLocations().contains(tileLoc)) {
			Tile savedTile = board.getTile(tileLoc);
			HandButton checked = (HandButton) tButtons.getCheckedButton();
			Tile bTile = checked.getTile();
			board.setTile(tileLoc, bTile);
			player.addMoveToMemory(new Move(savedTile, tileLoc));
		} else {
			vibrator.vibrate(50);
		}
	}

	// ----------------------------------------------------------
	/**
	 * Determines what happens when commit is clicked.
	 */
	public void commitClicked() {
		if (board.verifyBoard()) {
			this.updateHandButtons();
			player.clearMovesMemory();
			currScore.setText(String.valueOf(board.getScore()));
		} else {
			this.showPopupDialog("Turn Play Failed",
			    "Make sure the board has valid equations "
			        + "and a new equation has been added since the last turn!");
		}
	}

	// ----------------------------------------------------------
	/**
	 * Show a pop-up dialog on the screen displaying a message to the user until
	 * the user clicks "Ok" to exit the dialog box.
	 *
	 * @param title is the title of the dialog box.
	 * @param message is the message that the dialog box will contain.
	 */
	public void showPopupDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK",
		    new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int which) {
				    // Add your code for the button here.
			    }
		    });
		// Set the Icon for the Dialog
		// alertDialog.setIcon(R.drawable.icon);
		alertDialog.show();
	}

	// ----------------------------------------------------------
	/**
	 * Determines what happens when undo button is clicked.
	 */
	public void undoClicked() {
		Move toUndo = player.getLastMove();
		if (toUndo != null) {
			board.setTile(toUndo.getLocation(), toUndo.getTile());
			vibrator.vibrate(50);
		}
	}

	// ----------------------------------------------------------
	/**
	 * Determines what happens when the menu button is clicked.
	 */
	public void menuClicked() {
		player.clearMovesMemory();
		this.presentScreen(HomeScreen.class, 1);
	}

	// ----------------------------------------------------------
	/**
	 * Determines what happens when the menu button is clicked.
	 */
	public void resetClicked() {
		this.updateHandButtons();
		this.incrementScore(-5);
	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 *
	 * @param toIncrement
	 */
	public void incrementScore(int toIncrement) {
		board.getScoreKeeper().incrementScore(toIncrement);
		currScore.setText(String.valueOf(board.getScore()));
	}

	// ----------------------------------------------------------
	/**
	 * Get the Visual representation of the Hand (HandButtons in a list).
	 *
	 * @return the HandButtons in a list that represent the Player's hand on the
	 *         board.
	 */
	public ToggleButtonGroup getHandButtons() {
		return tButtons;
	}

	// ----------------------------------------------------------
	/**
	 * Pauses the screen and pauses game-play.
	 */
	@Override
	public void pause() {
		if (timer != null && !timer.isPaused()) {
			timer.pause();
		}
		if (musicPlayer != null && musicPlayer.isPlaying()) {
			musicPlayer.pause();
		}
		this.paused = true;
	}

	// ----------------------------------------------------------
	/**
	 * Resumes the screen and gameplay, usually after onPause() has been called.
	 */
	@Override
	public void resume() {
		if (isFinished) {
			this.initialize(1);
		}
		if (musicPlayer != null && !musicPlayer.isPlaying()) {
			musicPlayer.start();
		}
		this.paused = false;
	}

	// ----------------------------------------------------------
	/**
	 * Get if Screen is paused state or not.
	 *
	 * @return whether or not screen is paused.
	 */
	@Override
	public boolean isPaused() {
		return paused;
	}

	// ----------------------------------------------------------
	/**
	 * Overrides the onPause() method to also pause JinglePlayer music if it is
	 * playing.
	 */
	@Override
	public void onPause() {
		super.onPause();
		this.pause();
	}

	// ----------------------------------------------------------
	/**
	 * When Screen is resumed, Screen will also resume playing Jingles if any
	 * Jingle was paused previously (likely due to onPause() method).
	 */
	@Override
	public void onPostResume() {
		super.onPostResume();
		if (timer != null && this.paused) {
			timer.start();
		}
		this.resume();
	}

	// ----------------------------------------------------------
	/**
	 * When Screen is destroyed, the JinglePlayer associated with it is released
	 * and freed up for Garbage Collection.
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (musicPlayer != null) {
			musicPlayer.release();
			musicPlayer = null;
		}
	}

	// ----------------------------------------------------------
	@Override
	public void onTick() {
		// Do Nothing for now, homie
	}

	// ----------------------------------------------------------
	@Override
	public void onFinish() {
		board.getScoreKeeper().saveCurrentScore();
		timer.pause();
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Game Over!");
		alertDialog
		    .setMessage("Good Game! Your score was: " + board.getScore());

		alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Go to High Scores",
		    new DialogInterface.OnClickListener() {

			    @Override
			    public void onClick(DialogInterface dialog, int which) {
				    ScrumberScreen.this.finish();
				    ScrumberScreen.this.presentScreen(ScoreScreen.class, 2);
			    }
		    });
		alertDialog.setCancelable(false);
		alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Go Back to Menu",
		    new DialogInterface.OnClickListener() {

			    @Override
			    public void onClick(DialogInterface dialog, int which) {
				    ScrumberScreen.this.finish();
				    ScrumberScreen.this.presentScreen(HomeScreen.class, 2);
				    // ScrumberScreen.this.musicPlayer.release();
			    }
		    });

		alertDialog.show();
		isFinished = true;
	}

	// ----------------------------------------------------------
	/**
	 * Play any sound resource based on the path that is passed in.
	 *
	 * @param resourceName is the name of resource to play.
	 */
	public void playSoundResource(String resourceName) {
		String path = "android.resource://" + this.getPackageName() + "/raw/"
		    + resourceName;
		try {
			musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			musicPlayer.setDataSource(this.getApplicationContext(),
			    Uri.parse(path));
			musicPlayer.prepareAsync();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		ScrumberScreen.this.presentScreen(HomeScreen.class, 2);
	}
}