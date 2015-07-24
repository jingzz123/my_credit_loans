package cn.creditloans.tools.validator.dataflow.schema;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

import cn.creditloans.tools.context.AppContextUtils;
import cn.creditloans.tools.validator.dataflow.schema.descriptor.FieldDescriptor;
import cn.creditloans.tools.validator.dataflow.schema.descriptor.NestDatagramDescriptor;
import cn.creditloans.tools.validator.dataflow.schema.descriptor.NestSchemaDescriptor;
import cn.creditloans.tools.validator.dataflow.schema.descriptor.SubSchemaDescriptor;
import cn.creditloans.tools.validator.transaction.NestSchema;
import cn.creditloans.tools.validator.transaction.Schema;
import cn.creditloans.tools.validator.util.ValidatorConstants;

public class SchemaFactory {
	
    private static SchemaFactory schemaFactory = null;
    
    private NestDatagramDescriptor m_nestDatagramDescriptor;
    
    private static final Log logger = LogFactory.getLog(SchemaFactory.class);
    
    public SchemaFactory(String forConfigFile) {
        init(null,forConfigFile);
    }

    public SchemaFactory(ServletContext context, String forConfigFile) {
        init(context, forConfigFile);
    }

    /**
     * 
     * @param forConfigFile：Schema文件名
     * @param charset：文件的编码
     */
    public SchemaFactory(String forConfigFile, String charset) {
        init(null,forConfigFile);
    }

    public SchemaFactory(ServletContext context, String forConfigFile, String charset) {
        init(context,forConfigFile);
    }

    private void init(ServletContext context, String forConfigFile) {
    	init(context, forConfigFile,ValidatorConstants.ENCODING);
    }

    private void init(ServletContext context, String forConfigFile,String charset) {
        SAXReader saxBuilder = new SAXReader();
        Document docmunet = null;
        try {
        	Resource r = AppContextUtils.getResource(context, forConfigFile);
	        InputStreamReader reader = new InputStreamReader(r.getInputStream(),ValidatorConstants.ENCODING);
	        docmunet = saxBuilder.read(reader);
        } catch (Exception jdome) {
            logger.error(jdome.getMessage(), jdome);
            return;
        }

        Element root = docmunet.getRootElement();
        m_nestDatagramDescriptor = new NestDatagramDescriptor();
        List<?> segmentlist = root.elements("schema");
        Iterator<?> i = segmentlist.iterator();
        String strValue;
        while (i.hasNext()) {
            Element segment = (Element) i.next();
            NestSchemaDescriptor nestsd = new NestSchemaDescriptor();

            nestsd.setName(segment.attributeValue("name"));
            nestsd.setType(segment.attributeValue("type"));

            /*
            strFormat=segment.attributeValue("format");
            nestsd.setFormat(strFormat);

            Attribute att = segment.getAttribute("delimiter");
            String delimiter = att==null?null:att.getValue();
            nestsd.setDelimiter(delimiter);
             */

            //handle description, add by small 2004.4.14
            Element desElement = segment.element("property");
            if(desElement != null){
                Iterator<?> iteChildren = desElement.elements().iterator();
                while(iteChildren.hasNext()){
                    Element childEle = (Element)iteChildren.next();
                    nestsd.setProperty(childEle.getName(), childEle.getText());
                }
            }

            //handle the field list
            List<?> fieldlist = segment.elements("field");
            Iterator<?> j = fieldlist.iterator();
            while (j.hasNext()) {
                Element field = (Element) j.next();
                FieldDescriptor fd = new FieldDescriptor();
                fd.setName(field.attributeValue("name"));
                strValue = field.attributeValue("type");
                if (("Text").toUpperCase().equalsIgnoreCase(strValue.toUpperCase().trim())) {
                    fd.setType(Schema.TEXT);
                } else if (("String").toUpperCase().equalsIgnoreCase(strValue.toUpperCase().trim())) {
                    fd.setType(Schema.STRING);
                } else if (("Date").toUpperCase().equalsIgnoreCase(strValue.toUpperCase().trim())) {
                    fd.setType(Schema.DATE);
                } else if (("Int").toUpperCase().equalsIgnoreCase(strValue.toUpperCase().trim())) {
                    fd.setType(Schema.INT);
                } else if (("Long").toUpperCase().equalsIgnoreCase(strValue.toUpperCase().trim())) {
                    fd.setType(Schema.LONG);
                } else if (("Float").toUpperCase().equalsIgnoreCase(strValue.toUpperCase().trim())) {
                    fd.setType(Schema.FLOAT);
                } else if (("Double").toUpperCase().equalsIgnoreCase(strValue.toUpperCase().trim())) {
                    fd.setType(Schema.DOUBLE);
                } else if (("Number").toUpperCase().equalsIgnoreCase(strValue.toUpperCase().trim())) {
                    fd.setType(Schema.NUMBER);
                }
                strValue = field.attributeValue("length");
                if(strValue != null)
                    fd.setLength(Integer.parseInt(strValue));
                String desc = field.attributeValue("desc");
                if(desc == null){
                    desc = "";
                }
                fd.setDesc(desc);
                nestsd.addFieldDescriptor(fd);
            }

            //handle the subschema list
            List<?> subSchemalist = segment.elements("schema");
            Iterator<?> k = subSchemalist.iterator();
            while (k.hasNext()) {
                Element subschemaElement = (Element) k.next();
                SubSchemaDescriptor subSchemad = new SubSchemaDescriptor();
                subSchemad.setName(subschemaElement.attributeValue("name"));
                nestsd.addsubSchemaDescriptor(subSchemad);
            }
            m_nestDatagramDescriptor.addNestSchemaDescriptor(nestsd);
        }
    }

