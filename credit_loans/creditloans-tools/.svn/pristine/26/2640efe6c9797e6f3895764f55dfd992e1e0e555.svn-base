package com.intellicredit.platform.component.situation2;

import com.intellicredit.platform.util.ComparableCalendar;
import java.util.*;
import java.util.regex.*;

public class Operator {
    public static final int EQUAL = 0;
    public static final int NOT_EQUAL = 1;
    public static final int GREATER_EQUAL = 2;
    public static final int GREATER_THAN = 3;
    public static final int LESS_EQUAL = 4;
    public static final int LESS_THAN = 5;
    public static final int BETWEEN = 6;
    public static final int BETREEN_EXCLUDE = 7;
    public static final int MATCH = 8;
    
    public static final Operator OP_EQ = new Operator(EQUAL);
    public static final Operator OP_NE = new Operator(NOT_EQUAL);
    public static final Operator OP_GE = new Operator(GREATER_EQUAL);
    public static final Operator OP_GT = new Operator(GREATER_THAN);
    public static final Operator OP_LE = new Operator(LESS_EQUAL);
    public static final Operator OP_LT = new Operator(LESS_THAN);
    public static final Operator OP_BT = new Operator(BETWEEN);
    public static final Operator OP_BX = new Operator(BETREEN_EXCLUDE);
    public static final Operator OP_MA = new Operator(MATCH);
    
    public static Operator valueOf(int operator) {
        switch(operator) {
            case EQUAL:
                return OP_EQ;
            case NOT_EQUAL:
                return OP_NE;
            case GREATER_EQUAL:
                return OP_GE;
            case GREATER_THAN:
                return OP_GT;
            case LESS_EQUAL:
                return OP_LE;
            case LESS_THAN:
                return OP_LT;
            case BETWEEN:
                return OP_BT;
            case BETREEN_EXCLUDE:
                return OP_BX;
            case MATCH:
                return OP_MA;
            default:
                return OP_EQ;
        }
    }
    
    protected int m_operator;
    
    /** Creates a new instance of Operator */
    public Operator(int operator) {
        m_operator = operator;
    }
    
    public boolean eval(Number obj1, Number obj2) {
        double v1 = obj1.doubleValue();
        double v2 = obj2.doubleValue();
        switch(m_operator) {
            case EQUAL:
                return v1 == v2;
            case NOT_EQUAL:
                return v1 != v2;
            case GREATER_EQUAL:
                return v1 >= v2;
            case GREATER_THAN:
                return v1 > v2;
            case LESS_EQUAL:
                return v1 <= v2;
            case LESS_THAN:
                return v1 < v2;
                
            default:
                return false;
        }
        
    }
    
    public boolean eval(Number obj, Number obj1, Number obj2) {
        double v = obj.doubleValue();
        double v1 = obj1.doubleValue();
        double v2 = obj2.doubleValue();
        switch(m_operator) {
            case BETWEEN:
                return (v >= v1) && (v <= v2);
            case BETREEN_EXCLUDE:
                return (v < v1) || (v > v2);
            default:
                return false;
        }

     }

    public boolean eval(int v1, int v2) {
        switch(m_operator) {
            case EQUAL:
                return v1 == v2;
            case NOT_EQUAL:
                return v1 != v2;
            case GREATER_EQUAL:
                return v1 >= v2;
            case GREATER_THAN:
                return v1 > v2;
            case LESS_EQUAL:
                return v1 <= v2;
            case LESS_THAN:
                return v1 < v2;
                
            default:
                return false;
        }        
    }

    public boolean eval(int v, int v1, int v2) {
        switch(m_operator) {
            case BETWEEN:
                return (v >= v1) && (v <= v2);
            case BETREEN_EXCLUDE:
                return (v < v1) || (v > v2);
            default:
                return false;
        }
    }
    
    public boolean eval(long v1, long v2) {        
        switch(m_operator) {
            case EQUAL:
                return v1 == v2;
            case NOT_EQUAL:
                return v1 != v2;
            case GREATER_EQUAL:
                return v1 >= v2;
            case GREATER_THAN:
                return v1 > v2;
            case LESS_EQUAL:
                return v1 <= v2;
            case LESS_THAN:
                return v1 < v2;
                
            default:
                return false;
        }        
    }
    
    public boolean eval(long v, long v1, long v2) {
        switch(m_operator) {
            case BETWEEN:
                return (v >= v1) && (v <= v2);
            case BETREEN_EXCLUDE:
                return (v < v1) || (v > v2);
            default:
                return false;
        }
    }
    
