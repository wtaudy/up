package up;


import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author lhl
 *
 */
public class TestMain {

public static void main(String[] args) {
	
	Map mapp = new HashMap<>();
	String ssks = (String) mapp.get("ssks");
	Set entrySet = mapp.entrySet();
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		for (String temp : a) {
			if ("2".equals(temp)) {
				System.out.println("sssssssssss2222");
				a.remove(temp);
			}
		}   
	
	
	
	
	
	Calendar calendar = new GregorianCalendar();
	calendar.get(Calendar.AM_PM);
	calendar.get(Calendar.AM);
	int week  = calendar.get(Calendar.DAY_OF_WEEK);
	calendar.add(Calendar.DATE, 6-week);//
	System.out.println(calendar.getTime());
	
	System.out.println(Runtime.getRuntime().totalMemory());
	System.out.println(Runtime.getRuntime().maxMemory());
	HashSet ss = new HashSet<>(new ArrayList<>());	
	
	ThreadMXBean threadMBean = ManagementFactory.getThreadMXBean();
	ThreadInfo[] dumpAllThreads = threadMBean.dumpAllThreads(false, false);
	System.out.println(dumpAllThreads[0].getThreadName());
	String string = "0000111";
	String string2 = string.replaceFirst("^0*", "");
	System.out.println(string2);
	
	String sss = "你好";
	try {
		byte[] bs = sss.getBytes("UTF-8");
		byte[] bs2 = sss.getBytes("UNICODE");
		
		
		Charset cs = Charset.forName("UNICODE");
		ByteBuffer encode = cs.encode(sss);
		
		System.out.println(bs);
		System.out.println(bs2);
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	
	
	for (int i = 0; i < 100000; i++) {  
	      ByteBuffer.allocateDirect(128);  
	    }  
	
	
}



}