package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

//@TargetĿ�� Ԫע�⣺ ����MyAnnotationֻ��Ϊ����ע��
@Target({ElementType.METHOD})
public @interface MyAnnotation {

	String value();
	
}

//@Target({ElementType.METHOD}) Ŀ��
//ElementType.CONSTRUCTOR        ���������� 
//ElementType.FIELD          ���������������� enum ʵ���� 
//ElementType.LOCAL_VARIABLE     �ֲ��������� 
//ElementType.METHOD           ���������� 
//ElementType.PACKAGE          �������� 
//ElementType.PARAMETER          �������� 
//ElementType.TYPE          ���� �࣬�ӿڣ�����ע�����ͣ���enum����

//@Retention(RetentionPolicy.SOURCE)
//����ע�Ᵽ����class�ļ��У�Ҳ����˵��//��һ���ڱ���ʱ�����˵��ˡ�

//@Retention(RetentionPolicy.CLASS)
//ֻ��ע�Ᵽ����class�ļ��У���ʹ�÷����ȡע��ʱ������Щע�⡣

//@Retention(RetentionPolicy.RUNTIME)
//����ע�Ᵽ����class�ļ��У�Ҳ����ͨ�������ȡע�⡣


//@Inherited
//��Ĭ�ϵ�����£������ע�Ⲣ���ᱻ����̳С����Ҫ�̳У��ͱ������Inheritedע�⡣

















