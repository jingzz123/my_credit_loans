package com.intellicredit.platform.service.rule.forward2;

import java.io.*;
import java.util.*;
import java.text.*;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.intellicredit.platform.component.situation2.*;
import com.intellicredit.platform.component.transaction.*;
import com.intellicredit.platform.util.*;
import com.intellicredit.platform.component.expression.SymbolTable;

public class InferenceFileEngine extends InferenceEngine{

	private boolean m_needParse; //是否需要重新解析BaseDef文件
    private String m_filePath; //当前rule文件所在的directory
    private String m_baseFile; //BaseDef文件对应的文件名
    private Element m_baseRoot; //BaseDef文件所在的目录
    protected boolean m_useCache = true; //是否需要使用缓存中的XML文件

    public InferenceFileEngine(String name) {
    	super(name);
    }

    public InferenceFileEngine(String name, Situation situation) {
        super(name,situation);
    }

    public void isUseCache(boolean b) {
        m_useCache = b;
    }

    /**
     * 
     * @param file：ruleset文件名
     * @return
     */
    public boolean setEngine(File file) {
        init();
        try {
            SAXReader builder = new SAXReader();
            //SAXBuilder builder = new SAXBuilder();
            Document doc = builder.read(file);//TODO，在web应用中有问题，需要后续变更

            String filePath = file.getParent();
            m_needParse = !filePath.equals(m_filePath);
            if (m_needParse) {
                m_filePath = filePath;

            }
            Element root = doc.getRootElement();
            return parseXML(root);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setEngine(String s) {
        init();
        try {
            SAXReader builder = new SAXReader();
            //SAXBuilder builder = new SAXBuilder();
            Document doc = builder.read(new File(s));//TODO，在web应用中有问题，需要后续变更

            String filePath = new File(s).getParent();
            m_needParse = !filePath.equals(m_filePath);
            if (m_needParse) {
                m_filePath = filePath;

            }
            Element root = doc.getRootElement();
            return parseXML(root);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setEngine(java.net.URL url) {
        init();
        try {
            SAXReader builder = new SAXReader();
            //SAXBuilder builder = new SAXBuilder();
            Document doc = builder.read(url);//TODO，在web应用中有问题，需要后续变更
            Element root = doc.getRootElement();
            return parseXML(root);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //XML Parser
    private void init() {
        m_variables = null;
        m_variablesMap = new HashMap<String,RuleVariable>();
        m_conditionList.clear();
        m_ruleList = null;
    }

    private boolean parseXML(Element root) throws Exception {
        Map<String,RuleAction> actionMap = new HashMap<String,RuleAction>();
        Map<String,PreRuleCondition> preConditionMap = new HashMap<String,PreRuleCondition>();
        Map<String,RuleCondition> conditionMap = new HashMap<String,RuleCondition>();

        Element definition = root.element("definition");
        String file = definition.elementText("file");
        if (!m_needParse) {
            m_needParse = !file.equals(m_baseFile);
        }
        if (m_needParse || !m_useCache) {
            m_baseFile = file;
            if (!reloadBaseDef()) { //解析BaseDef文件
                return false;
            }
        }

        //解析Rule中使用到的变量
        List<?> varList = definition.element("variables").elements("var");
        List<RuleVariable> objVarList = parseVariables(m_baseRoot, varList);
        if (varList == null) {
            return false;
        }

        //解析Rule中使用到的动作
        List<?> actionList = definition.element("actions").elements("action");
        actionMap = parseActions(m_baseRoot, actionList, objVarList);
        if (actionMap == null) {
            return false;
        }

        //parseActions() also changes m_variables
        if(m_variablesMap==null){
            m_variablesMap = new HashMap<String,RuleVariable>();
        }
        for (int i = 0; i < m_variables.length; i++) {
            //ExpressionSource.addVariable(m_variables[i]);
            m_variablesMap.put(m_variables[i].getName(),m_variables[i]);

        }

        for (Iterator<?> iter = actionMap.values().iterator(); iter.hasNext(); ) {
            RuleAction action = (RuleAction) iter.next();
            if (action instanceof FunCallAction) {
                ( (FunCallAction) action).parseArgs();
            }
        }

        m_name = root.attributeValue("name");
        //解析Condition-s
        List<?> conditionList = root.element("conditions").elements("condition");
        for (Iterator<?> it = conditionList.iterator(); it.hasNext(); ) {
            Element condNode = (Element) it.next();
            String name = condNode.attributeValue("name");
            String lhs = condNode.attributeValue("lhs");
            Operator op = convertOpType(condNode.attributeValue("operator"));
            String rhs = condNode.attributeValue("rhs");
            int type = convertVarType(condNode.attributeValue("type"));
            ExpRuleCondition condition = new ExpRuleCondition(name, type, lhs, op, rhs, this);
            conditionMap.put(name, condition);
        }

        //解析PreCondition-s
        Element preConElement = root.element("preconditions");
        if(preConElement!=null){
            List<?> preConList = preConElement.elements("precondition");
            for (Iterator<?> it = preConList.iterator(); it.hasNext(); ) {
                Element preCondNode = (Element) it.next();
                String name = preCondNode.attributeValue("name");
                PreRuleCondition preCondition = new PreRuleCondition();
                preCondition.setEngine(this);
                preCondition.setName(name);
                List<?> rulecondList = preCondNode.elements("condition");
                List<RuleCondition> condsList = new ArrayList<RuleCondition>();
                for (Iterator<?> it2 = rulecondList.iterator(); it2.hasNext(); ) {
                    String condName = ( (Element) it2.next()).attributeValue("name");
                    condsList.add(conditionMap.get(condName));
                }
                preCondition.setConditionRef(condsList);
                preConditionMap.put(name, preCondition);
            }
        }

        //解析Rule-s
        List<?> ruleList = root.element("rules").elements("rule");
        RuleSet ruleset = new RuleSet();
        for (Iterator<?> it = ruleList.iterator(); it.hasNext(); ) {
            Element ruleNode = (Element) it.next();
            String name = ruleNode.attributeValue("name");
            String strDesc = null;
            Attribute descElement = ruleNode.attribute("desc");
            if (descElement != null) {
                strDesc = descElement.getValue();
            }
            //add by wu 2004/08/09, rule是否可用的属性
            boolean isUsed = true;
            Attribute useElement = ruleNode.attribute("use");
            if (useElement != null) {
                String strUsed = useElement.getValue();
                if (strUsed.equals("false")) {
                    isUsed = false;
                    //add by wu 2004/10/12, 如果为use=false, 那就不将此rule得到
                    continue;
                }
            }

            List<?> rulecondList = ruleNode.elements("condition");
            List<RuleCondition> condsList = new ArrayList<RuleCondition>();
            for (Iterator<?> it2 = rulecondList.iterator(); it2.hasNext(); ) {
                String condName = ( (Element) it2.next()).attributeValue("name");
                condsList.add(conditionMap.get(condName));
            }

            //Add PreConditions,方法:直接将从PreCondition中得到所有的conditions给Rule
            List<?> rulePreCondList = ruleNode.elements("precondition");
            if(rulePreCondList!=null){
                for (Iterator<?> it2 = rulePreCondList.iterator(); it2.hasNext(); ) {
                    String preCondName = ( (Element) it2.next()).attributeValue("name");
                    List<RuleCondition> curCondList = ((PreRuleCondition)preConditionMap.get(preCondName)).conditionRefs();
                    condsList.addAll(curCondList);
                }
            }
            RuleCondition[] conds = (RuleCondition[]) condsList.toArray(new RuleCondition[0]);

            List<?> ruleactList = ruleNode.elements("action");
            List<RuleAction> actionsList = new ArrayList<RuleAction>();
            for (Iterator<?> it2 = ruleactList.iterator(); it2.hasNext(); ) {
                String actName = ( (Element) it2.next()).attributeValue("name");
                actionsList.add(actionMap.get(actName));
            }
            RuleAction[] actions = (RuleAction[]) actionsList.toArray(new RuleAction[0]);

            Rule rule = new Rule(name, conds, actions);
            rule.setDescription(strDesc);
            rule.setUse(isUsed); //add by wu 2004/08/09
            ruleset.add(rule);
        }
        setRuleSet(ruleset);

        m_conditions = m_conditionList.toArray(new RuleCondition[0]);

        return true;
    }

    private void setRuleSet(RuleSet rules) {
        m_ruleList = rules.getRuleList();

        //generate condition set
        int size = m_ruleList.size();
        Set<RuleCondition> set = new HashSet<RuleCondition>();
        //得到所有Rule-s使用的所有Condition-s
        for (int i = 0; i < size; i++) {
            RuleCondition[] conditions = ( (Rule) m_ruleList.get(i)).
                getAntecedents();
            if (conditions != null) {
                for (int j = 0; j < conditions.length; j++) {
                    set.add(conditions[j]);
                }
            }
        }

        Iterator<RuleCondition> it = set.iterator();
        //将所有Rule-s使用的所有Condition-s存放到m_conditionList
        while (it.hasNext()) {
            m_conditionList.add(it.next());
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

    private int convertVarType(String type) {
        if (type.equals("NUMBER")) {
            return 1;
        }
        else if (type.equals("TEXT")) {
            return 2;
        }
        else if (type.equals("DATE")) {
            return 3;
        }
        else if (type.equals("INT")) {
            return 4;
        }
        else if (type.equals("FLOAT")) {
            return 5;
        }
        else if (type.equals("LONG")) {
            return 6;
        }
        else if (type.equals("DOUBLE")) {
            return 7;
        }

        return -1;
    }

    private Object convertVar(int type, String value) {
        try {
            if (type == 1 || type == 7) {
                return value == null ? null : Double.valueOf(value);
            }
            else if (type == 2) {
                return value == null ? null : value;
            }
            else if (type == 4) {
                return value == null ? null : Integer.valueOf(value);
            }
            else if (type == 5) {
                return value == null ? null : Float.valueOf(value);
            }
            else if (type == 6) {
                return value == null ? null : Long.valueOf(value);
            }
            else if (type == 3) {
                if (value == null) {
                    return null;
                }
                SimpleDateFormat dt1 = CalendarUtil.simpleDateTimeFormatter != null ?
                    CalendarUtil.simpleDateTimeFormatter :
                    CalendarUtil.defaultSimpleDateTimeFormatter;
                SimpleDateFormat dt2 = CalendarUtil.simpleDateFormatter != null ?
                    CalendarUtil.simpleDateFormatter :
                    CalendarUtil.defaultSimpleDateFormatter;
                try {
                    return dt1.parse(value);
                }
                catch (ParseException e) {
                    return dt2.parse(value);
                }
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            if (type == 1 || type == 7) {
                return new Double(0);
            }
            else if (type == 4) {
                return new Integer(0);
            }
            else if (type == 5) {
                return new Float(0);
            }
            else if (type == 6) {
                return new Long(0);
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
        return null;
    }

    private boolean reloadBaseDef() {
        try {
            SAXReader builder = new SAXReader();
            String basePath = null;
            m_baseRoot = null;

            File file = new File(m_filePath,m_baseFile);
            if (!file.exists()) {
                basePath = new File(m_filePath).getParent();
                file = new File(basePath,"basedef" + File.separatorChar + m_baseFile);
            }
            else {
                basePath = m_filePath;
            }
            Document doc = builder.read(file);
            m_baseRoot = doc.getRootElement();

            if (m_baseRoot == null) {
                return false;
            }

            Element input = m_baseRoot.element("input_schema");
            String clsDir = input.attributeValue("clsdir");
            String clsName = input.attributeValue("clsname");

            RootDirClassLoader clsLoader = new RootDirClassLoader(clsDir);
            try {
                m_schema = (Schema) clsLoader.loadClass(clsName).newInstance();
                //TransactionSource.setSchema(m_schema);
            }
            catch (Throwable e) {
                try {
                    clsLoader.setRootDir(new File(basePath,"schema").getAbsolutePath());
                    m_schema = (Schema) clsLoader.loadClass(clsName).newInstance();
                }
                catch (Throwable ee) {
                    ee.printStackTrace();
                    m_schema = null;
                }
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Element root: 指向basedef文件的root, List varList: Rule中的variables,是Element
    private List<RuleVariable> parseVariables(Element root, List<?> varList) {
        List<RuleVariable> varibaleList = new ArrayList<RuleVariable>();

        Element variables = root.element("variables");
        outer:for (Iterator<?> it = varList.iterator(); it.hasNext(); ) {
            Element var = (Element) it.next();

            String varName = var.attributeValue("name");
            for (int i = 0; i < varibaleList.size(); i++) {//相同变量名的只先获取一次
                if ( varibaleList.get(i).getName().equals(varName)) {
                    continue outer;
                }
            }

            String varType = var.attributeValue("vartype");
            boolean output = var.attributeValue("output") != null;

            Element mid = variables.element(varType);
            List<?> innerList = mid.elements("var");
            Element innerVar = null;
            //根据Rule中定义的变量的名称,察看BaseDef中是否定义了此变量
            for (Iterator<?> it2 = innerList.iterator(); it2.hasNext(); ) {
                Element elm = (Element) it2.next();
                if (elm.attributeValue("name").equals(varName)) {
                    innerVar = elm;
                    break;
                }
            }
            if (innerVar == null) {
                return null;
            }
            String type = innerVar.attributeValue("type");
            String description = null; //变量的描述
            Attribute descObj = innerVar.attribute("desc");
            if (descObj != null) {
                description = descObj.getValue().trim();
            }
            if (varType.equals("simple")) {
                int itype = convertVarType(type);
                String varValue = innerVar.attributeValue("initvalue");
                if (varValue.equals("")) {
                    varValue = null;
                }
                RuleVariable rv = RuleVariable.newSimpleRuleVariable(varName,itype, this,convertVar(itype, varValue));
                rv.isOutput(output);
                rv.setDescription(description);
                varibaleList.add(rv);
            }
            else if (varType.equals("transaction")) {
                String field = innerVar.attributeValue("field");
                String subId = innerVar.attributeValue("subid");
                if (subId != null && !subId.equals("")) {
                    field = field.substring(field.indexOf('.') + 1);
                }
                RuleVariable rv = RuleVariable.newTransactionRuleVariable(varName, convertVarType(type), field, subId,m_schema,this);
                rv.isOutput(output);
                rv.setDescription(description);
                varibaleList.add(rv);
            }
            else if (varType.equals("situation")) {
                int itype = convertVarType(type);
                String attrName = innerVar.attributeValue("attrname");
                String varValue = innerVar.attributeValue("initvalue");
                RuleVariable rv = RuleVariable.newExternalRuleVariable(varName, convertVarType(type), attrName, this,convertVar(itype, varValue));
                rv.isOutput(output);
                rv.setDescription(description);
                varibaleList.add(rv);
            }
            else if (varType.equals("expression")) {
                int itype = convertVarType(type);
                String varValue = innerVar.attributeValue("content");
                if (varValue.equals("")) {
                    varValue = null;
                }
                RuleVariable rv = RuleVariable.newExpressionRuleVariable(varName, itype, varValue,this);
                rv.setDescription(description);
                rv.isOutput(output);
                varibaleList.add(rv);

                if (varValue != null) {
                    jep.initSymTab();
                    jep.parseExpression(varValue);
                    if (jep.hasError()) {
                        continue;
                    }
                    SymbolTable st = jep.getSymbolTable();
                    for (Iterator<?> it3 = st.keySet().iterator(); it3.hasNext(); ) {
                        String var1 = (String) it3.next();
                        if (var1.charAt(0) == '$') {
                            var1 = var1.substring(1);
                            boolean found = false;
                            for (int j = 0; j < varibaleList.size(); j++) {
                                if ( ( (RuleVariable) varibaleList.get(j)).getName().
                                    equals(var1)) {
                                    found = true;
                                    break;
                                }
                            }
                            //当Expression中使用的其它变量没有被Rule中Condition直接使用时,将利用basedef中定义变量重新解析使用的变量
                            if (!found) {
                                parseVariable(root, var1, varibaleList);
                            }
                        }
                    }
                }
            }
            else {
                return null;
            }
        }
        return varibaleList;
    }

    private void parseVariable(Element root, String varName, List<RuleVariable> vList) {
        RuleVariable result = null;
        List<?> varList = root.element("variables").elements();
        for (Iterator<?> it = varList.iterator(); it.hasNext(); ) {
            Element mid = (Element) it.next();
            List<?> innerList = mid.elements("var");
            Element innerVar = null;
            for (Iterator<?> it2 = innerList.iterator(); it2.hasNext(); ) {
                Element elm = (Element) it2.next();
                if (elm.attributeValue("name").equals(varName)) {
                    innerVar = elm;
                    break;
                }
            }
            if (innerVar == null) {
                continue;
            }
            String varType = mid.getName();
            String type = innerVar.attributeValue("type");
            String description = null; //变量的描述
            Attribute descObj = innerVar.attribute("desc");
            if (descObj != null) {
                description = descObj.getValue().trim();
            }
            if (varType.equals("simple")) {
                int itype = convertVarType(type);
                String varValue = innerVar.attributeValue("initvalue");
                if (varValue.equals("")) {
                    varValue = null;
                }
                result = RuleVariable.newSimpleRuleVariable(varName, itype,this, convertVar(itype, varValue));
                result.setDescription(description);
                vList.add(result);
            }
            else if (varType.equals("transaction")) {
                String field = innerVar.attributeValue("field");
                String subId = innerVar.attributeValue("subid");
                if (subId != null && !subId.equals("")) {
                    field = field.substring(field.indexOf('.') + 1);
                }
                result = RuleVariable.newTransactionRuleVariable(varName, convertVarType(type), field, subId,m_schema,this);
                result.setDescription(description);
                vList.add(result);
            }
            else if (varType.equals("situation")) {
                int itype = convertVarType(type);
                String attrName = innerVar.attributeValue("attrname");
                String varValue = innerVar.attributeValue("initvalue");
                result = RuleVariable.newExternalRuleVariable(varName, convertVarType(type), attrName, this, convertVar(itype, varValue));
                result.setDescription(description);
                vList.add(result);
            }
            else if (varType.equals("expression")) {
                int itype = convertVarType(type);
                String varValue = innerVar.attributeValue("content");
                if (varValue.equals("")) {
                    varValue = null;
                }
                result = RuleVariable.newExpressionRuleVariable(varName, itype, varValue,this);
                result.setDescription(description);
                vList.add(result);

                if (varValue != null) {
                    jep.initSymTab();
                    jep.parseExpression(varValue);
                    if (jep.hasError()) {
                        continue;
                    }
                    SymbolTable st = jep.getSymbolTable();
                    for (Iterator<?> it3 = st.keySet().iterator(); it3.hasNext(); ) {
                        String var = (String) it3.next();
                        if (var.charAt(0) == '$') {
                            var = var.substring(1);
                            boolean found = false;
                            for (int j = 0; j < vList.size(); j++) {
                                if ( ( (RuleVariable) vList.get(j)).getName().equals(var)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                parseVariable(root, var, vList);
                            }
                        }
                    }
                }
            }
        }
    }

    private Map<String,RuleAction> parseActions(Element root, List<?> actionList, List<RuleVariable> varList) {
        Map<String,RuleAction> result = new HashMap<String,RuleAction>();

        Element actions = root.element("actions");
        for (Iterator<?> it = actionList.iterator(); it.hasNext(); ) {
            Element action = (Element) it.next();
            String actName = action.attributeValue("name");
            String actType = action.attributeValue("acttype");

            Element mid = actions.element(actType);
            List<?> innerList = mid.elements("action");
            Element innerAct = null;
            for (Iterator<?> it2 = innerList.iterator(); it2.hasNext(); ) {
                Element elm = (Element) it2.next();
                if (elm.attributeValue("name").equals(actName)) {
                    innerAct = elm;
                    break;
                }
            }
            if (innerAct == null) {
                return null;
            }
            if (result.containsKey(actName)) {
                continue;
            }
            if (actType.equals("nameaction")) {
                NameAction nameAct = new NameAction(actName);
                nameAct.setEngine(this);
                result.put(actName, nameAct);
            }
            else if (actType.equals("varaction")) {
                String varName = innerAct.attributeValue("var");
                int i;
                for (i = 0; i < varList.size(); i++) {//判断varaction是否为BaseDef定义的SIMPLE变量
                    if ( varList.get(i).getName().equals(varName)
                        &&varList.get(i).getSourceType() == RuleVariable.SIMPLE) {
                        break;
                    }
                }
                if (i >= varList.size()) {//如果不是定义的SIMPLE变量，只是简单创建一个ExpVarAction
                    result.put(actName, new ExpVarAction(actName, null, null,this));
                }
                else {
                    String varValue = innerAct.attributeValue("value");
                    if (varValue.equals("")) {
                        varValue = null;
                    }
                    result.put(actName,new ExpVarAction(actName,(RuleVariable) varList.get(i), varValue,this));

                    if (varValue != null) {
                        jep.initSymTab();
                        jep.parseExpression(varValue);
                        if (jep.hasError()) {
                            continue;
                        }
                        SymbolTable st = jep.getSymbolTable();
                        for (Iterator<?> it3 = st.keySet().iterator(); it3.hasNext(); ) {
                            String var = (String) it3.next();
                            if (var.charAt(0) == '$') {
                                var = var.substring(1);
                                boolean found = false;
                                for (int j = 0; j < varList.size(); j++) {
                                    if ( ( (RuleVariable) varList.get(j)).
                                        getName().equals(var)) {
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) {
                                    parseVariable(m_baseRoot, var, varList);
                                }
                            }
                        }
                    }
                }
            }
            else if (actType.equals("funcallaction")) {
                String varName = innerAct.attributeValue("var");
                int i;
                for (i = 0; i < varList.size(); i++) {
                    if (varList.get(i).getName().equals(varName)
                        &&varList.get(i).getSourceType() == RuleVariable.EXTERNAL) {
                        break;
                    }
                }
                if (i >= varList.size()) {
                    FunCallAction funAction = new FunCallAction(actName, null, null, null, null);
                    funAction.setEngine(this);
                    result.put(actName, funAction);
                }
                else {
                    String clsName = innerAct.attributeValue("classname");
                    if (clsName.equals("")) {
                        clsName = null;

                    }
                    String methodSig = innerAct.attributeValue("methodsig");
                    if (methodSig.equals("")) {
                        methodSig = null;
                    }
                    String args = innerAct.attributeValue("args");

                    FunCallAction funAction = new FunCallAction(actName, clsName, methodSig,(RuleVariable) varList.get(i),args);
                    funAction.setEngine(this);
                    result.put(actName,funAction);
                    if (args != null) {
                        StringTokenizer token = new StringTokenizer(args, ", ");
                        while (token.hasMoreTokens()) {
                            String expression = token.nextToken();

                            jep.initSymTab();
                            jep.parseExpression(expression);
                            if (jep.hasError()) {
                                continue;
                            }
                            SymbolTable st = jep.getSymbolTable();
                            for (Iterator<?> it3 = st.keySet().iterator();
                                 it3.hasNext(); ) {
                                String var = (String) it3.next();
                                if (var.charAt(0) == '$') {
                                    var = var.substring(1);
                                    boolean found = false;
                                    for (int j = 0; j < varList.size(); j++) {
                                        if ( ( (RuleVariable) varList.get(j)).
                                            getName().equals(var)) {
                                            found = true;
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        parseVariable(m_baseRoot, var, varList);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        m_variables = (RuleVariable[]) varList.toArray(new RuleVariable[0]);
        return result;
    }

}
