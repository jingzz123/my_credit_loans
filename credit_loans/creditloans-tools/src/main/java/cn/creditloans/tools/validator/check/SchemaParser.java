package cn.creditloans.tools.validator.check;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.creditloans.tools.validator.check.compare.Compare;
import cn.creditloans.tools.validator.check.compare.Operator;
import cn.creditloans.tools.validator.check.rule.Rule;
import cn.creditloans.tools.validator.check.rule.RuleAction;
import cn.creditloans.tools.validator.check.rule.RuleCondition;
import cn.creditloans.tools.validator.util.ValidatorConstants;

/**
 * <p>
 *  The <code>SchemaParser</code> class parses an XML Schema and creates
 *    <code>{@link Constraint}</code> objects from it.
 * </p>
 */
public class SchemaParser {

    //private URL m_schemaURL; //The URL of the schema to parse
    
    private InputStream m_inputStream;
    
    private Map<String,List<Constraint>> m_constraints; //The constraints from the schema

    /**
     * <p>
     *  This will create a new <code>SchemaParser</code>, given
     *    the URL of the schema to parse.
     * </p>
     *
     * @param schemaURL the <code>URL</code> of the schema to parse.
     * @throws <code>IOException</code> - when parsing errors occur.
     */
    public SchemaParser(InputStream inputStream) throws IOException {
    	m_inputStream = inputStream;
        m_constraints = new LinkedHashMap<String,List<Constraint>>();
        //m_schemaNamespace = Namespace.getNamespace(SCHEMA_NAMESPACE_URI);
        // Parse the schema and prepare constraints
        parseSchema();
    }

    public SchemaParser(InputStream inputStream,String encoding) throws IOException {
    	m_inputStream = inputStream;
        m_constraints = new LinkedHashMap<String,List<Constraint>>();
        //m_schemaNamespace = Namespace.getNamespace(SCHEMA_NAMESPACE_URI);
        // Parse the schema and prepare constraints
        parseSchema(encoding);
    }

    /**
     * <p>
     *  This will return constraints found within the document.
     * </p>
     *
     * @return <code>Map</code> - the schema-defined constraints.
     */
    public Map<String,List<Constraint>> getConstraints() {
        return m_constraints;
    }

    /**
     * <p>
     *  This will get the <code>Constraint</code> object for
     *    a specific constraint name. If none is found, this
     *    will return <code>null</code>.
     * </p>
     *
     * @param constraintName name of constraint to look up.
     * @return <code>Constraint</code> - constraints for
     *         supplied name.
     */
    public Constraint getConstraint(String constraintName) {
        Object o = m_constraints.get(constraintName);
        if (o != null) {
            return (Constraint) o;
        }
        else {
            return null;
        }
    }

    /**
     * <p>
     *  This will do the work of parsing the schema.
     * </p>
     *
     * @throws <code>IOException</code> - when parsing errors occur.
     */
    private void parseSchema() throws IOException {
    	parseSchema(ValidatorConstants.ENCODING);
    }
    
    private void parseSchema(String encoding) throws IOException {
        /**
         * Create builder to generate JDOM representation of XML Schema,
         *   without validation and using Apache Xerces.
         */
        // XXX: Allow validation, and allow alternate parsers
    	SAXReader builder = new SAXReader();
        try {
        	InputStreamReader reader = new InputStreamReader(m_inputStream, ValidatorConstants.ENCODING);
            Document schemaDoc = builder.read(reader);
            // Handle attributes
            List<?> attributes = schemaDoc.getRootElement().element("define_item").elements("attribute");
            for (Iterator<?> i = attributes.iterator(); i.hasNext(); ) {
                // Iterate and handle
                Element attribute = (Element) i.next();
                handleAttribute(attribute);
            }
            // Handle attributes nested within complex types
        }
        catch (Exception e) {
            throw new IOException("Problems occurred:" + e.getMessage(), e);
        }
    }

