package com.intellicredit.platform.service.dataflow.schema.descriptor;

import java.util.Vector;

public class DatagramDescriptor {
    private Vector<SchemaDescriptor> m_SchemaDescriptorList;

    public DatagramDescriptor() {

    }

    /**
     * @param forSegmentDescriptor
     */
    public void addSchemaDescriptor(SchemaDescriptor forSchemaDescriptor) {
        if (m_SchemaDescriptorList == null) {
            m_SchemaDescriptorList = new Vector<SchemaDescriptor>();
        }
        m_SchemaDescriptorList.add(forSchemaDescriptor);
    }
    /**
    * @param forSegmentName
    * @return creditunion.datatransfer.schema.descriptor.SegmentDescriptor
    */
   public SchemaDescriptor getSchemaDescriptorbySchemaName(String forSchemaName) {
       int length = 0;
       SchemaDescriptor sd = null;
       if (m_SchemaDescriptorList != null) {
           length = m_SchemaDescriptorList.size();
           for (int i = 0;i < length;i++) {
               sd = (SchemaDescriptor)m_SchemaDescriptorList.get(i);
               if (forSchemaName.equals(sd.getName())) {
                   return sd;
               }
           }
       }
       return null;
    }
}
