package config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.template.Engine;

import Interceptor.LoginInterceptor;
import common.AutoControllerRegist;
import controller.HelloController;
import corn.TimeTask;

public class Config extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		//开发模式，JFinal会输出每次请求的URL、Controller、Method和所有参数
		me.setDevMode(true);
		//开启依赖注入(将service注入到Controller中，也可以注入到拦截器Interceptor中)
		me.setInjectDependency(true);
		me.setInjectSuperClass(true);
		//配置使用fastJson：
		me.setJsonFactory(new FastJsonFactory());
	}

	//配置访问路由
	@Override
	public void configRoute(Routes me) {
		AutoControllerRegist.regist(me);
		//视图渲染基础路径
		me.setBaseViewPath("/views");
	}

	@Override
	public void configEngine(Engine me) {
		
	}

	/**
	 * JFinal的插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		/**
		 * Druid数据库连接池插件
		 */
//		Prop prop=PropKit.use("systemConfig.properties");
//		DruidPlugin dp=new DruidPlugin(prop.get("jdbc.url"),prop.get("jdbc.username"),prop.get("jdbc.password"));
//		me.add(dp);
		
		/**
		 * Redis插件服务，可轻易使用Redis相关API
		 * Cache redis = Redis.use("redis");
		 * redis.set("key", "value");
		 * redis.get("key");
		 */
		
//		 RedisPlugin redis = new RedisPlugin("redis", "localhost");
//		 me.add(redis);
		 
		 /**
		  * Cron4jPlugin任务调度插件，使用cron表达式，TimeTask是实现了Runnable借口的类，调度时执行run方法，注意要在POM文件中加入Cron4j的依赖
		  * Cron4jPlugin每次调用都是独立的，上次任务是否异常或者没有执行完毕，都不会影响新的任务的执行
		  * Cron4jPlugin在生产环境可以将参数写在配置文件中，详细可查看官方文档
		  */
		 Cron4jPlugin cp = new Cron4jPlugin();
		 cp.addTask("* * * * *", new TimeTask());
		 me.add(cp);
	}

	/**
	 * 配置JFinla的全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		//登录控制器（控制层全局拦截器）
		me.add(new LoginInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		
	}
}
