package cn.creditloans.tools.validator.dataflow.schema.descriptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class NestSchemaDescriptor {
    private String m_Name;
    private Vector<FieldDescriptor> m_FieldDescriptorList;
    private Vector<SubSchemaDescriptor> m_subSchemaList;
    private String m_type;
    //private String m_format;
    private String m_delimiter;
    private Map<String,String> m_propertyMap = new HashMap<String,String>();//add by small 2004.4.15
    //private boolean

    public NestSchemaDescriptor() {
    }
    /**
     * @param forName
     */
    public String getName() {
        return m_Name;
    }

    public void setName(String forName) {
        m_Name = forName;
    }
    //to add the fun to judge the type of schame
    public String getType() {
        return m_type;
    }

    public void setType(String type) {
        m_type = type;
    }

    /*
    public String getFormat() {
        return m_format;
    }

    public void setFormat(String format) {
        m_format = format;
    }
    */

    public String getDelimiter() {
        return m_delimiter;
    }

    public void setDelimiter(String delimiter) {
        m_delimiter = delimiter;
    }

    /**
     * @param forFieldDescriptor
     */
    public void addFieldDescriptor(FieldDescriptor forFieldDescriptor) {
        if (m_FieldDescriptorList == null) {
            m_FieldDescriptorList = new Vector<FieldDescriptor>();
        }
        m_FieldDescriptorList.add(forFieldDescriptor);
    }

    public void addsubSchemaDescriptor(SubSchemaDescriptor forSubSchemaDescriptor) {
        if (m_subSchemaList == null) {
            m_subSchemaList = new Vector<SubSchemaDescriptor>();
        }
        m_subSchemaList.add(forSubSchemaDescriptor);
    }

    public String[] getSubSchemaNames() {
        if (m_subSchemaList == null) {
            return null;
        }
        int length = m_subSchemaList.size();
        String[] subSchemaNames = new String[length];
        for (int i = 0; i < length; i++) {
            SubSchemaDescriptor subd =
            (SubSchemaDescriptor) m_subSchemaList.get(i);
            subSchemaNames[i] = subd.getName();
        }
        return subSchemaNames;
    }

    public String[] getColumnNames() {
        if (m_FieldDescriptorList == null) {
            return null;
        }
        int length = m_FieldDescriptorList.size();
        String[] ColumnNames = new String[length];
        for (int i = 0; i < length; i++) {
            FieldDescriptor fd = (FieldDescriptor) m_FieldDescriptorList.get(i);
            ColumnNames[i] = fd.getName();
        }
        return ColumnNames;
    }

    public String[] getColumnDesces() {
        if (m_FieldDescriptorList == null) {
            return null;
        }
        int length = m_FieldDescriptorList.size();
        String[] columnDesces = new String[length];
        for (int i = 0; i < length; i++) {
            FieldDescriptor fd = (FieldDescriptor) m_FieldDescriptorList.get(i);
            columnDesces[i] = fd.getDesc();
        }
        return columnDesces;
    }

    public int[] getColumnLengths() {
        if (m_FieldDescriptorList == null) {
            return null;
        }
        int length = m_FieldDescriptorList.size();
        int[] ColumnLengths = new int[length];
        for (int i = 0; i < length; i++) {
            FieldDescriptor fd = (FieldDescriptor) m_FieldDescriptorList.get(i);
            ColumnLengths[i] = fd.getLength();
        }
        return ColumnLengths;
    }

    public int[] getColumnTypes() {
        if (m_FieldDescriptorList == null) {
            return null;
        }
        int length = m_FieldDescriptorList.size();
        int[] ColumnTypes = new int[length];
        for (int i = 0; i < length; i++) {
            FieldDescriptor fd = (FieldDescriptor) m_FieldDescriptorList.get(i);
            ColumnTypes[i] = fd.getType();
        }
        return ColumnTypes;
    }
    public String toString() {
        String ret = null;
        ret = "m_Name = " + m_Name + "\n";
        ret += "m_type = " + m_type + "\n";
        //ret += "m_format = " + m_format + "\n";
        ret += "m_delimiter = " + m_delimiter + "\n";
        ret += "subSchemaNames = " + this.getName()+"\n";
        return ret;
    }

    //add by small 2004.4.15
    public void setProperty(String key,String value){
        m_propertyMap.put(key,value);
    }

    public Map<String,String> getProperties(){
        return m_propertyMap;
    }

    public String getProperty(String key){
        if(m_propertyMap.containsKey(key))
            return String.valueOf(m_propertyMap.get(key));
        else
            return null;
    }

}
