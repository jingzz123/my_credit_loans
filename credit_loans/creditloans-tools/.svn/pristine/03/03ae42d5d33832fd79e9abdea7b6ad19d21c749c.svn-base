package cn.creditloans.tools.validator.transaction;

import java.util.Date;
import java.util.Vector;

import cn.creditloans.tools.validator.util.Tokenizer;
import cn.creditloans.tools.validator.util.ValidatorConstants;

public class NestTransaction extends Transaction {

	private static final long serialVersionUID = -3409122112289286238L;
	
	private Schema[] m_subschemas;
    private Vector<Transaction> m_subtransactions;
    private Vector<Transaction>[] m_nestsubtransactions;
    private String[] m_subsegmentIds;
    private int[] m_subsegmentLengths;//It will be used when the type of the schema and its subSchemas are "Fixed_Length" and has no delimiter
    private int m_baseSegmentLength;
    //add by small 2004.4.15
    private String m_fieldDelimiter = null;//It will be used when all columns are seperated by same delimiter
    private int m_idLength;

    /** Creates a new instance of Transaction1
     * @param schema netSchema used by the Transaction
     */
    @SuppressWarnings("unchecked")
	public NestTransaction(NestSchema schema) {
        super(schema);
        m_type = NESTED;
        m_subschemas = schema.getSubSchemas();
        m_subtransactions = new Vector<Transaction>();//Transaction[m_subschemas.length];
        m_nestsubtransactions = new Vector[m_subschemas.length];
        for(int i = 0; i < m_subschemas.length; i++) {
            m_nestsubtransactions[i] = new Vector<Transaction>();
        }
        //Arrays.fill(m_subtransactions, null);
        m_subsegmentIds = new String[m_subschemas.length];
        for(int i = 0; i < m_subsegmentIds.length; i++) {
            m_subsegmentIds[i] = (m_subschemas[i].getColumnNames())[0];
        }
        //modify by small 2004.4.15
        m_idLength = Integer.parseInt(getSchema().getProperty(ValidatorConstants.ID_LENGTH));
        m_fieldDelimiter = getSchema().getProperty(ValidatorConstants.FIELD_DELIMITER);
        if(m_fieldDelimiter==null){
            m_subsegmentLengths = new int[m_subschemas.length];
            int[] lens = getSchema().getColumnPositions();
            m_baseSegmentLength = lens[lens.length - 1];
            for(int i = 0; i < m_subsegmentLengths.length; i++) {
                lens = m_subschemas[i].getColumnPositions();
                m_subsegmentLengths[i] = lens[lens.length - 1];
            }
        }
    }

    /*
     **
     * @return base transaction data
     *
     */
    public Transaction cloneBaseTransaction() {
        Transaction base = new Transaction(super.getSchema());
        int type = m_schema.getType();
        if(type==Schema.FIXED_LENGTH)
            base.setData(super.getData());
        else
            base.setData(super.getData(m_schema.getDelimeter()));
        return base;
        // return super.getData();
    }

    /*
     **
     * @return base transaction data
     *
     */
    public String getBaseTransactionData() {
        int type = m_schema.getType();
        if(type==Schema.FIXED_LENGTH)
            return super.getData();
        else
            return super.getData(m_schema.getDelimeter());

    }
    /**
     * @param index schema index
     * @return all subtransaction of with same schema
     */
    public Transaction[] getSubTransaction(int index) {
        // return m_subtransactions[index];
        if(m_nestsubtransactions[index].size() == 0)
            return null;
        else
            return (Transaction[])m_nestsubtransactions[index].toArray(new Transaction[0]);
    }

    @SuppressWarnings("unchecked")
	public void reset(){
        super.reset();
        m_subtransactions = new Vector<Transaction>();
        m_nestsubtransactions = new Vector[m_subschemas.length];
        for(int i = 0; i < m_subschemas.length; i++) {
            m_nestsubtransactions[i] = new Vector<Transaction>();
        }
    }

    /** get all subtransaction
     * @return all sub transactions
     */
    public Transaction[] getSubTransactions() {
        return (Transaction[])m_subtransactions.toArray(new Transaction[0]);
    }

    public void setSubtransaction(Transaction tran) {
        String   segmentId = tran.getString(0);
        for(int i = 0; i < m_subsegmentIds.length; i++) {
            if(segmentId.equals(m_subsegmentIds[i])) {
                m_subtransactions.add(tran);
                m_nestsubtransactions[i].add(tran);
                break;
            }
        }
    }

    //add by wu 2004.08.26
    public void setSubtransaction(Transaction tran,int index) {
        m_subtransactions.add(tran);
        m_nestsubtransactions[index].add(tran);
    }

