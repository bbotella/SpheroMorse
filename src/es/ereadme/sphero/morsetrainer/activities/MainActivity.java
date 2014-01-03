package es.ereadme.sphero.morsetrainer.activities;

import es.ereadme.sphero.morsetrainer.R;
import es.ereadme.sphero.morsetrainer.R.id;
import es.ereadme.sphero.morsetrainer.R.layout;
import es.ereadme.sphero.morsetrainer.R.menu;
import es.ereadme.sphero.morsetrainer.constants.GlobalObjects;
import orbotix.robot.base.Robot;
import orbotix.sphero.ConnectionListener;
import orbotix.sphero.Sphero;
import orbotix.view.connection.SpheroConnectionView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	private Sphero mRobot;

    /** The Sphero Connection View */
    private SpheroConnectionView mSpheroConnectionView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GlobalObjects.mSpheroConnectionView = (SpheroConnectionView) findViewById(R.id.sphero_connection_view);
		GlobalObjects.mSpheroConnectionView.addConnectionListener(new ConnectionListener() {

            @Override
            public void onConnected(Robot robot) {
            	GlobalObjects.mRobot = (Sphero) robot;
                //mRobot.getCollisionControl().addCollisionListener(mCollisionListener);
            	GlobalObjects.mRobot.getCollisionControl().startDetection(45, 45, 100, 100, 100);
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
