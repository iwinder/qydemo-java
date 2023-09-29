package netease;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int[][] board = new int[num][num];
		boolean gotResult = false;
		int numOfX = 0;
		int numOfO = 0;
		
		for(int i=0;i<num;i++){
			for(int j=0;j<num;j++){
				board[i][j]=in.nextInt();
			}
		}
		in.close();
		//检查行
		for(int i=0;i<num;i++){
			numOfX = 0;
			numOfO = 0;
			for(int j=0;j<num;j++){
				if(board[i][j]==1){
					numOfX ++;
				}else{
					numOfO ++;
				}
			}
			if(numOfX ==num || numOfO == num){
				gotResult = true;
				break;
			}
		}
		//检查列
		if( !gotResult){

			for(int i=0;i<num;i++){
				numOfX = 0;
				numOfO = 0;
				for(int j=0;j<num;j++){
					if(board[j][i]==1){
						numOfX ++;
					}else{
						numOfO ++;
					}
				}
				if(numOfX ==num || numOfO == num){
					gotResult = true;
					break;
				}
			}
		}
		//检查对角线_左上右下
		if( !gotResult){
			numOfX = 0;
			numOfO = 0;
			for(int i=0;i<num;i++){
				if(board[i][i]==1){
					numOfX ++;
				}else{
					numOfO ++;
				}
			}
			if(numOfX ==num || numOfO == num){
				gotResult = true;
			}
		}
		//检查对角线_左下右上
		if( !gotResult){
			numOfX = 0;
			numOfO = 0;
			int tmp = num-1;
			for(int i=0;i<num;i++){
				if(board[i][tmp-i]==1){
					numOfX ++;
				}else{
					numOfO ++;
				}
			}
			if(numOfX ==num || numOfO == num){
				gotResult = true;
			}
		}
		if(gotResult){
			if(numOfX == num){
				System.out.println("X");
			}else{
				System.out.println("O");
			}
		}else{
			System.out.println("NIL");
		}
		
	}
}
