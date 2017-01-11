package annotation1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *����һ��ע��
 */
@Target(ElementType.METHOD) //// ����һ���Է�����ע�⣬�������ǰ����ࡢ�����Ⱥܶණ��  
@Retention(RetentionPolicy.RUNTIME) // ����ʱ�䣬һ��ע�����Ϊ�˿�ܿ���ʱ���������ļ�ʹ�ã�JVM����ʱ�÷���ȡ������������һ�㶼ΪRUNTIME����  
@Documented // ���������������͵�annotationӦ�ñ���Ϊ����ע�ĳ����Ա�Ĺ���API����˿��Ա�����javadoc����Ĺ����ĵ���  
public @interface MyAnnotation {

	// ���������������͵�annotationӦ�ñ���Ϊ����ע�ĳ����Ա�Ĺ���API����˿��Ա�����javadoc����Ĺ����ĵ���  
	String para1() default "";
	int para2() default -1;
	
}
