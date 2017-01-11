package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����ע�� Test <br>
 * Ϊ������ԣ�ע��Ŀ��Ϊ�� ���������Լ����췽��<br> 
 * ע���к�������Ԫ�� id ,name�� gid;  <br>
 * id Ԫ�� ��Ĭ��ֵ 0 <br>
 */ 
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name() ;
    int id() default 0;
    Class<Long> gid();
}