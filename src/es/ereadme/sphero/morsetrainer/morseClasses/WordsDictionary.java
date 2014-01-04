package es.ereadme.sphero.morsetrainer.morseClasses;

import es.ereadme.sphero.morsetrainer.constants.GlobalObjects;

public final class WordsDictionary {
	public static final int SPANISH_DICT_LENGHT = 10000;
	
	public static final String getSpanishRandomWord(){
		//Get a random Spanish word and return it
		int wordId = (int)(Math.random() * SPANISH_DICT_LENGHT);
		String word = GlobalObjects.dictionariesDB.getSpanishWord(wordId);
		return word;
	}
}
