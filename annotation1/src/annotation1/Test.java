package annotation1;

import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
		// ��ȡ����ע��ķ���Method�������õ��˷����֪ʶ
		Method method = Class.forName("annotation1.User").getDeclaredMethod("myMethod");
		// ��Method������ͨ������getAnnotation����������õ�ע��
		MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);

		// �õ�ע��Ĳ���
		System.out.println(myAnnotation.para1());
		System.out.println(myAnnotation.para2());
	}

}
