package annotation;


public class Test {

//	@MyAnnotation(value = "abc")
	// 如果没有写属性名的值，而这个注解又有value属性，就将这个值赋给value属性，如果没有，就出现编译错误
	 @MyAnnotation("abc")
	public void myMethon() {

	}

}
