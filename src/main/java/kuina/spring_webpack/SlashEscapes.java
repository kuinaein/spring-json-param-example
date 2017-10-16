package kuina.spring_webpack;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;

class SlashEscapes extends CharacterEscapes {
	private static final long serialVersionUID = -8241218653502899828L;

	private final int[] asciiEscapes;

	public SlashEscapes() {
		int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
		esc['/'] = CharacterEscapes.ESCAPE_STANDARD;
		esc['>'] = CharacterEscapes.ESCAPE_STANDARD;
		this.asciiEscapes = esc;
	}

	@Override
	public int[] getEscapeCodesForAscii() {
		return this.asciiEscapes;
	}

	@Override
	public SerializableString getEscapeSequence(final int ch) {
		return null;
	}
}
