package com.intellicredit.platform.util;

import java.util.Enumeration;
import java.util.StringTokenizer;

public class Tokenizer implements Enumeration<Object> {
	private String sInput;
	private String sDelim;
	private boolean bIncludeDelim = false;
	private StringTokenizer oTokenizer;
	private String sPrevToken = "";
	private int iTokenNo = 0;
	private int iTotalTokens = -1;
	private int iTokens = 0;
	private int iLen = 0;

	/**
	 * Constructor
	 * 
	 * @param str
	 *            the input string
	 * @param sep
	 *            the delimiter string
	 */
	public Tokenizer(String str, String sep) {
		sInput = str;
		sDelim = sep;
		iLen = sDelim.length();
		oTokenizer = new StringTokenizer(str, sep, true);
		iTotalTokens = countTokens();
	}

	/**
	 * Constructor
	 * 
	 * @param str
	 *            the input string
	 * @param sep
	 *            the delimiter string
	 * @param bIncludeDelim
	 *            if true, include delimiters as tokens
	 */
	public Tokenizer(String str, String sep, boolean bIncludeDelim) {
		sInput = str;
		sDelim = sep;
		this.bIncludeDelim = bIncludeDelim;
		iLen = sDelim.length();
		oTokenizer = new StringTokenizer(str, sep, true);
		iTotalTokens = countTokens();
	}

	/**
	 * Returns the next token from the input string.
	 * 
	 * @return String the current token from the input string.
	 */
	public String nextToken() {
		String sToken = oTokenizer.nextToken();

		// return "" as token if consecutive delimiters are found.
		if ((sPrevToken.equals(sDelim)) && (sToken.equals(sDelim))) {
			sPrevToken = sToken;
			iTokenNo++;
			return "";
		}

		// check whether the token itself is equal to the delimiter and
		// present in a substring
		/*
		 * if ( (sToken.trim().startsWith("\"")) && (sToken.length() == 1) ) {
		 * // this is a special case when token itself is equal to delimeter
		 * String sNextToken = oTokenizer.nextToken(); while
		 * (!sNextToken.trim().endsWith("\"")) { sToken += sNextToken;
		 * sNextToken = oTokenizer.nextToken(); } sToken += sNextToken;
		 * sPrevToken = sToken; iTokenNo++; return sToken.substring(1,
		 * sToken.length()-1); } // check whether there is a substring inside
		 * the string
		 * 
		 * else if ( (sToken.trim().startsWith("\"")) &&
		 * (!((sToken.trim().endsWith("\"")) &&
		 * (!sToken.trim().endsWith("\"\"")))) ) { if
		 * (oTokenizer.hasMoreTokens()) { String sNextToken =
		 * oTokenizer.nextToken(); //System.out.println("next token = " +
		 * sNextToken // + ", token = " + sToken); // the reason it checks for
		 * presence of "\"\"" is // you get this while converting a excel file
		 * to csv file // if the excel file contains a "\"" while
		 * (!((sNextToken.trim().endsWith("\"")) &&
		 * (!sNextToken.trim().endsWith("\"\""))) ) { sToken += sNextToken; if
		 * (!oTokenizer.hasMoreTokens()) { sNextToken = ""; break; } sNextToken
		 * = oTokenizer.nextToken(); } sToken += sNextToken; } }
		 */
		sPrevToken = sToken;
		// remove any unnecessary double quote still present.
		/*
		 * if (sToken.length() > 0) { sToken = sToken.trim(); // remove double
		 * quote marks from beginning and end of the string if (sToken.charAt(0)
		 * == '"' && sToken.charAt(sToken.length()-1) == '"') sToken =
		 * sToken.substring(1, sToken.length()-1);
		 * 
		 * String sTemp = ""; int iPrevDblQuote = 0; int iDblQuote =
		 * sToken.indexOf("\"\""); // change "\"\""'s to "\"" if any of them are
		 * present if (iDblQuote != -1) { String sDummy = sToken; while
		 * (iDblQuote != -1) { sTemp = sDummy.substring(0, iDblQuote+1); sTemp
		 * += sDummy.substring(iDblQuote+2); iPrevDblQuote = iDblQuote; sDummy =
		 * sTemp; iDblQuote = sDummy.indexOf("\"\"", iPrevDblQuote+1); } sToken
		 * = sTemp; } }
		 */
		// call next token again, if delimeters are not to be included
		// as tokens.
		if ((!bIncludeDelim) && (sToken.equals(sDelim))) {
			sToken = nextToken();
		} else
			iTokenNo++;

		// System.out.println("idx = " + iTokenNo + ", token = " + sToken);
		return sToken;
	}

	/**
	 * Checks whether any token is left in the input string
	 * 
	 * @return boolean true, if any token is left
	 */
	public boolean hasMoreTokens() {
		if (iTotalTokens == -1)
			iTotalTokens = countTokens();

		return (iTokenNo < iTotalTokens);
	}

	/**
	 * Checks whether any token is left in the input string
	 * 
	 * @return boolean true, if any token is left
	 */
	public boolean hasMoreElements() {
		return hasMoreTokens();
	}

	/**
	 * Returns the next token from the input string.
	 * 
	 * @return Object the current token from the input string.
	 */
	public Object nextElement() {
		return nextToken();
	}

	/**
	 * Total number of tokens present in the input string
	 * 
	 * @return int total number of tokens
	 */
	public int countTokens() {
		// System.out.println("DD" + iTokenNo + " " + iTotalTokens);
		if (iTotalTokens != -1) {

			return iTotalTokens - iTokenNo;
		}
		iTokens = oTokenizer.countTokens();
		int[] aiIndex = new int[iTokens];
		aiIndex[0] = 0;
		if (bIncludeDelim) {
			return iTokens;
		} else {// if ( (!bIncludeDelim) || (iTokens == iActualTokens) ) {
				// remove the number of actual delimeters from
				// the string as this a case with bIncludeDelim=false
			int iIdx = 0;
			iIdx = sInput.indexOf(sDelim, iIdx + 1);
			while (iIdx != -1) {
				if (!((sInput.charAt(iIdx - 1) == '"')
						&& (sInput.charAt(iIdx + 1) == '"') && ((iIdx + 1
						+ iLen <= sInput.length()) && (sInput.substring(
						iIdx + 1, iIdx + 1 + iLen).equals(sDelim))))) {
					iTokens--;
				}

				// don't decrement the token count if consecutive tokens
				// are found.
				while ((iIdx + 1 < sInput.length())
						&& (sInput.substring(iIdx + 1, iIdx + 1 + iLen)
								.equals(sDelim))) {
					iIdx += iLen;
				}
				iIdx = sInput.indexOf(sDelim, iIdx + 1);
			}
		}

		return iTokens;
	}

}