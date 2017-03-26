package other;

import java.io.*;
import java.util.Arrays;

/**
 * 
小易来到了一条石板路前，每块石板上从1挨着编号为：1、2、3……. 
这条石板路要根据特殊的规则才能前进：对于小易当前所在的编号为K的 石板，小易单次只能往前跳K的一个约数(不含1和K)步，即跳到K+X(X为K的一个非1和本身的约数)的位置。 小易当前处在编号为N的石板，他想跳到编号恰好为M的石板去，小易想知道最少需要跳跃几次可以到达。 
 例如： 
N = 4，M = 24： 
4->6->8->12->18->24 
于是小易最少需要跳跃5次，就可以从4号石板跳到24号石板 
输入描述: 

输入为一行，有两个整数N，M，以空格隔开。
(4 ≤ N ≤ 100000)
(N ≤ M ≤ 100000)1
2
3


1
2
3

输出描述:

输出小易最少需要跳跃的步数,如果不能到达输出-11


1

输入例子:

4 241


1

输出例子:

5
 * @author fangyj
 *
 */

public class 跳石板 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;

	public static void main(String[] args) throws IOException{	
		n = nextInt();
		m = nextInt();
		int res1 = jump(n,m);
		System.out.println(res1);
	}
	private static int jump(int n, int m){
		int dp[] = new int[m+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[m] = 0;
		for(int i = m-1 ; i >= n ; i--){	
			for(int k = 2 ; k <= Math.sqrt(i) && k <= m-i; k++){			
				if(i%k == 0)
				{
					if(dp[i+k] != Integer.MAX_VALUE)
						dp[i] = Math.min(dp[i], dp[i+k] + 1);
					if(i/k <= m-i && dp[i+i/k] != Integer.MAX_VALUE)
						dp[i] = Math.min(dp[i], dp[i+i/k] + 1);
				}
			}
		}
		if(dp[n] == Integer.MAX_VALUE)
			return -1;
		else
			return dp[n];
	}

}



