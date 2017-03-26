package hihocoder;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class 状态压缩一 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	static int n,m,q;
	static int[] weights;
	static int[][] dp;
	static int maxM;
	public static void main(String[] args) throws IOException, ParseException {
	  	n = nextInt();
	  	m = nextInt();
	  	q = nextInt();
	  	
	  	weights = new int[n+1];  	
	  	for(int i = 1 ; i <= n ; i++)
	  		weights[i] = nextInt();
	  	
	  	int M = 1<<m;//用0 ~ M-1 的数来表示m个位置的选取状态
	  	int[] bits = new int[M];//统计每个0~M-1的数有多少个比特
	  	bits[0] = 0;
	  	int have = 1;
	  	while(have < M){
	  		for(int i = 0 ; i < have ; i++){
	  			bits[have + i] = bits[i] + 1;
	  		}
	  		have <<= 1;
	  	}
	  	
	  	int S = n-m+1;
	  	dp = new int[S+1][M+1];//dp[i][j]代表：i前面的位置状态都已经确定了且在i以及后面m-1个位置的选取状态用j表示的情况下，可以获得的最大价值
	  	//确定初始状态，即第一个位置的状态
	  	for(int state = 0 ; state < M ; state++){//state的二进制表示可以看成是是一个m位的数
	  		if(bits[state] <= q){
	  			for(int pos = 1 ; pos <= m ; pos++){//统计i有多少个比特，表示m个位置有多少被选了，加上每个被选中位置的权值
	  				if((state & 1<<(m-pos)) > 0){
	  					dp[1][state] += weights[pos];
	  				}
	  			}
	  		}
	  	} 	
	  	//从第二个位置的状态开始推
	  	for(int i = 2 ; i <= S ; i++){//枚举每个位置
	  		for(int state = 0 ; state < M ; state++){//枚举状态
	  			if(bits[state] <= q){//这个状态是可行的	
	  				//右移，留一个位置给i
	  				dp[i][state] = dp[i-1][state >> 1];
	  				if(bits[(state >> 1) + (1 << m-1)] <= q)//留出的位置可以填1
	  					dp[i][state] =  Math.max(dp[i][state], dp[i-1][(state >> 1) + (1 << m-1)]);//这里有诡异的优先级 ，加减法优先级大于移位操作			
	  				if((state & 1) > 0)
	  					dp[i][state] += weights[i+m-1];
	  			}
	  		}
	  	}
	  	
	  	int max = dp[S][0];
	  	for(int i = 1; i < M; i++){
	  		max = Math.max(max, dp[S][i]);
	  	}
	  	System.out.println(max);
	}
}
/**
 * 
描述

小Hi和小Ho在兑换到了喜欢的奖品之后，便继续起了他们的美国之行，思来想去，他们决定乘坐火车前往下一座城市——那座城市即将举行美食节！

但是不幸的是，小Hi和小Ho并没有能够买到很好的火车票——他们只能够乘坐最为破旧的火车进行他们的旅程。

不仅如此，因为美食节的吸引，许多人纷纷踏上了和小Hi小Ho一样的旅程，于是有相当多的人遭遇到了和小Hi小Ho一样的情况——这导致这辆车上的人非常非常的多，以至于都没有足够的位置能让每一个人都有地方坐下来。

小Hi和小Ho本着礼让他们的心情——当然还因为本来他们买的就是站票，老老实实的呆在两节车厢的结合处。他们本以为就能够这样安稳抵达目的地，但事与愿违，他们这节车厢的乘务员是一个强迫症，每隔一小会总是要清扫一次卫生，而时值深夜，大家都早已入睡，这种行为总是会惊醒一些人。而一旦相邻的一些乘客被惊醒了大多数的话，就会同乘务员吵起来，弄得大家都睡不好。

将这一切看在眼里的小Hi与小Ho决定利用他们的算法知识，来帮助这个有着强迫症的乘务员——在不与乘客吵起来的前提下尽可能多的清扫垃圾。

小Hi和小Ho所处的车厢可以被抽象成连成一列的N个位置，按顺序分别编号为1..N，每个位置上都有且仅有一名乘客在休息。同时每个位置上都有一些垃圾需要被清理，其中第i个位置的垃圾数量为Wi。乘务员可以选择其中一些位置进行清理，但是值得注意的是，一旦有编号连续的M个位置中有超过Q个的位置都在这一次清理中被选中的话（即这M个位置上的乘客有至少Q+1个被惊醒了），就会发生令人不愉快的口角。而小Hi和小Ho的任务是，计算选择哪些位置进行清理，在不发生口角的情况下，清扫尽可能多的垃圾。

提示一：无论是什么动态规划，都需要一个状态转移方程！

提示二：好像什么不对劲？状态压缩哪里去了？
 
 输入

每个测试点（输入文件）有且仅有一组测试数据。

每组测试数据的第一行为三个正整数N、M和Q，意义如前文所述。

每组测试数据的第二行为N个整数，分别为W1到WN，代表每一个位置上的垃圾数目。

对于100%的数据，满足N<=1000, 2<=M<=10,1<=Q<=M, Wi<=100

输出

对于每组测试数据，输出一个整数Ans，表示在不发生口角的情况下，乘务员最多可以清扫的垃圾数目。
样例输入
5 2 1
36 9 80 69 85 
样例输出
201

 */
