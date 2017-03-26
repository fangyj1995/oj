import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class LostInTheCity {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	static int n,m;
	static char[][] map;
	static char[][][] hi = new char[4][3][3];

	public static void main(String[] args) throws IOException, ParseException {
		String[] line = reader.readLine().split("\\s+");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		map = new char[n][m];
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < m; j++){
				map[i][j] = (char) reader.read();			
			}
			reader.readLine();
		}
		for(int i = 0 ; i < 3 ; i++){
			for(int j = 0 ; j < 3; j++){
				hi[0][i][j] = (char) reader.read();
			}
			reader.readLine();
		}
		for(int i = 1 ; i <= 3 ;i++){
			hi[i] = rotate(hi[i - 1]);
		}
		
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < m; j++){
				if(i+2<n && j+2<m && possible(i,j))
					System.out.println((i+2)+" "+(j+2));
			}
		}
	}
	private static boolean possible(int r, int c) {
		for(int t = 0 ; t < 4 ; t++){
			char[][] block = hi[t];
			boolean found = true;
			for(int i = 0 ; i < 3 ; i++){
				if(!found) break;
				for(int j = 0 ; j < 3; j++){
					if(map[r+i][c+j] != block[i][j])
					{
						//System.out.println(i+"　"+j);
						found =  false;
						break;
					}
				}
			}
			if(found)  return true;
		}
		return false;
	}
	private static char[][] rotate(char[][] hi) {
		char[][] res = new char[3][3];
		for(int i = 0 ; i < 3 ; i++){
			for(int j = 0 ; j < 3; j++){
				res[i][j] = hi[j][2 - i];
			}
		}
		return res;
	}
}

