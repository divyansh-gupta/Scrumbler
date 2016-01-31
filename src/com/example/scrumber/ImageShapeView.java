package com.example.scrumber;

import android.graphics.Rect;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Divyansh Gupta
 * @version April 24, 2015
 */
public class ImageShapeView extends ShapeView implements SurfaceHolder.Callback {

	private Rect imageRect;

	// ----------------------------------------------------------
	/**
	 * Create a new ImageShapeView object.
	 *
	 * @param context
	 */
	public ImageShapeView(Context context) {
		this(context, null, 0);
	}

	// ----------------------------------------------------------
	/**
	 * Create a new ImageShapeView object.
	 *
	 * @param context
	 * @param attrs
	 */
	public ImageShapeView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	// ----------------------------------------------------------
	/**
	 * Create a new ImageShapeView object.
	 *
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ImageShapeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.getHolder().addCallback(this);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Bitmap icon = BitmapFactory.decodeResource(getResources(),
		    R.drawable.chalkboard);
		if (imageRect == null) {
			imageRect = new Rect(0, 0, icon.getWidth(), icon.getHeight());
		}
		canvas.drawColor(Color.BLACK);
		//canvas.drawBitmap(icon, null, imageRect, null);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
	    int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		super.
		repaint();
		Canvas canvas = null;
		try {
			canvas = holder.lockCanvas(null);
			synchronized (holder) {
				onDraw(canvas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}
}