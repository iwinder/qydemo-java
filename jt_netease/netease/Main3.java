package netease;

import java.util.Scanner;

public class Main3 {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Scanner in = new Scanner(System.in);
//		int inNum = in.nextInt();
//		int oddNum=0;
//		int evenNum=0;
//		while(inNum != -1){
//			if(inNum%2==0){
//				evenNum++;
//			}else{
//				oddNum++;
//				
//			}
//			inNum = in.nextInt();
//		}
//		System.out.println(oddNum+" "+evenNum);
//	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Scanner in = new Scanner(System.in);
//		int inNum = in.nextInt();
//		
//		int tmpNum = 6;
//		int zNum=0;
//		int s = (int) Math.pow(10,tmpNum);
//		if(inNum==0){
//			System.out.println(0);
//		}else if(inNum==1){
//			System.out.println(1);
//		}else{
//			
//			int tmpInNum = inNum;
//			while(tmpNum>0){
//				if((inNum%s)!=inNum){
//					break;
//				}
//				tmpNum--;
//				s = (int) Math.pow(10,tmpNum);
//			}
//			int tmpNum2 =tmpNum+1;
//			int heightNum =0;
//			while(tmpNum>=0){
//				s = (int) Math.pow(10,tmpNum);
//				heightNum = tmpInNum/s;//最高位
//				if( (heightNum%2==0 && tmpNum2%2==0) || (heightNum%2!=0 && tmpNum2%2 !=0) ){
//					zNum += (int) Math.pow(2,tmpNum);
//					
//				}
//				tmpInNum = tmpInNum-heightNum*s;
//				tmpNum--;
//				tmpNum2 =tmpNum+1;
//			}
//			System.out.println(zNum);
//		}
//	}
//	public static void main(String[] args) {
//		int n,m=0;
//		Scanner in = new Scanner(System.in);
//		int []tmp={1,2};
//		int inum=0;
//	
//		while(inum<2){
//			tmp[inum] = in.nextInt();
//			inum++;
//		}
//		n = tmp[0];
//		m = tmp[1];
//		
//		int flag = 1;
//		int snum=0;
//
//		int isYes = 0;
//		
//		for(int i=2;;i++){
//			isYes=1;
//			if(flag>m)break;
//			for(int j=2;j<i;j++){
//				if( (i%j==0) &&(i!=2)){
//					isYes=0;
//					break;
//				}
//			}
//			if(isYes==1){
//				if(flag>=n){
//					snum+=i;
//				}
//				flag++;
//			}
//				
//		}
//		System.out.println(snum);
//		in.close();
//	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		in.close();
		boolean isZheng = true;
		if(num<0){
			isZheng = false;
			num = 0 - num;
		}
//		if(num==0){
//			System.out.println("ling");
//		}
		int lowNum = 0;
		int tmpNum = num;
		StringBuffer str = new StringBuffer();
		while((tmpNum/10)>=0){
			lowNum = tmpNum%10;
			tmpNum = tmpNum/10;

			switch (lowNum){
				case 0:
					str.insert(0, "ling ");
					break;
				case 1:
					str.insert(0, "yi ");
					break;
				case 2:
					str.insert(0, "er ");
					break;
				case 3:
					str.insert(0, "san ");
					break;
				case 4:
					str.insert(0, "si ");
					break;
				case 5:
					str.insert(0, "wu ");
					break;
				case 6:
					str.insert(0, "liu ");
					break;
				case 7:
					str.insert(0, "qi ");
					break;
				case 8:
					str.insert(0, "ba ");
					break;
				case 9:
					str.insert(0, "jiu ");
					break;
				
			}
			if(tmpNum==0){
				break;
			}
			
		}
		if(isZheng==false){
			str.insert(0, "fu ");
		}
		System.out.println(str.toString().trim());
		
		
	}
}
