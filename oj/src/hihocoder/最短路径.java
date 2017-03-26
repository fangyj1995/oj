package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 最短路径 {
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
		int res = dijstra();
		System.out.println(res);
	}
	private static int dijstra() {
		distTo = new int[n+1];//distTo[i]表示s到i的最短路径		
		for(int i = 1 ; i<= n ;i++){
			distTo[i] = Integer.MAX_VALUE;
		}
		distTo[s] = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(m,new Comparator<Integer>(){
			@Override
			public int compare(Integer v1, Integer v2) {
				return distTo[v1]-distTo[v2];//优先队列的比较条件是 起点的距离
			}			
		});	
		relax(queue,distTo,s);
		while(!queue.isEmpty()){
			int v = queue.poll();
			relax(queue,distTo,v);
		}
		return distTo[t];
	}
	private static void relax(PriorityQueue<Integer> queue,int[] distTo,int v) {
		for(Edge e : adj[v]){//v->w
			if(distTo[e.to] > distTo[v] + e.weight){
				distTo[e.to] = distTo[v] + e.weight;
				//if(!queue.contains(e.to))//在没有负权重的情况下,dij算法是不会重复入队的，这个判断条件应该是冗余的
				queue.offer(e.to);//重复入队的话就要先出对再入队
			}		
		}	
	}
}





