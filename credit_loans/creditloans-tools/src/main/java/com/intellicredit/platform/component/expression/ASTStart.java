package com.intellicredit.platform.component.expression;

/**
 * Start Node
 */
public class ASTStart
    extends SimpleNode {
    public ASTStart(int id) {
        super(id);
    }

    public ASTStart(Parser p, int id) {
        super(p, id);
    }

    /** Accept the visitor. **/
    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