    /**
     * <p>
     *  This will convert an attribute into constraints.
     * </p>
     *
     * @throws <code>IOException</code> - when parsing errors occur.
     */
    private void handleAttribute(Element attribute) throws IOException {
        // Get the attribute name and create a Constraint
        String name = attribute.attributeValue("name");
        if (name == null) {
            throw new IOException("All schema attributes must have names.");
        }
        Constraint constraint = new Constraint(name);
        // See if there is a data type on this constraint
        String schemaType = attribute.attributeValue("type");
        if (schemaType != null) {
            constraint.setDataType(DataConverter.getInstance().getJavaType(schemaType));
        }

        String alignType = attribute.attributeValue("align");
        if(alignType != null){
            constraint.setAlignType(alignType);
        }
        String defaultValue = attribute.attributeValue("default");
        if (defaultValue != null) {
            constraint.setDefault(defaultValue);
        }

        String description = attribute.attributeValue("desc");
        if (description != null) {
            constraint.setDescription(description.trim());
        }
        
        String need = attribute.attributeValue("need");
        if (need != null) {
            constraint.setM_need(need);
        }

        String use = attribute.attributeValue("use");
        if (use != null) {
            if (use.equals("false")) {
                constraint.setUse(false);
                return;
            }
            else {
                constraint.setUse(true);
            }
        }
        else {
            constraint.setUse(true);
        }
        
        //加入是否可以为空的判断, add by wu 2004.7.7
        String allowNull = attribute.attributeValue("allow_null");
        if (allowNull != null) {
            if (allowNull.equalsIgnoreCase("yes")) {
                constraint.setAllowNull(true);
            }
        }
        String beginPos = attribute.attributeValue("begin_pos");
        if (beginPos != null) {
        	constraint.setBeginPos(Integer.parseInt(beginPos));
        }
        String endPos = attribute.attributeValue("end_pos");
        if (endPos != null) {
        	constraint.setEndPos(Integer.parseInt(endPos));
        }
        /*  // Handle the data type
          schemaType = simpleType.attributeValue("baseType");
          if (schemaType == null) {
         throw new IOException("No data type specified for constraint " + name);
          }
         constraint.setDataType(DataConverter.getInstance().getJavaType(schemaType));
         */
        // Handle any allowed values
        if (attribute.element("list") != null) {
            List<?> allowedValues = attribute.element("list").elements("enumeration");
            if (allowedValues != null) {
                for (Iterator<?> i = allowedValues.iterator(); i.hasNext(); ) {
                    Element allowedValue = (Element) i.next();
                    constraint.addAllowedValue(allowedValue.attributeValue("value"));
                }
            }
        }

        // Handle ranges
        String value, booleanValue;
        Element boundary = attribute.element("min");
        if (boundary != null) {
            value = boundary.attributeValue("value");
            constraint.setMinExclusive(value);
            booleanValue = boundary.attributeValue("include");
            constraint.setMinInclusive(booleanValue.equalsIgnoreCase("yes") ? value : "");
        }

        boundary = attribute.element("max");
        if (boundary != null) {
            value = boundary.attributeValue("value");
            constraint.setMaxExclusive(value);
            booleanValue = boundary.attributeValue("include");
            constraint.setMaxInclusive(booleanValue.equalsIgnoreCase("yes") ? value : "");
        }

        boundary = attribute.element("length");
        if (boundary != null) {
            value = boundary.attributeValue("value");
            constraint.setLength(Integer.parseInt(value));
        }

        boundary = attribute.element("maxLen");
        if (boundary != null) {
            value = boundary.attributeValue("value");
            constraint.setMaxLength(Integer.parseInt(value));
        }

        boundary = attribute.element("minLen");
        if (boundary != null) {
            value = boundary.attributeValue("value");
            constraint.setMinLength(Integer.parseInt(value));
        }

        boundary = attribute.element("pattern");
        if (boundary != null) {
            value = boundary.attributeValue("value");
            constraint.setPattern(value);
        }

        boundary = attribute.element("whiteSpace");
        if (boundary != null) {
            value = boundary.attributeValue("value");
            constraint.setWhiteSpace(value);
        }
        //满足特定函数，就顶一个函数名，传入参数暂时就只有一个，为当前的值
        boundary = attribute.element("function");
        if(boundary != null){
        	value = boundary.attributeValue("value");
        	constraint.setFunction(value);
        }
        //获取compare
        try{
        	List<?> compares = attribute.elements("compare");
	        if(compares != null&& compares.size()>0){
	        	List<Compare> compareList = new LinkedList<Compare>();
	        	int size = compares.size();
	        	for(int i=0; i<size; i++){
	        		Element tmpElement = (Element)compares.get(i);
		        	String operator = tmpElement.attributeValue("operator");
		        	String parameter = tmpElement.attributeValue("parameter");
		        	String errorMsg = tmpElement.attributeValue("desc");
		        	Operator oper = convertOpType(operator);
		        	Compare compare = new Compare(oper,parameter,errorMsg);
		        	compareList.add(compare);
	        	}
	        	constraint.setCompares(compareList);
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }
        //rules-->rule
        if (attribute.element("rules") != null) {
            List<?> allowedValues = attribute.element("rules").elements("rule");
            if (allowedValues != null) {
            	Element allowedValue = null;
            	Element conElement,actionElement;
            	Rule rule;
            	RuleCondition rCondition;
            	RuleAction rAction;
            	String tmp;
                for (Iterator<?> i = allowedValues.iterator(); i.hasNext(); ) {
                    allowedValue = (Element) i.next();
                    rule = new Rule();
                    rule.setConstraintName(name);
                    rule.setName(allowedValue.attributeValue("name"));//名称
                    rule.setDesc(allowedValue.attributeValue("desc"));//描述
                    conElement = allowedValue.element("condition");
                    actionElement = allowedValue.element("action");
                    rCondition = new RuleCondition();
                    rCondition.setLhs(conElement.attributeValue("lhs"));
                    rCondition.setRhs(conElement.attributeValue("rhs"));
                    rCondition.setOpertor(convertOpType(conElement.attributeValue("operator")));
                    rCondition.setValueType(DataConverter.getInstance().getJavaType(conElement.attributeValue("value_type")));
                    rule.setCondition(rCondition);
                    rAction = new RuleAction();
                    rAction.setFunctionName(actionElement.attributeValue("function"));
                    tmp = actionElement.attributeValue("other_parameters");
                    if(tmp!=null&&!tmp.trim().equals("")){
                    	rAction.setOtherParameters(tmp.split(";"));
                    }else{
                    	rAction.setOtherParameters(new String[0]);
                    }
                    rule.setAction(rAction);
                    constraint.putRule(rule);
                }
            }
        }
        // Store this constraint
        if(m_constraints.containsKey(name)){
        	m_constraints.get(name).add(constraint);
        }else{
        	List<Constraint> list = new LinkedList<Constraint>();
        	list.add(constraint);
        	m_constraints.put(name, list);
        }
    }
    
    private Operator convertOpType(String type) {
        if (type.equals("EQUAL")) {
            return Operator.OP_EQ;
        }
        else if (type.equals("NOT_EQUAL")) {
            return Operator.OP_NE;
        }
        else if (type.equals("GREATER_EQUAL")) {
            return Operator.OP_GE;
        }
        else if (type.equals("GREATER_THAN")) {
            return Operator.OP_GT;
        }
        else if (type.equals("LESS_EQUAL")) {
            return Operator.OP_LE;
        }
        else if (type.equals("LESS_THAN")) {
            return Operator.OP_LT;
        }
        else if (type.equals("MATCH")) {
            return Operator.OP_MA;
        }

        return null;
    }

}
