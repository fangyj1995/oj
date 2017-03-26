
import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * 求最长公共子串和最长公共子序列的结合
 */
public class StringMatchingContentLength {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       

	public static void main(String[] args) throws IOException, ParseException {
		String a = next();
		String b = next();
		int res = maxSubSequence(a,b); 
		System.out.println(res);
	}
	private static int maxSubSequence(String a, String b) {
		int m = a.length();
		int n = b.length();
		int[][] f = new int[m+1][n+1];//f[i][j]代表a（1~i）,b(1~j)最长公共后缀长度
		for(int i = 1; i <= m; i++)
			f[i][0] = 0;		
		for(int j = 1; j <=n ;j++)
			f[0][j] = 0;		
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <=n ;j++){
				if(a.charAt(i-1) == b.charAt(j-1))
					f[i][j] = f[i-1][j-1] + 1;
				else
					f[i][j] = 0;
			}
		}
		int[][] dp = new int[m+1][n+1];//dp[i][j]代表a（1~i）,b(1~j)最长公共子序列
		for(int i = 1; i <= m; i++)
			dp[i][0] = 0;		
		for(int j = 1; j <=n ;j++)
			dp[0][j] = 0;		
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <=n ;j++){
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(f[i][j] >= 3)
					for(int k = 3 ; k <= f[i][j] ;k++)
						dp[i][j] = Math.max(dp[i][j], dp[i-k][j-k] + k);
			}
		}
		return dp[m][n];
	}
	
}
