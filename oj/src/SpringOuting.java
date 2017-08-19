
import java.io.*;
import java.util.*;

public class SpringOuting {
	// Scanner scan = new Scanner(System.in);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public  double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public  String next()throws IOException {in.nextToken();return (String)in.sval;} 	
	
	int n;
	int k;
	int[][] favor;
	public static void main(String[] args) throws IOException{	
		SpringOuting main = new SpringOuting();
		main.input();
		main.run();
	}
	public void  run(){
		int lastRes = 0;
		for(int place  = k; place > 0 ; place--){
			int vote = 0;
			for(int i = 1 ; i <= n; i++){
				if(favor[i][place] < favor[i][lastRes])
					vote++;
			}
			if(vote*2 > n){
				lastRes = place;
			}
		}
		if(lastRes == 0)
			System.out.println("otaku");
		else
			System.out.println(lastRes);
	}	
	
	private void input() throws IOException{
		n = nextInt();
		k = nextInt();
		favor = new int [n+1][k+1];
		for(int i = 1 ; i <= n; i++){
			for(int j = 0 ; j <= k ; j++){
				favor[i][nextInt()] = j;
			}
		}
	}
}