    public static synchronized SchemaFactory getInstance(String initFile) {
        if (schemaFactory == null) {
            schemaFactory = new SchemaFactory(initFile);
        }
        return schemaFactory;
    }

    public static synchronized SchemaFactory getInstance(String initFile, ServletContext context) {
        if (schemaFactory == null) {
            schemaFactory = new SchemaFactory(context, initFile);
        }
        return schemaFactory;
    }

    public Schema generateSchema(String schemaName) {
        NestSchemaDescriptor nestsd =m_nestDatagramDescriptor.getNestSchemaDescriptorbySchemaName(schemaName);
        if(nestsd == null){
            return null;
        }
        //check whether the definition has error
        if(!checkSchema(nestsd))
            return null;

        //handle the schema type,ignoreCase
        //==1. create Parent Schema
        String nestStr = nestsd.getProperty(ValidatorConstants.NESTED);
        if(nestStr == null || !nestStr.equalsIgnoreCase(ValidatorConstants.NESTED_YES)){
            return createSingleSchema(nestsd);
        }else if(nestStr.equalsIgnoreCase(ValidatorConstants.NESTED_YES)){
            String nestType = nestsd.getType();
            NestSchema nestSchema = null;
            if(nestType.equalsIgnoreCase("Fixed_Length")){
                nestSchema = new NestSchema(nestsd.getColumnNames(),nestsd.getColumnTypes(),nestsd.getColumnLengths());//fixed length schema
                nestSchema.setColumnDesces(nestsd.getColumnDesces());
            }else if(nestType.equalsIgnoreCase("Various_Length")){
                nestSchema = new NestSchema(nestsd.getColumnNames(),nestsd.getColumnTypes(),nestsd.getProperty(ValidatorConstants.DELIMITER));
                nestSchema.setColumnDesces(nestsd.getColumnDesces());
            }
            nestSchema.setName(nestsd.getName());
            Map<String,String> propertyMap = nestsd.getProperties();
            if(propertyMap.size()>0){
                Iterator<?> ite = propertyMap.keySet().iterator();
                while(ite.hasNext()){
                    String key = String.valueOf(ite.next());
                    nestSchema.setProperty(key, String.valueOf(propertyMap.get(key)));
                }
            }
            //==2. create all subSchemas
            String[] subSchemaNames=nestsd.getSubSchemaNames();
            List<Schema> subSchemaList=new ArrayList<Schema>();
            for (int i = 0; i < subSchemaNames.length; i++) {
                NestSchemaDescriptor schemasd = (NestSchemaDescriptor)m_nestDatagramDescriptor.getNestSchemaDescriptorbySchemaName(subSchemaNames[i]);
                subSchemaList.add(createSingleSchema(schemasd));
            }
            nestSchema.setSubSchemas((Schema[])subSchemaList.toArray(new Schema[0]));
            return (NestSchema) nestSchema;
        }
        return null;
    }

