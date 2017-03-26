package hihocoder;


import java.io.*;
import java.text.ParseException;
import java.util.*;


public class RMQ问题再临_线段树 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static SegTree root;
	static int[] weights;
	
	static class SegTree{
		int l;
		int r;
		int min;
		SegTree left;
		SegTree right;
		public SegTree(int l, int r) {
			super();
			this.l = l;
			this.r = r;
		}
		@Override
		public String toString() {
			return " [l=" + l + ", r=" + r + ", min=" + min + "]";
		}	
		
	}
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();	
		weights = new int[n+1];	
		for(int i = 1 ; i <= n ; i++)
			weights[i] = nextInt();
		buildTree(n);
		m = nextInt();
		for(int i = 1 ; i <= m; i++){
			if(nextInt() == 0)//query
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
	private static void buildTree(int n) {
		root = buildTree(1,n);
	}
	private static SegTree buildTree(int l, int r) {
		SegTree tree = new SegTree(l,r);
		if(l==r) {
			tree.min = weights[l];
			return tree;
		}	
		int mid = l + (r-l)/2;
		tree.left = buildTree(l,mid);
		tree.right = buildTree(mid+1,r);
		tree.min = Math.min(tree.left.min, tree.right.min);
		return tree;
	}
	private static void alter(int index, int weight) {
		weights[index] = weight;
		alter(root,index, weight);
	}

	private static void alter(SegTree tree, int index, int weight) {
		if(tree == null)	
			return;
		if(tree.l == tree.r && tree.l == index){
			tree.min = weight;
			return;
		}
		int mid = tree.l + (tree.r-tree.l)/2;
		if(index <= mid)
			alter(tree.left,index,weight);
		else
			alter(tree.right,index,weight);
		tree.min = Math.min(tree.left.min, tree.right.min);
	}
	private static int query(int l, int r) {
		return query(root,l,r);
	}
	private static int query(SegTree tree, int l, int r) {
		int min = Integer.MAX_VALUE;
		if(tree == null)
			return min;
		if(l == tree.l && r == tree.r)
			return tree.min;
		
		int mid = tree.l +(tree.r-tree.l)/2;//[l,mid][mid+1,r]		
		if(r <= mid){//go left
			min = Math.min(min, query(tree.left,l,r));
		}
		else if(l >= mid+1){//go right
			min = Math.min(min, query(tree.right,l,r));
		}
		else{//split
			min = Math.min(min, Math.min(query(tree.left,l,mid), query(tree.right,mid+1,r)));
		}
		return min;
	}


}

