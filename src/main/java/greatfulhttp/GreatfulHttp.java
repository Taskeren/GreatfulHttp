package greatfulhttp;

import java.io.IOException;

import com.sun.net.httpserver.*;

public abstract class GreatfulHttp implements HttpHandler {

	@Override
	public final void handle(HttpExchange exchange) throws IOException {
		onHandle(new GreatfulHttpExchange(exchange));
	}
	
	public abstract void onHandle(GreatfulHttpExchange exchange) throws IOException;
	
}
