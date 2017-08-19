
import java.io.*;

public class NumericKeyPad {
	// Scanner scan = new Scanner(System.in);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public  double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public  String next()throws IOException {in.nextToken();return (String)in.sval;} 	
	
	int n;
	String[] targets;
	boolean found;
	String res;
	int[][] pad = new int[][]{
		{0},
		{9,8,7,6,5,4,3,2,1,0},
		{9,8,6,5,3,2,0},
		{9,6,3},
		{9,8,7,6,5,4,0},
		{9,8,6,5,0},
		{9,6},
		{9,8,7,0},
		{9,8,0},
		{9}
	};
	public static void main(String[] args) throws IOException{	
		NumericKeyPad main = new NumericKeyPad();
		main.input();
		main.run();
	}
	public void  run(){
		for(int i = 0 ; i < n ; i++)
		{
			StringBuilder num = new StringBuilder();
			res = "";
			found = false;
			for(int next : pad[1]){
				dfs(num,targets[i],0,next);
			}		
			System.out.println(res);
		}
	}
	public void dfs(StringBuilder curNum, String target,int d,int num){
		if(found) return;
		String curN = curNum.toString();
		
		if(curN.compareTo(target) > 0){
			return;
		}
		if(curN.length() == target.length()){
			if(curN.compareTo(res)> 0){
				found = true;
				res = curN;
			}
			return;
		}
		curNum.append(num);
		for(int next : pad[num]){
			dfs(curNum, target,d+1,next);
		}
		curNum.deleteCharAt(curNum.length()-1);
	}
	
	
	public void dfs(StringBuilder curNum, String target, int len, int d,boolean canBigger){
		if(d > len-1)	return;
		int preNum = 1;
		if(d!=0){
			preNum = curNum.charAt(d-1)-'0';
		}
		int curTarget = target.charAt(d)-'0';		
		if(canBigger){//true
			for(int i : pad[preNum]){		
				if(i <= curTarget){	
					curNum.append(i);
					canBigger = i == curTarget;					
					dfs(curNum,target,len,d+1,canBigger);
					if(curNum.length() == len)
						return;
					curNum.deleteCharAt(curNum.length()-1);
				}			
			}
		}
		else{
			if(preNum != 0)
				curNum.append(9);
			else
				curNum.append(0);
			dfs(curNum,target,len,d+1,false);
		}
	}
	private void input() throws IOException{
		n = Integer.parseInt(reader.readLine());
		targets = new String[n];
		for(int i = 0 ; i < n ; i++)
			targets[i] = reader.readLine();
	}
}


