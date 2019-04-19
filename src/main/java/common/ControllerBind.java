package common;

public @interface ControllerBind {

	public String controllerKey() ;
	public String viewPath() default "";
	
}
