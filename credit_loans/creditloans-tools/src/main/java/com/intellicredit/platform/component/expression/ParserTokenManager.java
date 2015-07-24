package com.intellicredit.platform.component.expression;

public class ParserTokenManager implements ParserConstants {
	public java.io.PrintStream debugStream = System.out;

	public void setDebugStream(java.io.PrintStream ds) {
		debugStream = ds;
	}

	private final int jjStopStringLiteralDfa_0(int pos, long active0) {
		switch (pos) {
		default:
			return -1;
		}
	}

	private final int jjStartNfa_0(int pos, long active0) {
		return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
	}

	private final int jjStopAtPos(int pos, int kind) {
		jjmatchedKind = kind;
		jjmatchedPos = pos;
		return pos + 1;
	}

	private final int jjMoveStringLiteralDfa0_0() {
		switch (curChar) {
		case 33:
			jjmatchedKind = 26;
			return jjMoveStringLiteralDfa1_0(0x40000L);
		case 37:
			return jjStopAtPos(0, 25);
		case 38:
			return jjMoveStringLiteralDfa1_0(0x80000L);
		case 40:
			return jjStopAtPos(0, 28);
		case 41:
			return jjStopAtPos(0, 29);
		case 42:
			return jjStopAtPos(0, 23);
		case 43:
			return jjStopAtPos(0, 21);
		case 44:
			return jjStopAtPos(0, 30);
		case 45:
			return jjStopAtPos(0, 22);
		case 47:
			return jjStopAtPos(0, 24);
		case 60:
			jjmatchedKind = 14;
			return jjMoveStringLiteralDfa1_0(0x10000L);
		case 61:
			return jjMoveStringLiteralDfa1_0(0x8000L);
		case 62:
			jjmatchedKind = 13;
			return jjMoveStringLiteralDfa1_0(0x20000L);
		case 91:
			return jjStopAtPos(0, 31);
		case 93:
			return jjStopAtPos(0, 32);
		case 94:
			return jjStopAtPos(0, 27);
		case 124:
			return jjMoveStringLiteralDfa1_0(0x100000L);
		default:
			return jjMoveNfa_0(0, 0);
		}
	}

	private final int jjMoveStringLiteralDfa1_0(long active0) {
		try {
			curChar = input_stream.readChar();
		} catch (java.io.IOException e) {
			jjStopStringLiteralDfa_0(0, active0);
			return 1;
		}
		switch (curChar) {
		case 38:
			if ((active0 & 0x80000L) != 0L) {
				return jjStopAtPos(1, 19);
			}
			break;
		case 61:
			if ((active0 & 0x8000L) != 0L) {
				return jjStopAtPos(1, 15);
			} else if ((active0 & 0x10000L) != 0L) {
				return jjStopAtPos(1, 16);
			} else if ((active0 & 0x20000L) != 0L) {
				return jjStopAtPos(1, 17);
			} else if ((active0 & 0x40000L) != 0L) {
				return jjStopAtPos(1, 18);
			}
			break;
		case 124:
			if ((active0 & 0x100000L) != 0L) {
				return jjStopAtPos(1, 20);
			}
			break;
		default:
			break;
		}
		return jjStartNfa_0(0, active0);
	}

	private final void jjCheckNAdd(int state) {
		if (jjrounds[state] != jjround) {
			jjstateSet[jjnewStateCnt++] = state;
			jjrounds[state] = jjround;
		}
	}

	private final void jjAddStates(int start, int end) {
		do {
			jjstateSet[jjnewStateCnt++] = jjnextStates[start];
		} while (start++ != end);
	}

	private final void jjCheckNAddTwoStates(int state1, int state2) {
		jjCheckNAdd(state1);
		jjCheckNAdd(state2);
	}

	private final void jjCheckNAddStates(int start, int end) {
		do {
			jjCheckNAdd(jjnextStates[start]);
		} while (start++ != end);
	}

