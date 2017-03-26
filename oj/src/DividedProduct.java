import java.io.*;
import java.text.ParseException;
import java.util.*;


public class DividedProduct {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	static int n;
	static int m;
	static long cnt;
	static long mod = 1000000007;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		m = nextInt();
		for(int i = 1 ; i <= n ; i++)
			dfs(i,0,1);
		System.out.println(cnt%mod);
	}
	private static void dfs(int i, int sum, long product) {
		sum += i;
		product *= (long)i;
		if(sum == n && product % m == 0){
			cnt++;
			return;
		}
		for(int j = i+1; j <= n - sum; j++)
			dfs(j,sum,product);
		
	}

}

