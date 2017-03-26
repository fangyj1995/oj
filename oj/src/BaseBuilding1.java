import java.util.*;
import java.io.*;

public class BaseBuilding1 {

	Scanner scan;

	public void solve(){
		int n = scan.nextInt();
		int m = scan.nextInt();
		int k = scan.nextInt();
		int t = scan.nextInt();
		int a[] = new int[m];
		int b[] = new int[m];
		for(int i = 0 ; i < m ; i ++){
			a[i] = scan.nextInt();
		}
		for(int i = 0 ; i <  m ; i ++){
			b[i] = scan.nextInt();
		}
		int ans = 0;
		for(int idx = 0 ; idx < n ; idx ++){
			int dp[] = new int[k+1];
			Arrays.fill(dp , - 1);
			
			for(int i = 0 ; i < m ; i ++){
				for(int j = 1 ; j <= k ; j ++){
					//dp[j] = min(dp[j-b[i]] + a[i] , dp[j])
					if(j <= b[i]){
						if(dp[j] == -1){
							dp[j] = a[i];
						}else {
							dp[j] = Math.min(dp[j] , a[i]);
						}
					}else{
						if(dp[j-b[i]] != -1){
							if(dp[j] == -1){
								dp[j] = dp[j-b[i]] + a[i];
							}else{
								dp[j] = Math.min(dp[j-b[i]] + a[i] , dp[j]);
							}
						}
					}
				}
			}

			if(dp[k] == -1){
				System.out.println("No Answer");
				return;
			}else{
				ans += dp[k];
				for(int i = 0 ; i < m ; i ++){
					b[i] = b[i] / t;
				}
			}
		}
		System.out.println(ans);
	}
	
	public void run(){

		scan = new Scanner(System.in);
		int q = scan.nextInt();
		for(int i = 0 ; i < q ; i ++){
			solve();
		}
	}


	public static void main(String args[]){
		new BaseBuilding1().run();
	}
}