package es.ereadme.sphero.morsetrainer.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import es.ereadme.sphero.morsetrainer.R;
import es.ereadme.sphero.morsetrainer.constants.Constants;
import es.ereadme.sphero.morsetrainer.interfaces.ILetterListener;
import es.ereadme.sphero.morsetrainer.services.SpheroKeyboardService;

public class ContestActivity extends Activity {
	ILetterListener mListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contest);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent i=new Intent(this, SpheroKeyboardService.class);
	    
	    i.putExtra(SpheroKeyboardService.EXTRA_MESSENGER, new Messenger(handler));
	    startService(i);
		
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
	
	
	private Handler handler=new Handler() {
	    @Override
	    public void handleMessage(Message msg) {
	    	Log.d(Constants.TAG, "Message received from service");
	    	if(mListener!=null) 
        		mListener.onNewLetterEvent();
	    }
	  };
	

}
