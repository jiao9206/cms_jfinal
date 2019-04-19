package Interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class LoginInterceptor implements Interceptor{

	public void intercept(Invocation inv) {
		System.out.println("Interceptor Before Method");
		inv.invoke();
		System.out.println("Interceptor after Method");
	}

}
