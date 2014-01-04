package es.ereadme.sphero.morsetrainer.databases;

import android.provider.BaseColumns;

public class DictionaryContract {
	
	public DictionaryContract() {}

    public static abstract class SpanishFeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "spanishDictionary";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_NULLABLE = "NULL";
    }
}
