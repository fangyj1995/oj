package hihocoder;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class 物品价值 {
	//static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static int[] a;
	static int[] value;
	static int[] pCnt;//物品拥有的属性数量
	static int[][] paras;
	
	public static void main(String[] args) throws IOException{	
		int cases = nextInt();
		for(int c = 0  ; c < cases; c++){
			n = nextInt();
			m = nextInt();
			value = new int[n+1];
			pCnt = new int[n+1];
			paras = new int[n+1][m+1];
			for(int i = 1 ; i <= n ; i++){
				value[i] = nextInt();
				pCnt[i] = nextInt();
				for(int j = 1 ; j <= pCnt[i] ; j++){
					paras[i][j] = nextInt();
				}
			}
			int [][] dp = new int[n+1][(1<<10)+1];			
			for(int i = 1; i <= n ; i++){
				for(int state = 0 ; state <= (1<<m)-1 ; state++){
					dp[i][state] = Integer.MIN_VALUE;
				}
			}
			int s = 0;
			for(int j = 1 ; j <= pCnt[1]; j++){
				s = s ^(1<<(paras[1][j]-1));
			}
			dp[1][s] = value[1];
			dp[0][0] = 0;
			dp[1][0] = 0;//前1件物品只有0和s两种状态，其他状态都不可能
			
			for(int i = 2; i <= n ; i++){
				for(int state = 0 ; state <= (1<<m)-1 ; state++){			
					int preState = state;//选第i件物品,留给前i-1件物品的状态			
					for(int j = 1 ; j <= pCnt[i]; j++){
						int para = paras[i][j];//枚举物品的每种属性
						//把这个属性对应的位取反,第一个属性对应最后一位
						preState = preState ^(1<<(para-1));
					}
					dp[i][state] = Math.max(dp[i-1][state], dp[i-1][preState]+value[i]);
					//System.out.println(i+" "+Integer.toBinaryString(state)+" "+dp[i][state]);
				}
			}
			
			System.out.println(dp[n][(1<<m)-1]);
		}	
	}
}



