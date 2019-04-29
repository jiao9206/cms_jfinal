package config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jfinal.kit.StrKit;

public class BasePathHandler extends Handler{

	/**
	 * contextPathName存储项目根目录的key
	 */
	private String contextPathName;
	
	public BasePathHandler() {
		this.contextPathName="path";
	}
	
	public BasePathHandler(String contextPathName) {
		if (StrKit.isBlank(contextPathName)) {
			throw new IllegalArgumentException("contextPathName can not be blank.");
		}
		this.contextPathName=contextPathName;
	}
	
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		String contextPath=request.getContextPath();
		request.setAttribute(contextPathName, contextPath);
		next.handle(target, request, response, isHandled);
	}

}
