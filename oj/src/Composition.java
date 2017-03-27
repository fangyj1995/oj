import java.io.*;
import java.util.Arrays;

public class Composition {
static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	
	static int n;
	static int m;
	static char[] str;
	static boolean[][] canNotAdj = new boolean[26][26];
	public static void main(String[] args) throws IOException{	
		n = Integer.parseInt(reader.readLine());
		str = new char[n+1];
		for(int i = 1 ; i <= n ; i++){
			str[i] = (char)reader.read();
		}
		reader.readLine();
		m = Integer.parseInt(reader.readLine());
		for(int i = 0 ; i < m ; i++){
			char a = (char)reader.read();
			char b = (char)reader.read();
			canNotAdj[a-'a'][b-'a'] = true;
			canNotAdj[b-'a'][a-'a'] = true;
			reader.readLine();
		}	
		int[][] dp = new int[n+1][26];
		for(int i = 0 ; i <= n ; i++){
			Arrays.fill(dp[i],Integer.MAX_VALUE);
		}
		for(int i = 1 ; i <= n ; i++){
			char c = str[i];
			dp[i][c-'a'] = i-1;
			for(int j = 0 ; j < 26 ; j++){
				if(!canNotAdj[c-'a'][j] && dp[i-1][j] != Integer.MAX_VALUE)
					dp[i][c-'a'] = Math.min(dp[i][c-'a'], dp[i-1][j]);
			}
			for(int j = 0 ; j < 26  ; j++){
				if(dp[i-1][j] != Integer.MAX_VALUE)
					dp[i][j] = Math.min(dp[i][j],dp[i-1][j] + 1);
			}
		}
		int res = Integer.MAX_VALUE;
		for(int j = 0 ; j < 26; j++){
			res = Math.min(res, dp[n][j]);
		}
		System.out.println(res);
	}
		
}


