package greatfulhttp;

import java.util.HashMap;

public class ParamMap extends HashMap<String, String> {
	private static final long serialVersionUID = 1L;

	/**
	 * 建立一个空的哈希表
	 */
	public ParamMap() {
		super();
	}
	
	/**
	 * 根据输入的数据建立哈希表
	 * @param str
	 */
	public ParamMap(String str) {
		super();
		if(!str.isEmpty()) {
			String[] parts = str.split("&");       // "a=1&b=2" => ["a=1", "b=2"]
			for(String part : parts) {
				String[] kvpair = part.split("="); // "a=1" => ["a", "1"]
				if(kvpair[0] != null) {
					put(kvpair[0], kvpair.length > 1 ? kvpair[1] : ""); // to put them into the map.
				}
			}
		}
	}
	
}
