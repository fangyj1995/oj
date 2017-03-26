import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TotalHighWayDistance {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
	
	static int n,m ;
	static LinkedList<Edge>[] adj;
	static long thd;
	static int[] count;
	static class Edge{
		int v;
		int w;
		int dist;
		long cnt;
		public Edge(int v ,int w, int dist){
			this.v = v;
			this.w = w;
			this.dist = dist;
		}
		public int other(int s){
			if(s == v)return w;
			else return v;
		}
	}
	public static void main(String[] args) throws IOException {
		n = nextInt();
		m = nextInt();
		adj = (LinkedList<Edge>[])new LinkedList[n+1];
		count = new int[n+1];
		for(int i = 0 ; i < n + 1; i++){
			adj[i] = new LinkedList<Edge>();
		}
		for(int i = 0 ; i < n - 1; i++){
			int v ,w,dist;						
			v = nextInt();
			w = nextInt();
			dist = nextInt();
			Edge edge = new Edge(v,w,dist);
			adj[v].add(edge);
			adj[w].add(edge);
		}
		work();
		for(int i = 0 ; i < m ; i++){
			String action = next();
			if(action.equals("QUERY")){
				System.out.println(thd);
			}
			else{//EDIT
				int v ,w,dist;						
				v = nextInt();
				w = nextInt();
				dist = nextInt();
				for(Edge e:adj[v])
					if(e.other(v) == w){
						thd += (long)e.cnt*(long)(dist - e.dist);
						e.dist  = dist;
						break;
					}
			}
		}
	}
	private static void work() {
		boolean[] marked = new boolean[n+1];
		dfs(1,marked);
	}
	private static void dfs(int v,boolean[] marked){	
		count[v]=1;
		marked[v] = true;
		for(Edge e:adj[v]){			
			int w = e.other(v);
			if(!marked[w]){
				dfs(w,marked);
				count[v]+=count[w];
				e.cnt = (long)count[w]*(long)(n-count[w]);
				thd+=(long)e.dist*(long)e.cnt;
			}
		}		
	}
}
