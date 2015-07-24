package com.intellicredit.platform.component.validator2;

/**
 * <p>
 *   The <code>InvalidDataException</code> represents a problem occurring
 *     from data being invalid with regard to a specific data constraint.
 * </p>
 */
public class InvalidDataException extends Exception {

	private static final long serialVersionUID = -1329312854123129237L;

	/**
     * <p>
     *  This will throw an <code>Exception</code> that simply states that 
     *    some sort of incorrect/invalid data was supplied.
     * </p>
     */
    public InvalidDataException() {
        super("Invalid Data Supplied.");
    }

    /**
     * <p>
     *  This will throw an <code>Exception</code> that simply states that 
     *    some sort of incorrect/invalid data was supplied, along with a message
     *    indicating the problem.
     * </p>
     *
     * @param message <code>String</code> message indicating what data failed validation.
     */
    public InvalidDataException(String message) {
        super("Invalid Data Supplied: " + message);
    }
}