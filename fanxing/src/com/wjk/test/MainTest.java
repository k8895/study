package com.wjk.test;

public class MainTest {

	public static void main(String[] args) {
		
		Test1<String> test1=new Test1<String>();
		test1.setData("abc");
		System.out.println(test1.getData());
		
		Test1<Integer> test2=new Test1<Integer>();
		test2.setData(123);
		System.out.println(test2.getData());
		
		System.out.println(test1.getClass() == test2.getClass()); 
	}
	
}
