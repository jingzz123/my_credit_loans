package com.intellicredit.platform.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Utilities for handling strings
 */
public class StringUtil {
    /**
     * Replacement utility - substitutes <b>all</b> occurrences of 'src' with 'dest' in the string 'name'
     * @param name the string that the substitution is going to take place on
     * @param src the string that is going to be replaced
     * @param dest the string that is going to be substituted in
     * @return String with the substituted strings
     */
    public static String substitute( String orig, String src, String dest ) {
        if( orig == null || src == null || orig.length() == 0 ) {
            return orig;
        }
        
        if( dest == null ) {
            dest = "";
        }
        
        int index = orig.indexOf(src);
        if( index == -1 ) {
            return orig;
        }
        
        StringBuffer buf = new StringBuffer();
        int lastIndex = 0;
        while( index != -1 ) {
            buf.append( orig.substring(lastIndex,index) );
            buf.append( dest );
            lastIndex = index + src.length();
            index = orig.indexOf( src,lastIndex );
        }
        buf.append(orig.substring(lastIndex) );
        return buf.toString();
    }
    
    /**
     * Converts an exception into a string
     * @param t the exception to be converted
     * @return String a string representation of the exception
     */
    public static String exceptionToString( Throwable t ) {
        if( t != null ) {
            StringWriter sw   = new StringWriter();
            PrintWriter pw    = new PrintWriter(sw);
            pw.println( t.getMessage() );
            pw.println("\n=====================\n");
            t.printStackTrace(pw);
            pw.println("\n=====================\n");
            pw.close();
            return sw.toString();
        }
        else {
            return "";
        }
    }
    
    public static String trimLeft0(String s) {
        StringBuffer buf = new StringBuffer("");
        int n = s.length();
        boolean found = false;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '0' && !found)
                continue;
            buf.append(s.charAt(i));
            found = true;
        }
        return buf.toString();
    }
}
