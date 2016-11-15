package netease;

import java.util.Scanner;

public class Main4_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int[] a= new int[101];
		
		int x=0,y=0;
		int flag =0;
		int max = 0;
		int min = 0;
		boolean first = true;
		String s = " ";
		while(flag !=2){
		
				x =  in.nextInt();
				y = in.nextInt();
				a[x] +=y;
				if(x>max){
					max = x;
				}else if(x<min){
					min=x;
				}
				
				if(x==0){
					flag++;
				}
		}
		in.close();
		StringBuffer str = new StringBuffer();
		int tmp = 0;
		for(int i=max;i>=0;i--){
			if(a[i]!=0){
				if(i==0){
					if(a[i]<0 || i==max){
						str.append(a[i]);
					}else{
						str.append("+"+a[i]);
					}
				}else if(i==1){
					if(i==max && a[i]==1){
						str.append("x");
					}else if(i==max && a[i]==-1){
						str.append("-x");
					}else if((a[i]<0 && a[i] != -1) || i==max){
						str.append(a[i]+"x");
					}else if(a[i]>0 && a[i] !=1){
						str.append("+"+a[i]+"x");
					}else if(a[i]==1){
						str.append("+x");
					}else{
						str.append("-x");
					}
				}else {
					if(i==max && a[i]==1){
//						str.append(i+"x");
						str.append("x"+i);
					}else if(i==max && a[i]==-1){
//						str.append("-"+i+"x");
						str.append("-"+"x"+i);
					}else if((a[i]<0 && a[i] != -1) || i==max){
						str.append(a[i]+"x"+i);
					}else if(a[i]>0 && a[i] !=1){
						str.append("+"+a[i]+"x"+i);
					}else if(a[i]==1){
//						str.append("+"+i+"x");
						str.append("+x"+i);
					}else{
//						str.append("-"+i+"x");
						str.append("-x"+i);
					}
				}
			
			}
			if(a[0]==0){
				str.append("+0");
			}
			
		}

		System.out.println(str.toString());
	}
}
