package other;


import java.io.*;
import java.util.Arrays;



public class 买苹果 {
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
		n = nextInt();		
		if(n == 6){
			System.out.println(1);
			return;
		}
		if(n < 8){
			System.out.println(-1);
			return;
		}
		int[] f = new int[n+1];
		Arrays.fill(f, Integer.MAX_VALUE);
		f[6] = 1;
		f[8] = 1;
		f[0] = 0;
		for(int i = 6 ; i <= n ; i++){
			if(i > 6 && f[i-6] != Integer.MAX_VALUE)
				f[i] = Math.min(f[i], f[i-6] + 1);
			
			if(i > 8 && f[i-8] != Integer.MAX_VALUE)
				f[i] = Math.min(f[i], f[i-8] + 1);			
		}
		if(f[n] == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(f[n]);
	}


}



