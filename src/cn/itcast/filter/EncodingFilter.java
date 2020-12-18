package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
	private String charset;
	
	public void doFilter(ServletRequest req,
			ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if(charset == null || charset.isEmpty()) {
			charset = "UTF-8";
		}
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);

		EncodingRequest res = new EncodingRequest(request, charset);
		chain.doFilter(res, response);
	}

	public void destroy() {
	}

	public void init(FilterConfig config) throws ServletException {
		this.charset = config.getInitParameter("charset");
	}
}	
