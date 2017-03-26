import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class HiHoDrinkingGame {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	static int n;
	static int k;
	static int[] dice;
	public static void main(String[] args) throws IOException, ParseException {
		n = nextInt();
		k = nextInt();
		dice = new int[n];
		for(int i = 0; i< n; i++){
			dice[i] = nextInt();
		}
		int lo = 1;
		int hi = k+1;
		while(lo < hi){
			int mid = lo+(hi-lo)/2;
			boolean win = play(mid);
			if(win){
				hi = mid;
			}
			else
				lo = mid+1;
		}
		System.out.println(lo);
	}
	private static boolean play(int t) {
		int remain = t;
		int score = 0;
		for(int i = 0; i< n; i++){
			int d = dice[i];
			if(remain <= d)
				remain = 0;
			else{
				remain -= d;
				score++;
			}
			remain += t;	
		}
		return score > n/2;
	}
}
