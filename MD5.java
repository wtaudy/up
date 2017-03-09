package up;


import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Encoder;

public class MD5 {

	private static BASE64Encoder encoder = new BASE64Encoder();
	private static String salt = "dev-252-12345";

	public static void main2(String[] avs) {
		try {
//			String param = "{'beginDay':'20160805','ks':'2','ghlb':'1'}";
//			String timestamp = "2016081111371200001";
//			
			String param = "{'jg':'1000112','ghlb':'1','beginDay':'20160801'}";
			String timestamp = "2016081111371200001";
//			
//			String param = "{'beginDay':'20160805','ks':'2','ghlb':'1'}";
//			String timestamp = "2016081111371200001";
//			
			
//			{"jgmc":"aaa"}1
//			md5后6170ff86d075090470390a13eb904599
//			base64 NjE3MGZmODZkMDc1MDkwNDcwMzkwYTEzZWI5MDQ1OTk=
//			{"msg":"非法请求,签名未通过","code":"401","data":""}
//		
			
//			String param = "{\"jgmc\":\"\\u6d4b\\u8bd5\\u673a\\u6784`\"}";
//			String timestamp = "2016081116071751";
//					
					
			MessageDigest md = MessageDigest.getInstance("MD5");
			System.out.println(salt + param+timestamp);
			md.update((salt + param+timestamp).getBytes("UTF-8"));			
			byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
			System.out.println(buf.toString());
			String sign = encoder.encode(buf.toString().getBytes("utf-8"));
			String sign2 = Base64.encodeBase64String(buf.toString().getBytes("utf-8"));
			System.out.println(sign);
			System.out.println("**********");
			System.out.println(sign2);
			//System.out.println(encoder.encode("6170ff86d075090470390a13eb904599".getBytes("utf-8")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] avs) {
		try {
//			String param = "dev-252-12345aaaa1";
			String param = "dev-252-12345{\"jgmc\":\"\\u6d4b\\u8bd5\\u673a\\u6784`\"}2016081116071751";
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(param.getBytes("UTF-8"));			
			byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
			System.out.println(buf.toString());
			String sign = encoder.encode(buf.toString().getBytes("UTF-8"));
			System.out.println(sign);
			//System.out.println(encoder.encode("6170ff86d075090470390a13eb904599".getBytes("utf-8")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("###");
		main2(null);
	}
}
