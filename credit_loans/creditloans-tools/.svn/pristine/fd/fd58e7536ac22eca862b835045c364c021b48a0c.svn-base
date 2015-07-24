package cn.creditloans.tools.validatorEx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.core.io.Resource;

import cn.creditloans.tools.context.AppContextUtils;
import cn.creditloans.tools.validator.util.ValidatorConstants;


public class ErrorCode {

    //1.ETL处理
    //==>1.1文件读取
    public static int ETL_READ_ERR_ERROR_FILE = 1000;
    public static int ETL_READ_ERR_ERROR_RECORD = 1001;
    public static int ETL_ERR_ERROR_SAME_NO = 1002;
    public static int ETL_ERR_NOT_RECORD = 1003;

    private static Map<String,String> m_errorMap = null;//存放错误代码(key)和错误代码描述(value)的关系
    private static boolean m_initialized = false;

    public static boolean load(String errorCodeFile,ServletContext context){
        if(m_initialized)
            return true;
        Resource resource = AppContextUtils.getResource(context, errorCodeFile);
        if(resource==null){
            m_initialized = false;
            return false;
        }

        BufferedReader reader = null;
        m_errorMap = new HashMap<String,String>();
        try{
        	InputStreamReader inStream = new InputStreamReader(resource.getInputStream(),ValidatorConstants.ENCODING);
        	reader = new BufferedReader(inStream);
            String line = null;
            String key = null;
            String value = null;
            while(true){
                line = reader.readLine();
                if(line==null){
                    break;
                }
                if(line.length()==0 ||line.startsWith("#")){
                    continue;
                }
                int index = line.indexOf("=");
                if(index<=0){
                    continue;
                }
                key = line.substring(0,index);
                //System.out.println("key="+key);
                value = line.substring(index+1);
                if(key!=null&&value!=null){
                    m_errorMap.put(key,value);
                }
            }
        }catch(Exception e){
            m_initialized = false;
            return false;
        }finally{
            try{
                reader.close();
            }catch(Exception ee){
                ee.printStackTrace();
            }
        }
        m_initialized = true;
        return true;
    }

    //根据所给的错误代码得到有关的错误代码描述
    public static String getErrorInfo(int errno){
        if(m_errorMap==null || m_errorMap.size()==0){
            return String.valueOf(errno);
        }else{
            Object valueObj = m_errorMap.get(String.valueOf(errno));
            if(valueObj==null){
                return String.valueOf(errno);
            }else{
                return String.valueOf(valueObj);
            }
        }
    }

}