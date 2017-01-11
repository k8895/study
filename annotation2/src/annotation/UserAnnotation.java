package annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * �����ר����������ע��ʹ��
 */
@MyAnnotation(name = "type", gid = Long.class)
// ʹ������ע��
public class UserAnnotation {
	@MyAnnotation(name = "param", id = 1, gid = Long.class) // ʹ�������Աע��
	private Integer age;

	@MyAnnotation(name = "construct", id = 2, gid = Long.class) // ʹ���˹��췽��ע��
	public UserAnnotation() {
	}

	@MyAnnotation(name = "public method", id = 3, gid = Long.class) // ʹ���� public ����ע��
	public void a() {
		Map<String, String> m = new HashMap<String, String>(0);
	}

	@MyAnnotation(name = "protected method", id = 4, gid = Long.class) // protected
																// ����ע��
	protected void b() {
		Map<String, String> m = new HashMap<String, String>(0);
	}

	@MyAnnotation(name = "private method ", id = 5, gid = Long.class) // private ����ע��
	private void c() {
		Map<String, String> m = new HashMap<String, String>(0);
	}

	public void b(Integer a) {
	}
}