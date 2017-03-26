package hihocoder;

import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class 拆字游戏 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}      
	static int n;
	static int m;
	static int[][] id;
	static class point{
		int x;int y;
		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static class block {
		point daibiao;
		int mincol ,maxcol ;
		int minrow ,maxrow ;
	}
	public static void main(String[] args) throws IOException, ParseException {
				
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] line = reader.readLine().split("\\s+");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		boolean[][] matrix = new boolean[n][m];
		id = new int[n][m];
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < m ; j++){			
				char c = (char)reader.read();				
				if(c == '1') matrix[i][j] = true;
			}
			reader.readLine();
		}
		slove(matrix);
	}
	static int[][] dir = new int[][]{
		{0,1},
		{1,0},
		{-1,0},
		{0,-1}
		};
	private static void slove(boolean[][] matrix) {
		boolean[][] visited = new boolean[n][m];
		int cnt = 1;
		List<block> blist = new LinkedList<block>();		
		for(int j = 0 ; j < m ; j++){
			for(int i = 0 ; i < n ; i++){				
				if(matrix[i][j]&&!visited[i][j]){
					block b = new block();
					b.daibiao = new point(i,j);
					b.minrow = b.maxrow = i;
					b.mincol = b.maxcol = j;				
					dfs(i, j, visited, matrix, b,cnt);					
					blist.add(b);	
					cnt++;
				}
			}
		}		
		for(block b: blist){
			int h = b.maxrow - b.minrow + 1;
			int w = b.maxcol - b.mincol + 1;
			System.out.println(h +" "+w);
			point p = b.daibiao;
			int d = id[p.x][p.y];
			for(int i = 0 ; i < h ; i++){
				for(int j = 0 ; j < w ; j++){
					if(id[i + b.minrow][j + b.mincol] == d) 
						System.out.print(1);
					else 		 
						System.out.print(0);
				}
				System.out.println();
			}
		}
	}
	private static void dfs(int i, int j, boolean[][] visited,boolean[][] matrix,block b,int cnt) {
		visited[i][j] = true;
		id[i][j] = cnt;
		b.mincol = Math.min(b.mincol, j);
		b.maxcol = Math.max(b.maxcol, j);	
		b.minrow = Math.min(b.minrow, i);						
		b.maxrow = Math.max(b.maxrow, i);
		for(int d = 0 ; d < 4; d++){
			int row = i + dir[d][0];
			int col = j + dir[d][1];						
			if(checkRange(row,col)&&matrix[row][col]&&!visited[row][col]){					
				dfs(row,col,visited,matrix,b,cnt);
			}
		}		
		
	}
	private static boolean checkRange(int i,int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
	
	
}
