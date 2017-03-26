import java.io.*;
import java.util.*;


public class Kthstring {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
	static int N,M,K;
	static long[][] pailie;
	public static void main(String[] args) throws IOException {
		int cases = nextInt();
		for(int i = 0 ; i < cases ;i++){
			N = nextInt();
			M = nextInt();
			K = nextInt();
			pailie = new long[N+M+1][N+M+1];
			for(int j = 1 ; j <= N+M ; j++){
				for(int k = 0 ; k <= j ; k++){
					if(j == 1||k == 0||k == j)
						pailie[j][k] = 1;
					else if(k == 1||k == j - 1)
						pailie[j][k] = j;
					else{
						pailie[j][k] = pailie[j - 1][k - 1] + pailie[j - 1][j - k -1];
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			cal(N,M,K,sb);
			System.out.println(sb.toString());
		}
	}
	private static void cal(int n, int m, long k,StringBuilder sb) {
		if(n == 0 ||m == 0){//n或m等于0 都只有一种排列
			if(k == 1){
				for(int i = 0 ; i < n - 1 ; i++)
					sb.append('0');
				for(int i = 0 ; i < m; i++)
					sb.append('1');	
			}
			else{
				sb.replace(0, sb.length(), "Impossible");
			}
			return;
		}
		long sub = pailie(m+n-1,n-1) - k;//以0开头的数的个数是pailie(m+n-1,n-1)
		if(sub == 0){//sub = 0 说明k是以0开头的最后一个数，一个0加上m个1再加上n - 1 个0
			sb.append('0');
			for(int i = 0 ; i < m; i++)
				sb.append('1');
			for(int i = 0 ; i < n - 1 ; i++)
				sb.append(0);
			return;
		}
		if(sub>0)
		{
			sb.append('0');
			cal(n - 1,m,k,sb);
		}
		else{
			sb.append('1');
			cal(n,m - 1,-sub,sb);
		}
	}
	private static long pailie(int m, int n) {
		return pailie[m][n];
	}	
}
