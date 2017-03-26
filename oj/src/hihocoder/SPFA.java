package hihocoder;
import java.io.*;
import java.text.ParseException;
import java.util.*;


public class SPFA {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n,m,s,t;
	static List<Edge>[] adj;
	static int[] distTo;
	static class Edge{
		int to;
		int weight;
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}	
	}
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		m = nextInt();
		s = nextInt();
		t = nextInt();
		adj = new LinkedList[n+1];
		for(int i = 1 ; i <= n ;i++){
			adj[i] = new LinkedList<Edge>();
		}
		for(int i = 1 ; i <= m ;i++){
			int v = nextInt();
			int w = nextInt();
			int weight = nextInt();
			adj[v].add(new Edge(w,weight));
			adj[w].add(new Edge(v,weight));
		}
		int res = spfa();
		System.out.println(res);
	}
	private static int spfa() {
		distTo = new int[n+1];//distTo[i]表示s到i的最短路径		
		for(int i = 1 ; i<= n ;i++){
			distTo[i] = Integer.MAX_VALUE;
		}
		distTo[s] = 0;
		//和dij的区别就是不用优先队列，入队时要检查是否已在队列里，在就话就不入队
		Queue<Integer> queue = new LinkedList<Integer>();		
		boolean[] onQueue = new boolean[n+1];
		queue.offer(s);
		while(!queue.isEmpty()){
			int v = queue.poll();
			onQueue[v] = false;
			relax(queue,distTo,v,onQueue);
		}
		return distTo[t];
	}
	private static void relax(Queue<Integer> queue,int[] distTo,int v,boolean[] onQueue) {
		for(Edge e : adj[v]){//v->w
			if(distTo[e.to] > distTo[v] + e.weight){
				distTo[e.to] = distTo[v] + e.weight;
				if(!onQueue[e.to])
				{
					queue.offer(e.to);
					onQueue[e.to] = true;
				}
			}		
		}	
	}
}













