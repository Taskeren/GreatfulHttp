package greatfulhttp;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;

public class GreatfulHttpUtil {

	/**
	 * 以 UTF8 解码 URL
	 * 
	 * @param raw 原内容
	 * @return 解码内容
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
	 */
	public static String decode(String raw, String env) throws IOException {
		return URLDecoder.decode(raw, env);
	}

	/**
	 * 转换参数为哈希表
	 * @param paramStr 原内容
	 * @return 哈希表
	 */
	public static HashMap<String, String> toMap(String paramStr) {
		final HashMap<String, String> map = new HashMap<>();

		if(paramStr == null || paramStr.isEmpty()) return map;

		final String[] parts = paramStr.split("&");
		for(String part : parts) {
			String[] kvp = part.split("=");
			if(kvp[0] == null) continue;
			map.put(kvp[0], kvp.length>1 ? kvp[1] : ""); // to prevent ArrayOutOfRangeException.
		}

		return map;
	}

}
