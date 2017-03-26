package hihocoder;


import java.io.*;
import java.text.ParseException;
import java.util.*;


public class _1139_二分_二分答案 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;
	static int k;
	static int t;
	static List<Edge>[] adj;
	static class Edge{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}		
	}
	public static void main(String[] args) throws IOException{
		n = nextInt();
		m = nextInt();
		k = nextInt();
		t = nextInt();
		adj = new LinkedList[n+1];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 1 ; i <= n ; i++)
			adj[i] = new LinkedList<Edge>();
		for(int i = 1 ; i <= m ; i++){
			int v = nextInt();
			int w = nextInt();
			int weight = nextInt();
			max = Math.max(max, weight);
			min = Math.min(min, weight);
			adj[v].add(new Edge(v,w,weight));
			adj[w].add(new Edge(w,v,weight));
		}
		int res = binarySearch(min,max);
		System.out.println(res);
	}
	private static int binarySearch(int min, int max) {
		int lo = min;
		int hi = max;
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(can(mid)){
				hi = mid-1;
			}
			else{
				lo = mid+1;
			}
		}
		return lo;
	}
	private static boolean can(int weight) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		boolean[] marked = new boolean[n+1];
		marked[1] = true;
		int[] step = new int[n+1];
		Arrays.fill(step, Integer.MAX_VALUE);
		step[1] = 0;
		while(!queue.isEmpty()){
			int v = queue.poll();
			if(v == t && step[v] <= k) return true;
			if(step[v] == k)
				continue;
			for(Edge e : adj[v]){//from v
				if(!marked[e.to] && e.weight <= weight)
				{
					step[e.to] = step[v] + 1;
					marked[e.to] = true;
					queue.add(e.to);
				}
			}
		}
		return false;
	}
}



