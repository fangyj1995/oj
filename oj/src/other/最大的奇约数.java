package other;


import java.io.*;
import java.util.Arrays;



public class 最大的奇约数 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int m;

	public static void main(String[] args) throws IOException{	
		long n = nextInt();		
		long res = 0;
		while(n >= 1){
			while(n%2 == 0){
				res += n*n/4;
				n >>= 1;
			}
			res += n;
			n--;
		}
		System.out.println(res);
	}


}



