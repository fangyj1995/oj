

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HamliCycle
{
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		List<Integer> edge[] = new List[n];
		for (int i = 0 ; i < n ; i++)
			edge[i] = new ArrayList<Integer>();
		for (int i = 0 ; i < m ; i ++)
		{
			int x = scan.nextInt();
			int y = scan.nextInt();
			x--; y--;
			boolean flag = false;
			Iterator<Integer> it = edge[x].iterator();
			while (it.hasNext())
			{
				int tmp = it.next();
				if (tmp == y) flag = true;
			}
			if (!flag) edge[x].add(y);
		}
		int tot = 0;
		for (int i = 0 ; i < n ; i++)
		{
			int maxs = (1 << n);
			int dp[][] = new int[maxs][n];
			for (int j = 0 ; j < maxs ; j++)
				for (int k = 0 ; k < n ; k++)
					dp[j][k] = 0;
			dp[1<<i][i] = 1;
			for (int j = (1 << i) ; j < maxs ; j++)
				for (int k = 0 ; k < n ; k++)
				{
					if (dp[j][k] == 0) continue;
					Iterator<Integer> it = edge[k].iterator();
					while (it.hasNext())
					{
						int nx = it.next();
						if (((1 << nx) & j) > 0) continue;
						dp[j | (1 << nx)][nx] += dp[j][k];
					}
				}
		
			for (int k = 0 ; k < n ; k++)
			{
				if (dp[maxs-1][k] == 0) continue;
				boolean flag = false;
				Iterator<Integer> it = edge[k].iterator();
				while (it.hasNext())
				{
					int nx = it.next();
					if (nx == i) flag = true;
				}
				if (flag)
					tot += dp[maxs-1][k];
			}
		}
		System.out.println(tot / n);
	}
}
