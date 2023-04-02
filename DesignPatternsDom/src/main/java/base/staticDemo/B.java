package base.staticDemo;

import Utills.PrintUtill;

public class B extends A {
	public B (){
		PrintUtill.println("B 初始化");
//		System.out.println("B 初始化");
	}


	public static void println(){
		PrintUtill.println("static B print");
	}

	private  static  void pritn2(){
		PrintUtill.println("private static B print");
	}

	public static void main(String[] args) {
		A a = new B();
		a.println();
		new B().pritn2();
		a = new A();
		a.println();
	}
}
