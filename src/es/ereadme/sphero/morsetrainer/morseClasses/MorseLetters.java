package es.ereadme.sphero.morsetrainer.morseClasses;

import java.util.Hashtable;

public class MorseLetters {
	//. -> 0
	//- -> 1
	public static final String a = "01";
	public static final String b = "1000";
	public static final String c = "1010";
	public static final String d = "100";
	public static final String e = "0";
	public static final String f = "0010";
	public static final String g = "110";
	public static final String h = "0000";
	public static final String i = "00";
	public static final String j = "0111";
	public static final String k = "101";
	public static final String l = "0100";
	public static final String m = "11";
	public static final String n = "10";
	public static final String – = "11011";
	public static final String o = "111";
	public static final String p = "0110";
	public static final String q = "1101";
	public static final String r = "010";
	public static final String s = "000";
	public static final String t = "1";
	public static final String u = "001";
	public static final String v = "0001";
	public static final String w = "011";
	public static final String x = "1001";
	public static final String y = "1011";
	public static final String z = "1100";
	public static final String punto = "010101";
	
	public static Hashtable<String, String> morseDict = null;
	
	public MorseLetters(){
		morseDict = new Hashtable<String, String>();
		
		morseDict.put("a", a);
		morseDict.put("b", b);
		morseDict.put("c", c);
		morseDict.put("d", d);
		morseDict.put("e", e);
		morseDict.put("f", f);
		morseDict.put("g", g);
		morseDict.put("h", h);
		morseDict.put("i", i);
		morseDict.put("j", j);
		morseDict.put("k", k);
		morseDict.put("l", l);
		morseDict.put("m", m);
		morseDict.put("n", n);
		morseDict.put("–", –);
		morseDict.put("o", o);
		morseDict.put("p", p);
		morseDict.put("q", q);
		morseDict.put("r", r);
		morseDict.put("s", s);
		morseDict.put("t", t);
		morseDict.put("u", u);
		morseDict.put("v", v);
		morseDict.put("w", w);
		morseDict.put("x", x);
		morseDict.put("y", y);
		morseDict.put("z", z);
	}
}
