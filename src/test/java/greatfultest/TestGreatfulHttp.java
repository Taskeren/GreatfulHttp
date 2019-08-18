package greatfultest;

import org.junit.Test;

import greatfulhttp.ParamMap;

public class TestGreatfulHttp {

	@Test
	public void testRemap() {
		String str = "name=Taskeren&alias=壮烈成仁&hardness=C10&draws=";
		ParamMap map = new ParamMap(str);
		System.out.println(map);
	}
	
}
