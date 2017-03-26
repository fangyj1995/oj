package hihocoder;
import java.io.*;



public class 图片排版 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
	static int N,M;
	static int[] W;
	static int[] H;
 	public static void main(String[] args) throws IOException{
		M = nextInt();
		N = nextInt();
		W = new int[N+1];
		H = new int[N+1];
		for(int i = 1 ; i <= N; i++){
			W[i] = nextInt();
			H[i] = nextInt();			
		}
		slove();
	}
	private static void slove() {
		int[] accHeight = new int[N+1];
		int[] height = new int[N+1];
		int[] width = new int[N+1];
		boolean[] caled = new boolean[N+1];
		width[0] = M;		
		int totalH = calTotal(accHeight,height,width,caled);		
		int min = Integer.MAX_VALUE;
		for(int i = 1 ; i <= N; i++){
			//获取已放入前i-1张图片时排版的状态
			int h = height[i - 1];
			int w = width[i - 1];
			if(w == 0) {
				w = M;
				h = 0;
			}
			int accH = accHeight[i - 1] - h;		
			for(int j = i + 1; j <= N; j++){
				if(w == M && caled[j]){//从j开始新一行的状态已经计算过了
					accH += (totalH - accHeight[j - 1]);
					break;
				}
				if(W[j] <= w){
					h = Math.max(h, H[j]);
					w -= W[j];
				}
				else{
					int scaleHeight = (int)Math.ceil((double)H[j] * (double)w / (double)W[j]);
					h = Math.max(h,scaleHeight);
					w = 0;
				}
				if(w == 0) {//放满了就下一行
					accH += h;
					w = M;
					h = 0;				
				}	
			}
			if(w != 0) accH += h;
			min = Math.min(min, accH);
		}
		System.out.println(min);
	}
	public static int calTotal(int[] accHeight,int[] height,int[] width,boolean[] caled){
		int w = M;//当前行纸张剩余宽度
		int h = 0;//当前行的高度
		int accH = 0;//累计行高
		for(int i = 1 ; i <= N ; i++){
			if(W[i] <= w){//直接放入
				h = Math.max(h, H[i]);
				w -= W[i];
			}
			else{//放不下，缩放后再放入
				int scaleHeight = (int)Math.ceil((double)H[i] * (double)w / (double)W[i]);
				h = Math.max(h,scaleHeight);
				w = 0;
			}
			accHeight[i] = accH + h;//前i张图片的累计排版高度就是之前累计行高加上当前行高
			width[i] = w;//放入第i张图片后当前行剩余宽度
			height[i] = h;//放入第i张图片后当前行高度
			if(w == 0) {//放满了就下一行
				accH += h;//当一行满的时候才更新累计行高
				w = M;
				h = 0;	
				if(i < N) caled[i+1] = true;
			}			
		}
		if(w != 0) accH += h;//全部图片放完了之后还有空位
		return accH;
	}
}