	static final long[] jjbitVec0 = { 0xfffffffffffffffeL, 0xffffffffffffffffL,
			0xffffffffffffffffL, 0xffffffffffffffffL };
	static final long[] jjbitVec2 = { 0x0L, 0x0L, 0xffffffffffffffffL,
			0xffffffffffffffffL };
	static final long[] jjbitVec3 = { 0x1ff00000fffffffeL, 0xffffffffffffc000L,
			0xffffffffL, 0x600000000000000L };
	static final long[] jjbitVec4 = { 0x0L, 0x0L, 0x0L, 0xff7fffffff7fffffL };
	static final long[] jjbitVec5 = { 0x0L, 0xffffffffffffffffL,
			0xffffffffffffffffL, 0xffffffffffffffffL };
	static final long[] jjbitVec6 = { 0xffffffffffffffffL, 0xffffffffffffffffL,
			0xffffL, 0x0L };
	static final long[] jjbitVec7 = { 0xffffffffffffffffL, 0xffffffffffffffffL,
			0x0L, 0x0L };
	static final long[] jjbitVec8 = { 0x3fffffffffffL, 0x0L, 0x0L, 0x0L };

	private final int jjMoveNfa_0(int startState, int curPos) {
		int startsAt = 0;
		jjnewStateCnt = 24;
		int i = 1;
		jjstateSet[0] = startState;
		int kind = 0x7fffffff;
		for (;;) {
			if (++jjround == 0x7fffffff) {
				ReInitRounds();
			}
			if (curChar < 64) {
				long l = 1L << curChar;
				do {
					switch (jjstateSet[--i]) {
					case 0:
						if ((0x3ff000000000000L & l) != 0L) {
							if (kind > 5) {
								kind = 5;
							}
							jjCheckNAddStates(0, 4);
						} else if (curChar == 36) {
							if (kind > 10) {
								kind = 10;
							}
							jjCheckNAdd(11);
						} else if (curChar == 34) {
							jjCheckNAddStates(5, 7);
						} else if (curChar == 46) {
							jjCheckNAdd(1);
						}
						break;
					case 1:
						if ((0x3ff000000000000L & l) == 0L) {
							break;
						}
						if (kind > 7) {
							kind = 7;
						}
						jjCheckNAddTwoStates(1, 2);
						break;
					case 3:
						if ((0x280000000000L & l) != 0L) {
							jjCheckNAdd(4);
						}
						break;
					case 4:
						if ((0x3ff000000000000L & l) == 0L) {
							break;
						}
						if (kind > 7) {
							kind = 7;
						}
						jjCheckNAdd(4);
						break;
					case 5:
						if (curChar == 34) {
							jjCheckNAddStates(5, 7);
						}
						break;
					case 6:
						if ((0xfffffffbffffdbffL & l) != 0L) {
							jjCheckNAddStates(5, 7);
						}
						break;
					case 8:
						if ((0x8400000000L & l) != 0L) {
							jjCheckNAddStates(5, 7);
						}
						break;
					case 9:
						if (curChar == 34 && kind > 9) {
							kind = 9;
						}
						break;
					case 10:
						if (curChar != 36) {
							break;
						}
						if (kind > 10) {
							kind = 10;
						}
						jjCheckNAdd(11);
						break;
					case 11:
						if ((0x3ff001000000000L & l) == 0L) {
							break;
						}
						if (kind > 10) {
							kind = 10;
						}
						jjCheckNAdd(11);
						break;
					case 12:
						if ((0x3ff000000000000L & l) == 0L) {
							break;
						}
						if (kind > 5) {
							kind = 5;
						}
						jjCheckNAddStates(0, 4);
						break;
					case 13:
						if ((0x3ff000000000000L & l) == 0L) {
							break;
						}
						if (kind > 5) {
							kind = 5;
						}
						jjCheckNAdd(13);
						break;
					case 14:
						if ((0x3ff000000000000L & l) != 0L) {
							jjCheckNAddTwoStates(14, 15);
						}
						break;
					case 15:
						if (curChar != 46) {
							break;
						}
						if (kind > 7) {
							kind = 7;
						}
						jjCheckNAddTwoStates(16, 17);
						break;
					case 16:
						if ((0x3ff000000000000L & l) == 0L) {
							break;
						}
						if (kind > 7) {
							kind = 7;
						}
						jjCheckNAddTwoStates(16, 17);
						break;
					case 18:
						if ((0x280000000000L & l) != 0L) {
							jjCheckNAdd(19);
						}
						break;
					case 19:
						if ((0x3ff000000000000L & l) == 0L) {
							break;
						}
						if (kind > 7) {
							kind = 7;
						}
						jjCheckNAdd(19);
						break;
					case 20:
						if ((0x3ff000000000000L & l) != 0L) {
							jjCheckNAddTwoStates(20, 21);
						}
						break;
					case 22:
						if ((0x280000000000L & l) != 0L) {
							jjCheckNAdd(23);
						}
						break;
					case 23:
						if ((0x3ff000000000000L & l) == 0L) {
							break;
						}
						if (kind > 7) {
							kind = 7;
						}
						jjCheckNAdd(23);
						break;
					default:
						break;
					}
				} while (i != startsAt);
			} else if (curChar < 128) {
				long l = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
					case 0:
					case 11:
						if ((0x7fffffe87fffffeL & l) == 0L) {
							break;
						}
						if (kind > 10) {
							kind = 10;
						}
						jjCheckNAdd(11);
						break;
					case 2:
						if ((0x2000000020L & l) != 0L) {
							jjAddStates(8, 9);
						}
						break;
					case 6:
						if ((0xffffffffefffffffL & l) != 0L) {
							jjCheckNAddStates(5, 7);
						}
						break;
					case 7:
						if (curChar == 92) {
							jjstateSet[jjnewStateCnt++] = 8;
						}
						break;
					case 8:
						if ((0x14404410000000L & l) != 0L) {
							jjCheckNAddStates(5, 7);
						}
						break;
					case 17:
						if ((0x2000000020L & l) != 0L) {
							jjAddStates(10, 11);
						}
						break;
					case 21:
						if ((0x2000000020L & l) != 0L) {
							jjAddStates(12, 13);
						}
						break;
					default:
						break;
					}
				} while (i != startsAt);
			} else {
				int hiByte = (int) (curChar >> 8);
				int i1 = hiByte >> 6;
				long l1 = 1L << (hiByte & 077);
				int i2 = (curChar & 0xff) >> 6;
				long l2 = 1L << (curChar & 077);
				do {
					switch (jjstateSet[--i]) {
					case 0:
					case 11:
						if (!jjCanMove_1(hiByte, i1, i2, l1, l2)) {
							break;
						}
						if (kind > 10) {
							kind = 10;
						}
						jjCheckNAdd(11);
						break;
					case 6:
						if (jjCanMove_0(hiByte, i1, i2, l1, l2)) {
							jjAddStates(5, 7);
						}
						break;
					default:
						break;
					}
				} while (i != startsAt);
			}
			if (kind != 0x7fffffff) {
				jjmatchedKind = kind;
				jjmatchedPos = curPos;
				kind = 0x7fffffff;
			}
			++curPos;
			if ((i = jjnewStateCnt) == (startsAt = 24 - (jjnewStateCnt = startsAt))) {
				return curPos;
			}
			try {
				curChar = input_stream.readChar();
			} catch (java.io.IOException e) {
				return curPos;
			}
		}
	}

	static final int[] jjnextStates = { 13, 14, 15, 20, 21, 6, 7, 9, 3, 4, 18,
			19, 22, 23, };

	private static final boolean jjCanMove_0(int hiByte, int i1, int i2,
			long l1, long l2) {
		switch (hiByte) {
		case 0:
			return ((jjbitVec2[i2] & l2) != 0L);
		default:
			if ((jjbitVec0[i1] & l1) != 0L) {
				return true;
			}
			return false;
		}
	}

	private static final boolean jjCanMove_1(int hiByte, int i1, int i2,
			long l1, long l2) {
		switch (hiByte) {
		case 0:
			return ((jjbitVec4[i2] & l2) != 0L);
		case 48:
			return ((jjbitVec5[i2] & l2) != 0L);
		case 49:
			return ((jjbitVec6[i2] & l2) != 0L);
		case 51:
			return ((jjbitVec7[i2] & l2) != 0L);
		case 61:
			return ((jjbitVec8[i2] & l2) != 0L);
		default:
			if ((jjbitVec3[i1] & l1) != 0L) {
				return true;
			}
			return false;
		}
	}

	public static final String[] jjstrLiteralImages = { "", null, null, null,
			null, null, null, null, null, null, null, null, null, "\76", "\74",
			"\75\75", "\74\75", "\76\75", "\41\75", "\46\46", "\174\174",
			"\53", "\55", "\52", "\57", "\45", "\41", "\136", "\50", "\51",
			"\54", "\133", "\135", };
	public static final String[] lexStateNames = { "DEFAULT", };
	static final long[] jjtoToken = { 0x1ffffe6a1L, };
	static final long[] jjtoSkip = { 0x1eL, };
	private JavaCharStream input_stream;
	private final int[] jjrounds = new int[24];
	private final int[] jjstateSet = new int[48];
	protected char curChar;

	public ParserTokenManager(JavaCharStream stream) {
		if (JavaCharStream.staticFlag) {
			throw new Error(
					"ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
		}
		input_stream = stream;
	}

	public ParserTokenManager(JavaCharStream stream, int lexState) {
		this(stream);
		SwitchTo(lexState);
	}

	public void ReInit(JavaCharStream stream) {
		jjmatchedPos = jjnewStateCnt = 0;
		curLexState = defaultLexState;
		input_stream = stream;
		ReInitRounds();
	}

	private final void ReInitRounds() {
		int i;
		jjround = 0x80000001;
		for (i = 24; i-- > 0;) {
			jjrounds[i] = 0x80000000;
		}
	}

	public void ReInit(JavaCharStream stream, int lexState) {
		ReInit(stream);
		SwitchTo(lexState);
	}

	public void SwitchTo(int lexState) {
		if (lexState >= 1 || lexState < 0) {
			throw new TokenMgrError("Error: Ignoring invalid lexical state : "
					+ lexState + ". State unchanged.",
					TokenMgrError.INVALID_LEXICAL_STATE);
		} else {
			curLexState = lexState;
		}
	}

	private final Token jjFillToken() {
		Token t = Token.newToken(jjmatchedKind);
		t.kind = jjmatchedKind;
		String im = jjstrLiteralImages[jjmatchedKind];
		t.image = (im == null) ? input_stream.GetImage() : im;
		t.beginLine = input_stream.getBeginLine();
		t.beginColumn = input_stream.getBeginColumn();
		t.endLine = input_stream.getEndLine();
		t.endColumn = input_stream.getEndColumn();
		return t;
	}

	int curLexState = 0;
	int defaultLexState = 0;
	int jjnewStateCnt;
	int jjround;
	int jjmatchedPos;
	int jjmatchedKind;

	public final Token getNextToken() {
		Token matchedToken;
		int curPos = 0;

		EOFLoop: for (;;) {
			try {
				curChar = input_stream.BeginToken();
			} catch (java.io.IOException e) {
				jjmatchedKind = 0;
				matchedToken = jjFillToken();
				return matchedToken;
			}

			try {
				input_stream.backup(0);
				while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L) {
					curChar = input_stream.BeginToken();
				}
			} catch (java.io.IOException e1) {
				continue EOFLoop;
			}
			jjmatchedKind = 0x7fffffff;
			jjmatchedPos = 0;
			curPos = jjMoveStringLiteralDfa0_0();
			if (jjmatchedKind != 0x7fffffff) {
				if (jjmatchedPos + 1 < curPos) {
					input_stream.backup(curPos - jjmatchedPos - 1);
				}
				if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
					matchedToken = jjFillToken();
					return matchedToken;
				} else {
					continue EOFLoop;
				}
			}
			int error_line = input_stream.getEndLine();
			int error_column = input_stream.getEndColumn();
			String error_after = null;
			boolean EOFSeen = false;
			try {
				input_stream.readChar();
				input_stream.backup(1);
			} catch (java.io.IOException e1) {
				EOFSeen = true;
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
				if (curChar == '\n' || curChar == '\r') {
					error_line++;
					error_column = 0;
				} else {
					error_column++;
				}
			}
			if (!EOFSeen) {
				input_stream.backup(1);
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
			}
			throw new TokenMgrError(EOFSeen, curLexState, error_line,
					error_column, error_after, curChar,
					TokenMgrError.LEXICAL_ERROR);
		}
	}

}
