

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class BeautifulString{
    public boolean isBeautiful(char[] s){
    	int i = 0 , j = i + 1 ;
    	while(j < s.length && s[j] == s[i]) j++;
    	if(j >= s.length) return false;  
    	int k = j + 1;
    	while(k < s.length && s[k] == s[j]) k++;    	
    	if(k >= s.length) return false;  	
    	int l = k + 1;   	
    	while(l < s.length && s[l] == s[k]) l++;
    	
        while(k < s.length){        	
        	if(k - j <= j - i && k - j <= l - k && s[j] - s[i] == 1 && s[k] - s[j] == 1)
        		return true;
        	i = j;
        	j = k;
        	k = l;
        	l = k + 1;       	
        	while(l < s.length && s[l] == s[k]) l++;
        }
        return false;
    }
    public static void main(String[] args) throws IOException{
        BeautifulString bs = new BeautifulString();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for(int i = 0 ;i < n ; i++){
            int len = Integer.parseInt(reader.readLine());
            char[] s = new char[len];
            reader.read(s);
            reader.readLine();
            if (bs.isBeautiful(s))  System.out.println("YES");
            else 				    System.out.println("NO");
        }
    }
}
