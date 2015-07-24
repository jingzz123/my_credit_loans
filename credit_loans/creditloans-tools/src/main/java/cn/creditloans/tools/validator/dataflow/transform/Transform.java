package cn.creditloans.tools.validator.dataflow.transform;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import cn.creditloans.tools.validator.transaction.Schema;
import cn.creditloans.tools.validator.transaction.Transaction;

/** The Transform is the building brick of the DataFlow Plan. Transforms are built-in program modules for performing the following types of operations:
*
* 	controlling the flow of data in a Plan
* 	analyzing data
* 	broadcasting data to other users
* 	populating a data mart and transforming data
* 	customizing data manipulation at the record level
*
* A transform can have one to many inputs and one to many outputs.
*
* The main task of Transforms is to process records. Transforms translate input into
* output. Record processing methods determine the primary differences among Transform
* base classes. If a Transform processes one record without referring to other records,
* minimal resources are devoted to record storage. One record is processed at a time
* and delivered to the next Transform. Some Transforms need all the records before
* they can continue processing. For example, a sorting Transform accumulates all
* the input records before it sorts and processes the records. In this case, the
* transform must save the record in a buffer. The buffer can either in memory or
* in an external file.
*/
public abstract class Transform {
   
   public static final int ABSTRACTTRANSFORM = 0;
   public static final int SOURCE = 1;
   public static final int TARGET = 2;
   
   public static final int ONE_2_ONE_SAME_FORMAT = 3;
   public static final int ONE_2_ONE_NEW_FORMAT = 4;
   public static final int ONE_2_ONE_SINGLE_MEMORY_BUFFER = 5;
   public static final int ONE_2_ONE_DOUBLE_MEMORY_BUFFER = 6;
   public static final int ONE_2_ONE_SINGLE_FILE_BUFFER = 7;
   public static final int ONE_2_ONE_DOUBLE_FILE_BUFFER = 8;
   
   public static final int MANY_2_ONE_NEW_FORMAT = 9;
   public static final int MANY_2_ONE_SINGLE_MEMORY_BUFFER = 10;
   public static final int MANY_2_ONE_DOUBLE_MEMORY_BUFFER = 11;
   public static final int MANY_2_ONE_SINGLE_FILE_BUFFER = 12;
   public static final int MANY_2_ONE_DOUBLE_FILE_BUFFER = 13;
   
   public static final int ONE_2_MANY_DOUBLE_MEMORY_BUFFER = 14;
   public static final int ONE_2_MANY_DOUBLE_FILE_BUFFER = 15;
   
   public static final int MANY_2_MANY_DOUBLE_MEMORY_BUFFER = 16;
   public static final int MANY_2_MANY_DOUBLE_FILE_BUFFER = 17;
   public static final int ONE_2_MANY_SINGLE_MEMORY_BUFFER = 18;
   public static final int ONE_2_MANY = 19;
   public static final int ONE_2_MANY_1 = 20;
   
   public static final int MANY_2_ONE_STEP_PROCESS = 21;
   public static final int MANY_2_ONE_STEP_TO_MEMORY_BUFFER = 22;
   public static final int MANY_2_ONE_STEP_TO_FILE_BUFFER = 23;
   public static final int MANY_2_ONE_STEP_TO_FILE_BUFFER_SIGNAL = 24;
   
   public static final int MANY_2_MANY_STEP_TO_MEMORY_BUFFER = 25;
   public static final int MANY_2_MANY_STEP_TO_FILE_BUFFER = 26;
   public static final int MANY_2_MANY_STEP_TO_FILE_BUFFER_SIGNAL = 27;

   public static final int ASYNC_MANY_2_ONE_STEP_PROCESS = 28;
   public static final int MANY_2_MANY_GENERAL = 29;
    
   public static final int REMOTE = 50;
   public static final int RECV_SINGAL_AND_SPLIT = 51;
   public static final int ONE_FILTER_OTHER_DATA = 52;
   
   public static final int OTHER = 100;
   
   protected String m_name = "";
   protected int m_type = ABSTRACTTRANSFORM;
   protected boolean m_inited = true;
   protected DFInstance m_engine = null;
   protected Schema[] m_inSchemas = null;
   protected Schema[] m_outSchemas = null;
   protected Transform[] m_transforms = null; //input transforms
   protected int[] m_channels = null; //output channels of input Transforms
   protected Map<String,Object>  m_parameters = new LinkedHashMap<String,Object>();
   protected Logger m_logger; //general log
   protected Logger m_badRecordLogger; //bad record log
   
   /** Create a new Transform. When a object of subclass is called no gurrenty the code
    * in this construct will be executed.
    */
   public Transform() {
   }
   
   
   /** set name of the transform
    * @param name name of the transform
    */
   public void setName(String name) {
       m_name = name;
   }
   
   /** get name of the Transform
    * @return name of the Transform
    */
   public String getName() {
       return m_name;
   }
   
   /** set DataFlow engine.
    * @param engine DataFlow engine
    */
   public void setDFInstance(DFInstance intance) {
       m_engine = intance;
       m_logger = m_engine.getLogger();
       m_badRecordLogger = m_engine.getBadRecordLogger();
   }
   
   /** get DataFlow Engine
    * @return DataFlow Engine
    */
   public DFInstance getDFInstance() {
       return m_engine;
   }
   
