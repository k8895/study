package annotation1;

import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		// 提取到被注解的方法Method，这里用到了反射的知识
		Method method = Class.forName("annotation1.User").getDeclaredMethod("myMethod");
		// 从Method方法中通过方法getAnnotation获得我们设置的注解
		MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);

		// 得到注解的参数
		System.out.println(myAnnotation.para1());
		System.out.println(myAnnotation.para2());
	}

}
