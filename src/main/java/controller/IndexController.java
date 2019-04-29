package controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "index")
public class IndexController extends Controller{
	
	/**
	 * 主页路由
	 */
	public void index() {
		render("index.html");
	}
	
	/**
	 *主页路由，根目录访问
	 */
	@ActionKey("/")
	public void index2() {
		render("index.html");
	}
}
