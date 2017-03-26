package hihocoder;


import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 最小生成树三_堆优化的Prim算法_邻接表 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static boolean[] added;
	static LinkedList<Edge>[] adj;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		m = nextInt();
		added = new boolean[n+1];
		adj = new LinkedList[n+1];
		for(int i = 1; i <= n ; i++){
			adj[i] = new LinkedList<>();
		}
		for(int i = 1; i <= m ; i++){
			int v = nextInt();
			int w = nextInt();
			int weight = nextInt();
			
			adj[v].add(new Edge(v,w,weight));
			adj[w].add(new Edge(w,v,weight));
		}
		int res = prim();
		System.out.println(res);
	}
	static class Edge{
		int from;
		int to;
		int weight;
		public Edge(int v,int w,int weight){
			this.from = v;
			this.to = w;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			return "Edge [v=" + from + ", w=" + to + ", weight=" + weight + "]";
		}
		
	}
	private static int prim() {
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>(m,new Comparator<Edge>(){
			@Override
			public int compare(Edge o1, Edge o2) {			
				return o1.weight - o2.weight;
			}			
		});
		
		added[1] = true;
		for(Edge e:adj[1]){
			queue.offer(e);
		}	
		
		int res = 0;
		while(!queue.isEmpty()){
			Edge e = queue.poll();
			if(added[e.to])
				continue;
			added[e.to] = true;
			res += e.weight;		
			int from = e.to;
			for(Edge edge : adj[from]){
				if(!added[edge.to]){
					queue.add(edge);
				}
			}
		}
		return res;
	}



}



