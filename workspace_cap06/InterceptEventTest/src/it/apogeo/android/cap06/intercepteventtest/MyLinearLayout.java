/**
 * 
 */
package it.apogeo.android.cap06.intercepteventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author Utente
 *
 */
public class MyLinearLayout extends LinearLayout {

	/**
	 * @param context
	 */
	public MyLinearLayout(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/* (non-Javadoc)
	 * @see android.view.ViewGroup#onInterceptTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i("InterceptTest","MyLinearLayout onInterceptTouchEvent"+ev);
		return false;
	}

	/* (non-Javadoc)
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("InterceptTest","MyLinearLayout onTouchEvent   "+event);
		return false;
	}
	
	
	
	

}
