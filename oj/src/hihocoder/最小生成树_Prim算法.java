package hihocoder;


import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 最小生成树_Prim算法 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int[][] dist;
	static boolean[] added;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		dist = new int[n+1][n+1];
		added = new boolean[n+1];
		for(int i = 1; i <= n ; i++){
			for(int j = 1; j <= n; j++){
				dist[i][j] = nextInt();
			}
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
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>(n*n,new Comparator<Edge>(){
			@Override
			public int compare(Edge o1, Edge o2) {			
				return o1.weight - o2.weight;
			}			
		});		
		added[1] = true;
		for(int i = 1 ; i <= n ; i++){
			queue.offer(new Edge(1,i,dist[1][i]));
		}			
		int res = 0;
		while(!queue.isEmpty()){
			Edge e = queue.poll();
			if(added[e.to])
				continue;
			added[e.to] = true;
			res += e.weight;
			
			int from = e.to;
			for(int to = 1 ; to <= n ; to++){
				if(!added[to]){
					queue.add(new Edge(from,to,dist[from][to]));
				}
			}
		}
		return res;
	}

}



