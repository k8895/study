package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

//@Target目标 元注解： 表明MyAnnotation只能为方法注解
@Target({ElementType.METHOD})
public @interface MyAnnotation {

	String value();
	
}

//@Target({ElementType.METHOD}) 目标
//ElementType.CONSTRUCTOR        构造器声明 
//ElementType.FIELD          　　域声明（包括 enum 实例） 
//ElementType.LOCAL_VARIABLE     局部变量声明 
//ElementType.METHOD           　方法声明 
//ElementType.PACKAGE          　包声明 
//ElementType.PARAMETER          参数声明 
//ElementType.TYPE          　　 类，接口（包括注解类型）或enum声明

//@Retention(RetentionPolicy.SOURCE)
//不将注解保存在class文件中，也就是说象“//”一样在编译时被过滤掉了。

//@Retention(RetentionPolicy.CLASS)
//只将注解保存在class文件中，而使用反射读取注解时忽略这些注解。

//@Retention(RetentionPolicy.RUNTIME)
//即将注解保存在class文件中，也可以通过反射读取注解。


//@Inherited
//在默认的情况下，父类的注解并不会被子类继承。如果要继承，就必须加上Inherited注解。

















