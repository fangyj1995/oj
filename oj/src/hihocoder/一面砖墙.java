package hihocoder;


import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;


public class 一面砖墙 {
	// Scanner scan = new Scanner(System.in);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public  double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public  String next()throws IOException {in.nextToken();return (String)in.sval;} 	
	DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
	int n;
	TreeMap<Integer,Integer> map;
	int width;
	public static void main(String[] args) throws IOException{	
		一面砖墙 main = new 一面砖墙();
		main.input();
		main.run();		
	}
	private void input() throws IOException{
		n = nextInt();
		map = new TreeMap<>();
		for(int i = 0 ; i < n ; i++){
			int w = nextInt();
			int now = 0;			
			for(int j = 0 ; j < w ; j++){
				now += nextInt();
				width = Math.max(width, now);
				if(map.containsKey(now))
					map.put(now, map.get(now)+1);
				else
					map.put(now, 1);
			}		
		}
	}
	public void  run(){	
		int min = n;
		for(Entry<Integer, Integer>  entry: map.entrySet()){
			if(entry.getKey() == width) continue;
			min = Math.min(min, n-entry.getValue());
		}
		System.out.println(min);
	}	
}


