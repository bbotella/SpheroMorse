package es.ereadme.sphero.morsetrainer.activities;

import orbotix.robot.base.CollisionDetectedAsyncData;
import orbotix.sphero.CollisionListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import es.ereadme.sphero.morsetrainer.R;
import es.ereadme.sphero.morsetrainer.constants.Constants;
import es.ereadme.sphero.morsetrainer.constants.GlobalObjects;
import es.ereadme.sphero.morsetrainer.interfaces.ILetterListener;

public class ContestActivity extends Activity {
	ILetterListener mListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contest);
		// Show the Up button in the action bar.
		setupActionBar();
		GlobalObjects.mRobot.getCollisionControl().addCollisionListener(mCollisionListener);
		GlobalObjects.mRobot.getCollisionControl().startDetection(45, 45, 100, 100, 100);
		this.setNewLetterEventListener(new ILetterListener() {
			@Override
			public void onNewLetterEvent() {
				//Update IU and make the checks
				Log.d(Constants.TAG, "New letter detected");
			}
		});
	}
	
	public void setNewLetterEventListener(ILetterListener eventListener) {
		mListener=eventListener;
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contest, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private final CollisionListener mCollisionListener = new CollisionListener() {
        public void collisionDetected(CollisionDetectedAsyncData collisionData) {
        	Log.d(Constants.TAG, "Collision detected");
        	//Here comes the code to detect the morse characer. When detected, throw the event
        	if(mListener!=null) 
        		mListener.onNewLetterEvent();
        }
    };
	

}
