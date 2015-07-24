package com.intellicredit.platform.service.dataflow.schema.descriptor;

import java.util.Vector;

public class SchemaDescriptor {
    private String m_Name;
    private Vector<FieldDescriptor> m_FieldDescriptorList;

    public SchemaDescriptor() {
    }

    public void setName(String forName) {
        m_Name = forName;
    }

    public void addFieldDescriptor(FieldDescriptor forFieldDescriptor) {
        if (m_FieldDescriptorList == null) {
            m_FieldDescriptorList = new Vector<FieldDescriptor>();
        }
        m_FieldDescriptorList.add(forFieldDescriptor);
    }

    public String getName() {
        return m_Name;
    }

    public String[] getColumnNames() {
        if (m_FieldDescriptorList == null) {
            return null;
        }
        int length = m_FieldDescriptorList.size();
        String[] ColumnNames = new String[length];
        for (int i = 0; i < length ; i++) {
            FieldDescriptor fd = (FieldDescriptor)m_FieldDescriptorList.get(i);
            ColumnNames[i] = fd.getName();
        }
        return ColumnNames;
    }

    public int[] getColumnLengths() {
        if (m_FieldDescriptorList == null) {
            return null;
        }
        int length = m_FieldDescriptorList.size();
        int[] ColumnLengths = new int[length];
        for (int i = 0; i < length ; i++) {
            FieldDescriptor fd = (FieldDescriptor)m_FieldDescriptorList.get(i);
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
        for (int i = 0; i < length ; i++) {
            FieldDescriptor fd = (FieldDescriptor)m_FieldDescriptorList.get(i);
            ColumnTypes[i] = fd.getType();
        }
        return ColumnTypes;
    }

}
