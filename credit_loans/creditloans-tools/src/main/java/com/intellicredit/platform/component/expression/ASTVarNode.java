package com.intellicredit.platform.component.expression;

/**
 * Variable Node
 */
public class ASTVarNode
    extends SimpleNode {

    private String varName;

    public ASTVarNode(int id) {
        super(id);
        varName = "";
    }

    public ASTVarNode(Parser p, int id) {
        super(p, id);
    }

    /**
     * Accept the visitor.
     */
    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    /**
     * Sets the name of the variable.
     */
    public void setName(String varName_in) {
        varName = varName_in;
    }

    /**
     * Returns the name of the variable.
     */
    public String getName() {
        return varName;
    }

    /**
     * Creates a string containing the variable's name and value
     */
    public String toString() {
        String temp = "Variable: \"" + getName() + "\"";

        return temp;
    }
}
