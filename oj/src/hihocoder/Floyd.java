package hihocoder;
import java.io.*;
import java.text.ParseException;
import java.util.*;


public class Floyd {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	static int n,m;
	static int[][] adj;
	static int[][] dist;
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
		adj = new int[n+1][n+1];//邻接矩阵方便一点
		for(int i = 1 ; i <= n ;i++){
			Arrays.fill(adj[i], Integer.MAX_VALUE);
			adj[i][i] = 0;
		}
		for(int i = 1 ; i <= m ;i++){
			int v = nextInt();
			int w = nextInt();
			int weight = nextInt();
			adj[v][w] = Math.min(adj[v][w], weight);
			adj[w][v] = Math.min(adj[w][v], weight);	
		}
		floyd();
		for(int i = 1 ; i <= n ;i++){
			for(int j = 1 ; j <= n ; j++){
				out.print(dist[i][j]+" ");
			}		
			out.println();
		}		
		out.flush();
	}
	private static void floyd() {
		dist = new int[n+1][n+1];
		for(int i = 1 ; i <= n ;i++){
			for(int j = 1 ; j <= n ; j++){
				dist[i][j] = adj[i][j];
			}
		}
		for(int k = 1 ; k <= n ; k++){//这里注意k循环写在最外面
			for(int i = 1 ; i <= n ;i++){
				for(int j = 1 ; j <= n ; j++){		
					if(i!=j && j!=k && i!=k && dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE){
						dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
					}
				}
			}
		}
	}
}














