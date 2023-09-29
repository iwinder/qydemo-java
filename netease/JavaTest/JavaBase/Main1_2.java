package netease;

import java.util.Scanner;

public class Main1_2 {
	
	/**
	 * 1、
	 * fahrenheit 华氏温度
	 * celsius  摄氏温度
	 * 
	 * */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Scanner in = new Scanner(System.in);
//		int fahrenheit = in.nextInt();
//		int celsius= (int)((fahrenheit-32)*(5/9.0));
//		System.out.println(celsius);
//	}
	
	/**
	 * 2.1
	 * */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Scanner in = new Scanner(System.in);
//		int BJT = in.nextInt();
//		
//		if((BJT<0 || BJT>2359)|| (BJT%100)>60 ){
//			//大于边界时报错
//			System.out.println("error");
//		}else if( (BJT<=2359) && (BJT>=800) ){
//			//不跨日时
//			System.out.println(BJT-800);
//		}else{
//			//跨日时：2400-800+BJT=>1600+BJT
//			System.out.println(1600+BJT);
//		}
//	}
	/**
	 * 2.2
	 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int inNum = in.nextInt();
		
		if(inNum>=11 && inNum<=59){
			int inR = inNum/10-1;
			int inS = inNum%10-1;
			String[] r= {"Unreadable",
					 "Barely readable, occasional words distinguishable",
					 "Readable with considerable difficulty",
					 "Readable with practically no difficulty",
					 "Perfectly readable"};
			String[] s={
					"Faint signals, barely perceptible",
					"Very weak signals",
					"Weak signals",
					"Fair signals",
					"Fairly good signals",
					"Good signals",
					"Moderately strong signals",
					"Strong signals",
					"Extremely strong signals"
			};
			System.out.println(s[inS]+", "+r[inR].toLowerCase()+".");
		}else{
			System.out.println("error");
		}
		in.close();
		
	}
}