    public boolean eval(float v1, float v2) {        
        switch(m_operator) {
            case EQUAL:
                return v1 == v2;
            case NOT_EQUAL:
                return v1 != v2;
            case GREATER_EQUAL:
                return v1 >= v2;
            case GREATER_THAN:
                return v1 > v2;
            case LESS_EQUAL:
                return v1 <= v2;
            case LESS_THAN:
                return v1 < v2;
                
            default:
                return false;
        }        
    }
    
    public boolean eval(float v, float v1, float v2) {
        switch(m_operator) {
            case BETWEEN:
                return (v >= v1) && (v <= v2);
            case BETREEN_EXCLUDE:
                return (v < v1) || (v > v2);
            default:
                return false;
        }
    }
    
    public boolean eval(double v1, double v2) {        
        switch(m_operator) {
            case EQUAL:
                return v1 == v2;
            case NOT_EQUAL:
                return v1 != v2;
            case GREATER_EQUAL:
                return v1 >= v2;
            case GREATER_THAN:
                return v1 > v2;
            case LESS_EQUAL:
                return v1 <= v2;
            case LESS_THAN:
                return v1 < v2;
                
            default:
                return false;
        }        
    }
    
    public boolean eval(double v, double v1, double v2) {
        switch(m_operator) {
            case BETWEEN:
                return (v >= v1) && (v <= v2);
            case BETREEN_EXCLUDE:
                return (v < v1) || (v > v2);
            default:
                return false;
        }
    }
    
    public boolean eval(Number obj1, Number obj2, int type) {
        switch(type) {
            case Variable.INT:
                return eval(obj1.intValue(), obj2.intValue());
            case Variable.LONG:
                return eval(obj1.longValue(), obj2.longValue());
            case Variable.FLOAT:
                return eval(obj1.floatValue(), obj2.floatValue());
            case Variable.DOUBLE:
                return eval(obj1.doubleValue(), obj2.doubleValue());
            default:
                    return false;
        }        
    }
    
    public boolean eval(Number obj, Number obj1, Number obj2, int type) {
        switch(type) {
            case Variable.INT:
                return eval(obj.intValue(), obj1.intValue(), obj2.intValue());
            case Variable.LONG:
                return eval(obj.longValue(), obj1.longValue(), obj2.longValue());
            case Variable.FLOAT:
                return eval(obj.floatValue(), obj1.floatValue(), obj2.floatValue());
            case Variable.DOUBLE:
                return eval(obj.doubleValue(), obj1.doubleValue(), obj2.doubleValue());
            default:
                    return false;
        }        
    }

    public boolean eval(String v1, String v2) {
        
        switch(m_operator) {
            case EQUAL:
                return v1.equals(v2);
            case NOT_EQUAL:
                return !v1.equals(v2);
            case GREATER_EQUAL:
                return v1.compareTo(v2) >= 0;
            case GREATER_THAN:
                return v1.compareTo(v2) > 0;
            case LESS_EQUAL:
                return v1.compareTo(v2) <= 0 ;
            case LESS_THAN:
                return v1.compareTo(v2) < 0 ;
            case MATCH:
                return Pattern.matches(v2, v1);
                
            default:
                return false;
        }
    }
    
    public boolean eval(String v, String v1, String v2) {
        switch(m_operator) {
            case BETWEEN:
                return (v.compareTo(v1)>=0) && (v.compareTo(v2)<=0);
            case BETREEN_EXCLUDE:
                return (v.compareTo(v1)<0) || (v.compareTo(v2)>0);
            default:
                return false;
        }
    }
    
    public boolean eval(Date date1, Date date2) {
        ComparableCalendar c1 = new ComparableCalendar(date1);
        ComparableCalendar c2 = new ComparableCalendar(date2);
        switch(m_operator) {
            case EQUAL:
                return c1.compareTo(c2) == 0;
            case NOT_EQUAL:
                return c1.compareTo(c2) != 0;
            case GREATER_EQUAL:
                return c1.compareTo(c2) >= 0;
            case GREATER_THAN:
                return c1.compareTo(c2) > 0;
            case LESS_EQUAL:
                return c1.compareTo(c2) <= 0;
            case LESS_THAN:
                return c1.compareTo(c2) < 0;
                
            default:
                return false;
        }
    }

    public boolean eval(Date date, Date date1, Date date2) {
        ComparableCalendar c = new ComparableCalendar(date);
        ComparableCalendar c1 = new ComparableCalendar(date1);
        ComparableCalendar c2 = new ComparableCalendar(date2);
        switch(m_operator) {
            case BETWEEN:
                return (c.compareTo(c1)>=0) && (c.compareTo(c2)<=0);
            case BETREEN_EXCLUDE:
                return (c.compareTo(c1)<0) || (c.compareTo(c2)>0);

            default:
                return false;
        }
    }
}
