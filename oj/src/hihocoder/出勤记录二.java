package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class 出勤记录二 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n = 0;
	static int mod = 1000000007;
	static long[][][] dp ;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		dp = new long[n+1][2][4];
		if(n == 1){
			System.out.println(3);
			return;
		}
		for(int i = 0 ; i <= 1; i++){
			for(int j = 0 ; j <= 3 ; j++){
				dp[2][i][j] = 1;
			}
		}
		dp[2][1][0] = 2;
		dp[2][1][3] = 0;
		for(int i = 3 ; i <= n ; i++){
			//0		
			dp[i][0][0] = dp[i-1][0][0]%mod + dp[i-1][0][2]%mod;
			dp[i][0][1] = dp[i-1][0][0]%mod + dp[i-1][0][2]%mod;
			dp[i][0][2] = dp[i-1][0][1]%mod + dp[i-1][0][3]%mod;
			dp[i][0][3] = dp[i-1][0][1]%mod;
			
			//1
			dp[i][1][0] = dp[i-1][1][0]%mod + dp[i-1][1][2]%mod + dp[i-1][0][0]%mod + dp[i-1][0][2]%mod;
			dp[i][1][1] = dp[i-1][1][0]%mod + dp[i-1][1][2]%mod;
			dp[i][1][2] = dp[i-1][1][1]%mod + dp[i-1][1][3]%mod + dp[i-1][0][1]%mod + dp[i-1][0][3]%mod;
			dp[i][1][3] = dp[i-1][1][1]%mod;
			
		}
		long max = 0;
		for(int i = 0 ; i <= 1; i++){
			for(int j = 0 ; j <= 3 ; j++)
				max = (max%mod + dp[n][i][j]%mod);
		}
		System.out.println(max%mod);
	}

	
}
