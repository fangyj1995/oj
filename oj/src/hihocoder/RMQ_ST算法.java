package hihocoder;
import java.io.*;
import java.text.ParseException;
import java.util.*;


public class RMQ_ST算法 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static int[][] preCal;
	static final int maxPower = 20;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();	
		preCal = new int[n+1][maxPower];
		//先初始化长度为1的区间.2de 0次方	
		for(int i = 1 ; i <= n ; i++)
			preCal[i][0] = nextInt();
		precal();	
		m = nextInt();
		for(int i = 1 ; i <= m; i++){
			int l = nextInt();
			int r = nextInt();
			
			int res = query(l,r);
			out.println(res);
			out.flush();
		}
	}
	private static void precal() {
		for(int len = 2, power = 1 ; len <= n ; len<<=1,power++){
			for(int i = 1 ; i+len-1<=n ; i++){
				int halfLen = (len>>1);
				preCal[i][power] = Math.min(preCal[i][power-1], preCal[i+halfLen][power-1]);
			}
		}
	}
	private static int query(int l, int r) {
		int len = r-l+1;
		int power = 0;
		while(len != 1){
			len >>= 1;
			power++;
		}
		len = (1 << power);
		int min = Math.min(preCal[l][power], preCal[r-len+1][power]);
		return min;
	}


}

/**
 * 
 * TLE..............
 * 
#1068 : RMQ-ST算法



时间限制:10000ms

单点时限:1000ms

内存限制:256MB


描述

小Hi和小Ho在美国旅行了相当长的一段时间之后，终于准备要回国啦！而在回国之前，他们准备去超市采购一些当地特产——比如汉堡（大雾）之类的回国。

但等到了超市之后，小Hi和小Ho发现者超市拥有的商品种类实在太多了——他们实在看不过来了！于是小Hi决定向小Ho委派一个任务：假设整个货架上从左到右拜访了N种商品，并且依次标号为1到N，每次小Hi都给出一段区间[L, R]，小Ho要做的是选出标号在这个区间内的所有商品重量最轻的一种，并且告诉小Hi这个商品的重量，于是他们就可以毫不费劲的买上一大堆东西了——多么可悲的选择困难症患者。

（虽然说每次给出的区间仍然要小Hi来进行决定——但是小Hi最终机智的选择了使用随机数生成这些区间！但是为什么小Hi不直接使用随机数生成购物清单呢？——问那么多做什么！）
 
提示一：二分法是宇宙至强之法！（真的么？）

提示二：线段树不也是二分法么？
 

输入

每个测试点（输入文件）有且仅有一组测试数据。

每组测试数据的第1行为一个整数N，意义如前文所述。

每组测试数据的第2行为N个整数，分别描述每种商品的重量，其中第i个整数表示标号为i的商品的重量weight_i。

每组测试数据的第3行为一个整数Q，表示小Hi总共询问的次数。

每组测试数据的第N+4~N+Q+3行，每行分别描述一个询问，其中第N+i+3行为两个整数Li, Ri，表示小Hi询问的一个区间[Li, Ri]。

对于100%的数据，满足N<=10^6，Q<=10^6, 1<=Li<=Ri<=N，0<weight_i<=10^4。

输出

对于每组测试数据，对于每个小Hi的询问，按照在输入中出现的顺序，各输出一行，表示查询的结果：标号在区间[Li, Ri]中的所有商品中重量最轻的商品的重量。
样例输入10
7334
1556
8286
1640
2699
4807
8068
981
4120
2179
5
3 4
2 8
2 4
6 8
7 10
样例输出1640
981
1556
981
981
*/