    //add by small 2004.4.15
    public boolean setData(String data){
        if(m_fieldDelimiter==null)
            return setFixData(data);
        else
            return setData(data, m_fieldDelimiter);
    }
    /** set transaction data for fixed length transaction and data has no delimiter
     * @param data transaction data
     * @return error code, 0 means no error
     *
     */
    //TO only for fixed length transaction
    //modify by small 2004.4.15
    //public boolean setData(String data){
    private boolean setFixData(String data) {
        //super(data);
        m_subtransactions.clear();
        for(int i = 0; i < m_subschemas.length; i++) {
            m_nestsubtransactions[i].clear();
        }
        int size = data.length();
        String segment;
        //TODO should first byte the length of the record
        //int totalLen = Integer.parseInt(data.substring(0,4));
        //the main data
        int pos = m_baseSegmentLength;
        segment = data.substring(0, pos);
        super.setData(segment);

        //set the sub data
        String segmentId;
        boolean found = false;
        while(pos < size) {
            //segmentId = data.substring(pos, pos+2);
            segmentId = data.substring(pos, pos+m_idLength);
            found = false;
            for(int i = 0; i < m_subsegmentIds.length; i++) {
                if(segmentId.equals(m_subsegmentIds[i])) {
                    segment = data.substring(pos, pos + m_subsegmentLengths[i]);
                    Transaction tran = new Transaction(m_subschemas[i]);
                    tran.setData(segment);
                    m_subtransactions.add(tran);
                    m_nestsubtransactions[i].add(tran);
                    pos = pos + m_subsegmentLengths[i];
                    found = true;
                    break;
                }

            }
            if(!found) {
                break;
            }
        }
        if(!found)
            return false;
        else
            return true;
    }
    /** set transaction data that are delimited by del no matter the schema is various
     *length or fixed length.
     * @param data transaction data
     * @return error code, 0 means no error
     *
     */
    public boolean setData(String data, String del) {
        m_subtransactions.clear();
        for(int i = 0; i < m_subschemas.length; i++) {
            m_nestsubtransactions[i].clear();
        }

        // StringTokenizer tokens = new StringTokenizer(data, del, false);
        //use Tokenizer to deal with cosecutive del
        Tokenizer tokens = new Tokenizer(data, del, false);
        Schema baseSchema = super.getSchema();
        int columnNum = baseSchema.getColumnNum();
        String[] values = new String[columnNum];
        //let Transaction take care null or ""
        for(int i = 0; i < columnNum; i++) {
            values[i] = tokens.nextToken();
        }
        super.setData(values);
        String segmentId;
        boolean found = false;

        while(tokens.hasMoreTokens()) {
            segmentId = tokens.nextToken();
            found = false;
            for(int i = 0; i < m_subsegmentIds.length; i++) {
                if(segmentId.equals(m_subsegmentIds[i])) {
                    columnNum = m_subschemas[i].getColumnNum();
                    values = new String[columnNum];
                    values[0] = segmentId;
                    for(int j = 1; j < columnNum; j++) {
                        values[j] = tokens.nextToken();
                    }
                    Transaction tran = new Transaction(m_subschemas[i]);
                    tran.setData(values);
                    m_subtransactions.add(tran);
                    m_nestsubtransactions[i].add(tran);
                    found = true;
                    break;
                }
            }
            if(!found)
                return false;
        }
        return true;
    }
    /** get transaction data
     * @return Transaction data including subtransaction.
     */
    public String getData() {
        StringBuffer buffer = new StringBuffer("");
        buffer.append(super.getData());
        int size = m_subtransactions.size();
        for(int i = 0; i < size; i ++) {
            //if(m_subtransactions.get(index) != null)
            buffer.append(((Transaction)m_subtransactions.get(i)).getData());
        }
        return buffer.toString();
    }

    /** get transaction data with column separated by del
     * @return Transaction data including subtransaction.
     */
    public String getData(String del) {
        StringBuffer buffer = new StringBuffer("");
        buffer.append(super.getData(del));
        //buffer.append(del);
        int size = m_subtransactions.size();
        for(int i = 0; i < size; i ++) {
            buffer.append(del);
            buffer.append(((Transaction)m_subtransactions.get(i)).getData(del));
        }
        return buffer.toString();
    }

