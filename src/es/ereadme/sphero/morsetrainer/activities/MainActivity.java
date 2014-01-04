package es.ereadme.sphero.morsetrainer.activities;

import orbotix.robot.base.Robot;
import orbotix.sphero.ConnectionListener;
import orbotix.sphero.Sphero;
import orbotix.view.connection.SpheroConnectionView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import es.ereadme.sphero.morsetrainer.R;
import es.ereadme.sphero.morsetrainer.constants.GlobalObjects;

public class MainActivity extends Activity {

	private Sphero mRobot;

    /** The Sphero Connection View */
    private SpheroConnectionView mSpheroConnectionView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button contestButton = (Button) findViewById(R.id.contest_button);
		contestButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), ContestActivity.class);
				startActivity(i);
			}
		});
		
		GlobalObjects.mSpheroConnectionView = (SpheroConnectionView) findViewById(R.id.sphero_connection_view);
		GlobalObjects.mSpheroConnectionView.addConnectionListener(new ConnectionListener() {
            @Override
            public void onConnected(Robot robot) {
            	GlobalObjects.mRobot = (Sphero) robot;
            }
            @Override
            public void onConnectionFailed(Robot sphero) {
                // let the SpheroConnectionView handle or hide it and do something here...
            }
            @Override
            public void onDisconnected(Robot sphero) {
            	GlobalObjects.mSpheroConnectionView.startDiscovery();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
