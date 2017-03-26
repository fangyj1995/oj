import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Constellations {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	static int n;
	static boolean[][] sky;
	static int[][][] stellas;
	static int h , w;
	static int[] count;
	public static void main(String[] args) throws IOException, ParseException {
		n = reader.read()-'0';	
		stellas = new int[n][][];
		count = new int[n];		
		reader.readLine();
		for(int k = 0 ; k < n; k++){
			String[] line = reader.readLine().split("\\s+");
			int h = Integer.parseInt(line[0]);
			int w = Integer.parseInt(line[1]);

			stellas[k] = new int[20][2];
			int cnt = 0;
			boolean first = false;
			int x  = 0, y = 0;
			for(int i = 0 ; i < h ; i++){				
				for(int j = 0 ; j < w; j++){
					char ch = (char) reader.read();
					if(ch == '#') {
						if(!first){
							first = true;
							y = i;
							x = j;							
						}
						stellas[k][cnt][0] = i - y;	
						stellas[k][cnt][1] = j - x;	
						cnt++;
					}
				}			
				reader.readLine();
			}
			count[k] = cnt;
		}
		
		String[] line = reader.readLine().split("\\s+");
	    h = Integer.parseInt(line[0]);
		w = Integer.parseInt(line[1]);
		sky = new boolean[h][w];	
		for(int i = 0 ; i < h ; i++){
			for(int j = 0 ; j < w; j++){
				char ch = (char) reader.read();
				if(ch == '#') sky[i][j] = true;			
			}			
			reader.readLine();
		}
		for(int k = 0 ; k < n; k++){
			boolean res = spot(k);
			if(res)
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}
	private static boolean spot(int k) {
		//System.out.println(count[k]);
		for(int i = 0 ; i < h  ;i++){
			for(int j = 0 ; j < w  ;j++){
				boolean spoted = true;			
				for(int star = 0; star < count[k] ; star++){
					
					int r = stellas[k][star][0]+i;
					int c = stellas[k][star][1]+j;
					
					if(r<0||c<0||r>h-1||c>w-1||!sky[r][c])
					{
						spoted = false;
						break;
					}
				}			
				if(spoted) {
					//System.out.println(i+" , "+j);
					return true;
				}
			}
		}
		return false;
	}
}
