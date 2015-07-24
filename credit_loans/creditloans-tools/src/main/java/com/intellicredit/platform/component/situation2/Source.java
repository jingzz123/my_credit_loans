package com.intellicredit.platform.component.situation2;

public interface Source {

    /**
     * Get the value of source.
     * @return the value of source.<br>
     *          There are three types of value allowed to be returned.<br>
     *          Number ----- the Number type include all java number class.
     *                       e.g. Integer, Long, Short, Byte, etc.<br>
     *          TEXT   ----- the return value can be any class, we call value.toString()
     *                       to convert it to String.<br>
     *          DATE   ----- the return value must be type of java.util.Date.
     *
     */
    public abstract Object getValue();

    /**
     * Reset the source.
     */
    public abstract void reset();
}
