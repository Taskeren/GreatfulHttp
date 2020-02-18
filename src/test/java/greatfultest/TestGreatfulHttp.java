package greatfultest;

import com.sun.net.httpserver.HttpServer;
import greatfulhttp.GreatfulHttp;
import greatfulhttp.GreatfulHttpExchange;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TestGreatfulHttp {

	@Test
	public void testServer() throws IOException {

		final HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext("/", new GreatfulHttpMain());
		server.start();

	}

	public static class GreatfulHttpMain extends GreatfulHttp {

		@Override
		public void onHandle(GreatfulHttpExchange exchange) throws IOException {
			System.out.println("====== BodyContent ======");
			System.out.println(exchange.body());
			System.out.println("====== Parameters ======");
			System.out.println(exchange.getParameters());
			System.out.println("------ EndRequest ------");
			exchange.end(200, "Undefined");
		}
	}
	
}
