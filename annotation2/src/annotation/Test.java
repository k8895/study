package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

	/**
	 * 简单打印出UserAnnotation 类中所使用到的类注解 该方法只打印了 Type 类型的注解
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void parseTypeAnnotation() throws ClassNotFoundException {
		Class clazz = Class.forName("annotation.UserAnnotation");
		Annotation[] annotations = clazz.getAnnotations();

		for (Annotation annotation : annotations) {
			MyAnnotation myAnnotation = (MyAnnotation) annotation;
			System.out.println("type name = " + clazz.getName() + 
					" | id = " + myAnnotation.id() + 
					" | name = " + myAnnotation.name() + 
					" | gid = " + myAnnotation.gid());
		}
	}

	
	/**
     * 简单打印出UserAnnotation 类中所使用到的方法注解
     * 该方法只打印了 Method 类型的注解
     * @throws ClassNotFoundException
     */
    public static void parseMethodAnnotation() throws ClassNotFoundException{
    	Method[] methods =UserAnnotation.class.getDeclaredMethods();
    	for(Method method : methods){
    		 /* 
             * 判断方法中是否有指定注解类型的注解 
             */  
    		boolean hasAnnotation=method.isAnnotationPresent(MyAnnotation.class);
    		if(hasAnnotation){
    			MyAnnotation myAnnotation=method.getAnnotation(MyAnnotation.class);
    			System.out.println("method name = " + method.getName() + 
    					" | id = " + myAnnotation.id() + 
    					" | description = " + myAnnotation.name() + 
    					" | gid = " + myAnnotation.gid());
    		}
    	}
    }
    
    /**
     * 简单打印出UserAnnotation 类中所使用到的构造方法注解
     * 该方法只打印了 构造方法 类型的注解
     * @throws ClassNotFoundException
     */
    public static void parseConstructAnnotation()  throws ClassNotFoundException{
    	Constructor[] constructors=UserAnnotation.class.getConstructors();
    	for (Constructor constructor : constructors) {
            /* 
             * 判断构造方法中是否有指定注解类型的注解 
             */  
            boolean hasAnnotation = constructor.isAnnotationPresent(MyAnnotation.class);
            if(hasAnnotation){
                 /* 
                 * 根据注解类型返回方法的指定类型注解 
                 */
            	MyAnnotation myAnnotation=(MyAnnotation) constructor.getAnnotation(MyAnnotation.class);
            	System.out.println("constructor = " + constructor.getName() + 
            			" | id = " + myAnnotation.id() + 
            			" | description = " + myAnnotation.name() + 
            			" | gid = "+myAnnotation.gid());
    
            }
         }
     }
    
    /**
     * 简单打印出UserAnnotation 类中所使用到的字段注解
     * 该方法只打印了 类成员 类型的注解
     * @throws ClassNotFoundException
     */
    public static void parseFieldAnnotation() throws ClassNotFoundException{
    	Field[] fields=UserAnnotation.class.getDeclaredFields();
    	for (Field field : fields) {
            boolean hasAnnotation = field.isAnnotationPresent(MyAnnotation.class);
            if(hasAnnotation){
            	MyAnnotation myAnnotation=field.getAnnotation(MyAnnotation.class);
            	System.out.println("Field = " + field.getName()  + 
            			" | id = " + myAnnotation.id() + 
            			" | description = " + myAnnotation.name() + 
            			" | gid = "+myAnnotation.gid());
            }
    	}
    }
    
	
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("------------------------------解析Type注解----------------------------------------------------------");
		parseTypeAnnotation();
		System.out.println("------------------------------解析Method注解-------------------------------------------------------");
        parseMethodAnnotation();
        System.out.println("------------------------------解析构造方法(Construct)注解------------------------------------------");
        parseConstructAnnotation();
        System.out.println("------------------------------解析字段(Field)注解-----------------------------------------------------");
        parseFieldAnnotation();
	}
}






















