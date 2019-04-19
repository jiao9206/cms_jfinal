package common;

import java.util.List;

import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
public class AutoControllerRegist {
	
	
	@SuppressWarnings("rawtypes")
	public static void regist(Routes routes) {
		List<Class> classList = ClassSearcher.findClasses(Controller.class);
		ControllerBind controllerBind = null;
		for (Class clazz : classList) {
			controllerBind = (ControllerBind) clazz.getAnnotation(ControllerBind.class);
			if (controllerBind == null) {
				routes.add(controllerKey(clazz), clazz);
			} else {
				routes.add(controllerBind.controllerKey(), clazz, controllerBind.viewPath());
			}
		}
	}
	private static String controllerKey(Class clazz) {
		if (!clazz.getSimpleName().endsWith("Controller")) {
			throw new RuntimeException(clazz + " don,t has a ControllerBind annotation and it,s don,t end Controller! ");
		}
		String controllerKey = "/" + StrKit.firstCharToLowerCase(clazz.getSimpleName());
		controllerKey = controllerKey.substring(0, controllerKey.indexOf("Controller"));
		return controllerKey;
	}
	
}
