package hihocoder;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class 岛屿3 {
	static class UnionFind {
		int[] id ;
		int[] size;
		int cnt = 0;
		public UnionFind(int n){
			id = new int[n+1];
			size = new int[n+1];
			for(int i = 0 ; i < n ; i++){
				id[i] = i;
				size[i] = 1;
			}
			cnt = n;
		}
		public  boolean connected(int p, int q) {	
			return findRoot(p) == findRoot(q);
		}
		public  int findRoot(int p){
			while(id[p] != p){
				p = id[p];
			}
			return p;
		}
		public  boolean union(int p, int q) {
			int pRoot = findRoot(p);
			int qRoot = findRoot(q);
			if(pRoot == qRoot) return false;
			
			assert( (id[pRoot] == pRoot) &(id[qRoot] == qRoot));
			if(size[pRoot]>size[qRoot]){//q并入p
				id[qRoot] = pRoot;
				size[qRoot]++;
			}
			else{//p并入q
				id[pRoot] = qRoot;
				size[pRoot]++;
			}
			cnt--;
			return true;
		}
	}
	//static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int[] row;
	static int[] col;
	static int[][] dir = new int[][]{
			{1,0},
			{-1,0},
			{0,1},
			{0,-1}
	};
	static boolean[][] map;
	public static void main(String[] args) throws IOException{	
		n = nextInt();
		row = new int[n+1];
		col = new int[n+1];
		map = new boolean[1001][1001];
		for(int i = 1 ; i <= n; i++){
			row[i] = nextInt();
			col[i] = nextInt();
		}
		int N = 1000*1000;
		UnionFind uf = new UnionFind(N);
		int cnt = 0;
		int area = 0;
		int circle = 0;
		
		for(int i = 1 ; i <= n; i++){
			int id1 = id(row[i],col[i]);
			if(map[row[i]][col[i]]) 
			{
				System.out.println(cnt +" "+ area+" "+circle);
				continue;
			}
					
			map[row[i]][col[i]] = true;		
			int adj = 0;
			int unionCnt = 0;
	
			for(int d = 0 ; d < 4; d++){
				int r = row[i] + dir[d][0];
				int c = col[i] + dir[d][1];
				if(!check(r,c)) continue;	
				if(map[r][c])
				{
					int id2 = id(r,c);
					if(uf.union(id1, id2)){
						unionCnt++;
					}
					adj++;
				}		
			}		
			area++;
			cnt++;
			cnt -= unionCnt;			
			circle = circle - adj+(4-adj);
			System.out.println(cnt +" "+ area+" "+circle);
		}
	}
	public static boolean check(int r,int c){
		return r >= 0 && r <1000 && c >= 0 && c < 1000;
	}
	public static int id(int r,int c){
		return r*1000 + c;
	}
}



