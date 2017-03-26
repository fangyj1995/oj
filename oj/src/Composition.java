import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Composition {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
	public static void main(String[] args) throws IOException {
		int n = nextInt();
		char[] str = next().toCharArray();
		int m = nextInt();
		boolean[][] conflict = new boolean[26][26];
		for(int i = 0 ; i < m ; i++){
			String line = next();
			conflict[line.charAt(0) - 'a'][line.charAt(1) - 'a'] = true;
			conflict[line.charAt(1) - 'a'][line.charAt(0) - 'a'] = true;			
		}
		int[][] dp = new int[n][26];//dp[i][j]表示已扫描到前i个字符，其中最后一个字符为j,且不冲突的最大字符数
		for(int i = 0 ; i < n; i++)
			Arrays.fill(dp[i], 200001);
		dp[0][str[0]-'a'] = 0;
		int[] last = new int[26];
		Arrays.fill(last, -1);
		last[str[0] - 'a'] = 0;
		for(int i = 1 ; i < n; i++){//dp[i][cur] dp[i][j]
			int cur = str[i] - 'a';
			for(int j = 0 ; j < 26; j++)//删除cur			
				if(last[j]!=-1)
					dp[i][j] = dp[i - 1][j]+1;
			for(int j = 0 ; j <26;j++)//不删除cur,将j作为cur的前一个字符
				if(!conflict[cur][j]&&last[j]!=-1)							
					dp[i][cur] =  Math.min(dp[i][cur], dp[last[j]][j]+i - last[j]-1);			
			dp[i][cur] = Math.min(dp[i][cur], i);//如果所有的j都与cur冲突,dp[i][cur]> i,要保留cur就要把前面的所有字符都删除
			last[cur] = i;
		}
		int res = n;
		for(int i = 0 ; i < 26 ;i++)
			res = Math.min(res, dp[n-1][i]);
		System.out.println(res);
	}

}
