package cn.goldlone.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * IO操作工具包
 * @ClassName: IOUtils
 * @Description: TODO 
 * @author: CN
 * 创建时间: 2017年9月29日 下午7:23:06
 */
public class IOUtils {

	/**
	 * InputStream转String
	 * @param stream
	 * @return
	 */
	public static String inputStreamToString(InputStream stream) {
		StringBuilder sb = new StringBuilder();
		
		int len;
		byte[] buf = new byte[1024];
		try {
			while((len = stream.read(buf)) != -1)
				sb.append(new String(buf, 0, len));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
