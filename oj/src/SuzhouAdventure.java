import java.io.*;
import java.text.ParseException;
import java.util.*;
public class SuzhouAdventure {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	static int N,K,M;
	static boolean[] must;
	static int[] mustv;
	static int[] score;
	static LinkedList<Integer>[] adj;
	static long[][] dp ;
	static int[] edgeTo;
	static int cnt = 0;
	static int mod = 100000;
	public static void main(String[] args) throws IOException, ParseException {
		N = nextInt();
		K = nextInt();
		M = nextInt();
		must = new boolean[N+1];
		mustv = new int[N+1];
		score = new int[N+1];
		dp = new long[N+1][M+1];
		edgeTo = new int[N+1];		
		adj = (LinkedList<Integer>[]) new LinkedList[N+1];
		
		
		for(int i = 1 ; i <= N ; i++)
		{
			score[i] = nextInt();
			adj[i] = new LinkedList<>();
			Arrays.fill(dp[i], -1);
		}
		
		for(int i = 1 ; i <= K ; i++)
		{
			int v = nextInt();
			mustv[i] = v;
			must[v] = true;
		}
		for(int i = 1 ; i <= N-1 ; i++)
		{
			int v = nextInt();
			int w = nextInt();
			adj[v].add(w);
			adj[w].add(v);
		}
		solve();
		
	}
	private static void solve() {
		must[1] = true;
		edgeTo[1] = 1;
		dfs(1,new boolean[N+1]);		
		for(int i = 1 ; i <= K ; i++){
			int v = mustv[i];
			while(edgeTo[v] != 1){
				v = edgeTo[v];
				must[v] = true;
			}
		}
		for(int i = 1 ; i <= N ; i++)
		{	
			if(must[i]) {
				score[i] += mod;
				cnt++;
			}
		}
		if(cnt > M) {
			System.out.println(-1);
			return ;
		}
		dp(1,M);
		System.out.println(dp[1][M]%mod);
	}
	private static void dp(int s,int m){
		if(m == 0) return;
		if(dp[s][m] != -1) return;		
		dp[s][m] = 0;//表示不选择s节点		
		long[][] g = new long[adj[s].size()+1][m+1];//g[i][j]表示前i个儿子选择j个节点时最大权值		
		int i = 0;//子节点索引
		for(Integer v : adj[s]){//枚举子节点
			if(edgeTo[v] != s) continue;//Important
			i++;
			for(int j = 0 ; j <= m-1 ; j++){//枚举容量
				g[i][j] = g[i-1][j];//不选第i个子节点,k=0	
				for(int k = 1 ; k <= j ;k++)//在前i个子节点中选了k个
				{
					dp(v,k);
					g[i][j] = Math.max(g[i][j],g[i-1][j-k] + dp[v][k]);
				}
			}		
		}
		dp[s][m] = Math.max(dp[s][m], g[i][m-1]+score[s]);
	}
	private static void dfs(int v,boolean[] marked){
		marked[v] = true;
		for(Integer w :adj[v]){
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(w,marked);
			}
		}
	}
}
