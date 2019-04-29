package controller;

import java.util.List;
import java.util.UUID;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.plugin.activerecord.tx.Tx;
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
	@Before({Tx.class})
	public void testDB() {
		try {
			List<Record> list=Db.find("select * from tb_test");
			System.out.println(list);
			/**
			 * Db.tx+lambda表达式进行事务控制，lambda表达式返回true,则提交事务，返回false则回滚事务
			 */
//			Db.tx(()->{
//				Db.save("tb_test", new Record().set("id", UUID.randomUUID().toString().replaceAll("-", "")).set("name", "潘月").set("age", 21).set("address", "山东济南"));
//				Db.save("tb_test", new Record().set("id", UUID.randomUUID().toString().replaceAll("-", "")).set("name", "潘小月").set("age", 21).set("address", "山东济南"));
//				return true;
//			});
			Db.save("tb_test", new Record().set("id", UUID.randomUUID().toString().replaceAll("-", "")).set("name", "潘月").set("age", 21).set("address", "山东济南"));
//			int i=1/0;
			Db.save("tb_test", new Record().set("id", UUID.randomUUID().toString().replaceAll("-", "")).set("name", "潘小月").set("age", 21).set("address", "山东济南"));
			renderText("SUCCESS!");
		}catch(Exception e) {
			e.printStackTrace();
			renderText("Fail!");
			/**
			 * @Before({Tx.class})方式控制事务，发生异常的时候，必须向上抛出，Tx拦截器捕获异常后就会回滚事务
			 */
			throw e;
		}
		
	}
}
