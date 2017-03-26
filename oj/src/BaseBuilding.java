import java.util.Scanner;

public class BaseBuilding {
	static int k,n,m,t;
	static int[] cost,val;
	public static int minCost(){
		int[] f = new int[k + 1];
		int res = 0;
		for(int c = 0 ; c < n ; c++){//每一个建设级别
			for(int i = 0 ; i < f.length ; i++){
				f[i] =  Integer.MAX_VALUE;
			}
			f[0] = 0;
			for(int i = 0 ; i < m; i++){//每一种金属
				if(val[i] == 0) continue;
				for(int value = val[i] ; value <= val[i] + k ; value++){
					int x = value > k ? k : value;
					if(f[x - val[i]] != Integer.MAX_VALUE)
						f[x] = Math.min(f[x], f[x - val[i]]+cost[i]);
					System.out.println("f("+i+","+value+") = " + f[x]);
				}				
			}
			for(int i = 0 ; i < m ; i++){
				val[i] = val[i]/2;
			}
			if(f[k] >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
			res += f[k];
		}
		
		return res;
	}
	public static int minCost1(){
		int[][] f = new int[m][k + 1];
		int res = 0;
		for(int c = 0 ; c < n ; c++){//每一个建设级别
			for(int i = 0 ;i < m; i++){
				for(int j = 0 ; j <= k ; j++){
					f[i][j] = Integer.MAX_VALUE;
				}
			}
			for(int i = 0 ;i < m ; i++)
				for(int j =0 ; j < val[i] ; j++){
					f[i][j] = 0;
				}
				//f[i][0] = 0;
			for(int i = 0 ; i < m; i++){//每一种金属
				if(val[i] == 0) continue;
				for(int j = val[i] ; j <= val[i] + k ; j++){
					int x = Math.min(j, k);
					if(i == 0)  f[i][x] = f[i][x - val[i]]+cost[i];
					else if(f[i][x - val[i]] != Integer.MAX_VALUE) 
						 f[i][x] = Math.min(f[i - 1][x], f[i][x - val[i]]+cost[i]);
					else 
						f[i][x] = f[i - 1][x];
					//System.out.println("f("+i+","+j+") = " + f[i][x]);
				}
			}
			for(int i = 0 ; i < m ; i++){
				val[i] = val[i]/2;
			}
			if(f[n - 1][k] == Integer.MAX_VALUE) return Integer.MAX_VALUE;
			res += f[n - 1][k];
		}
		return res;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();	
		for(int i = 0 ; i < cases ; i++){
			n = scan.nextInt();
			m = scan.nextInt();
			k = scan.nextInt();
			t = scan.nextInt();
			cost = new int[m];
			val = new int[m];
			for(int j = 0; j < m; j++)
				cost[j] = scan.nextInt();			
			for(int j = 0; j < m; j++)
				val[j] = scan.nextInt();
			int res = minCost1();
			//minCost1();
			if(res == Integer.MAX_VALUE)
				System.out.println("No Answer");
			else 
				System.out.println(res);
		}
		scan.close();
	}

}
