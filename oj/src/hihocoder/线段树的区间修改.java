package hihocoder;


import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 线段树的区间修改 {
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
		int sum;
		SegTree left;
		SegTree right;
		int tag;
		public SegTree(int l, int r) {
			super();
			this.l = l;
			this.r = r;
			this.tag = -1;
		}
		@Override
		public String toString() {
			return " [l=" + l + ", r=" + r + ", min=" + sum + "]";
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
				int l = nextInt();
				int r = nextInt();
				int weight = nextInt();
				alter(l,r,weight);			
			}			
		}
		out.flush();
	}
	private static void buildTree(int n) {
		root = buildTree(1,n);
	}
	private static SegTree buildTree(int l, int r) {
		SegTree tree = new SegTree(l,r);
		if(l==r) {
			tree.sum = weights[l];
			return tree;
		}	
		int mid = l + (r-l)/2;
		tree.left = buildTree(l,mid);
		tree.right = buildTree(mid+1,r);
		tree.sum =  tree.left.sum + tree.right.sum;
		return tree;
	}
	private static void alter(int l,int r, int weight) {
		alter(root,l,r, weight);
	}

	private static void alter(SegTree tree, int l,int r, int weight) {
		if(tree == null)	
			return;
		if(tree.r == r && tree.l == l){
			tree.sum = weight*(r-l+1);
			tree.tag = weight; 
			return;
		}
		
		if(tree.tag != -1 && tree.left!=null && tree.right!=null){
			tree.left.tag = tree.tag;
			tree.right.tag = tree.tag;
			tree.left.sum = (tree.left.r-tree.left.l+1)*tree.left.tag;
			tree.right.sum = (tree.right.r-tree.right.l+1)*tree.right.tag;		
			tree.tag = -1;
		}
		int mid = tree.l + (tree.r-tree.l)/2;
		if(r <= mid){//go left
			alter(tree.left,l,r,weight);			
		}
		else if(l >= mid+1){//go right
			alter(tree.right,l,r,weight);
		}
		else{//split
			alter(tree.left,l,mid,weight);
			alter(tree.right,mid+1,r,weight);
		}		
		tree.sum = tree.left.sum + tree.right.sum;
	}
	private static int query(int l, int r) {
		return query(root,l,r);
	}
	private static int query(SegTree tree, int l, int r) {
		if(tree == null)
			return 0;
		if(l == tree.l && r == tree.r)
			return tree.sum;
		if(tree.tag != -1 && tree.left!=null && tree.right!=null){
			tree.left.tag = tree.tag;
			tree.right.tag = tree.tag;
			tree.left.sum = (tree.left.r-tree.left.l+1)*tree.left.tag;
			tree.right.sum = (tree.right.r-tree.right.l+1)*tree.right.tag;		
			tree.tag = -1;
		}
		int mid = tree.l +(tree.r-tree.l)/2;//[l,mid][mid+1,r]		
		if(r <= mid){//go left
			return query(tree.left,l,r);
		}
		else if(l >= mid+1){//go right
			return query(tree.right,l,r);
		}
		else{//split
			return query(tree.left,l,mid)+query(tree.right,mid+1,r);
		}
	}


}


