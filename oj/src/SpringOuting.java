import java.util.*;

public class SpringOuting {
	static int n;
	static int k;
	static int result;
	static int[][] rank;
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		k = scan.nextInt();
		rank = new int[n][k + 1];//地点ki在ni心中的排名
		for(int i = 0 ; i < n ; i++){
			for(int j = 0; j < k + 1; j++){
				int place = scan.nextInt();
				rank[i][place] = j;
			}
		}
		vote();		
		System.out.println(result == 0? "otaku":result);
	}
	private static void vote() {	
		for(int place = k; place > 0 ; place--){
			int vote = 0;//根据place+1的投票结果对place投票
			for(int peo = 0 ; peo < n ; peo++){
				System.out.println("peo "+peo+"　：　"+1+" rank of "+place+" "+rank[peo][place]+" , rank of "+result +" "+rank[peo][result]);
				if(rank[peo][place] < rank[peo][result]) vote++;
			}
			System.out.println("vote  of " +place+" is " + vote);
			if(vote > n/2)	{
				result = place;
			}
		}		
	}
}
