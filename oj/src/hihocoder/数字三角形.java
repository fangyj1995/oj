package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.Arrays;

public class 数字三角形 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int[][] map;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		map = new int[n+1][n+1];
		int[][] best = new int[n+1][n+1];
		for(int i = 1 ; i <= n ; i++){
			Arrays.fill(best[i], -1);
			for(int j = 1; j<= i ; j++){
				map[i][j] = nextInt();
			}
		}
		int res = dfs(1,1,best);
		dfs(1,1,0,best);
		System.out.println(res);
		//System.out.println(best[n][n]);
	}
	//从i,j出发走出迷宫能收集的最大奖券数
	public static int dfs(int i,int j,int[][] best){
		if(best[i][j] != -1)
			return best[i][j];
		int prize = map[i][j];
		if(i == n)//到了迷宫的终点，递归返回
		{
			best[i][j] = prize;
			return prize;
		}
		//加上两条子路中的最大值
		prize += Math.max(dfs(i+1,j+1,best), dfs(i+1,j,best));
		//记忆化找到的最优解
		best[i][j] = prize;
		return prize;
	}
	//sum,best表示从起点走到i,j收集到的最大奖券数
	public static int dfs(int i , int j , int sum , int[][] best){
		if(sum < best[i][j])
			return best[i][j];
		sum += map[i][j];
		if(i == n)//到了迷宫的终点，递归返回
		{
			best[i][j] = sum;
			return sum;
		}
		best[i][j] = sum;
		dfs(i+1,j+1,sum,best);
		dfs(i+1,j,sum,best);
		return sum;
	}

}
/**
 * 
#1037 : 数字三角形

 


时间限制:10000ms

单点时限:1000ms

内存限制:256MB


问题描述

小Hi和小Ho在经历了螃蟹先生的任务之后被奖励了一次出国旅游的机会，于是他们来到了大洋彼岸的美国。美国人民的生活非常有意思，经常会有形形色色、奇奇怪怪的活动举办，这不，小Hi和小Ho刚刚下飞机，就赶上了当地的迷宫节活动。迷宫节里展览出来的迷宫都特别的有意思，但是小Ho却相中了一个其实并不怎么像迷宫的迷宫——因为这个迷宫的奖励非常丰富~

于是小Ho找到了小Hi，让小Hi帮助他获取尽可能多的奖品，小Hi把手一伸道：“迷宫的介绍拿来！”

小Ho选择的迷宫是一个被称为“数字三角形”的n(n不超过200)层迷宫，这个迷宫的第i层有i个房间，分别编号为1..i。除去最后一层的房间，每一个房间都会有一些通往下一层的房间的楼梯，用符号来表示的话，就是从第i层的编号为j的房间出发会有两条路，一条通向第i+1层的编号为j的房间，另一条会通向第i+1层的编号为j+1的房间，而最后一层的所有房间都只有一条离开迷宫的道路。这样的道路都是单向的，也就是说当沿着这些道路前往下一层的房间或者离开迷宫之后，小Ho没有办法再次回到这个房间。迷宫里同时只会有一个参与者，而在每个参与者进入这个迷宫的时候，每个房间里都会生成一定数量的奖券，这些奖券可以在通过迷宫之后兑换各种奖品。小Ho的起点在第1层的编号为1的房间，现在小Ho悄悄向其他参与者弄清楚了每个房间里的奖券数量，希望小Hi帮他计算出他最多能获得多少奖券。

提示一：盲目贪心不可取，搜索计算太耗时

提示二：记忆深搜逞神威，宽度优先解难题

提示三：总结归纳提公式，减少冗余是真理

输入

每个测试点（输入文件）有且仅有一组测试数据。

每组测试数据的第一行为一个正整数n,表示这个迷宫的层数。

接下来的n行描述这个迷宫中每个房间的奖券数，其中第i行的第j个数代表着迷宫第i层的编号为j的房间中的奖券数量。

测试数据保证，有100%的数据满足n不超过100

对于100%的数据，迷宫的层数n不超过100

对于100%的数据，每个房间中的奖券数不超过1000

对于50%的数据，迷宫的层数不超过15（小Ho表示2^15才3万多呢，也就是说……）

对于10%的数据，迷宫的层数不超过1（小Hi很好奇你的边界情况处理的如何？~）

对于10%的数据，迷宫的构造满足：对于90%以上的结点，左边道路通向的房间中的奖券数比右边道路通向的房间中的奖券数要多。

对于10%的数据，迷宫的构造满足：对于90%以上的结点，左边道路通向的房间中的奖券数比右边道路通向的房间中的奖券数要少。

输出

对于每组测试数据，输出一个整数Ans，表示小Ho可以获得的最多奖券数。
样例输入5
2
6 4
1 2 8
4 0 9 6
6 5 5 3 6
样例输出28

 */
