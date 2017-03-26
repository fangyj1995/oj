package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;


public class _1079_离散化_线段树 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static SegTree root;
	static int l;
	static int[][] post;
	static int[] points;
	static int cnt = 0;
	static HashMap<Integer,Integer> map;
	
	static class SegTree{
		int l;
		int r;
		SegTree left;
		SegTree right;
		int tag;
		public SegTree(int l, int r) {			
			if(r <= l) throw new RuntimeException("l:"+l+" , r:"+r);			
			this.l = l;
			this.r = r;
			this.tag = -1;
		}
		@Override
		public String toString() {
			return " [l=" + l + ", r=" + r + ", min=]"; 
		}	
		
	}
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();	
		l = nextInt();
		post = new int[n+1][2];
		points = new int[2*n+1];
		for(int i = 1 ; i <= n; i++){
			post[i][0] = nextInt();
			post[i][1] = nextInt();
			points[2*i-1] = post[i][0];
			points[2*i] = post[i][1];
		}
		work();
		out.flush();
	}
	private static void work() {
		Arrays.sort(points);
		//System.out.println(Arrays.toString(points));
		map = new HashMap<>();	
		map.put(points[1], cnt++);
		for(int i = 2 ; i <= 2*n ; i++){
			if(points[i] != points[i-1])
				map.put(points[i], cnt++);
		}
		//System.out.println(map);
		//points = null;
		buildTree(cnt);
		for(int i = 1; i<= n; i++){
			int l = map.get(post[i][0]);
			int r = map.get(post[i][1]);
			insert(l,r,i);
		}
		HashSet<Integer> set = new HashSet<>();
		collect(set);
		System.out.println(set.size());
	}
	private static void buildTree(int n) {
		root = buildTree(0,n);
	}
	private static SegTree buildTree(int l, int r) {
		SegTree tree = new SegTree(l,r);
		if(l+1 == r) {
			return tree;
		}	
		int mid = l + (r-l)/2;
		tree.left = buildTree(l,mid);
		tree.right = buildTree(mid,r);
		return tree;
	}
	private static void insert(int l,int r, int id) {
		insert(root,l,r,id);
	}

	private static void insert(SegTree tree, int l,int r,int id) {
		if(tree == null)	
			return;
		//插入的这张海报覆盖了这个区间
		if(tree.r == r && tree.l == l){
			tree.tag = id; 
			return;
		}
		//插入的过程中遇到已经有海报的节点，将覆盖标记下放
		if(tree.tag != -1 && tree.left!=null && tree.right!=null){
			tree.left.tag = tree.tag;
			tree.right.tag = tree.tag;			
			tree.tag = -1;
		}
		
		int mid = tree.l + (tree.r-tree.l)/2;
		if(r <= mid){//go left
			insert(tree.left,l,r,id);			
		}
		else if(l >= mid){//go right
			insert(tree.right,l,r,id);
		}
		else{//split
			insert(tree.left,l,mid,id);
			insert(tree.right,mid,r,id);
		}		
	}
	private static void collect(Set<Integer> set) {
		collect(root,set);
	}
	private static void collect(SegTree tree, Set<Integer> set) {
		if(tree == null)
			return;
		if(tree.tag != -1)
		{
			set.add(tree.tag);
			return;
		}
		if(tree.l+1 == tree.r)
			return;
		collect(tree.left,set);
		collect(tree.right,set);
	}
}
/*
 * 
#1079 : 离散化

 


时间限制:10000ms

单点时限:1000ms

内存限制:256MB


描述

小Hi和小Ho在回国之后，重新过起了朝7晚5的学生生活，当然了，他们还是在一直学习着各种算法~

这天小Hi和小Ho所在的学校举办社团文化节，各大社团都在宣传栏上贴起了海报，但是贴来贴去，有些海报就会被其他社团的海报所遮挡住。看到这个场景，小Hi便产生了这样的一个疑问——最后到底能有几张海报还能被看见呢？

于是小Ho肩负起了解决这个问题的责任：因为宣传栏和海报的高度都是一样的，所以宣传栏可以被视作长度为L的一段区间，且有N张海报按照顺序依次贴在了宣传栏上，其中第i张海报贴住的范围可以用一段区间[a_i, b_i]表示，其中a_i, b_i均为属于[0, L]的整数，而一张海报能被看到当且仅当存在长度大于0的一部分没有被后来贴的海报所遮挡住。那么问题就来了：究竟有几张海报能被看到呢？
 
提示一：正确的认识信息量



提示二：小Hi大讲堂之线段树的节点意义

 输入

每个测试点（输入文件）有且仅有一组测试数据。

每组测试数据的第1行为两个整数N和L，分别表示总共贴上的海报数量和宣传栏的宽度。

每组测试数据的第2-N+1行，按照贴上去的先后顺序，每行描述一张海报，其中第i+1行为两个整数a_i, b_i，表示第i张海报所贴的区间为[a_i, b_i]。

对于100%的数据，满足N<=10^5，L<=10^9，0<=a_i<b_i<=L。

输出

对于每组测试数据，输出一个整数Ans，表示总共有多少张海报能被看到。
样例输入5 10
4 10
0 2
1 6
5 9
3 4
样例输出5

 */


