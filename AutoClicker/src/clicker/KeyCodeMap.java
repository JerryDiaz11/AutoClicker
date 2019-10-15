package clicker;

import java.util.HashMap;

public class KeyCodeMap {
	
	private HashMap<String,Integer> keyMap = new HashMap<>();
	
	public KeyCodeMap() {
		super();
		
		keyMap.put("CHAR_UNDEFINED", 65535);
		keyMap.put("KEY_FIRST", 400);
		keyMap.put("KEY_LAST", 402);
		keyMap.put("KEY_LOCATION_LEFT", 2);
		keyMap.put("KEY_LOCATION_NUMPAD", 4);
		keyMap.put("KEY_LOCATION_RIGHT", 3);
		keyMap.put("KEY_LOCATION_STANDARD", 1);
		keyMap.put("KEY_LOCATION_UNKNOWN", 0);
		keyMap.put("KEY_PRESSED", 401);
		keyMap.put("KEY_RELEASED", 402);
		keyMap.put("KEY_TYPED", 400);
		keyMap.put("0", 48);
		keyMap.put("1", 49);
		keyMap.put("2", 50);
		keyMap.put("3", 51);
		keyMap.put("4", 52);
		keyMap.put("5", 53);
		keyMap.put("6", 54);
		keyMap.put("7", 55);
		keyMap.put("8", 56);
		keyMap.put("9", 57);
		keyMap.put("A", 65);
		keyMap.put("ACCEPT", 30);
		keyMap.put("ADD", 107);
		keyMap.put("AGAIN", 65481);
		keyMap.put("ALL_CANDIDATES", 256);
		keyMap.put("ALPHANUMERIC", 240);
		keyMap.put("ALT", 18);
		keyMap.put("ALT_GRAPH", 65406);
		keyMap.put("AMPERSAND", 150);
		keyMap.put("ASTERISK", 151);
		keyMap.put("AT", 512);
		keyMap.put("B", 66);
		keyMap.put("BACK_QUOTE", 192);
		keyMap.put("BACK_SLASH", 92);
		keyMap.put("BACK_SPACE", 8);
		keyMap.put("BEGIN", 65368);
		keyMap.put("BRACELEFT", 161);
		keyMap.put("BRACERIGHT", 162);
		keyMap.put("C", 67);
		keyMap.put("CANCEL", 3);
		keyMap.put("CAPS_LOCK", 20);
		keyMap.put("CIRCUMFLEX", 514);
		keyMap.put("CLEAR", 12);
		keyMap.put("CLOSE_BRACKET", 93);
		keyMap.put("CODE_INPUT", 258);
		keyMap.put("COLON", 513);
		keyMap.put("COMMA", 44);
		keyMap.put("COMPOSE", 65312);
		keyMap.put("CONTEXT_MENU", 525);
		keyMap.put("CONTROL", 17);
		keyMap.put("CONVERT", 28);
		keyMap.put("COPY", 65485);
		keyMap.put("CUT", 65489);
		keyMap.put("D", 68);
		keyMap.put("DEAD_ABOVEDOT", 134);
		keyMap.put("DEAD_ABOVERING", 136);
		keyMap.put("DEAD_ACUTE", 129);
		keyMap.put("DEAD_BREVE", 133);
		keyMap.put("DEAD_CARON", 138);
		keyMap.put("DEAD_CEDILLA", 139);
		keyMap.put("DEAD_CIRCUMFLEX", 130);
		keyMap.put("DEAD_DIAERESIS", 135);
		keyMap.put("DEAD_DOUBLEACUTE", 137);
		keyMap.put("DEAD_GRAVE", 128);
		keyMap.put("DEAD_IOTA", 141);
		keyMap.put("DEAD_MACRON", 132);
		keyMap.put("DEAD_OGONEK", 140);
		keyMap.put("DEAD_SEMIVOICED_SOUND", 143);
		keyMap.put("DEAD_TILDE", 131);
		keyMap.put("DEAD_VOICED_SOUND", 142);
		keyMap.put("DECIMAL", 110);
		keyMap.put("DELETE", 127);
		keyMap.put("DIVIDE", 111);
		keyMap.put("DOLLAR", 515);
		keyMap.put("DOWN", 40);
		keyMap.put("E", 69);
		keyMap.put("END", 35);
		keyMap.put("ENTER", 10);
		keyMap.put("EQUALS", 61);
		keyMap.put("ESCAPE", 27);
		keyMap.put("EURO_SIGN", 516);
		keyMap.put("EXCLAMATION_MARK", 517);
		keyMap.put("F", 70);
		keyMap.put("F1", 112);
		keyMap.put("F10", 121);
		keyMap.put("F11", 122);
		keyMap.put("F12", 123);
		keyMap.put("F13", 61440);
		keyMap.put("F14", 61441);
		keyMap.put("F15", 61442);
		keyMap.put("F16", 61443);
		keyMap.put("F17", 61444);
		keyMap.put("F18", 61445);
		keyMap.put("F19", 61446);
		keyMap.put("F2", 113);
		keyMap.put("F20", 61447);
		keyMap.put("F21", 61448);
		keyMap.put("F22", 61449);
		keyMap.put("F23", 61450);
		keyMap.put("F24", 61451);
		keyMap.put("F3", 114);
		keyMap.put("F4", 115);
		keyMap.put("F5", 116);
		keyMap.put("F6", 117);
		keyMap.put("F7", 118);
		keyMap.put("F8", 119);
		keyMap.put("F9", 120);
		keyMap.put("FINAL", 24);
		keyMap.put("FIND", 65488);
		keyMap.put("FULL_WIDTH", 243);
		keyMap.put("G", 71);
		keyMap.put("GREATER", 160);
		keyMap.put("H", 72);
		keyMap.put("HALF_WIDTH", 244);
		keyMap.put("HELP", 156);
		keyMap.put("HIRAGANA", 242);
		keyMap.put("HOME", 36);
		keyMap.put("I", 73);
		keyMap.put("INPUT_METHOD_ON_OFF", 263);
		keyMap.put("INSERT", 155);
		keyMap.put("INVERTED_EXCLAMATION_MARK", 518);
		keyMap.put("J", 74);
		keyMap.put("JAPANESE_HIRAGANA", 260);
		keyMap.put("JAPANESE_KATAKANA", 259);
		keyMap.put("JAPANESE_ROMAN", 261);
		keyMap.put("K", 75);
		keyMap.put("KANA", 21);
		keyMap.put("KANA_LOCK", 262);
		keyMap.put("KANJI", 25);
		keyMap.put("KATAKANA", 241);
		keyMap.put("KP_DOWN", 225);
		keyMap.put("KP_LEFT", 226);
		keyMap.put("KP_RIGHT", 227);
		keyMap.put("KP_UP", 224);
		keyMap.put("L", 76);
		keyMap.put("LEFT", 37);
		keyMap.put("LEFT_PARENTHESIS", 519);
		keyMap.put("LESS", 153);
		keyMap.put("M", 77);
		keyMap.put("META", 157);
		keyMap.put("MINUS", 45);
		keyMap.put("MODECHANGE", 31);
		keyMap.put("MULTIPLY", 106);
		keyMap.put("N", 78);
		keyMap.put("NONCONVERT", 29);
		keyMap.put("NUM_LOCK", 144);
		keyMap.put("NUMBER_SIGN", 520);
		keyMap.put("NUMPAD0", 96);
		keyMap.put("NUMPAD1", 97);
		keyMap.put("NUMPAD2", 98);
		keyMap.put("NUMPAD3", 99);
		keyMap.put("NUMPAD4", 100);
		keyMap.put("NUMPAD5", 101);
		keyMap.put("NUMPAD6", 102);
		keyMap.put("NUMPAD7", 103);
		keyMap.put("NUMPAD8", 104);
		keyMap.put("NUMPAD9", 105);
		keyMap.put("O", 79);
		keyMap.put("OPEN_BRACKET", 91);
		keyMap.put("P", 80);
		keyMap.put("PAGE_DOWN", 34);
		keyMap.put("PAGE_UP", 33);
		keyMap.put("PASTE", 65487);
		keyMap.put("PAUSE", 19);
		keyMap.put("PERIOD", 46);
		keyMap.put("PLUS", 521);
		keyMap.put("PREVIOUS_CANDIDATE", 257);
		keyMap.put("PRINTSCREEN", 154);
		keyMap.put("PROPS", 65482);
		keyMap.put("Q", 81);
		keyMap.put("QUOTE", 222);
		keyMap.put("QUOTEDBL", 152);
		keyMap.put("R", 82);
		keyMap.put("RIGHT", 39);
		keyMap.put("RIGHT_PARENTHESIS", 522);
		keyMap.put("ROMAN_CHARACTERS", 245);
		keyMap.put("S", 83);
		keyMap.put("SCROLL_LOCK", 145);
		keyMap.put("SEMICOLON", 59);
		keyMap.put("SEPARATER", 108);
		keyMap.put("SEPARATOR", 108);
		keyMap.put("SHIFT", 16);
		keyMap.put("SLASH", 47);
		keyMap.put("SPACE", 32);
		keyMap.put("STOP", 65480);
		keyMap.put("SUBTRACT", 109);
		keyMap.put("T", 84);
		keyMap.put("TAB", 9);
		keyMap.put("U", 85);
		keyMap.put("UNDEFINED", 0);
		keyMap.put("UNDERSCORE", 523);
		keyMap.put("UNDO", 65483);
		keyMap.put("UP", 38);
		keyMap.put("V", 86);
		keyMap.put("W", 87);
		keyMap.put("WINDOWS", 524);
		keyMap.put("X", 88);
		keyMap.put("Y", 89);
		keyMap.put("Z", 90);
	}
	
	public int getCode(String key) {
		return keyMap.get(key);
	}

	
	

}
