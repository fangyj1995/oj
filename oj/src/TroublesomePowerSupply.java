import java.io.*;
import java.text.ParseException;
import java.util.*;
public class TroublesomePowerSupply {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	static int N,M;
	static int index;
	static int cnt;
	static boolean[] onStack;
	static int componet[];
	static Stack<Integer> s;
	static LinkedList<Integer> [] adj;
	static int[] dfn;// 在DFS中该节点被搜索的次序(时间戳)
	static int[] low;//为i或i的子树能够追溯到的最早的栈中节点的次序号
				     //当DFN[ i ]==LOW[ i ]时，为i或i的子树可以构成一个强连通分量。

	
	public static void main(String[] args) throws IOException, ParseException {
		int cases = nextInt();
		for(int i = 0 ; i < cases; i++){
			N = nextInt();
			M = nextInt();
			adj = (LinkedList<Integer>[]) new LinkedList[2*N+2];
			dfn = new int[2*N+2];
			Arrays.fill(dfn, -1);
			low = new int[2*N+2];
			componet = new int[2*N+2];
			onStack = new boolean[2*N+2];
			index = 0;
			cnt = 0;
			s = new Stack<Integer>();
			
			for(int j = 0 ; j < M ;j++){
				int v = nextInt();
				int w = nextInt();
				int c = nextInt();	
				//i:1,i+N:0
				if(c == 0){
					if(adj[v+N] == null) adj[v+N] = new LinkedList<Integer>();
					if(adj[w+N] == null) adj[w+N] = new LinkedList<Integer>();
					//v0,w1
					adj[v+N].add(w);
					//w0,v1
					adj[w+N].add(v);
				}
				else{
					
					if(adj[v] == null) adj[v] = new LinkedList<Integer>();
					if(adj[w] == null) adj[w] = new LinkedList<Integer>();
					//v1,w0
					adj[v].add(w+N);
					//w1,v0
					adj[w].add(v+N);
				}
			}
			for(int j = 1 ; j <= 2*N; j++)
			{
				if(dfn[j] == -1)
					tarjan(j);
			}				
			String ans = "Yes";
			for(int j = 1 ; j <= N; j++){
				if(componet[j] == componet[j+N])
				{
					ans = "No";
					break;
				}
			}
			System.out.println(ans);
		}
	}
	private static void tarjan(int v) {
		dfn[v] = low[v] = index++;	
		s.push(v);
		onStack[v] = true;
		if(adj[v]!=null)
		{	
			for(Integer w : adj[v]){
				if(dfn[w] == -1){
					tarjan(w);
					low[v] = Math.min(low[v], low[w]);
				}
				else if(onStack[w]){
					low[v] = Math.min(low[v], dfn[w]);
				}			
			}
		}
		if(dfn[v] == low[v])
		{
			cnt++;
			while(s.peek() != v)
			{
				int w = s.pop();
				onStack[w] = false;
				componet[w] = cnt;
			}
			onStack[s.pop()] = false;
			componet[v] = cnt;
		}
	}
	


}

