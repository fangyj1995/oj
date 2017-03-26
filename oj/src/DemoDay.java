import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DemoDay {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int n,m;
	static boolean[][] maze;
	static int[][][]dp;
	public static void main(String[] args) throws IOException {
		String[] line = reader.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		maze = new boolean[n][m];
		dp = new int[n][m][2];
		//System.out.println(n+" "+m);
		for(int i = 0 ; i < n; i++){
			for(int j = 0 ; j < m; j++){
				char c = (char)reader.read();
				if     (c == '.') maze[i][j] = true;
				else if(c == 'b') maze[i][j] = false;
				//System.out.print(c);
			}
			reader.readLine();
			//System.out.println();
		}
		work();
	}
	private static void work() {
		for(int x = 0 ; x < n; x++){
			for(int y = 0 ; y < m; y++){
				dp[x][y][0] = dp[x][y][1] = Integer.MAX_VALUE - 1;
			}
		}
		dp[0][0][1] = dp[0][0][0] = (maze[0][0]? 0 : 1);
		if(m > 1&& maze[0][1])
			dp[0][0][1]++;
		
		for(int x = 0 ; x < n; x++){
			for(int y = 0 ; y < m; y++){
				if(x == 0 && y == 0){
					//System.out.print(dp[x][y][0] +" " + dp[x][y][1] +" ; ");
					continue;
				}	
				//x不等于0说明x,y可以由方向1到达
				if(x != 0){
					//上一个点只可能由1方向到达
					if		(y == 0)
							dp[x][y][1] = dp[x - 1][y][1];
					else if (y == m - 1 || !maze[x - 1][y + 1])//上一个点的0方向本来就不能走
							dp[x][y][1] = Math.min(dp[x - 1][y][0] , dp[x - 1][y][1]);
					else
							dp[x][y][1] = Math.min(dp[x - 1][y][0] + 1 , dp[x - 1][y][1]);//我们让上一个点的0方向不能走
					
					if(!maze[x][y]) dp[x][y][1]++;
				}
				//y不等于0说明x,y可以由方向0到达
				if( y != 0){
					//上一个点只可能由0方向到达
					if		(x == 0)
							dp[x][y][0] = dp[x][y - 1][0];
					//上一个点可能也由0方向到达,但如果由1方向到达,说明它不能继续往1方向走
					else if (x == n - 1 || !maze[x + 1][y - 1])//上一个点的1方向本来不能走
							dp[x][y][0] = Math.min(dp[x][y - 1][0] , dp[x][y - 1][1]);
					else
							dp[x][y][0] = Math.min(dp[x][y - 1][0] , dp[x][y - 1][1] + 1);//我们让上一个点的1方向不能走	
					
					if(!maze[x][y]) dp[x][y][0]++;
				}
				//System.out.print(dp[x][y][0] +" " + dp[x][y][1] +" ; ");
			}
			//System.out.println();
		}
		System.out.println(Math.min(dp[n - 1][m - 1][0] , dp[n - 1][m - 1][1]));
	}

}
