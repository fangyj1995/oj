package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;



public class 最小生成树二_Kruscal算法 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	
	static class Edge implements Comparable<Edge>{
		int v1;
		int v2;
		int weight;
		public Edge(int v,int w,int weight){
			this.v1 = v;
			this.v2 = w;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			return "Edge [v=" + v1 + ", w=" + v2 + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}	
	static class UF{
		int[] id ;
		int[] size;
		public UF(int n){
			id = new int[n+1];
			size = new int[n+1];
			for(int i = 0 ; i <= n ; i++){
				id[i] = i;
			}
		}
		private  boolean connected(int p, int q) {	
			return findRoot(p) == findRoot(q);
		}
		private  int findRoot(int p){
			while(id[p] != p){
				p = id[p];
			}
			return p;
		}
		private  boolean union(int p, int q) {
			int pRoot = findRoot(p);
			int qRoot = findRoot(q);
			if(pRoot == qRoot)
				return false;
			assert( (id[pRoot] == pRoot) &(id[qRoot] == qRoot));
			if(size[pRoot]>size[qRoot]){//q并入p
				id[qRoot] = pRoot;
				size[qRoot]++;
			}
			else{//p并入q
				id[pRoot] = qRoot;
				size[pRoot]++;
			}
			return true;
		}
	}
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		m = nextInt();
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>(m);
		for(int i = 1; i <= m ; i++){
			int v = nextInt();
			int w = nextInt();
			int weight = nextInt();
			Edge e = new Edge(v,w,weight);
			queue.add(e);
		}
		UF uf = new UF(n);
		int res = kruscal(queue,uf);
		System.out.println(res);
	}
	private static int kruscal(Queue<Edge> queue,UF uf) {
		int res = 0;
		while(!queue.isEmpty()){
			Edge e = queue.poll();
			if(uf.union(e.v1, e.v2)){
				res += e.weight;
			}		
		}	
		return res;
	}
}



