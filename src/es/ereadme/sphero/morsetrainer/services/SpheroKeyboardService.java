package es.ereadme.sphero.morsetrainer.services;

import orbotix.robot.base.CollisionDetectedAsyncData;
import orbotix.sphero.CollisionListener;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import es.ereadme.sphero.morsetrainer.constants.Constants;
import es.ereadme.sphero.morsetrainer.constants.GlobalObjects;

public class SpheroKeyboardService extends Service{

	public static final String EXTRA_MESSENGER="es.ereadme.sphero.morsetrainer.services.EXTRA_MESSENGER";
	private static Intent i; 
	
	@Override
	public void onCreate() {
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		i = intent;
		GlobalObjects.mRobot.getCollisionControl().addCollisionListener(mCollisionListener);
		GlobalObjects.mRobot.getCollisionControl().startDetection(45, 45, 100, 100, 100);
		return START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	private final CollisionListener mCollisionListener = new CollisionListener() {
        public void collisionDetected(CollisionDetectedAsyncData collisionData) {
        	Log.d(Constants.TAG, "Collision detected");
        	Bundle extras=i.getExtras();
        	if (extras!=null) {
        	      Messenger messenger=(Messenger)extras.get(EXTRA_MESSENGER);
        	      Message msg=Message.obtain();
        	      
        	      msg.arg1=Activity.RESULT_OK;
        	      
        	      try {
        	        messenger.send(msg);
        	      }
        	      catch (android.os.RemoteException e1) {
        	        Log.e(getClass().getName(), "Exception sending message", e1);
        	      }
        	    }
        }
    };

}
