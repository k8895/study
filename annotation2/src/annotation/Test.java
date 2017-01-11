package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

	/**
	 * �򵥴�ӡ��UserAnnotation ������ʹ�õ�����ע�� �÷���ֻ��ӡ�� Type ���͵�ע��
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
     * �򵥴�ӡ��UserAnnotation ������ʹ�õ��ķ���ע��
     * �÷���ֻ��ӡ�� Method ���͵�ע��
     * @throws ClassNotFoundException
     */
    public static void parseMethodAnnotation() throws ClassNotFoundException{
    	Method[] methods =UserAnnotation.class.getDeclaredMethods();
    	for(Method method : methods){
    		 /* 
             * �жϷ������Ƿ���ָ��ע�����͵�ע�� 
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
     * �򵥴�ӡ��UserAnnotation ������ʹ�õ��Ĺ��췽��ע��
     * �÷���ֻ��ӡ�� ���췽�� ���͵�ע��
     * @throws ClassNotFoundException
     */
    public static void parseConstructAnnotation()  throws ClassNotFoundException{
    	Constructor[] constructors=UserAnnotation.class.getConstructors();
    	for (Constructor constructor : constructors) {
            /* 
             * �жϹ��췽�����Ƿ���ָ��ע�����͵�ע�� 
             */  
            boolean hasAnnotation = constructor.isAnnotationPresent(MyAnnotation.class);
            if(hasAnnotation){
                 /* 
                 * ����ע�����ͷ��ط�����ָ������ע�� 
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
     * �򵥴�ӡ��UserAnnotation ������ʹ�õ����ֶ�ע��
     * �÷���ֻ��ӡ�� ���Ա ���͵�ע��
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
		System.out.println("------------------------------����Typeע��----------------------------------------------------------");
		parseTypeAnnotation();
		System.out.println("------------------------------����Methodע��-------------------------------------------------------");
        parseMethodAnnotation();
        System.out.println("------------------------------�������췽��(Construct)ע��------------------------------------------");
        parseConstructAnnotation();
        System.out.println("------------------------------�����ֶ�(Field)ע��-----------------------------------------------------");
        parseFieldAnnotation();
	}
}






















