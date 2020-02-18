package greatfulhttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Scanner;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

/**
 * 对 {@link HttpExchange} 的封装类。
 * 
 * @author Taskeren
 */
public class GreatfulHttpExchange extends HttpExchange {

	protected final HttpExchange exchange;

	public GreatfulHttpExchange(HttpExchange exchange) {
		this.exchange = exchange;
	}

	/*
	 * ******************************************
	 * 
	 * GreatfulHttp Methods
	 * 
	 ******************************************/

	protected String requestBody;

	/**
	 * 获取内容
	 * @return 内容
	 */
	public String body() throws IOException {
		return body("UTF-8");
	}

	/**
	 * 获取内容
	 * @param charset 编码
	 * @return 内容
	 */
	public String body(String charset) throws IOException {
		if (requestBody == null) {
			Scanner scan = new Scanner(getRequestBody(), charset);
			scan.useDelimiter("\\A");
			String str = scan.hasNext() ? scan.next() : "";
			scan.close();
			requestBody = GreatfulHttpUtil.decode(str);
		}
		return requestBody;
	}

	/**
	 * 返回状态并关闭请求（用于部分不返回内容的请求和状态）
	 * @param status 状态
	 * @see #end(int, Object)
	 */
	public void end(int status) throws IOException {
		end(status, "");
	}

	/**
	 * 返回内容并关闭请求
	 * @param status  状态（e.g. 200，404，503）
	 * @param message 返回内容（部分状态限制不返回任何内容）
	 */
	public void end(int status, Object message) throws IOException {
		final byte[] b = String.valueOf(message).getBytes();
		exchange.sendResponseHeaders(status, b.length);
		OutputStream o = exchange.getResponseBody();
		o.write(b);
		o.close();
	}

	public boolean isGetRequest() {
		return getRequestMethod().equalsIgnoreCase("get");
	}

	public boolean isPutRequest() {
		return getRequestMethod().equalsIgnoreCase("put");
	}

	public boolean isPostRequest() {
		return getRequestMethod().equalsIgnoreCase("post");
	}

	public boolean isHeadRequest() {
		return getRequestMethod().equalsIgnoreCase("head");
	}

	public boolean isDeleteRequest() {
		return getRequestMethod().equalsIgnoreCase("delete");
	}

	/**
	 * 获取请求URI中的Param键值对
	 * <p>
	 * a=1&b=2 => {"a": 1, "b": 2}
	 */
	public HashMap<String, String> getParameters() {
		return GreatfulHttpUtil.toMap(getRequestURI().getQuery());
	}

	/*
	 * ******************************************
	 * 
	 * Override Methods
	 * 
	 ******************************************/

	public Headers getRequestHeaders() {
		return exchange.getRequestHeaders();
	}

	public Headers getResponseHeaders() {
		return exchange.getResponseHeaders();
	}

	public URI getRequestURI() {
		return exchange.getRequestURI();
	}

	public String getRequestMethod() {
		return exchange.getRequestMethod();
	}

	public HttpContext getHttpContext() {
		return exchange.getHttpContext();
	}

	public void close() {
		exchange.close();
	}

	public InputStream getRequestBody() {
		return exchange.getRequestBody();
	}

	public OutputStream getResponseBody() {
		return exchange.getResponseBody();
	}

	public void sendResponseHeaders(int var1, long var2) throws IOException {
		exchange.sendResponseHeaders(var1, var2);
	}

	public InetSocketAddress getRemoteAddress() {
		return exchange.getRemoteAddress();
	}

	public int getResponseCode() {
		return exchange.getResponseCode();
	}

	public InetSocketAddress getLocalAddress() {
		return exchange.getLocalAddress();
	}

	public String getProtocol() {
		return exchange.getProtocol();
	}

	public Object getAttribute(String var1) {
		return exchange.getAttribute(var1);
	}

	public void setAttribute(String key, Object val) {
		exchange.setAttribute(key, val);
	}

	public void setStreams(InputStream var1, OutputStream var2) {
		exchange.setStreams(var1, var2);
	}

	public HttpPrincipal getPrincipal() {
		return exchange.getPrincipal();
	}

}
