import java.io.*;
import java.text.ParseException;
import java.util.*;
public class KochSnowFlake {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 

	public static void main(String[] args) throws IOException, ParseException {
		int cases = nextInt();
		for(int i = 0 ; i < cases; i++){
			int res = cal(nextInt(),nextInt());
			System.out.println(res);
		}		
	}
	private static int cal(int id, int g) {		
		int left = id%4;
		while(left != 2 && left != 3)
		{
			if(g == 0) return 0;
			id = (id-left)/4;
			if(left == 1) id++;
			g--;
			left = id%4;
		}
		return g;
	}

}

