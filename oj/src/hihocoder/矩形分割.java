package hihocoder;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


public class 矩形分割 {
	// Scanner scan = new Scanner(System.in);
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public  int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public  double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public  String next()throws IOException {in.nextToken();return (String)in.sval;} 	
	DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
	int N;
	int M;
	class UnionFind {
		int[] id ;
		int[] size;
		public UnionFind(int n){
			id = new int[n];
			size = new int[n];
			for(int i = 0 ; i <= N ; i++){
				id[id(i,0)] = 0;
				id[id(i,M)] = 0;
			}
			for(int j = 0 ; j <= M ; j++){
				id[id(0,j)] = 0;
				id[id(N,j)] = 0;
			}	
			size[0] = 2*(N+1)+2*(M+1) - 4;

			for(int i = 1 ; i <= N-1 ; i++){
				for(int j = 1 ; j <= M-1 ; j++){
					id[id(i,j)] = id(i,j);
					size[id(i,j)] = 1;
				}
			}
		}
		public  int findRoot(int p){
			while(id[p] != p){
				p = id[p];
			}
			return p;
		}
		public  boolean union(int p, int q) {
			int pRoot = findRoot(p);
			int qRoot = findRoot(q);
			if(pRoot == qRoot) return false;
			assert( (id[pRoot] == pRoot) &(id[qRoot] == qRoot));
			if(size[pRoot]>size[qRoot]){//q并入p
				id[qRoot] = pRoot;
				size[qRoot]++;
			}
			else{//p并入q
				id[pRoot] = qRoot;
				size[pRoot]++;
			}
			return true;
		}
	}
	public static void main(String[] args) throws IOException{	
		矩形分割 main = new 矩形分割();
		main.input();
		main.run();		
	}
	private void input() throws IOException{
		String[] line = reader.readLine().split("\\s+");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		int cnt = 1;
		UnionFind uf = new UnionFind((N+1)*(M+1));	
		for(int i = 0 ; i < N ; i++){
			for(int j = 0 ; j < M ; j++){
				char c = (char)reader.read();
				if(c == '/' && !uf.union(id(i+1,j),id(i,j+1)))
					cnt++;								
				else if(c == '\\' && !uf.union(id(i,j), id(i+1,j+1)))
					cnt++;											
			}
			reader.readLine();
		}
		System.out.println(cnt);
	}
	public int id(int i , int j){
		return i * (M+1) +j;
	}	
	public void  run(){	

	}
	
}



