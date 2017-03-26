import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DiskStroage {
	static Scanner scan = new Scanner(System.in);
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	static int N,M,H,R;
	static int[] radix;
	static int[][] seg;
	static int cnt = 0;
	public static void main(String[] args) throws IOException, ParseException {
		N = nextInt();
		M = nextInt();
		H = nextInt();
		R = nextInt();
		radix = new int[N];
		seg = new int[N][2];
		for(int i = 0 ; i < N; i++)
			radix[i] = nextInt();
		
		int ans = solve();
		System.out.println(ans);
	}
	private static int solve() {
		Arrays.sort(radix);
		seg[0][0] = 0;
		for(int i = 1 ; i < N; i++)
		{
			if(radix[i] - radix[i - 1] > M){
				seg[cnt++][1] = i - 1;
				seg[cnt][0] = i;
			}
		}	
		seg[cnt][1] = N - 1;
		for(int i = cnt ; i >= 0 ; i--){//尝试从最大的段开始放
			if(radix[seg[i][0]] > R) continue;//找到第一个能放的分段
			
			int index = 0;
			for(int j = seg[i][0] ; j <= seg[i][1]; j++){
				if(radix[j] > index + R) {//这个段放了一半
					return Math.min(H, j);
				}
				else index++;
			}
			//这个段已经放完了
			return  Math.min(H,seg[i][1]+1);
		}
		return 0;
	}

}

