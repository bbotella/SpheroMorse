package es.ereadme.sphero.morsetrainer.constants;

import orbotix.sphero.Sphero;
import orbotix.view.connection.SpheroConnectionView;
import es.ereadme.sphero.morsetrainer.databases.DictionaryDbHelper;

public class GlobalObjects {
	public static Sphero mRobot = null;
	public static SpheroConnectionView mSpheroConnectionView = null;
	public static DictionaryDbHelper dictionariesDB = null;
}
