package greatfulhttp;

import java.io.IOException;
import java.net.URLDecoder;

public class GreatfulHttpUtil {

	/**
	 * 以 UTF8 解码 URL
	 * 
	 * @param raw 原内容
	 * @return 解码内容
	 * @throws IOException
	 */
	public static String decode(String raw) throws IOException {
		return decode(raw, "UTF-8");
	}

	/**
	 * 解码 URL
	 * 
	 * @param raw 原内容
	 * @param env 编码
	 * @return 解码内容
	 * @throws IOException
	 */
	public static String decode(String raw, String env) throws IOException {
		return URLDecoder.decode(raw, env);
	}

}
