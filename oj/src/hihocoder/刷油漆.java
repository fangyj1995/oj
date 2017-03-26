package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 刷油漆 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static LinkedList<Integer>[] adj;
	static int res;
	static int[] value;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		m = nextInt();
		adj = new LinkedList[n+1];
		value = new int[n+1];
		for(int i = 1 ; i <= n ; i++)
			value[i] = nextInt();
		for(int i = 1 ; i<= n ; i++){
			adj[i] = new LinkedList<Integer>();
		}
		for(int i = 1 ; i<= n-1 ; i++){	
			int v = nextInt();
			int w = nextInt();
			adj[v].add(w);
			adj[w].add(v);
		}

		int res = slove();
		System.out.println(res);
	}
	private static int slove() {	
		int[][] dp = new int[n+1][n+1];
		for(int i = 1 ; i<= n ; i++){
			Arrays.fill(dp[i], -1);
			dp[i][0] = 0;
			dp[i][1] = value[i];
		}
		dfs(1,1,dp,m);
		return dp[1][m];
	}
	//在以cur为根节点的树中选m个节点能达到的最大价值
	private static void dfs(int pre,int cur,int[][] dp,int m){	
		if(dp[cur][m] != -1)
			return;		
		for(int next : adj[cur]){
			if(next == pre) continue;
			dfs(cur,next,dp,m-1);//子树最多能选m-1个节点		
			for(int i = m; i >= 2 ; i--){//从大到小推
				for(int j = 1; j < i ; j++){
					//在next子树中选j个，在next前所有的子树中选i-j个，每个子树都可以选或者不选，01？多重？
					dp[cur][i] = Math.max(dp[cur][i],dp[next][j]+dp[cur][i-j]);
					//dp[next][next.childs][j]+dp[cur][next-1][i-j];
				}
				//这里求出来的dp其实是只考虑当前子节点及之前的值
				//dp[i][j][k];在以i为根节点的树中对前j个子节点中选k个节点（包括i）能达到的最大价值
			}
		}		
	}
}
/**
 * 
#1055 : 刷油漆

 


时间限制:10000ms

单点时限:1000ms

内存限制:256MB


描述

上回说到，小Ho有着一棵灰常好玩的树玩具！这棵树玩具是由N个小球和N-1根木棍拼凑而成，这N个小球都被小Ho标上了不同的数字，并且这些数字都是处于1..N的范围之内，每根木棍都连接着两个不同的小球，并且保证任意两个小球间都不存在两条不同的路径可以互相到达。没错，这次说的还是这棵树玩具的故事！

小Ho的树玩具的质量似乎不是很好，短短玩了几个星期，便掉漆了！

“简直是一场噩梦！”小Ho拿着树玩具眼含热泪道。

“这有什么好忧伤的，自己买点油漆刷一刷不就行了？”小Hi表示不能理解。

“还可以这样？”小Ho顿时兴高采烈了起来，立马跑出去买回来了油漆，但是小Ho身上的钱却不够——于是他只买回了有限的油漆，这些油漆最多能给M个结点涂上颜色，这就意味着小Ho不能够将他心爱的树玩具中的每一个结点都涂上油漆！

小Ho低头思索了半天——他既不想只选一部分结点补漆，也不想找小Hi借钱，但是很快，他想出了一个非常棒的主意：将包含1号结点的一部分连通的结点进行涂漆（这里的连通指的是这一些涂漆的结点可以互相到达并且不会经过没有涂漆的结点），然后将剩下的结点拆掉！

那么究竟选择哪些结点进行涂漆呢？小Ho想了想给每个结点都评上了分——他希望最后留下来，也就是涂漆了的那些结点的评分之和可以尽可能的高！

那么，小Ho该如何做呢？

提示一：树上的动态规划？其实老早就接触过了吧！

输入

每个测试点（输入文件）有且仅有一组测试数据。

每组测试数据的第一行为两个整数N、M，意义如前文所述。

每组测试数据的第二行为N个整数，其中第i个整数Vi表示标号为i的结点的评分

每组测试数据的第3~N+1行，每行分别描述一根木棍，其中第i+1行为两个整数Ai，Bi，表示第i根木棍连接的两个小球的编号。

对于100%的数据，满足N<=10^2，1<=Ai<=N, 1<=Bi<=N, 1<=Vi<=10^3, 1<=M<=N 

小Hi的Tip：那些用数组存储树边的记得要开两倍大小哦！

输出

对于每组测试数据，输出一个整数Ans，表示使得涂漆结点的评分之和最高可能是多少。

样例输入10 4
370 328 750 930 604 732 159 167 945 210 
1 2
2 3
1 4
1 5
4 6
4 7
4 8
6 9
5 10
样例输出2977

 * 
 */
