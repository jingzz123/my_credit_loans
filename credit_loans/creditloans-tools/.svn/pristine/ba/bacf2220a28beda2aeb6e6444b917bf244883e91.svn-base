package cn.creditloans.tools.util;

import java.util.Date;

/**
 * 提供了一些通用的算法
 * @author Ash
 *
 */
public class UtilMethod {
	
	/**
	 * xml格式化
	 * @param strInput
	 * @return
	 */
	public static String xmlEncode(String strInput) {
		String input = strInput;
		input = parseStr(input);
		if (("").equals(input)) {
			return "";
		}
		input = replace(input, "&", "&amp;");
		input = replace(input, "\"", "&quot;");
		input = replace(input, "<", "&lt;");
		input = replace(input, ">", "&gt;");
		input = replace(input, "\r\n", "");
		input = replace(input, "\r", "");
		input = replace(input, "\n", "");
		return (input);
	}

	// 转换字符串数据
	public static String parseStr(String str) {
		if (str == null)
			return "";
		return str;
	}
	
	/**
	 * 空字符串转换为null，主要为了插入时别插入''
	 * @param str
	 * @return
	 */
	public static String transferStr(String str) {
		if (str == "")
			return null;
		return str;
	}

	public static String replace(String str, String substr, String substitute) {
		str = parseStr(str);
		String retstr = str;
		substr = parseStr(substr);
		substitute = parseStr(substitute);
		int substrlen = substr.length();
		int pos = retstr.indexOf(substr);
		String temp, half;
		while (pos != -1) {
			temp = retstr.substring(0, pos);
			temp = parseStr(temp);
			temp = temp + substitute;
			pos = pos + substrlen;
			half = retstr.substring(pos);
			half = parseStr(half);
			if (("").equals(half)) {
				retstr = temp;
				break;
			} else {
				if (half.indexOf(substr) == -1) {
					pos = -1;
				} else {
					pos = half.indexOf(substr) + temp.length();
				}
			}
			retstr = temp + half;
		}
		return retstr;
	}

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		if(str==null||str.trim().equals("")){
			return false;
		}
		//手机号码规则 ：匹配的号码是第一个为1，第二位为3458，长度为11的号码，前面可以有0.该规则基本满足现有手机的号段。
		if(str.matches("^[1][3,4,5,8][0-9]{9}$")){
			return true;
		}
		return false;
	}

	/**
	 * 验证带有区号电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		if(str==null||str.trim().equals("")){
			return false;
		}
		if(str.length()<=9){
			return false;
		}
		//"^[1-9]{1}[0-9]{5,8}$"; // 验证没有区号的
		//固定电话规则 ：根据现有的区号规则总结归纳出来的，区号规则是010只有一个、02开头的三位数、03到09开头的四位数。同样，区号也是可选的，真实的号码长度在6到8位。
		if(str.matches("^(010|02\\d|0[3-9]\\d{2})?\\d{7,8}$")){//用于匹配固定电话号码
			return true;
		}
		return false;
	}
	
	/**
	 * 将字符串转换为int返回，若为null或者“”，返回0
	 * @param string 要转换的字符串
	 * @return
	 */
    public static int parseInt(String string) {
        string = parseStr(string);
        try {
            if ("".equals(string)) {
                return 0;
            } else {
                return Integer.parseInt(string);
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * 很多虚假度字段在为空的情况下，缺省设定为“未确认”
     * @param status
     * @return
     */
    public static String getDefaultStatus(String status){
    	if(status==null||status.trim().equals("")){
    		return "未确认";
    	}
    	return status;
    }
    
    /**
     * 确认日期为空时，一般设定为当前日期
     */
   public static Date getDefaultProducedOn(Date dt){
	   if(dt == null){
		   return new Date();
	   }
	   return dt;
   }
   
   /**
    * 返回两位小数
    * @param value
    * @return
    */
   public static String formatTowDecimal(double value){
//	   DecimalFormat df = new DecimalFormat(".00");
//	   System.out.println(String.format("%.2f", value));
//	   return df.format(value);
	   return String.format("%.2f", value);
   }
   
	/**
	 * 获取总页数
	 * @param total 总数
	 * @param showSize 每页个数
	 * @return
	 */
   public static int getTotalPage(int total, int showSize) {
		if(total <= showSize) {
			return 1;
		}
		if(total % showSize == 0) {
			return total / showSize;
		}
		if(total % showSize != 0) {
			return (total / showSize) + 1;
		}
		return 0;
	}
}
