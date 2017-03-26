package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;


public class RMQ问题再临 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static int[][] minium;
	static final int maxPower = 14;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();	
		minium = new int[n+1][maxPower];
		//先初始化长度为1的区间.2de 0次方	
		for(int i = 1 ; i <= n ; i++)
			minium[i][0] = nextInt();
		precal();	
		m = nextInt();
		for(int i = 1 ; i <= m; i++){
			if(nextInt() == 0)
			{
				int l = nextInt();
				int r = nextInt();			
				int res = query(l,r);
				out.println(res);
			}
			else{
				int index = nextInt();
				int weight = nextInt();
				alter(index,weight);
				
			}			
			out.flush();
		}
	}
	private static void alter(int index, int weight) {
		minium[index][0] = weight; 
		for(int len = 2 ,power = 1; len <= n ; len = (1 << ++power)){
			int i = Math.max(1, index - len + 1);
			for(; i <= index && i + len-1 <= n; i++){
				int halfLen = (len>>1);
				minium[i][power] = Math.min(minium[i][power-1], minium[i+halfLen][power-1]);
			}
		}
		
	}
	private static void precal() {
		for(int len = 2 ,power = 1; len <= n ; len = (1 << ++power)){
			for(int i = 1 ; i+len-1<=n ; i++){
				int halfLen = (len>>1);
				minium[i][power] = Math.min(minium[i][power-1], minium[i+halfLen][power-1]);
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
		int min = Math.min(minium[l][power], minium[r-len+1][power]);
		return min;
	}


}
/**
 * 
#1070 : RMQ问题再临

 


时间限制:10000ms

单点时限:1000ms

内存限制:256MB


描述

终于，小Hi和小Ho踏上了回国的旅程。在飞机上，望着采购来的特产——小Hi陷入了沉思：还记得在上上周他们去超市的时候，前前后后挑了那么多的东西，都幸运的没有任何其他人（售货员/其他顾客）来打搅他们的采购过程。但是如果发生了这样的事情，他们的采购又会变得如何呢？

于是小Hi便向小Ho提出了这个问题：假设整个货架上从左到右摆放了N种商品，并且依次标号为1到N，每次小Hi都给出一段区间[L, R]，小Ho要做的是选出标号在这个区间内的所有商品重量最轻的一种，并且告诉小Hi这个商品的重量。但是在这个过程中，可能会因为其他人的各种行为，对某些位置上的商品的重量产生改变（如更换了其他种类的商品），面对这样一个问题，小Ho又该如何解决呢？
 
提示：平衡乃和谐之理
 
输入

每个测试点（输入文件）有且仅有一组测试数据。

每组测试数据的第1行为一个整数N，意义如前文所述。

每组测试数据的第2行为N个整数，分别描述每种商品的重量，其中第i个整数表示标号为i的商品的重量weight_i。

每组测试数据的第3行为一个整数Q，表示小Hi总共询问的次数与商品的重量被更改的次数之和。

每组测试数据的第N+4~N+Q+3行，每行分别描述一次操作，每行的开头均为一个属于0或1的数字，分别表示该行描述一个询问和描述一次商品的重量的更改两种情况。对于第N+i+3行，如果该行描述一个询问，则接下来为两个整数Li, Ri，表示小Hi询问的一个区间[Li, Ri]；如果该行描述一次商品的重量的更改，则接下来为两个整数Pi，Wi，表示位置编号为Pi的商品的重量变更为Wi

对于100%的数据，满足N<=10^4，Q<=10^4, 1<=Li<=Ri<=N，1<=Pi<=N, 0<weight_i, Wi<=10^4。

输出

对于每组测试数据，对于每个小Hi的询问，按照在输入中出现的顺序，各输出一行，表示查询的结果：标号在区间[Li, Ri]中的所有商品中重量最轻的商品的重量。
样例输入10
618 5122 1923 8934 2518 6024 5406 1020 8291 2647 
6
0 3 6
1 2 2009
0 2 2
0 2 10
1 1 5284
0 2 5
样例输出1923
2009
1020
1923

 * 
 */