    public boolean isNull(String segmentId, int columnIndex) {
        if(segmentId == null || segmentId.length() == 0) {
            return super.isNull(columnIndex);
        }
        else {
            for(int i = 0; i < m_subschemas.length; i++) {
                if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                    // for(int j = 0; j < subtransactions.length; j++)
                    Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                    if(subtransactions.length == 0)
                        return true;
                    else
                        return subtransactions[0].isNull(columnIndex);
                }
            }
            return true;
        }
    }

    public boolean isNull(String segmentId, String columnName) {
        if(segmentId == null || segmentId.length() == 0) {
            return super.isNull(columnName);
        }
        else {
            for(int i = 0; i < m_subschemas.length; i++) {
                if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                    // for(int j = 0; j < subtransactions.length; j++)
                    Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                    if(subtransactions.length == 0)
                        return true;
                    else
                        return subtransactions[0].isNull(columnName);
                }
            }
            return true;
        }
    }

    public int[] getInt(String segmentId, int columnIndex) {
        int[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new int[1];
            result[0] = super.getInt(columnIndex);
            return result;
        }
        if(m_subschemas.length==0)
            return new int[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new int[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getInt(columnIndex);
                }
                return result;
                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public int[] getInt(String segmentId, String columnName) {
        int[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new int[1];
            result[0] = super.getInt(columnName);
            return result;
        }
        if(m_subschemas.length==0)
            return new int[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new int[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getInt(columnName);
                }
                return result;
                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public long[] getLong(String segmentId, int columnIndex) {
        long[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new long[1];
            result[0] = super.getLong(columnIndex);
            return result;
        }
        if(m_subschemas.length==0)
            return new long[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new long[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getLong(columnIndex);
                }
                return result;
                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public long[] getLong(String segmentId, String columnName) {
        long[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new long[1];
            result[0] = super.getLong(columnName);
            return result;
        }
        if(m_subschemas.length==0)
            return new long[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new long[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getLong(columnName);
                }
                return result;
                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public float[] getFloat(String segmentId, int columnIndex) {
        float[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new float[1];
            result[0] = super.getFloat(columnIndex);
            return result;
        }
        if(m_subschemas.length==0)
            return new float[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new float[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getFloat(columnIndex);
                }
                return result;

                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public float[] getFloat(String segmentId, String columnName) {
        float[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new float[1];
            result[0] = super.getFloat(columnName);
            return result;
        }
        if(m_subschemas.length==0)
            return new float[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new float[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getFloat(columnName);
                }
                return result;

                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public double[] getDouble(String segmentId, int columnIndex) {
        double[] result = null;
        if(segmentId == null || segmentId.length()==0) {
            result = new double[1];
            result[0] = super.getDouble(columnIndex);
            return result;
        }
        if(m_subschemas.length==0)
            return new double[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new double[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getDouble(columnIndex);
                }
                return result;
                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public double[] getDouble(String segmentId, String columnName) {
        double[] result = null;
        if(segmentId == null || segmentId.length()==0) {
            result = new double[1];
            result[0] = super.getDouble(columnName);
            return result;
        }
        if(m_subschemas.length==0)
            return new double[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new double[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getDouble(columnName);
                }
                return result;
                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public String[] getString(String segmentId, int columnIndex) {
        String[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new String[1];
            result[0] = super.getString(columnIndex);
            return result;
        }
        if(m_subschemas.length==0)
            return new String[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new String[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getString(columnIndex);
                }
                return result;
                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public String[] getString(String segmentId, String columnName) {
        String[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new String[1];
            result[0] = super.getString(columnName);
            return result;
        }
        if(m_subschemas.length==0)
            return new String[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new String[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getString(columnName);
                }
                return result;
                // m_subtransactions[i].getInt(columnName.substring(index+1));
            }
        }
        return null;
    }

    public Date[] getDate(String segmentId, int columnIndex) {
        Date[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new Date[1];
            result[0] = super.getDate(columnIndex);
            return result;
        }
        if(m_subschemas.length==0)
            return new Date[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new Date[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getDate(columnIndex);
                }
                return result;
            }
        }
        return null;
    }

    public Date[] getDate(String segmentId, String columnName) {
        Date[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new Date[1];
            result[0] = super.getDate(columnName);
            return result;
        }
        if(m_subschemas.length==0)
            return new Date[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new Date[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getDate(columnName);
                }
                return result;
            }
        }
        return null;
    }

    public String[] getColData(String segmentId, int columnIndex) {
        String[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new String[1];
            result[0] = super.getColData(columnIndex);
            return result;
        }
        if(m_subschemas.length==0)
            return new String[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new String[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getColData(columnIndex);
                }
                return result;
            }
        }
        return null;
    }

    public String[] getColData(String segmentId, String columnName) {
        String[] result = null;
        if(segmentId == null || segmentId.length() == 0) {
            result = new String[1];
            result[0] = super.getColData(columnName);
            return result;
        }
        if(m_subschemas.length==0)
            return new String[0];
        for(int i = 0; i < m_subschemas.length; i++) {
            if(m_subschemas[i].m_columnNames[0].equals(segmentId)) {
                Transaction[] subtransactions = (Transaction[]) m_nestsubtransactions[i].toArray(new Transaction[0]);
                result = new String[subtransactions.length];
                for(int j = 0; j < subtransactions.length; j++) {
                    result[j] = subtransactions[j].getColData(columnName);
                }
                return result;
            }
        }
        return null;
    }

    //add by wu for speed
    @SuppressWarnings("unchecked")
	public Object clone(){
        NestTransaction obj = null;
        try{
            obj = (NestTransaction)super.clone();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        obj.m_subtransactions = new Vector<Transaction>();//Transaction[m_subschemas.length];
        obj.m_nestsubtransactions = new Vector[m_subschemas.length];
        for(int i = 0; i < m_subschemas.length; i++) {
            obj.m_nestsubtransactions[i] = new Vector<Transaction>();
        }
        return obj;
    }

}
