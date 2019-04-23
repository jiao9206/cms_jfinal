package controller;

import java.util.List;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import bean.HelloBean;
import service.HelloService;

@ControllerBind(controllerKey = "/hello")
public class HelloController extends Controller{

	@Inject
	private HelloService helloService;
	
	public void index() {
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
	public void testDB() {
		try {
			List<Record> list=Db.find("select * from tb_test");
			System.out.println(list);
			renderText("SUCCESS!");
		}catch(Exception e) {
			e.printStackTrace();
			renderText("Fail!");
		}
		
	}
}
