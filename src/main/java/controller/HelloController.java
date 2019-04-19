package controller;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.core.paragetter.Para;

import bean.HelloBean;
import common.ControllerBind;
import service.HelloService;

@ControllerBind(controllerKey = "/myhello")
public class HelloController extends Controller{

	@Inject
	private HelloService helloService;
	
	public void index(@Para("name") String name) {
		System.out.println(name);
		System.out.println(this.getPara("name"));
		helloService.test();
		render("hello.html");
	}
	
	public void save() {
		HelloBean helloBean=this.getBean(HelloBean.class,"");
		renderText("success");
	}
	/**
	 * Controller中的public方法都会成为Action，除了@NotAction注解的方法
	 */
	@NotAction
	public void notAction() {
		
	}
}
