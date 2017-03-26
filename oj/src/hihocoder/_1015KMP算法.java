package hihocoder;
import java.util.Scanner;

public class _1015KMP算法 {
	public static int kmp(String pat,String txt)
	{
		int i=0;int j=0;
		int n=txt.length();
		int m=pat.length();
		int [] next=next(pat);
		int count=0;
		while(i<n)
		{
			if(pat.charAt(j)==txt.charAt(i))
			{
				i++;
				if(j==m-1){
					count++;
					j=next[j+1];				    
				}
				else j++;				
			}
			else{
				j=next[j];
				if(j==0&&pat.charAt(j)!=txt.charAt(i))
					i++;									
			}			
		}		
		return count;
	}
	public static int subStringCount(String pattern,String txt)//暴力字符串匹配
	{
		int i=0,j=0;
		int n=txt.length();
		int m=pattern.length();int count=0;
		while(j<m&&i<n){
			if(pattern.charAt(j)==txt.charAt(i))
			{
				i++;j++;
				if(j==m) count++;
			}
			else{
				i=i-j+1;j=0;
			}
		}
		return count;
	}
	public static int[] next(String pat)
	{
		int[] next=new int[pat.length()+1];//next[i]是当i指示的字符不匹配时，指针j应该回退的位置。
		if(pat.length()<=2)
			return next;	
		 for(int i=2;i<pat.length()+1;i++)
        {
        	char o=pat.charAt(i-1);
        	int j=next[i-1];       	
        	while(j!=0&&pat.charAt(j)!=o)
        	{
        		j=next[j];
        	}
        	if(pat.charAt(j)==o)
        	{
        		next[i]=j+1;
        	}
        	else next[i]=0;
        }
		return next;		
	}
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		for(int i=0;i<n;i++)
		{
			String pat=in.next();
			String txt=in.next();
			int count=0;				
			count = kmp(pat,txt);	
			System.out.println(count);
		}
	}

}
