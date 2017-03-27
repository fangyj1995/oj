
import java.io.*;

public class DemoDay {
	// Scanner scan = new Scanner(System.in);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public  double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public  String next()throws IOException {in.nextToken();return (String)in.sval;} 	
	
	int n;
	int m;
	boolean[][] obstacle;
	public static void main(String[] args) throws IOException{	
		DemoDay main = new DemoDay();
		main.input();
		main.run();
	}
	public void  run(){
		int[][][] dp = new int[n+1][m+1][2];
		for(int i = 1 ; i <= n ; i++)
			dp[i][0][0] = dp[i][0][1] = Integer.MAX_VALUE;
		for(int j = 1 ; j <= m ; j++)
			dp[0][j][0] = dp[0][j][1] = Integer.MAX_VALUE;		
		dp[1][0][0] = 0;
		for(int i = 1 ; i <= n ; i++){
			for(int j = 1 ; j <= m ; j++){
				dp[i][j][0] = dp[i][j-1][0];
				if(dp[i][j-1][1] != Integer.MAX_VALUE)
					dp[i][j][0] = Math.min(dp[i][j][0], dp[i][j-1][1]+(i==n||obstacle[i+1][j-1]? 0:1));
				
				dp[i][j][1] = dp[i-1][j][1];
				if(dp[i-1][j][0] != Integer.MAX_VALUE)
				dp[i][j][1] = Math.min(dp[i][j][1], dp[i-1][j][0]+(j==m||obstacle[i-1][j+1]? 0:1));
				
				if(obstacle[i][j]){
					if(dp[i][j][0] != Integer.MAX_VALUE) 
						dp[i][j][0]++;
					if(dp[i][j][1] != Integer.MAX_VALUE) 
						dp[i][j][1]++;
				}
			}
		}
		System.out.println(Math.min(dp[n][m][0], dp[n][m][1]));
	}
	private void input() throws IOException{
		String[] line = reader.readLine().split("\\s+");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		obstacle = new boolean[n+1][m+1];
		for(int i = 1 ; i <= n ; i++){
			for(int j = 1 ; j <= m ; j++){
				char c = (char)reader.read();
				if(c == 'b')
					obstacle[i][j] = true;
			}
			reader.readLine();
		}
	}
}


