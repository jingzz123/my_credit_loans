package cn.creditloans.tools.validator.check;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  The <code>DataConverter</code> class provides utility
 *    methods to convert XML Schema data types to Java
 *    types and Java data types to XML Schema types.
 * </p>
 */
public class DataConverter {

    /** Singleton instance */
    private static DataConverter instance = null;

    /** Mappings from Java to XML Schema */
    private Map<?,?> schemaMappings;

    /** Mappings from XML Schema to Java */
    private Map<?,?> javaMappings;

    /**
     * <p>
     *  This (intentionally left) private constructor handles initialization
     *    of the data mappings. It can only be created internally, and forces
     *    the singleton pattern to be used.
     * </p>
     */
    private DataConverter() {
        schemaMappings = getSchemaMappings();
        javaMappings = getJavaMappings();
    }

    /**
     * <p>
     *  This will retrieve the singleton instance of this class, allowing
     *    it to be used across applications.
     * </p>
     *
     * @return <code>DataConverter</code> - the singleton instance to use.
     */
    public static DataConverter getInstance() {
        if (instance == null) {
            synchronized (DataConverter.class) {
                if (instance == null) {
                    instance = new DataConverter();
                }
            }
        }
        return instance;
    }

    /**
     * <p>
     *  This will return the Java data type given an XML Schema data type.
     * </p>
     *
     * @param schemaType XML Schema data type (<code>String</code> format).
     * @return <code>String</code> - Java data type that is comparable.
     */
    public String getJavaType(String schemaType) {
        return (String)javaMappings.get(schemaType);
    }

    /**
     * <p>
     *  This will return the XML Schema data type given a Java data type.
     * </p>
     *
     * @param javaType Java data type (<code>String</code> format).
     * @return <code>String</code> - XML Schema data type that is comparable.
     */
    public String getSchemaType(String javaType) {
        return (String)schemaMappings.get(javaType);
    }

    /**
     * <p>
     *  This will generate the data mappings from XML Schema to Java.
     * </p>
     *
     * @return <code>Map</code> - data type mappings.
     */
    private Map<String,String> getSchemaMappings() {
        Map<String,String> map = new HashMap<String,String>();

        // Key is Java type, value is XML Schema type
        map.put("String", "string");
        map.put("boolean", "boolean");
        map.put("float", "float");
        map.put("double", "double");

        map.put("CSTRING", "string");
        map.put("STRING", "string_eng");
        map.put("IDATE", "idate");
        map.put("IDATETIME", "idatetime");
        map.put("LONG", "long");
        map.put("INT", "int");
        // XXX: Need to map these extra data types
        // map.put("", "decimal");
        // map.put("", "timeDuration");
        // map.put("", "recurringDuration");
        // map.put("", "binary");
        // map.put("", "uriReference");
        // map.put("", "ID");
        // map.put("", "IDREF");
        // map.put("", "ENTITY");
        // map.put("", "NOTATION");
        // map.put("", "QName");
        // map.put("", "language");
        // map.put("", "IDREFS");
        // map.put("", "ENTITIES");
        // map.put("", "NMTOKEN");
        // map.put("", "Name");
        // map.put("", "NCName");
        // map.put("", "integer");
        // map.put("", "nonPositiveInteger");
        // map.put("", "negativeInteger");
        map.put("long", "long");
        map.put("int", "int");
        map.put("short", "short");
        map.put("byte", "byte");
        // map.put("", "nonNegativeInteger");
        // map.put("", "negativeInteger");
        // map.put("", "unsignedLong");
        // map.put("", "unsignedInt");
        // map.put("", "unsignedShort");
        // map.put("", "unsignedByte");
        // map.put("", "positiveInteger");
        // map.put("", "timeInstant");
        // map.put("time", "time");
         map.put("timePeriod", "timePeriod");
         map.put("Date", "date");
         map.put("date_int", "date_int");
         map.put("date_int_null", "date_int_null");
        // map.put("", "month");
        // map.put("", "year");
        // map.put("", "century");
        // map.put("", "recurringDate");
        // map.put("", "recurringDay");

        return map;
    }

    /**
     * <p>
     *  This will generate the data mappings from Java to XML Schema.
     * </p>
     *
     * @return <code>Map</code> - data type mappings.
     */
    private Map<String,String> getJavaMappings() {
        Map<String,String> map = new HashMap<String,String>();

        // Key is XML Schema type, value is Java type
        map.put("string", "String");
        map.put("boolean", "boolean");
        map.put("float", "float");
        map.put("double", "double");

        map.put("CSTRING", "string");
        map.put("STRING", "string_eng");
        map.put("IDATE", "idate");
        map.put("IDATETIME", "idatetime");
        map.put("LONG", "long");
        map.put("INT", "int");
        map.put("DOUBLE", "double");
        map.put("FLOAT", "float");

        // XXX: Need to map these extra data types
        // map.put("decimal", "");
        // map.put("timeDuration", "");
        // map.put("recurringDuration", "");
        // map.put("binary", "");
        // map.put("uriReference", "");
        // map.put("ID", "");
        // map.put("IDREF", "");
        // map.put("ENTITY", "");
        // map.put("NOTATION", "");
        // map.put("QName", "");
        // map.put("language", "");
        // map.put("IDREFS", "");
        // map.put("ENTITIES", "");
        // map.put("NMTOKEN", "");
        // map.put("Name", "");
        // map.put("NCName", "");
        map.put("integer", "int");
        // map.put("nonPositiveInteger", "");
        // map.put("negativeInteger", "");
        map.put("long", "long");
        map.put("int", "int");
        map.put("short", "short");
        map.put("byte", "byte");
        // map.put("nonNegativeInteger", "");
        // map.put("negativeInteger", "");
        // map.put("unsignedLong", "");
        // map.put("unsignedInt", "");
        // map.put("unsignedShort", "");
        // map.put("unsignedByte", "");
        // map.put("positiveInteger", "");
        // map.put("timeInstant", "");
       //  map.put("time", "time");
         map.put("timePeriod", "timePeriod");
         map.put("date", "Date");
        // map.put("month", "");
        // map.put("year", "");
        // map.put("century", "");
        // map.put("recurringDate", "");
        // map.put("recurringDay", "");

        return map;
    }

}