   /** set type of the Transform
    * @param type the type of the Transfor
    */
   public void setType(int type) {
       m_type = type;
   }
   /** get type of the Transform.
    * @return type of the transform
    */
   public int getType() {
       return m_type;
   }
   
   
   /** set Schemas for the input ports. A Transform can have one to many input ports,
    * so An array is used to make the method generic.
    * @param schemas array of input Schemas
    */
   public void setInSchemas(Schema[] schemas) {
       m_inSchemas = schemas;
   }
   
   /** get the Schemas of the input ports.
    * @return the Schemas of input ports
    */
   public Schema[] getInSchemas() {
       return m_inSchemas;
   }
   
   /** set the Schemas for output ports. A Transform can have one to more output ports,
    * so an array of Schema is used here.
    * @param schemas Schema for output ports
    */
   public void setOutSchemas(Schema[] schemas) {
       m_outSchemas = schemas;
   }
   
   /** get Schemas of output ports
    * @return Schemas for output ports
    */
   public Schema[] getOutSchemas() {
       return m_outSchemas;
   }
   
   /** set the Transforms that connect to this Transform. These transforms are called
    * input Transforms of this Transforms. Each Transform in the array
    * is connected to one input port of the Trasform in the order of the index number
    * in the array. Because each transform can have many output port. So the channels
    * specify which output port is connect to this Transforms input port.
    * @param transforms the array of transforms that connect to the input of this transform.
    * @param channels array of output port number for the transforms that connect to the input of this
    * Transform
    */
   public void setInTransforms(Transform[] transforms, int[] channels) {
       m_transforms = transforms;
       m_channels = channels;
   }
   
   /** get the Transforms that connect to the input ports of the Transform.
    * @return input Transforms
    */
   public Transform[] getInTransforms() {
       return m_transforms;
   }
   
   /** add an input Transform
    * @param transform input Transform
    * @param channel output port of the input Transform
    */
   public void addTransform(Transform transform,int channel,int index) {
       if(m_transforms==null && m_channels==null) {
           m_transforms = new Transform[index+1];
           m_transforms[index] = transform;
           m_channels = new int[index+1];
           m_channels[index] = channel;
       } else if(index<m_transforms.length){
           m_transforms[index] = transform;
           m_channels[index] = channel;
       } else {
           Transform[] f = new Transform[index+1];
           System.arraycopy(m_transforms,0,f,0,m_transforms.length);
           f[index]=transform;
           m_transforms=f;
           
           int[] c = new int[index+1];
           System.arraycopy(m_channels,0,c,0,m_channels.length);
           c[index]=channel;
           m_channels=c;
       }
   }
   
   /** get output ports of the input Transforms
    * @return array of output ports of the input Transform
    */
   public int[] getChannels() {
       return m_channels;
   }
   
   /** set a parameter of the Transform
    * @param key the key of the parameter
    * @param value value of the parameter
    */
   public void setParameter(String key, Object value) {
       m_parameters.put(key,  value);
   }
   
   /** get a parameter of the Transform
    * @param key name of the parameter
    */
   public Object  getParameter(String key) {
       return m_parameters.get(key);
   }
   
   /** get the keys of the all the parameter
    * @return names of all the parameters of the transform
    */
   public String[] getParameterNames() {
       return (String[])m_parameters.keySet().toArray(new String[0]);
   }
   
   //subclass must call super.reset() at last
   //current only do init() again.
   //deprecated
   /** reset the Transform */
/*
   public void reset() {
 
       if(init()!=0 && m_logger!=null)
           m_logger.severe(m_name + " reset failure!");
           
       if(m_transforms==null) 
           return;
       for(int i=0;i<m_transforms.length;i++) {
           m_transforms[i].reset();
       }
   }
**/
   /** log the bad transaction
    * @param errorCode error code
    * @param tran bad transaction
    */
   public void log(String errorCode, Transaction tran) {
       m_engine.getBadRecordLogger().warning(errorCode + "--" + tran.getData());
   }
   
   /** .initiate the transform and return an error flag
    * error flag. 0 no error.
    * @return error number with 0 means no error.
    *
    */
   public int init() {
//       int failNum = 0;
//       if(m_transforms==null) 
//           return failNum;
//       for(int i=0;i<m_transforms.length;i++) {
//           failNum = failNum + m_transforms[i].init();
//       }
//       return failNum;
       return 0;
   }
   
   /*
    *release resource only
    */
   public void cleanup() {
//       if(m_transforms==null) 
//           return;
//       for(int i=0;i<m_transforms.length;i++) {
//           m_transforms[i].cleanup();
//       }
   }

   /** validate the Transform to make sure its internal status is ready and properly connectted with other Transform in a DataFlow Plan
    * correctly.
    */
   public abstract boolean validate();
   /** This is the interface for transforms to pass data.
    * @return the next record from the transform. return null when get to the end of channel.
    * if some incoming records are invalide and the transform can not create a
    * associated output record, the bad incoming record and cause of the error is
    * logged.
    * @param outChannel the index of the output port. One transform can have more than one output ports
    */
   public abstract Transaction next(int outChannel);
   //  public abstract Transaction[] nextBlock(int outChannel) {
   //     return null;
   // }
   
   /*
    *
    *@param outChannel the index of the output port. One transform can have more than one output ports
    *@return a block of trasanctions from outChannel
    */
   public Transaction[] nextA(int outChannel) {
       return null;
   }
}
