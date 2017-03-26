import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class HaveLunchTogether {
	//static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
//	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
//	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
//	public static String next()throws IOException {in.nextToken();return (String)in.sval;}   
//	
	static int n;
	static int m;
	static char[][] map;
	static int r;
	static int c;
	static int[][] distTo;
	static int[][] dir = new int[][]{
			{1,0},
			{0,1},
			{-1,0},
			{0,-1}
	};
	static class Cord{
		int r;
		int c;
		int id;
		public Cord(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		public boolean isOccuped(){
			return map[r][c] == 'o';
		}
		public boolean checkIndex(){
			return c >= 0 && c <= n - 1 && r >= 0 && r <= m - 1;
		}
	}
	public static void main(String[] args) throws IOException, ParseException {
		String[] line = reader.readLine().split("\\s+");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		map = new char[n][m];
		distTo = new int[n][m];
		
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < m; j++){
				char ch = (char) reader.read();
				if(ch == 'S') map[i][j] = 's';
				else if(ch == '#'|| ch == 'P') map[i][j] = 'o';
				else if(ch == '.') map[i][j] = ' ';
				else{
					r = i;c = j;
					map[i][j] = 'h';
				}
			}
			reader.readLine();
		}

		calDist();
		long minDist = Long.MAX_VALUE;
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < m; j++){
				if(map[i][j] == 's' && distTo[i][j] != Integer.MAX_VALUE)
				{
					for(int d = 0 ; d < 4 ; d++){
						Cord w = new Cord(i+dir[d][0],j+dir[d][1]);
						if(w.checkIndex() && map[w.r][w.c] == 's' && distTo[w.r][w.c] != Integer.MAX_VALUE)
						{
							minDist = Math.min(minDist, distTo[i][j]+distTo[w.r][w.c]);
						}					
					}
					
				}
			}
		}
		if(minDist == Long.MAX_VALUE)
			System.out.println("Hi and Ho will not have lunch.");
		else
			System.out.println(minDist);
	}
	private static void calDist() {
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < m; j++){
				distTo[i][j] = Integer.MAX_VALUE;
			}
		}
		distTo[r][c] = 0;
		boolean[][] visited = new boolean[n][m];
		Queue<Cord> q = new LinkedList<Cord>();
		q.offer(new Cord(r,c));
		visited[r][c] = true;
		while(!q.isEmpty()){
			Cord v = q.poll();	
			if(map[v.r][v.c] == 's') continue;
			for(int d = 0 ; d < 4 ; d++){
				Cord w = new Cord(v.r+dir[d][0],v.c+dir[d][1]);
				if(w.checkIndex()&&!w.isOccuped()&&!visited[w.r][w.c])
				{
					q.offer(w);
					visited[w.r][w.c] = true;
					distTo[w.r][w.c] = distTo[v.r][v.c]+1;
				}					
			}
		}		
	}	

}
