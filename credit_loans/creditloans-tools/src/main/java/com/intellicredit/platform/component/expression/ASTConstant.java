package com.intellicredit.platform.component.expression;

/**
 * Constant Node
 */
public class ASTConstant
    extends SimpleNode {
    private Object value;

    public ASTConstant(int id) {
        super(id);
    }

    public ASTConstant(Parser p, int id) {
        super(p, id);
    }

    public void setValue(Object val) {
        value = val;
    }

    public Object getValue() {
        return value;
    }

    /** Accept the visitor. **/
    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public String toString() {
        return "Constant: " + value;
    }
}
