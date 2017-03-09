package up;

import org.apache.commons.codec.binary.Base64;

import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {

	private static String privKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMu6MbNDBZrPCup6fxrWm59RMOpoxV6UibsZTRGSpm6tWe6lAAKo9RyBNiYCKOsUfh//+p4mEUz7OhmQ4t2f/Pd1hD0GMBwvzdmJa50tZeuHGyZMILn3CyKE3e0WSWmUCLT8DVOpWXtrV9XgyaE8ZRTlfIMSfTUMJedr1PrFfgOfAgMBAAECgYBFoEboGxnJRMVYSbEy/PGGy3rKQjBiGKXRud3rCZGz9a7srpuITTIoIcjdyJMvdHt+eD+w2RXEl3/NyQSf9dPAKqVh60VHFltCJuKbm1YDPmIfgUd737tYgchV6pP5iRSnPyjfp4M6WCewiEIe3YoxysOqf+Lz5haLyAvC4DfdMQJBAPFLR9lVIEUd18bCI9h8HjqVojpDy70A4Y44zKxWVWaoFaL4mbE1D0l0DNQoT227Ve+KAUZW89gxMtGc3yIua4MCQQDYJMoZ3lz1SkzxTxVHFnwqyeOVEDx7kDqMzCC5NBylXPYT7jjm5XhHFM95LlM0oDLM1G7IoqgmGR8kblee5AC1AkB8u2Oaz0xtLi4aysTo5dBijHNeFym8vDXc5TO4arCe7oiwxD3+wuTyHZF8IpJcsRiNSRO7nrtSqKwSeztFWiWtAkAbTfJ1+CA4IG294iam91EyOpTUZNuvJFYxKSxNGScxQKAZfnOTv5NFL3zSKyb5s+8W7xMbp2ikf3HusT3CZb0tAkAx14Jh763byh4aA2Yezstvz4fQoVyabIShbtyji7KndrCjiGk561RcdHcoNvbgTVM3IWAh5mliv4x8eXywqTns";
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLujGzQwWazwrqen8a1pufUTDqaMVelIm7GU0RkqZurVnupQACqPUcgTYmAijrFH4f//qeJhFM+zoZkOLdn/z3dYQ9BjAcL83ZiWudLWXrhxsmTCC59wsihN3tFklplAi0/A1TqVl7a1fV4MmhPGUU5XyDEn01DCXna9T6xX4DnwIDAQAB";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String signedData;
		try {

			byte[] decodeBase64 = Base64.decodeBase64(privKey);
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(decodeBase64);
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
			/**把json 对象转出字符串 名称或者值为空的不参与,名称与值以=分割,仔细按传入的参数的次序
			 * {
				"id":"",
				"xm":"张三",
				"xb":"1",
				"yyh":"",
				"csrq":"2016-08-05",
				"sfzh":"130630199212042810",
				"sjh":"15227030347",
				"xzdz":"北京市朝阳区光华路SOHO A座10层",
				"xxly":"18","hzxz":"1","ghlb":"1",
				"szks":"","szys":"",
				"yyrq":"2016-10-31","jzzt":"1",
				"jzrq":"","jg":"1000112","scbz":"0"
			}
			 */
			String myinfo = "xm=张三xb=1csrq=2016-08-05sfzh=130630199212042810sjh=15227030347xzdz=北京市朝阳区光华路SOHO A座10层xxly=18hzxz=1ghlb=1yyrq=2016-10-31jzzt=1jg=1000112scbz=0timestamp=2016081113071100"; // 要签名的信息
			// 用私钥对信息生成数字签名
			java.security.Signature signet = java.security.Signature.getInstance("SHA1WithRSA");
			signet.initSign(myprikey);
			signet.update(myinfo.getBytes("UTF-8"));
			byte[] signed = signet.sign(); // 对信息的数字签名
			signedData = Base64.encodeBase64String(signed);
			System.out.println("signed(签名内容)原值=" + Base64.encodeBase64String(signed));
			System.out.println("info（原值）=" + myinfo);
			
			
			
			X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);


			java.security.Signature signetcheck = java.security.Signature.getInstance("SHA1WithRSA");
			signetcheck.initVerify(pubKey);
			signetcheck.update(myinfo.toString().getBytes("UTF-8"));
			boolean flag = signetcheck.verify(Base64.decodeBase64(signedData));
			System.out.println("*************"+flag);
			
			String str  ="emXa4JIdPAE+DaRF8B/zazGHiW0q+kDOwJCjs662jYW5HnuyTw9sIf7kStI9U7eO3jh78ZAOCUOAjsV3v92TAX6/LD94+oYj08IXnYrOwO1A7GapkGegBdNe7j4FsSOGJGSzi35iA6RLcE1dum1nTfaUiUVx17iWveMfwyZV1JQ=";
			System.out.println(URLEncoder.encode(str));
			          
		} catch (java.lang.Exception e) {
			e.printStackTrace();
			System.out.println("签名并生成文件失败");
		}
	}
}
