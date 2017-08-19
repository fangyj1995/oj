
import java.io.*;
import java.util.*;

public class HamliCycle1 {
	// Scanner scan = new Scanner(System.in);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public  double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public  String next()throws IOException {in.nextToken();return (String)in.sval;} 	
	
	int n;
	int m;
	LinkedList<Integer>[] adj;
	int res;
	int[][] dp;
	public static void main(String[] args) throws IOException{	
		HamliCycle1 main = new HamliCycle1();
		main.input();
		main.run();
	}
	public void  run(){
		res = dfs(1,1,0);
		System.out.println(res);
	}	
	public int dfs(int s, int v, int state){
		if(dp[v][state] != -1){
			return dp[v][state];
		}
		
		state = state^(1 << (v-1));					
		int res = 0;
		for(int w: adj[v]){
			if((state&(1 << (w-1))) == 0)
				res += dfs(s,w,state);
			else{
				if(w == s && state == ((1<<n)-1) ){
					state = state^(1 << (v-1));
					dp[v][state] = 1;
					return 1;					
				}
			}
		}
		state = state^(1 << (v-1));
		dp[v][state] = res;
		return res;
	}
	private void input() throws IOException{
		n = nextInt();
		m = nextInt();
		adj = new LinkedList[n+1];
		dp = new int[n+1][1<<(n+1)+1];
		Arrays.fill(dp[0], -1);
		for(int i = 1 ; i <=n ; i++){
			adj[i] = new LinkedList<>();
			Arrays.fill(dp[i], -1);
		}
		for(int i = 0 ; i < m ; i++){			
			int v = nextInt();
			int w = nextInt();
			adj[v].add(w);
		}
	}
}