    private boolean checkSchema(NestSchemaDescriptor nestSDec){
        String isNested = nestSDec.getProperty(ValidatorConstants.NESTED);
        String type = nestSDec.getType();
        String name = nestSDec.getName();
        List<String> delimiterList = new ArrayList<String>();
        String errorInfo_1 = " Schema must have delimiter property";
        String errorInfo_2 = " Schema must have field_delimiter property";
        String errorInfo_3 = " Schema: all delimiters and field_delimiter must be the same";
        String errorInfo_4 = " Schema must have sub schemas";
        if(isNested==null ||!isNested.equalsIgnoreCase(ValidatorConstants.NESTED_YES)){
            if(type.equalsIgnoreCase("Various_Length")){
                String baseDelimiter = nestSDec.getProperty(ValidatorConstants.DELIMITER);
                if(baseDelimiter==null){
                    System.out.println(name+errorInfo_1);
                    return false;
                }
            }
        }else if(isNested.equalsIgnoreCase(ValidatorConstants.NESTED_YES)){
            //It must have sub schema-s
            if(nestSDec.getSubSchemaNames()==null){
                System.out.println(name+errorInfo_4);
                return false;
            }
            String fieldDelimiter = null;
            boolean hasFDelimiter = false;
            if(type.equalsIgnoreCase("Various_Length")){
                hasFDelimiter = true;
                String baseDelimiter = nestSDec.getProperty(ValidatorConstants.DELIMITER);
                if(baseDelimiter==null){
                    System.out.println(name+errorInfo_1);
                    return false;
                }
                fieldDelimiter = nestSDec.getProperty(ValidatorConstants.FIELD_DELIMITER);
                if(fieldDelimiter==null){
                    System.out.println(name+errorInfo_2);
                    return false;
                }
                if(!delimiterList.contains(baseDelimiter))
                    delimiterList.add(baseDelimiter);
                if(!delimiterList.contains(fieldDelimiter))
                    delimiterList.add(fieldDelimiter);
            }
            //check all the delimiters of Schema-s and field_delimiter
            String[] subNames = nestSDec.getSubSchemaNames();
            for(int i=0; i<subNames.length; i++){
                NestSchemaDescriptor subSchemaDes = m_nestDatagramDescriptor.getNestSchemaDescriptorbySchemaName(subNames[i]);
                String subType = subSchemaDes.getType();
                if(subType.equalsIgnoreCase("Various_Length")){
                    hasFDelimiter = true;
                    String subDelimiter = subSchemaDes.getProperty(ValidatorConstants.DELIMITER);
                    String subName = subSchemaDes.getName();
                    if(subDelimiter==null){
                        System.out.println(subName+errorInfo_1);
                        return false;
                    }
                    if(!delimiterList.contains(subDelimiter))
                        delimiterList.add(subDelimiter);
                }
            }
            if(hasFDelimiter&&fieldDelimiter==null){
                System.out.println(name+errorInfo_2);
                return false;
            }
            if(delimiterList.size()>1){
                System.out.println(name+errorInfo_3);
                return false;
            }
        }
        return true;
    }

    private Schema createSingleSchema(NestSchemaDescriptor nestSDec){
        Schema schema = null;
        if(nestSDec.getType().equalsIgnoreCase("Fixed_length")){//fixed length schema
            schema = new Schema(nestSDec.getColumnNames(),nestSDec.getColumnTypes(),nestSDec.getColumnLengths());
            schema.setColumnDesces(nestSDec.getColumnDesces());
        }else if(nestSDec.getType().equalsIgnoreCase("Various_length")){//various length schema
            schema = new Schema(nestSDec.getColumnNames(),nestSDec.getColumnTypes(),nestSDec.getProperty(ValidatorConstants.DELIMITER));
            schema.setColumnDesces(nestSDec.getColumnDesces());
        }
        if(schema!=null){
            schema.setName(nestSDec.getName());
            Map<String,String> propertyMap = nestSDec.getProperties();
            if(propertyMap.size()>0){
                Iterator<String> ite = propertyMap.keySet().iterator();
                while(ite.hasNext()){
                    String key = ite.next();
                    schema.setProperty(key, propertyMap.get(key));
                }
            }
        }
        return schema;
    }
}
