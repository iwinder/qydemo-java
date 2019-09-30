package base.staticDemo;

import Utills.PrintUtill;

public class A {
	public A(){
		PrintUtill.println("A 初始化");
//		System.out.print("A 初始化");
	}



	public static void println(){
		PrintUtill.println("static A print");
	}

	private  static  void pritnln2(){
		PrintUtill.println("private static A print");
	}
}
