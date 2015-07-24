package com.intellicredit.platform.component.expression;

import com.intellicredit.platform.component.expression.function.*;

/**
 * Function Node
 */
public class ASTFunNode
    extends SimpleNode {

    /** The function class used to evaluate the node */
    private PostfixMathCommandI pfmc;

    /** Name of the function */
    private String name;

    /**
     * Creates a new ASTFunNode
     */
    public ASTFunNode(int id) {
        super(id);
    }

    /**
     * Creates a new ASTFunNode
     */
    public ASTFunNode(Parser p, int id) {
        super(p, id);
    }

    /**
     * Accept the visitor.
     */
    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    /**
     * Sets the function for a node. A name and function class must
     * be specified.
     */
    public void setFunction(String name_in, PostfixMathCommandI pfmc_in) {
        name = name_in;
        pfmc = pfmc_in;
    }

    /**
     * Returns a string containing the function name.
     */
    public String toString() {
        return "Function \"" + name + "\"";
    }

    /**
     * Returns the math command class associated with this node.
     */
    public PostfixMathCommandI getPFMC() {
        return pfmc;
    }

    /**
     * Returns the name of the node (operator symbol or function name).
     */
    public String getName() {
        return name;
    }
}
