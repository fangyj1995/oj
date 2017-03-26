package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class 奖券兑换 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n,m;
	static int[] cost;
	static int[] value;
	static int[] dp;
	static int[][] pack;
	public static void main(String[] args) throws IOException, ParseException {
	  	n = nextInt();
	  	m = nextInt();
	  	cost = new int[110];
	  	value = new int[110];
	  	dp = new int[m+1];
	  	pack = new int[11][11];//记录一个cost,value组合出现的次数，看做同一件商品,转化为多重背包问题  	
	  	for(int i = 1 ; i <= n ; i++){
	  		int cost = nextInt();
	  		int value = nextInt();
	  		pack[cost][value]++;
	  	}
	  	int cnt = 0;
	  	for(int i = 1; i <= 10 ; i++){
	  		for(int j = 1; j <= 10; j++){
	  			if(pack[i][j]!=0){
	  				cnt++;
	  				cost[cnt] = i;
	  				value[cnt] = j;  				
	  			}
	  		}
	  	}
	  	for(int i = 1; i<= cnt; i++){//一共有cnt件物品  	
	  		if(pack[cost[i]][value[i]] * cost[i] >= m){//这种物品的代价*数量超过了限制
	  			for(int j = cost[i] ; j <= m; j++){//完全背包	  			
		  			dp[j] = Math.max(dp[j], dp[j-cost[i]]+value[i]);
		  		}
	  		}
	  		else{
	  			int num = pack[cost[i]][value[i]];
	  			int k = 1;
	  			while(num >= k){//将num按二进制分解成多个可以0,1背包的物品
	  				int c = k*cost[i];
	  				int v = k*value[i];
	  				for(int j = m ; j >= c; j--){
		  				dp[j] = Math.max(dp[j], dp[j-c]+v);
		  			}
	  				num -= k;
	  				k = k<<1;
	  			}
	  			//再来一次0,1背包
	  			if(num > 0){
	  				int c = num*cost[i];
	  				int v = num*value[i];
		  			for(int j = m ; j >= c; j--){
		  				dp[j] = Math.max(dp[j], dp[j-c]+v);
		  			}
	  			}
	  		}  		
	  	}
	  	System.out.println(dp[m]);
	}
}
/*
 * 
描述

小Hi在游乐园中获得了M张奖券，这些奖券可以用来兑换奖品。

可供兑换的奖品一共有N件。第i件奖品需要Wi张奖券才能兑换到，其价值是Pi。  

小Hi使用不超过M张奖券所能兑换到的最大奖品总价值是多少？ 

输入

第一行两个整数N，M。  

接下来N行，每行两个整数Wi，Pi。  

对于 50%的数据： 1≤N,M≤1000  

对于 100%的数据： 1≤N,M≤105,1≤Pi,Wi≤10。  

输出

一行一个整数，表示最大的价值。
样例输入3 10  
2 3  
8 8   
10 10
样例输出11 

01背包转多重背包加二进制优化
 */