package hihocoder;


import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class 歌德巴赫猜想 {
	// Scanner scan = new Scanner(System.in);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public  double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public  String next()throws IOException {in.nextToken();return (String)in.sval;} 	
	DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
	int n;
	boolean[] isPrime;
	public static void main(String[] args) throws IOException{	
		歌德巴赫猜想 main = new 歌德巴赫猜想();
		main.input();
		main.run();		
	}
	private void input() throws IOException{
		n = nextInt();
	}
	public void  run(){
		isPrime = prime();
		int p = 0;
		while(p < n){
			if(isPrime[p] && isPrime[n-p]) break;
			p++;
		}
		System.out.println(p+" "+(n-p));
	}
	boolean[] prime(){
		boolean[] flags = new boolean[n+1];
		Arrays.fill(flags, true);
		flags[0] = flags[1] = false;
		int prime = 2;
		while(prime <= n){
			cross(flags,prime);
			prime = nextPrime(flags,prime);
		}
		return flags;		
	}
	private int nextPrime(boolean[] flags, int prime) {
		int next = prime +1;
		while(next < flags.length && !flags[next]){
			next++;
		}
		return next;
	}
	private void  cross(boolean[] flags, int prime) {
		for(long i = (long)prime * (long)prime; i < flags.length; i+= prime){
			flags[(int) i] = false;
		}	
	}
	
}



