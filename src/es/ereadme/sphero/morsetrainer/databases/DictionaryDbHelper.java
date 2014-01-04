package es.ereadme.sphero.morsetrainer.databases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import es.ereadme.sphero.morsetrainer.constants.Constants;
import es.ereadme.sphero.morsetrainer.databases.DictionaryContract.SpanishFeedEntry;

public class DictionaryDbHelper extends SQLiteOpenHelper{
	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Dictionaries.db";
    
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + SpanishFeedEntry.TABLE_NAME + " (" +
        		SpanishFeedEntry._ID + " INTEGER PRIMARY KEY," +
        		SpanishFeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
        		SpanishFeedEntry.COLUMN_NAME_WORD + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + SpanishFeedEntry.TABLE_NAME;
    
    private Context mContext;
    
    
    public DictionaryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }
    
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        
        //Now we fill the database with the words read from asset file
        try {
			BufferedReader reader = new BufferedReader(
			        new InputStreamReader(mContext.getAssets().open("spanishWords.txt")));
			
			String word = "";
			int i = 0;
			while ((word = reader.readLine()) != null){
				ContentValues values = new ContentValues();
		        values.put(SpanishFeedEntry.COLUMN_NAME_ENTRY_ID, i);
		        values.put(SpanishFeedEntry.COLUMN_NAME_WORD, word);

		        db.insert(SpanishFeedEntry.TABLE_NAME,
		        		SpanishFeedEntry.COLUMN_NAME_NULLABLE,
		                 values);
		        Log.d(Constants.TAG, "Added word " + word + " to database with id " + i);
		        i = i + 1;
			}
			
		} catch (IOException e) {
			Log.e(Constants.TAG, e.toString());
			e.printStackTrace();
		}
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
    public String getSpanishWord(int id){
    	String word = "";
    	
    	SQLiteDatabase db = this.getReadableDatabase();
    	
    	String[] projection = {
    			SpanishFeedEntry._ID,
    			SpanishFeedEntry.COLUMN_NAME_ENTRY_ID,
    			SpanishFeedEntry.COLUMN_NAME_WORD,
    	    };

    	String selection = SpanishFeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
    	
    	String[] selectionArgs = { id+"" };
    	
    	String sortOrder =
    			SpanishFeedEntry.COLUMN_NAME_ENTRY_ID + " ASC";
    	
    	Cursor c = db.query(
    			SpanishFeedEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    selection,                                // The columns for the WHERE clause
    	    selectionArgs,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    sortOrder                                 // The sort order
    	    );
    	
    	c.moveToFirst();
    	word = c.getString(c.getColumnIndexOrThrow(SpanishFeedEntry.COLUMN_NAME_WORD));
    	return word;
    }

}
