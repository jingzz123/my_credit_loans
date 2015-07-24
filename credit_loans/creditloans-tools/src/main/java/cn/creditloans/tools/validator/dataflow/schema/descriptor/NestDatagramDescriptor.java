package cn.creditloans.tools.validator.dataflow.schema.descriptor;

import java.util.Vector;

public class NestDatagramDescriptor {
	private Vector<NestSchemaDescriptor> m_NestSchemaDescriptorList;

	public NestDatagramDescriptor() {

	}

	public void addNestSchemaDescriptor(NestSchemaDescriptor fornestSchemaDescriptor) {
		if (m_NestSchemaDescriptorList == null) {
			m_NestSchemaDescriptorList = new Vector<NestSchemaDescriptor>();
		}
		m_NestSchemaDescriptorList.add(fornestSchemaDescriptor);
	}

   public NestSchemaDescriptor getNestSchemaDescriptorbySchemaName(String forNestSchemaName) {
	   int length = 0;
	   NestSchemaDescriptor nestsd = null;
	   if (m_NestSchemaDescriptorList != null) {
		   length = m_NestSchemaDescriptorList.size();
		   for (int i = 0;i < length;i++) {
			   nestsd = (NestSchemaDescriptor)m_NestSchemaDescriptorList.get(i);
			   if (forNestSchemaName.equals(nestsd.getName())) {
				   return nestsd;
			   }
		   }
	   }
	   return null;
	}

}
