package com.intellicredit.platform.component.expression;

public interface ParserConstants {

    int EOF = 0;
    int INTEGER_LITERAL = 5;
    int DECIMAL_LITERAL = 6;
    int FLOATING_POINT_LITERAL = 7;
    int EXPONENT = 8;
    int STRING_LITERAL = 9;
    int IDENTIFIER = 10;
    int LETTER = 11;
    int DIGIT = 12;
    int GT = 13;
    int LT = 14;
    int EQ = 15;
    int LE = 16;
    int GE = 17;
    int NE = 18;
    int AND = 19;
    int OR = 20;
    int PLUS = 21;
    int MINUS = 22;
    int MUL = 23;
    int DIV = 24;
    int MOD = 25;
    int NOT = 26;
    int POWER = 27;

    int DEFAULT = 0;

    String[] tokenImage = {
        "<EOF>",
        "\" \"",
        "\"\\t\"",
        "\"\\n\"",
        "\"\\r\"",
        "<INTEGER_LITERAL>",
        "<DECIMAL_LITERAL>",
        "<FLOATING_POINT_LITERAL>",
        "<EXPONENT>",
        "<STRING_LITERAL>",
        "<IDENTIFIER>",
        "<LETTER>",
        "<DIGIT>",
        "\">\"",
        "\"<\"",
        "\"==\"",
        "\"<=\"",
        "\">=\"",
        "\"!=\"",
        "\"&&\"",
        "\"||\"",
        "\"+\"",
        "\"-\"",
        "\"*\"",
        "\"/\"",
        "\"%\"",
        "\"!\"",
        "\"^\"",
        "\"(\"",
        "\")\"",
        "\",\"",
        "\"[\"",
        "\"]\"",
    };

}
