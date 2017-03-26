package hihocoder;

import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class 数组拆分 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}      
	public static void main(String[] args) throws IOException, ParseException {
		int n = nextInt();
		int mod = 1000000007;
		Map<Integer,Long> map= new HashMap<>(n);//key:sum[i] , value:dp[i],和为sum的数组有多少种拆法
		long[] dp = new long[n + 1];
		int[] sum = new int[n + 1];//位置i之前所有数的和
		int[] num = new int[n+1];
		
		for(int i = 1 ; i <= n ; i++)
			num[i] = nextInt();		
		for(int i = 1 ; i <= n ; i++)
			sum[i] = num[i] + sum[i - 1];	
						
		map.put(0,1l);	
		long s = 1;
		for(int i = 1 ; i <= n ; i++){
			long temp = 0;
			if(map.containsKey(sum[i]))
				temp = map.get(sum[i]);
			
			dp[i] = (s - temp + mod) % mod;
			map.put(sum[i], (temp+dp[i]) % mod);
			s = (s + dp[i]) % mod;
		}
		
		System.out.println("\n"+Arrays.toString(sum));
		System.out.println(Arrays.toString(dp));
		System.out.println(dp[n]%mod);
	}

	
}

