package cn.creditloans.tools.validator.dataflow.transform;

import java.util.Iterator;
import java.util.Map;
import java.util.Observer;
import java.util.logging.Logger;

import cn.creditloans.tools.validator.dataflow.transform.template.TSource;
import cn.creditloans.tools.validator.dataflow.transform.template.TTarget;
import cn.creditloans.tools.validator.transaction.Transaction;

public class DFInstance {

    private int m_state=DFConstant.UNCHECKED;
    private String m_id;
    private String m_def;
    private TSource[] m_sources;
    private TTarget[] m_targets;

    private Map<String,Transform> m_ObjMap;
    private String m_logName;//得到info的名称
    private Logger m_logger;
    private String m_badRecordLogName;//得到badRecord的名称
    private Logger m_badRecordLogger;

    private String[] m_engines;
    private String[] m_sourcePatterns;
    private String[] m_targetPatterns;

    public DFInstance() {
    }

    public DFInstance(String id, String def) {
        m_id = id;
        m_def = def;
    }

    public String getLogName(){
        return m_logName;
    }

    public String getBadLogName(){
        return m_badRecordLogName;
    }

    public void setLogger(Logger log) {
        m_logger = log;
    }

    public Logger getLogger() {
        return m_logger;
    }

    public void setBadRecordLogger(Logger log) {
        m_badRecordLogger = log;
    }

    public Logger getBadRecordLogger() {
        return m_badRecordLogger;
    }

    public void start() {
        start(false);
    }

    public void start(boolean singleStep) {
        start(singleStep, true);
    }

    /*
     *every time a record is pulled from a target.
           in the case that all taget come from one xx2many transform
           then some taget may have no record for one cycle. we still think
           there may be some new record next time. but if all the target have no
           record for one cycle, we think it is the end of stream.

     *there is a assumption that all target in a dataflow case is
     *associated. even some target store aggregated info and has less output than
     *others. but if there still one target has something to pull, then other
           targets may also have something to pull.
     */
    public void start(boolean singleStep, boolean singleThread) {
        if(m_state<=0)
            return;

        if(singleThread) {
            m_state = DFConstant.START;
            if(initTransforms()>0) {
                m_logger.severe("Init failed!");
                cleanupTransforms();
                return;
            }
        }
        if(!singleStep) {
            for(int i=0;i<m_targets.length;i++)
                m_targets[i].pull();
        }
        else {
            int totalCompleted = 0;
            while(totalCompleted < m_targets.length) {
                totalCompleted = 0;
                for(int i=0;i<m_targets.length;i++) {
                    if(0 == m_targets[i].pull(1)) {
                        totalCompleted++;
                    }
                }
            }
        }

//        for(int i=0;i<m_targets.length;i++) {
//            m_targets[i].cleanup();
//        }

        if(singleThread) {
            cleanupTransforms();
            m_state = DFConstant.STOP;
        }
     //   m_observable.setChanged();
    //    m_observable.notifyObservers(this);
    }

    public void stop() {
        m_state = DFConstant.STOP;
    }

    public int pull(int n) {
        int totalCompleted = 0;
        for(int i=0;i<m_targets.length;i++) {
            if(0 == m_targets[i].pull(n)) {
                totalCompleted++;
            }
        }
        return totalCompleted;
    }

    public int getTargetCount() {
        return m_targets==null ? -1 : m_targets.length;
    }
    /**
     * pull data from any transform in this DFInstance
     * @param id the Transform id
     * @param channel from which Transform will get data
     * @param buffer call next() will put transaction to this buffer
     * @return the count of transactions fetched
     */
    public int pull(String id, int channel, String[] buffer) {
        Transform transform = (Transform)m_ObjMap.get(id);
        int count = 0;
        Transaction tran = null;
        while(count<buffer.length && (tran=transform.next(channel))!=null) {
            buffer[count++] = tran.getData();
        }
        return count;
    }

    public void setId(String id) {
        m_id = id;
    }

    public String getId() {
        return m_id;
    }

    public void setDef(String def) {
        m_def = def;
    }

    public String getDef() {
        return m_def;
    }

    public void setState(int state) {
        m_state = state;
    }

    public int getState() {
        return m_state;
    }

    public Transform[] getTransforms() {

        return (Transform[]) (m_ObjMap.values().toArray(new Transform[0]));
    }

    public void errorLog(Object obj) {
        if(obj!=null)
            m_logger.severe(obj.toString());
    }

    public int initTransforms() {
        int errorNum = 0;
        for(Iterator<Transform> it=m_ObjMap.values().iterator();it.hasNext();) {
            Transform ts = (Transform)it.next();
            errorNum += ts.init();
        }
        return errorNum;
    }

    public void cleanupTransforms() {
        for(Iterator<Transform> it=m_ObjMap.values().iterator();it.hasNext();) {
            Transform ts = (Transform)it.next();
            ts.cleanup();
        }
    }

    public String[] getSourcePatterns() {
        return m_sourcePatterns;
    }

    public String[] getTargetPatterns() {
        return m_targetPatterns;
    }

    public String[] getEngines() {
        return m_engines;
    }

    public void setSourceFile(String name, String[] fileNames) {
        if(fileNames==null || fileNames.length==0)
            return;

        TSource source = null;
        for(int i=0; i<m_sources.length; i++) {
            if(m_sources[i].getName().equals(name)) {
                source = m_sources[i];
                break;
            }
        }
        if(source==null)
            return;
        source.setParameter("input_file", fileNames[0]);
        source.setParameter("input_files", fileNames);
    }

    public void addObserver(Observer o) {
//        m_observable.addObserver(o);
    }
}
