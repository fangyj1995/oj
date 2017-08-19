package hihocoder;


import java.io.*;

public class hiho字符串 {
	// Scanner scan = new Scanner(System.in);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public  double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public  String next()throws IOException {in.nextToken();return (String)in.sval;} 	
	
	int n;
	String str;
	public static void main(String[] args) throws IOException{	
		hiho字符串  main = new hiho字符串();
		main.input();
		main.run();
	}
	public void  run(){
		int cnth = 0, cnti = 0, cnto = 0;
		int i = 0 , j = 0;
		int n = str.length();
		int minLen = n+1;
		while(j < n){
			char c = str.charAt(j);
			if(c == 'h'){
				cnth++;
			}
			else if(c == 'i'){
				cnti++;
			}
			else if(c == 'o'){
				cnto++;
			}
			while(cnth >= 2 && cnti >= 1 && cnto >= 1){
				if(cnth == 2 && cnti == 1 && cnto == 1){
					minLen = Math.min(minLen, j-i+1);
				}
				c = str.charAt(i);
				if(c == 'h'){
					cnth--;
				}
				else if(c == 'i'){
					cnti--;
				}
				else if(c == 'o'){
					cnto--;
				}
				i++;
			}
			j++;
		}
		System.out.println(minLen == n+1 ? -1:minLen);
	}	
	
	private void input() throws IOException{
		str = next();
	}
}



