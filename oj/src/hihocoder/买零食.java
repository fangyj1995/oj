package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;
public class 买零食 {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static double[] price;
	static int[] eager;
	static int max;
	public static void main(String[] args) throws IOException, ParseException {
		int cases = nextInt();
		for(int i = 0 ; i < cases ; i++){
			n = nextInt();
			price = new double[n];
			eager = new int[n];
			for(int j = 0 ; j < n ; j++){
				price[j] = nextDouble();
				eager[j] = nextInt();
			}
			max = Integer.MIN_VALUE;
			solve();
		}
	}
	private static void solve() {
		double p = 0.0;
		int e = 0;
		for(int i = 0 ; i < n ; i++){
			p = price[i];
			e = eager[i];
			if(p%5 == 0) max = Math.max(max, e);
			for(int j = i+1 ; j < n ; j++){
				p += price[j];
				e += eager[j];
				if(p%5 == 0) max = Math.max(max, e);
				for(int k = j+1 ; k < n ; k++){
					p += price[k];
					e += eager[k];
					if(p%5 == 0) max = Math.max(max, e);
				}
			}
		}
		System.out.println(max);
	}

	


}
